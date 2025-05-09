# **Μηχανές Πεπερασμένων Καταστάσεων (FSM) και Περιορισμός Regular**  

---

## **Ενότητα 1: Μηχανές Πεπερασμένων Καταστάσεων (Finite State Machines - FSM)**

1.  **Ορισμός:**
    *   Μια FSM (ή Πεπερασμένο Αυτόματο - Finite Automaton, FA) είναι ένα αφηρημένο μαθηματικό μοντέλο υπολογισμού.
    *   Αποτελείται από:
        *   Ένα πεπερασμένο σύνολο **καταστάσεων (states)**.
        *   Ένα σύνολο **μεταβάσεων (transitions)** μεταξύ αυτών των καταστάσεων, που πυροδοτούνται από **σύμβολα εισόδου (input symbols)** από ένα καθορισμένο **αλφάβητο (alphabet)**.
        *   Μια διακεκριμένη **αρχική κατάσταση (start state)**.
        *   Ένα (πιθανώς κενό) σύνολο **τελικών ή αποδεκτών καταστάσεων (final/accepting states)**.
    *   **Σκοπός:** Οι FSM χρησιμοποιούνται κυρίως για να **αναγνωρίζουν (recognize)** ή να **αποδέχονται (accept)** συγκεκριμένες **ακολουθίες συμβόλων** (συμβολοσειρές ή "λέξεις") που ανήκουν σε μια τυπική γλώσσα (συγκεκριμένα, μια **κανονική γλώσσα - regular language**).

2.  **Τύποι Πεπερασμένων Αυτομάτων:**
    *   **Ντετερμινιστικό Πεπερασμένο Αυτόματο (Deterministic Finite Automaton - DFA):** Για κάθε κατάσταση και κάθε σύμβολο εισόδου, υπάρχει **ακριβώς μία** επόμενη κατάσταση.
    *   **Μη-Ντετερμινιστικό Πεπερασμένο Αυτόματο (Nondeterministic Finite Automaton - NFA):** Για μια κατάσταση και ένα σύμβολο εισόδου, μπορεί να υπάρχουν **πολλές** (ή καμία) επόμενες καταστάσεις.
    *   **NFA με ε-μεταβάσεις (NFA-ε):** Επιτρέπονται μεταβάσεις μεταξύ καταστάσεων **χωρίς** την κατανάλωση συμβόλου εισόδου (αυθόρμητες μεταβάσεις).
    *   **Υπολογιστική Ισοδυναμία:** Παρά τις διαφορές στη δομή τους, τα DFAs, NFAs, και NFA-ε είναι **ισοδύναμα** ως προς την κλάση των γλωσσών που μπορούν να αναγνωρίσουν (τις κανονικές γλώσσες). Για κάθε NFA υπάρχει ένα ισοδύναμο DFA (αν και μπορεί να έχει εκθετικά περισσότερες καταστάσεις).

3.  **Λειτουργία ενός DFA:**
    *   Ξεκινά στην αρχική κατάσταση (`q₀`).
    *   Για κάθε σύμβολο της εισόδου (διαβάζοντας τη συμβολοσειρά από αριστερά προς τα δεξιά):
        *   Χρησιμοποιεί τη **συνάρτηση μετάβασης (transition function `δ`)** για να καθορίσει την επόμενη κατάσταση: `δ(τρέχουσα_κατάσταση, σύμβολο_εισόδου) = επόμενη_κατάσταση`.
        *   Μεταβαίνει στην επόμενη κατάσταση.
    *   Αφού διαβαστούν όλα τα σύμβολα της εισόδου, η συμβολοσειρά **γίνεται αποδεκτή** αν το αυτόματο βρίσκεται σε μια **τελική κατάσταση (F)**. Διαφορετικά, **απορρίπτεται**.
    *   **Τυπικός Ορισμός DFA:** Μια 5-άδα (Q, Σ, δ, q₀, F) όπου:
        *   Q: Πεπερασμένο σύνολο καταστάσεων.
        *   Σ: Πεπερασμένο αλφάβητο εισόδου.
        *   δ: Q × Σ → Q (Συνάρτηση μετάβασης).
        *   q₀ ∈ Q: Αρχική κατάσταση.
        *   F ⊆ Q: Σύνολο τελικών καταστάσεων.

