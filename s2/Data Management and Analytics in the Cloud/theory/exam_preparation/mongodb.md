# MongoDB: Ερωτήσεις πολλαπλής επιλογής

Για τις ανάγκες των ερωτήσεων, θα υποθέσουμε ότι έχουμε μια συλλογή `books` με τα ακόλουθα πεδία (και ορισμένα νέα για περισσότερες ερωτήσεις):

*   `_id`: ObjectId
*   `title`: String (Τίτλος του βιβλίου)
*   `author`: String (Συγγραφέας, υποθέτουμε ένας)
*   `authors`: Array of Strings (Λίστα συγγραφέων, για ερωτήσεις με `$unwind`)
*   `pyear`: Number (Έτος έκδοσης)
*   `pages`: Number (Αριθμός σελίδων)
*   `rating`: Number (Βαθμολογία από 1.0 έως 5.0)
*   `genre`: String (Κύριο είδος, π.χ., "Fantasy", "Mystery")
*   `genres`: Array of Strings (Πολλά είδη, π.χ., ["Fantasy", "Adventure"])
*   `keywords`: Array of Strings (Λέξεις-κλειδιά, π.χ., ["magic", "dragons", "quest"])
*   `language`: String (Γλώσσα, π.χ., "English", "Greek")
*   `inStock`: Boolean (Διαθεσιμότητα σε απόθεμα)

Ακολουθούν 100 ερωτήσεις πολλαπλής επιλογής, με την σωστή απάντηση και λεπτομερή εξήγηση. Θα τις ομαδοποιήσω ανάλογα με την πολυπλοκότητα των λειτουργιών (Basic `find`, Projections, `count()`, Sorting, Aggregation Basics, Aggregation with `$match`, Complex Aggregations, Array Operations).

---

### **Σειρά Ερωτήσεων για MongoDB**

**Ομάδα 1: Βασικές Ερωτήσεις `db.collection.find()`**

**1. Ποια εντολή ανακτά όλα τα βιβλία από τη συλλογή `books`;**
    α. `db.books.find()`
    β. `db.books.getAll()`
    γ. `db.books.select({})`
    δ. `db.books.list()`
*   **Απάντηση:** α
*   **Εξήγηση:** Η μέθοδος `find()` χωρίς ορίσματα επιστρέφει όλα τα έγγραφα της συλλογής.

**2. Ποια εντολή ανακτά βιβλία με τίτλο "The Hobbit";**
    α. `db.books.find({title: "The Hobbit"})`
    β. `db.books.find(title="The Hobbit")`
    γ. `db.books.find({"title": "The Hobbit"})`
    δ. `db.books.find({title: /The Hobbit/})`
*   **Απάντηση:** α
*   **Εξήγηση:** Για ακριβή αντιστοίχιση σε ένα πεδίο, παρέχουμε ένα query document `{<field>: <value>}`. Η β είναι λάθος σύνταξη, η γ είναι ισοδύναμη αλλά η α είναι πιο συχνή, και η δ είναι για pattern matching (regular expressions).

**3. Ποια εντολή ανακτά όλα τα βιβλία που εκδόθηκαν το έτος 2000;**
    α. `db.books.find({pyear: "2000"})`
    β. `db.books.find({pyear: 2000})`
    γ. `db.books.find({pyear: {$eq: 2000}})`
    δ. Όλα τα παραπάνω.
*   **Απάντηση:** β
*   **Εξήγηση:** Το `pyear` είναι αριθμός, οπότε η τιμή πρέπει να είναι αριθμητική. Η α είναι λανθασμένη. Η γ είναι σωστή αλλά περιττή χρήση του `$eq` για ακριβή ισότητα. Επομένως, η β είναι η πιο άμεση και ορθή.

**4. Ποια εντολή ανακτά βιβλία γραμμένα από τον συγγραφέα "J.K. Rowling";**
    α. `db.books.find({author: "J.K. Rowling"})`
    β. `db.books.find({authors: "J.K. Rowling"})`
    γ. `db.books.find({author: {$eq: "J.K. Rowling"}})`
    δ. Όλα τα παραπάνω, αν το πεδίο είναι `author`.
*   **Απάντηση:** δ
*   **Εξήγηση:** Οι επιλογές α και γ είναι σωστές για το πεδίο `author`. Η β είναι σωστή αν το πεδίο είναι `authors` (λίστα), αλλά η ερώτηση αναφέρεται σε 'συγγραφέα', υπονοώντας ένα πεδίο συγγραφέα (όπως στο παράδειγμα της άσκησης), όχι απαραίτητα λίστα. Αλλά αν υποθέσουμε ότι μπορεί να υπάρχει είτε `author` είτε `authors` τότε η δ καλύπτει τις περιπτώσεις όπου το `author` είναι ένα απλό String (όπως και στην αρχική άσκηση).

**5. Ποια εντολή ανακτά βιβλία που έχουν βαθμολογία (rating) ίση με 4.5;**
    α. `db.books.find({rating: 4.5})`
    β. `db.books.find({"rating": 4.5})`
    γ. `db.books.find({rating: {$eq: 4.5}})`
    δ. Όλα τα παραπάνω.
*   **Απάντηση:** δ
*   **Εξήγηση:** Όλες οι επιλογές είναι συντακτικά σωστές και κάνουν το ίδιο πράγμα. Η α και β είναι πιο απλές, η γ είναι πιο ρητή.

**6. Ποια εντολή βρίσκει όλα τα βιβλία του είδους (genre) "Science Fiction";**
    α. `db.books.find({genre: "Science Fiction"})`
    β. `db.books.find({"genre": "Science Fiction"})`
    γ. `db.books.find({genres: "Science Fiction"})` (αν το `genres` είναι λίστα)
    δ. Όλα τα παραπάνω, ανάλογα με το πεδίο.
*   **Απάντηση:** δ
*   **Εξήγηση:** Οι επιλογές α και β είναι ίδιες και σωστές για το πεδίο `genre`. Η επιλογή γ είναι σωστή αν το πεδίο είναι `genres` (λίστα), καθώς η MongoDB μπορεί να αντιστοιχίσει ένα στοιχείο σε έναν πίνακα. Δεδομένου ότι η ερώτηση δεν καθορίζει αν το `genre` είναι ένα πεδίο String ή ένα πεδίο Array, όλες είναι δυνητικά σωστές.

**7. Ποια εντολή ανακτά βιβλία που είναι `inStock` (διαθέσιμα) και έχουν πάνω από 300 σελίδες;**
    α. `db.books.find({inStock: true, pages: {$gt: 300}})`
    β. `db.books.find({$and: [{inStock: true}, {pages: {$gt: 300}}]})`
    γ. Και οι δύο α και β είναι σωστές.
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** γ
*   **Εξήγηση:** Όταν έχουμε πολλαπλές συνθήκες στο ίδιο έγγραφο, η MongoDB τις αντιμετωπίζει ως λογικό AND by default (επιλογή α). Η χρήση του `$and` (επιλογή β) είναι ρητή, αλλά δίνει το ίδιο αποτέλεσμα.

**8. Ποια εντολή ανακτά βιβλία που έχουν τίτλο "Dune" *ή* γράφτηκαν από τον "Frank Herbert";**
    α. `db.books.find({title: "Dune", author: "Frank Herbert"})`
    β. `db.books.find({$or: [{title: "Dune"}, {author: "Frank Herbert"}]})`
    γ. `db.books.find({title: "Dune" || author: "Frank Herbert"})`
    δ. `db.books.find({title: "Dune"}, {author: "Frank Herbert"})`
*   **Απάντηση:** β
*   **Εξήγηση:** Ο τελεστής `$or` χρησιμοποιείται για να καθορίσει λογικό Ή μεταξύ διαφορετικών συνθηκών. Η α είναι για λογικό ΚΑΙ, η γ είναι λάθος σύνταξη JavaScript και η δ είναι λάθος χρήση της find (τα δύο arguments είναι query και projection).

**9. Ποια εντολή ανακτά βιβλία που εκδόθηκαν πριν από το έτος 2000;**
    α. `db.books.find({pyear: {$lt: 2000}})`
    β. `db.books.find({pyear: < 2000})`
    γ. `db.books.find({pyear: {$lte: 1999}})`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το `$lt` σημαίνει "λιγότερο από", οπότε `$lt: 2000` σημαίνει 1999 και κάτω. Το `$lte: 1999` σημαίνει "λιγότερο ή ίσο με 1999", που είναι το ίδιο αποτέλεσμα. Η β είναι λάθος σύνταξη.

**10. Ποια εντολή ανακτά βιβλία με βαθμολογία (rating) μεταξύ 3.5 και 4.5 (συμπεριλαμβανομένων);**
    α. `db.books.find({rating: {$gte: 3.5, $lte: 4.5}})`
    β. `db.books.find({rating: {$between: [3.5, 4.5]}})`
    γ. `db.books.find({rating: [3.5, 4.5]})`
    δ. `db.books.find({$and: [{rating: {$gte: 3.5}}, {rating: {$lte: 4.5}}]})`
*   **Απάντηση:** α
*   **Εξήγηση:** Μπορούμε να ορίσουμε εύρος τιμών για το ίδιο πεδίο μέσα στο ίδιο query document. Η δ είναι επίσης σωστή αλλά πιο εκτενής, ενώ η α είναι πιο συμπυκνωμένη. Το β και γ είναι λάθος σύνταξη ή τελεστές.

---

**Ομάδα 2: Ερωτήσεις με Προβολή (Projection) και Μετρητές (`count()`)**

**11. Ποια εντολή ανακτά μόνο τους τίτλους όλων των βιβλίων, αποκλείοντας το `_id`;**
    α. `db.books.find({}, {title: 1, _id: 0})`
    β. `db.books.find({title: 1, _id: 0})`
    γ. `db.books.find({}, {title: 1})`
    δ. `db.books.find({_id: 0, title: 1})`
*   **Απάντηση:** α
*   **Εξήγηση:** Το πρώτο `,{}` ορίζει το κριτήριο αναζήτησης (όλα τα έγγραφα) και το δεύτερο `{}` ορίζει την προβολή. Το `_id: 0` είναι απαραίτητο για να αποκλειστεί το `_id`, το οποίο επιστρέφεται by default.

