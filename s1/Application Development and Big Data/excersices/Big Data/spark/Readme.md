# Εισαγωγή στο Apache Spark

## Τι είναι το Apache Spark;
Το Apache Spark είναι ένα σύστημα υπολογισμών υψηλής ταχύτητας και κατανεμημένο σύστημα επεξεργασίας δεδομένων. Χρησιμοποιείται για την επεξεργασία μεγάλων δεδομένων με μεγάλη ταχύτητα και ευελιξία.

### Χαρακτηριστικά
- **Υψηλή Απόδοση**: Έως 100 φορές πιο γρήγορο από το Hadoop.   
- **Γλώσσες**: Υποστηρίζει Python, Java και R.   
- **Ενσωμάτωση**: Συνεργάζεται με HDFS, Cassandra, Hive, κ.ά.   
- **Κατανεμημένη Επεξεργασία**: Υποστηρίζει παράλληλη εκτέλεση εργασιών σε clusters.   

---

## Αρχιτεκτονική Spark

### Driver
- Τρέχει τη λογική της εφαρμογής.   
- Επικοινωνεί με τον Cluster Manager για την κατανομή εργασιών.

### Cluster Manager
- Συντονίζει το cluster και διαχειρίζεται τους πόρους.   
- Συνεργάζεται με YARN, Mesos, και αυτόνομους διαχειριστές.

### Executors
- Εκτελούν εργασίες και αποθηκεύουν δεδομένα προσωρινά στη μνήμη ή στο δίσκο.

