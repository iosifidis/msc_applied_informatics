# **Εισαγωγή σε Next Generation Databases: NoSQL και Big Data**

## **1. Εισαγωγή και Βασικοί Ορισμοί (Διαφάνειες 1-2)**

*   **Τι είναι Βάση Δεδομένων;** Μια οργανωμένη συλλογή δεδομένων, αποθηκευμένη και προσβάσιμη ηλεκτρονικά. Σκεφτείτε την ως ένα "ηλεκτρονικό αρχείο" με δομή.
*   **Τι είναι Σύστημα Διαχείρισης Βάσεων Δεδομένων (ΣΔΒΔ - DBMS);** Το λογισμικό που αποτελεί τη "γέφυρα" μεταξύ χρηστών/εφαρμογών και της βάσης δεδομένων. Είναι υπεύθυνο για την αποθήκευση, ανάκτηση, ασφάλεια και διαχείριση των δεδομένων.

## **2. Οι Τρεις Επαναστάσεις των Βάσεων Δεδομένων (Διαφάνεια 3)**

Η ιστορία των βάσεων δεδομένων μπορεί να χωριστεί σε τρεις μεγάλες περιόδους/επαναστάσεις:

*   **1η Επανάσταση (1950-1972): Προ-Σχεσιακά (Pre-Relational)**
    *   Εστίαση σε φυσικά μέσα αποθήκευσης (μαγνητική ταινία, μαγνητικός δίσκος).
    *   Ανάδυση πρώτων μεθόδων ευρετηρίασης (ISAM).
    *   Συστήματα πλοηγούμενης προσπέλασης (Navigational Models) όπως τα Ιεραρχικά (IMS) και τα Δικτυακά (CODASYL - IDMS).
    *   **Σημαντικό:** Τα δεδομένα και η λογική της εφαρμογής ήταν συνδεδεμένα, κάνοντας δύσκολες τις αλλαγές.

*   **2η Επανάσταση (1972-2005): Σχεσιακά (Relational)**
    *   Κυριαρχία του Σχεσιακού Μοντέλου, βασισμένο στις ιδέες του E.F. Codd.
    *   Ανάπτυξη της γλώσσας SQL και εμπορικών RDBMS (System R, Oracle, Ingres, DB2, Sybase, Postgres, SQL Server, MySQL).
    *   Εστίαση στην ανεξαρτησία δεδομένων (logically disconnected from physical storage), θεωρητική βάση, ακεραιότητα.
    *   Επικράτηση του μοντέλου Client-Server.
    *   **Σημαντικό:** Η "χρυσή εποχή" των παραδοσιακών βάσεων δεδομένων.

*   **3η Επανάσταση (2005-2015+): Επόμενη Γενιά (The Next Generation)**
    *   Ανάδυση των NoSQL και Big Data συστημάτων.
    *   Επηρέαση από εταιρείες όπως η Google και η Amazon.
    *   Εμφάνιση λύσεων όπως MapReduce, Hadoop, Dynamo, Cassandra, MongoDB, Riak, κλπ.
    *   **Σημαντικό:** Απάντηση στις προκλήσεις του Web-scale, των Big Data, της κατανεμημένης επεξεργασίας και της ευελιξίας.

## **3. Πρώτη Επανάσταση: Αρχές και Περιορισμοί (Διαφάνειες 4-8)**

*   Η εμφάνιση του όρου "βάση δεδομένων" τη δεκαετία του '60 για συλλογές όπως εγκυκλοπαίδειες, λεξικά (datasets) και οργανωμένα αρχεία (βιβλιοθήκες).
*   Ο ρόλος των Υπολογιστών:
    *   Μαγνητικοί Δίσκοι: επέτρεψαν την *άμεση* προσπέλαση σε μεμονωμένες εγγραφές (βελτίωση σε σχέση με την ταινία).
    *   ISAM: μέθοδος ευρετηριακής ακολουθιακής προσπέλασης.
    *   OLTP (On-Line Transaction Processing): πρώτες εφαρμογές που απαιτούσαν επεξεργασία συναλλαγών.