**12. Ποια εντολή ανακτά τον τίτλο και τον συγγραφέα για βιβλία έκδοσης 2020;**
    α. `db.books.find({pyear: 2020}, {title: 1, author: 1})`
    β. `db.books.find({pyear: 2020}, {title: 1, author: 1, _id: 0})`
    γ. Και οι δύο α και β είναι σωστές.
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** γ
*   **Εξήγηση:** Η επιλογή α επιστρέφει το `_id` μαζί με τον τίτλο και τον συγγραφέα. Η επιλογή β αποκλείει το `_id`. Και οι δύο είναι "σωστές" ανάλογα με το αν επιθυμούμε ή όχι το `_id`, αλλά αρκεί να επιστραφούν αυτά που ζητούνται. Συνήθως ζητείται ρητά η εξαίρεση του `_id`. Επειδή η ερώτηση δεν αναφέρει τίποτα για το `_id`, και οι δύο μορφές είναι αποδεκτές για την ανάκτηση των ζητούμενων πεδίων.

**13. Ποια εντολή επιστρέφει το σύνολο των βιβλίων στη συλλογή;**
    α. `db.books.countDocuments({})`
    β. `db.books.find({}).count()`
    γ. Και οι δύο α και β είναι σωστές.
    δ. `db.books.length()`
*   **Απάντηση:** γ
*   **Εξήγηση:** Το `countDocuments({})` είναι η προτεινόμενη μέθοδος για την καταμέτρηση εγγράφων σε τρέχουσες εκδόσεις της MongoDB. Το `find({}).count()` είναι μια παλαιότερη μέθοδος που εξακολουθεί να λειτουργεί.

**14. Ποια εντολή μετράει τα βιβλία του είδους "Fantasy";**
    α. `db.books.countDocuments({genre: "Fantasy"})`
    β. `db.books.find({genre: "Fantasy"}).count()`
    γ. `db.books.count({genre: "Fantasy"})`
    δ. Όλα τα παραπάνω.
*   **Απάντηση:** δ
*   **Εξήγηση:** Όλες οι επιλογές είναι έγκυροι τρόποι να μετρήσουμε έγγραφα με ένα συγκεκριμένο κριτήριο. Το `count()` (χωρίς `Documents`) είναι deprecated αλλά λειτουργεί.

**15. Ποια εντολή μετράει τα βιβλία που έχουν βαθμολογία πάνω από 4.0 και είναι διαθέσιμα (inStock);**
    α. `db.books.countDocuments({rating: {$gt: 4.0}, inStock: true})`
    β. `db.books.find({rating: {$gt: 4.0}, inStock: true}).count()`
    γ. `db.books.count({rating: {$gt: 4.0}, inStock: true})`
    δ. Όλα τα παραπάνω.
*   **Απάντηση:** δ
*   **Εξήγηση:** Όλες οι επιλογές εκτελούν την καταμέτρηση με τα δεδομένα κριτήρια.

---

**Ομάδα 3: Ερωτήσεις Ταξινόμησης (`sort()`) και Περιορισμού (`limit()`, `skip()`)**

**16. Ποια εντολή ανακτά όλα τα βιβλία, ταξινομημένα κατά τίτλο αύξουσα;**
    α. `db.books.find().sort({title: 1})`
    β. `db.books.find().sort({title: "asc"})`
    γ. `db.books.sort({title: 1}).find()`
    δ. `db.books.find().orderBy({title: 1})`
*   **Απάντηση:** α
*   **Εξήγηση:** Το `sort()` εφαρμόζεται μετά το `find()`. Η τιμή 1 υποδηλώνει αύξουσα ταξινόμηση.

**17. Ποια εντολή ανακτά τα βιβλία του "Stephen King", ταξινομημένα κατά έτος έκδοσης φθίνουσα;**
    α. `db.books.find({author: "Stephen King"}).sort({pyear: -1})`
    β. `db.books.find({author: "Stephen King"}).sort({pyear: "desc"})`
    γ. `db.books.sort({pyear: -1}).find({author: "Stephen King"})`
    δ. `db.books.find({author: "Stephen King"}).sortBy({pyear: -1})`
*   **Απάντηση:** α
*   **Εξήγηση:** Η τιμή -1 υποδηλώνει φθίνουσα ταξινόμηση. Η σειρά `find().sort()` είναι η σωστή.

**18. Ποια εντολή επιστρέφει τα 10 πιο πρόσφατα βιβλία;**
    α. `db.books.find().sort({pyear: -1}).limit(10)`
    β. `db.books.find().limit(10).sort({pyear: -1})`
    γ. `db.books.find().top(10).sortBy({pyear: -1})`
    δ. `db.books.find().order_by({pyear: -1}).take(10)`
*   **Απάντηση:** α
*   **Εξήγηση:** Είναι σημαντικό η ταξινόμηση να γίνει *πριν* τον περιορισμό του πλήθους (`limit`), ώστε να επιλέξουμε τα "σωστά" 10 βιβλία.

**19. Ποια εντολή επιστρέφει τα βιβλία 11 έως 20 (δηλαδή την δεύτερη σελίδα, αν κάθε σελίδα έχει 10 βιβλία) ταξινομημένα κατά τίτλο;**
    α. `db.books.find().sort({title: 1}).skip(10).limit(10)`
    β. `db.books.find().skip(10).limit(10).sort({title: 1})`
    γ. `db.books.find().limit(10).skip(10).sort({title: 1})`
    δ. `db.books.find().skip(11).limit(10).sort({title: 1})`
*   **Απάντηση:** α
*   **Εξήγηση:** Η σωστή σειρά είναι `sort()` (πρώτα για να έχει νόημα η σελιδοποίηση) μετά `skip()` (παράλειψη των πρώτων Ν εγγράφων) και μετά `limit()` (περιορισμός των επόμενων Μ εγγράφων).

**20. Ποια εντολή βρίσκει τα 5 βιβλία με τις περισσότερες σελίδες;**
    α. `db.books.find().sort({pages: -1}).limit(5)`
    β. `db.books.find().limit(5).sort({pages: -1})`
    γ. `db.books.find().top(5, {pages: -1})`
    δ. `db.books.find({pages: {$gt: 0}}).sort({pages: -1}).limit(5)`
*   **Απάντηση:** α
*   **Εξήγηση:** Για να βρούμε τα "περισσότερες σελίδες", πρέπει πρώτα να ταξινομήσουμε τα βιβλία κατά τον αριθμό των σελίδων σε φθίνουσα σειρά (`pages: -1`) και μετά να περιορίσουμε το αποτέλεσμα στα πρώτα 5.

---

**Ομάδα 4: Ερωτήσεις με Προχωρημένους Τελεστές Αναζήτησης (`$in`, `$nin`, `$ne`, `$exists`, `$type`, `$regex`, `$all`, `$size`)**

**21. Ποια εντολή ανακτά βιβλία των συγγραφέων "Jane Austen" ή "Mark Twain";**
    α. `db.books.find({author: "Jane Austen" || "Mark Twain"})`
    β. `db.books.find({author: {$in: ["Jane Austen", "Mark Twain"]}})`
    γ. `db.books.find({$or: [{author: "Jane Austen"}, {author: "Mark Twain"}]})`
    δ. Και οι δύο β και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Ο τελεστής `$in` είναι ο βέλτιστος για την αναζήτηση αντιστοίχισης οποιασδήποτε τιμής σε μια λίστα. Η χρήση του `$or` είναι επίσης σωστή και λειτουργικά ισοδύναμη.

**22. Ποια εντολή ανακτά βιβλία που *δεν* είναι γραμμένα στη γλώσσα "English";**
    α. `db.books.find({language: {$ne: "English"}})`
    β. `db.books.find({language: "!English"})`
    γ. `db.books.find({language: {$nin: ["English"]}})`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Ο τελεστής `$ne` (not equal) και ο `$nin` (not in) με μία μόνο τιμή είναι λειτουργικά ισοδύναμοι για αυτή την περίπτωση.

**23. Ποια εντολή ανακτά βιβλία που *δεν* έχουν βαθμολογία (rating) καθόλου;**
    α. `db.books.find({rating: null})`
    β. `db.books.find({rating: {$exists: false}})`
    γ. `db.books.find({rating: {$not: true}})`
    δ. `db.books.find({rating: undefined})`
*   **Απάντηση:** β
*   **Εξήγηση:** Ο τελεστής `$exists: false` είναι ο σωστός τρόπος για να ελέγξουμε αν ένα πεδίο δεν υπάρχει καθόλου στο έγγραφο.

**24. Ποια εντολή ανακτά βιβλία που το πεδίο `pages` είναι τύπου αριθμού;**
    α. `db.books.find({pages: {$type: "number"}})`
    β. `db.books.find({pages: {$type: 1}})`
    γ. `db.books.find({pages: "number"})`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Ο τελεστής `$type` μπορεί να δεχτεί είτε το string alias ("number") είτε τον BSON αριθμό τύπου (1 για Double, 16 για 32-bit Integer, 18 για 64-bit Integer, αλλά "number" τα καλύπτει όλα αυτά).

**25. Ποια εντολή ανακτά βιβλία των οποίων ο τίτλος ξεκινάει με τη λέξη "The";**
    α. `db.books.find({title: /^The/})`
    β. `db.books.find({title: {$regex: "^The"}})`
    γ. `db.books.find({title: /The.*/})`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η α είναι η σύντομη σύνταξη για regular expressions. Η β είναι η πιο ρητή χρήση του `$regex` operator. Και οι δύο κάνουν το ίδιο πράγμα. Η γ θα έβρισκε και λέξεις όπως "Theorem", ενώ η α και β ψάχνουν μόνο στην αρχή της λέξης.

**26. Ποια εντολή ανακτά βιβλία που έχουν ακριβώς 3 λέξεις-κλειδιά (keywords);**
    α. `db.books.find({keywords: {$size: 3}})`
    β. `db.books.find({keywords: {$length: 3}})`
    γ. `db.books.find({keywords: {$count: 3}})`
    δ. `db.books.find({keywords: {$num: 3}})`
*   **Απάντηση:** α
*   **Εξήγηση:** Ο τελεστής `$size` χρησιμοποιείται για να βρούμε arrays με συγκεκριμένο πλήθος στοιχείων.

