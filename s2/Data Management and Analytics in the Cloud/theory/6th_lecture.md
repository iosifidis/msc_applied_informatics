# 🗂 **Βάσεις Δεδομένων Εγγράφων (Document Databases) - Με έμφαση στο MongoDB**

## **1. Βάσεις Δεδομένων Εγγράφων (Document Databases) (Διαφάνειες 2, 5)**

- **Τι είναι Document Databases;** Είναι βάσεις δεδομένων που αποθηκεύουν δεδομένα ως **δομημένα έγγραφα (structured documents)**.
  - Βασικά Format Εγγράφων: **XML** και **JSON**.
- **JSON (JavaScript Object Notation):**
  - Είναι ένα lightweight format ανταλλαγής δεδομένων.
  - Εύκολο στην ανάγνωση/γραφή από ανθρώπους και στην ανάλυση/παραγωγή από μηχανές.
  - Βασίζεται σε ένα υποσύνολο της JavaScript.
  - Είναι **γλώσσα-ανεξάρτητο** format κειμένου, χρησιμοποιώντας δομές γνωστές σε πολλές γλώσσες προγραμματισμού (όπως C, C++, Java, Python κλπ.) (Διαφάνεια 3).
  - **Δομές JSON:**
    - Συλλογή ζευγών ονόματος/τιμής (name/value pairs) -> Αντιστοιχεί σε `objects`, `records`, `structs`, `dictionaries`, `hash tables`, `keyed lists`, `associative arrays` σε διάφορες γλώσσες.
    - Ταξινομημένη λίστα τιμών -> Αντιστοιχεί σε `arrays`, `vectors`, `lists`, `sequences`.
  - **JSON vs XML:** Και τα δύο είναι format για δομημένα δεδομένα. Το JSON είναι γενικά πιο "ελαφρύ", πιο εύκολο για parse/generate σε εφαρμογές και έχει γίνει ιδιαίτερα δημοφιλές για web-based workloads και APIs (Διαφάνεια 4).
- **Χαρακτηριστικά Document Databases:**
  - **Μονάδα Αποθήκευσης:** Τα δεδομένα αποθηκεύονται **μέσα σε έγγραφα (inside documents)**. Ένα **Έγγραφο (Document)** είναι η βασική μονάδα αποθήκευσης (`basic unit of storage`).
  - **Πρόσβαση:** Ένα έγγραφο είναι ουσιαστικά μια συλλογή `key-value pairs` (αντίστοιχη με ένα object ή ένα dictionary), όπου το **κλειδί του εγγράφου** επιτρέπει την πρόσβαση στην τιμή του (που είναι ολόκληρο το έγγραφο).
  - **Ευελιξία Σχήματος:** Τα έγγραφα **δεν αναγκάζονται τυπικά να έχουν ένα σταθερό σχήμα (not typically forced to have a schema)**. Έγγραφα μέσα στην ίδια "συλλογή" μπορούν να έχουν διαφορετικά πεδία ή διαφορετικές δομές για τα ίδια πεδία. Αυτό τα καθιστά **ευέλικτα και εύκολα να αλλάξουν**.
  - **Ομαδοποίηση:** Τα έγγραφα αποθηκεύονται σε **συλλογές (collections)** προκειμένου να ομαδοποιούνται διαφορετικά είδη δεδομένων. Αυτό είναι παρόμοιο με την έννοια του "πίνακα" (table) σε RDBMS.
  - Τα έγγραφα μπορούν να περιέχουν πολλά διαφορετικά ζεύγη κλειδιού-τιμής, key-array ζεύγη, ή ακόμη και **εμφωλευμένα (nested) έγγραφα/δομές**.
  - Παραδείγματα Document databases: MongoDB, CouchDB.

## **2. RDBMS vs Document DB - Σύγκριση Μοντέλων (Διαφάνεια 6)**