*   **Ανάδυση των ΣΔΒΔ:** Ξεχωρίζει τη λογική διαχείρισης δεδομένων από την εφαρμογή. Εξασφαλίζει επιδόσεις και ακεραιότητα.
*   **Ιεραρχικό και Δικτυακό Μοντέλο:** Πλοηγούμενα μοντέλα. Τα δεδομένα είχαν μια αυστηρή (ιεραρχική) ή πιο ευέλικτη (δικτυακή) δομή σχέσεων. Η προσπέλαση γινόταν μέσω προκαθορισμένων "μονοπατιών".
*   **Περιορισμοί Πρώτων ΣΔΒΔ:**
    *   **Άκαμπτο Μοντέλο Δεδομένων:** Δύσκολη αλλαγή δομής.
    *   **Άκαμπτη Εκτέλεση Ερωτημάτων:** Η προσπέλαση καθοριζόταν από τα πλοηγούμενα μονοπάτια.
    *   **Επεξεργασία "μίας-εγγραφής-τη-φορά" (One Record At A Time):** Βασικές λειτουργίες όπως CRUD (Create, Read, Update, Delete).
    *   **Πολυπλοκότητα Αναλυτικών Ερωτημάτων:** Απαιτούσαν σύνθετο κώδικα για τη διάσχιση των δομών.

## **4. Δεύτερη Επανάσταση: Το Σχεσιακό Μοντέλο (Διαφάνειες 9-15)**

*   **Edgar F. Codd:** Θεμελιωτής του σχεσιακού μοντέλου. Ανταποκρίθηκε στις αδυναμίες των προηγούμενων μοντέλων (δυσχρηστία, έλλειψη θεωρίας, ανάμειξη λογικής/φυσικής υλοποίησης).
*   **Βασικές Έννοιες Σχεσιακού Μοντέλου:**
    *   **Λογική ανεξαρτησία δεδομένων:** Αποσύνδεση από τη φυσική αποθήκευση.
    *   **Tuples (Πλειάδες):** Μη ταξινομημένη συλλογή τιμών γνωρισμάτων (μία "γραμμή" στον πίνακα).
    *   **Relation (Σχέση):** Συλλογή διακριτών πλειάδων (ένας "πίνακας").
    *   **Constraints (Περιορισμοί):** Επιβολή συνοχής (π.χ. κλειδιά - keys).
    *   **Operations (Λειτουργίες):** Σύνολα βασισμένες πράξεις πάνω σε σχέσεις (project, join, union, κλπ.).
    *   Κάθε γραμμή σε έναν πίνακα αναγνωρίζεται από μία μοναδική τιμή κλειδιού.
    *   Όχι εμφωλευμένες δομές (flat structure).
*   **Κανονικοποίηση Βάσεων Δεδομένων (Normalization):**
    *   Διαδικασία για τη βελτίωση της δομής ενός σχεσιακού σχήματος, μειώνοντας την επανάληψη (redundancy) και βελτιώνοντας την ακεραιότητα.
    *   Επίπεδα κανονικοποίησης (3NF, BCNF, 4NF, κλπ.).
    *   Ο κανόνας της 3NF: "όλα τα μη-κλειδιά γνωρίσματα πρέπει να εξαρτώνται *από το κλειδί, ολόκληρο το κλειδί και τίποτα άλλο εκτός από το κλειδί* - βοηθάμε Codd!". Στόχος είναι να μην υπάρχουν μεταβατικές εξαρτήσεις (dependencies).
    *   Παράδειγμα: Μετατροπή un-normalized δομής (Test scores) σε normalized σχεσιακούς πίνακες (Students, Tests, Test Answers, Test Questions) - βελτιώνει τη δομή αλλά μπορεί να απαιτήσει joins για σύνθετα ερωτήματα.
