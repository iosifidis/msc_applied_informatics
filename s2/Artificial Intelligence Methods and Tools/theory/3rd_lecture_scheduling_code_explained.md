### Εξήγηση κώδικα χρονοπρογραμματισμός

- Υπάρχουν 8 εργασίες (JOBS) και 2 μηχανές (MACHINES).   
- Κάθε εργασία έχει   
  - μια προκαθορισμένη διάρκεια di, i ε JOBS   
  - απαιτεί συγκεκριμένη μηχανή ri = j, j ε MACHINES   
- Περιορισμοί διάταξης (μερικής) μεταξύ των εργασιών.   
  - (i,j) => Ai << Aj   
- Μηχανές χωρητικότητας 1 (disjunctive scheduling)   
- Ποιοι είναι οι χρόνοι έναρξης των εργασιών ώστε να ελαχιστοποιείται η διάρκεια του συνολικού έργου (makespan);   

- Εργασία A1 με d1 = 5, r1 = M1, διάταξη (1,2)   
- Εργασία A2 με d2 = 4, r2 = M2, διάταξη (1,2)   
- Εργασία A3 με d3 = 7, r3 = M2, διάταξη (3,5)   

- Αναπαράσταση σε MiniZinc, με χρήση array:
```minizinc
%% Number of tasks
n = 8;

%% respective durations
duration = [5,4,7,1,9,6,7,8];

%% Orderings
ords = 3;

%% Ordering (tuples are represeted in a two dimensional array.
order = [|1,2|3,5|7,8|];
```

- Για κάθε εργασία Ai, ορίζονται οι αντίστοιχες μεταβλητές, και ο
περιορισμός (χρόνος έναρξης + διάρκεια = χρόνος λήξης):   
```
forall(j in JOBS)(end[j] = start[j] + duration[j]);
```

#### **Διάταξη**
- Περιορισμοί διάταξης (μερικής) μεταξύ των εργασιών (πχ η εργασία 1 πρέπει να λάβει χώρα πριν την εργασία 2 -> end[1] =< start[2] ).   
```minizinc
%% Ordering Constraints
order = [|1,2|3,5|7,8|];
constraint forall(o in ORDERS)
     (end[order[o,1]] <= start[order[o,2]] );
```

Εξήγηση
1. **Δήλωση της παραμέτρου `order`:**
   ```minizinc
   order = [|1,2|3,5|7,8|];
   ```
   Αυτή η γραμμή ορίζει μια δομή δεδομένων `order` που περιέχει ζεύγη αριθμών. Κάθε ζεύγος αντιπροσωπεύει μια σειρά εκτέλεσης εργασιών. Για παράδειγμα:
   - Η εργασία 1 πρέπει να ολοκληρωθεί πριν από την εργασία 2.
   - Η εργασία 3 πρέπει να ολοκληρωθεί πριν από την εργασία 5.
   - Η εργασία 7 πρέπει να ολοκληρωθεί πριν από την εργασία 8.

2. **Περιορισμός `forall`:**
   ```minizinc
   constraint forall(o in ORDERS)
      (end[order[o,1]] <= start[order[o,2]] );
   ```
   Αυτή η γραμμή ορίζει έναν περιορισμό που πρέπει να ικανοποιείται για όλα τα ζεύγη εργασιών που ορίζονται στη δομή `order`. Αναλύοντας τον περιορισμό:
   - `forall(o in ORDERS)`: Αυτό σημαίνει ότι ο περιορισμός πρέπει να ικανοποιείται για κάθε στοιχείο `o` στο σύνολο `ORDERS`.
   - `end[order[o,1]] <= start[order[o,2]]`: Αυτός ο περιορισμός σημαίνει ότι η ώρα λήξης (`end`) της πρώτης εργασίας στο ζεύγος `order[o]` πρέπει να είναι μικρότερη ή ίση με την ώρα έναρξης (`start`) της δεύτερης εργασίας στο ίδιο ζεύγος.

Σε απλά λόγια, αυτός ο περιορισμός διασφαλίζει ότι κάθε εργασία σε ένα ζεύγος πρέπει να ολοκληρωθεί πριν ξεκινήσει η επόμενη εργασία στο ίδιο ζεύγος.

#### **Απαιτήσεις σε Μηχανές**
- Κάθε εργασία απαιτεί για την υλοποίησής της μια συγκεκριμένη μηχανή.   
- Άρα χωρίζουμε τις εργασίες σε σύνολα ανάλογα με την μηχανή η οποία πρέπει να τις εκτελέσει.   
```minizinc
%% job Allocation
machineA = {1,4,5,7};
machineB = {2,3,6,8};
```

Αυτός ο κώδικας είναι ένα απλό παράδειγμα κατανομής εργασιών σε δύο μηχανές, την `machineA` και την `machineB`.

