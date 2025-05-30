# Παράδειγμα Απαριθμητοί τύποι

```
enum DAY = {mon,tue,wed,thu,fri,sat,sun};

var DAY: start;
var DAY: off;
var mon..fri: working_day;

constraint enum2int(start) + 2 = enum2int(working_day);
constraint off > working_day;

solve maximize working_day;
```

## Εξήγηση

Αυτός ο κώδικας είναι ένα παράδειγμα μοντέλου περιορισμών (constraint model) που χρησιμοποιεί ένα σύνολο ημερών της εβδομάδας για να ορίσει κάποιες σχέσεις και περιορισμούς μεταξύ τους. Ας τον αναλύσουμε κατά κομμάτια:

```plaintext
enum DAY = {mon, tue, wed, thu, fri, sat, sun};
```

- Αυτή η γραμμή ορίζει ένα σύνολο με τις ημέρες της εβδομάδας. Το `enum DAY` δηλώνει ένα σύνολο με τις τιμές `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, και `sun`.

```plaintext
var DAY: start;
var DAY: off;
```

- Αυτές οι γραμμές δηλώνουν δύο μεταβλητές, `start` και `off`, οι οποίες μπορούν να πάρουν τιμές από το σύνολο `DAY`. Δηλαδή, αυτές οι μεταβλητές μπορούν να είναι οποιαδήποτε ημέρα της εβδομάδας.

```plaintext
var mon..fri: working_day;
```

- Αυτή η γραμμή δηλώνει μια μεταβλητή `working_day` που μπορεί να πάρει τιμές από τις ημέρες Δευτέρα έως Παρασκευή (`mon` έως `fri`). Δηλαδή, η `working_day` δεν μπορεί να είναι Σάββατο (`sat`) ή Κυριακή (`sun`).

```plaintext
constraint enum2int(start) + 2 = enum2int(working_day);
```

- Αυτή η γραμμή ορίζει έναν περιορισμό που συνδέει τις μεταβλητές `start` και `working_day`. Η συνάρτηση `enum2int` μετατρέπει τις τιμές του `enum` σε ακέραιους αριθμούς (π.χ., `mon` μετατρέπεται σε 0, `tue` σε 1, κ.ο.κ.). Ο περιορισμός λέει ότι η ακέραια τιμή της `start` αυξημένη κατά 2 πρέπει να είναι ίση με την ακέραια τιμή της `working_day`. Για παράδειγμα, αν `start` είναι `mon` (0), τότε `working_day` πρέπει να είναι `wed` (2).

```plaintext
constraint off > working_day;
```

- Αυτή η γραμμή ορίζει έναν περιορισμό που λέει ότι η ημέρα `off` πρέπει να είναι μετά την `working_day`. Δηλαδή, η τιμή της `off` πρέπει να είναι μεγαλύτερη από την τιμή της `working_day`.

```plaintext
solve maximize working_day;
```

- Αυτή η γραμμή ορίζει τον στόχο του προβλήματος, να μεγιστοποιήσει την τιμή της `working_day`. Δηλαδή, θέλουμε να βρούμε τη μεγαλύτερη δυνατή τιμή για την `working_day` που ικανοποιεί όλους τους παραπάνω περιορισμούς.

Συνοψίζοντας, ο κώδικας ορίζει ένα πρόβλημα περιορισμών όπου θέλουμε να βρούμε τη μεγαλύτερη δυνατή ημέρα εργασίας (`working_day`) που ικανοποιεί τους περιορισμούς σχετικά με τις ημέρες `start` και `off`.

---

# ΠΡΟΒΛΗΜΑ

Οι διαθέσιμες συχνότητες τις οποίες μπορεί να αναθέσει η εταιρεία σε 4 γειτονικούς σταθμούς A, B, Γ και Δ, είναι από 900Mhz μέχρι και 960Mhz με βήμα αύξησης τα 10Mhz (900,910, κλπ). Η ανάθεση των συχνοτήτων γίνεται με αύξουσα σειρά, δηλαδή ο Α έχει μικρότερη συχνότητα από τον Β, ο Β από τον Γ κοκ, στους τέσσερις σταθμούς πρέπει να ανατεθούν διαφορετικές συχνότητες και όλες οι αποστάσεις μεταξύ των συχνοτήτων πρέπει να είναι διαφορετικές, δηλαδή η διαφορά μεταξύ οποιονδήποτε δύο των συχνοτήτων να είναι διαφορετική. Να μοντελοποιήσετε και να λύσετε το πρόβλημα, ως πρόβλημα ικανοποίησης περιορισμών και να δημιουργήσετε το αντίστοιχο πρόγραμμα σε MiniZinc.

## ΛΥΣΗ 2

```
set of int: RANGE =
    {freq | freq in 900..960 where freq mod 10 = 0 };