- **Σχεσιακό Μοντέλο:**
  - **Tables (Πίνακες):** Δομή για ομαδοποίηση γραμμών.
  - **Rows (Γραμμές):** Μια εγγραφή δεδομένων.
  - **Columns (Στήλες):** Τα πεδία με σταθερό σχήμα.
  - **Joins (Συζεύξεις):** Μηχανισμός σύνδεσης δεδομένων από διαφορετικούς πίνακες βάσει κοινών τιμών.
- **Document Model:**
  - **Collections (Συλλογές):** Δομή για ομαδοποίηση εγγράφων (παρόμοιο με Tables).
  - **Documents (Έγγραφα):** Η μονάδα αποθήκευσης δεδομένων (παρόμοιο με Rows).
  - **Key/value pairs (Ζεύγη Κλειδιού/Τιμής):** Τα πεδία μέσα στο έγγραφο. Τα πεδία **δεν έχουν σταθερό σχήμα** μεταξύ διαφορετικών εγγράφων στην ίδια συλλογή (παρόμοιο με Columns αλλά ευέλικτο).
  - **Joins (Συζεύξεις):** **Δεν είναι διαθέσιμες** ως εγγενής, αυτόματη λειτουργία μεταξύ εγγράφων (όπως στα RDBMS). Οι "σχέσεις" μοντελοποιούνται με διαφορετικούς τρόπους (embedding, linking) και τα joins γίνονται από την εφαρμογή ή μέσω aggregation pipelines.

## **3. Μοντελοποίηση σε Document Databases (Διαφάνειες 7-11)**

- Η μοντελοποίηση σε Document Databases (και γενικά σε NoSQL) συχνά ξεκινάει με κίνητρο τα **ερωτήματα της εφαρμογής (application queries)** (Διαφάνεια 7).
  - RDBMS Modeling: "Πώς είναι τα δεδομένα μου;" (Πώς να δομήσω δεδομένα σε πίνακες)
  - NoSQL Modeling: "Πώς είναι τα ερωτήματά μου;" (Πώς να δομήσω τα δεδομένα ώστε τα συχνά ερωτήματα να είναι αποδοτικά)
- **Dominant Techniques:**
  - **Data Duplication (Επανάληψη Δεδομένων):** Αντί να αφαιρείς επανάληψη όπως στην κανονικοποίηση RDBMS, στο Document Model συχνά **αντιγράφεις τα ίδια δεδομένα** σε πολλαπλά έγγραφα για να απλοποιήσεις/βελτιστοποιήσεις συγκεκριμένα ερωτήματα (Διαφάνεια 7, 8 - Denormalization). Trade-off: αυξάνεις χώρο και πολυπλοκότητα ενημέρωσης, αλλά μειώνεις το κόστος ερωτημάτων (ιδιαίτερα Io).
  - **Denormalization (Απο-κανονικοποίηση):** Η τεχνική της επανάληψης δεδομένων σε πολλαπλά έγγραφα/πίνακες (στο RDBMS πλαίσιο) για απλοποίηση/βελτιστοποίηση της επεξεργασίας ερωτημάτων. Στο Document Model, αυτό συχνά επιτυγχάνεται μέσω της **Aggregation/Embedding**. (Διαφάνεια 8)
  - **Aggregation / Embedding (Συσσωμάτωση / Ενσωμάτωση) (Διαφάνεια 9, 10):**
    - Επιτρέπει τη δημιουργία κλάσεων οντοτήτων (entities) με **πολύπλοκες εσωτερικές δομές**, ενσωματώνοντας (embedding) συσχετισμένα δεδομένα απευθείας μέσα στο κυρίως έγγραφο (όπως φωλιασμένα έγγραφα - nested entities/document embedding).
    - **Σημαντικό:** Μειώνει τις σχέσεις ενός-προς-πολλά σε επίπεδο εγγράφου και **ελαχιστοποιεί την ανάγκη για joins**. Αν π.χ. ένα βιβλίο έχει πολλές κριτικές, οι κριτικές μπορούν να είναι εμφωλευμένες μέσα στο έγγραφο του βιβλίου.
    - Μπορεί να καλύψει διαφορές στη δομή διαφορετικών οντοτήτων χρησιμοποιώντας την ίδια συλλογή (flex schema).
