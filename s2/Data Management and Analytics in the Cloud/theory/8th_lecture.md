# Βάσεις Δεδομένων Γραφήματος

---

## **Εισαγωγή: Τι είναι τα Δεδομένα Γραφήματος και πού εμφανίζονται;**

*   **Δεδομένα Γραφήματος:** Αναφέρονται σε δομές όπου τα αντικείμενα συνδέονται μεταξύ τους με συγκεκριμένες σχέσεις. Είναι μια φυσική αναπαράσταση πολλών πραγματικών συστημάτων.
*   **Παραδείγματα από τις Διαφάνειες:**
    *   **Κοινωνικά Δίκτυα:** Άτομα ως κόμβοι, φιλίες/σχέσεις ως ακμές. Παράδειγμα: Δίκτυο Facebook (δείχνει συνδέσεις, έννοια των "βαθμών διαχωρισμού").
    *   **Δίκτυα Μέσων/Πληροφοριών:** Άρθρα/Blog/Δημοσιεύσεις ως κόμβοι, αναφορές/συνδέσεις/links ως ακμές. Παράδειγμα: Δίκτυο πολιτικών blog (δείχνει πόλωση). Δίκτυα βιβλιογραφικών αναφορών ("Χάρτες επιστήμης") όπου οι επιστήμονες/εργασίες είναι κόμβοι και οι παραπομπές ακμές.
    *   **Δίκτυα Επικοινωνίας/Τεχνολογικά:** Συσκευές/Routers ως κόμβοι, συνδέσεις/γέφυρες ως ακμές. Παράδειγμα: Ο Internet, το ιστορικό πρόβλημα με τις Γέφυρες του Königsberg (Euler, 1735) ως πρώιμο παράδειγμα χρήσης γραφημάτων.
    *   **Ο Παγκόσμιος Ιστός (Web):** Ένα τεράστιο **κατευθυνόμενο γράφημα**. Webpages (ιστοσελίδες) είναι οι κόμβοι (Nodes), Hyperlinks (σύνδεσμοι) είναι οι ακμές (Edges). Οι ακμές έχουν κατεύθυνση (από πού σε πού οδηγεί ο σύνδεσμος).

---

## **Βασικά Στοιχεία Ενός Γραφήματος (Property Graph Model)**

Το μοντέλο **Property Graph** είναι το πιο διαδεδομένο σε σύγχρονες βάσεις δεδομένων γραφήματος (όπως η Neo4j).

*   **Κόμβοι (Vertices or Nodes):** Αναπαριστούν τα διακριτά αντικείμενα στο γράφημα (π.χ., Άνθρωπος, Εταιρεία, Webpage, Προϊόν).
*   **Ακμές (Edges or Relationships or Arcs):** Συνδέουν τους κόμβους και αναπαριστούν τις σχέσεις μεταξύ τους (π.χ., είναι Φίλος του, Εργάζεται στην, Παραπέμπει σε).
    *   Συνήθως είναι **κατευθυνόμενες** (π.χ., Α *αγαπά* τον Β, αλλά ο Β δεν *αγαπά* απαραίτητα τον Α).
    *   Συνήθως είναι **ονομασμένες/είναι τύπου** (π.χ., τύπος σχέσης "ΑΓΑΠΑΕΙ", "ΓΝΩΡΙΖΕΙ", "ΑΓΟΡΑΣΕ"). Αυτό προσδίδει **σημασιολογικό νόημα** στις συνδέσεις.
*   **Ιδιότητες (Properties):** Τόσο οι κόμβοι όσο και οι ακμές μπορούν να έχουν ιδιότητες. Πρόκειται για χαρακτηριστικά σε μορφή ζευγών (κλειδί, τιμή) [key, value pairs] που περιγράφουν τον κόμβο ή τη σχέση. (π.χ., στον κόμβο Άνθρωπος: ιδιότητα όνομα='Νίο', ηλικία=29. Στην ακμή "ΓΝΩΡΙΖΕΙ": ιδιότητα διάρκεια='12 χρόνια').

---

## **Βασικές Πράξεις (Πρωτεύοντα Primitive Operations) σε ένα Γράφημα**