set of int: STATIONS = 1..4;

array[STATIONS] of var RANGE: alloc;
array[int] of var int: diffs =
    [abs(alloc[i]-alloc[j]) | i,j in STATIONS where i < j];

include "alldifferent.mzn";
constraint alldifferent(alloc);
constraint alldifferent(diffs);
constraint alloc[1] < alloc[2]
    /\ alloc[2] < alloc[3]
    /\ alloc[3] < alloc[4];

solve satisfy;
```

### Ανάλυση Κώδικα:

1. **Δήλωση συνόλων και πινάκων:**

    ```minizinc
    set of int: RANGE = {freq | freq in 900..960 where freq mod 10 = 0 };
    ```

    Δηλώνει ένα σύνολο ακεραίων με το όνομα `RANGE`. Αυτό το σύνολο περιέχει όλους τους ακέραιους αριθμούς από το 900 έως το 960 που είναι πολλαπλάσια του 10 (δηλαδή, 900, 910, 920, ..., 960).

    ```minizinc
    set of int: STATIONS = 1..4;
    ```

    Δηλώνει ένα σύνολο ακεραίων με το όνομα `STATIONS`, το οποίο περιέχει τους αριθμούς από το 1 έως το 4.

2. **Δήλωση πινάκων μεταβλητών:**

    ```minizinc
    array[STATIONS] of var RANGE: alloc;
    ```

    Δηλώνει έναν πίνακα μεταβλητών με το όνομα `alloc`, ο οποίος έχει μήκος ίσο με το μέγεθος του συνόλου `STATIONS` (δηλαδή, 4). Κάθε στοιχείο του πίνακα `alloc` είναι μια μεταβλητή που μπορεί να πάρει τιμές από το σύνολο `RANGE`.

    ```minizinc
    array[int] of var int: diffs = [abs(alloc[i]-alloc[j]) | i,j in STATIONS where i < j];
    ```

    Δηλώνει έναν πίνακα μεταβλητών με το όνομα `diffs`, ο οποίος περιέχει τις απόλυτες διαφορές μεταξύ των στοιχείων του πίνακα `alloc`. Για κάθε ζεύγος `(i, j)` όπου `i < j`, υπολογίζεται η απόλυτη διαφορά `abs(alloc[i] - alloc[j])`.

3. **Εισαγωγή βιβλιοθήκης και περιορισμοί:**

    ```minizinc
    include "alldifferent.mzn";
    ```

    Εισάγει τη βιβλιοθήκη `alldifferent.mzn`, η οποία παρέχει τη συνάρτηση `alldifferent` που εξασφαλίζει ότι όλες οι τιμές σε έναν πίνακα είναι διαφορετικές.

    ```minizinc
    constraint alldifferent(alloc);
    ```

    Εφαρμόζει τον περιορισμό `alldifferent` στον πίνακα `alloc`, δηλαδή όλες οι τιμές στον πίνακα `alloc` πρέπει να είναι διαφορετικές.

    ```minizinc
    constraint alldifferent(diffs);
    ```

    Εφαρμόζει τον περιορισμό `alldifferent` στον πίνακα `diffs`, δηλαδή όλες οι τιμές στον πίνακα `diffs` πρέπει να είναι διαφορετικές.

    ```minizinc
    constraint alloc[1] < alloc[2]
    /\ alloc[2] < alloc[3]
    /\ alloc[3] < alloc[4];
    ```

    Εφαρμόζει περιορισμούς για τη σειρά των τιμών στον πίνακα `alloc`. Οι τιμές πρέπει να είναι σε αύξουσα σειρά: `alloc[1] < alloc[2] < alloc[3] < alloc[4]`.

4. **Επίλυση του προβλήματος:**

    ```minizinc
    solve satisfy;
    ```

    Δηλώνει ότι το πρόβλημα είναι ένα πρόβλημα ικανοποίησης περιορισμών (satisfaction problem), δηλαδή ζητάμε να βρεθεί μια λύση που ικανοποιεί όλους τους περιορισμούς.

Συνοψίζοντας, ο κώδικας αυτός δημιουργεί έναν πίνακα `alloc` με τέσσερις διαφορετικές τιμές από το σύνολο `RANGE`, οι οποίες πρέπει να είναι σε αύξουσα σειρά. Επιπλέον, οι απόλυτες διαφορές μεταξύ των τιμών του `alloc` πρέπει να είναι διαφορετικές. Το πρόβλημα επιλύεται ως πρόβλημα ικανοποίησης περιορισμών.

## ΛΥΣΗ 3

```
set of int: RANGE =
    {freq | freq in 900..960 where freq mod 10 = 0 };
