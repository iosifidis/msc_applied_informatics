# **Column Stores & Cassandra**

## **1. Ανασκόπηση: Σχεσιακά Δεδομένα & OLTP**

*   **Σχεσιακές Βάσεις (Row Stores):** Σχεδιασμένες κυρίως για **OLTP (Online Transaction Processing)** - δηλαδή διαχείριση τρέχουσων συναλλαγών (CRUD - Create, Read, Update, Delete).
*   **Αποθήκευση:** Τα δεδομένα οργανώνονται και αποθηκεύονται φυσικά ως **γραμμές (rows)**. Όλες οι στήλες μιας γραμμής γράφονται μαζί στον δίσκο.
*   **Χαρακτηριστικά OLTP:**
    *   Συναλλαγές που αφορούν **λίγες εγγραφές (records/tuples)** κάθε φορά.
    *   **Συχνές ενημερώσεις (Frequent updates)** και ερωτήματα.
    *   Πολλοί ταυτόχρονοι χρήστες.
    *   Απαίτηση για γρήγορους χρόνους απόκρισης.
*   **Βελτιστοποίηση:** Οι Row Stores είναι βελτιστοποιημένες για **εγγραφές (write-optimized)** σε αυτή τη λογική – η εισαγωγή ή διαγραφή μιας εγγραφής απαιτεί συνήθως μία φυσική εγγραφή στον δίσκο.
*   **Μειονέκτημα:** Η ανάγνωση μόνο συγκεκριμένων στηλών απαιτεί την ανάγνωση ολόκληρης της γραμμής από τον δίσκο, διαβάζοντας **περιττά δεδομένα (unnecessary data)** και σπαταλώντας μνήμη και εύρος ζώνης Ι/Ο.

## **2. Η Ανάγκη για OLAP (Online Analytical Processing)**

*   **OLAP:** Επεξεργασία δεδομένων για **υποστήριξη αποφάσεων (decision support)** και **επιχειρηματική ευφυΐα (business intelligence)**. Συχνά περιλαμβάνει τη δημιουργία αναφορών (reports), συχνά offline.
*   **Χαρακτηριστικά OLAP:**
    *   Συναλλαγές/Ερωτήματα που αφορούν **μεγάλο αριθμό εγγραφών**.
    *   Συχνά **Ad-hoc ερωτήματα** (μη προκαθορισμένα) και **σπάνιες ενημερώσεις**.
    *   Λίγοι χρήστες (decision-makers).
    *   Απαίτηση για γρήγορους χρόνους απόκρισης, παρά τον όγκο δεδομένων.
*   **Περιορισμός Σχεσιακών Βάσεων:** Είναι βελτιστοποιημένες για OLTP, όχι ιδανικές για τυπικά OLAP φορτία.
*   **Data Warehouse (Αποθήκη Δεδομένων):**
    *   Ξεχωριστή βάση βελτιστοποιημένη για OLAP.
    *   Δεδομένα: **Ιστορικά (historical), περιληπτικά (summarized), συναθροισμένα (aggregated)**.
    *   Λογικός Σχεδιασμός: Διαφορετικός από OLTP, συνήθως **Star Schema** ή **Snowflake Schema**.
    *   **Unnormalized Data:** Συχνά περιέχει αποκανονικοποιημένα δεδομένα για βελτίωση απόδοσης.
    *   Λειτουργίες: Κυρίως **αναγνώσεις (mostly read operations)**, με ενημερώσεις που γίνονται περιοδικά σε **παρτίδες (batch updates)**.

## **3. Η Προσέγγιση των Column Stores (Αποθήκες Στηλών)**