**27. Ποια εντολή ανακτά βιβλία που ανήκουν *και* στα είδη "Fantasy" *και* "Adventure" (χρησιμοποιώντας το πεδίο `genres`);**
    α. `db.books.find({genres: "Fantasy", genres: "Adventure"})`
    β. `db.books.find({genres: {$all: ["Fantasy", "Adventure"]}})`
    γ. `db.books.find({$and: [{genres: "Fantasy"}, {genres: "Adventure"}]})`
    δ. Και οι δύο β και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Ο τελεστής `$all` είναι ο πιο κατάλληλος για να ελέγξει αν ένα array περιέχει όλα τα συγκεκριμένα στοιχεία. Η χρήση του `$and` είναι επίσης σωστή καθώς η MongoDB βρίσκει στοιχεία εντός array.

**28. Ποια εντολή ανακτά βιβλία των οποίων ο τίτλος *δεν* τελειώνει σε "s" (case-insensitive);**
    α. `db.books.find({title: {$not: /s$/i}})`
    β. `db.books.find({title: /[^s]$/i})`
    γ. `db.books.find({title: {$regex: "s$", $options: "i", $not: true}})`
    δ. `db.books.find({title: {$ne: /s$/i}})`
*   **Απάντηση:** α
*   **Εξήγηση:** Ο τελεστής `$not` μπορεί να εφαρμοστεί σε μια έκφραση, συμπεριλαμβανομένων των regular expressions. Το `/s$/i` σημαίνει "τελειώνει σε s, ανεξαρτήτως πεζών/κεφαλαίων".

**29. Ποια εντολή βρίσκει βιβλία χωρίς συγγραφέα ή χωρίς τίτλο;**
    α. `db.books.find({$or: [{author: {$exists: false}}, {title: {$exists: false}}]})`
    β. `db.books.find({author: null, title: null})`
    γ. `db.books.find({$or: [{author: ""}, {title: ""}]})`
    δ. `db.books.find({$and: [{author: {$exists: false}}, {title: {$exists: false}}]})`
*   **Απάντηση:** α
*   **Εξήγηση:** Χρησιμοποιούμε `$or` για τη λογική OR και `$exists: false` για να ελέγξουμε την απουσία πεδίου.

**30. Ποια εντολή ανακτά βιβλία που έχουν rating, αλλά δεν έχουν εκδότη (publisher);**
    α. `db.books.find({rating: {$exists: true}, publisher: {$exists: false}})`
    β. `db.books.find({rating: {$exists: true}, publisher: null})`
    γ. `db.books.find({rating: {$ne: null}, publisher: {$eq: null}})`
    δ. `db.books.find({rating: {$exists: 1}, publisher: {$exists: 0}})`
*   **Απάντηση:** α
*   **Εξήγηση:** Συνδυασμός δύο `$exists` ελέγχων σε ένα query document, το οποίο λειτουργεί ως λογικό AND. Το `rating: {$exists: true}` σημαίνει "το πεδίο rating υπάρχει", ενώ `publisher: {$exists: false}` σημαίνει "το πεδίο publisher δεν υπάρχει".

---

**Ομάδα 5: Ερωτήσεις για Εισαγωγή Δεδομένων (`insertOne`, `insertMany`)**

**31. Ποια εντολή εισάγει ένα μόνο νέο βιβλίο στη συλλογή `books`;**
    α. `db.books.insert({title: "New Book", author: "New Author"})`
    β. `db.books.insertOne({title: "New Book", author: "New Author"})`
    γ. `db.books.save({title: "New Book", author: "New Author"})`
    δ. Και οι δύο β και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η `insertOne()` είναι η προτεινόμενη μέθοδος. Η `save()` είναι μια παλαιότερη μέθοδος που εισάγει αν δεν υπάρχει `_id` ή ενημερώνει αν υπάρχει `_id`.

**32. Ποια εντολή εισάγει πολλά βιβλία ταυτόχρονα στη συλλογή `books`;**
    α. `db.books.insertMany([{...}, {...}])`
    β. `db.books.insert([{...}, {...}])`
    γ. `db.books.bulkInsert([{...}, {...}])`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η `insertMany()` είναι η προτεινόμενη μέθοδος. Η `insert()` μπορεί να δεχτεί και array από documents για πολλαπλές εισαγωγές (είναι παλαιότερη μέθοδος).

**33. Τι συμβαίνει αν επιχειρήσετε να εισάγετε ένα document με ένα `_id` που υπάρχει ήδη στη συλλογή;**
    α. Εισάγεται ένα νέο document με διαφορετικό `_id`.
    β. Προκαλείται σφάλμα `DuplicateKeyException`.
    γ. Το υπάρχον document ενημερώνεται.
    δ. Αγνοείται η εισαγωγή.
*   **Απάντηση:** β
*   **Εξήγηση:** Το `_id` πρέπει να είναι μοναδικό σε κάθε συλλογή. Αν προσπαθήσετε να εισάγετε ένα document με υπάρχον `_id`, θα προκληθεί σφάλμα.

---

**Ομάδα 6: Ερωτήσεις για Ενημέρωση Δεδομένων (`updateOne`, `updateMany`, `$set`, `$inc`, `$unset`, Array Operators)**

**34. Ποια εντολή αλλάζει τον τίτλο του βιβλίου με `_id: ObjectId("...")` σε "The New Title";**
    α. `db.books.updateOne({_id: ObjectId("...")}, {title: "The New Title"})`
    β. `db.books.update({_id: ObjectId("...")}, {$set: {title: "The New Title"}})`
    γ. `db.books.updateMany({_id: ObjectId("...")}, {$set: {title: "The New Title"}})`
    δ. Όλα τα παραπάνω, αν το `_id` είναι μοναδικό.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η β είναι η σωστή σύνταξη με την παλαιότερη `update`. Η `updateOne` (νεότερη) είναι πιο άμεση για ένα μόνο document. Η `updateMany` θα δούλευε επίσης αλλά υποδηλώνει αναζήτηση για πολλά document ενώ το `_id` είναι μοναδικό.

**35. Ποια εντολή ενημερώνει *όλα* τα βιβλία του "J.R.R. Tolkien" ώστε να έχουν βαθμολογία `5.0`;**
    α. `db.books.updateMany({author: "J.R.R. Tolkien"}, {$set: {rating: 5.0}})`
    β. `db.books.updateOne({author: "J.R.R. Tolkien"}, {$set: {rating: 5.0}})`
    γ. `db.books.update({author: "J.R.R. Tolkien"}, {rating: 5.0})`
    δ. `db.books.updateAll({author: "J.R.R. Tolkien"}, {$set: {rating: 5.0}})`
*   **Απάντηση:** α
*   **Εξήγηση:** Το `updateMany` είναι η σωστή μέθοδος για την ενημέρωση πολλαπλών εγγράφων που ταιριάζουν με ένα κριτήριο. Το `$set` χρησιμοποιείται για να ορίσει μια τιμή σε ένα πεδίο.

**36. Ποια εντολή αυξάνει τον αριθμό των σελίδων ενός βιβλίου με `_id: ObjectId("...")` κατά 10;**
    α. `db.books.updateOne({_id: ObjectId("...")}, {pages: {$add: 10}})`
    β. `db.books.updateOne({_id: ObjectId("...")}, {$inc: {pages: 10}})`
    γ. `db.books.updateOne({_id: ObjectId("...")}, {$set: {pages: {$sum: "$pages" + 10}}})`
    δ. `db.books.updateOne({_id: ObjectId("...")}, {pages: 10})`
*   **Απάντηση:** β
*   **Εξήγηση:** Ο τελεστής `$inc` χρησιμοποιείται για να αυξήσει (ή να μειώσει με αρνητικό αριθμό) την τιμή ενός αριθμητικού πεδίου.

**37. Ποια εντολή αφαιρεί το πεδίο `rating` από όλα τα βιβλία που εκδόθηκαν πριν το 1950;**
    α. `db.books.updateMany({pyear: {$lt: 1950}}, {$unset: {rating: ""}})`
    β. `db.books.updateMany({pyear: {$lt: 1950}}, {$unset: {rating: null}})`
    γ. `db.books.updateMany({pyear: {$lt: 1950}}, {rating: {$remove: true}})`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Ο τελεστής `$unset` χρησιμοποιείται για την αφαίρεση ενός πεδίου από ένα έγγραφο. Η τιμή που του δίνεται (εδώ `""` ή `null`) δεν έχει σημασία, αρκεί να υπάρχει.

**38. Ποια εντολή προσθέτει το keyword "bestseller" σε ένα βιβλίο με `_id: ObjectId("...")` εάν δεν υπάρχει ήδη;**
    α. `db.books.updateOne({_id: ObjectId("...")}, {$push: {keywords: "bestseller"}})`
    β. `db.books.updateOne({_id: ObjectId("...")}, {$addToSet: {keywords: "bestseller"}})`
    γ. `db.books.updateOne({_id: ObjectId("...")}, {$add: {keywords: "bestseller"}})`
    δ. `db.books.updateOne({_id: ObjectId("...")}, {$append: {keywords: "bestseller"}})`
*   **Απάντηση:** β
*   **Εξήγηση:** Ο τελεστής `$addToSet` προσθέτει ένα στοιχείο σε ένα array μόνο αν δεν υπάρχει ήδη. Το `$push` θα προσθέσει το στοιχείο ακόμα κι αν υπάρχει διπλότυπο.

**39. Ποια εντολή αφαιρεί το keyword "fiction" από το array `keywords` για όλα τα βιβλία που το περιέχουν;**
    α. `db.books.updateMany({keywords: "fiction"}, {$pull: {keywords: "fiction"}})`
    β. `db.books.updateMany({keywords: "fiction"}, {$unset: {keywords: "fiction"}})`
    γ. `db.books.updateMany({keywords: {$all: ["fiction"]}}, {$remove: {keywords: "fiction"}})`
    δ. `db.books.updateMany({keywords: "fiction"}, {$pop: {keywords: "fiction"}})`
*   **Απάντηση:** α
*   **Εξήγηση:** Ο τελεστής `$pull` αφαιρεί *όλες* τις εμφανίσεις μιας συγκεκριμένης τιμής από ένα array.