---

## **Ενότητα 2: Το Πρόβλημα Προγραμματισμού Βαρδιών Προσωπικού (Crew Rostering)**

1.  **Σενάριο:**
    *   Δεδομένοι: Ένας αριθμός **φρουρών** και ένα **χρονικό διάστημα** (π.χ., 10 ημέρες).
    *   Απαιτήσεις: Κάθε μέρα πρέπει να κατανεμηθούν οι φρουροί σε βάρδιες.
    *   Τύποι Βαρδιών: `day` (πρωινή), `ngt` (νυχτερινή), `off` (ρεπό).

2.  **Περιορισμοί:**
    *   **Περιορισμοί Κάλυψης (ανά Ημέρα):**
        *   Κάθε μέρα, τουλάχιστον 2 νυχτερινοί φρουροί.
        *   Κάθε μέρα, τουλάχιστον 4 πρωινοί φρουροί.
    *   **Περιορισμοί Φρουρού (ανά Φρουρό, για όλο το διάστημα):**
        *   Κάθε φρουρός πρέπει να κάνει *ακριβώς* 2 νυχτερινές βάρδιες συνολικά.
    *   **Περιορισμοί Ακολουθίας (ανά Φρουρό - οι πιο σύνθετοι):** Ορίζουν ποιες ακολουθίες βαρδιών είναι επιτρεπτές για έναν μεμονωμένο φρουρό. Αυτοί είναι οι κανόνες που θα μοντελοποιηθούν με FSM:
        1.  Η ακολουθία δεν μπορεί να ξεκινά με `off`.
        2.  Μετά από `ngt`, ακολουθεί *υποχρεωτικά* `off`.
        3.  Μετά από 3 συνεχόμενες `day`, ακολουθεί *υποχρεωτικά* `off`.
        4.  Μετά από `off`, πρέπει να ακολουθήσει `day` ή `ngt`.
        5.  Μετά από την 1η `day`, *μπορεί* να ακολουθήσει `ngt`, αλλά *όχι* μετά από 2 ή 3 συνεχόμενες `day`.
        6.  Δεν μπορεί να πάρει `off` αμέσως μετά την 1η `day`.

---

## **Ενότητα 3: Μοντελοποίηση Κανόνων Ακολουθίας με FSM**

1.  **Ιδέα:** Κατασκευάζουμε ένα FSM όπου:
    *   Οι **καταστάσεις** "συμπυκνώνουν" το σχετικό **ιστορικό** των προηγούμενων βαρδιών ενός φρουρού, ώστε να μπορούμε να αποφασίσουμε ποιες είναι οι επόμενες επιτρεπτές βάρδιες.
    *   Το **αλφάβητο** είναι οι τύποι βαρδιών: Σ = {`day`, `ngt`, `off`}.
    *   Οι **μεταβάσεις** αντιστοιχούν στην ανάθεση μιας συγκεκριμένης βάρδιας και οδηγούν σε μια νέα κατάσταση που αντικατοπτρίζει το ενημερωμένο ιστορικό.
2.  **Κατασκευή του FSM (παράδειγμα διαφανειών):**
    *   **Κατάσταση 1 (Αρχική):** Ο φρουρός είτε ξεκινά το πρόγραμμα είτε μόλις πήρε ρεπό. Επιτρεπτές επόμενες βάρδιες: `day` (πάει κατάσταση 2), `ngt` (πάει κατάσταση 4). (*Κανόνας 1 & 4*)
    *   **Κατάσταση 2:** Ο φρουρός έκανε 1 `day`. Επιτρεπτές: `day` (πάει κατάσταση 3), `ngt` (πάει κατάσταση 4). (*Κανόνας 6 & 5*)
    *   **Κατάσταση 3:** Ο φρουρός έκανε 2 συνεχόμενες `day`. Επιτρεπτές: `day` (πάει κατάσταση ? - *λείπει από το διάγραμμα, ας την πούμε 3b*), `off` (πάει κατάσταση 1). (*Κανόνας 5 - όχι ngt*)
    *   **Κατάσταση ? (3b):** Ο φρουρός έκανε 3 συνεχόμενες `day`. Επιτρεπτή: Μόνο `off` (πάει κατάσταση 1). (*Κανόνας 3*)
    *   **Κατάσταση 4:** Ο φρουρός έκανε `ngt`. Επιτρεπτή: Μόνο `off` (πάει κατάσταση 1). (*Κανόνας 2*)
    *   **Κατάσταση 0 (Παγίδα/Απαγορευμένη - Trap State):** Αν γίνει μια μη επιτρεπτή μετάβαση (δεν φαίνεται απαραίτητη αν ορίζουμε μόνο τις επιτρεπτές).
    *   **Τελικές Καταστάσεις:** Στο τέλος του χρονικού διαστήματος, *όλες* οι καταστάσεις (εκτός της παγίδας) είναι αποδεκτές.
