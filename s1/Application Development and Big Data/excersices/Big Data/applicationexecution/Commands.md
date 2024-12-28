# Αναλυτικά Σχόλια για Εντολές Εκτέλεσης Εφαρμογών

## Εντολές για τοπική εκτέλεση με PySpark

### 1. Εκκίνηση PySpark σε τοπική διαμόρφωση
```bash
pyspark --master local[*]
```

- Ξεκινά το PySpark με το mode local[*], χρησιμοποιώντας όλους τους πυρήνες της τοπικής μηχανής.

### 2. Ανάγνωση αρχείου CSV

```
my = spark.read.csv('file:///home/bigdata/stocks.csv', header=True, inferSchema=True)
```

- Διαβάζει το αρχείο `stocks.csv`.  
- `header=True`: Χρησιμοποιεί την πρώτη γραμμή ως ονόματα στηλών.   
- `inferSchema=True`: Αυτόματη ανίχνευση τύπων δεδομένων για τις στήλες.

### 3. Εμφάνιση DataFrame
```
my.show()
```

- Προβολή των πρώτων 20 γραμμών του DataFrame.

### 4. Προβολή χωρίς περικοπή περιεχομένων
```
my.show(truncate=False)
```

- Εμφάνιση των δεδομένων χωρίς περικοπή τιμών που υπερβαίνουν το όριο χαρακτήρων.

### 5. Δημιουργία νέας στήλης `DESCRIPTION`
```
from pyspark.sql.functions import concat_ws
newdf = my.withColumn("DESCRIPTION", concat_ws("-", "SYMBOL", 'COMPANY'))
newdf.show(truncate=False)
```

- Συνενώνει τις στήλες `SYMBOL` και `COMPANY` με παύλα (-).

### 6. Μετατροπή χαρακτήρων σε πεζά (χρησιμοποιώ τη συνάρτηση lower())
```
from pyspark.sql.functions import lower
newdf = newdf.withColumn("lower_COMPANY", lower("COMPANY"))
newdf.show(truncate=False)
```

- Δημιουργεί μια νέα στήλη `lower_COMPANY` με το όνομα της εταιρείας σε πεζά γράμματα.

### 7. Υπολογισμός αποτίμησης σε ευρώ
```
newdf = newdf.withColumn("VAL_EUR", newdf.PRICE * newdf.NUM_STOCKS * 0.94)
newdf.show(truncate=False)
```

- Υπολογίζει την αξία μετοχών σε ευρώ, χρησιμοποιώντας ισοτιμία 0.94.

### 8. Στρογγυλοποίηση σε 2 δεκαδικά με τη round
```
from pyspark.sql.functions import round
newdf = newdf.withColumn("VAL_EUR", round(newdf.PRICE * newdf.NUM_STOCKS *
0.94,2))
newdf.show(truncate=False)
```

- Στρογγυλοποιεί την αξία `VAL_EUR` σε δύο δεκαδικά.

### 9. Επιλογή συγκεκριμένων πεδίων με select - εμφανίζονται με τη σειρά που τα επιλέγω
```
out = newdf.select("DESCRIPTION", "VAL_EUR")
out.show()
```

- Επιλέγει μόνο τις στήλες `DESCRIPTION` και `VAL_EUR`.

### 10. Αντικατάσταση παυλών με underscores
```
from pyspark.sql.functions import regexp_replace
out = out.withColumn('DESCRIPTION', regexp_replace('DESCRIPTION', '-', '_'))
out.show(truncate=False)
```

- Αντικαθιστά το σύμβολο `-` με `_` στη στήλη `DESCRIPTION`.

### 11. Εγγραφή αποτελεσμάτων σε αρχείο
```
out.write.format('csv').option("header", True).mode('overwrite').save("file:///home/bigdata/stocks_output")
```

- Αποθηκεύει το DataFrame `out` ως αρχείο CSV στον φάκελο `stocks_output`.

### 12. Έξοδος από το shell του PySpark
```
exit()
```

- Τερματίζει τη συνεδρία PySpark.

---

## Εντολές για εκτέλεση σε cluster

### 1. Εκκίνηση HDFS
```
start-dfs.sh
```

- Ξεκινά τις υπηρεσίες HDFS.

### 2. Εκκίνηση YARN
```
start-yarn.sh
```

- Εκκινεί το YARN για τη διαχείριση πόρων στο cluster.

## 3. Εκτέλεση παραδείγματος υπολογισμού του π
```
spark-submit spark/examples/src/main/python/pi.py 5 --deploy-mode client --py-files
spark/examples/src/main/python/pi.py
```

- Εκτελεί το αρχείο `pi.py` με 5 επαναλήψεις.   
- `--deploy-mode client`: Ο driver εκτελείται τοπικά.

### 4. Εκτέλεση εφαρμογής μέσω REST API

Εκκίνηση SPARK
```
start-all.sh
```

- Ξεκινά όλες τις υπηρεσίες του Spark.

Εκτέλεση εφαρμογής `pi.py` με `curl`
```
curl -X POST http://127.0.0.1:6066/v1/submissions/create --header
"Content-Type:application/json;charset=UTF-8" --data '{
"appResource": "file:/home/bigdata/pi.py",
"sparkProperties": {
"spark.executor.memory": "512m",
"spark.executor.cores": "1",
"spark.driver.memory": "512m",
"spark.driver.cores": "1",
"spark.master": "yarn",
"spark.eventLog.enabled": "false",
"spark.app.name": "Spark REST API - PI",
"spark.submit.deployMode": "client",
"spark.driver.supervise": "true"
},
"clientSparkVersion": "3.3.0",
"mainClass": "org.apache.spark.deploy.SparkSubmit",
"environmentVariables": {
"SPARK_ENV_LOADED": "1"
},
"action": "CreateSubmissionRequest",
"appArgs": [ "/home/bigdata/pi.py", "5" ]}'
```

- Υποβάλλει εφαρμογή στο cluster μέσω REST API.   
- Παράμετροι όπως μνήμη και πυρήνες μπορούν να ρυθμιστούν.

Έλεγχος εκτέλεσης

- Μετά την εκτέλεση, μπορείς να παρακολουθείς την πρόοδο στον browser:
```
http://localhost:8080/
```

---

## ΠΗΓΕΣ

- [Συναρτήσεις pyspark](https://spark.apache.org/docs/latest/api/python/reference/pyspark.sql/functions.html)