*   Ορισμός / Διαγραφή κόμβων ή σχέσεων.
*   Εύρεση **γειτονικών κόμβων** ενός συγκεκριμένου κόμβου. Αυτή η πράξη είναι θεμελιώδης.
*   Συνδυάζοντας τις παραπάνω, ορίζονται **διασχίσεις γραφήματος (graph traversals)**, π.χ., εύρεση μονοπατιών, εύρεση όλων των κόμβων σε ακτίνα Ν βημάτων κ.α.

---

## **Γιατί να Χρησιμοποιήσουμε Βάσεις Δεδομένων Γραφήματος; (vs Σχεσιακές)**

Οι βάσεις δεδομένων γραφήματος είναι ιδιαίτερα χρήσιμες και αποδοτικές όταν:

1.  Οι **σχέσεις** μεταξύ των δεδομένων είναι **ιδιαίτερα σημαντικές** και αποτελούν αναπόσπαστο μέρος του μοντέλου και των ερωτημάτων μας.
2.  Χρειαζόμαστε συχνά να πραγματοποιούμε **διασχίσεις** στο δίκτυο, ειδικά σε **άγνωστο βάθος** (π.χ., εύρεση "φίλων των φίλων", το πρόβλημα των "βαθμών διαχωρισμού").
3.  Στις σχεσιακές βάσεις δεδομένων (RDBMS), τα ερωτήματα που απαιτούν διασχίσεις μεταφράζονται σε **πολλά και πολύπλοκα JOIN**.
    *   Η απόδοση των JOIN στις σχεσιακές **επιδεινώνεται σημαντικά** καθώς αυξάνεται το "βάθος" της σχέσης που θέλουμε να διασχίσουμε (δηλ., ο αριθμός των JOINs). (Παράδειγμα πίνακα απόδοσης Friends-of-Friends RDBMS vs Neo4j στη διαφάνεια 18).
    *   Επιπλέον, η προσθήκη νέων τύπων σχέσεων σε μια σχεσιακή βάση συχνά απαιτεί **αλλαγές στο σχήμα** της βάσης (προσθήκη νέων πινάκων ή στηλών), ενώ στις βάσεις γραφήματος είναι πιο δυναμικό.
    *   Οι σχέσεις στις σχεσιακές βάσεις υπολογίζονται "on-the-fly" με τα JOINs, ενώ στις βάσεις γραφήματος είναι **μόνιμες** (persistent) δομές αποθήκευσης, γεγονός που καθιστά την διάσχιση πολύ ταχύτερη, αλλά μπορεί να είναι πιο "ακριβό" το INSERT μιας σχέσης αρχικά.

---

## **Τι Είναι Μια Βάση Δεδομένων Γραφήματος (Graph Database);**

*   Ένα σύστημα διαχείρισης βάσεων δεδομένων (DBMS) που χρησιμοποιεί και εκθέτει στους χρήστες (μέσω πράξεων και ερωτημάτων) ένα **μοντέλο δεδομένων γραφήματος**.
*   Έχει **ρητή δομή γραφήματος** στο επίπεδο αποθήκευσης.
*   Είναι συνήθως σχεδιασμένες για **συναλλαγές (Transactional Processing / OLTP)**.
*   Υποστηρίζουν τις βασικές πράξεις **CREATE, READ, UPDATE, DELETE** στα στοιχεία του γραφήματος (κόμβοι και σχέσεις).
*   Παρέχουν δυνατότητα **ευρετηρίασης (indexing)** σε ιδιότητες κόμβων ή σχέσεων για ταχεία αναζήτηση.
*   Έχουν το χαρακτηριστικό **"Index-Free Adjacency"**: Κάθε κόμβος αποθηκεύει απευθείας **δείκτες** στους γειτονικούς του κόμβους και στις σχετικές ακμές. Αυτό σημαίνει ότι η "τοπική" εξερεύνηση (εύρεση γειτόνων) είναι σταθερής ταχύτητας **ανεξάρτητα από το συνολικό μέγεθος του γραφήματος** – κάθε "βήμα" (hop) στο γράφημα είναι πολύ γρήγορο.

**Προκλήσεις:** Η **κατανομή (Distribution)** μιας βάσης δεδομένων γραφήματος σε πολλαπλούς servers είναι συνήθως πιο πολύπλοκη από τις σχεσιακές ή άλλες NoSQL βάσεις, λόγω της έντονης διασύνδεσης των δεδομένων.

