from pyspark.sql import SparkSession
from pyspark.sql.types import StructType
from pyspark.sql.functions import col, sum, when

# Δημιουργία ενός SparkSession
spark = SparkSession.builder.appName("Streaming").getOrCreate()

# Ορισμός του σχήματος για το αρχείο με τα υφιστάμενα στατιστικά
existing_stats_schema = (
    StructType()
    .add('shopping_mall', 'string')
    .add('sum_total', 'double')  # Υφιστάμενα στατιστικά
    .add('sum_quantity', 'integer')
    .add('transaction_count', 'integer')
)

# Φόρτωση του αρχείου με τα υφιστάμενα στατιστικά
existing_stats_df = (
    spark.read
    .option("header", "true")
    .schema(existing_stats_schema)
    .csv("/opt/spark/work-dir/shopping_mall_statistics/*.csv")
)

# Ορισμός του σχήματος για το streaming DataFrame
streaming_schema = (
    StructType()
    .add('shopping_mall', 'string')
    .add('total_spent', 'double')  # Νέα δεδομένα
    .add('total_quantity', 'integer')
    .add('transactions', 'integer')
)

# Διαβάζουμε τα νέα δεδομένα ως streaming
streaming_df = (
    spark.readStream
    .format("csv")
    .option('sep', ',')
    .option("header", "true")
    .schema(streaming_schema)
    .option("path", "/opt/spark/work-dir/input_frontend/*.csv")
    .load()
)

# Σύνδεση των δύο πινάκων με βάση τη στήλη shopping_mall
def process_batch(df_batch, batch_id):
    # Σύνδεση των δύο πινάκων με βάση τη στήλη shopping_mall
    combined_df = existing_stats_df.join(
        df_batch,
        on="shopping_mall",
        how="outer"
    )
    
    # Υπολογισμός των συνολικών στατιστικών
    updated_stats_df = combined_df.groupBy("shopping_mall").agg(
        (
            sum(when(col("sum_total").isNotNull(), col("sum_total")).otherwise(0)) +
            sum(when(col("total_spent").isNotNull(), col("total_spent")).otherwise(0))
        ).alias("total_spent"),
        
        (
            sum(when(col("sum_quantity").isNotNull(), col("sum_quantity")).otherwise(0)) +
            sum(when(col("total_quantity").isNotNull(), col("total_quantity")).otherwise(0))
        ).alias("total_quantity"),
        
        (
            sum(when(col("transaction_count").isNotNull(), col("transaction_count")).otherwise(0)) +
            sum(when(col("transactions").isNotNull(), col("transactions")).otherwise(0))
        ).alias("transactions")
    )
    
    # Αποθήκευση των νέων στατιστικών σε CSV
    updated_stats_df.write.mode("overwrite").format('csv').option("header", True).save("/opt/spark/work-dir/updated_stats")

# Εκκίνηση της streaming εφαρμογής
query = (
    streaming_df.writeStream
    .outputMode("update")
    .foreachBatch(process_batch)
    .option("checkpointLocation", "/opt/spark/work-dir/checkpoint")
    .start()
)

# Αναμονή για την ολοκλήρωση της ροής
query.awaitTermination()
