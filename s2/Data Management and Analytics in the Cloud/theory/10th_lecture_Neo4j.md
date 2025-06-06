## Graph Databases

**Διαφάνεια 1: Graph Databases**
*   **Τίτλος:** Εισαγωγή στο θέμα των Βάσεων Δεδομένων Γράφων.

**Διαφάνειες 2-6: Δεδομένα Γράφων - Παραδείγματα Εφαρμογών**
*   Αυτές οι διαφάνειες τονίζουν την πανταχού παρουσία δεδομένων με δομή γράφου/δικτύου:
    *   **Κοινωνικά Δίκτυα (Social Networks - Facebook):** Αναπαράσταση φιλικών σχέσεων, συνδέσεων. Το παράδειγμα "4-degrees of separation" (6 βαθμοί διαχωρισμού) δείχνει τη σημασία των διαδρομών και της απόστασης σε ένα δίκτυο.
    *   **Δίκτυα Μέσων (Media Networks - Political Blogs):** Συνδέσεις μεταξύ πολιτικών ιστολογίων. Η οπτικοποίηση (κόκκινα και μπλε clusters) αναδεικνύει την **πόλωση του δικτύου** (δηλ. διακριτές ομάδες με λίγες συνδέσεις μεταξύ τους, κάτι που είναι σημαντικό στην ανάλυση επιρροής).
    *   **Δίκτυα Πληροφοριών (Information Nets - Citation Networks & Maps of Science):** Συνδέσεις μεταξύ επιστημονικών εργασιών (μέσω παραπομπών) ή πεδίων έρευνας. Αποκαλύπτει τη δομή της επιστήμης και τη διασύνδεση διαφορετικών επιστημονικών κλάδων (Χημεία, Βιοτεχνολογία, Ιατρική κ.λπ.).
    *   **Δίκτυα Επικοινωνίας (Communication Nets - Internet):** Αναπαράσταση της υποδομής του Διαδικτύου (υπολογιστές, servers, routers) και των συνδέσεών τους.
    *   **Τεχνολογικά Δίκτυα (Technological Networks - Seven Bridges of Königsberg):** Το κλασικό πρόβλημα του Euler από το 1735, ένα πρώιμο παράδειγμα χρήσης γράφων για την επίλυση προβλημάτων που αφορούν τη διαδρομή σε ένα δίκτυο.

**Διαφάνειες 7-9: Ο Παγκόσμιος Ιστός ως Γράφος (Web as a Graph)**
*   **Σημασία:** Ο Παγκόσμιος Ιστός είναι ένα από τα πιο προφανή και κρίσιμα παραδείγματα κατευθυνόμενου γράφου.
    *   **Κόμβοι (Nodes):** Ιστοσελίδες (Webpages).
    *   **Ακμές (Edges):** Υπερσύνδεσμοι (Hyperlinks) - κατευθυνόμενες συνδέσεις από μία σελίδα σε άλλη.
*   Αυτή η δομή επέτρεψε την ανάπτυξη αλγορίθμων όπως ο PageRank της Google, που εκμεταλλεύονται τις σχέσεις (links) μεταξύ ιστοσελίδων για να προσδιορίσουν τη σχετική τους σημασία.

**Διαφάνεια 10: Γράφοι (Graphs) - Θεμελιώδεις Έννοιες**
*   **Ορισμός Γράφου:**
    *   **Vertices ή Nodes (Κόμβοι):** Αντιπροσωπεύουν διακριτά αντικείμενα (π.χ., ένα άτομο, μια ιστοσελίδα, μια πόλη).
    *   **Edges, Relationships ή Arcs (Ακμές/Σχέσεις):** Συνδέουν τους κόμβους, αναπαριστώντας μια σχέση μεταξύ τους (π.χ., "γνωρίζει", "συνδέεται με", "βρίσκεται κοντά σε").
    *   **Properties (Ιδιότητες):** Τόσο οι κόμβοι όσο και οι ακμές μπορούν να έχουν ιδιότητες (key-value pairs) που περιγράφουν περαιτέρω τα αντικείμενα ή τις σχέσεις (π.χ., για έναν κόμβο-άτομο: όνομα, ηλικία· για μια ακμή-φιλία: ημερομηνία έναρξης φιλίας). Αυτό το μοντέλο είναι γνωστό ως **Property Graph Model**.

