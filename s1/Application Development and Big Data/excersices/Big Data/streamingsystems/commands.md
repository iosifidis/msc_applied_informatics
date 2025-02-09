## Αναλυτικές εντολές για τη ρύθμιση και εκτέλεση ροών δεδομένων με Spark και PySpark.

### **1. Προετοιμασία του Περιβάλλοντος**
- Μετάβαση στον φάκελο `/spark/conf` και ρύθμιση του αρχείου `log4j.properties` για να αλλάξει το επίπεδο καταγραφής (`info → error`).
- Εκκίνηση του **HDFS** με `start-dfs.sh`.

**Σκοπός**: Η αλλαγή του επιπέδου logging σε `error` μειώνει τα μηνύματα που εμφανίζονται στην κονσόλα, ώστε να είναι πιο εύκολη η παρακολούθηση της εκτέλεσης. Η εντολή `start-dfs.sh` ξεκινά το HDFS, το οποίο είναι απαραίτητο για την επεξεργασία δεδομένων σε κατανεμημένο περιβάλλον.

### **2. Εκτέλεση Streaming με Spark**
- Εκτέλεση του αρχείου `structured_network_wordcount.py` από τον φάκελο `spark/examples/src/main/python/sql/streaming/`.
- Άνοιγμα ενός τερματικού και εκτέλεση του:
  ```bash
  nc -lk 9999
  ```
- Άνοιγμα δεύτερου τερματικού και εκτέλεση:
  ```bash
  spark-submit --master local[*] spark/examples/src/main/python/sql/streaming/structured_network_wordcount.py localhost 9999
  ```

**Σκοπός**: Η εντολή `nc -lk 9999` ξεκινά έναν TCP server που ακούει στη θύρα 9999 και μπορεί να λαμβάνει δεδομένα από τον χρήστη. Το `spark-submit` εκτελεί το παράδειγμα `structured_network_wordcount.py`, το οποίο διαβάζει δεδομένα από το socket και εκτελεί έναν απλό αλγόριθμο μέτρησης λέξεων (WordCount) σε πραγματικό χρόνο.

### **3. Εργασία με DataFrames στο PySpark**
- Εκκίνηση του PySpark:
  ```bash
  pyspark --master local[*]
  ```
- Ανάγνωση αρχείων CSV ως DataFrames:
  ```python
  df1 = spark.read.csv('file:///home/bigdata/stocks.csv', header=True, inferSchema=True)
  df2 = spark.read.csv('file:///home/bigdata/update.csv', header=True, inferSchema=True)
  df1.show()
  df2.show()
  ```

**Σκοπός**: Τα αρχεία `stocks.csv` και `update.csv` διαβάζονται σε DataFrames. Η παράμετρος `header=True` δηλώνει ότι η πρώτη γραμμή του αρχείου περιέχει τα ονόματα των στηλών, ενώ η `inferSchema=True` επιτρέπει στο Spark να προσδιορίσει αυτόματα τον τύπο δεδομένων κάθε στήλης.

### **4. Εκτέλεση SQL Joins με PySpark**
#### **Με χρήση PySpark API**
- **Inner Join**
  ```python
  df1.join(df2, (df1["SYMBOL"] == df2["SYMBOL"]) & (df1["OWNERID"] == df2["OWNERID"])).show()
  ```
- **Left Join**
  ```python
  df1.join(df2, (df1["SYMBOL"] == df2["SYMBOL"]) & (df1["OWNERID"] == df2["OWNERID"]), "left").show()
  ```
- **Right Join**
  ```python
  df1.join(df2, (df1["SYMBOL"] == df2["SYMBOL"]) & (df1["OWNERID"] == df2["OWNERID"]), "right").show()
  ```

**Σκοπός**: Οι εντολές αυτές εκτελούν **joins** μεταξύ των δύο DataFrames (`df1` και `df2`) με βάση τις κοινές στήλες `SYMBOL` και `OWNERID`. 
  - **Inner Join**: Επιστρέφει μόνο τις γραμμές που έχουν αντιστοιχίες και στα δύο DataFrames.  
  - **Left Join**: Επιστρέφει όλες τις γραμμές από το `df1` και τις αντιστοιχίες από το `df2` (αν υπάρχουν).  
  - **Right Join**: Επιστρέφει όλες τις γραμμές από το `df2` και τις αντιστοιχίες από το `df1` (αν υπάρχουν).  

#### **Με χρήση SQL Queries**
- Δημιουργία προσωρινών views:
  ```python
  df1.createOrReplaceTempView("l")
  df2.createOrReplaceTempView("r")
  ```
- **Inner Join μέσω SQL**
  ```sql
  spark.sql("SELECT * FROM l, r WHERE l.SYMBOL = r.SYMBOL AND l.OWNERID = r.OWNERID").show()
  ```