*   **Συναλλαγές (Transactions) - ACID Properties (Jim Gray):**
    *   **Atomic (Ατομικότητα):** Μία συναλλαγή είτε ολοκληρώνεται επιτυχώς (commit) είτε αποτυγχάνει πλήρως (rollback) - "όλα ή τίποτα".
    *   **Consistent (Συνέπεια):** Μία συναλλαγή φέρνει τη βάση από μία συνεπή κατάσταση σε μία άλλη συνεπή κατάσταση.
    *   **Isolated (Απομόνωση):** Η εκτέλεση πολλαπλών ταυτόχρονων συναλλαγών είναι ισοδύναμη με κάποια σειριακή εκτέλεσή τους - οι συναλλαγές δεν "βλέπουν" τις ενδιάμεσες, μη-ολοκληρωμένες αλλαγές άλλων.
    *   **Durable (Μονιμότητα):** Οι αλλαγές μίας ολοκληρωμένης (committed) συναλλαγής παραμένουν μόνιμες, ακόμη και σε περίπτωση αποτυχίας του συστήματος (π.χ. διακοπή ρεύματος).
    *   Οι ACID ιδιότητες είναι κρίσιμες για εφαρμογές που απαιτούν υψηλή ακεραιότητα (π.χ. τραπεζικά συστήματα).
*   **Η Εποχή των RDBMS:**
    *   Ανάπτυξη ισχυρών RDBMS με SQL, ACID συναλλαγές και βάση στο σχεσιακό μοντέλο (Oracle, SQL Server, MySQL κ.α. κυριαρχούν).
    *   Υποστήριξη μοντέλου Client-Server.
    *   Σύντομη αναφορά σε αντικειμενοστραφείς βάσεις (που δεν επικράτησαν ως γενική λύση).

## **5. Τρίτη Επανάσταση: NoSQL και Big Data (Διαφάνειες 16-41)**

*   **Ορισμός και Οδηγοί Ανάπτυξης (Triggers):**
    *   Η επανάσταση "NoSQL" ("Not Only SQL" πλέον).
    *   Κύριοι οδηγοί:
        *   **Web 2.0 και Social Media:** Μαζικό περιεχόμενο παραγόμενο από χρήστες, εκρηκτική αύξηση δεδομένων, υψηλές απαιτήσεις διαχείρισης.
        *   **Web-Scale Applications:** Εφαρμογές που εξυπηρετούν εκατομμύρια ή δισεκατομμύρια χρήστες παγκοσμίως.
        *   **Big Data:** Δεδομένα σε τεράστιο όγκο, υψηλή ταχύτητα παραγωγής/αλλαγής, ποικιλία μορφών (Volumes, Velocity, Variety, Veracity).
        *   **Cloud Computing:** Εύκολη πρόσβαση σε κατανεμημένη υποδομή.
        *   **Ευελιξία Σχήματος:** Προτίμηση σε δυναμικά δομημένα δεδομένα, με συχνές αλλαγές στο "σχήμα".
        *   **Open Source Community:** Δυναμική ανάπτυξη open source λύσεων.
*   **Γιατί τα RDBMS δεν ήταν Ιδανικά για Big Data/Web-Scale:**
    *   **Πλαίσιο Internet:** Δεδομένα μαζικά και *αραιά* (massive and sparse), *ημιδομημένα* (semi-structured) ή *αδόμητα* (unstructured), όχι πυκνά και ομοιόμορφα όπως συχνά σε παραδοσιακά RDBMS.
    *   Τα RDBMS είχαν σχεδιαστεί για δομημένα δεδομένα με "σχήμα στην εγγραφή" (schema on write), όπου η δομή είναι προκαθορισμένη.
    *   Με μαζικά, αραιά σύνολα δεδομένων, οι παραδοσιακοί μηχανισμοί αποθήκευσης και πρόσβασης "ζορίζονται".
