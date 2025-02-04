# Εισαγωγή στο MapReduce

---

## Ορισμός και Στόχοι
1. **MapReduce**:  
   - Ένα προγραμματιστικό μοντέλο σχεδιασμένο από τη Google για μαζική επεξεργασία δεδομένων μεγάλης κλίμακας.  
   - Στοχεύει στην επίλυση προβλημάτων που αφορούν τεράστιες ποσότητες δεδομένων.  
   - Παρέχει μηχανισμό για τη διαχείριση κατανεμημένων υπολογισμών με ευκολία.  

2. **Υλοποίηση**:  
   - Χρησιμοποιείται από το **Apache Hadoop**, που αποτελεί δημοφιλή μηχανή κατανεμημένης επεξεργασίας δεδομένων.  

---

## Βασικές Αρχές
1. **Οριζόντια έναντι κατακόρυφης κλιμάκωσης**:  
   - **Κατακόρυφη κλιμάκωση**: Αύξηση δυνατοτήτων ενός συστήματος με αναβάθμιση υλικού, αλλά με υψηλό κόστος.  
   - **Οριζόντια κλιμάκωση**: Προσθήκη περισσότερων μηχανών, επιτρέποντας φθηνότερη επέκταση του συστήματος.  

2. **Αντιμετώπιση αστοχιών**:  
   - Σε κατανεμημένα συστήματα, η πιθανότητα αστοχιών είναι υψηλή.  
   - Το MapReduce περιλαμβάνει μηχανισμούς ανάκαμψης για να συνεχίζεται η επεξεργασία χωρίς επανεκκίνηση.  

3. **Μετακίνηση υπολογισμών αντί δεδομένων**:  
   - Δεδομένου ότι η μετακίνηση τεράστιων δεδομένων είναι κοστοβόρα, τα προγράμματα επεξεργασίας μεταφέρονται στους υπολογιστές που φιλοξενούν τα δεδομένα.  

4. **Σειριακή επεξεργασία ροής**:  
   - Τα δεδομένα επεξεργάζονται σε μορφή ροής για αποφυγή καθυστερήσεων.  
   - Οργανώνεται ώστε να μειώνεται η χρήση κύριας μνήμης.  

---

## Δομή και Μηχανισμοί του MapReduce
1. **Map Step (Απεικόνιση)**:  
   - Διαχωρίζει τα δεδομένα σε μικρότερα τμήματα.  
   - Παράγει ζεύγη κλειδιού-τιμής για κάθε είσοδο.  

2. **Reduce Step (Συγχώνευση)**:  
   - Επεξεργάζεται και συγκεντρώνει τα ζεύγη με το ίδιο κλειδί.  
   - Παράγει την τελική έξοδο.  

3. **Shuffle and Sort**:  
   - Ομαδοποιεί ζεύγη με κοινό κλειδί.  
   - Δρομολογεί τα δεδομένα στους αντίστοιχους Reducers.  

4. **Εκτέλεση**:  
   - Κάθε Mapper επεξεργάζεται ανεξάρτητα ένα τμήμα των δεδομένων.  
   - Το Reduce βήμα ξεκινά όταν όλοι οι Mappers έχουν ολοκληρώσει.  

---

## Ειδικά Θέματα
1. **Combiners** (Συνδυαστές):  
   
   **Ρόλος**:

   - Μειώνουν τον όγκο δεδομένων που μεταφέρονται από τους Mappers στους Reducers.   
   - Παράγουν τοπικά ενδιάμεσα αποτελέσματα.

   **Προϋποθέσεις χρήσης**:

   - Οι λειτουργίες του Combiner πρέπει να είναι προσεταιριστικές (associative) και αντιμεταθετικές (commutative).   
   - Δεν επηρεάζουν την ορθότητα του αλγορίθμου, καθώς μπορεί να εκτελούνται ή να παραλείπονται.

   **Παράδειγμα**:

   - Σε μέτρηση συχνότητας διευθύνσεων IP, ο Combiner ομαδοποιεί τις τιμές τοπικά και μεταφέρει ζεύγη τύπου `<IP, Συχνότητα>`.

2. **Partitioners** (Διαμεριστές):  
   - Ορίζουν ποια ζεύγη δεδομένων αποστέλλονται σε κάθε Reducer.  
   - Βοηθούν στην εξισορρόπηση φόρτου μεταξύ των Reducers.  

   **Προσαρμογή**:

   - Μπορεί να οριστεί συνάρτηση διαμερισμού από τον χρήστη ή να χρησιμοποιηθεί η προκαθορισμένη (hash του κλειδιού).

---