### SparkSession
- Παρέχει πρόσβαση στα APIs RDD, DataFrame και Dataset.   
- Δημιουργείται με τη μέθοδο `builder`:
  ```python
  from pyspark.sql import SparkSession
  spark = SparkSession.builder.appName("Example").getOrCreate()

---

## Βασικές Έννοιες

### RDD (Resilient Distributed Datasets)

- Τι είναι: Κατανεμημένα και αμετάβλητα datasets.  
- Βασικές λειτουργίες:   

  - Transformations: Δημιουργούν νέο RDD από υπάρχον (π.χ. map, filter).   
  - Actions: Επιστρέφουν αποτελέσματα ή αποθηκεύουν δεδομένα (π.χ. count, collect).

- Παράδειγμα:
```
rdd = sc.textFile("hdfs://myfile.txt")
filtered_rdd = rdd.filter(lambda x: len(x) > 5)
result = filtered_rdd.collect()
```

---

## DataFrame και Spark SQL

### DataFrame
- Ορισμός: Πίνακας δεδομένων με γραμμές και στήλες, παρόμοιος με έναν πίνακα SQL.   
- Χρήση:
```
df = spark.read.csv("file.csv", header=True)
df.filter(df['age'] > 30).show()
```

### Spark SQL
- Εκτέλεση ερωτημάτων SQL σε DataFrames:
```
df.createOrReplaceTempView("people")
spark.sql("SELECT * FROM people WHERE age > 30").show()
```

---

## Spark ML και Streaming

### MLlib (Machine Learning)
- Παρέχει αλγόριθμους για clustering, classification και regression.   
- Παράδειγμα:
```
from pyspark.ml.classification import LogisticRegression
lr = LogisticRegression()
model = lr.fit(training_data)
```

### Spark Streaming
- Επεξεργάζεται δεδομένα σε πραγματικό χρόνο.
- Παράδειγμα:
```
from pyspark.streaming import StreamingContext
ssc = StreamingContext(sc, 10)
lines = ssc.socketTextStream("localhost", 9999")
```

---

## Παραδείγματα Κώδικα

### Δημιουργία RDD
```
rdd = sc.textFile("file.txt")
filtered_rdd = rdd.filter(lambda x: "error" in x)
result = filtered_rdd.count()
print(f"Number of errors: {result}")
```

### WordCount στο PySpark
```
from pyspark.sql import SparkSession
from operator import add

if __name__ == "__main__":
    spark = SparkSession.builder.appName("WordCount").getOrCreate()
    sc = spark.sparkContext
    lines = sc.textFile("file.txt")
    counts = lines.flatMap(lambda x: x.split(' ')) \
                  .map(lambda x: (x, 1)) \
                  .reduceByKey(add)
    for word, count in counts.collect():
        print(f"{word}: {count}")
    sc.stop()
```

---

# Προχωρημένα Θέματα Apache Spark

## Περιεχόμενα
1. [Βελτίωση Επίδοσης](#βελτίωση-επίδοσης)
    - [Διατήρηση Δεδομένων](#διατήρηση-δεδομένων)
    - [Μετάδοση Δεδομένων](#μετάδοση-δεδομένων)
    - [Συσσώρευση Δεδομένων](#συσσώρευση-δεδομένων)
2. [Επεξεργασία Δομημένων Δεδομένων](#επεξεργασία-δομημένων-δεδομένων)
3. [Εξόρυξη Δεδομένων και Ανακάλυψη Γνώσης](#εξόρυξη-δεδομένων-και-ανακάλυψη-γνώσης)

---

## Βελτίωση Επίδοσης

### Διατήρηση Δεδομένων
- **Προσωρινή Αποθήκευση (Caching)**:
  - Βελτιώνει την ταχύτητα μειώνοντας επαναλαμβανόμενους υπολογισμούς.
  - Επίπεδα αποθήκευσης:
    - `MEMORY_ONLY`: Διατήρηση μόνο στη μνήμη.
    - `MEMORY_AND_DISK`: Αν η μνήμη γεμίσει, χρησιμοποιείται ο δίσκος.
  - Παράδειγμα:
    ```python
    my_rdd = sc.textFile("file.txt").filter(lambda x: len(x) > 3)
    my_rdd.persist(StorageLevel.MEMORY_AND_DISK)
    print(my_rdd.count())  # Πρώτη ενέργεια
    my_rdd.saveAsTextFile("output.txt")  # Δεύτερη ενέργεια
    my_rdd.unpersist()  # Αποδέσμευση πόρων
    ```

### Μετάδοση Δεδομένων
- Χρησιμοποιείται για τη βελτιστοποίηση της μεταφοράς δεδομένων από τον Driver στους Executors.
- **Broadcast Variables**: Μεταδίδουν δεδομένα στους Executors για ανάγνωση.
  - Παράδειγμα:
    ```python
    broadcast_var = sc.broadcast([1, 2, 3])
    rdd = sc.parallelize([1, 2, 3, 4, 5])
    filtered_rdd = rdd.filter(lambda x: x in broadcast_var.value)
    print(filtered_rdd.collect())
    ```

### Συσσώρευση Δεδομένων
- **Accumulators**: Χρησιμοποιούνται για τη συγκέντρωση δεδομένων από πολλές εργασίες.
- Παράδειγμα:
  ```python
  accum = sc.accumulator(0)
  rdd = sc.parallelize([1, 2, 3, 4])
  rdd.foreach(lambda x: accum.add(x))
  print(accum.value)  # Εκτυπώνει το σύνολο των τιμών

### Επεξεργασία Δομημένων Δεδομένων
- Χρήση του DataFrame API για ευκολία και καλύτερη απόδοση.   
- Παράδειγμα δημιουργίας DataFrame:
```
from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("Example").getOrCreate()
df = spark.read.csv("people.csv", header=True, inferSchema=True)
df.show()
df.printSchema()
```

### Μετασχηματισμοί DataFrame
- filter: Φιλτράρει δεδομένα.
```
result = df.filter(df["age"] > 28)
result.show()
```

- select: Επιλέγει συγκεκριμένες στήλες.
```
result = df.select("name", "age")
result.show()
```

### Ερωτήματα SQL
- Δημιουργία προσωρινής όψης και εκτέλεση SQL ερωτήματος:
```
df.createOrReplaceTempView("people")
result = spark.sql("SELECT * FROM people WHERE age > 28")
result.show()
```

### Εξόρυξη Δεδομένων και Ανακάλυψη Γνώσης
- Εφαρμογή μηχανικής μάθησης μέσω του Spark ML.
- Παράδειγμα ταξινόμησης κειμένου με Logistic Regression:
```
from pyspark.ml.feature import Tokenizer, HashingTF, IDF
from pyspark.ml.classification import LogisticRegression

tokenizer = Tokenizer(inputCol="text", outputCol="words")
words_df = tokenizer.transform(df)

hashing_tf = HashingTF(inputCol="words", outputCol="rawFeatures")
featurized_data = hashing_tf.transform(words_df)

idf = IDF(inputCol="rawFeatures", outputCol="features")
idf_model = idf.fit(featurized_data)
final_data = idf_model.transform(featurized_data)

lr = LogisticRegression(featuresCol="features", labelCol="label")
model = lr.fit(final_data)
predictions = model.transform(final_data)
predictions.show()
```

---

## Συμπεράσματα
- Το Spark επιτρέπει την αποδοτική επεξεργασία μεγάλων δεδομένων.   
- Τα εργαλεία βελτιστοποίησης όπως caching, broadcasting, και accumulators βελτιώνουν την απόδοση.   
- Η χρήση του DataFrame API απλοποιεί την επεξεργασία δομημένων δεδομένων.   
- Το Spark ML παρέχει ισχυρά εργαλεία για ανάλυση και εξόρυξη γνώσης.

---

## Επεξήγηση εντολών

Δείτε στο αρχείο [Εντολές](Commands.md)

---

## Πηγές

- [Apache Spark Official Documentation](https://spark.apache.org/docs/latest/)   

