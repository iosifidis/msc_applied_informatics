from pyspark.sql import SparkSession
from pyspark.sql.types import StructType
from pyspark.sql.functions import col, sum, count

# Δημιουργία ενός SparkSession
spark = SparkSession.builder.appName("StructuredStreamingStatistics").getOrCreate()

# Φόρτωση του αρχείου CSV σε DataFrame. Ο κατάλογος είναι αυτός μέσα από το docker ("file://///opt/spark/work-dir/output_frontend.csv")
df = spark.read.option("header", "true").csv("output_frontend.csv")

# Υπολογισμός του συνολικού ποσού για κάθε εγγραφή
df = df.withColumn("quantity", col("quantity").cast("int"))
df = df.withColumn("total", col("price") * col("quantity"))

# Υπολογισμός των στατιστικών ανά εμπορικό κέντρο
total_spent = df.groupBy("shopping_mall").sum("total")
total_quantity = df.groupBy("shopping_mall").sum("quantity")
total_transactions = df.groupBy("shopping_mall").count()

# Ενοποίηση των αποτελεσμάτων σε ένα DataFrame
statistics_df = total_spent.join(total_quantity, "shopping_mall").join(total_transactions, "shopping_mall")

# Αποθήκευση του στατιστικού πίνακα σε αρχείο CSV
statistics_df.write.format('csv').option("header", True).mode('overwrite').save("input_frontend")

# Σταματάμε το SparkSession μετά την ολοκλήρωση
spark.stop()