3.  **Πίνακας Μεταβάσεων:** Ο παραπάνω FSM μπορεί να αναπαρασταθεί με έναν πίνακα `δ(τρέχουσα_κατάσταση, σύμβολο_βάρδιας) = επόμενη_κατάσταση`.

---

## **Ενότητα 4: Ο Περιορισμός `regular`**

1.  **Ορισμός:** Ένας καθολικός περιορισμός που επιβάλλει μια **ακολουθία μεταβλητών** να σχηματίζει μια λέξη που γίνεται **αποδεκτή από ένα δεδομένο FSM (συνήθως DFA)**.
2.  **Σύνταξη στη MiniZinc:**
    ```minizinc
    predicate regular(
        array [int] of var $Val: x, // Η ακολουθία των μεταβλητών
        array [$State,$Val] of opt $State: d, // Πίνακας Μεταβάσεων
        $State: q0, // Αρχική Κατάσταση
        set of $State: F // Σύνολο Τελικών Καταστάσεων
    )
    ```
3.  **Παράμετροι:**
    *   `x`: Ένας μονοδιάστατος πίνακας μεταβλητών απόφασης (π.χ., η ακολουθία βαρδιών `roster[g, d]` για τον φρουρό `g`). Οι τιμές αυτών των μεταβλητών πρέπει να ανήκουν στο αλφάβητο του αυτομάτου (`$Val`, που αντιστοιχεί στο Σ).
    *   `d`: Ο **πίνακας μεταβάσεων** του DFA. Είναι ένας 2D πίνακας όπου `d[q, v]` δίνει την επόμενη κατάσταση όταν το αυτόματο βρίσκεται στην κατάσταση `q` και διαβάζει το σύμβολο `v`. Ο τύπος `$State` συνήθως είναι `int`. Χρησιμοποιείται `opt $State` για να δηλωθούν μη-ορισμένες/απαγορευμένες μεταβάσεις (με την τιμή `<>`). Η έκδοση της MiniZinc που περιγράφεται *δεν* απαιτεί κατάσταση παγίδας.
    *   `q0`: Η τιμή (ακέραιος) που αντιστοιχεί στην αρχική κατάσταση του DFA.
    *   `F`: Το σύνολο των τιμών (ακεραίων) που αντιστοιχούν στις τελικές καταστάσεις του DFA.
4.  **Εφαρμογή στο Crew Rostering:**
    *   Ορίζουμε έναν τύπο `SHIFT = {day, ngt, off}` και τις καταστάσεις του FSM `STATE = 1..ns` (όπου `ns` ο αριθμός καταστάσεων, π.χ., 4 στο παράδειγμα των διαφανειών).
    *   Κωδικοποιούμε τον πίνακα μεταβάσεων `allowed[STATE, SHIFT] of opt STATE`.
    *   Για **κάθε φρουρό `g`**, εφαρμόζουμε τον περιορισμό `regular` στη γραμμή του `roster` που του αντιστοιχεί:
        ```minizinc
        constraint forall(g in GUARD) (
            regular(
                row(roster, g), // Η ακολουθία βαρδιών για τον φρουρό g
                allowed,        // Ο πίνακας μεταβάσεων του FSM
                1,              // Η αρχική κατάσταση
                STATE           // Όλες οι καταστάσεις είναι τελικές
            )
        );
        ```

---

## **Ενότητα 5: Counting Constraints (Περιορισμοί Μέτρησης)**

