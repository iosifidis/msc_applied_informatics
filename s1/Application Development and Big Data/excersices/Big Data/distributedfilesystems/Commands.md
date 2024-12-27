# Εντολές σε Γραμμή Εντολών

## 1. Βασικές Εντολές Διαχείρισης Καταλόγων και Αρχείων
- **Λίστα αρχείων:** `ls`
- **Είσοδος σε κατάλογο:** `cd [όνομα_καταλόγου]`
- **Επιστροφή στον προηγούμενο κατάλογο:** `cd ..`
- **Επιστροφή στον αρχικό κατάλογο:** `cd`
- **Εμφάνιση περιεχομένων αρχείου:** `cat [όνομα_αρχείου]`

---

## 2. Εντολές Διαχείρισης Συστήματος
- **Ελεύθερος χώρος κατά partition:** `df -h`
- **Διεργασίες Java που τρέχουν:** `jps`
- **Εκκίνηση HDFS:** `start-dfs.sh`
- **Τερματισμός HDFS:** `stop-dfs.sh`

---

## 3. Διαχείριση Αρχείων στο HDFS
- **Λίστα αρχείων στο HDFS:** `hdfs dfs -ls`
- **Λίστα αρχείων καταλόγου στο HDFS:** `hdfs dfs -ls [όνομα_καταλόγου]`
- **Ελεύθερος χώρος στο HDFS:** `hdfs dfs -df -h`
- **Λήψη αρχείου από το HDFS:** `hdfs dfs -get [όνομα_αρχείου]`
- **Λήψη φακέλου από το HDFS:** `hdfs dfs -get [όνομα_φακέλου] [προορισμός]`
- **Μεταφόρτωση αρχείου στο HDFS:** `hdfs dfs -put [όνομα_αρχείου]`
- **Φέρνω από το HDFS τον φάκελο input στο τοπικό σύστημα και τον αποθηκεύω στον φάκελο /tmp:** `hdfs dfs -get input /tmp/`


---

## 4. Αναφορές και Κατάσταση Cluster
- **Αναφορά κατάστασης cluster:** `hadoop/bin/hdfs dfsadmin -report`
- **Πρόσβαση μέσω browser:** `localhost:9870`

---

## 5. Χρήση WebHDFS
- **Λίστα καταλόγου /tmp:**
  ```bash
  curl -i -X GET "http://localhost:9870/webhdfs/v1/tmp/?op=LISTSTATUS"
  ```
- **Λίστα καταλόγου /user/bigdata:**
  ```bash
  curl -i -X GET "http://localhost:9870/webhdfs/v1/user/bigdata/?op=LISTSTATUS"
  ```
- **Δημιουργία καταλόγου:**
  ```bash
  curl -i -X PUT "http://localhost:9870/webhdfs/v1/user/bigdata/testme/?op=MKDIRS"
  ```
- **Δημιουργία αρχείου:**
  ```bash
  curl -i -X PUT "http://localhost:9870/webhdfs/v1/tmp/firewall_rules.sh?op=create&user.name=bigdata"
  ```
- **Αποστολή δεδομένων σε Datanode:**
  ```bash
  curl -i -X PUT 'http://bigdata-virtualbox:9864/webhdfs/v1/tmp/firewall_rules.sh?op=CREATE&user.name=bigdata&namenoderpcaddress=master:9000&createflag=&createparent=true&overwrite=false' -H 'Content-Type: application/octet-stream' -T 'firewall_rules.sh'
  ```

---

## 6. PySpark Εντολές
- **Εκκίνηση PySpark:** `pyspark`
- **Δημιουργία DataFrame:**
  ```python
  data = [{
    'Brand': 'Toyota',
    'Model': 'Corolla',
    'Rate': 14.52,
    'Days': 7
  }, {
    'Brand': 'Volvo',
    'Model': 'V40',
    'Rate': 24.2,
    'Days': 2
  }]
```

- **Δημιουργία dataframe:**
```
df = spark.createDataFrame(data)
```

- **Εμφάνιση περιεχομένων DataFrame:**
  ```python
  df.show(truncate=False)
  ```
- **Αποθήκευση DataFrame:**
  ```python
  df.write.format('json').mode('overwrite').save('hdfs://localhost:9000/user/bigdata/json-example')
  ```
- **Ανάγνωση DataFrame από HDFS:**
  ```python
  dfhdfs = spark.read.format('json').load('hdfs://localhost:9000/user/bigdata/json-example')
  ```
- **Δημιουργία νέας στήλης με υπολογισμό:**
  ```python
  newdf = dfhdfs.withColumn("Total", dfhdfs.Days*dfhdfs.Rate)
  ```
- **Δημιουργία νέας στήλης με παράθεση συμβολοσειρών:**
  ```python
  from pyspark.sql.functions import concat_ws
  newdf = newdf.withColumn("Type", concat_ws(" ", "Brand", "Model"))
  newdf.show()
  ```

---

## 7. RDD API Εντολές
- **Μεταφόρτωση αρχείου στο HDFS:** `hdfs dfs -put temps.txt`
- **Ανάγνωση δεδομένων σε RDD:**
  ```python
  parTempData = sc.textFile("hdfs://localhost:9000/user/bigdata/temps.txt")
  ```
- **Φέρνω τα δεδομένα στον driver:**
  ```python
  parTempData.collect()
  ```
- **Μετατροπή Φαρενάιτ σε Κελσίου:**
  ```python
  def FtoC(fahrenheit):
      celsius = (fahrenheit-32)*5/9
      return celsius
  ```
- **Μετατροπή θερμοκρασιών:**
  ```python
  celsiusTemps = parTempData.map(lambda x: float(x)).map(FtoC)
  ```
- **Παίρνω την πρώτη θερμοκρασία:**
  ```python
  celsiusTemps.first()
  ```
- **Φιλτράρισμα θερμοκρασιών >= 13°C:**
  ```python
  filteredTemps = celsiusTemps.filter(lambda temp: temp >= 13)
  filteredTemps.collect()
  ```

---

Αυτές οι σημειώσεις καλύπτουν τις βασικές εντολές για HDFS, WebHDFS, PySpark και RDDs. Για περισσότερες πληροφορίες, ανατρέξτε στο εκπαιδευτικό υλικό ή την τεκμηρίωση.