---

## **Κατηγορίες Βάσεων Δεδομένων Γραφήματος (Ανάλογα την Υλοποίηση)**

1.  **Native Graph Databases:** Βασίζονται σε μοντέλα αποθήκευσης **ειδικά σχεδιασμένα και βελτιστοποιημένα** για δομές γραφήματος. Χρησιμοποιούν κυρίως "Index-Free Adjacency".
    *   Παραδείγματα: **Neo4j**, **Sparksee**. (Η Neo4j χρησιμοποιεί ξεχωριστά αρχεία για κόμβους/σχέσεις/ιδιότητες, σχέσεις ως διπλά συνδεδεμένες λίστες, δείκτες).
2.  **Non-Native Graph Databases:** "Χτίζουν" ένα επίπεδο (layer) γραφήματος **πάνω από υπάρχοντα μοντέλα αποθήκευσης** (π.χ., key-value stores, document stores). Μπορεί να μην έχουν Index-Free Adjacency και η διάσχιση να μεταφράζεται σε lookup/join στο υποκείμενο σύστημα.
    *   Παραδείγματα: **OrientDB** (πάνω από Document DB), **Titan** (μπορεί να χρησιμοποιήσει backends όπως BerkeleyDB, Cassandra, HBase).

---

## **Μηχανές Παράλληλης Επεξεργασίας Γραφημάτων (Graph Parallel Processing Engines)**

Αυτά δεν είναι βάσεις δεδομένων συναλλαγών, αλλά συστήματα για **μαζική (batch)** επεξεργασία και ανάλυση γραφημάτων μεγάλης κλίμακας, συχνά σε κατανεμημένα περιβάλλοντα.

*   Σχεδιασμένα για επεξεργασία **αλγορίθμων γραφήματος** σε μεγάλα δεδομένα (π.χ., PageRank, εύρεση κοινοτήτων).
*   Συχνά λειτουργούν πάνω από πλατφόρμες κατανεμημένης επεξεργασίας όπως Hadoop ή Spark.
*   Παραδείγματα: **Apache Giraph** (πάνω από Hadoop), **GraphX** (μέρος του Berkeley Data Analytic Stack, χρησιμοποιεί Spark), **Titan** (μπορεί να χρησιμοποιηθεί και σε batch mode πάνω από τα κατανεμημένα backends).

---

## **Τύποι Ερωτημάτων σε Βάσεις Γραφήματος (Υψηλού Επιπέδου)**

*   **Sub-graph queries:** Αναζήτηση για την ύπαρξη ενός συγκεκριμένου μοτίβου γραφήματος μέσα στην βάση (graph pattern matching, συχνά με την έννοια του sub-graph isomorphism).
*   **Super-graph queries:** Αναζήτηση για γραφήματα (όταν η βάση περιέχει πολλά ανεξάρτητα γραφήματα) που **περιέχουν** ένα συγκεκριμένο μοτίβο γραφήματος.
*   **Similarity queries:** Εύρεση γραφημάτων στην βάση που είναι **παρόμοια** με ένα γράφημα ερωτήματος (χρησιμοποιώντας μέτρα ομοιότητας).

**Neo4j: Ένα Παράδειγμα Βάσης Δεδομένων Γραφήματος**

*   Είναι μία από τις πιο δημοφιλείς **Native Graph Databases**.
*   Είναι **NoSQL**, Ανοιχτού Κώδικα, Cross-Platform (σε Java/JVM).
*   Είναι **Επεκτάσιμη (Scalable)**, μπορεί να χειριστεί δισεκατομμύρια κόμβους.
*   Υποστηρίζει **Transactional ACID** εγγυήσεις και Multi-version consistency.
*   Χρησιμοποιεί το μοντέλο **Property Graph** που περιγράψαμε.
*   Η κύρια γλώσσα ερωτημάτων της είναι η **Cypher**.

---

## **Cypher: Η Γλώσσα Ερωτημάτων της Neo4j**

*   Είναι μια **δηλωτική** (declarative) γλώσσα, **εμπνευσμένη από την SQL**, αλλά ειδικά σχεδιασμένη για γραφήματα.
*   Περιγράφει τα μοτίβα που θέλουμε να αναζητήσουμε ή να επεξεργαστούμε στο γράφημα **οπτικά**, χρησιμοποιώντας ένα είδος "ascii-art" σύνταξης.