1. **`machineA = {1,4,5,7};`**:
   - Αυτή η γραμμή ορίζει ένα σύνολο με το όνομα `machineA` που περιέχει τις εργασίες (jobs) με τα αναγνωριστικά (IDs) 1, 4, 5 και 7. Αυτό σημαίνει ότι αυτές οι εργασίες έχουν ανατεθεί στη μηχανή A.

2. **`machineB = {2,3,6,8};`**:
   - Αυτή η γραμμή ορίζει ένα σύνολο με το όνομα `machineB` που περιέχει τις εργασίες με τα αναγνωριστικά 2, 3, 6 και 8. Αυτό σημαίνει ότι αυτές οι εργασίες έχουν ανατεθεί στη μηχανή B.
   
#### Χωρητικότητα Μηχανών
Μηχανές Χωρητικότητα 1. Δεν πρέπει δύο εργασίες να αλληλεπικαλύπτονται.
```
endi <= startj \/ endj <= starti
```

**Ο περιορισμός disjunctive**
```minizinc
%% Machine Constraints
include "globals.mzn";
constraint disjunctive([start[i] | i in machineA],
    [duration[i] | i in machineA]);
constraint disjunctive([start[i] | i in machineB],
    [duration[i] | i in machineB]);
```

Εξήγηση:

```minizinc
constraint disjunctive([start[i] | i in machineA], [duration[i] | i in machineB]);
```
Αυτή η γραμμή ορίζει έναν περιορισμό χρησιμοποιώντας τη συνάρτηση `disjunctive`. Ο περιορισμός `disjunctive` χρησιμοποιείται για να διασφαλίσει ότι δύο ή περισσότερες εργασίες δεν εκτελούνται ταυτόχρονα στην ίδια μηχανή.

- `[start[i] | i in machineA]`: Αυτή η λίστα περιέχει τις χρονικές στιγμές έναρξης (`start`) όλων των εργασιών που εκτελούνται στη μηχανή `machineA`.
- `[duration[i] | i in machineA]`: Αυτή η λίστα περιέχει τις διάρκειες (`duration`) όλων των εργασιών που εκτελούνται στη μηχανή `machineA`.

Ο περιορισμός `disjunctive` διασφαλίζει ότι καμία εργασία στη μηχανή `machineA` δεν ξεκινάει πριν ολοκληρωθεί η προηγούμενη εργασία στην ίδια μηχανή.

```minizinc
constraint disjunctive([start[i] | i in machineB], [duration[i] | i in machineB]);
```
Αυτή η γραμμή είναι ανάλογη με την προηγούμενη, αλλά αφορά τη μηχανή `machineB`.

- `[start[i] | i in machineB]`: Αυτή η λίστα περιέχει τις χρονικές στιγμές έναρξης (`start`) όλων των εργασιών που εκτελούνται στη μηχανή `machineB`.
- `[duration[i] | i in machineB]`: Αυτή η λίστα περιέχει τις διάρκειες (`duration`) όλων των εργασιών που εκτελούνται στη μηχανή `machineB`.

Ο περιορισμός `disjunctive` διασφαλίζει ότι καμία εργασία στη μηχανή `machineB` δεν ξεκινάει πριν ολοκληρωθεί η προηγούμενη εργασία στην ίδια μηχανή.

Συνοψίζοντας, αυτός ο κώδικας ορίζει περιορισμούς για δύο μηχανές (`machineA` και `machineB`) ώστε να διασφαλίσει ότι οι εργασίες σε κάθε μηχανή δεν εκτελούνται ταυτόχρονα.

#### Συνάρτηση Βελτιστοποίησης
Ελαχιστοποίηση της συνολικής διάρκειας έργου;
```minizinc
%% decisions
var int: makespan;
constraint makespan = max(end);

solve minimize makespan;
```

### ΣΥΝΟΛΙΚΟΣ ΚΩΔΙΚΑΣ
```minizinc
%%% Scheduling Example
int: n;
set of int : JOBS = 1..n;

%% orderings
int : ords ;
set of int : ORDERS = 1..ords;

%% Array holding task durations.
array[JOBS] of int: duration;
array[ORDERS,1..2] of int: order;

%% maximum project duration
int : max_duration = sum(duration);
set of int : TIME = 0..max_duration;

%% job Allocation
set of int : machineA;
set of int : machineB;

%% decisions
array[JOBS] of var TIME: start;
array[JOBS] of var TIME: end;

%%
constraint forall(j in JOBS)(end[j] = start[j] + duration[j]);

%% ordering
constraint forall(o in ORDERS) (end[order[o,1]] <=
start[order[o,2]] );

include "globals.mzn";
constraint disjunctive([start[i] | i in machineA],
                [duration[i] | i in machineA]);
constraint disjunctive([start[i] | i in machineB],
                [duration[i] | i in machineB]);

%%% Project Ends
var int: makespan;
constraint makespan = max(end);

solve minimize makespan;
```

**ΑΠΟΤΕΛΕΣΜΑ**
```
start = [7, 13, 0, 21, 12, 7, 0, 17];
end = [12, 17, 7, 22, 21, 13, 7, 25];
makespan = 25;
```