*   **Λογικό Μοντέλο:** Μπορεί να είναι το ίδιο με το Σχεσιακό Μοντέλο (πίνακες, στήλες, γραμμές).
*   **Φυσική Αποθήκευση (Κύρια Διαφορά):** Τα δεδομένα για **κάθε στήλη (column) αποθηκεύονται μαζί** στον δίσκο, αντί για τις γραμμές. (Διαφ. 5, 7).
*   **Βασικό Πλεονέκτημα (Key Intuition):** Διαβάζεις από τον δίσκο **μόνο τις στήλες που είναι απαραίτητες** για το ερώτημά σου. Αν ένα ad-hoc query χρειάζεται 2 από τις 20 στήλες ενός πίνακα, διαβάζονται μόνο τα δεδομένα αυτών των 2 στηλών.
*   **Χαρακτηριστικά:**
    *   **Βελτιστοποιημένες για Aggregation Queries:** Ερωτήματα όπως `SUM(column)`, `AVG(column)`, `COUNT(column)` είναι πολύ γρήγορα γιατί διαβάζουν συνεχόμενα μόνο τα δεδομένα της συγκεκριμένης στήλης (Διαφ. 8).
    *   **Υψηλή Συμπιεστότητα (High Compressibility):**
        *   Επειδή οι τιμές μιας στήλης είναι συνήθως ομοειδείς και επαναλαμβανόμενες, συμπιέζονται πολύ καλά.
        *   Η συμπίεση μειώνει τον όγκο δεδομένων => μειώνει το Ι/Ο.
        *   Βελτιώνει την απόδοση Ι/Ο: μειώνει seek times (σχετικά δεδομένα πιο κοντά), μειώνει transfer times, αυξάνει buffer hit rate.
        *   Μέθοδοι: Dictionary Encoding, Delta Encoding (αποθήκευση διαφορών από μια τιμή βάσης, ειδικά αν τα δεδομένα είναι ταξινομημένα) κ.ά. (Διαφ. 9, 10).
    *   **Ποινή στις Εγγραφές (Write Penalty):** Η εισαγωγή μιας νέας *γραμμής* απαιτεί εγγραφή σε πολλαπλά σημεία στον δίσκο (ένα για κάθε στήλη), αυξάνοντας το κόστος Ι/Ο για τις εγγραφές/ενημερώσεις σε σχέση με τις row stores (Διαφ. 11).

## **4. Αρχιτεκτονική Column Stores (Αντιμετώπιση Write Penalty)**

*   **Πρόβλημα:** Οι συνεχείς ενημερώσεις (updates) είναι προβληματικές για τις καθαρές column stores λόγω της ποινής στις εγγραφές. Οι μαζικές ενημερώσεις (batch updates) δεν παρέχουν πάντα ενημερωμένα δεδομένα.
*   **Λύση: Delta Store (Διαφ. 13, 14)**
    *   Χρησιμοποιείται μια **ενδιάμεση, βελτιστοποιημένη για εγγραφές "delta store"**.
    *   **Χαρακτηριστικά Delta Store:**
        *   Συνήθως **in-memory**.
        *   Υβριδική αποθήκευση (row/column) ή καθαρά row-oriented.
        *   Δεδομένα **ασυμπίεστα (uncompressed)**.
        *   Βελτιστοποιημένη για **συχνές ενημερώσεις (frequent updates)**.
    *   Οι νέες εγγραφές/ενημερώσεις πηγαίνουν πρώτα στην delta store.
    *   **Περιοδική Συγχώνευση (Merge):** Τα δεδομένα από την delta store συγχωνεύονται περιοδικά με την κύρια, συμπιεσμένη column store στο παρασκήνιο (asynchronous tuple mover).
    *   **Ερωτήματα (Queries):** Ένα ερώτημα μπορεί να χρειαστεί να διαβάσει δεδομένα και από την κύρια column store και από την delta store και να συνδυάσει τα αποτελέσματα.

## **5. Projections (Προβολές) (Διαφ. 15, 16)**