- **Application Side Joins (Διαφάνεια 11):** Δεδομένου ότι δεν υπάρχουν αυτόματα joins μεταξύ εγγράφων, οι συνδέσεις δεδομένων ("joins") πρέπει να υλοποιούνται στον **κώδικα της εφαρμογής** (join by design) ή να γίνονται μέσω των μηχανισμών aggregation pipeline (join by query) - κάτι που δεν είναι τόσο ισχυρό ή γενικό όσο τα SQL joins.

## **4. Σχεσιακό σε JSON Παράδειγμα (Διαφάνεια 12-14)**

- **Σχεσιακό:** Πίνακες `Actors` και `FilmActors` (join table) και `Films`.
- **Μετατροπή σε JSON Document:** Ένα `Film` αναπαρίσταται ως ένα JSON document. Οι ηθοποιοί που πρωταγωνιστούν σε αυτό το φιλμ, αντί να συνδέονται μέσω ενός join table, **ενσωματώνονται** ως πίνακας (`array`) εμφωλευμένων αντικειμένων (`objects`) με τα στοιχεία τους (όνομα, id ηθοποιού) **μέσα** στο document του φιλμ. (Διαφάνεια 12 - Relational to JSON).
- **Document Embedding:** Η ενσωμάτωση των πληροφοριών των ηθοποιών απευθείας στο document του φιλμ (Διαφάνεια 13).
- **Document Linking:** Εναλλακτική προσέγγιση (αντί για embedding) είναι το **linking** (παρόμοιο με Foreign Keys σε RDBMS). Αντί να ενσωματώσεις όλα τα στοιχεία των ηθοποιών, αποθηκεύεις απλώς μια **λίστα από IDs ηθοποιών** μέσα στο document του φιλμ. Για να βρεις τα στοιχεία ενός ηθοποιού, πρέπει να κάνεις ξεχωριστή αναζήτηση στη συλλογή `Actors` χρησιμοποιώντας το ID. Αυτό απαιτεί _application side join_. Χρήσιμο όταν οι εμφωλευμένες οντότητες (π.χ. ηθοποιοί) είναι **μεγάλες** ή **αναφέρονται από πολλά διαφορετικά έγγραφα** (π.χ. ένας ηθοποιός πρωταγωνιστεί σε πολλά φιλμ). (Διαφάνεια 14)

## **5. MongoDB - Γενικά (Διαφάνειες 1, 15-20)**

- **MongoDB:** Ένα από τα πιο δημοφιλή open-source Document Databases. Αναπτύχθηκε από την 10gen (τώρα MongoDB Inc.) το 2007 (Διαφάνεια 18).
- Το όνομά του προέρχεται από το "humongous" (τεράστιο) για να τονίσει την ικανότητά του να χειρίζεται μεγάλα σύνολα δεδομένων (Διαφάνεια 16).
- **DB-Engines Ranking:** Συχνά κατατάσσεται πολύ ψηλά στα δημοφιλέστερα ΣΔΒΔ, ιδιαίτερα στα Document Databases (Διαφάνεια 17).
- **Χαρακτηριστικά MongoDB (Διαφάνεια 18):**
  - Open Source Document-oriented, NoSQL database.
  - Βασίζεται σε hashing (για διαμερισμό), **schema-less** (με την έννοια ότι δεν απαιτεί αυστηρό σχήμα στο definition της συλλογής).
  - **Δεν έχει γλώσσα ορισμού δεδομένων (Data Definition Language - DDL)** με την ίδια έννοια των RDBMS (π.χ. `CREATE TABLE ... (schema)`). Το σχήμα ορίζεται έμμεσα από τη δομή των εγγράφων που εισάγεις.
  - Αποθηκεύει hashes με κλειδιά και τιμές (εσωτερική υλοποίηση).
  - Χρησιμοποιεί αυτόματα παραγόμενα αναγνωριστικά για κάθε document (\_id), το οποίο λειτουργεί ως **primary key** (μοναδικό σε κάθε συλλογή).
  - Χρησιμοποιεί format **BSON** (Binary JSON) για την αποθήκευση, που είναι μια δυαδική αναπαράσταση του JSON (αποτελεσματικότερο σε μέγεθος και parse/generate από το JSON text).
  - Γράφτηκε σε C++. Παρέχει API σε πολλές γλώσσες.
