### Σημειώσεις για τον Χρονοπρογραμματισμό στη Μοντελοποίηση Προβλημάτων

---

#### **1. Εισαγωγή στον Χρονοπρογραμματισμό**
Ο χρονοπρογραμματισμός (scheduling) αφορά τη διαδικασία ανάθεσης πόρων σε εργασίες εντός ενός χρονικού διαστήματος, με βάση συγκεκριμένους περιορισμούς και ένα κριτήριο βελτιστοποίησης. Χρησιμοποιείται σε πολλούς τομείς, όπως η βιομηχανία, η λογιστική και η πληροφορική.

**Βασικά Στοιχεία:**
- **Εργασίες (Jobs):** Έχουν διάρκεια, απαιτήσεις σε πόρους και χρονικά όρια.
- **Πόροι (Resources):** Μπορεί να είναι επαναχρησιμοποιήσιμοι (reusable) ή καταναλωσιμοί (consumable).
- **Στόχος:** Ελαχιστοποίηση ή μεγιστοποίηση ενός μεγέθους (π.χ., συνολικού χρόνου, κόστους).

---

#### **2. Χαρακτηριστικά Εργασιών**
- **Απαιτήσεις σε Πόρους (r):** Καθορίζουν ποιους πόρους και σε ποια ποσότητα χρειάζεται μια εργασία.
- **Χρονικά Όρια:**
  - **Χρόνος Έναρξης (s):** Εντός ενός διαστήματος [est, lst].
  - **Χρόνος Ολοκλήρωσης (c):** Εντός ενός διαστήματος [ect, lct].
  - **Διάρκεια (d):** Μπορεί να εξαρτάται από τον πόρο (processing time pij).
- **Βάρος (w):** Δηλώνει τη σημασία μιας εργασίας σε σχέση με άλλες.

**Είδη Εργασιών:**
- **Μη προεκτοπιστικές (non-preemptive):** Δεν διακόπτονται (di= ci - si).
- **Προεκτοπιστικές (preemptive):** Μπορούν να διακοπούν και να επανεκκινήσουν (di = Σ(dki) ≤ ci - si | k in I}).
- **Ελαστικές (elastic):** Η διάρκεια και οι απαιτήσεις σε πόρους μπορούν να αλλάξουν, με σταθερό γινόμενο (Ei = di * ri).

---

#### **3. Τύποι Πόρων**
- **Επαναχρησιμοποιήσιμοι (reusable):** Δεσμεύονται προσωρινά και ελευθερώνονται μετά τη χρήση (π.χ., μηχανές).
- **Καταναλωσιμοί (consumable):** Καταναλώνονται ή παράγονται κατά την εκτέλεση της εργασίας (π.χ., πρώτες ύλες).

---

#### **4. Περιορισμοί Χρονοπρογραμματισμού**
- **Disjunctive Scheduling:**  
  Χρησιμοποιείται όταν η χωρητικότητα των πόρων είναι 1. Δύο εργασίες που απαιτούν τον ίδιο πόρο δεν μπορούν να αλληλεπικαλύπτονται:  
  c1 ≤ s2 \/ c2 ≤ s1

- **Cumulative Scheduling:**  
  Χρησιμοποιείται όταν η χωρητικότητα των πόρων είναι > 1. Το άθροισμα των πόρων που καταναλώνονται σε κάθε χρονική στιγμή δεν πρέπει να ξεπερνά τη χωρητικότητα:  
  \[ \sum_{A_i \in A} r(A_i, t) \leq \text{cap} \quad \forall t \in [\min(\text{start}_A), \max(\text{end}_A)] \]

**Περιορισμοί στη MiniZinc:**
- `disjunctive(StartTimes, Durations)`: Εξασφαλίζει ότι οι εργασίες δεν αλληλεπικαλύπτονται.
- `cumulative(StartTimes, Durations, Resources, ResourceLimit)`: Ελέγχει τη συνολική χρήση πόρων.

---

#### **5. Συναρτήσεις Βελτιστοποίησης**
- **Makespan:** Ελαχιστοποίηση του συνολικού χρόνου ολοκλήρωσης:  
  \[ \max(c_1, c_2, \ldots, c_n) \]
- **Συνολικό Βαρυμετρικό Κόστος:**  
  \[ \sum (w_i \times c_i) \]
- **Μέγιστη Αργοπορία (Lateness):**  
  \[ \max(c_i - \delta_i) \]
- **Μέγιστη Καθυστέρηση (Tardiness):**  
  \[ \max(0, c_i - \delta_i) \]

---

#### **6. Μοντελοποίηση σε MiniZinc**
**Παράδειγμα Disjunctive Scheduling:**
```prolog
include "globals.mzn";
constraint disjunctive([start[i] | i in machineA], [duration[i] | i in machineA]);
solve minimize max(end);
```

**Παράδειγμα Cumulative Scheduling:**
```prolog
constraint cumulative(
    [start[i] | i in allocated_to[m]],
    [duration[i] | i in allocated_to[m]],
    [resource[i] | i in allocated_to[m]],
    capacity[m]
);
```

---

#### **7. Τεχνικές Διάδοσης Περιορισμών**
- **Edge Finding:**  
  Αναγνωρίζει εργασίες που πρέπει να εκτελεστούν πριν ή μετά από άλλες, με βάση τη χωρητικότητα των πόρων.  
  \[ \text{let}_\Omega - \text{est}_\Omega < d_\Omega \implies \text{Ασυνέπεια} \]

- **TimeTable Algorithm:**  
  Χρησιμοποιεί τα "compulsory parts" των εργασιών για να ελέγξει τη χρήση πόρων και να ενημερώσει τα χρονικά πεδία.

- **Sweep Algorithm:**  
  Διατρέχει τα χρονικά σημεία ενδιαφέροντος για να ενημερώσει τα `est` και `let` των εργασιών.

---

#### **8. Συμπεράσματα**
- Ο χρονοπρογραμματισμός είναι ένα ισχυρό εργαλείο για τη βελτιστοποίηση της χρήσης πόρων.
- Η επιλογή μεταξύ disjunctive και cumulative scheduling εξαρτάται από τη χωρητικότητα των πόρων.
- Οι τεχνικές διάδοσης περιορισμών (όπως edge finding) βελτιώνουν την αποδοτικότητα της επίλυσης.

---

#### **9. Πρακτικές Συμβουλές**
- **Επαλήθευση Δεδομένων:** Βεβαιωθείτε ότι οι πίνακες durations και resources είναι συνεπείς.
- **Αποσφαλμάτωση:** Χρησιμοποιήστε ενδιάμεσες εκτυπώσεις για να ελέγξετε τις τιμές των μεταβλητών.
- **Βελτιστοποίηση:** Εξετάστε τη χρήση redundant constraints για να επιταχύνετε τη διαδικασία επίλυσης.