*   Σε ορισμένες column store (όπως C-Store/Vertica), ένας λογικός πίνακας (logical table) αναπαρίσταται φυσικά ως ένα σύνολο από **προβολές (projections)**.
*   Κάθε projection περιέχει ένα **υποσύνολο των στηλών** του λογικού πίνακα.
*   Οι στήλες μέσα σε ένα projection αποθηκεύονται ξεχωριστά (columnar storage) αλλά μοιράζονται μια κοινή **σειρά ταξινόμησης (sort order)** που ορίζεται από ένα **κλειδί ταξινόμησης (SORT KEY)**.
*   Κάθε στήλη του λογικού πίνακα πρέπει να υπάρχει σε τουλάχιστον ένα projection.
*   Μια στήλη μπορεί να υπάρχει σε πολλαπλά projections, ενδεχομένως με **διαφορετική σειρά ταξινόμησης** σε καθένα.
*   **Δημιουργία Projections:**
    *   Χειροκίνητα από τον διαχειριστή (DBA).
    *   "On the fly" από τον βελτιστοποιητή ερωτημάτων (query optimizer) βάσει ενός συγκεκριμένου ερωτήματος.
    *   Μαζικά, βάσει ανάλυσης ιστορικών φορτίων εργασίας (historical workloads).
*   **Σκοπός:** Βελτιστοποίηση της απόδοσης για συγκεκριμένα μοτίβα ερωτημάτων, παρέχοντας τα δεδομένα στις κατάλληλες στήλες και με την κατάλληλη ταξινόμηση.

## **6. Εισαγωγή στην Cassandra**

*   **Προέλευση:** Δημιουργήθηκε αρχικά στο Facebook, δόθηκε ως open-source στο Apache Software Foundation το 2008.
*   **Τεχνολογία:** Γραμμένη σε Java, cross-platform.
*   **Βασικοί Στόχοι:**
    *   **Κλιμάκωση (Scalability):** Υψηλή απόδοση ανάγνωσης/εγγραφής (read/write throughput).
    *   **Διαθεσιμότητα (Availability):** Υψηλή ανθεκτικότητα σε σφάλματα.
    *   **Ανοχή σε Σφάλματα (Fault Tolerance).**

## **7. Μοντέλο Δεδομένων Cassandra (Επιρροές από Google BigTable)**

*   **Βάση:** Επηρεασμένο από το paper της Google για το **BigTable**.
*   **Οργάνωση:**
    *   Δεδομένα οργανωμένα σε **πίνακες (tables)** (στην ορολογία CQL) ή **Column Families** (στην παλαιότερη ορολογία Thrift/εσωτερική δομή).
    *   Οι πίνακες είναι ευρετηριασμένοι και ταξινομημένοι βάσει του **κλειδιού γραμμής (rowkey)** (ή πιο σωστά, του **Partition Key**).
    *   Οι στήλες (columns) ομαδοποιούνται σε **Οικογένειες Στηλών (Column Families - CFs)**.
        *   Οι CFs πρέπει να **ορίζονται εκ των προτέρων (defined in advance)**, κάνοντας το Cassandra ένα "structured storage system".
        *   Περιορισμένος αριθμός CFs ανά πίνακα.
*   **Column Family:** Μια πολυδιάστατη αντιστοίχιση (multidimensional map). Τα δεδομένα για μια συγκεκριμένη CF αποθηκεύονται μαζί στον δίσκο.
*   **Κελί (Cell):** Η τομή μιας γραμμής και μιας στήλης.
    *   Μπορεί να περιέχει **πολλαπλές εκδόσεις (versions)** της τιμής, η καθεμία με **timestamp**. Οι τιμές αποθηκεύονται συνήθως σε φθίνουσα σειρά timestamp (πιο πρόσφατη πρώτα). (Διαφ. 20).
    *   Οι διαγραφές γίνονται μέσω ειδικών δεικτών **tombstone markers** (soft delete). Τα δεδομένα διαγράφονται οριστικά αργότερα κατά τη διαδικασία compaction.
*   **Πίνακας Cassandra (Table/CF):** Μπορεί να θεωρηθεί ως "κατανεμημένος πολυδιάστατος χάρτης (map) που ευρετηριάζεται από ένα κλειδί (key)".
    *   **Στήλες (Columns):** Μπορεί να είναι απλές (simple) ή "super" (super columns - παλαιότερη έννοια για ένθεση, τώρα γίνεται με collections ή User Defined Types). Μια στήλη ουσιαστικά περιέχει (Όνομα, Τιμή, Timestamp).
    *   **Γραμμές (Rows):** Μπορούν να έχουν **μεταβλητό αριθμό στηλών**. Δεν απαιτείται όλες οι γραμμές να έχουν τις ίδιες στήλες (sparse).
