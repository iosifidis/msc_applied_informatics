# **Αναπαραγωγή, Κατακερματισμός και Ομαδοποίηση σε Συστήματα Βάσεων Δεδομένων**

## **ΜΕΡΟΣ Α: MySQL Partitioning (Slides 1-23)**

Το Partitioning στο MySQL αφορά τον **λογικό διαμερισμό (logical splitting)** ενός πίνακα σε μικρότερα, διαχειρίσιμα κομμάτια, τα οποία αποθηκεύονται είτε στον ίδιο δίσκο είτε σε διαφορετικούς. Το σημαντικό είναι ότι αυτή η διαμέριση είναι **αόρατη (transparent)** στον χρήστη/εφαρμογή από την άποψη της σύνταξης των ερωτημάτων (το ερώτημα γίνεται στον "λογικό" πίνακα), αλλά έχει σημαντικές επιπτώσεις στην απόδοση και τη διαχείριση στο παρασκήνιο.

1.  **Τι ακριβώς είναι το Partitioning; (Slide 2)**
    *   Είναι ένας τρόπος για να "σπάσουμε" έναν πολύ μεγάλο πίνακα σε μικρότερα, πιο εύχρηστα τμήματα (partitions).
    *   Αυτά τα τμήματα μπορούν να αντιμετωπιστούν από το MySQL engine ως ανεξάρτητα, αν και παραμένουν μέρος ενός ενιαίου λογικού πίνακα.
    *   Σκοπός: βελτιστοποίηση της απόδοσης σε ορισμένα ερωτήματα και απλοποίηση των λειτουργιών διαχείρισης (π.χ. διαγραφή παλαιών δεδομένων).

2.  **Τρόπος Υλοποίησης: PARTITION BY (Slide 3)**
    *   Γίνεται κατά τη δημιουργία (`CREATE TABLE`) ή τροποποίηση (`ALTER TABLE`) ενός πίνακα.
    *   Χρησιμοποιείται η δήλωση `PARTITION BY <τύπος> (<έκφραση διαμερισμού>)`.
    *   Η `<έκφραση διαμερισμού>` είναι συνήθως μια στήλη ή μια συνάρτηση που εφαρμόζεται σε μία ή περισσότερες στήλες.
    *   Η στήλη ή οι στήλες που χρησιμοποιούνται στην έκφραση διαμερισμού ονομάζονται **partitioning key**.
    *   Ο `<τύπος>` καθορίζει τον τρόπο με τον οποίο το MySQL θα κατανείμει τις γραμμές στα partitions.