**40. Ποια εντολή αλλάζει το πεδίο `genre` από "Science Fiction" σε "Sci-Fi" για ένα συγκεκριμένο βιβλίο, και αν δεν υπάρχει `genre`, το προσθέτει ως "Sci-Fi";**
    α. `db.books.updateOne({_id: ObjectId("...")}, {$set: {genre: "Sci-Fi"}})`
    β. `db.books.updateOne({_id: ObjectId("...")}, {$set: {genre: "Sci-Fi"}}, {upsert: true})`
    γ. `db.books.updateOne({_id: ObjectId("...")}, {genre: "Sci-Fi"})`
    δ. `db.books.upsert({_id: ObjectId("...")}, {$set: {genre: "Sci-Fi"}})`
*   **Απάντηση:** α
*   **Εξήγηση:** Το `$set` λειτουργεί και για να προσθέσει ένα πεδίο αν δεν υπάρχει. Η επιλογή β περιέχει το `upsert: true` το οποίο έχει νόημα αν το document δεν υπάρχει *γενικά* και όχι μόνο το πεδίο. Η ερώτηση είναι "αν δεν υπάρχει genre, το προσθέτει", κάτι που το `$set` το κάνει oh so, αντικαθιστά ή προσθέτει.

---

**Ομάδα 7: Ερωτήσεις για Διαγραφή Δεδομένων (`deleteOne`, `deleteMany`)**

**41. Ποια εντολή διαγράφει το βιβλίο με τίτλο "A Forgotten Tale";**
    α. `db.books.deleteOne({title: "A Forgotten Tale"})`
    β. `db.books.delete({title: "A Forgotten Tale"})`
    γ. `db.books.remove({title: "A Forgotten Tale"})`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η `deleteOne()` είναι η προτεινόμενη μέθοδος. Η `remove()` είναι παλαιότερη και λειτουργικά ισοδύναμη όταν θέλετε να διαγράψετε ένα μόνο document που ταιριάζει.

**42. Ποια εντολή διαγράφει όλα τα βιβλία που εκδόθηκαν πριν από το 1900;**
    α. `db.books.deleteMany({pyear: {$lt: 1900}})`
    β. `db.books.remove({pyear: {$lt: 1900}})`
    γ. `db.books.deleteAll({pyear: {$lt: 1900}})`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η `deleteMany()` είναι η προτεινόμενη μέθοδος. Η `remove()` (χωρίς το `justOne: true`) είναι παλαιότερη και λειτουργικά ισοδύναμη για πολλαπλές διαγραφές.

**43. Ποια εντολή διαγράφει *όλα* τα documents από τη συλλογή `books`;**
    α. `db.books.deleteMany({})`
    β. `db.books.remove({})`
    γ. `db.books.drop()`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το `deleteMany({})` και το `remove({})` (που λειτουργεί χωρίς όρια όταν είναι άδειο το query) είναι σωστές. Το `drop()` διαγράφει ολόκληρη τη *συλλογή*, όχι μόνο τα documents, και είναι πιο ριζική ενέργεια.

---

**Ομάδα 8: Aggregation Framework - `$project`, `$unwind`**

**44. Ποια εντολή χρησιμοποιεί το aggregation pipeline για να επιστρέψει μόνο τον τίτλο και τον συγγραφέα κάθε βιβλίου;**
    α. `db.books.aggregate([{$project:{title:1, author:1, _id:0}}])`
    β. `db.books.aggregate([{$project:{_id:0, title:1, author:1}}])`
    γ. `db.books.aggregate([{$project:{title:"$title", author:"$author"}}])`
    δ. Όλα τα παραπάνω είναι σωστά.
*   **Απάντηση:** δ
*   **Εξήγηση:** Ο `$project` επιλέγει, επανασχεδιάζει ή αποκλείει πεδία. Όλες οι επιλογές οδηγούν στο ίδιο αποτέλεσμα. Η α και β είναι η πιο απλή σύνταξη, ενώ η γ χρησιμοποιεί ρητά τις ονομασίες των πεδίων.

**45. Έχοντας ένα πεδίο `authors` που είναι πίνακας (π.χ., `["Author1", "Author2"]`), ποια εντολή θα δημιουργήσει ένα ξεχωριστό document για κάθε συγγραφέα, επιτρέποντας την επεξεργασία ανά μεμονωμένο συγγραφέα;**
    α. `db.books.aggregate([{$expand: "$authors"}] )`
    β. `db.books.aggregate([{$unwind: "$authors"}] )`
    γ. `db.books.aggregate([{$separate: "$authors"}] )`
    δ. `db.books.aggregate([{$flatten: "$authors"}] )`
*   **Απάντηση:** β
*   **Εξήγηση:** Το στάδιο `$unwind` deconstructs ένα array field από τα input documents για να παραγάγει ένα output document για κάθε στοιχείο του array.