*   **Ευρετήρια (Indexes):**
    *   **Πρωτεύον Ευρετήριο (Primary Index):** Βασίζεται στο **row key** (partition key + clustering columns).
    *   **Δευτερεύον Ευρετήριο (Secondary Index):** Μπορούν να δημιουργηθούν ευρετήρια πάνω στα **ονόματα/τιμές συγκεκριμένων στηλών**.

## **8. Αντιστοίχιση Ορολογίας: RDBMS vs Cassandra**

| RDBMS                 | Cassandra                    |
| :-------------------- | :--------------------------- |
| database instance     | cluster                      |
| database              | keyspace                     |
| table                 | table / column family        |
| row                   | row                          |
| column (ίδιο για όλες τις γραμμές) | column (μπορεί να διαφέρει ανά γραμμή) |

## **9. Λειτουργίες & Cassandra Query Language (CQL)**

*   **Βασικές Λειτουργίες:** GET, SET, DEL (παλαιότερο Thrift API).
*   **CQL:**
    *   Η κύρια διεπαφή αλληλεπίδρασης σήμερα.
    *   **SQL-like:** Έχει σύνταξη παρόμοια με την SQL (CREATE, ALTER, UPDATE, DROP, DELETE, INSERT, SELECT).
    *   **Απλούστερη από SQL:**
        *   **Όχι Joins, Όχι Subqueries.**
        *   Πιο **περιορισμένες συνθήκες WHERE** και **ORDER BY**.
    *   **Αφαίρεση (Abstraction):** Παρέχει ένα πιο "σχεσιακό-φιλικό" μοντέλο (πίνακες, γραμμές, στήλες) πάνω από την υποκείμενη δομή των column families. (Διαφ. 24, 25).
    *   **Μοντελοποίηση "Wide Rows":** Οι πλατιές γραμμές (γραμμές με δυνητικά χιλιάδες στήλες) μοντελοποιούνται στο CQL χρησιμοποιώντας **σύνθετα πρωτεύοντα κλειδιά (composite primary keys)**, τα οποία αποτελούνται από:
        *   **Partition Key:** Καθορίζει σε ποιον κόμβο (partition) θα αποθηκευτεί η γραμμή.
        *   **Clustering Columns:** Καθορίζουν τη σειρά ταξινόμησης των στηλών *μέσα* σε ένα partition.
    *   **Περιορισμοί `SELECT` (Διαφ. 51, 52):**
        *   Δεν υποστηρίζονται συναθροίσεις (aggregates) όπως SUM, AVG απευθείας στο SELECT (αν και υπάρχουν workarounds ή drivers που το υποστηρίζουν).
        *   Τα φίλτρα WHERE και η ταξινόμηση ORDER BY είναι κυρίως **περιορισμένα στις clustering columns** και πρέπει να αφορούν δεδομένα **μέσα σε ένα συγκεκριμένο partition key**. Range queries και ταξινόμηση λειτουργούν μόνο στις clustering columns.

## **10. Συλλογές (Collections) στην Cassandra (Διαφ. 26, 27)**

*   Το CQL επιτρέπει στις στήλες να περιέχουν τύπους συλλογών:
    *   `set`: Ένα σύνολο από μοναδικές τιμές (π.χ., `set<text>`).
    *   `list`: Μια ταξινομημένη λίστα στοιχείων (επιτρέπει διπλότυπα) (π.χ., `list<int>`).
    *   `map`: Μια αντιστοίχιση κλειδιού-τιμής (π.χ., `map<text, text>`).

## **11. Διαχείριση Keyspaces & Tables/Indexes (Διαφ. 28, 29)**

*   Το CQL παρέχει εντολές DDL (Data Definition Language) για:
    *   **Keyspaces:** `CREATE KEYSPACE` (ορίζοντας τη στρατηγική replication), `USE`, `ALTER KEYSPACE`, `DROP KEYSPACE`.
    *   **Tables (Column Families):** `DROP TABLE`, `TRUNCATE TABLE` (διαγράφει δεδομένα, όχι τον πίνακα).
    *   **Indexes:** `CREATE INDEX` (για δευτερεύοντα ευρετήρια), `DROP INDEX`.