- **Γιατί MongoDB; (Διαφάνεια 19):**
  - Απλά ερωτήματα (`simple queries`): Η γλώσσα ερωτημάτων του, βασισμένη σε JSON objects, είναι συχνά πιο άμεση για απλές αναζητήσεις από την SQL για προγραμματιστές που εργάζονται με JSON στην εφαρμογή.
  - Κατάλληλο για τις **περισσότερες web εφαρμογές** (ιδιαίτερα αυτές που χειρίζονται δεδομένα σε JSON format).
  - Ευκολότερη και ταχύτερη **ενσωμάτωση δεδομένων** (easy and faster integration of data) με την εφαρμογή (μείωση impedance mismatch).
  - **Δεν είναι κατάλληλο** για συστήματα με **πολύ βαριές και πολύπλοκες συναλλαγές** (ACID Properties - αν και έχει γίνει καλύτερο σε νεότερες εκδόσεις) ή για εργασίες που απαιτούν πολύπλοκα joins.
- **Βασικές Έννοιες MongoDB (Διαφάνεια 20):**
  - **Instance:** Μία εγκατάσταση MongoDB.
  - **Databases:** Ένα MongoDB instance μπορεί να έχει μηδέν ή περισσότερες βάσεις (υψηλό επίπεδο container, παρόμοιο με relational dbs).
  - **Collections:** Μία βάση μπορεί να έχει μηδέν ή περισσότερες συλλογές (παρόμοιο με relational tables).
  - **Documents:** Μία συλλογή έχει μηδέν ή περισσότερα έγγραφα (παρόμοιο με relational rows).
  - **Fields:** Ένα document έχει ένα ή περισσότερα πεδία (ζεύγη key/value), παρόμοια με γνωρίσματα/στήλες. **Κάθε document σε μια συλλογή μπορεί να έχει το δικό του, μοναδικό σύνολο πεδίων.**
  - **Indexes:** Παρόμοια με τα ευρετήρια των RDBMS. Αυτόματα δημιουργείται ευρετήριο στο \_id. Μπορούν να οριστούν από το χρήστη για επιδόσεις, μοναδικότητα κλπ. (Διαφάνεια 22)
  - **Cursor:** Όταν εκτελείται ένα ερώτημα αναζήτησης (`find`), το MongoDB επιστρέφει έναν κέρσορα. Αυτός είναι ένας δείκτης (pointer) στο σύνολο αποτελεσμάτων. Μπορείς να κάνεις ενέργειες στον κέρσορα (π.χ. count, skip, limit, sort) πριν "τραβήξεις" τα πραγματικά δεδομένα.

## **6. MongoDB Document - Παράδειγμα (Διαφάνεια 21)**

- Ένα document στο MongoDB είναι ένα BSON (ουσιαστικά JSON) αντικείμενο.
- Παράδειγμα ενός απλού document για κάποιον "John":
  ```json
  {
    "name": "John",
    "age": 20,
    "phone": "777",
    "address": "J-street"
  }
  ```
- Αυτό είναι μια αυτοτελής μονάδα δεδομένων, παρόμοια με μια γραμμή σε RDBMS αλλά με την ευελιξία ενός object/dictionary.