---

## **Σύνταξη της Cypher:**

*   **Κόμβοι (Nodes):**
    *   `()` : Ανώνυμος κόμβος (υπάρχει, αλλά δεν μας ενδιαφέρει να τον αναφέρουμε ξανά).
    *   `(x)` : Εκχωρεί τη μεταβλητή `x` στον κόμβο. Η μεταβλητή αυτή είναι προσβάσιμη στο εύρος (scope) του ερωτήματος.
    *   `(x: Label)` : Κόμβος με τη μεταβλητή `x` και το label `Label`. Τα labels κατηγοριοποιούν τους κόμβους. Ένας κόμβος μπορεί να έχει πολλά labels (π.χ., `:Person:Entity`).
    *   `(x {property: "abc"})` : Κόμβος `x` με συγκεκριμένη τιμή στην ιδιότητα `property`. Αναφερόμαστε σε αυτήν την ιδιότητα ως `x.property`.
*   **Σχέσεις (Relationships):**
    *   `-->` ή `<--` : Δείχνει κατευθυνόμενη σχέση. `(a)--> (b)` σημαίνει σχέση από `a` προς `b`.
    *   `--` : Δείχνει μη-κατευθυνόμενη σχέση (η Cypher την αντιμετωπίζει σαν να είναι προς και τις δύο κατευθύνσεις κατά το ταίριασμα μοτίβων, αν και οι σχέσεις αποθηκεύονται με κατεύθυνση στη Neo4j).
    *   `-[ ]->` ή `<-[ ]-` ή `-[ ]-` : Εντός των αγκύλων `[ ]` μπαίνουν τα χαρακτηριστικά της σχέσης.
    *   `-[r]->` : Εκχωρεί τη μεταβλητή `r` στη σχέση.
    *   `-[:TYPE]->` : Σχέση τύπου `TYPE`. Μπορεί να έχει πολλαπλούς τύπους με `|` (π.χ., `[:RELATES1|RELATES2]`).
    *   `-[r:TYPE]->` : Σχέση με μεταβλητή `r` και τύπο `TYPE`.
    *   `-[r:TYPE {property:'abc'}]->` : Σχέση `r` τύπου `TYPE` με συγκεκριμένη ιδιότητα `property`. Αναφορά ως `r.property`.
    *   `-[*1..3]->` : Ορίζει το μήκος της διαδρομής. Σχέση που επαναλαμβάνεται 1 έως 3 φορές (π.χ., φίλοι του φίλου, φίλοι του φίλου του φίλου). `*` σημαίνει 0 ή περισσότερες, `*N` N ή περισσότερες, `*N..M` από N έως M, `*..M` 0 έως M.

**Μοτίβα (Patterns):** Συνδυασμοί κόμβων και σχέσεων που περιγράφουν ένα μέρος του γραφήματος (ή τη δομή που ψάχνουμε). Γράφονται συνεχόμενα ή διαχωρισμένα με κόμμα. Μπορούμε να αναφερθούμε σε μεταβλητές που δηλώθηκαν νωρίτερα. (Παραδείγματα διαφανειών 34, 35).

---

## **Δομή Ερωτημάτων Cypher:**