set of int: STATIONS = 1..4;

array[STATIONS] of var RANGE: alloc;
array[int] of var int: diffs =
    [abs(alloc[i]-alloc[j]) | i,j in STATIONS where i < j];

include "alldifferent.mzn";
constraint alldifferent(alloc);
constraint alldifferent(diffs);
constraint forall(i,j in STATIONS where j = i + 1)
    (alloc[i] < alloc[j]);

solve satisfy;
```

### Ανάλυση Κώδικα:

1. **Ορισμός του συνόλου `RANGE`:**
   ```minizinc
   set of int: RANGE = {freq | freq in 900..960 where freq mod 10 = 0 };
   ```
   Δημιουργούμε το σύνολο `RANGE`, το οποίο περιλαμβάνει αριθμούς από το 900 έως το 960, με τη συνθήκη ότι οι αριθμοί αυτοί πρέπει να είναι πολλαπλάσια του 10. Αυτό σημαίνει ότι το σύνολο `RANGE` θα περιλαμβάνει τους αριθμούς: `{900, 910, 920, 930, 940, 950, 960}`.

2. **Ορισμός του συνόλου `STATIONS`:**
   ```minizinc
   set of int: STATIONS = 1..4;
   ```
   Το σύνολο `STATIONS` περιλαμβάνει τους αριθμούς 1 έως 4, οι οποίοι φαίνεται να αναφέρονται στους σταθμούς ή τις θέσεις στους οποίους θα τοποθετηθούν οι συχνότητες.

3. **Ορισμός του πίνακα `alloc`:**
   ```minizinc
   array[STATIONS] of var RANGE: alloc;
   ```
   Δημιουργείται ένας πίνακας (array) `alloc` με μέγεθος 4 (αντιστοιχεί στους 4 σταθμούς), και κάθε στοιχείο του πίνακα είναι μία μεταβλητή (τύπου `var`), η οποία παίρνει τιμές από το σύνολο `RANGE`. Έτσι, το κάθε στοιχείο του πίνακα `alloc` θα αντιστοιχεί σε μια συχνότητα από το σύνολο `{900, 910, 920, 930, 940, 950, 960}`.

4. **Υπολογισμός διαφορών μεταξύ των συχνοτήτων:**
   ```minizinc
   array[int] of var int: diffs = [abs(alloc[i]-alloc[j]) | i,j in STATIONS where i < j];
   ```
   Δημιουργείται ένας πίνακας `diffs`, ο οποίος αποθηκεύει τις απόλυτες διαφορές (`abs`) μεταξύ των συχνοτήτων που έχουν ανατεθεί στους σταθμούς. Για κάθε ζεύγος σταθμών `i` και `j` με `i < j`, υπολογίζεται η διαφορά μεταξύ των συχνοτήτων `alloc[i]` και `alloc[j]`.

   Ο πίνακας `diffs` θα περιλαμβάνει τις απόλυτες διαφορές μεταξύ των συχνοτήτων για όλα τα δυνατά ζεύγη σταθμών.

5. **Χρήση του "alldifferent" περιορισμού:**
   ```minizinc
   include "alldifferent.mzn";
   constraint alldifferent(alloc);
   constraint alldifferent(diffs);
   ```
   - `include "alldifferent.mzn";`: Εισάγεται ο περιορισμός `alldifferent`, ο οποίος εξασφαλίζει ότι τα στοιχεία του πίνακα θα είναι διαφορετικά μεταξύ τους. Η MiniZinc περιλαμβάνει βιβλιοθήκες με συνηθισμένα πρότυπα περιορισμών, και το `alldifferent` διασφαλίζει ότι όλα τα στοιχεία του πίνακα `alloc` (οι συχνότητες) και του πίνακα `diffs` (οι διαφορές) είναι μοναδικά. Έτσι, οι συχνότητες και οι διαφορές μεταξύ τους πρέπει να είναι διαφορετικές μεταξύ τους.
   
6. **Περιορισμός για τις συχνότητες των γειτονικών σταθμών:**
   ```minizinc
   constraint forall(i,j in STATIONS where j = i + 1)(alloc[i] < alloc[j]);
   ```
   Αυτός ο περιορισμός δηλώνει ότι για κάθε ζεύγος γειτονικών σταθμών `i` και `j` (δηλαδή όταν `j = i + 1`), η συχνότητα που ανατίθεται στον σταθμό `i` πρέπει να είναι μικρότερη από αυτήν που ανατίθεται στον σταθμό `j`. Δηλαδή, οι συχνότητες πρέπει να αυξάνονται όσο προχωράμε από τον πρώτο στον τέταρτο σταθμό.

7. **Λύση του προβλήματος:**
   ```minizinc
   solve satisfy;
   ```
   Ορίζεται ότι πρέπει να ικανοποιηθούν όλοι οι περιορισμοί (satisfy). Αυτό σημαίνει ότι η MiniZinc θα προσπαθήσει να βρει μια λύση που να ικανοποιεί όλους τους περιορισμούς, χωρίς να χρειάζεται να βελτιστοποιήσει κάποιο αντικειμενικό στόχο (π.χ., μέγιστο ή ελάχιστο άθροισμα).

### Συνολική Ερμηνεία:
Ο κώδικας αφορά την κατανομή συχνοτήτων στους 4 σταθμούς, με περιορισμούς:
- Οι συχνότητες που ανατίθενται στους σταθμούς πρέπει να είναι διαφορετικές μεταξύ τους.
- Οι διαφορές μεταξύ των συχνοτήτων των σταθμών πρέπει να είναι διαφορετικές μεταξύ τους.
- Οι συχνότητες πρέπει να είναι αυξανόμενες καθώς πηγαίνουμε από τον πρώτο στον τέταρτο σταθμό.
- Επίσης, οι συχνότητες πρέπει να είναι από το σύνολο `{900, 910, 920, 930, 940, 950, 960}` και να είναι πολλαπλάσια του 10.
  
Το πρόγραμμα προσπαθεί να βρει μια κατανομή των συχνοτήτων που να πληροί όλους αυτούς τους περιορισμούς.

---

# Διαφορά 2 αριθμών των 5 ψηφίων

### Ανάλυση Κώδικα:

1. **Ορισμός του συνόλου `NUMBERS`:**
   ```minizinc
   set of int: NUMBERS = 0..9;
   ```
   Το σύνολο `NUMBERS` περιλαμβάνει τους αριθμούς από το 0 έως το 9, δηλαδή τους δεκαδικούς αριθμούς που μπορούν να χρησιμοποιηθούν ως ψηφία.

2. **Ορισμός του αριθμού `u`:**
   ```minizinc
   int : u = 5;
   ```
   Ορίζεται μια μεταβλητή `u` με τιμή 5. Αυτή η τιμή καθορίζει τον αριθμό των ψηφίων για τους πίνακες `n` και `m`. Δηλαδή, οι πίνακες `n` και `m` θα περιλαμβάνουν 5 ψηφία ο καθένας.

3. **Ορισμός του συνόλου `DIGITS`:**
   ```minizinc
   set of int: DIGITS = 1..u;
   ```
   Το σύνολο `DIGITS` περιλαμβάνει τους αριθμούς από το 1 έως το 5 (δηλαδή, τα 5 ψηφία που χρησιμοποιούνται στους πίνακες `n` και `m`).

4. **Δημιουργία των πινάκων `n` και `m`:**
   ```minizinc
   array[DIGITS] of var NUMBERS: n;
   array[DIGITS] of var NUMBERS: m;
   ```
   Δημιουργούνται δύο πίνακες, `n` και `m`, οι οποίοι περιλαμβάνουν 5 μεταβλητές (από το σύνολο `NUMBERS`, δηλαδή από το 0 έως το 9). Αυτές οι μεταβλητές αντιπροσωπεύουν τα ψηφία των αριθμών που θα σχηματιστούν.

5. **Ορισμός των μεταβλητών `num_N1` και `num_M1`:**
   ```minizinc
   var int: num_N1;
   var int: num_M1;
   ```
   Οι μεταβλητές `num_N1` και `num_M1` θα αποθηκεύσουν τους αριθμούς που σχηματίζονται από τους πίνακες `n` και `m`, αντίστοιχα.

6. **Υπολογισμός των αριθμών `num_N1` και `num_M1`:**
   ```minizinc
   constraint num_N1 = sum(i in DIGITS)(n[i]*pow(10,u-i));
   constraint num_M1 = sum(i in DIGITS)(m[i]*pow(10,u-i));
   ```
   Αυτές οι εκφράσεις υπολογίζουν τους αριθμούς που σχηματίζονται από τους πίνακες `n` και `m`. Η συνάρτηση `pow(10, u-i)` χρησιμοποιείται για να πολλαπλασιάσει το κάθε ψηφίο με τη σωστή δύναμη του 10, ανάλογα με τη θέση του ψηφίου στον αριθμό.
   - Για τον αριθμό `num_N1`, το ψηφίο `n[i]` πολλαπλασιάζεται με το 10^ (5-i) (για `i = 1..5`).
   - Ομοίως για τον αριθμό `num_M1`, το ψηφίο `m[i]` πολλαπλασιάζεται με το 10^(5-i).
   
   Αυτοί οι υπολογισμοί δημιουργούν τους αριθμούς `num_N1` και `num_M1` από τα ψηφία στους πίνακες `n` και `m`.

7. **Ορισμός της μεταβλητής `dif`:**
   ```minizinc
   var int: dif = abs(num_N1 - num_M1);
   ```
   Η μεταβλητή `dif` υπολογίζει την απόλυτη διαφορά μεταξύ των αριθμών `num_N1` και `num_M1`.

8. **Περιορισμοί για συγκεκριμένα ψηφία:**
   ```minizinc
   constraint n[2]=5 /\ n[5]=3 /\ m[3]=0 /\ m[5] = 1;
   ```
   Αυτοί οι περιορισμοί καθορίζουν τις τιμές για συγκεκριμένα ψηφία στους πίνακες `n` και `m`:
   - Το δεύτερο ψηφίο του πίνακα `n` πρέπει να είναι 5.
   - Το πέμπτο ψηφίο του πίνακα `n` πρέπει να είναι 3.
   - Το τρίτο ψηφίο του πίνακα `m` πρέπει να είναι 0.
   - Το πέμπτο ψηφίο του πίνακα `m` πρέπει να είναι 1.

9. **Εισαγωγή του περιορισμού `alldifferent`:**
   ```minizinc
   include "alldifferent.mzn";
   constraint alldifferent(m++n);
   ```
   Εδώ, χρησιμοποιείται ο περιορισμός `alldifferent`, ο οποίος διασφαλίζει ότι όλα τα ψηφία στους πίνακες `m` και `n` (μαζί, δηλαδή ο συνδυασμός των δύο πινάκων) είναι διαφορετικά μεταξύ τους. Η σύνταξη `m++n` συνδυάζει τους πίνακες `m` και `n`.

10. **Στρατηγική επίλυσης:**
    ```minizinc
    solve minimize dif;
    ```
    Η στρατηγική επίλυσης ζητά τη λύση που ελαχιστοποιεί την τιμή της μεταβλητής `dif`, δηλαδή τη διαφορά μεταξύ των δύο αριθμών που σχηματίζονται από τους πίνακες `n` και `m`.

11. **Έξοδος:**
    ```minizinc
    output [...];
    ```
    Αυτή η εντολή καθορίζει ποια δεδομένα θα εμφανιστούν ως έξοδος. Αν και η συγκεκριμένη σύνταξη δεν έχει ολοκληρωθεί, συνήθως περιλαμβάνει τον τύπο της εξόδου που επιθυμούμε, όπως τους πίνακες `n`, `m` και τη διαφορά `dif`.

### Συνολική Ερμηνεία:

Ο κώδικας προσπαθεί να βρει δύο αριθμούς, `num_N1` και `num_M1`, οι οποίοι σχηματίζονται από 5 ψηφία, όπου:
- Τα ψηφία είναι διαφορετικά μεταξύ τους.
- Ο δεύτερος πίνακας έχει το 0 στο τρίτο ψηφίο και το 1 στο πέμπτο.
- Ο πρώτος πίνακας έχει το 5 στο δεύτερο ψηφίο και το 3 στο πέμπτο.

Η διαφορά μεταξύ των δύο αριθμών πρέπει να είναι όσο το δυνατόν μικρότερη, και όλα τα ψηφία στους πίνακες πρέπει να είναι διαφορετικά. Το MiniZinc θα προσπαθήσει να βρει την καλύτερη κατανομή των ψηφίων που να ικανοποιεί όλους τους περιορισμούς.

---

# Τελευταία διαφάνεια

Τι θα κάνει το παρακάτω πρόγραμμα?

```
int :mode;
array[1..3] of var 1..10: tab;

