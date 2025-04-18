## Εισαγωγή στη MiniZinc

### 1. Εισαγωγή
Η MiniZinc είναι μια γλώσσα μοντελοποίησης περιορισμών (Constraint Programming - CP) υψηλού επιπέδου, που επιτρέπει τη δημιουργία μοντέλων τα οποία μπορούν να εκτελούνται σε πολλούς διαφορετικούς επιλυτές (solvers). Η MiniZinc μεταφράζεται σε FlatZinc, μια γλώσσα χαμηλότερου επιπέδου που χρησιμοποιείται από τους επιλυτές για την επίλυση του προβλήματος.

#### Πλεονεκτήματα της MiniZinc:
- Ανεξαρτησία από γλώσσες προγραμματισμού και υποστήριξη πολλών solvers.
- Γρήγορη ανάπτυξη και σύγκριση μοντέλων.
- Ευέλικτο και εκφραστικό σύστημα τύπων (int, float, bool, sets, arrays).

---

### 2. Βασικά Χαρακτηριστικά
#### Τύποι Δεδομένων
- **int**: Ακέραιοι αριθμοί (π.χ. 4..10).
- **float**: Πραγματικοί αριθμοί.
- **bool**: Λογικές τιμές (true/false).
- **string**: Αλφαριθμητικά (χρήσιμα μόνο για έξοδο).
- **sets**: Σύνολα τιμών.
- **arrays**: Πίνακες δεδομένων.
- **enum**: Απαριθμητοί τύποι για συμβολικές τιμές.

#### Μεταβλητές
- **par**: Παράμετροι, σταθερές κατά την εκτέλεση. (`par int : nQueens;` ή `int: nq = 42;`)   
- **var**: Μεταβλητές απόφασης, τιμές που υπολογίζονται από τον επιλύτη. (`var int : nPos;` ή `var 1..10: nums;` ή `var {10,20,30}: steps;`)    

**Δήλωση μεταβλητών**   
```
<Είδος Μεταβλητής> <Τύπος> : <Όνομα μεταβλητής> ;
```

---