*   **Πρωτοπόροι και Λύσεις:**
    *   **Google:** GFS (Google File System), MapReduce (μοντέλο παράλληλης επεξεργασίας), BigTable (κατανεμημένη column-family βάση).
    *   **Hadoop:** Open-Source υλοποίηση της Google stack. Οικονομικό, κλιμακούμενο σύστημα αποθήκευσης (HDFS) και επεξεργασίας (MapReduce/YARN). Χρησιμοποιεί "σχήμα στην ανάγνωση" (schema on read) - η ερμηνεία της δομής γίνεται κατά την επεξεργασία.
    *   **MapReduce:** Μοντέλο προγραμματισμού για κατανεμημένη επεξεργασία μεγάλων συνόλων δεδομένων. Φάσεις Map (χαρτογράφηση) και Reduce (μείωση/συγκέντρωση). Ιδανικό για παράλληλες διεργασίες όπως aggregates.
    *   **HBase:** Column-oriented βάση πάνω από το HDFS. Επιβάλλει *κάποια* δομή εντός των column families. Υποστηρίζει πολλαπλές εκδόσεις δεδομένων (timestamped).
    *   **Hive & Pig:** SQL-like interfaces (Hive) και γλώσσα ροής δεδομένων (Pig Latin) πάνω από το Hadoop, απλοποιώντας την ανάλυση Big Data.
    *   **Hadoop Ecosystem:** Ένα ευρύ φάσμα εργαλείων για διάφορες εργασίες (φώρτωση: Flume, Sqoop, συγχρονισμός: Zookeeper, workflows: Oozie, UI: Hue, streaming/messaging: Kafka, ML: Mahout, ταχεία επεξεργασία: Spark).
    *   **Amazon Dynamo:** Κατανεμημένη key-value βάση, σχεδιασμένη για υψηλή διαθεσιμότητα και αντοχή σε διαμερίσεις δικτύου.

## **6. Βασικές Έννοιες NoSQL: Scalability, Consistency, Availability (Διαφάνειες 42-60)**

*   **Κλιμακούμενοτητα (Scalability) και Ελαστικότητα (Elasticity):**
    *   **Vertical Scalability (Scale-Up):** Αναβάθμιση του υπάρχοντος server (περισσότερη CPU, RAM, κλπ.). Περιορισμοί από το hardware.
    *   **Horizontal Scalability (Scale-Out):** Προσθήκη περισσότερων μηχανών στο cluster. Ιδανικό για κατανεμημένα συστήματα. Το ζητούμενο για τις Web-Scale/Big Data εφαρμογές.
    *   **Τεχνικές Horizontal Scalability:** Replication (αναπαραγωγή), Sharding (διαμερισμός).
*   **Αναπαραγωγή (Replication):**
    *   Αποθήκευση αντιγράφων των δεδομένων σε πολλαπλούς κόμβους (nodes).
    *   Βελτιώνει τη *διαθεσιμότητα* (αν ένας κόμβος πέσει, υπάρχουν αντίγραφα αλλού).
    *   Βοηθά στην *κλιμάκωση των αναγνώσεων* (διαμοιρασμός φορτίου αναγνώσεων σε αντίγραφα).
    *   Ένα μοντέλο είναι το Master/Slave.
    *   Παράδειγμα caching: Memcached (cache layer για μείωση φορτίου στη βάση, βελτίωση αναγνώσεων).
*   **Διαμερισμός (Sharding / Partitioning):**
    *   Διαμερισμός μίας λογικής βάσης δεδομένων ή πίνακα σε πολλαπλά μέρη ("shards") που αποθηκεύονται σε διαφορετικούς φυσικούς servers.
    *   Μπορεί να είναι οριζόντιος (διαμερισμός γραμμών) ή κάθετος (διαμερισμός στηλών).
    *   Το σχήμα συχνά αντιγράφεται σε κάθε shard, αλλά τα δεδομένα διανέμονται βάσει μίας τιμής κλειδιού (sharding key).
    *   **Κλιμακώνει αναγνώσεις και εγγραφές** (διαμοιρασμός φορτίου στους servers).
    *   **Μειονεκτήματα Sharding:**
        *   **Πολυπλοκότητα Εφαρμογής:** Η εφαρμογή πρέπει να "ξέρει" σε ποιο shard είναι τα δεδομένα ή να έχει δυναμικό routing.
        *   **"Σακατεμένη" SQL (Crippled SQL):** Δύσκολα ή αδύνατα τα joins μεταξύ shards. Ερωτήματα που αφορούν πολλά shards είναι πολύπλοκα.
        *   **Απώλεια Ακεραιότητας Συναλλαγών:** Οι ACID εγγυήσεις σε RDBMS ισχύουν για μία βάση. Με sharding, μία συναλλαγή που αφορά πολλά shards δεν είναι εύκολο να είναι Atomic, Consistent, Isolated, Durable (απαιτούνται πιο σύνθετοι μηχανισμοί όπως 2PC - Two-Phase Commit, που σπάνια χρησιμοποιούνται στο NoSQL).
        *   **Λειτουργική Πολυπλοκότητα:** Απαιτεί rebalancing των shards αν αλλάξει το φορτίο ή η χωρητικότητα, δύσκολες αλλαγές σχήματος που αφορούν πολλά shards.
    *   **Τύποι Sharding:** Key-based/Hash (βάσει hash function), Range-based (βάσει εύρους τιμών κλειδιού), Directory-based (πίνακας lookup που αντιστοιχίζει κλειδιά σε shards).