**Διαφάνεια 11: Παράδειγμα Property Graph (Από το "Matrix")**
*   Οπτικοποίηση του Property Graph Model:
    *   **Κόμβοι:** Neo, Morpheus, Trinity, Agent Smith, Cypher, The Architect.
    *   **Σχέσεις (Ακμές):** KNOWS (γνωρίζει), LOVES (αγαπά), CODED_BY (κωδικοποιήθηκε από), ROOT (ριζώνει σε).
    *   **Ιδιότητες (στοιχεία πάνω στους κόμβους/ακμές):** name (όνομα), age (ηλικία), occupation (επάγγελμα), rank (βαθμός) για τους κόμβους. Για τις σχέσεις: age (διάρκεια σχέσης), disclosure (δημοσιότητα).

**Διαφάνεια 12: Βασικές Πρωτογενείς Λειτουργίες (Basic Primitives)**
*   Οι βασικές λειτουργίες που υποστηρίζει μια βάση δεδομένων γράφων:
    *   Δημιουργία/διαγραφή κόμβων και σχέσεων.
    *   Εύρεση γειτονικών κόμβων (άμεσων συνδέσεων).
    *   **Graph Traversal (Περιήγηση Γράφου):** Ο συνδυασμός αυτών των πρωτογενών λειτουργιών για την πλοήγηση στον γράφο, εξερευνώντας μονοπάτια και ακολουθώντας σχέσεις.

**Διαφάνεις 13-15: Μετατροπή από Σχεσιακές Βάσεις σε Γράφους (Relational to Graphs)**
*   Αυτές οι διαφάνειες παρουσιάζουν τη βασική πρόκληση των σχεσιακών βάσεων δεδομένων (RDBMS) όταν διαχειρίζονται πολύπλοκες, συνδεδεμένες δομές και πώς οι βάσεις δεδομένων γράφων τις επιλύουν.
    *   **Σχεσιακή Βάση:** Χρειάζονται πολλές σχέσεις **JOIN** μεταξύ πινάκων για να αναπαρασταθούν απλές σχέσεις (π.χ., "ένας ηθοποιός έπαιξε σε μια ταινία που σκηνοθέτησε κάποιος"). Όσο πιο βαθιά είναι η σχέση (π.χ. φίλοι των φίλων των φίλων), τόσο περισσότερα JOINs απαιτούνται, οδηγώντας σε πολυπλοκότητα και επιβράδυνση.
    *   **Βάση Δεδομένων Γράφων:** Η σχέση αποθηκεύεται άμεσα ως μια ακμή μεταξύ δύο κόμβων. Η πλοήγηση σε αυτή τη σχέση είναι άμεση ("index-free adjacency").

**Διαφάνεια 16: Γιατί Γράφοι; (Why Graphs?)**
*   Συνοψίζει τα πλεονεκτήματα:
    *   **Όταν οι σχέσεις είναι σημαντικές:** Οι γράφοι μοντελοποιούν άμεσα τις σχέσεις, καθιστώντας τις δεδομένα πρώτης κλάσης.
    *   **Περιορισμοί της SQL:** Η SQL δεν έχει εύκολη σύνταξη για **περιηγήσεις αγνώστου βάθους** (άγνωστο πλήθος JOINs), όπως στο πρόβλημα των "βαθμών διαχωρισμού" (degrees of separation).
    *   **Επιδείνωση της Απόδοσης:** Στις RDBMS, η απόδοση υποβαθμίζεται σημαντικά καθώς αυξάνεται το βάθος της περιήγησης (πολλαπλά JOINs).

