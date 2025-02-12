from pyspark.sql import SparkSession
from pyspark.sql.functions import col, sum, count, round

# Δημιουργία ενός SparkSession
spark = SparkSession.builder.appName("StatisticsTable").getOrCreate()

# Φόρτωση του αρχείου CSV σε DataFrame. Ο κατάλογος είναι αυτός μέσα από το docker
df = spark.read.option("header", "true").csv("file://///opt/spark/work-dir/customer_shopping_data.csv")
#df = spark.read.option("header", "true").csv("file:///home/bigdata/customer_shopping_data.csv")

# Υπολογισμός του συνολικού ποσού για κάθε εγγραφή
df = df.withColumn("quantity", col("quantity").cast("int"))
df = df.withColumn("price", col("price").cast("double"))
#df = df.withColumn("total", col("price") * col("quantity"))
df = df.withColumn("total", round(col("price") * col("quantity"), 2))

# Υπολογισμός των στατιστικών ανά εμπορικό κέντρο
total_spent = df.groupBy("shopping_mall").sum("total")
total_quantity = df.groupBy("shopping_mall").sum("quantity")
total_transactions = df.groupBy("shopping_mall").count()

# Ενοποίηση των αποτελεσμάτων σε ένα DataFrame
statistics_df = total_spent.join(total_quantity, "shopping_mall").join(total_transactions, "shopping_mall")

# Αποθήκευση του στατιστικού πίνακα σε αρχείο CSV
statistics_df.write.format('csv').option("header", True).mode('overwrite').save("shopping_mall_statistics")

# Σταματάμε το SparkSession μετά την ολοκλήρωση
spark.stop()
