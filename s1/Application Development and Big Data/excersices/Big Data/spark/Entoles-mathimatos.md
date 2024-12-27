# PySpark Shell

## Περιεχόμενα
1. [Ξεκινώντας με το PySpark Shell](#ξεκινώντας-με-το-pyspark-shell)
2. [Actions στο RDD](#actions-στο-rdd)
3. [Transformations στο RDD](#transformations-στο-rdd)
4. [Μετρήσεις λέξεων σε RDD](#μετρήσεις-λέξεων-σε-rdd)
5. [Δομημένο API με DataFrames](#δομημένο-api-με-dataframes)
6. [Λειτουργίες σε DataFrames](#λειτουργίες-σε-dataframes)
7. [Χρήση SQL σε DataFrames](#χρήση-sql-σε-dataframes)
8. [Αποθήκευση DataFrame σε αρχείο](#αποθήκευση-dataframe-σε-αρχείο)

## Ξεκινώντας με το PySpark Shell

1. Ανοίξτε το PySpark interactive shell:
```
pyspark
```

2. Δημιουργία RDD από λίστα:
```
number = sc.parallelize([1, 2, 3, 1])
```

---

## Actions στο RDD

Οι ενέργειες επιστρέφουν δεδομένα ή γράφουν δεδομένα σε εξωτερική πηγή.

**Παράδειγμα 1**: Αριθμός στοιχείων
```
number.count()  # Επιστρέφει 4
```

**Παράδειγμα 2**: Πρώτα N στοιχεία
```
number.take(2)  # Επιστρέφει [1, 2]
```

**Παράδειγμα 3**: Φιλτράρισμα περιττών αριθμών
```
number.filter(lambda x: x % 2 != 0).collect()  # Επιστρέφει [1, 3, 1]
```
**Παράδειγμα 4**: Επιστροφή όλων των στοιχείων
```
number.take(number.count())  # Επιστρέφει [1, 2, 3, 1]
```

---

## Transformations στο RDD

Οι μετασχηματισμοί δημιουργούν νέο RDD.

**Παράδειγμα 1**: Προσθήκη 2 σε κάθε στοιχείο (map)
```
number.map(lambda x: x + 2).collect()  # Επιστρέφει [3, 4, 5, 3]
```

**Παράδειγμα 2**: Χρήση intermediate RDD
```
myrdd1 = number.map(lambda x: x + 2)
myrdd1.collect()  # Επιστρέφει [3, 4, 5, 3]
```

**Παράδειγμα 3**: Δημιουργία πολλών στοιχείων από ένα (flatMap)
```
number.flatMap(lambda x: [x - 1, x, x + 1]).collect() # Επιστρέφει [0, 1, 2, 1, 2, 3, 2, 3, 4, 0, 1, 2]
```

---

## Μετρήσεις λέξεων σε RDD

1. Δημιουργία RDD από αρχείο:
```
dataFile = sc.textFile("/tmp/firewall_rules.sh")
```

2. Διαχωρισμός γραμμών σε λέξεις:
```
words = dataFile.flatMap(lambda line: line.split(" "))
```

3. Καταμέτρηση λέξεων:
```
words.count()  # Επιστρέφει τον αριθμό των λέξεων
```

---

## Δομημένο API με DataFrames

### Φόρτωση Dataset

1. Κατεβάστε και τοποθετήστε το dataset:
```
import os
os.system('wget https://pkgstore.datahub.io/machine-learning/adult/adult_csv/data/c8c87915d76926896a93604761460f22/adult_csv.csv')
os.system('mv adult_csv.csv /tmp/')
```

2. Φορτώστε το CSV ως DataFrame:
```
dataframe_csv = spark.read.csv('/tmp/adult_csv.csv', header=True, inferSchema=True)
```

---

## Λειτουργίες σε DataFrames

**Εμφάνιση δεδομένων**
```
dataframe_csv.show(5)  # Εμφανίζει τις 5 πρώτες γραμμές
```

**Μέτρηση μοναδικών γραμμών**
```
dataframe_csv.distinct().count()  # Επιστρέφει τον αριθμό μοναδικών γραμμών
```

**Επιλογή στήλης**
```
otherdf = dataframe_csv.select('age')
otherdf.show()  # Εμφανίζει τη στήλη age
```

**Μοναδικές ηλικίες**
```
dataframe_csv.select('age').distinct().show()
```

**Συνάθροιση και ταξινόμηση**
```
dataframe_csv.groupBy("age").count().orderBy("count", ascending=False).show()
```

---

## Χρήση SQL σε DataFrames

1. Δημιουργία προσωρινής όψης:
```
dataframe_csv.createOrReplaceTempView("adult")
```

2. Εκτέλεση SQL ερωτήματος:
```
spark.sql("SELECT age, count(*) FROM adult GROUP BY age ORDER BY age ASC").show()
```

---

## Αποθήκευση DataFrame σε αρχείο

1. Εγγραφή σε CSV:
```
otherdf.write.format("csv").mode("append").save("/home/bigdata/sparkout/")
```

  - `format("csv")`: Ορίζει τον τύπο του αρχείου.   
  - `mode("append")`: Προσθέτει δεδομένα στο αρχείο αν υπάρχει ήδη.
  
Αυτές οι οδηγίες καλύπτουν παραδείγματα RDD και DataFrame σε Spark με Python.