*   **Δομή Ανάγνωσης (Read Query Structure):**
    `[MATCH WHERE]`
    `[OPTIONAL MATCH WHERE]`
    `[WITH [ORDER BY] [SKIP] [LIMIT]]`
    `RETURN [ORDER BY] [SKIP] [LIMIT]`

    *   **MATCH:** Η θεμελιώδης ρήτρα για **εύρεση μοτίβων** στο γράφημα. Βρίσκει τα μέρη του γραφήματος που ταιριάζουν με τα μοτίβα που ορίζουμε.
    *   **WHERE:** Εφαρμόζει επιπλέον **συνθήκες/φίλτρα** στις μεταβλητές ή τις ιδιότητες που βρέθηκαν στο MATCH.
    *   **OPTIONAL MATCH:** Δοκιμάζει να ταιριάξει ένα μοτίβο, αλλά δεν απορρίπτει ολόκληρο το αποτέλεσμα αν δεν βρεθεί ταίριασμα (οι μεταβλητές που δεν βρέθηκαν είναι null).
    *   **WITH:** Χρησιμοποιείται για να **"αλυσοδέσει" (pipeline)** μέρη ενός ερωτήματος. Επιτρέπει τη μετάβαση αποτελεσμάτων από ένα βήμα στο επόμενο, φιλτράρισμα, ταξινόμηση ή ομαδοποίηση ενδιάμεσα. Λειτουργεί ως το "κεφάλι" του επόμενου βήματος (π.χ., ενός νέου MATCH ή ενός RETURN).
    *   **RETURN:** Καθορίζει ποια δεδομένα θα **επιστραφούν** ως αποτέλεσμα του ερωτήματος (όπως η SELECT στην SQL). Μπορεί να επιστρέψει κόμβους, σχέσεις, ιδιότητες, υπολογισμένες τιμές (π.χ., με count()).
    *   **ORDER BY:** Ταξινομεί τα αποτελέσματα.
    *   **SKIP:** Παραλείπει έναν αριθμό αποτελεσμάτων από την αρχή (όπως OFFSET).
    *   **LIMIT:** Περιορίζει τον μέγιστο αριθμό αποτελεσμάτων που θα επιστραφούν.

*   **Δομή Εγγραφής (Write-Only Query Structure):**
    `(CREATE [UNIQUE] | MERGE)*`
    `[SET | DELETE | REMOVE | FOREACH]*`
    `[RETURN [ORDER BY] [SKIP] [LIMIT]]` (Το RETURN είναι προαιρετικό στην εγγραφή)

    *   **CREATE:** Δημιουργεί νέα στοιχεία (κόμβους ή σχέσεις) στο γράφημα.
    *   **MERGE:** Προσπαθεί να ταιριάξει ένα μοτίβο. **Εάν το μοτίβο βρεθεί**, επιστρέφει τα στοιχεία που ταιριάζουν. **Εάν δεν βρεθεί**, **δημιουργεί** το μοτίβο. Είναι ένας συνδυασμός MATCH και CREATE, χρήσιμο για αποφυγή διπλότυπων. Η [UNIQUE] επιτρέπει τον ορισμό constraints.
    *   **SET:** Ενημερώνει (προσθέτει ή τροποποιεί) **ιδιότητες** σε κόμβους ή σχέσεις. Μπορεί επίσης να προσθέσει **labels** σε κόμβους.
    *   **DELETE:** Διαγράφει κόμβους ή σχέσεις. Η διαγραφή κόμβου απαιτεί πρώτα τη διαγραφή όλων των σχέσεων που συνδέονται με αυτόν.
    *   **DETACH DELETE:** Διαγράφει έναν κόμβο **μαζί** με όλες τις σχέσεις του.
    *   **REMOVE:** Διαγράφει **labels** από κόμβους ή συγκεκριμένες **ιδιότητες** από κόμβους ή σχέσεις (όχι τα ίδια τα στοιχεία).
    *   **FOREACH:** Εκτελεί μία ή περισσότερες ενέργειες σε κάθε στοιχείο μιας λίστας.

*   **Δομή Ανάγνωσης-Εγγραφής (Read-Write Query Structure):** Συνδυάζει ρήτρες ανάγνωσης (MATCH, WITH) και ρήτρες εγγραφής (CREATE, MERGE, SET, DELETE, κλπ) στην ίδια εντολή, ακολουθώντας μια λογική σειρά.

**(Σημείωση: Δείτε τα παραδείγματα Cypher στις διαφάνειες 35, 36, 40, 44 για πρακτική εξάσκηση στο συντακτικό.)**

---

## **Τελική Σύνοψη:**

Οι βάσεις δεδομένων γραφήματος είναι μια ισχυρή εναλλακτική λύση στις σχεσιακές βάσεις όταν η δομή των δεδομένων είναι πολύπλοκο δίκτυο και οι σχέσεις και οι διασχίσεις αυτού του δικτύου είναι κεντρικές στην εφαρμογή και στα ερωτήματα. Το μοντέλο Property Graph και γλώσσες όπως η Cypher στη Neo4j παρέχουν μια διαισθητική και αποδοτική προσέγγιση για τη μοντελοποίηση, αποθήκευση και ανάκτηση αυτών των δεδομένων.