3.  **Τύποι Partitioning στο MySQL (Slides 3-7)**
    *   **RANGE Partitioning (Slide 4):**
        *   Οι γραμμές κατανέμονται βάσει ανεύρους τιμών (range) της έκφρασης διαμερισμού.
        *   Ορίζεται με `PARTITION BY RANGE (<έκφραση>) (... VALUES LESS THAN (<τιμή>)...)`.
        *   Κάθε partition καλύπτει ένα συγκεκριμένο εύρος τιμών, εκτός από το τελευταίο που μπορεί να χρησιμοποιήσει `MAXVALUE` για να καλύψει όλες τις τιμές μεγαλύτερες από το προηγούμενο εύρος.
        *   Οι τιμές για τα ανεύρη πρέπει να είναι ακέραιες και να παρατίθενται με αύξουσα σειρά.
        *   Ιδανικό για: χρονολογικά δεδομένα (π.χ. partitioning by year, month), διαμερισμός σε εύρη ID, δεδομένα που θέλουμε να διαγράφουμε εύκολα με το dropping ενός partition.
        * Παράδειγμα:
        ```sql
             CREATE TABLE Employee (
                emp_id INT AUTO_INCREMENT,
                fname VARCHAR(50),
                lname VARCHAR(50),
                store_id TINYINT,
                PRIMARY KEY  (emp_id)
             ) ENGINE=MyISAM
             PARTITION BY RANGE (emp_id) (
                PARTITION p0 VALUES LESS THAN (10000),
                PARTITION p1 VALUES LESS THAN (20000),
                PARTITION p2 VALUES LESS THAN (30000),
                PARTITION p3 VALUES LESS THAN (40000),
                PARTITION p4 VALUES LESS THAN MAXVALUE
             );
        ```
     
    *   **LIST Partitioning (Slide 5):**
        *   Οι γραμμές κατανέμονται βάσει μιας συγκεκριμένης λίστας τιμών (list) της έκφρασης διαμερισμού.
        *   Ορίζεται με `PARTITION BY LIST (<έκφραση>) (... VALUES IN (<λίστα τιμών>)...)`.
        *   Κάθε partition αντιστοιχεί σε ένα ρητά ορισμένο σύνολο τιμών. Κάθε τιμή πρέπει να ανήκει σε **ένα και μόνο ένα** partition.
        *   Ιδανικό για: διακριτές, μη ταξινομημένες τιμές (π.χ. partitioning by store ID, region code).
        *   **Σημαντική Περιορισμός (στο MySQL):** Αν ο πίνακας έχει Primary Key (ή οποιοδήποτε Unique Key), **όλες** οι στήλες που αποτελούν το Primary Key πρέπει να περιλαμβάνονται στην έκφραση διαμερισμού. Αυτό γίνεται για να μπορεί το MySQL να ελέγξει την μοναδικότητα ενός κλειδιού, ξέροντας εκ των προτέρω σε ποιο partition αναζητήσει.
        *  Παράδειγμα:
        ```sql
             CREATE TABLE Employee (
                emp_id INT,
                fname VARCHAR(50),
                lname VARCHAR(50),
                store_id TINYINT
             ) ENGINE=MyISAM
             PARTITION BY LIST (store_id) (
                PARTITION pNorth VALUES IN (2,8,12),
                PARTITION pEast VALUES IN (1,4,7),
                PARTITION pWest VALUES IN (3,5,6,10),
                PARTITION pSouth VALUES IN (9,11)
             );
        ```
        
    *   **HASH Partitioning (Slide 6):**
        *   Οι γραμμές κατανέμονται βάσει της τιμής μιας συνάρτησης κατακερματισμού (hash function) που εφαρμόζεται στην έκφραση διαμερισμού.
        *   Ορίζεται με `PARTITION BY HASH (<έκφραση>) PARTITIONS <αριθμός>;`.
        *   Η έκφραση πρέπει να επιστρέφει ακέραιο. Το MySQL χρησιμοποιεί modulo (`) στην τιμή για να καθορίσει σε ποιο από τα `<αριθμός>` partitions θα πάει η γραμμή (π.χ. `hash(expr) MOD <αριθμός>`).
        *   Ιδανικό για: ομοιόμορφη κατανομή δεδομένων όταν δεν υπάρχει σαφές εύρος ή λίστα. Βελτιώνει την απόδοση σε ερωτήματα ισότητας στο partitioning key (καθώς το MySQL ξέρει ακριβώς ποιο partition να ελέγξει).
        * Παράδειγμα:
        ```sql
             CREATE TABLE Employee (
                emp_id INT AUTO_INCREMENT,
                fname VARCHAR(50),
                lname VARCHAR(50),
                store_id TINYINT,
                PRIMARY KEY  (emp_id)
             ) ENGINE=MyISAM
             PARTITION BY HASH (emp_id) PARTITIONS 4;
        ```
        
        * Αν `emp_id=9`, τότε:
        ```
             mod(9,4) = 1
        ```
     Οπότε, η εγγραφή αποθηκεύεται στο partition `p1`.
        
    *   **KEY Partitioning (Slide 7):**
        *   Παρόμοιο με το HASH, αλλά το MySQL παρέχει τη δική του εσωτερική συνάρτηση κατακερματισμού (βασισμένη στην PASSWORD() αρχικά).
        *   Ορίζεται με `PARTITION BY KEY (<στήλη(ες)>) PARTITIONS <αριθμός>;`.
        *   Η έκφραση διαμερισμού δεν χρειάζεται να επιστρέφει ακέραιο - το MySQL την χειρίζεται εσωτερικά.
        *   Ιδανικό όταν θέλετε να κάνετε hash partitioning αλλά δεν υπάρχει ακέραια στήλη ή δεν θέλετε να ορίσετε δική σας hash συνάρτηση.
        * Παράδειγμα:
        ```sql
             CREATE TABLE Employee (
                emp_id INT,
                fname VARCHAR(50),
                lname VARCHAR(50),
                store_id TINYINT
             ) ENGINE=MyISAM
             PARTITION BY KEY (lname) PARTITIONS 4;
        ```

4.  **Partition Pruning (Κλάδεμα Partitions) (Slides 8-14)**
    *   Αυτό είναι το κύριο όφελος απόδοσης.
    *   Όταν ένα ερώτημα περιέχει ένα `WHERE` clause που αναφέρεται στην στήλη (ή έκφραση) διαμερισμού, ο βελτιστοποιητής ερωτημάτων (query optimizer) του MySQL μπορεί να προσδιορίσει ποια partitions **πρέπει** να σαρωθούν και να αγνοήσει τα υπόλοιπα.
    *   **Παράδειγμα (Slides 9-14):** Σύγκριση ενός ερωτήματος αναζήτησης (ισότητας ή εύρους) σε έναν *μη διαμερισμένο* πίνακα (αναγκαστική σάρωση όλου του πίνακα, Slide 9-11) έναντι ενός *διαμερισμένου* πίνακα (σάρωση μόνο των σχετικών partitions, Slide 12-14). Η διαφορά στον χρόνο εκτέλεσης μπορεί να είναι πολύ μεγάλη.

5.  **Οφέλη του Partitioning (Slides 15-16)**
    *   **Βελτίωση Απόδοσης:**
        *   Ταχύτερες μεμονωμένες εισαγωγές (`INSERT`) και αναζητήσεις (`SELECT`) αν το ερώτημα περιλαμβάνει το partitioning key (λόγω direct routing ή pruning).
        *   Ταχύτερες αναζητήσεις εύρους (`SELECT ... WHERE ... BETWEEN ...`) σε RANGE/LIST partitioning (λόγω pruning).
    *   **Βελτίωση Διαχείρισης:**
        *   Δυνατότητα διαμερισμού δεδομένων σε διαφορετικούς δίσκους/file systems (ενισχύει I/O παράλληλισμό - αν και λιγότερο εμφανές στις διαφάνειες).
        *   Αποτελεσματική αποθήκευση και διαγραφή **ιστορικών δεδομένων** (`ALTER TABLE ... DROP PARTITION`). Αντί για χρονοβόρα `DELETE` statement, απλά απορρίπτεις (drop) ένα partition που περιέχει μια χρονική περίοδο.
        *   (Less common benefits) Έλεγχος ARCHIVE tables, διάκριση ρόλων σε master/slave (π.χ. hash partitioning στο master για γρήγορα inserts, range partitioning στο slave για γρήγορα reporting).

6.  **Πότε να Χρησιμοποιήσετε Partitioning (Slide 17)**
    *   Έχετε **μεγάλους πίνακες**.
    *   Γνωρίζετε ότι τα ερωτήματά σας συχνά αναφέρονται στη **στήλη διαμερισμού** (για να επωφεληθείτε από το pruning).
    *   Έχετε **ιστορικά δεδομένα** που θέλετε να διαγράφετε/αρχειοθετείτε εύκολα και γρήγορα.
    *   Τα **ευρετήριά σας** είναι μεγαλύτερα από την διαθέσιμη RAM (το partitioning μειώνει το μέγεθος του ευρετηρίου ανά partition).

7.  **Έλεγχος Partitions (Slides 18-23)**
    *   Μπορείτε να δείτε τα ονόματα, την έκφραση και την περιγραφή των partitions ενός πίνακα στον πίνακα `information_schema.partitions` (Slide 18).
    *   Η διαφορά στην απόδοση είναι εμφανής όταν συγκρίνεται ο χρόνος εκτέλεσης ενός ερωτήματος σε μη-διαμερισμένο vs. διαμερισμένο πίνακα (Slides 20-21: 1.52s vs 0.41s για SELECT COUNT).
    *   Η διαφορά στη διαχείριση (ειδικά διαγραφής) είναι ακόμη πιο δραματική (Slides 22-23: 19.13s για DELETE vs 1.35s για ALTER TABLE DROP PARTITION).

## **ΜΕΡΟΣ Β: MySQL Replication (Slides 24-45)**

Το Replication στο MySQL αφορά την **αντιγραφή (copying)** δεδομένων από έναν MySQL server (Master) σε έναν ή περισσότερους άλλους MySQL servers (Slaves). Κύριοι στόχοι: **διαθεσιμότητα (availability)** και **κλιμάκωση αναγνώσεων (read scaling)**. Είναι ένα μοντέλο διανομής δεδομένων που βασίζεται στην **ασύγχρονη αναπαραγωγή** ως προεπιλογή (αν και υπάρχει και συγχρονισμένη επιλογή).

1.  **Ορολογία (Slides 25-26)**
    *   **Master MySQL Server:** Ο server όπου γίνονται οι κύριες εγγραφές/ενημερώσεις (`changes data`). Διατηρεί ενεργό το **Binary Log (binlog)**. Στέλνει (pushes) τα binlog events στους slaves.
    *   **Slave MySQL Server:** Λαμβάνει (`gets`) τα binlog events από τον master και τα εφαρμόζει τοπικά (`applies`) στο δικό του αντίγραφο των δεδομένων. Συνήθως χρησιμοποιείται για ερωτήματα αναγνώσεων. Έχει ένα **replication control point**.
    *   **Binary Log (binlog):** Ένα log στο master server που καταγράφει *όλες* τις αλλαγές δεδομένων. Χωρίζεται σε συναλλαγές (transactional components). Χρησιμοποιείται για το replication και για point-in-time recovery.
    *   **Συγχρονισμένη Αναπαραγωγή (Synchronous Replication) (Slide 26):** Μια συναλλαγή στον master **δεν ολοκληρώνεται (commit)** μέχρι τα δεδομένα να έχουν αναπαραχθεί (και εφαρμοστεί) στους slaves. **Ασφαλέστερη** (μικρότερος κίνδυνος απώλειας δεδομένων), αλλά **αργότερη**. Διαθέσιμη κυρίως στο MySQL Cluster.
    *   **Ασύγχρονη Αναπαραγωγή (Asynchronous Replication) (Slide 26):** Μια συναλλαγή στον master **ολοκληρώνεται (commit)** αμέσως, *πριν* τα δεδομένα αναπαραχθούν στους slaves. **Ταχύτερη**, αλλά υπάρχει **κίνδυνος απώλειας συναλλαγών** αν ο master αποτύχει προτού οι αλλαγές φτάσουν στους slaves. **Πιο εύκολη εγκατάσταση** μεταξύ απλών MySQL servers. Αυτός είναι ο πιο κοινός τύπος replication. (Εδώ φαίνεται η υλοποίηση του AP trade-off του CAP Theorem).

2.  **Τοπολογίες Replication (Slides 27-38)**
    *   **Master with Slave (Slides 28-29):** Η πιο απλή τοπολογία. Ένας master, ένας slave. Τα binlog events στέλλονται μέσω TCP σύνδεσης.
    *   **Master with Many Slaves (Slide 30):** Ένας master, πολλοί slaves. Ο master στέλνει το binlog σε κάθε slave. Καλό για κλιμάκωση **αναγνώσεων**. Όλες οι εγγραφές πηγαίνουν στον master.
    *   **Chain (Αλυσίδα) (Slides 31-34):** Οι slaves μπορούν να λειτουργήσουν και ως masters για άλλους slaves (`log_slave_updates = 1`). Ο Master Α αναπαράγει στον Slave B, ο Slave B αναπαράγει στον Slave C, κ.ο.κ. (Α -> Β -> C). Χρήσιμο για διάδοση του φόρτου από τον κύριο master. **Πρόβλημα:** Αν ένας κόμβος στην αλυσίδα αποτύχει, όλοι οι downstream slaves **βγαίνουν εκτός συγχρονισμού (out of sync)** (Slides 32-33). Ο μοναδικός `server_id` κάθε server βοηθά στην παρακολούθηση της προέλευσης των events στο binlog (Slide 34).
    *   **Ring (Δακτύλιος) (Slides 35-36):** Κάθε server λειτουργεί ως master για έναν άλλο server και ως slave από έναν άλλο, σχηματίζοντας έναν κύκλο. **Δεν συνιστάται** ως γενική ρύθμιση, καθώς μια αποτυχία σε ένα σημείο μπορεί να σπάσει τον κύκλο και η αποκατάσταση της συνέπειας είναι δύσκολη.
    *   **Pair of Masters (Master-Master) (Slide 37-38):** Ειδική περίπτωση δακτυλίου με μόνο δύο servers. Ο καθένας είναι master του άλλου και slave του άλλου. Χρησιμοποιείται συχνά για **υψηλή διαθεσιμότητα (High Availability)** - αν ο ένας master αποτύχει, ο άλλος μπορεί να αναλάβει (failover). Απαιτεί προσεκτική διαχείριση των εγγραφών για να αποφευχθούν συγκρούσεις (π.χ. οι εγγραφές κατευθύνονται μόνο σε έναν master κάθε φορά μέσω application logic ή εξωτερικών εργαλείων). Αυτή και η τοπολογία "Master με πολλούς Slaves" είναι οι **δύο πιο κοινές** (Slide 38).
    *   **Relay Slave / Blackhole Storage Engine (Slides 39-40):**
        *   Ένας Relay Slave (`log_slave_updates` ενεργό) λαμβάνει binlog events από τον master, αλλά δεν είναι απαραίτητο να αποθηκεύει τα δεδομένα του πίνακα.
        *   Η Blackhole storage engine είναι μια ειδική engine που **δέχεται εγγραφές αλλά τις απορρίπτει αμέσως**, χωρίς να αποθηκεύει τα δεδομένα.
        *   Ένας Slave με Blackhole engine μπορεί να χρησιμοποιηθεί ως **relay** για να λαμβάνει binlog από έναν master και να το προωθεί σε άλλους slaves, χωρίς ο ίδιος να αποθηκεύει τα δεδομένα που αναπαράγει. Χρήσιμο για σύνθετες τοπολογίες ή debugging, ή για διανομή του binlog φόρτου.

3.  **Περιπτώσεις Χρήσης Replication (Slides 41-44)**
    *   **Intensive Reads (Εντατικές Αναγνώσεις) (Slide 42):** Η τοπολογία Master with Many Slaves επιτρέπει τη διανομή του φόρτου αναγνώσεων σε πολλούς servers, κλιμακώνοντας την ικανότητα εξυπηρέτησης αναγνωστικών ερωτημάτων.
    *   **High Availability (Υψηλή Διαθεσιμότητα) (Slide 42):** Η τοπολογία Master-Master pair επιτρέπει την γρήγορη εναλλαγή (failover) στον έτερο master αν ο κύριος master αποτύχει.
    *   **"Specialist" Slaves (Εξειδικευμένοι Slaves) (Slides 43-44):** Slaves μπορούν να διαμορφωθούν ή να χρησιμοποιηθούν για συγκεκριμένες λειτουργίες, ώστε να μην επιβαρύνουν τον κύριο master ή τους slaves που εξυπηρετούν την εφαρμογή:
        *   **Backups και Reporting:** Ένας slave είναι αποκλειστικά για τη λήψη backups ή την εκτέλεση χρονοβόρων αναλυτικών ερωτημάτων.
        *   **Per-Application / Subset:** Διαφορετικοί slaves μπορούν να αναπαράγουν μόνο συγκεκριμένα υποσύνολα δεδομένων ή να εξυπηρετούν ερωτήματα συγκεκριμένων εφαρμογών/λειτουργιών (π.χ. ένας slave για "friends list" queries, ένας άλλος για "message board" queries, πιθανώς συνδυασμένο με partitioning).

**ΜΕΡΟΣ Γ: MySQL Cluster (Slides 46-48)**

Το MySQL Cluster είναι μια διαφορετική, **κατανεμημένη (distributed)** και **Share-Nothing** αρχιτεκτονική βάσης δεδομένων, σχεδιασμένη για πολύ υψηλή διαθεσιμότητα (High Availability) και απόδοση. Δεν είναι απλώς ένα σύνολο MySQL servers με replication.

1.  **Αρχιτεκτονική (Slide 47):**
    *   Έχει διαφορετικούς τύπους κόμβων:
        *   **SQL Nodes:** Συνήθεις MySQL servers που επεξεργάζονται ερωτήματα (Query processing).
        *   **Data Nodes:** Αποθηκεύουν τα δεδομένα και διαχειρίζονται τα partitions και τα αντίγραφα (replicas).
        *   **Management Nodes:** Διαχειρίζονται τη ρύθμιση και την οργάνωση του cluster.
    *   Επικοινωνούν μέσω ενός ειδικού API (`NDB API`).

2.  **Cluster Partitioning & Replication (Slide 48):**
    *   Το MySQL Cluster χρησιμοποιεί εγγενώς **Partitioning** για να διαμερίσει αυτόματα τους πίνακες στα Data Nodes.
    *   Και ταυτόχρονα χρησιμοποιεί **Synchronous Replication** για να διατηρήσει αντίγραφα των partitions σε διαφορετικούς Data Nodes (συνήθως 2 ή 3 αντίγραφα).
    *   Π.χ. Ένας πίνακας διαιρεμένος σε 4 partitions, με 2 replicas ανά partition, θα έχει 8 fragments (4x2), κατανεμημένα στα Data Nodes.
    *   **Σημαντικό:** Αυτός ο συνδυασμός partitioning και built-in synchronous replication είναι αυτό που παρέχει την υψηλή διαθεσιμότητα και fault tolerance του MySQL Cluster, εγγυώμενος ότι οι αλλαγές γίνονται μόνιμες (persistent) σε πολλαπλούς Data Nodes *πριν* η συναλλαγή ολοκληρωθεί.

---

### **Εφαρμογές & Παραδείγματα**
| Περίπτωση Χρήσης            | Κατάλληλη Τεχνολογία |
|-----------------------------|---------------------|
| Αναλύσεις μεγάλου όγκου δεδομένων | **Κατακερματισμός (Partitioning)** |
| Ταχύτερη ανάκτηση δεδομένων | **Κατακερματισμός + Αναπαραγωγή** |
| Αποφυγή απώλειας δεδομένων | **Συγχρονισμένη Αναπαραγωγή** |
| Ισορροπία φόρτου εργασίας | **Ασύγχρονη Αναπαραγωγή** |
| Υψηλή διαθεσιμότητα | **Clustering (Ομαδοποίηση)** |

---

### **Συμπεράσματα**
Το Partitioning είναι μια τεχνική για τη διαχείριση μεγάλων πινάκων **εντός ενός MySQL server** ή ως βάση για διανομή. Βελτιώνει την απόδοση συγκεκριμένων ερωτημάτων και διευκολύνει τη διαχείριση. Το Replication είναι μια τεχνική για **αντιγραφή δεδομένων μεταξύ διαφορετικών MySQL servers**, κυρίως για High Availability και Read Scaling. Ενώ το standard MySQL replication είναι ασύγχρονο, το MySQL Cluster είναι ένα παράδειγμα κατανεμημένης αρχιτεκτονικής που συνδυάζει αυτόματο partitioning και *συγχρονισμένη* αναπαραγωγή για εγγενή HA.

- Ο **Κατακερματισμός (Partitioning)** βελτιστοποιεί την απόδοση μεγάλων πινάκων.
- Η **Αναπαραγωγή (Replication)** διασφαλίζει την ανθεκτικότητα των δεδομένων.
- Η **Ομαδοποίηση (Clustering)** είναι απαραίτητη για εξαιρετικά μεγάλες εφαρμογές.