1.  **Χρήση:** Χρήσιμοι για τους περιορισμούς κάλυψης (ανά ημέρα) και τους περιορισμούς φρουρού (συνολικός αριθμός νυχτερινών).
2.  **MiniZinc `count()` built-in:**
    *   `count(list_comprehension)(condition)`: Μετρά πόσες φορές η `condition` είναι αληθής για τα στοιχεία που παράγονται από το comprehension. *Αν και υπάρχει, μπορεί να είναι λιγότερο αποδοτικό από global constraints.*
    *   Πχ: `constraint forall(g in GUARD) (count(d in PERIOD)(roster[g,d] == ngt) == 2);`
3.  **Συντομογραφίες (`count_eq`, `count_leq`, etc.):**
    *   Παρέχονται από τη MiniZinc ως πιο βολική γραφή:
        *   `count_eq(array, value, count)`: Επιβάλλει το `value` να εμφανίζεται ακριβώς `count` φορές στο `array`.
        *   Πχ: `count_eq([roster[g,d] | d in PERIOD], ngt, 2)`
4.  **Global Cardinality Constraint (`global_cardinality` - GCC):**
    *   Ένας πιο γενικός και ισχυρός περιορισμός μέτρησης.
    *   `global_cardinality(x, cover, counts)`: Επιβάλλει ότι για κάθε `i`, η τιμή `cover[i]` πρέπει να εμφανίζεται στη λίστα μεταβλητών `x` **ακριβώς** `counts[i]` φορές.
    *   `cover`: Σταθερός πίνακας με τις τιμές που θέλουμε να μετρήσουμε.
    *   `counts`: Πίνακας (σταθερός ή μεταβλητών) με τις απαιτούμενες μετρήσεις.
    *   **Low/High Παραλλαγή (`gcc_low_high`):** Επιτρέπει τον ορισμό *εύρους* [low, high] για κάθε μέτρηση.
5.  **Εφαρμογή GCC στο Rostering:**
    *   **Μέτρηση `ngt` ανά φρουρό:**
        `global_cardinality([roster[g,d]|d in PERIOD], [day, ngt, off], [count_d, 2, count_off])` (όπου `count_d`, `count_off` είναι μεταβλητές ή αδιάφορες).
    *   **Μέτρηση ανά ημέρα:**
        `global_cardinality([roster[g,d]|g in GUARD], [day, ngt, off], count_shifts[d])` (όπου `count_shifts` είναι ένας πίνακας με τις μετρήσεις).
        Και μετά περιορισμοί: `count_shifts[d, day] >= 4`, `count_shifts[d, ngt] >= 2`.

6.  **Redundant Constraints:** Η προσθήκη περιορισμών που λογικά συνεπάγονται από άλλους (redundant), όπως το `global_cardinality` για νυχτερινές *μαζί* με το απλό `sum`, μπορεί μερικές φορές να βοηθήσει τον solver να βρει λύση γρηγορότερα, ενισχύοντας τη διάδοση.

---

## **Ενότητα 6: Διάδοση Περιορισμού `regular` (Αλγόριθμος)**

1.  **Αναπαράσταση Γράφου:** Ο αλγόριθμος διάδοσης για τον `regular` χρησιμοποιεί μια **εσωτερική δομή δεδομένων**: έναν **κατευθυνόμενο γράφο με επίπεδα (layered graph)**.
    *   Ο γράφος έχει `N+1` επίπεδα, όπου `N` είναι το μήκος της ακολουθίας μεταβλητών `x`.
    *   Κάθε επίπεδο `i` (από 1 έως N+1) περιέχει έναν κόμβο για **κάθε κατάσταση** του αρχικού FSM. Ένας κόμβος σε αυτό το γράφο συμβολίζεται `qk⁽ⁱ⁾`.
    *   Υπάρχει μια **ακμή** από τον κόμβο `qk⁽ⁱ⁾` στον κόμβο `q₂⁽ⁱ⁺¹⁾` **αν και μόνο αν** υπάρχει μια τιμή (σύμβολο) `s` στο **τρέχον πεδίο της μεταβλητής `Xᵢ`** τέτοια ώστε να υπάρχει μετάβαση `δ(qk, s) = q₂` στο αρχικό FSM. Η ακμή αυτή "σημαδεύεται" με την τιμή `s`.