**Διαφάνειες 17-18: Παράδειγμα Απόδοσης - "Φίλοι των Φίλων" (Friends-of-Friends)**
*   **Αναζήτηση ηθοποιών που συνεργάστηκαν με τον Keanu Reeves:** Δείχνει την πολυπλοκότητα του SQL query (πολλά JOINs) για ένα φαινομενικά απλό ερώτημα που αφορά σχέσεις.
*   **Σύγκριση Απόδοσης RDBMS vs. Neo4j:** Αυτός ο πίνακας είναι κρίσιμος.
    *   **RDBMS:** Ο χρόνος εκτέλεσης αυξάνεται δραματικά με το βάθος της αναζήτησης (π.χ., από 30 δευτερόλεπτα για βάθος 3 σε 1543 δευτερόλεπτα για βάθος 4, ενώ βάθος 5 δεν ολοκληρώθηκε). Αυτό οφείλεται στην πολυπλοκότητα των JOINs.
    *   **Neo4j:** Ο χρόνος εκτέλεσης παραμένει σχεδόν σταθερός και πολύ χαμηλός (π.χ., από 0.168 σε 1.359 σε 2.132 δευτερόλεπτα), ανεξαρτήτως του βάθους. Αυτό συμβαίνει επειδή η πλοήγηση γίνεται με "index-free adjacency", δηλαδή κάθε κόμβος γνωρίζει απευθείας τους γειτονικούς του, χωρίς να απαιτείται περίπλοκη αναζήτηση ευρετηρίων.

**Διαφάνειες 19-20: Βάσεις Δεδομένων Γράφων (Graph Databases - Επεξήγηση)**
*   **Βασικά Χαρακτηριστικά:**
    *   Σύστημα διαχείρισης βάσεων δεδομένων με **ρητή δομή γράφου**.
    *   **Data-centric view:** Τα δεδομένα και οι σχέσεις τους βρίσκονται στο επίκεντρο.
    *   Υποστήριξη **OLTP (Online Transactional Processing):** Συναλλαγές (CRUD - Create, Read, Update, Delete) σε στοιχεία του γράφου.
    *   **Index-free adjacency:** Κάθε κόμβος έχει άμεσο δείκτη (pointer) στους γειτονικούς του κόμβους, καθιστώντας την περιήγηση εξαιρετικά γρήγορη ανεξαρτήτως του μεγέθους του γράφου. Το κόστος ενός "hop" (βήματος) παραμένει σταθερό.
    *   **Graph Traversal:** Η κύρια μέθοδος πρόσβασης στα δεδομένα.
    *   Πρόσθετα ευρετήρια (indexes) για αναζητήσεις ιδιοτήτων (π.χ., "βρες όλους τους κόμβους με ιδιότητα 'επάγγελμα = γιατρός'").
    *   **Πρόκληση:** Η **κατανεμημένη αρχιτεκτονική (distribution)** των βάσεων δεδομένων γράφων ήταν ιστορικά μια πρόκληση (αλλά έχει βελτιωθεί σημαντικά σε σύγχρονες υλοποιήσεις).

**Διαφάνεια 21: Σχεσιακές vs. Βάσεις Δεδομένων Γράφων (Relational vs Graph Database)**
*   **RDBMS (Σχεσιακές ΒΔ):**
    *   Απαιτούν **αλλαγές σχήματος** για νέους τύπους σχέσεων (π.χ., προσθήκη στήλης "manager" ή νέου πίνακα).
    *   Πολλά JOINs για περιηγήσεις σε σχέσεις, καθώς οι σχέσεις υπολογίζονται κατά την εκτέλεση του ερωτήματος.