## **7. Αρχιτεκτονική MongoDB (Διαφάνεια 23-26)**

Το MongoDB έχει μια κατανεμημένη (distributed) αρχιτεκτονική για να υποστηρίξει επεκτασιμότητα (scalability).

- **Βασικοί Διεργασίες (Processes) (Διαφάνεια 24):**
  - **Mongod:** Η κύρια διεργασία της βάσης δεδομένων (database instance). Διαχειρίζεται τα δεδομένα και τις λειτουργίες σε έναν server.
  - **Mongos:** Οι διεργασίες "router" ή "sharding processes". Χειρίζονται τα αιτήματα των clients, καθορίζουν σε ποιους **mongods** (shards) θα στείλουν το ερώτημα, συγκεντρώνουν τα αποτελέσματα και τα επιστρέφουν στον client.
  - **Mongo:** Ο διαδραστικός shell (περιβάλλον Javascript) για διαχείριση και ερωτήματα στη βάση.
- **Sharding (Διαφάνεια 25):**
  - Ο μηχανισμός για την οριζόντια επεκτασιμότητα (horizontal scalability), διανέμοντας τα δεδομένα (documents) σε πολλαπλές διεργασίες **mongod** (shards).
  - Χρειάζεται ένα **shard key**: ένα ή περισσότερα πεδία στο document που χρησιμοποιούνται από τους `mongos` για να καθορίσουν σε ποιο shard ανήκει ένα document.
  - Ο διαμερισμός μπορεί να βασίζεται σε **range** (εύρος τιμών του shard key) ή **hash** (συνάρτηση κατακερματισμού στο shard key).
  - Το **Cluster balancing** είναι μια διεργασία που διασφαλίζει την ομοιόμορφη κατανομή των δεδομένων (ως chunks) στα shards. Τα **chunks** είναι μονάδες δεδομένων (περίπου 64MB).
- **Replication (Διαφάνεια 26):**
  - Οι διεργασίες `mongod` οργανώνονται σε **Replica sets** για διαθεσιμότητα (High Availability).
  - Ένα replica set έχει έναν **primary node** και έναν ή περισσότερους **secondary nodes**.
  - Ο primary node δέχεται όλες τις **εγγραφές (writes)** και τις διαδίδει **ασύγχρονα (asynchronously)** στους secondary nodes.
  - Υπάρχει ένας μηχανισμός επιλογής του primary node (συχνά μέσω ψηφοφορίας).
  - Ο primary διατηρεί ένα **oplog** (operations log) που καταγράφει τις αλλαγές. Οι secondaries διαβάζουν από το oplog του primary.
  - Χρησιμοποιούνται μηνύματα heartbeat για επικοινωνία μεταξύ των μελών του replica set.
- **Write/Read Concerns και Consistency (Διαφάνεια 27):**
  - **Write Concern:** Καθορίζει το επίπεδο εγγύησης για μια ενέργεια εγγραφής (write). Π.χ., πόσοι κόμβοι (primary + N secondaries) πρέπει να επιβεβαιώσουν την εγγραφή πριν η εντολή επιστρέψει στον client.
  - **Read Preference:** Καθορίζει από ποιους κόμβους (primary ή secondaries) θα γίνονται οι αναγνώσεις (reads). **Προεπιλεγμένα** οι αναγνώσεις κατευθύνονται στον **primary** (αυστηρή συνέπεια - **strictly consistent** by default for reads). Μπορείς να τις κατευθύνεις σε secondaries για scaling reads (αλλά μπορείς να πάρεις stale data).
  - Το MongoDB, στις προεπιλεγμένες ρυθμίσεις για Reads και Writes, προσπαθεί να είναι **Strictly Consistent** (αντί για Eventually Consistent όπως άλλα NoSQL). Αυτό επιτυγχάνεται με τη χρήση **document-level locking** - ένα write κλειδώνει ένα document.