## Ροή Δεδομένων
1. **Είσοδος**: Τα δεδομένα αποθηκεύονται στο HDFS (Hadoop Distributed File System).  
2. **Map**: Παράγει ενδιάμεσα ζεύγη κλειδιών-τιμών.  
3. **Shuffle and Sort**: Τα δεδομένα μεταφέρονται και ταξινομούνται ανά κλειδί.  
4. **Reduce**: Παράγει την τελική έξοδο που αποθηκεύεται στο HDFS.  

---

## Πλεονεκτήματα
1. **Απλότητα προγραμματισμού**: Παρέχει υψηλού επιπέδου μοντέλο για ανάπτυξη κατανεμημένων εφαρμογών.  
2. **Ανθεκτικότητα σε αποτυχίες**: Δυνατότητα συνέχισης εργασιών σε περίπτωση αστοχιών.  
3. **Κλιμάκωση**: Υποστηρίζει επεξεργασία τεράστιων δεδομένων με προσθήκη νέων μηχανών.  

---

## Εφαρμογές
1. **Ανάλυση αρχείων καταγραφής**: Μέτρηση συχνότητας εμφάνισης διευθύνσεων IP.  
2. **Μετατροπή δεδομένων**: Παράλληλη επεξεργασία για μετατροπή αρχείων σε διαφορετικό μορφότυπο.  
3. **Προγραμματιστικές εργασίες**: Αυτόματος έλεγχος μεγάλου αριθμού υποβαλλόμενων κώδικα.  

---

## Κύρια Βήματα (Παραδείγματα)
1. **Μέτρηση γραμμών αρχείων**:  
   - Ανάλυση δεδομένων με διανομή σε πολλούς υπολογιστές.  
2. **Μέτρηση συχνότητας εμφάνισης IP**:  
   - Δημιουργία ζευγών `<IP, Συχνότητα>`.  
   - Συγκέντρωση αποτελεσμάτων σε τελική λίστα.  

---

## Χρήσιμες Παρατηρήσεις
- **Εκτέλεση Map/Reduce**: Απαιτεί καλή κατανόηση αλγορίθμων και διαχείριση μεγάλων δεδομένων.  
- **Χρονικά όρια**: Το Reduce ξεκινά μόνο αφού ολοκληρωθεί το Map από όλους τους υπολογιστές.  
- **Κατακερματισμός δεδομένων**: Διασφαλίζει ότι δεδομένα με το ίδιο κλειδί καταλήγουν στον ίδιο Reducer.  

---

## Τρόπος Εκτέλεσης:

### Java (αρχείο [mapReduce.java](mapReduce.java))

1. Μεταγλώττιση του Κώδικα Java

Για να μεταγλωττίσετε τον Java κώδικα, χρησιμοποιήστε την ακόλουθη εντολή.  
Βεβαιωθείτε ότι η μεταβλητή περιβάλλοντος HADOOP_CLASSPATH έχει οριστεί σωστά:

```
export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar
```

Στη συνέχεια, εκτελέστε τη μεταγλώττιση:

```
hadoop com.sun.tools.javac.Main Social.java
```

Αυτό θα δημιουργήσει τα αρχεία .class.

2. Δημιουργία αρχείου JAR

Δημιουργήστε ένα εκτελέσιμο αρχείο JAR που θα περιέχει τις κλάσεις σας:
```
jar cf social.jar Social*.class
```

3. Εκτέλεση Εφαρμογής MapReduce

Χρησιμοποιήστε την εντολή hadoop jar για να εκτελέσετε την εφαρμογή σας στο Hadoop:

```
hadoop jar social.jar Social <input_path> <output_path>
```

- `<input_path>`: Ο κατάλογος που περιέχει τα δεδομένα εισόδου (π.χ., input).   
- `<output_path>`: Ο κατάλογος όπου θα αποθηκευτούν τα αποτελέσματα (π.χ., output).


### Python (αρχεία [map.py](map.py) και [reduce.py](reduce.py))

1. Εκτέλεση τοπικά με χρήση pipes:

```
cat input.txt | ./map.py | sort | ./reduce.py
```

2. Εκτέλεση σε Hadoop: 

```
hadoop jar hadoop-streaming-*.jar -files map.py,reduce.py \
-mapper map.py -reducer reduce.py -input input -output output
```

---

## Βίντεο

- [Map Reduce explained with example | System Design](https://youtu.be/cHGaQz0E7AU?si=SgxstdGOPUJ6uAe9)   
- [What is MapReduce?](https://youtu.be/43fqzaSH0CQ?si=Uiap4a7NqmJtkl71)  
- [MapReduce - Computerphile](https://youtu.be/cvhKoniK5Uo?si=TtEH5GCAO_IyDRIh)   