*   **Graph DB (Βάσεις Γράφων):**
    *   Οι σχέσεις δημιουργούνται/διαγράφονται **δυναμικά**, χωρίς περιορισμό στον τύπο ή τον αριθμό (schemaless ή schema-flexible).
    *   Οι σχέσεις **αποθηκεύονται μόνιμα** και δεν υπολογίζονται κατά την εκτέλεση του ερωτήματος, κάνοντας τις αναζητήσεις σχέσεων πολύ πιο γρήγορες (αλλά ενδεχομένως πιο "δαπανηρές" κατά την εισαγωγή δεδομένων αρχικά).

**Διαφάνεια 22: Μοντέλο Αποθήκευσης (Storage Model)**
*   Δύο προσεγγίσεις για τις ΒΔ Γράφων:
    *   **Native Graph Databases:** Σχεδιάστηκαν από το μηδέν με ένα μοντέλο αποθήκευσης ειδικά βελτιστοποιημένο για δομές γράφων (π.χ., Neo4j, Sparksee). Προσφέρουν την καλύτερη απόδοση για περιηγήσεις.
    *   **Graph Databases built upon existing storage models (Non-Native):** Υποστηρίζουν ένα "graph layer" πάνω σε υπάρχοντα μοντέλα αποθήκευσης (π.χ., βάσεις key-value ή document databases). Μπορεί να προσφέρουν ευελιξία, αλλά η απόδοση στις βαθιές περιηγήσεις ενδέχεται να μην είναι τόσο καλή όσο στις native.

**Διαφάνεια 23: Native Graph Databases**
*   **Neo4j:** Χρησιμοποιεί ξεχωριστά αρχεία για κόμβους, σχέσεις και ιδιότητες. Οι σχέσεις είναι διπλά συνδεδεμένες λίστες και οι δείκτες συνδέουν αποτελεσματικά τους κόμβους και τις σχέσεις με τις ιδιότητές τους.
*   **Sparksee (DEX):** Αντιμετωπίζει κόμβους και σχέσεις ως αντικείμενα που αποθηκεύονται με metadata και μοναδικά αναγνωριστικά.

**Διαφάνεια 24: Non-Native Graph Databases**
*   **OrientDB:** Μπορεί να λειτουργήσει ως Document database, InfiniteGraph (μια μορφή), ή Object-oriented database.
*   **Titan (πλέον DataStax Enterprise Graph / JanusGraph):** Υποστήριζε διάφορα backends key-value βάσεων δεδομένων:
    *   **BerkeleyDB:** Κατάλληλη για single-site λύσεις.
    *   **Cassandra & HBase:** Συστήματα αποθήκευσης key-value σχεδιασμένα για **διανομή** και **υψηλή διαθεσιμότητα** (δηλ. ιδανικά για το Cloud Computing).

**Διαφάνεια 25: Μηχανές Παράλληλης Επεξεργασίας Γράφων (Graph Parallel Processing Engines)**
*   Αυτά είναι συστήματα που επιτρέπουν την επεξεργασία *πολύ μεγάλων* γράφων σε **κατανεμημένα περιβάλλοντα** (π.χ., υπολογιστικό νέφος) χρησιμοποιώντας παράλληλους αλγορίθμους.
    *   Ενσωματώνουν μοντέλα επεξεργασίας γράφων με άλλα μοντέλα δεδομένων.
    *   Παρέχουν Graph APIs και αλγορίθμους επεξεργασίας γράφων.
    *   Σχεδιασμένα για **batch operations/analysis** (όχι απαραίτητα OLTP).
    *   **Συστήματα:** Apache Giraph (πάνω από Hadoop MapReduce), GraphX (μέρος του Berkeley Data Analytic Stack, χρησιμοποιεί Spark). Το Titan μπορεί επίσης να θεωρηθεί τέτοιο, καθώς βασίζεται σε κατανεμημένες βάσεις.