## **12. Partitioning στην Cassandra (Διαφ. 30-34)**

*   **Consistent Hashing:** Χρησιμοποιείται για την κατανομή των δεδομένων στους κόμβους (ring structure).
*   **Partitioner:** Ένα συστατικό που καθορίζει πώς ένα κλειδί γραμμής (partition key) αντιστοιχίζεται σε έναν κόμβο στον δακτύλιο.
    *   `Murmur3Partitioner` (default): Γρήγορο, μη-κρυπτογραφικό hash, παρέχει καλή ομοιόμορφη κατανομή.
    *   `RandomPartitioner` (παλαιότερο, MD5): Επίσης ομοιόμορφη κατανομή.
    *   `ByteOrderedPartitioner`: Ταξινομεί τις γραμμές λεξικογραφικά βάσει των bytes του κλειδιού. **Προσοχή:** Μπορεί να οδηγήσει σε άνιση κατανομή φορτίου αλλά επιτρέπει range scans σε επίπεδο partition key (σπάνια χρησιμοποιείται πλέον).
*   **Virtual Nodes (VNodes):**
    *   Για να αποφευχθούν τα προβλήματα του απλού consistent hashing (άνιση κατανομή, δύσκολη προσθήκη/αφαίρεση κόμβων), κάθε φυσικός κόμβος κατέχει **πολλά, μικρότερα εύρη (ranges)** στον δακτύλιο, τα οποία αντιστοιχούν σε virtual nodes (π.χ., 256 VNodes ανά φυσικό κόμβο).
    *   **Πλεονεκτήματα VNodes:**
        *   Καλύτερη εξισορρόπηση φορτίου (balancing).
        *   Ευκολότερη προσθήκη/αφαίρεση κόμβων (λιγότερα δεδομένα προς μετακίνηση).
        *   Καλύτερη διαχείριση ετερογενούς hardware.
        *   Καταμερισμός του φόρτου ενός κόμβου που αποτυγχάνει σε πολλούς άλλους.

## **13. Gossip Protocol & Αρχιτεκτονική P2P (Διαφ. 35, 36)**

*   **Αρχιτεκτονική:** Η Cassandra έχει **masterless (peer-to-peer)** αρχιτεκτονική. Όλοι οι κόμβοι είναι **ίσοι (equal)**.
*   **Προσωρινοί Ρόλοι:** Κόμβοι αναλαμβάνουν προσωρινά ρόλους όπως:
    *   **Coordinator:** Ο κόμβος που λαμβάνει το αίτημα από τον client και συντονίζει την εκτέλεσή του (επικοινωνία με replicas).
    *   **Seed Node:** Χρησιμοποιείται για την αρχική ανακάλυψη του cluster από νέους κόμβους.
*   **Πρόβλημα:** Χωρίς master, πώς διατηρούν όλοι οι κόμβοι ενημερωμένη εικόνα της κατάστασης και της τοπολογίας του cluster;
*   **Λύση: Gossip Protocol**
    *   Μια **πιθανοτική (probabilistic)** διαδικασία ανταλλαγής πληροφοριών.
    *   Κάθε κόμβος περιοδικά (π.χ., κάθε δευτερόλεπτο) επικοινωνεί με λίγους άλλους τυχαίους κόμβους και ανταλλάσσει πληροφορίες για την κατάστασή του και την κατάσταση άλλων κόμβων που γνωρίζει.
    *   Οι πληροφορίες διαδίδονται σταδιακά σε όλο το cluster.
    *   Επιτρέπει την **πιθανοτική ανίχνευση σφαλμάτων (failure detection)**. Οι κόμβοι γίνονται σταδιακά "ανήσυχοι" (worried) για κόμβους που δεν απαντούν.
    *   **Χωρίς Single Point of Failure.**

## **14. Replication & Στρατηγικές Τοποθέτησης (Διαφ. 37-40)**