## **8. Λειτουργίες CRUD και Ερωτήματα (Διαφάνειες 28-35)**

- **Εγκατάσταση και Shell (Διαφάνειες 28-29):** Οδηγίες για εγκατάσταση και βασικές εντολές στον Mongo Shell (π.χ. `show dbs`, `use dbname`, `show collections`).
- **CRUD Operations (Διαφάνεια 30-32):** Οι βασικές λειτουργίες που είδαμε νωρίτερα υλοποιούνται μέσω μεθόδων στις συλλογές:
  - `insertOne()`, `insertMany()`: Δημιουργία document(s). Αν παραλείψεις το `_id`, το MongoDB παράγει ένα μοναδικό.
  - `updateOne()`, `updateMany()`: Ενημέρωση document(s). Μπορεί να κάνει `upsert` (insert if not exists) με option.
  - `deleteOne()`, `deleteMany()`, `drop()`: Διαγραφή document(s) ή ολόκληρης συλλογής.
  - `find()`, `findOne()`: Ανάκτηση document(s).
    - `find()`: Βρίσκει documents βάσει κριτηρίων φιλτραρίσματος (`query`, παρόμοιο με `WHERE` στην SQL, αλλά ως JSON object). Μπορείς να ορίσεις ποια πεδία (`projection`) να επιστραφούν. Επιστρέφει `cursor` για αποτελέσματα. Υποστηρίζει `ordering` (`sort`), `skip`, `limit`.
    - `findOne()`: Επιστρέφει το _πρώτο_ document που ταιριάζει στο query.
- **Comparison & Logical Operators (Διαφάνειες 33-34):** Το MongoDB παρέχει ένα πλούσιο σύνολο τελεστών για την έκφραση κριτηρίων αναζήτησης στα queries (π.χ. `$eq`, `$gt`, `$gte`, `$in`, `$and`, `$or`).
- **Querying Examples (Διαφάνειες 35-45):** Παραδείγματα queries σε MongoDB (unicorn dataset) που δείχνουν πώς να αναζητάς, φιλτράρεις, κάνεις count, sort, skip/limit, και ενημερώνεις documents χρησιμοποιώντας τη σύνταξη MongoDB query.
  - Αναζήτηση βάσει πεδίων και συνθηκών (`find({gender: 'm', weight: {$gt: 700}})` - Διαφάνεια 38).
  - Αναζήτηση απουσίας πεδίου (`find({vampires: {$exists: false}})` - Διαφάνεια 39).
  - Αναζήτηση με `$in` (μέσα σε λίστα) (`find({loves: {$in: ['apple', 'orange']}})` - Διαφάνεια 40).
  - Αναζήτηση με `$or` (Διαφάνεια 41).
  - Projection: Επιλογή πεδίων για εμφάνιση (Διαφάνεια 42). Το `_id` εμφανίζεται πάντα εκτός αν το αποκλείσεις ρητά.
  - Sorting (`sort({weight: -1})` - Διαφάνεια 43).
  - Skip και Limit (`limit(2).skip(1)` - Διαφάνεια 44).
  - Counting (`countDocuments()` - Διαφάνεια 45).
- **Updating Examples (Διαφάνεια 46-49):** Παραδείγματα ενημερώσεων.
  - Αντικατάσταση ολόκληρου document (Διαφάνεια 46 - `updateOne` χωρίς `$set` ή άλλους modifiers).
  - Ενημέρωση συγκεκριμένων πεδίων (`updateOne` με `$set`) (Διαφάνεια 46).
  - Ατομικές ενημερώσεις σε πεδία (`$inc` για αύξηση/μείωση αριθμού, `$push` για προσθήκη στοιχείου σε array) (Διαφάνεια 47-48).
  - `upsert` (insert if not exists) με `$inc` (Διαφάνεια 48).
  - Ενημέρωση πολλών document (`updateMany`) (Διαφάνεια 49).