constraint forall(i in 1..2)
    (tab[i+1] = if mode = 1 then tab[i]
            else tab[i]-1 endif);

solve satisfy;
```

Αυτός ο κώδικας είναι ένα πρόγραμμα περιορισμών που ορίζει έναν πίνακα `tab` και μια μεταβλητή `mode`. Εξηγώ τον κώδικα γραμμή προς γραμμή και το τι θα κάνει:

1. `int :mode;`
   - Δηλώνει μια μεταβλητή με το όνομα `mode` της οποίας η τιμή είναι ακέραιος αριθμός.

2. `array[1..3] of var 1..10: tab;`
   - Δηλώνει έναν πίνακα με το όνομα `tab` που έχει τρεις θέσεις (από 1 έως 3). Κάθε θέση του πίνακα μπορεί να πάρει τιμές από 1 έως 10.

3. `constraint forall(i in 1..2)`
   - Εισάγει έναν περιορισμό που ισχύει για τις θέσεις 1 και 2 του πίνακα `tab`.

4. `(tab[i+1] = if mode = 1 then tab[i]`
   - Εάν η μεταβλητή `mode` είναι ίση με 1, τότε η τιμή του `tab[i+1]` (δηλαδή `tab[2]` για `i=1` και `tab[3]` για `i=2`) πρέπει να είναι ίση με την τιμή του `tab[i]`.

5. `else tab[i]-1 endif);`
   - Διαφορετικά, εάν η `mode` δεν είναι ίση με 1, τότε η τιμή του `tab[i+1]` πρέπει να είναι ίση με την τιμή του `tab[i]` μειωμένη κατά 1.

6. `solve satisfy;`
   - Αυτή η εντολή ζητά από τον επιλυτή περιορισμών να βρει μια λύση που ικανοποιεί όλους τους περιορισμούς.

### Τι θα κάνει το πρόγραμμα;

Το πρόγραμμα θα δημιουργήσει έναν πίνακα `tab` με τρεις θέσεις και θα ορίσει τις τιμές του βάσει της τιμής της μεταβλητής `mode`:

- Εάν `mode = 1`, τότε:
  - `tab[2]` θα είναι ίσο με `tab[1]`
  - `tab[3]` θα είναι ίσο με `tab[2]`

- Εάν `mode ≠ 1`, τότε:
  - `tab[2]` θα είναι ίσο με `tab[1] - 1`
  - `tab[3]` θα είναι ίσο με `tab[2] - 1`

Ο επιλυτής θα πρέπει να βρει μια λύση που ικανοποιεί αυτούς τους περιορισμούς. Για παράδειγμα, εάν `mode = 1` και `tab[1] = 5`, τότε `tab[2]` και `tab[3]` θα είναι επίσης 5. Εάν `mode ≠ 1` και `tab[1] = 5`, τότε `tab[2]` θα είναι 4 και `tab[3]` θα είναι 3.