*   **Replication Factor (N):** Ο συνολικός αριθμός αντιγράφων (replicas) που θα διατηρούνται για κάθε κομμάτι δεδομένων. Ορίζεται στο επίπεδο του keyspace.
*   **Όλα τα Replicas Ίσα:** Δεν υπάρχει master replica.
*   **Coordinator:** Ο κόμβος που αντιστοιχεί στο hash του partition key είναι υπεύθυνος για τη γραφή των δεδομένων στα Ν replicas (στον εαυτό του και στα Ν-1 επόμενα).
*   **Στρατηγικές Τοποθέτησης (Replica Placement Strategies):**
    *   `SimpleStrategy`: (Default, για ένα data center) Τοποθετεί τα replicas στους επόμενους N-1 κόμβους στον δακτύλιο.
    *   `NetworkTopologyStrategy`: (Για πολλαπλά data centers ή racks) Επιτρέπει τον ορισμό του αριθμού των replicas ανά rack/data center, προσπαθώντας να τοποθετήσει τα replicas σε διαφορετικά failure domains για μέγιστη ανθεκτικότητα.
*   **Αριθμός Replicas ανά Data Center:** Συμβιβασμός μεταξύ τοπικών αναγνώσεων (χαμηλό latency) και αντοχής σε σφάλματα. Συνήθως 2-3 replicas ανά DC.

## **15. Συνέπεια (Consistency) (Διαφ. 41-45)**

*   **Tunable Consistency (Ρυθμιζόμενη Συνέπεια):** Όπως στο Dynamo, η Cassandra επιτρέπει στον client να ορίσει το **επίπεδο συνέπειας (consistency level)** για κάθε λειτουργία ανάγνωσης (R) ή εγγραφής (W), επηρεάζοντας τον αριθμό των κόμβων που πρέπει να απαντήσουν.
    *   Ν: Ορίζεται από το Replication Factor του keyspace.
*   **Επίπεδα Συνέπειας Εγγραφής (Write Consistency):**
    *   `ANY`: Αρκεί να γράψει ένας κόμβος (ακόμα και μέσω hinted handoff). Γρηγορότερο, λιγότερο συνεπές.
    *   `ONE`, `TWO`, `THREE`: Απαιτεί επιβεβαίωση από 1, 2, ή 3 κόμβους.
    *   `QUORUM`: Απαιτεί επιβεβαίωση από την πλειοψηφία των replicas (`floor(N/2) + 1`) σε όλα τα DCs.
    *   `LOCAL_QUORUM`: Απαιτεί επιβεβαίωση από την πλειοψηφία στο *τρέχον* data center του coordinator.
    *   `EACH_QUORUM`: Απαιτεί επιβεβαίωση από πλειοψηφία *σε κάθε* data center.
    *   `ALL`: Απαιτεί επιβεβαίωση από *όλους* τους replicas. Ισχυρότερη συνέπεια, αλλά χαμηλότερη διαθεσιμότητα (αποτυγχάνει αν έστω ένας κόμβος είναι κάτω).
*   **Επίπεδα Συνέπειας Ανάγνωσης (Read Consistency):**
    *   `ONE`, `TWO`, `THREE`: Επικοινωνεί με τον/τους πλησιέστερο(ους) κόμβο(υς).
    *   `LOCAL_ONE`: Διαβάζει από τον πλησιέστερο στο τρέχον DC.
    *   `QUORUM`: Διαβάζει από πλειοψηφία replicas σε όλα τα DCs και επιστρέφει την πιο πρόσφατη τιμή.
    *   `LOCAL_QUORUM`: Διαβάζει από πλειοψηφία στο τρέχον DC.
    *   `EACH_QUORUM`: Διαβάζει από πλειοψηφία σε κάθε DC.
    *   `ALL`: Διαβάζει από όλους τους replicas.
*   **Σχέση R + W > N:** Εξασφαλίζει συνήθως strong consistency (με την προϋπόθεση ότι τα partitions δεν αλλάζουν κατά τη διάρκεια).
*   **Πίνακας Συνέπειας (Διαφ. 45):** Δείχνει τους συνδυασμούς και τα χαρακτηριστικά τους.

## **16. Anti-entropy Mechanisms (Μηχανισμοί Αντι-Εντροπίας) (Διαφ. 46)**