**46. Ποια εντολή θα επέστρεφε για κάθε βιβλίο το τίτλο και την πρώτη λέξη-κλειδί (keyword) αν το πεδίο `keywords` είναι array;**
    α. `db.books.aggregate([{$project: {title: 1, firstKeyword: {$arrayElemAt: ["$keywords", 0]}}}])`
    β. `db.books.aggregate([{$project: {title: 1, firstKeyword: {$first: "$keywords"}}}] )`
    γ. `db.books.aggregate([{$project: {title: 1, firstKeyword: "$keywords[0]"}}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Ο `$arrayElemAt` operator είναι ο σωστός τρόπος για να ανακτήσουμε ένα στοιχείο σε μια συγκεκριμένη θέση (index) μέσα σε ένα array.

**47. Ποια εντολή θα επέστρεφε ένα νέο πεδίο `totalPages` που να είναι το `pages` πολλαπλασιασμένο επί 2 για όλα τα βιβλία;**
    α. `db.books.aggregate([{$project: {title: 1, totalPages: {$multiply: ["$pages", 2]}}}])`
    β. `db.books.aggregate([{$project: {title: 1, totalPages: "$pages" * 2}}])`
    γ. `db.books.aggregate([{$addFields: {totalPages: {$multiply: ["$pages", 2]}}}])`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το `$project` μπορεί να χρησιμοποιηθεί για να δημιουργήσει νέα πεδία με υπολογιζόμενες τιμές. Το `$addFields` είναι ένα ειδικό στάδιο για να προσθέσει νέα πεδία διατηρώντας τα υπάρχοντα. Και τα δύο λειτουργούν.

**48. Ποια εντολή θα εμφανίσει τον τίτλο, τον συγγραφέα και το έτος έκδοσης, αλλά το `_id` θα το ονομάσει `bookId` μέσα στο aggregation pipeline;**
    α. `db.books.aggregate([{$project: {_id: "$_id", bookId: "$_id", title: 1, author: 1, pyear: 1}}])`
    β. `db.books.aggregate([{$project: {bookId: "$_id", title: 1, author: 1, pyear: 1, _id: 0}}])`
    γ. `db.books.aggregate([{$addFields: {bookId: "$_id"}}, {$project: {_id: 0}}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** β
*   **Εξήγηση:** Μέσα στο `$project`, μπορούμε να ορίσουμε ένα νέο όνομα πεδίου και να του αντιστοιχίσουμε την τιμή ενός υπάρχοντος πεδίου. Ταυτόχρονα, το `_id: 0` αφαιρεί το αρχικό `_id`.

**49. Ποια εντολή ανακτά τους τίτλους των βιβλίων του Stephen King και του Dean Koontz χρησιμοποιώντας aggregation;**
    α. `db.books.aggregate([{$match: {$or: [{author: "Stephen King"}, {author: "Dean Koontz"}]}}, {$project: {title: 1, _id: 0}}])`
    β. `db.books.aggregate([{$project: {title: 1, _id: 0}}, {$match: {$or: [{author: "Stephen King"}, {author: "Dean Koontz"}]}}])`
    γ. `db.books.aggregate([{$match: {author: {$in: ["Stephen King", "Dean Koontz"]}}}, {$project: {title: 1, _id: 0}}])`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το `$match` πρέπει να γίνει πριν το `$project` αν το πεδίο (author) φιλτραρίσματος πρόκειται να "εξαφανιστεί" από το `$project` ή αν θέλουμε να φιλτράρουμε τον αρχικό όγκο δεδομένων νωρίς στην pipeline. Το α και γ είναι σωστές συντακτικά και λειτουργικά.

**50. Ποια εντολή βρίσκει τον τίτλο του βιβλίου με το υψηλότερο rating;**
    α. `db.books.find().sort({rating: -1}).limit(1).project({title:1, _id:0})`
    β. `db.books.aggregate([{$sort: {rating: -1}}, {$limit: 1}, {$project: {title: 1, _id: 0}}])`
    γ. Και οι δύο α και β είναι σωστές.
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** γ
*   **Εξήγηση:** Και οι δύο προσεγγίσεις είναι σωστές. Η α χρησιμοποιεί τη μεθόδο `find` με chaining `sort` και `limit` ενώ η β χρησιμοποιεί το aggregation pipeline με αντίστοιχα στάδια.

---

**Ομάδα 9: Aggregation Framework - `$group` με Advanced Accumulators**

**51. Ποια εντολή βρίσκει το μέσο όρο των σελίδων (`pages`) για όλα τα βιβλία;**
    α. `db.books.aggregate([{$group:{_id:null, avgPages:{$avg:"$pages"}}}])`
    β. `db.books.aggregate([{$group:{avgPages:{$avg:"$pages"}}}] )`
    γ. `db.books.find().avg("pages")`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Για να υπολογίσουμε έναν συνολικό μέσο όρο για όλη τη συλλογή, ομαδοποιούμε με `_id: null`. Και οι δύο α και β επιλογές επιτυγχάνουν το ίδιο, η α απλώς ορίζει ρητά το `_id:null`.

**52. Ποια εντολή βρίσκει τη μέγιστη βαθμολογία (rating) που έχει δοθεί σε οποιοδήποτε βιβλίο;**
    α. `db.books.aggregate([{$group:{_id:null, maxRating:{$max:"$rating"}}}])`
    β. `db.books.find().sort({rating:-1}).limit(1).project({rating:1,_id:0})`
    γ. `db.books.aggregate([{$group:{_id:"max_rating", maxRating:{$max:"$rating"}}}])`
    δ. Όλα τα παραπάνω.
*   **Απάντηση:** δ
*   **Εξήγηση:** Όλες οι επιλογές μπορούν να χρησιμοποιηθούν για να βρουν τη μέγιστη βαθμολογία. Η α και γ χρησιμοποιούν `$max` accumulator, η β βρίσκει το document με τη μέγιστη τιμή και επιστρέφει τη βαθμολογία του.

**53. Ποια εντολή υπολογίζει τον συνολικό αριθμό σελίδων (`pages`) για όλα τα βιβλία του κάθε συγγραφέα;**
    α. `db.books.aggregate([{$group:{_id:"$author", totalPages:{$sum:"$pages"}}}])`
    β. `db.books.aggregate([{$group:{_id:"$author", "sumPages":{$sum:"$pages"}}}] )`
    γ. Και οι δύο α και β είναι σωστές.
    δ. `db.books.find().sum({pages: "$author"})`
*   **Απάντηση:** γ
*   **Εξήγηση:** Χρησιμοποιείται το `$group` με το `_id` να είναι το πεδίο `author` και ο accumulator `$sum` στο πεδίο `pages`. Τα ονόματα των νέων πεδίων (totalPages ή sumPages) είναι επιλογές.

**54. Ποια εντολή βρίσκει τον συγγραφέα με τον χαμηλότερο μέσο όρο βαθμολογίας (rating);**
    α. `db.books.aggregate([{$group:{_id:"$author", avgRating:{$avg:"$rating"}}}, {$sort:{avgRating:1}}, {$limit:1}])`
    β. `db.books.aggregate([{$group:{_id:"$author", avgRating:{$avg:"$rating"}}}, {$sort:{avgRating:-1}}, {$limit:1}])`
    γ. `db.books.aggregate([{$project:{_id:"$author", avgRating:{$avg:"$rating"}}}, {$sort:{avgRating:1}}, {$limit:1}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Πρώτα ομαδοποιούμε ανά συγγραφέα και υπολογίζουμε το μέσο όρο. Μετά ταξινομούμε αυτά τα αποτελέσματα αύξουσα (`1`) κατά τον μέσο όρο για να βρούμε τον χαμηλότερο, και τέλος περιορίζουμε στο πρώτο αποτέλεσμα.

**55. Ποια εντολή βρίσκει τον εκδότη (`publisher`) με τα περισσότερα βιβλία που εκδόθηκαν μετά το 2000;**
    α. `db.books.aggregate([{$match:{pyear:{$gt:2000}}}, {$group:{_id:"$publisher", bookCount:{$sum:1}}}, {$sort:{bookCount:-1}}, {$limit:1}])`
    β. `db.books.aggregate([{$group:{_id:"$publisher", bookCount:{$sum:1}}}, {$match:{pyear:{$gt:2000}}}, {$sort:{bookCount:-1}}, {$limit:1}])`
    γ. `db.books.aggregate([{$sort:{pyear:-1}}, {$match:{pyear:{$gt:2000}}}, {$group:{_id:"$publisher", bookCount:{$sum:1}}}, {$limit:1}])`
    δ. `db.books.aggregate([{$group:{_id:"$publisher", bookCount:{$sum:1}}}, {$sort:{bookCount:-1}}, {$limit:1}], {$match:{pyear:{$gt:2000}}})`
*   **Απάντηση:** α
*   **Εξήγηση:** Το `$match` για το `pyear` πρέπει να είναι το *πρώτο* στάδιο για να φιλτράρει τα δεδομένα *πριν* την ομαδοποίηση. Έπειτα, ομαδοποιούμε, ταξινομούμε και περιορίζουμε.

**56. Ποια εντολή ανακτά όλα τα μοναδικά genres που υπάρχουν στη συλλογή, χρησιμοποιώντας το πεδίο `genre` (αν είναι String);**
    α. `db.books.distinct("genre")`
    β. `db.books.aggregate([{$group:{_id:"$genre"}}])`
    γ. `db.books.find().distinct("genre")`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η `distinct()` είναι η πιο άμεση μέθοδος για την εύρεση μοναδικών τιμών ενός πεδίου. Η χρήση του `$group` με `_id:"$genre"` θα δώσει επίσης μοναδικά genres ως `_id` των ομαδοποιημένων αποτελεσμάτων.

**57. Ποια εντολή ανακτά όλα τα μοναδικά `keywords` από τη συλλογή, δεδομένου ότι το `keywords` είναι ένας πίνακας;**
    α. `db.books.distinct("keywords")`
    β. `db.books.aggregate([{$unwind:"$keywords"}, {$group:{_id:"$keywords"}}])`
    γ. `db.books.find({}).project({_id:0, keywords:1}).unwind("keywords").distinct("keywords")`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το `distinct("keywords")` μπορεί να λειτουργήσει σε πεδία array, βρίσκοντας όλες τις μοναδικές τιμές του array. Η προσέγγιση με `$unwind` και `$group` είναι η κλασική aggregation για arrays και δίνει επίσης το σωστό αποτέλεσμα.

**58. Ποια εντολή βρίσκει τον συνολικό αριθμό σελίδων για βιβλία με βαθμολογία (rating) πάνω από 4.0;**
    α. `db.books.aggregate([{$match:{rating:{$gt:4.0}}}, {$group:{_id:null, totalPages:{$sum:"$pages"}}}])`
    β. `db.books.aggregate([{$group:{_id:null, totalPages:{$sum:"$pages"}}}, {$match:{rating:{$gt:4.0}}}])`
    γ. `db.books.find({rating:{$gt:4.0}}).map(doc => doc.pages).sum()`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Πρώτα φιλτράρουμε τα δεδομένα με το `$match` και μετά κάνουμε την άθροιση `$sum` στα φιλτραρισμένα αποτελέσματα.

**59. Ποια εντολή υπολογίζει τον μέσο όρο βαθμολογίας (rating) ανά γλώσσα (language) για βιβλία με πάνω από 200 σελίδες;**
    α. `db.books.aggregate([{$match:{pages:{$gt:200}}}, {$group:{_id:"$language", avgRating:{$avg:"$rating"}}}])`
    β. `db.books.aggregate([{$group:{_id:"$language", avgRating:{$avg:"$rating"}}}, {$match:{pages:{$gt:200}}}])`
    γ. `db.books.aggregate([{$project:{_id:0, language:1, rating:1, pages:1}}, {$match:{pages:{$gt:200}}}, {$group:{_id:"$language", avgRating:{$avg:"$rating"}}}])`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το `$match` για τις σελίδες πρέπει να προηγηθεί του `$group` για να ομαδοποιηθούν μόνο τα σχετικά βιβλία. Το α είναι η πιο απλή και άμεση. Η γ προσθέτει ένα `$project` που δεν είναι απαραίτητο αλλά δεν είναι λάθος.

**60. Ποια εντολή επιστρέφει το πλήθος των βιβλίων ανά έτος έκδοσης και ανά συγγραφέα;**
    α. `db.books.aggregate([{$group:{_id:{pyear:"$pyear", author:"$author"}, bookCount:{$sum:1}}}])`
    β. `db.books.aggregate([{$group:{_id:"$pyear", subGroups:{$group:{_id:"$author", bookCount:{$sum:1}}}}}] )`
    γ. `db.books.aggregate([{$group:{_id:["$pyear", "$author"], bookCount:{$sum:1}}}])`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Για ομαδοποίηση με πολλαπλά κλειδιά, χρησιμοποιούμε ένα document στο `_id` με πεδία ή έναν πίνακα (αν και το document είναι πιο τυπικό).

---

**Ομάδα 10: Aggregation Framework - Συνδυασμός Σταδίων Pipeline**

**61. Ποια εντολή βρίσκει τον τίτλο και το έτος του βιβλίου με τις περισσότερες σελίδες που εκδόθηκε μετά το 1990;**
    α. `db.books.aggregate([{$match:{pyear:{$gt:1990}}}, {$sort:{pages:-1}}, {$limit:1}, {$project:{title:1, pyear:1, _id:0}}])`
    β. `db.books.aggregate([{$sort:{pages:-1}}, {$limit:1}, {$match:{pyear:{$gt:1990}}}, {$project:{title:1, pyear:1, _id:0}}])`
    γ. `db.books.find({pyear:{$gt:1990}}).sort({pages:-1}).limit(1).project({title:1, pyear:1, _id:0})`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το `$match` (ή `find` κριτήριο) πρέπει να εφαρμοστεί πρώτο για να περιοριστούν τα δεδομένα, μετά η ταξινόμηση και ο περιορισμός, και τέλος η προβολή.

**62. Ποια εντολή βρίσκει τους 5 κορυφαίους συγγραφείς με τον υψηλότερο μέσο όρο βαθμολογίας για τα βιβλία τους, για βιβλία με τουλάχιστον 100 σελίδες;**
    α. `db.books.aggregate([{$match:{pages:{$gte:100}}}, {$group:{_id:"$author", avgRating:{$avg:"$rating"}}}, {$sort:{avgRating:-1}}, {$limit:5}])`
    β. `db.books.aggregate([{$group:{_id:"$author", avgRating:{$avg:"$rating"}}}, {$match:{pages:{$gte:100}}}, {$sort:{avgRating:-1}}, {$limit:5}])`
    γ. `db.books.aggregate([{$project:{author:1, avgRating:{$avg:"$rating"}, pages:1}}, {$match:{pages:{$gte:100}}}, {$sort:{avgRating:-1}}, {$limit:5}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Η σωστή σειρά είναι: `$match` για να φιλτράρουμε τα αρχικά δεδομένα, `$group` για να υπολογίσουμε το μέσο όρο, `$sort` για να ταξινομήσουμε τα αποτελέσματα, και `$limit` για να πάρουμε τους κορυφαίους.

**63. Ποια εντολή βρίσκει τη γλώσσα και το μέσο αριθμό σελίδων για βιβλία με βαθμολογία πάνω από 4.0, ταξινομημένα φθίνουσα κατά μέσο όρο σελίδων;**
    α. `db.books.aggregate([{$match:{rating:{$gt:4.0}}}, {$group:{_id:"$language", avgPages:{$avg:"$pages"}}}, {$sort:{avgPages:-1}}])`
    β. `db.books.aggregate([{$group:{_id:"$language", avgPages:{$avg:"$pages"}}}, {$match:{rating:{$gt:4.0}}}, {$sort:{avgPages:-1}}])`
    γ. `db.books.aggregate([{$project:{_id:0, language:1, avgPages:{$avg:"$pages"}, rating:1}}, {$match:{rating:{$gt:4.0}}}, {$sort:{avgPages:-1}}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Το `$match` πρέπει να εφαρμοστεί πρώτο για να φιλτράρουμε τα αρχικά έγγραφα. Μετά, το `$group` υπολογίζει το μέσο όρο και τέλος το `$sort` ταξινομεί.

**64. Ποια εντολή υπολογίζει τον συνολικό αριθμό βιβλίων και τον μέσο όρο σελίδων για κάθε εκδότη (`publisher`), μόνο για βιβλία που είναι `inStock` (διαθέσιμα);**
    α. `db.books.aggregate([{$match:{inStock:true}}, {$group:{_id:"$publisher", totalBooks:{$sum:1}, avgPages:{$avg:"$pages"}}}])`
    β. `db.books.aggregate([{$group:{_id:"$publisher", totalBooks:{$sum:1}, avgPages:{$avg:"$pages"}}}, {$match:{inStock:true}}])`
    γ. `db.books.aggregate([{$match:{inStock:true}}, {$group:{_id:"$publisher"}}, {$project:{totalBooks:{$sum:1}, avgPages:{$avg:"$pages"}}}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Το `$match` φιλτράρει τα διαθέσιμα βιβλία, και στη συνέχεια το `$group` υπολογίζει τις ζητούμενες συγκεντρωτικές μετρήσεις ανά εκδότη.

**65. Ποια εντολή ανακτά τους συγγραφείς που έχουν τουλάχιστον 2 βιβλία γραμμένα στην "English" γλώσσα, μαζί με τον αριθμό αυτών των βιβλίων;**
    α. `db.books.aggregate([{$match:{language:"English"}}, {$group:{_id:"$author", bookCount:{$sum:1}}}, {$match:{bookCount:{$gte:2}}}])`
    β. `db.books.aggregate([{$group:{_id:"$author", bookCount:{$sum:1}}}, {$match:{bookCount:{$gte:2}, language:"English"}}])`
    γ. `db.books.aggregate([{$group:{_id:"$author", bookCount:{$sum:1}, language:{$first:"$language"}}}, {$match:{bookCount:{$gte:2}, language:"English"}}])`
    δ. `db.books.aggregate([{$match:{bookCount:{$gte:2}}}, {$group:{_id:"$author", bookCount:{$sum:1}}}, {$match:{language:"English"}}])`
*   **Απάντηση:** α
*   **Εξήγηση:** Το φιλτράρισμα της γλώσσας γίνεται στο αρχικό `$match` ώστε μόνο τα αγγλικά βιβλία να ομαδοποιηθούν. Έπειτα, ομαδοποιούμε, και φιλτράρουμε τις ομάδες που έχουν τουλάχιστον 2 βιβλία.

**66. Ποια εντολή ανακτά τους 3 συγγραφείς με τα περισσότερα βιβλία, των οποίων ο τίτλος περιέχει τη λέξη "Dragon" (case-insensitive);**
    α. `db.books.aggregate([{$match:{title:{$regex:/dragon/i}}}, {$group:{_id:"$author", bookCount:{$sum:1}}}, {$sort:{bookCount:-1}}, {$limit:3}])`
    β. `db.books.aggregate([{$group:{_id:"$author", bookCount:{$sum:1}}}, {$match:{title:{$regex:/dragon/i}}}, {$sort:{bookCount:-1}}, {$limit:3}])`
    γ. `db.books.aggregate([{$project:{_id:0, title:1, author:1}}, {$match:{title:{$regex:/dragon/i}}}, {$group:{_id:"$author", bookCount:{$sum:1}}}, {$sort:{bookCount:-1}}, {$limit:3}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Ο `$match` με `$regex` για τον τίτλο εφαρμόζεται πρώτος. Στη συνέχεια, γίνεται η ομαδοποίηση, η ταξινόμηση και ο περιορισμός.

**67. Ποια εντολή επιστρέφει το πλήθος των βιβλίων ανά genre και το μέσο όρο rating για κάθε genre;**
    α. `db.books.aggregate([{$group:{_id:"$genre", bookCount:{$sum:1}, avgRating:{$avg:"$rating"}}}])`
    β. `db.books.aggregate([{$unwind:"$genres"}, {$group:{_id:"$genres", bookCount:{$sum:1}, avgRating:{$avg:"$rating"}}}])` (αν το `genres` είναι array)
    γ. Και οι δύο α και β μπορούν να είναι σωστές, ανάλογα με τη δομή του `genre` πεδίου.
    δ. `db.books.aggregate([{$group:{genre:"$genre", totalCount:{$count: {}}, avgRating:{$avg:"$rating"}}}])`
*   **Απάντηση:** γ
*   **Εξήγηση:** Αν το `genre` είναι ένα string, η α είναι σωστή. Αν το `genres` είναι ένα array (πχ. ένα βιβλίο έχει πολλά είδη), τότε το `$unwind` είναι απαραίτητο πριν την ομαδοποίηση, οπότε η β είναι σωστή.

**68. Ποια εντολή ανακτά τα `titles` των βιβλίων και τους `authors` (όπου το `authors` είναι array) αλλά για κάθε συγγραφέα να εμφανίζεται σε ξεχωριστή γραμμή;**
    α. `db.books.aggregate([{$unwind: "$authors"}, {$project: {title: 1, author: "$authors", _id: 0}}])`
    β. `db.books.aggregate([{$project: {title: 1, author: "$authors", _id: 0}}, {$unwind: "$authors"}])`
    γ. `db.books.aggregate([{$flatten: "$authors"}, {$project: {title: 1, author: "$authors", _id: 0}}])`
    δ. `db.books.find({}, {title: 1, authors: 1, _id: 0}).unwind("authors")`
*   **Απάντηση:** α
*   **Εξήγηση:** Το `$unwind` πρέπει να γίνει πρώτο για να "ξεδιπλώσει" το array. Μετά το `$project` επιλέγει και μετονομάζει τα πεδία.

**69. Ποια εντολή βρίσκει τον μέσο όρο βαθμολογίας (`rating`) και τον συνολικό αριθμό βιβλίων (`totalBooks`) για κάθε εκδότη (`publisher`), και ταξινομεί τα αποτελέσματα κατά `totalBooks` φθίνουσα;**
    α. `db.books.aggregate([{$group:{_id:"$publisher", avgRating:{$avg:"$rating"}, totalBooks:{$sum:1}}}, {$sort:{totalBooks:-1}}])`
    β. `db.books.aggregate([{$sort:{totalBooks:-1}}, {$group:{_id:"$publisher", avgRating:{$avg:"$rating"}, totalBooks:{$sum:1}}}])`
    γ. `db.books.aggregate([{$group:{_id:"$publisher", avgRating:{$avg:"$rating"}, totalBooks:{$count: {}}}}, {$sort:{totalBooks:-1}}])`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** α
*   **Εξήγηση:** Πρώτα ομαδοποιούμε με `$group` για να υπολογίσουμε τα aggregations, και μετά ταξινομούμε αυτά τα αποτελέσματα με `$sort`.

**70. Ποια εντολή βρίσκει το συνολικό αριθμό σελίδων (`pages`) για κάθε έτος (`pyear`), μόνο για βιβλία με τίτλο που ξεκινάει από "The", και επιστρέφει μόνο τα έτη με συνολικά πάνω από 1000 σελίδες;**
    α. `db.books.aggregate([{$match:{title:/^The/}}, {$group:{_id:"$pyear", totalPages:{$sum:"$pages"}}}, {$match:{totalPages:{$gt:1000}}}])`
    β. `db.books.aggregate([{$group:{_id:"$pyear", totalPages:{$sum:"$pages"}}}, {$match:{title:/^The/, totalPages:{$gt:1000}}}])`
    γ. `db.books.aggregate([{$group:{_id:"$pyear", totalPages:{$sum:"$pages"}, title:{$first:"$title"}}}, {$match:{title:/^The/, totalPages:{$gt:1000}}}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Ο `$match` για τον τίτλο γίνεται στα αρχικά documents. Το `$group` υπολογίζει το `totalPages`. Ο δεύτερος `$match` φιλτράρει τις ομάδες με βάση το υπολογιζόμενο `totalPages`.

---

**Ομάδα 11: Ερωτήσεις για Indexing**

**71. Ποια εντολή δημιουργεί ένα single-field index στο πεδίο `author` για γρήγορη αναζήτηση ανά συγγραφέα;**
    α. `db.books.createIndex({author: 1})`
    β. `db.books.addIndex({author: 1})`
    γ. `db.books.index({author: 1})`
    δ. `db.books.ensureIndex({author: 1})`
*   **Απάντηση:** α
*   **Εξήγηση:** Η `createIndex()` είναι η σωστή μέθοδος. Η `ensureIndex()` είναι deprecated αλλά λειτουργεί. Το `1` υποδηλώνει αύξουσα σειρά (ή οποιαδήποτε τιμή για ένα single-field index).

**72. Ποια εντομή δημιουργεί ένα compound index στα πεδία `pyear` (αύξουσα) και `rating` (φθίνουσα);**
    α. `db.books.createIndex({pyear: 1, rating: -1})`
    β. `db.books.createIndex({"pyear": "asc", "rating": "desc"})`
    γ. `db.books.createIndex({pyear: -1, rating: 1})`
    δ. `db.books.createIndex([pyear: 1, rating: -1])`
*   **Απάντηση:** α
*   **Εξήγηση:** Ένα compound index ορίζεται με ένα document που περιέχει τα πεδία και τις κατευθύνσεις τους.

**73. Για ποιο λόγο θα χρησιμοποιούσαμε την επιλογή `{unique: true}` κατά τη δημιουργία ενός index;**
    α. Για να διασφαλίσουμε ότι τα δεδομένα στο πεδίο είναι μοναδικά σε όλη τη συλλογή.
    β. Για να επιταχύνουμε τις αναζητήσεις μοναδικών τιμών.
    γ. Για να αποτρέψουμε την εισαγωγή διπλότυπων εγγράφων.
    δ. Όλα τα παραπάνω.
*   **Απάντηση:** δ
*   **Εξήγηση:** Ένα unique index διασφαλίζει ότι όλες οι τιμές στο συγκεκριμένο πεδίο (ή συνδυασμό πεδίων για compound index) είναι μοναδικές σε όλη τη συλλογή, αποτρέποντας διπλότυπα και επιταχύνοντας τις αναζητήσεις μοναδικών τιμών.

**74. Ποια εντολή εμφανίζει όλα τα indexes μιας συλλογής;**
    α. `db.books.getIndexes()`
    β. `db.books.showIndexes()`
    γ. `db.books.listIndexes()`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Και οι δύο μέθοδοι, `getIndexes()` και `listIndexes()`, χρησιμοποιούνται για την εμφάνιση των ευρετηρίων μιας συλλογής.

**75. Ποια εντολή διαγράφει ένα index με το όνομα "author_1" από τη συλλογή `books`;**
    α. `db.books.dropIndex("author_1")`
    β. `db.books.removeIndex("author_1")`
    γ. `db.books.deleteIndex("author_1")`
    δ. `db.books.dropIndexes({author: 1})`
*   **Απάντηση:** α
*   **Εξήγηση:** Η `dropIndex()` είναι η μέθοδος για τη διαγραφή ενός ευρετηρίου, παίρνοντας ως όρισμα το όνομα του ευρετηρίου.

---

**Ομάδα 12: Διάφορες Λειτουργίες και Εντολές Shell**

**76. Ποια εντολή θα εκτελέσει JavaScript κώδικα απευθείας στο MongoDB shell;**
    α. `run()`
    β. `eval()`
    γ. `js()`
    δ. `execute()`
*   **Απάντηση:** β
*   **Εξήγηση:** Η `eval()` είναι μια συνάρτηση που εκτελεί JavaScript κώδικα στο shell της MongoDB. (Προσοχή, δεν συνίσταται σε production περιβάλλοντα για λόγους ασφαλείας).

**77. Ποια εντολή εμφανίζει τη λίστα με όλες τις βάσεις δεδομένων στον MongoDB server;**
    α. `show dbs`
    β. `list databases`
    γ. `show databases`
    δ. `show all dbs`
*   **Απάντηση:** α
*   **Εξήγηση:** Το `show dbs` (ή `show databases`) είναι η εντολή του MongoDB shell για να λίστα τις διαθέσιμες βάσεις δεδομένων.

**78. Ποια εντολή εμφανίζει τη λίστα με όλες τις συλλογές στην τρέχουσα βάση δεδομένων;**
    α. `show collections`
    β. `list collections`
    γ. `show tables`
    δ. `show all collections`
*   **Απάντηση:** α
*   **Εξήγηση:** Το `show collections` είναι η εντολή του MongoDB shell για να λίστα τις συλλογές στην ενεργή βάση δεδομένων.

**79. Ποια εντολή διαγράφει ολόκληρη τη συλλογή `books` από την τρέχουσα βάση δεδομένων;**
    α. `db.books.dropCollection()`
    β. `db.books.remove({})`
    γ. `db.books.drop()`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Και οι δύο μέθοδοι, `dropCollection()` και `drop()`, διαγράφουν ολόκληρη τη συλλογή. Το `remove({})` διαγράφει *μόνο* τα documents αλλά αφήνει τη συλλογή κενή.

**80. Ποια εντολή αλλάζει στην τρέχουσα βάση δεδομένων με το όνομα "university_db";**
    α. `use university_db`
    β. `connect university_db`
    γ. `select database university_db`
    δ. `db.use("university_db")`
*   **Απάντηση:** α
*   **Εξήγηση:** Η εντολή `use <database_name>` είναι ο τρόπος για να επιλέξετε (ή να δημιουργήσετε αν δεν υπάρχει) μια βάση δεδομένων.

---

**Ομάδα 13: Προχωρημένες Ερωτήσεις Aggregation και Array Operations**

**81. Ποια εντολή θα επιστρέψει τους συγγραφείς (`authors`, που είναι array) που έχουν γράψει βιβλία μαζί με "J.R.R. Tolkien";**
    α. `db.books.aggregate([{$match:{authors:"J.R.R. Tolkien"}}, {$unwind:"$authors"}, {$group:{_id:"$authors"}}])`
    β. `db.books.find({authors:"J.R.R. Tolkien"}).distinct("authors")`
    γ. `db.books.aggregate([{$match:{authors:{$in:["J.R.R. Tolkien"]}}}, {$unwind:"$authors"}, {$match:{authors:{$ne:"J.R.R. Tolkien"}}}, {$group:{_id:"$authors"}}])`
    δ. `db.books.aggregate([{$unwind:"$authors"}, {$match:{authors:"J.R.R. Tolkien"}}, {$group:{_id:"$authors"}}])`
*   **Απάντηση:** α
*   **Εξήγηση:** Πρώτα φιλτράρουμε τα βιβλία που περιλαμβάνουν τον "J.R.R. Tolkien" στους συγγραφείς τους. Έπειτα, χρησιμοποιούμε `$unwind` για να αποσυμπιέσουμε τον πίνακα των συγγραφέων σε ξεχωριστά documents. Τέλος, ομαδοποιούμε με `_id:"$authors"` για να πάρουμε όλους τους μοναδικούς συγγραφείς από αυτά τα βιβλία.

**82. Ποια εντολή θα υπολογίσει το μέσο μήκος (σελίδες) των τίτλων για κάθε genre;**
    α. `db.books.aggregate([{$project:{_id:0, genre:1, titleLength:{$strLenCP:"$title"}}}, {$group:{_id:"$genre", avgTitleLength:{$avg:"$titleLength"}}}])`
    β. `db.books.aggregate([{$group:{_id:"$genre", avgTitleLength:{$avg:{$strLenCP:"$title"}}}] )`
    γ. `db.books.aggregate([{$group:{_id:"$genre", avgTitleLength:{$avg:"$title.length"}}}] )`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Πρώτα, δημιουργούμε ένα νέο πεδίο `titleLength` χρησιμοποιώντας τον operator `$strLenCP` (string length). Έπειτα, ομαδοποιούμε ανά genre και υπολογίζουμε το μέσο όρο αυτού του νέου πεδίου.

**83. Ποια εντολή θα επιστρέψει όλους τους τίτλους των βιβλίων του Stephen King, γραμμένους με κεφαλαία γράμματα;**
    α. `db.books.aggregate([{$match:{author:"Stephen King"}}, {$project:{_id:0, title:{$toUpper:"$title"}}}] )`
    β. `db.books.aggregate([{$match:{author:"Stephen King"}}, {$project:{title:{$upper:"$title"}}}] )`
    γ. `db.books.find({author:"Stephen King"}, {title:{$toUpper:1}, _id:0})`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Ο `$toUpper` είναι ένας aggregation operator που μετατρέπει ένα string σε κεφαλαία. Χρησιμοποιείται μέσα σε ένα `$project` stage.

**84. Ποια εντολή θα υπολογίσει τον μέσο όρο των βαθμολογιών για βιβλία που εκδόθηκαν το κάθε δεκαετία (π.χ. 1950s, 1960s);**
    α. `db.books.aggregate([{$project:{decade:{$subtract:["$pyear", {$mod:["$pyear",10]}]}, rating:1}}, {$group:{_id:"$decade", avgRating:{$avg:"$rating"}}}])`
    β. `db.books.aggregate([{$group:{_id:{$floor:{$divide:["$pyear",10]}}, avgRating:{$avg:"$rating"}}}])`
    γ. `db.books.aggregate([{$project:{decade:{$multiply:[{$floor:{$divide:["$pyear",10]}},10]}, rating:1}}, {$group:{_id:"$decade", avgRating:{$avg:"$rating"}}}])`
    δ. Και οι δύο α και γ είναι σωστές.
*   **Απάντηση:** γ
*   **Εξήγηση:** Για να δημιουργήσουμε δεκαετία (π.χ., 1950, 1960), μπορούμε να διαιρέσουμε το έτος με 10, να πάρουμε το ακέραιο μέρος, και να πολλαπλασιάσουμε πάλι με 10. Το `$floor` και `$divide` και `$multiply` επιτρέπουν αυτό τον υπολογισμό.

**85. Ποια εντολή θα προσθέσει ένα πεδίο `shortDescription` (String) σε όλα τα βιβλία που δεν έχουν αυτή τη στιγμή περιγραφή;**
    α. `db.books.updateMany({shortDescription:{$exists:false}}, {$set:{shortDescription:"No description available."}})`
    β. `db.books.updateMany({shortDescription:null}, {$set:{shortDescription:"No description available."}})`
    γ. `db.books.insertMany({shortDescription:"No description available."})`
    δ. `db.books.bulkWrite({updateMany:{filter:{shortDescription:{$exists:false}}, update:{$set:{shortDescription:"No description available."}}}}])`
*   **Απάντηση:** α
*   **Εξήγηση:** Χρησιμοποιούμε το `$exists: false` για να βρούμε τα έγγραφα που δεν έχουν το πεδίο, και το `$set` για να το προσθέσουμε.

**86. Ποια εντολή θα αλλάξει το `language` του βιβλίου `_id: ObjectId(...)` σε "Greek" και ταυτόχρονα να του δώσει `rating: 5.0`;**
    α. `db.books.updateOne({_id: ObjectId(...)}, {$set:{language:"Greek", rating:5.0}})`
    β. `db.books.updateOne({_id: ObjectId(...)}, {language:"Greek", rating:5.0})`
    γ. `db.books.update({_id: ObjectId(...)}, {$set:{language:"Greek"}, $set:{rating:5.0}})`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Μπορούμε να ορίσουμε πολλές ενημερώσεις `$set` μέσα στο ίδιο update document.

**87. Ποια εντολή θα βρει όλες τις βιβλιογραφικές εγγραφές που έχουν το `author` πεδίο ως String και όχι Array (αν υποτεθεί ότι μπορεί να είναι και τα δύο);**
    α. `db.books.find({author: {$type: "string"}})`
    β. `db.books.find({author: {$not: {$type: "array"}}})`
    γ. Και οι δύο α και β είναι σωστές.
    δ. `db.books.find({author: {$eq: "String"}})`
*   **Απάντηση:** γ
*   **Εξήγηση:** Μπορούμε είτε να ψάξουμε ρητά τον τύπο string είτε να αποκλείσουμε τον τύπο array.

**88. Ποια εντολή βρίσκει το 2ο βιβλίο με τις περισσότερες σελίδες από τον "Stephen King";**
    α. `db.books.find({author: "Stephen King"}).sort({pages: -1}).skip(1).limit(1)`
    β. `db.books.aggregate([{$match:{author:"Stephen King"}}, {$sort:{pages:-1}}, {$skip:1}, {$limit:1}])`
    γ. Και οι δύο α και β είναι σωστές.
    δ. `db.books.find({author: "Stephen King"}).skip(1).limit(1).sort({pages: -1})`
*   **Απάντηση:** γ
*   **Εξήγηση:** Για να βρούμε το N-ιοστό στοιχείο (πχ το 2ο), πρέπει πρώτα να φιλτράρουμε (αν υπάρχει κριτήριο), μετά να ταξινομήσουμε, να παραλείψουμε (skip) N-1 στοιχεία, και τέλος να πάρουμε 1 στοιχείο (limit).

**89. Ποια εντολή θα βρει το πλήθος των βιβλίων ανά έτος έκδοσης, αλλά θα εμφανίσει μόνο τα έτη με περισσότερα από 5 βιβλία, και θα ταξινομήσει φθίνουσα ανά πλήθος;**
    α. `db.books.aggregate([{$group:{_id:"$pyear", count:{$sum:1}}}, {$match:{count:{$gt:5}}}, {$sort:{count:-1}}])`
    β. `db.books.aggregate([{$match:{count:{$gt:5}}}, {$group:{_id:"$pyear", count:{$sum:1}}}, {$sort:{count:-1}}])`
    γ. `db.books.aggregate([{$sort:{count:-1}}, {$group:{_id:"$pyear", count:{$sum:1}}}, {$match:{count:{$gt:5}}}])`
    δ. Καμία από τις παραπάνω.
*   **Απάντηση:** α
*   **Εξήγηση:** Σωστή σειρά των σταδίων aggregation: `$group` (για υπολογισμό του count), `$match` (για φιλτράρισμα με βάση το υπολογιζόμενο count), `$sort` (για ταξινόμηση).

**90. Ποια εντολή ανακτά βιβλία με βαθμολογία (rating) όχι ίση με 3.0;**
    α. `db.books.find({rating: {$ne: 3.0}})`
    β. `db.books.find({rating: {$not: {$eq: 3.0}}})`
    γ. Και οι δύο α και β είναι σωστές.
    δ. `db.books.find({rating: "!3.0"})`
*   **Απάντηση:** γ
*   **Εξήγηση:** Ο `$ne` είναι ο πιο άμεσος τελεστής για "not equal". Ο `$not` μπορεί να χρησιμοποιηθεί για να αντιστρέψει μια έκφραση, όπως εδώ το `$eq`.

---

**Ομάδα 14: Έλεγχος Βάσης Δεδομένων & Ασφάλειας**

**91. Ποια εντολή χρησιμοποιείται για να αναζητήσει documents σε όλα τα πεδία χωρίς να καθοριστεί συγκεκριμένο πεδίο;**
    α. Αυτό δεν υποστηρίζεται απευθείας από την MongoDB Find Query.
    β. `db.books.find({$text: {$search: "keyword"}})` (με προϋπόθεση ύπαρξης text index)
    γ. `db.books.find("keyword")`
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η α είναι σωστή διότι ο βασικός `find` δεν μπορεί να ψάξει "σε όλα τα πεδία". Η β είναι σωστή μόνο αν έχει δημιουργηθεί ένα `text index` και χρησιμοποιηθεί ο `$text` operator.

**92. Τι κάνει η επιλογή `w: "majority"` σε ένα write operation στην MongoDB;**
    α. Εξασφαλίζει ότι η εγγραφή θα επιβεβαιωθεί μόνο όταν γραφτεί στην πλειοψηφία των ψηφοφόρων μελών ενός replica set.
    β. Κάνει την εγγραφή ασύγχρονη.
    γ. Σημαίνει ότι η εγγραφή θα γίνει σε τουλάχιστον ένα μέλος του replica set.
    δ. Δεν είναι έγκυρη επιλογή.
*   **Απάντηση:** α
*   **Εξήγηση:** Το `w` είναι το write concern. Το `w: "majority"` εγγυάται ότι η εγγραφή έχει γίνει σε πάνω από το μισό των μελών του replica set, παρέχοντας υψηλή συνέπεια.

**93. Ποιο είναι το default write concern για τις εγγραφές στην MongoDB;**
    α. `w: 1`
    β. `w: "majority"`
    γ. `w: 0`
    δ. Καμία από τις παραπάνω. Εξαρτάται από τη ρύθμιση του driver ή του server.
*   **Απάντηση:** δ
*   **Εξήγηση:** Από την MongoDB 3.4 και μετά, το προεπιλεγμένο `write concern` για single-node instances είναι `w: 1` (επιβεβαίωση από τον primary). Για replica sets, ο client driver μπορεί να έχει προεπιλογή σε `w: 1` ή `w: majority`. Η απάντηση `d` είναι η πιο ακριβής καθώς εξαρτάται. (Ωστόσο, για ακαδημαϊκή χρήση, το `w: 1` συχνά θεωρείται το "πιο απλό default"). Αν υποθέσουμε ότι ζητείται η συμπεριφορά του primary server, τότε το `w:1` είναι η απάντηση. Ας διαλέξουμε την πιο κοινή παραδοχή για εξετάσεις.

**94. Τι επιστρέφει η μέθοδος `db.books.explain().find()` ή `db.books.explain().aggregate()` στην MongoDB;**
    α. Τις τιμές των πεδίων των εγγράφων.
    β. Πληροφορίες για το query plan που χρησιμοποιεί ο optimizer.
    γ. Στατιστικά για την απόδοση της ερώτησης.
    δ. Και οι δύο β και γ είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Η `explain()` παρέχει λεπτομερείς πληροφορίες σχετικά με το πώς ο MongoDB εκτελεί ένα query, συμπεριλαμβανομένου του query plan, των index που χρησιμοποιούνται και των στατιστικών απόδοσης.

**95. Ποιο είναι το purpose του `--port` argument όταν ξεκινάμε έναν MongoDB daemon (`mongod`)**;**
    α. Καθορίζει το port για τη βάση δεδομένων.
    β. Καθορίζει το port για το web interface της MongoDB.
    γ. Καθορίζει το port για το authentication.
    δ. Καθορίζει το port για τη διαχείριση του server.
*   **Απάντηση:** α
*   **Εξήγηση:** Το `--port` καθορίζει τη θύρα στην οποία ο `mongod` θα ακούει για εισερχόμενες συνδέσεις.

---

**Ομάδα 15: Μικρές Ερωτήσεις Σύνταξης / Λογικής**

**96. Ποιο είναι το ισοδύναμο του SQL `SELECT title FROM books WHERE pyear = 2023;` σε MongoDB;**
    α. `db.books.find({pyear: 2023}, {title: 1})`
    β. `db.books.find({pyear: 2023}, {title: 1, _id: 0})`
    γ. `db.books.find({title: 1, pyear: 2023})`
    δ. Και οι δύο α και β είναι σωστές, ανάλογα αν το `_id` θέλουμε να εμφανιστεί.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το `SELECT title` αντιστοιχεί στο `{title: 1}` στο projection. Το `WHERE pyear = 2023` είναι το query `{pyear: 2023}`. Το `_id: 0` εξαιρεί το προεπιλεγμένο `_id` πεδίο, όπως συμβαίνει στην SQL που δεν επιστρέφεται το primary key εκτός αν ζητηθεί ρητά.

**97. Ποιο από τα παρακάτω *δεν* είναι έγκυρος aggregation operator;**
    α. `$sum`
    β. `$avg`
    γ. `$count` (ως accumulator)
    δ. `$first`
*   **Απάντηση:** γ
*   **Εξήγηση:** Ενώ υπάρχει ένα στάδιο `$count` στην aggregation pipeline, δεν υπάρχει ένας accumulator `$count` όπως το `$sum`, `$avg`, `$first` μέσα στο `$group`. Το αντίστοιχο για μέτρηση μέσα σε `$group` είναι `$sum: 1`.

**98. Ποιο πεδίο ορίζεται αυτόματα από την MongoDB αν δεν το παρέχει ο χρήστης κατά την εισαγωγή ενός document;**
    α. `_uid`
    β. `docId`
    γ. `_id`
    δ. `id`
*   **Απάντηση:** γ
*   **Εξήγηση:** Το `_id` είναι το primary key της MongoDB και δημιουργείται αυτόματα αν δεν παρασχεθεί κατά την εισαγωγή.

**99. Γιατί ένα `_id` ObjectId ξεκινάει με 12 bytes;**
    α. Περιέχει την τρέχουσα χρονική στιγμή, machine identifier, process ID, και counter.
    β. Για να είναι μοναδικό σε όλα τα distributed συστήματα.
    γ. Είναι ένα τυχαίο hex string.
    δ. Και οι δύο α και β είναι σωστές.
*   **Απάντηση:** δ
*   **Εξήγηση:** Το ObjectId αποτελείται από χρονική σήμανση, machine identifier, process ID και έναν counter, διασφαλίζοντας έτσι τη μοναδικότητά του ακόμη και σε distributed environments.

**100. Ποιο από τα παρακάτω είναι μια επιλογή για το read preference σε ένα replica set της MongoDB;**
    α. `primary`
    β. `secondary`
    γ. `nearest`
    δ. Όλα τα παραπάνω.
*   **Απάντηση:** δ
*   **Εξήγηση:** Όλα αυτά είναι έγκυρες επιλογές read preference, που καθορίζουν από ποιο μέλος του replica set θα διαβάσει ο client τα δεδομένα. `primary` διαβάζει μόνο από τον primary, `secondary` από δευτερεύοντα μέλη, και `nearest` από το πιο κοντινό (με βάση το network latency).

---

Ελπίζω αυτές οι 100 ερωτήσεις να σας φανούν εξαιρετικά χρήσιμες για την προετοιμασία των εξετάσεών σας στη MongoDB! Καλό διάβασμα!