*   **CAP Theorem (Brewer's Theorem):**
    *   Σε ένα *κατανεμημένο σύστημα* (με Replication) που υπόκειται σε *Partition Tolerance (διαμερίσεις δικτύου)*, μπορείτε να έχετε *μόνο* δύο από τις ακόλουθες ιδιότητες:
        *   **Consistency (Συνέπεια):** Όλοι οι client βλέπουν την ίδια (τελευταία) έκδοση των δεδομένων ταυτόχρονα.
        *   **Availability (Διαθεσιμότητα):** Κάθε request λαμβάνει μια (μη-σφάλμα) απάντηση (χωρίς εγγύηση ότι η απάντηση είναι η τελευταία ενημέρωση).
        *   **Partition Tolerance (Αντοχή σε Διαμερίσεις):** Το σύστημα συνεχίζει να λειτουργεί ακόμη και αν υπάρξει αποτυχία στην επικοινωνία μεταξύ κόμβων (το δίκτυο "σπάσει" σε κομμάτια). Αυτό είναι αναπόφευκτο σε μεγάλα κατανεμημένα συστήματα.
    *   Το θεώρημα λέει: **Choose 2 out of 3.** Σε ένα κατανεμημένο σύστημα, αντέχετε στις διαμερίσεις δικτύου (P). Άρα, πρέπει να επιλέξετε ανάμεσα σε C (συνέπεια) και A (διαθεσιμότητα) *κατά τη διάρκεια μιας διαμέρισης*.
    *   **CP Συστήματα:** Επιλέγουν Consistency και Partition Tolerance. Όταν συμβεί διαμέριση, για να εγγυηθούν συνέπεια, *γίνονται μη-διαθέσιμα* στους client που δεν μπορούν να επικοινωνήσουν με τον κύριο κόμβο (Master). (Παραδοσιακά RDBMS με αυστηρή Consistency πέφτουν σε αυτή την κατηγορία όταν κατανέμονται).
    *   **AP Συστήματα:** Επιλέγουν Availability και Partition Tolerance. Όταν συμβεί διαμέριση, παραμένουν *διαθέσιμα* σε όλους τους client, επιτρέποντας ίσως αναγνώσεις "παλιών" (stale) δεδομένων στους client που δεν μπορούν να επικοινωνήσουν με τον κόμβο που έχει την τελευταία ενημέρωση. Μετά την αποκατάσταση της διαμέρισης, τα δεδομένα συγχρονίζονται.
    *   **Σημαντικό:** Το CA είναι κυρίως θεωρητικό για πραγματικά κατανεμημένα συστήματα, καθώς η Partition Tolerance θεωρείται αναπόφευκτη.
*   **Εν τέλει Συνέπεια (Eventual Consistency):**
    *   Η βάση πίσω από πολλά AP NoSQL συστήματα.
    *   Πιο "χαλαρή" μορφή συνέπειας σε σχέση με την άμεση συνέπεια των ACID.
    *   **BASE Properties:** **B**asically **A**vailable (διαθέσιμο παρόλο που μπορεί να υπάρχουν βλάβες), **S**oft **S**tate (το state του συστήματος μπορεί να αλλάζει με την πάροδο του χρόνου ακόμη και χωρίς input, καθώς τα δεδομένα διαδίδονται), **E**ventually Consistent (οι κόμβοι τελικά θα συγχρονιστούν και θα αποκτήσουν την ίδια, τελευταία τιμή *αν σταματήσουν οι ενημερώσεις*).
    *   Υπάρχει **απεριόριστη καθυστέρηση** στη διάδοση των αλλαγών στα αντίγραφα, που μπορεί να οδηγήσει σε **αναγνώσεις παλιών (stale) δεδομένων**.
    *   Η εφαρμογή **πρέπει να χειριστεί** την πιθανότητα αντικρουόμενων (conflicting) ενημερώσεων ή αναγνώσεων μη-ενημερωμένων δεδομένων.
    *   Προσέγγιση "Best effort", "περίπου σωστές" απαντήσεις είναι αποδεκτές σε κάποια σενάρια.
    *   **Tunable Consistency:** Σε συστήματα όπως το Dynamo, μπορεί κανείς να ρυθμίσει το επίπεδο συνέπειας, ορίζοντας πόσα αντίγραφα (W για write, R για read) πρέπει να επιβεβαιώσουν μία ενέργεια από τα συνολικά N αντίγραφα. Ρυθμίσεις όπως W=N (πολύ αργό write, αυστηρή συνέπεια) ή R=1 (ταχεία read, πιθανή ανάγνωση παλιών δεδομένων) καθορίζουν τη συμπεριφορά. Συνήθως: W+R > N για να εξασφαλίζεται κάποιο επίπεδο overlap (quorum).
    *   **Amazon Dynamo:** Ένα χαρακτηριστικό παράδειγμα AP συστήματος με Eventual Consistency και Tunable Consistency.

## **7. Χαρακτηριστικά και Σύγκριση NoSQL με RDBMS (Διαφάνειες 61-63)**

*   **Διακριτά Χαρακτηριστικά NoSQL:**
    *   Μεγάλος όγκος δεδομένων (Big Data).
    *   Κλιμακούμενη αναπαραγωγή και κατανομή (Scalable replication and distribution) σε χιλιάδες μηχανές.
    *   Σχεδιασμένα για γρήγορες απαντήσεις σε συγκεκριμένα ερωτήματα.
    *   Προσανατολισμός σε αναγνώσεις (read-heavy workloads) και λιγότερο σε συχνές, πολύπλοκες ενημερώσεις.
    *   Ασύγχρονες εισαγωγές και ενημερώσεις.
    *   **Schema-less** (ή Schema-on-Read / Dynamic Schema).
    *   Οι ACID ιδιότητες *δεν* είναι απαραίτητες – προτίμηση στο **BASE**.
    *   Εφαρμόζουν το **CAP Theorem**, συχνά ως AP.
    *   Ανοιχτού κώδικα ανάπτυξη (Open source development).
*   **RDBMS vs NoSQL - Σύνοψη:**
    | Χαρακτηριστικό          | RDBMS                                    | NoSQL                                                                 |
    | :---------------------- | :--------------------------------------- | :-------------------------------------------------------------------- |
    | Μοντέλο Δεδομένων     | Σχεσιακό, Στοιχείο (table, row, column) | Key-Value, Document, Column-Family, Graph                             |
    | Γλώσσα Ερωτημάτων     | SQL (δηλωτική)                          | Συνήθως API/γλώσσες συγκεκριμένες του συστήματος (όχι πάντα δηλωτικές) |
    | Σχήμα Δεδομένων       | Προκαθορισμένο (schema-on-write)        | Χωρίς προκαθορισμένο (schema-less / schema-on-read)                   |
    | Ακεραιότητα Συναλλαγής | ACID (Atomic, Consistent, Isolated, Durable) | Συχνά BASE (Basically Available, Soft State, Eventually Consistent)   |
    | Τύπος Δεδομένων       | Συνήθως δομημένα                         | Δομημένα, ημιδομημένα, αδόμητα, απρόβλεπτα                              |
    | Θεώρημα                | CAP Theorem (CP / CA)                   | CAP Theorem (Συνήθως AP)                                              |
    | Προτεραιότητα          | Ακεραιότητα, Συνοχή                       | Υψηλή επίδοση, υψηλή διαθεσιμότητα, κλιμακούμενοτητα                 |
*   **Πλεονεκτήματα NoSQL:**
    *   Υψηλή κλιμακούμενοτητα (horizontal).
    *   Σχεδιασμός για κατανεμημένα συστήματα.
    *   Χαμηλότερο κόστος υλοποίησης (συχνά open source, hardware commodity).
    *   Ευελιξία σχήματος (ιδανικό για evolving data).
    *   Δεν απαιτούνται σύνθετες σχέσεις/joins (συχνά μοντελοποίηση με aggregation/denormalization).
*   **Μειονεκτήματα NoSQL:**
    *   Έλλειψη τυποποίησης (πολλά διαφορετικά συστήματα, διαφορετικά API).
    *   Περιορισμένες δυνατότητες ερωτημάτων (π.χ. όχι εύκολα joins, σύνθετα aggregates).
    *   Η εν τέλει συνέπεια (eventual consistency) μπορεί να είναι δύσκολη στο προγραμματισμό και τη διαχείριση (η εφαρμογή πρέπει να χειριστεί πιθανές ανακρίβειες).

## **8. Τύποι Βάσεων Δεδομένων NoSQL (Διαφάνειες 64-75)**

*   **Key-Value Stores:**
    *   Ο πιο βασικός τύπος NoSQL.
    *   Αποθήκευση δεδομένων ως ένα "λεξικό" ή hash table (μοναδικό κλειδί οδηγεί σε μια τιμή).
    *   Ανεξαρτησία σχήματος (η τιμή μπορεί να είναι οτιδήποτε: string, JSON, BLOB).
    *   Πολύ γρήγορη πρόσβαση (SET, GET, DEL) βάσει κλειδιού.
    *   Ιδανικό για απλές αναζητήσεις, sessions, shopping carts.
    *   Συνήθως ακολουθούν A+P του CAP.
    *   Παραδείγματα: Redis, Dynamo, Riak.
*   **Column-Oriented (or Column-Family) Databases:**
    *   Οργανώνουν δεδομένα κατά στήλες αντί κατά γραμμές (όπως τα RDBMS).
    *   Οι τιμές μιας στήλης αποθηκεύονται συνεχόμενα στο δίσκο.
    *   Ιδανικό για αναλυτικά/aggregation queries (π.χ. SUM, AVG) καθώς χρειάζεται να διαβαστούν μόνο οι σχετικές στήλες.
    *   Καλή συμπίεση δεδομένων (οι τιμές μιας στήλης είναι συχνά του ίδιου τύπου).
    *   Χρησιμοποιούνται σε Data Warehousing, Business Intelligence.
    *   Παραδείγματα: BigTable, Cassandra, HBase.
*   **Graph Databases:**
    *   Μοντελοποιούν δεδομένα ως κόμβους (entities) και ακμές (relationships) με ιδιότητες.
    *   Οι ακμές αναπαριστούν τις "συνδέσεις" μεταξύ κόμβων και είναι συνήθως κατευθυνόμενες, ονοματισμένες και σημασιολογικά σχετικές.
    *   Βελτιστοποιημένες για την αποτελεσματική διάσχιση σύνθετων σχέσεων (traversing relationships).
    *   Ιδανικές για social networks, recommendation engines, knowledge graphs, fraud detection.
    *   Παράδειγμα: Neo4j.
*   **Document Oriented Databases:**
    *   Αποθηκεύουν δεδομένα σε μορφή "εγγράφων" (documents).
    *   Ένα έγγραφο είναι μια συλλογή key-value ζευγών, συχνά σε format όπως JSON, BSON, ή XML.
    *   **Δεν επιβάλλουν σχήμα** – τα έγγραφα στην ίδια συλλογή μπορούν να έχουν διαφορετική δομή (ευέλικτα!).
    *   Υποστηρίζουν φωλιασμένες (nested) δομές εντός των εγγράφων, μειώνοντας την ανάγκη για joins.
    *   Ομαδοποιούν έγγραφα σε "συλλογές" (collections), αντί για "πίνακες".
    *   Πολύ καλά για εφαρμογές που το μοντέλο ταιριάζει σε aggregate roots (π.χ. ένα προϊόν με όλες τις αξιολογήσεις του embedded).
    *   Παραδείγματα: MongoDB, CouchDB.

## **9. Μοντελοποίηση NoSQL και Τεχνικές (Διαφάνειες 76-80)**

*   Η προσέγγιση μοντελοποίησης NoSQL ξεκινά από τα **ερωτήματα της εφαρμογής (application-specific queries)**, όχι από τη δομή των δεδομένων.
    *   RDBMS: "What answers do I have?" (ποια δεδομένα έχω και πώς είναι δομημένα;)
    *   NoSQL: "What questions do I have?" (ποια ερωτήματα πρέπει να εξυπηρετήσω γρήγορα;). Το μοντέλο σχεδιάζεται για να βελτιστοποιήσει τα συγκεκριμένα access patterns.
*   **Denormalization (Απο-κανονικοποίηση):**
    *   Αντί να αφαιρέσουμε την επανάληψη δεδομένων όπως στην κανονικοποίηση των RDBMS, στο NoSQL συχνά *αντιγράφουμε* τα ίδια δεδομένα σε πολλαπλά έγγραφα/εγγραφές.
    *   **Στόχος:** Απλοποίηση/βελτιστοποίηση της επεξεργασίας ερωτημάτων, προσαρμογή στο data model.
    *   **Trade-off:** Αύξηση όγκου δεδομένων και πολυπλοκότητας ενημερώσεων έναντι μείωσης του όγκου δεδομένων/IO και πολυπλοκότητας για συγκεκριμένα ερωτήματα.
*   **Aggregation (Συσσωμάτωση):**
    *   Ενσωμάτωση σχετιζόμενων "εν οις καινοποιούνται" δεδομένων (π.χ. ενός-προς-πολλά σχέσεις) σε ένα μοναδικό entity/έγγραφο μέσω φωλιασμένων δομών (nested entities).
    *   **Στόχος:** Ελαχιστοποίηση της ανάγκης για joins κατά την προσπέλαση.
    *   Επιτρέπει ευέλικτο "soft schema".
*   **Application Side Joins:**
    *   Τα joins, αντί να γίνονται αυτόματα από το DBMS query engine (όπως στην SQL), υλοποιούνται *στον κώδικα της εφαρμογής* κατά το design time ή το query execution time.
    *   Απαιτεί ο προγραμματιστής να γράψει τη λογική για τη σύνδεση δεδομένων από διαφορετικά μέρη (π.χ. παίρνει ID από ένα έγγραφο και κάνει lookup σε άλλο).

## **10. Συμπεράσματα (Διαφάνεια 81)**

*   Δεν υπάρχει "τέλεια" λύση NoSQL που να ταιριάζει παντού.
*   Κάθε σύστημα (RDBMS ή συγκεκριμένο NoSQL) έχει τα πλεονεκτήματα και μειονεκτήματά του.
*   Η επιλογή του κατάλληλου συστήματος εξαρτάται άμεσα από τις **ανάγκες της εφαρμογής, τους στόχους και τα χαρακτηριστικά των δεδομένων**.
*   Τα NoSQL συστήματα είναι ιδιαίτερα κατάλληλα για:
    *   Διαχείριση Big Data.
    *   Αραιά, αδόμητα και ημιδομημένα δεδομένα.
    *   Απαιτήσεις υψηλής οριζόντιας κλιμακούμενοτητας (high horizontal scalability).
    *   Εφαρμογές που απαιτούν μαζικές παράλληλες υπολογιστικές διεργασίες.
*   Διαφορετικοί τύποι NoSQL (Key-Value, Document, Column-Oriented, Graph) εξυπηρετούν διαφορετικά access patterns και μοντέλα δεδομένων.

**Σημείο για Σκέψη:** Πότε θα επιλέγατε ένα NoSQL έναντι ενός RDBMS; Τι στοιχεία της εφαρμογής θα σας οδηγούσαν σε αυτή την απόφαση; Ποιους συμβιβασμούς θα έπρεπε να διαχειριστείτε;
