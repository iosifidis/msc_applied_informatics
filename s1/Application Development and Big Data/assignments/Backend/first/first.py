from pyspark.sql import SparkSession
from pyspark.sql.functions import  col, regexp_replace, upper, round
# from pyspark.sql.functions import round

# Δημιουργία ενός SparkSession
spark = SparkSession.builder.appName("RetailTransactionProcessing").getOrCreate()

# Φόρτωση του αρχείου CSV σε DataFrame. Ο κατάλογος είναι αυτός μέσα από το docker
df = spark.read.option("header", "true").csv("file://///opt/spark/work-dir/customer_shopping_data.csv")
#df = spark.read.option("header", "true").csv("customer_shopping_data.csv")
#df = spark.read.option("header", "true").csv("file:///home/bigdata/customer_shopping_data.csv")

# 1. Αντικατάσταση του κενό με κάτω παύλα στις στήλες payment_method και shopping_mall
df = df.withColumn("payment_method", regexp_replace(col("payment_method"), " ", "_"))

df= df.withColumn("shopping_mall", regexp_replace(col("shopping_mall"), " ", "_"))

# 2. Μετατροπή των ονομάτων του shopping_mall σε κεφαλαία
df = df.withColumn("shopping_mall", upper(col("shopping_mall")))

# 3. Υπολογισμός του πλήθους των συναλλαγών
transaction_count = df.count()
print(f"Πλήθος συναλλαγών: {transaction_count}")

# 4. Μετατροπή της τιμής από TL σε ευρώ (1 TL = 0.1 EUR)
df = df.withColumn("price_eur", round(col("price") * 0.1, 2))
#df = df.withColumn("price_eur", col("price") * 0.1)

# 5. Υπολογισμός του πλήθους των συναλλαγών ανά πλήθος αντικειμένων και αποθήκευση σε CSV
transaction_items_count = df.groupBy("quantity").count()
#transaction_items_count.write.csv("transactions_by_item_count", header=True)
transaction_items_count.write.format('csv').option("header", True).mode('overwrite').save("transactions_by_item_count")

# 6. Υπολογισμός του συνολικού ποσού που δαπανήθηκε και αποθήκευση σε αρχείο CSV
df = df.withColumn("total", col("price") * col("quantity"))
#df.write.csv("transactions_with_total", header=True)
df.write.format('csv').option("header", True).mode('overwrite').save("transactions_with_total")

# Σταματάμε το SparkSession μετά την ολοκλήρωση
spark.stop()