**Διαφάνεια 26: Είδη Ερωτημάτων (Type of Queries)**
*   Κατηγοριοποίηση κοινών ερωτημάτων γράφων:
    *   **Sub-graph queries (ερωτήματα υπογράφων):** Αναζήτηση για ένα συγκεκριμένο μοτίβο (pattern) γράφου μέσα σε έναν μεγαλύτερο γράφο (π.χ., "βρες όλους τους κύκλους φίλων 3 ατόμων"). Αφορά το **sub-graph isomorphism** (εύρεση ισομορφικών υπογράφων).
    *   **Super-graph queries (ερωτήματα υπερ-γράφων):** Αντίστροφα, αναζήτηση για έναν γράφο που περιέχει ένα δεδομένο μοτίβο.
    *   **Similarity queries (ερωτήματα ομοιότητας):** Εύρεση γράφων ή τμημάτων γράφων που είναι παρόμοια με ένα δεδομένο γράφο/μοτίβο (π.χ., "βρες χρήστες με παρόμοια δίκτυα φίλων").

**Διαφάνεια 27-28: Neo4j**
*   **Βασικά Χαρακτηριστικά του Neo4j:**
    *   NoSQL Graph Database.
    *   Open Source.
    *   Cross-Platform, γραμμένο σε Java.
    *   **Επεκτάσιμο (Scalable):** Υποστηρίζει δισεκατομμύρια κόμβους και σχέσεις.
    *   Optional Schema: Ευελιξία στο σχήμα, επιτρέποντας την προσθήκη νέων ιδιοτήτων και τύπων σχέσεων δυναμικά.
    *   Βελτιστοποιημένο για απόδοση σε γράφους.
    *   **ACID transactions:** Εξασφαλίζει την ακεραιότητα των δεδομένων.
    *   Multi-version consistency.
    *   Master-slave replication.
    *   **Expressive graph query language (Cypher):** Μια γλώσσα ερωτημάτων σχεδιασμένη ειδικά για γράφους.

**Διαφάνεια 29: Μοντέλο Δεδομένων: Property Graph**
*   Επαναλαμβάνει τον ορισμό του Property Graph:
    *   **Κόμβοι (Nodes):** Οντότητες (entities).
    *   **Σχέσεις (Relationships):** Ακμές (edges), **κατευθυνόμενες** και με **σημασιολογικά σχετικό όνομα** (π.χ., :LOVES, :KNOWS).
    *   **Ιδιότητες (Properties):** Key-value pairs που συνδέονται τόσο με τους κόμβους όσο και με τις σχέσεις.

**Διαφάνεια 30: Property Graph (Επανάληψη παραδείγματος Matrix)**
*   Επαναλαμβάνει το οπτικό παράδειγμα από το "Matrix" για να εμπεδώσει το μοντέλο του Property Graph.

**Διαφάνεια 31-44: Cypher**
*   **Τι είναι η Cypher:** Μια δηλωτική, εμπνευσμένη από την SQL, γλώσσα για την περιγραφή μοτίβων σε γράφους, χρησιμοποιώντας σύνταξη που μοιάζει με ASCII-art.
*   **Cypher Syntax:**
    *   **Για κόμβους:** `()` (ανώνυμος κόμβος), `(x)` (κόμβος με μεταβλητή x), `(x: Label)` (κόμβος με ετικέτα), `(x:L1:L2)` (κόμβος με πολλές ετικέτες), `(x {property: "value"})` (κόμβος με ιδιότητα).
    *   **Για σχέσεις:** `-[:REL]->` (σχέση με τύπο REL), `-[:REL1 | REL2]->` (με πολλαπλούς τύπους), `-[y]->` (σχέση με μεταβλητή), `-[y:REL{prop:'val'}]->` (σχέση με ιδιότητα).
    *   `*1..3` για επαναλαμβανόμενες σχέσεις (path patterns).
    *   Το `-` δείχνει σχέση, το `->` δείχνει κατευθυνόμενη σχέση.