- **Left Join μέσω SQL**
  ```sql
  spark.sql("SELECT * FROM l LEFT JOIN r ON l.SYMBOL = r.SYMBOL AND l.OWNERID = r.OWNERID").show()
  ```
- **Right Join μέσω SQL**
  ```sql
  spark.sql("SELECT * FROM l RIGHT JOIN r ON l.SYMBOL = r.SYMBOL AND l.OWNERID = r.OWNERID").show()
  ```

**Σκοπός**: Οι εντολές αυτές εκτελούν τα ίδια joins όπως παραπάνω, αλλά χρησιμοποιώντας **SQL**. Τα DataFrames καταχωρούνται ως προβολές (views) με ονόματα `I` και `r`, και τα joins εκτελούνται με SQL ερωτήματα.

### **5. Ενημέρωση Πινάκων με DataFrames**
- Μετονομασία στηλών για αποφυγή συγκρούσεων:
  ```python
  from pyspark.sql.functions import col
  aldf1 = df1.select(col("SYMBOL").alias("LSYMBOL"), col("OWNERID").alias("LOWNERID"), col("COMPANY"), col("NUM_STOCKS"), col("PRICE"))
  aldf2 = df2.select(col("SYMBOL").alias("RSYMBOL"), col("OWNERID").alias("ROWNERID"), col("UPDATE"))
  ```
- Δημιουργία **Left Join**:
  ```python
  ljdf = aldf1.join(aldf2, (aldf1["LSYMBOL"] == aldf2["RSYMBOL"]) & (aldf1["LOWNERID"] == aldf2["ROWNERID"]), "left")
  ```
- Ενημέρωση στηλών με συνθήκες:
  ```python
  from pyspark.sql.functions import when
  updf = ljdf.withColumn('new', when(col('UPDATE').isNull(), col('NUM_STOCKS')).otherwise(col('NUM_STOCKS') + col('UPDATE')))
  updf.show(truncate=False)
  ```
- Τελικό DataFrame με ενημερωμένα δεδομένα:
  ```python
  finaldf = updf.select(col("LSYMBOL").alias("SYMBOL"), col("LOWNERID").alias("OWNERID"), col("COMPANY"), col("new").alias("NUM_STOCKS"), col("PRICE"))
  finaldf.show()
  ```

**Σκοπός**: 
  - Οι στήλες `SYMBOL` και `OWNERID` μετονομάζονται για να αποφευχθούν διενέξεις κατά το join.
  - Με τη συνάρτηση `when`, ενημερώνεται η στήλη `NUM_STOCKS` με βάση την τιμή της στήλης `UPDATE`. Αν η `UPDATE` είναι `null`, η `NUM_STOCKS` παραμένει η ίδια, αλλιώς προστίθεται η τιμή της `UPDATE`.
  - Το τελικό DataFrame `finaldf` περιέχει τις ενημερωμένες τιμές.

### **6. Streaming Joins**
- Ορισμός του σχήματος ενός εισερχόμενου CSV stream:
  ```python
  from pyspark.sql.types import StructType
  userSchema = StructType().add('SYMBOL', 'string').add('OWNERID', 'integer').add('UPDATE', 'integer')
  ```
- Ανάγνωση ροής δεδομένων από τον φάκελο `/tmp/input/`:
  ```python
  df2 = spark.readStream.format('csv').option('sep', ',').option('header', 'true').schema(userSchema).option('path','file:///tmp/input/').load()
  ```
- Εκτέλεση **Streaming Join**:
  ```python
  df3 = df1.join(df2, ["SYMBOL", "OWNERID"])
  ```
- Αποθήκευση αποτελεσμάτων συνεχούς ροής στον φάκελο `/tmp/myoutput/`:
  ```python
  df3.writeStream.outputMode("append").option("path","file:///tmp/myoutput").option("checkpointLocation", "file:///tmp/checkpoint/").format("csv").start().awaitTermination()
  ```
  
**Σκοπός**: 
  - Τα δεδομένα από τον φάκελο `/tmp/input` διαβάζονται ως **streaming** και ενημερώνονται συνεχώς.
  - Γίνεται **join** μεταξύ του στατικού DataFrame `df1` και του streaming DataFrame `df2`.
  - Τα αποτελέσματα του join αποθηκεύονται στον φάκελο `/tmp/myoutput` σε μορφή CSV, ενώ η τοποθεσία `checkpoint` χρησιμοποιείται για την αποθήκευση της κατάστασης του streaming.
  
---

### Συμπεράσματα

Οι εντολές αυτές καλύπτουν μια ευρεία γκάμα λειτουργιών, από την προετοιμασία του περιβάλλοντος έως την εκτέλεση streaming εφαρμογών. Η χρήση των DataFrames και του SQL κάνει τον κώδικα πιο ευανάγνωστο και εύκολο στη διαχείριση, ενώ η δυνατότητα streaming επιτρέπει την επεξεργασία δεδομένων σε πραγματικό χρόνο.