### 3. Εκφράσεις και Περιορισμοί
#### Αριθμητικές Εκφράσεις
- Υποστηρίζονται πράξεις όπως `+`, `-`, `*`, `div`, `mod`.   
- Συναρτήσεις: `abs`, `min/2`, `max/2`, `pow/2`, `exp`, `log`.   
  -  Περιγραφή: [https://www.minizinc.org/resources.html](https://www.minizinc.org/resources.html)   

#### Μετατροπές τύπων
- `int2float`, `bool2float`, `bool2int`, `round`, `floor`, `ceil`   

#### Boolean Εκφράσεις
- Τελεστές σύγκρισης: `=`, `!=`, `>`, `<`, `>=`, `<=`.
- Λογικοί τελεστές: `/\` (AND), `\/` (OR), `->` (συνεπαγωγή), `<->` (διπλή συνεπαγωγή), `not` (άρνηση), `xor` (αποκλειστική διάζευξη).

#### Περιορισμοί (Constraints)
- Η δήλωση γίνεται με τη λέξη-κλειδί `constraint`.
- Οι περιορισμοί θεωρούνται ότι συνδυάζονται με λογική σύζευξη (AND).

#### Παράδειγμα Περιορισμού:
```
var int: x;
constraint x < 100;
solve maximize x;
```

#### Επίλυση
Δηλώσεις solve 
- Μία λύση: `solve satisfy;` (δίνει την πρώτη λύση. ΟΧΙ την βέλτιστη)   
- Βέλτιστη λύση (μέγιστο): `solve maximize <exp>;`   
- Βέλτιστη λύση (ελάχιστο): `solve minimize <exp>;`   

#### MiniZinc
- `_objective`: αποτίμηση της λύσης που βρήκε.   
- `==========`: σημαίνει ότι βρήκε την βέλτιστη λύση.   
- `++`: συνένωση αλφαριθμητικών.   
- `%`: σχόλιο μέχρι το τέλος της γραμμής.   
- `/* ... */`: σχόλια πολλαπλών γραμμών.   

#### Διαμόρφωση αποτελέσματος MiniZinc
- `x:\(<όνομα μεταβλητής>)`: Εμφανίζει x:\τιμή   
- `" "`: Πως θα εμφανίζεται το αποτέλεσμα.

**πχ**
```
output ["x:\(x),y:\(y) and z:\(z)." ++ "\n Problem solved."];
```

---

### 4. Σύνολα (Sets)
Τα σύνολα στην MiniZinc δηλώνονται ως `set of <type>`. Μπορούν να χρησιμοποιηθούν για τον ορισμό διακριτών τιμών.

Παράδειγμα:
```
set of int: NUMS = {10, 20, 30};
var set of int: values;    % ως μεταβλητές απόφασης
constraint card(values) >= 1;
```

**Περιορισμοί**: `card (cardinality), in (set inclusion), union, intersect, subset, superset, diff, symdiff`

---

### 5. Απαριθμητοί Τύποι (Enums)
Οι απαριθμητοί τύποι επιτρέπουν τον ορισμό συμβολικών τιμών.

- Σύνταξη: `enum <Όνομα> ={<item1>,<item2>,...,<itemn>};`

**Παράδειγμα**:
```
enum Color = {red, green, blue};
var Color: chosenColor;
```

**Συναρτήσεις**: 
- enum_next(Enum,x)   
- enum_prev(Enum,x)   
- to_enum(Enum,i)   
- enum2int(x)   

**Συναρτήσεις Συνόλων**:
- max(S)   
- min(S)   
- card(S)   

**ΠΛΕΟΝΕΚΤΗΜΑΤΑ**
- Διαχωρισμός μεταξύ διαφορετικών συνόλων σε ένα μοντέλο.   
- Ονοματοδοσία αντικειμένων   
- περισσότερο αναγνώσιμα μοντέλα   
- αποφυγή λαθών (έλεγχος τύπων)   

---

### 6. Πίνακες (Arrays)
Οι πίνακες υποστηρίζουν μία ή περισσότερες διαστάσεις. Χρησιμοποιούνται για αποθήκευση πολλών τιμών του ίδιου τύπου.

Παράδειγμα:
```
array[1..3] of int: numbers = [10, 20, 30];
```

* Ο τύπος μπορεί να είναι οτιδήποτε εκτός από array.

-> Πίνακες περισσότερων διαστάσεων:    

`array[ind_set1,ind_set2...] of <type> : <όνομα>;`

- Αρχικοποίηση πινάκων μιας διάστασης με λίστα.
- Αρχικοποίηση πινάκων 2 διαστάσεων με χρήση `|`.

---

### 7. Comprehensions
Οι συνταγές Comprehension επιτρέπουν την κατασκευή πινάκων και συνόλων με συμπαγή τρόπο.

Παράδειγμα:
```
array[1..5] of int: squares = [i * i | i in 1..5];
```

- **Generator**: `<μεταβλητή> in <Σύνολο>` πx. i in {1,2,3}, j in NN, κλπ.   
- **Expr**: Αριθμητική έκφραση ή Περιορισμός

Και αν θέλω το γινόμενο των στοιχείων **1 προς 1**;

```
set of int: NUMS = 1..3;
array[NUMS] of int : t1 = [1,2,3];
array[NUMS] of int : t2 = [10,20,30];

array[int] of int : resTab2 =
      [t1[i]*t2[j] | i in NUMS, j in NUMS where i = j];
```
Αποτέλεσμα: resTab2 = [10, 40, 90]

Στη γλώσσα μοντελοποίησης MiniZinc, οι εκφράσεις `{Expr | Generator}` και `[Expr | Generator]` χρησιμοποιούνται για τη δημιουργία συνόλων (sets) και πινάκων (arrays) αντίστοιχα. Η βασική διαφορά τους είναι ο τύπος δεδομένων που παράγουν και πώς αυτά τα δεδομένα οργανώνονται. Ας δούμε αναλυτικά:

### 1. `{Expr | Generator}`
- **Τύπος δεδομένων**: Παράγει ένα **σύνολο** (set).
- **Χρήση**: Χρησιμοποιείται για τη δημιουργία συνόλων από τις τιμές της έκφρασης `Expr`, που υπολογίζονται για κάθε στοιχείο του γεννήτορα `Generator`. Έχουν διάταξη από μικρότερο σε μεγαλύτερο και δεν έχουν διπλότυπα (ιδιότητες συνόλου).   
- **Παράδειγμα**:
  ```minizinc
  {i * 2 | i in 1..5}
  ```
  - Ο γεννήτορας `i in 1..5` παράγει τις τιμές `1, 2, 3, 4, 5`.
  - Η έκφραση `i * 2` υπολογίζεται για κάθε τιμή του `i`.
  - Το αποτέλεσμα είναι το σύνολο `{2, 4, 6, 8, 10}`.

### 2. `[Expr | Generator]`
- **Τύπος δεδομένων**: Παράγει έναν **πίνακα** (array).
- **Χρήση**: Χρησιμοποιείται για τη δημιουργία πινάκων από τις τιμές της έκφρασης `Expr`, που υπολογίζονται για κάθε στοιχείο του γεννήτορα `Generator`. Δεν έχουν διάταξη και έχουν διπλότυπα (ιδιότητες συνόλου).   
- **Παράδειγμα**:
  ```minizinc
  [i * 2 | i in 1..5]
  ```
  - Ο γεννήτορας `i in 1..5` παράγει τις τιμές `1, 2, 3, 4, 5`.
  - Η έκφραση `i * 2` υπολογίζεται για κάθε τιμή του `i`.
  - Το αποτέλεσμα είναι ο πίνακας `[2, 4, 6, 8, 10]`.

### Επιπλέον παραδείγματα

#### Παράδειγμα 1: Σύνολο vs Πίνακας
```minizinc
{i mod 2 | i in 1..5}  →  {0, 1}  (σύνολο, χωρίς διπλότυπα)
[i mod 2 | i in 1..5]  →  [1, 0, 1, 0, 1]  (πίνακας, με διπλότυπα και σειρά)
```

#### Παράδειγμα 2: Σύνολο με συνθήκη
```minizinc
{i | i in 1..10 where i mod 2 = 0}  →  {2, 4, 6, 8, 10}
```

#### Παράδειγμα 3: Πίνακας με συνθήκη
```minizinc
[i | i in 1..10 where i mod 2 = 0]  →  [2, 4, 6, 8, 10]
```

### Συμπέρασμα
- Χρησιμοποιήστε `{Expr | Generator}` όταν θέλετε να δημιουργήσετε ένα **σύνολο** (χωρίς διπλότυπα και χωρίς σειρά).
- Χρησιμοποιήστε `[Expr | Generator}` όταν θέλετε να δημιουργήσετε έναν **πίνακα** (με σειρά και δυνατότητα διπλότυπων).

---

#### Συναρτήσεις Λιστών & Συνόλων

**Λίστες αριθμών**
- `sum, product, min, max`

**Μεγάλο ενδιαφέρον**
- forall(<List>): οι περιορισμοί στη λίστα είναι σε σύζευξη.   
- exists(<List>): οι περιορισμοί στη λίστα είναι σε διάζευξη.   

**Παράδειγμα**
```minizinc
set of int : ALLNUMS = 1..30;
constraint forall([x > 0 | x in ALLNUMS ]);
constraint exists([x = 20 | x in ALLNUMS ]);
```
Ας δούμε πιο αναλυτικά:

- Περιορισμός για όλα τα στοιχεία του συνόλου να είναι μεγαλύτερα από το 0:   

```
% constraint forall([x > 0 | x in ALLNUMS ]);
constraint forall(x in ALLNUMS)(x > 0) ;
```
Αυτός ο περιορισμός δηλώνει ότι για όλα τα στοιχεία του συνόλου `ALLNUMS`, κάθε αριθμός πρέπει να είναι μεγαλύτερος από το 0. Η σύνταξη `forall(x in ALLNUMS)(x > 0)` είναι μια έκφραση που διασφαλίζει ότι όλοι οι αριθμοί από το 1 έως το 30 θα είναι μεγαλύτεροι από το 0 (δηλαδή, αυτός ο περιορισμός δεν προσθέτει νέες συνθήκες, επειδή το σύνολο είναι ήδη ορισμένο από το 1 έως το 30).

- Περιορισμός για να υπάρχει τουλάχιστον το 20 στο σύνολο:   

```
% constraint exists([x = 20 | x in ALLNUMS ]);
constraint exists(x in ALLNUMS)(x = 20);
```
Αυτός ο περιορισμός δηλώνει ότι πρέπει να υπάρχει τουλάχιστον ένα στοιχείο στο σύνολο `ALLNUMS` που να είναι ίσο με 20. Η έκφραση `exists(x in ALLNUMS)(x = 20)` σημαίνει "υπάρχει τουλάχιστον ένα `x` στο σύνολο `ALLNUMS` για το οποίο ισχύει ότι `x = 20`".

---

### 8. Ευρετικές Συναρτήσεις
Οι ευρετικές συναρτήσεις χρησιμοποιούνται για τη βελτιστοποίηση της αναζήτησης. Περιλαμβάνουν στρατηγικές επιλογής μεταβλητών και τιμών.

#### Επιλογή Μεταβλητών:
- `input_order`: Σύμφωνα με τη σειρά εισόδου   
- `first_fail`: Μικρότερο πεδίο τιμών, `anti_first_fail`: το μεγαλύτερο πεδίο   
- `largest`, `smallest`: Μεγαλύτερη ή μικρότερη τιμή αντίστοιχα   
- `most_constrained`: Μικρότερο πεδίο, και σε περίπτωση "ισοψηφίας" με εκείνη με τους περισσότερους περιορισμούς   
- `dom_w_deg`: Επιλογή μεταβλητής με το μεγαλύτερο πεδίο/αριθμό περιορισμών σταθμισμένων με το πόσο συχνά προκάλεσαν αποτυχία   
- `occurrence`: συμμετέχει στους περισσότερους περιορισμούς

#### Επιλογή τιμών:
- `indomain`: με την σειρά   
- `indomain_max`: μεγαλύτερη τιμή, `indomain_min`: μικρότερη τιμή, `indomain_median`: τιμή στη "μέση", `indomain_middle`: κοντά στην μέση τιμή των ορίων, `indomain_random`: τυχαία τιμή   
- `indomain_interval`: Αν το πεδίο αποτελείται από πολλά συνεχόμενα διαστήματα, επέλεξε το πρώτο, αλλιώς διαχώρισε το πεδίο στα δύο   
- `indomain_split`: διαχώρισε το πεδίο στα δύο, αποκλείοντας το "πάνω" μισό, `indomain_reverse_split`: αποκλείοντας το κάτω μισό", `indomain_split_random`   
- `outdomain_max`: απέκλεισε την μεγαλύτερη τιμή από το πεδίο), `outdomain_min`: μικρότερη, `outdomain_random`: τυχαία, `outdomain_median`: μεσαία   

---

### 9. Έλεγχος Αναζήτησης
Η διαδικασία επίλυσης περιλαμβάνει τον καθορισμό στρατηγικών αναζήτησης μέσω εντολών όπως:
```
solve :: int_search([X, Y], input_order, indomain) satisfy;
```

---

### 10. Το Πρόβλημα των Ν-Βασιλισσών
```
int: queens;
set of int: QUEENS = 1..queens;
set of int: BOARD = 1..queens;
array[QUEENS] of var BOARD : row;

include "all_different.mzn";
constraint all_different(row);
constraint forall(q in QUEENS) (forall(j in 1..queens where q+j <= queens) ((row[q] + j != row[q+j] /\ row[q] - j != row[q+j]))) ;

solve :: int_search(row,input_order,indomain,complete) satisfy;
```

---

### if then else endif
Αποτέλεσμα υπό συνθήκη (και όχι διακλάδωση υπο συνθήκη)

`if <συνθήκη> then <αποτέλεσμα1> else <αποτέλεσμα2> endif`

πχ

```
constraint forall(i in 1..3)
    (if mode = 1 then tab[i] = 1
        else tab[i] = 2 endif);

%%% Ισοδύναμο
constraint forall(i in 1..3)
    (tab[i] = if mode = 1 then 1 else 2 endif);
```