2.  **Έλεγχος Συνέπειας (Πώς Δουλεύει η Διάδοση):**
    *   Μια λύση για την ακολουθία `x` αντιστοιχεί σε ένα **μονοπάτι** στον γράφο επιπέδων, που ξεκινά από τον κόμβο `q₀⁽¹⁾` (αρχική κατάσταση στο 1ο επίπεδο) και καταλήγει σε έναν κόμβο `q_f⁽ᴺ⁺¹⁾` όπου `q_f` είναι μια **τελική κατάσταση** (ανήκει στο `F`).
    *   Ο αλγόριθμος διαγράφει από τον γράφο όλες τις ακμές και τους κόμβους που **δεν** μπορούν να ανήκουν σε ένα τέτοιο έγκυρο μονοπάτι.
    *   **Βήμα 1 (Διάδοση προς τα Εμπρός - Forward Pass):** Ξεκινώντας από το `q₀⁽¹⁾`, βρίσκει όλους τους κόμβους που είναι προσβάσιμοι (reachable). Διαγράφει όλους τους κόμβους και τις ακμές που δεν είναι προσβάσιμες από την αρχή.
    *   **Βήμα 2 (Διάδοση προς τα Πίσω - Backward Pass):** Ξεκινώντας από *όλους* τους κόμβους `q_f⁽ᴺ⁺¹⁾` όπου `q_f ∈ F` (τελικές καταστάσεις στο τελευταίο επίπεδο), βρίσκει όλους τους κόμβους που μπορούν να τους φτάσουν, πηγαίνοντας ανάποδα τις ακμές. Διαγράφει όλους τους κόμβους και τις ακμές που δεν μπορούν να οδηγήσουν σε τελική κατάσταση.
    *   **Βήμα 3 (Φιλτράρισμα Πεδίων):** Μετά τις διαγραφές, για κάθε μεταβλητή `Xᵢ`, μια τιμή `s` παραμένει στο πεδίο της **μόνο αν** υπάρχει τουλάχιστον μία ακμή σημαδεμένη με `s` μεταξύ του επιπέδου `i` και `i+1` που επέζησε και των δύο διαδόσεων.

3.  **Απόδοση και Incrementality:**
    *   Αυτός ο αλγόριθμος είναι αρκετά αποδοτικός (πολυωνυμικός ως προς το μέγεθος του γράφου επιπέδων).
    *   Είναι **incremental**: αν μια τιμή αφαιρεθεί από το πεδίο μιας μεταβλητής `Xᵢ` (λόγω άλλου περιορισμού ή αναζήτησης), ο αλγόριθμος μπορεί να ξανατρέξει, πιθανώς διαγράφοντας περισσότερες ακμές/κόμβους και αφαιρώντας περαιτέρω τιμές από τα πεδία *άλλων* μεταβλητών της ακολουθίας. Αυτό επιτυγχάνει ισχυρή διάδοση.

**Συμπεράσματα:**

*   Οι FSM είναι ένα ισχυρό εργαλείο για τη μοντελοποίηση περιορισμών που αφορούν **ακολουθίες**.
*   Ο καθολικός περιορισμός `regular` επιτρέπει την ενσωμάτωση της λογικής ενός FSM απευθείας σε ένα μοντέλο Constraint Programming.
*   Είναι ιδιαίτερα χρήσιμος σε προβλήματα όπως ο προγραμματισμός βαρδιών (rostering), όπου υπάρχουν σύνθετοι κανόνες για τις επιτρεπτές ακολουθίες καταστάσεων (βαρδιών).
*   Η διάδοση του `regular` βασίζεται στην κατασκευή και ανάλυση ενός γράφου επιπέδων, επιτρέποντας ισχυρό φιλτράρισμα των πεδίων των μεταβλητών της ακολουθίας.
*   Οι περιορισμοί μέτρησης (`count`, `global_cardinality`) είναι επίσης σημαντικοί για πολλά προβλήματα rostering και μπορούν να συνδυαστούν αποτελεσματικά με τον `regular`.