*   **Patterns (Μοτίβα):** Η Cypher επιτρέπει τη δημιουργία σύνθετων μοτίβων για αναζήτηση:
    *   Friend-of-a-friend: `(user)-[:KNOWS]-(friend)-[:KNOWS]-(foaf)`
    *   Shortest path: `shortestPath((user)-[:KNOWS*..5]-(other))`
    *   Collaborative Filtering: `(user)-[:PURCHASED]->(product)<-[:PURCHASED]-()-[:PURCHASED]->(otherProduct)`
    *   Tree navigation: `(root)<-[:PARENT*]-(leaf:Category)-[:ITEM]->(data:Product)`
*   **Example Pattern & Query (Emil, Jim, Ian):** Δείχνει πώς ένα οπτικό μοτίβο μεταφράζεται σε Cypher query.
*   **Building Blocks of a Cypher Pattern:** Αναλύει ένα MATCH statement στα συστατικά του (Label, Property, Variable) για κόμβους και σχέσεις.
*   **Cypher Query Structure:**
    *   **Read-Only:** `MATCH` (προσδιορίζει μοτίβο), `WHERE` (φιλτράρει), `WITH` (συνδέει στάδια query/pipeline), `RETURN` (επιστρέφει αποτέλεσμα), `ORDER BY`, `SKIP`, `LIMIT`.
    *   **Write-Only:** `CREATE` (δημιουργεί), `MERGE` (αντιστοιχεί ή δημιουργεί αν δεν υπάρχει), `SET` (ενημερώνει ιδιότητες), `DELETE` (διαγράφει), `REMOVE` (αφαιρεί ετικέτες/ιδιότητες), `FOREACH`.
    *   **Read-Write:** Συνδυασμός των παραπάνω.
*   **Cypher Examples:** Πρακτικά παραδείγματα query για αναζήτηση φίλων και δημιουργία/ενημέρωση σχέσεων.

**Διαφάνεια 45: Πόροι (Resources)**
*   Μια λίστα χρήσιμων πόρων και βιβλίων για περαιτέρω μελέτη του Neo4j, του Cypher και άλλων βάσεων δεδομένων γράφων. Ιδιαίτερα σημαντική η επίσημη τεκμηρίωση του Neo4j και του Cypher.

---

**Συμπέρασμα για το Μάθημα "Διαχείριση και Αναλυτική Δεδομένων στο Υπολογιστικό Νέφος":**

Οι βάσεις δεδομένων γράφων και οι γλώσσες ερωτημάτων τους όπως η Cypher είναι κρίσιμες για τη διαχείριση και ανάλυση συνδεδεμένων δεδομένων. Το πλεονέκτημά τους έναντι των σχεσιακών βάσεων δεδομένων στην αποτελεσματική περιήγηση σχέσεων (λόγω index-free adjacency) τις καθιστά απαραίτητες για εφαρμογές όπως τα κοινωνικά δίκτυα, τα συστήματα συστάσεων, την ανάλυση απάτης, τη βιοπληροφορική και την τεχνητή νοημοσύνη.

Η εκμάθηση του τρόπου μοντελοποίησης δεδομένων ως γράφους και η χρήση εργαλείων όπως το Neo4j με τη Cypher, ιδιαίτερα στο πλαίσιο των κατανεμημένων συστημάτων και του υπολογιστικού νέφους, είναι μια βασική δεξιότητα για τον σύγχρονο επιστήμονα δεδομένων και μηχανικό δεδομένων.

Για την μελέτη σας, εστιάστε στα εξής:
1.  Κατανόηση του **Property Graph Model**.
2.  Τους **περιορισμούς των RDBMS** στην αντιμετώπιση συνδεδεμένων δεδομένων και γιατί οι Graph DBs υπερέχουν (ιδιαίτερα η **index-free adjacency**).
3.  Τα βασικά **είδη ερωτημάτων** που μπορούν να απαντηθούν με γράφους (π.χ., link prediction, community detection, shortest path).
4.  Βασικές έννοιες της **Cypher** (πώς αναπαρίστανται κόμβοι/σχέσεις και οι κύριες ρήτρες MATCH, WHERE, RETURN).
