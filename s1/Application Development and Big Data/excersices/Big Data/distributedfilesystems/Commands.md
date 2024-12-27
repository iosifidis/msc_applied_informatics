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
  df = spark.createDataFrame(data)   # Create DF
  ```
- **Αποθήκευση DataFrame:**
  ```python
  df.write.format('json').mode('overwrite').save('hdfs://localhost:9000/user/bigdata/json-example')
  ```
- **Ανάγνωση DataFrame από HDFS:**
  ```python
  dfhdfs = spark.read.format('json').load('hdfs://localhost:9000/user/bigdata/json-example')
  ```

---

## 7. RDD API Εντολές
- **Μεταφόρτωση αρχείου στο HDFS:** `hdfs dfs -put temps.txt`
- **Ανάγνωση δεδομένων σε RDD:**
  ```python
  parTempData = sc.textFile("hdfs://localhost:9000/user/bigdata/temps.txt")
  ```
- **Μετατροπή Φαρενάιτ σε Κελσίου:**
  ```python
  def FtoC(fahrenheit):
      celsius = (fahrenheit-32)*5/9
      return celsius
  ```
- **Φιλτράρισμα δεδομένων:**
  ```python
  filteredTemps = celsiusTemps.filter(lambda temp: temp >= 13)
  ```

---

Αυτές οι σημειώσεις καλύπτουν τις βασικές εντολές για HDFS, WebHDFS, PySpark και RDDs. Για περισσότερες πληροφορίες, ανατρέξτε στο εκπαιδευτικό υλικό ή την τεκμηρίωση.