- **Aggregation (Διαφάνεια 50-56):**
  - Το MongoDB παρέχει ένα ισχυρό **Aggregation Pipeline**, που επιτρέπει SQL-like λειτουργίες aggregate σε documents (Διαφάνεια 50).
  - Documents περνούν μέσα από μια σειρά σταδίων (`stages`) σε ένα pipeline (`aggregate([ {stage1}, {stage2}, ... }])`).
  - Στάδια: `$group` (ομαδοποίηση documents, παρόμοιο με SQL GROUP BY), `$match` (φιλτράρισμα documents, παρόμοιο με SQL WHERE και HAVING), `$project` (αναμόρφωση documents), `$sort`, `$limit`, `$skip` κλπ.
  - Παραδείγματα aggregate queries: `count(*)` (Διαφάνεια 50), `distinct(name)` (Διαφάνεια 51), σύνθετο query με `$group` και `$match` (Διαφάνεια 52).
  - Άλλα παραδείγματα aggregate queries στο dataset των unicorns (Διαφάνειες 53-56).

## **9. Περιορισμοί του MongoDB (Διαφάνεια 58)**

- **No Referential Integrity (Όχι Αναφορική Ακεραιότητα):** Δεν έχει μηχανισμό Foreign Keys ή constraints που να εγγυώνται ότι μια "σύνδεση" (link) μεταξύ εγγράφων (μέσω IDs) είναι έγκυρη. Η ακεραιότητα πρέπει να διατηρηθεί στην εφαρμογή.
- **Denormalization Incurs Significant Update Costs:** Εάν επιλέξεις να κάνεις **embedding/denormalization** (π.χ. να ενσωματώσεις δεδομένα ηθοποιών μέσα σε κάθε φιλμ), και κάποιο από αυτά τα **ενσωματωμένα δεδομένα** πρέπει να ενημερωθεί **συχνά** (π.χ. αλλαγή ονόματος ηθοποιού), πρέπει να ενημερώσεις αυτό το πεδίο σε **όλα** τα έγγραφα φιλμ που περιέχουν τον ηθοποιό. Αυτό μπορεί να είναι πολύ ακριβό σε writes. (Εξηγεί γιατί το **Linking** μπορεί να είναι καλύτερο για οντότητες που ενημερώνονται συχνά ή μοιράζονται από πολλά έγγραφα).
- **Not Required Schema, but...:** Ενώ το σχήμα **δεν είναι υποχρεωτικό** σε επίπεδο βάσης, οι **εφαρμογές** απαιτούν _κάποιο_ σχήμα για να μπορούν να εργαστούν με τα δεδομένα. Η ευελιξία του σχήματος, αν δεν διαχειριστεί προσεκτικά, μπορεί να οδηγήσει σε **υψηλή αναντιστοιχία/ασυνέπεια** μεταξύ εγγράφων στην ίδια συλλογή, κάνοντας τον κώδικα της εφαρμογής πιο πολύπλοκο.

## **Σύνδεση με το μάθημα:**

Το MongoDB, ως κορυφαίο παράδειγμα Document Database, είναι εξαιρετικά σημαντικό στο Υπολογιστικό Νέφος για τις Web-scale εφαρμογές. Η ευελιξία του στο σχήμα, η εύκολη αντιστοίχιση με Objects/JSON στην εφαρμογή, και η ενσωματωμένη υποστήριξη Sharding και Replication το καθιστούν ιδανική επιλογή για πολλές σύγχρονες ανάγκες. Ωστόσο, η κατανόηση των διαφορών του με τα RDBMS (έλλειψη joins/αναφορικής ακεραιότητας, διαφορετική προσέγγιση στη μοντελοποίηση/ενημέρωση) και των δικών του περιορισμών είναι κρίσιμη για την σωστή επιλογή και σχεδίαση συστημάτων.