*   **Ανάγκη:** Όταν η συνέπεια εγγραφής δεν είναι `ALL`, ασυνέπειες μπορεί να συσσωρευτούν με τον χρόνο (εντροπία αυξάνεται).
*   **Μηχανισμοί Επισκευής:**
    *   **Hinted Handoff:** Αν ο κόμβος που πρέπει να λάβει μια εγγραφή είναι προσωρινά μη διαθέσιμος, ένας άλλος κόμβος αποθηκεύει προσωρινά την εγγραφή ("hint") γι' αυτόν. Όταν ο κόμβος επιστρέψει (εντός χρονικού ορίου, π.χ., 3 ώρες), του αποστέλλεται η ενημέρωση. (Χρησιμοποιείται από το consistency level `ANY`).
    *   **Read Repair:** Κατά τη διάρκεια μιας ανάγνωσης (που απαιτεί επικοινωνία με πολλαπλούς κόμβους, π.χ., `QUORUM`), αν ο coordinator εντοπίσει ότι κάποια replicas έχουν παλιότερα δεδομένα (stale data), στέλνει ασύγχρονα μια ενημέρωση για να τα διορθώσει.
    *   **(Υπάρχει και explicit repair process που τρέχει ο διαχειριστής).**

## **17. Timestamps & Conflict Resolution στην Cassandra (Διαφ. 48)**

*   **Μηχανισμός:** Η Cassandra χρησιμοποιεί κυρίως **timestamps** για την επίλυση συγκρούσεων.
*   **Last Write Wins (LWW):** Η εγγραφή (τιμή) με το **μεγαλύτερο timestamp** θεωρείται η πιο πρόσφατη και "κερδίζει".
*   **Granularity (Κοκκοποίηση):** Η μονάδα τροποποίησης, ταυτοχρονισμού (concurrency) και επίλυσης συγκρούσεων είναι το **μεμονωμένο κελί (individual cell)** (η τομή γραμμής-στήλης).

## **18. Lightweight Transactions (LWT) (Διαφ. 49, 50, 51)**

*   **Αρχιτεκτονική Χωρίς Κλειδώματα (Lockless):** Η Cassandra δεν χρησιμοποιεί παραδοσιακά κλειδώματα.
*   **LWT:** Παρέχει έναν τρόπο για **ατομικές (atomic) λειτουργίες υπό συνθήκη**, κυρίως για ένα μεμονωμένο partition key.
*   **Compare-and-Set (CAS) Pattern:** Υποστηρίζει το μοτίβο "έλεγξε-και-όρισε".
    *   Ελέγχει αν η τρέχουσα τιμή ενός κελιού είναι αυτή που αναμένεται.
    *   **Αν ναι**, εκτελεί την ενημέρωση/εγγραφή (set). **Αν όχι**, η λειτουργία αποτυγχάνει.
    *   Π.χ., `UPDATE ... SET balance=1200 WHERE id=100 IF balance=1000;`
*   **Παρόμοιο με Optimistic Locking:** Υποθέτει ότι οι συγκρούσεις είναι σπάνιες και απλώς ελέγχει για σύγκρουση πριν την τελική εγγραφή.
*   **Υλοποίηση:** Χρησιμοποιεί μια παραλλαγή του αλγορίθμου consenso **Paxos** στο παρασκήνιο για να εγγυηθεί τη γραμμικοποίηση (linearizability) αυτών των λειτουργιών. Έχει μεγαλύτερο overhead από τις κανονικές εγγραφές.

## **19. Σύνοψη Cassandra (Διαφ. 53)**

*   **Άδεια:** Open-source (Apache License).
*   **Μοντέλο Δεδομένων:** Column Family Store (βασισμένο στο BigTable) αλλά με μια προβολή/αφαίρεση ως πίνακες μέσω CQL.
*   **Συναλλαγές/Συνέπεια:** Tunable Consistency, υποστήριξη LWT (μέσω Paxos).
*   **Clustering/Partitioning:** Consistent Hashing (με VNodes).
*   **API:** Κυρίως CQL (Cassandra Query Language).
