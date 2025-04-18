# Ικανοποίηση Περιορισμών (Constraint Satisfaction Problems - CSP)

---

### **1. Εισαγωγή στην Ικανοποίηση Περιορισμών (CSP)**  
Τα προβλήματα ικανοποίησης περιορισμών αφορούν καταστάσεις όπου δεν γνωρίζουμε την τελική λύση εκ των προτέρων, αλλά γνωρίζουμε τις ιδιότητές της.  

#### **Παραδείγματα εφαρμογών:**
- **Προγραμματισμός πόρων** (resource allocation)  
- **Χρονοπρογραμματισμός** (scheduling)  
- **Δρομολόγηση εργασιών** (planning)  

#### **Μοντελοποίηση ενός CSP προβλήματος**  
Ένα CSP πρόβλημα αποτελείται από:  
- **Μεταβλητές**: \( V_1, V_2, ..., V_n \)  
- **Πεδία τιμών**: \( D_1, D_2, ..., D_n \), όπου κάθε μεταβλητή \( V_i \) ανήκει στο \( D_i \)  
- **Περιορισμούς**: \( c_1, c_2, ..., c_m \), οι οποίοι εκφράζουν σχέσεις μεταξύ των μεταβλητών  

🔹 Τα προβλήματα ικανοποίησης περιορισμών χαρακτηρίζονται από μεγάλο αριθμό μεταβλητών και πιθανών τιμών (πεδίων)
- Φαινόμενο της συνδυαστικής έκρηξης ([combinatorial explosion](https://youtu.be/T0KYF-hvr0A?si=cgAXq4wdGgyG7XOq)).
- Απαιτείται μείωση του χώρου αναζήτησης.

🔹 **Παράδειγμα CSP**:  

✅ Ένα βιομηχανικό μύλο έχει τέσσερα προϊόντα Α, Β, Γ, Δ και πρέπει να καθοριστεί η σειρά εισαγωγής τους.  

✅ Οι περιορισμοί είναι:  
  - Το Α εισάγεται μετά το Δ  
  - Το Γ πριν από το Β  
  - Το Β πριν από το Α  

✅ Μοντελοποίηση:  
  - Μεταβλητές: \( V_A, V_B, V_C, V_D \) με πεδία τιμών \([1,2,3,4]\)  
  - Περιορισμοί:  
    - \( V_A > V_D \)  
    - \( V_C < V_B \)  
    - \( V_B < V_A \)  

✅ Λύση: Δυνατοί συνδυασμοί τιμών που ικανοποιούν τους περιορισμούς.   
  - V_A = 4, V_B = 2, V_C, = 1, V_D = 3, δηλαδή η σειρά είναι: Γ, Β, Δ, Α   
  - V_A = 4, V_B = 3, V_C, = 1, V_D = 2, δηλαδή η σειρά είναι: Γ, Δ, Β, Α   
  - V_A = 4, V_B = 3, V_C, = 2, V_D = 1, δηλαδή η σειρά είναι: Δ, Γ, Β, Α   
---

### **2. Μέθοδοι Επίλυσης CSP**
1. **Παραγωγή και Δοκιμή (Generate & Test)**  
   - Δημιουργεί όλες τις πιθανές λύσεις και ελέγχει ποιες ικανοποιούν τους περιορισμούς.  
   - Πολύ αργή για μεγάλα προβλήματα λόγω συνδυαστικής έκρηξης.  
   
2. **Αλγόριθμοι Αναζήτησης**  
   - **Αναζήτηση κατά βάθος (Depth-first search - DFS)**  
   - **Οπισθοδρόμηση (Backtracking - BT)**: Προχωρά βήμα-βήμα και επιστρέφει σε προηγούμενη κατάσταση αν συναντήσει αδιέξοδο.  
   
3. **Ευρετικοί Αλγόριθμοι**  
   - **Hill Climbing**: Επιλέγει τη λύση που ελαχιστοποιεί τις συγκρούσεις. Εξετάζει ένα μεγάλο πλήθος "γειτονικών" καταστάσεων. Μπορεί να "πέσει" σε τοπικό ελάχιστο.  
   - **Ελάχιστες Συγκρούσεις (Min-conflicts heuristic)**: Ξεκινά από μια τυχαία ανάθεση τιμών και προσαρμόζει τις τιμές ώστε να ελαχιστοποιήσει τις παραβιάσεις των περιορισμών.  

🔹 **Προβλήματα:**   
  - Δεν είναι βέβαιο ότι θα καταλήξουν σε λύση.   
  - Δεν εγγυώνται την βέλτιστη λύση.   
  
🔹 **Πλεονεκτήματα**  
  - Ευκολία στην κωδικοποίηση   
  - Χειρισμός μεγάλων σε μέγεθος προβλημάτων   

🔹 **Παράδειγμα:**  

✅ Το πρόβλημα απαιτεί να τοποθετηθούν 8 βασίλισσες σε μια σκακιέρα 8x8 χωρίς να απειλούν η μια την άλλη. Στο πρόβλημα των 8 βασιλισσών, μια βασίλισσα μπορεί να επιτεθεί σε άλλη αν βρίσκεται:  
  - Στην ίδια γραμμή  
  - Στην ίδια στήλη  
  - Στην ίδια διαγώνιο  

✅ Ο αλγόριθμος **Min-conflicts** θα μετακινεί βασίλισσες σε θέσεις που ελαχιστοποιούν τις απειλές.

---

### **3. Διάδοση Περιορισμών & Έλεγχος Συνέπειας**
Για τη μείωση του χώρου αναζήτησης, χρησιμοποιούνται τεχνικές διάδοσης περιορισμών.

1. **Συνέπεια Τόξου (Arc Consistency - AC3 Algorithm)**  
   - Εξαλείφει τιμές από τα πεδία τιμών που δεν μπορούν να συμμετέχουν σε έγκυρη λύση.  
   - Για κάθε δυαδικό περιορισμό \( c(V_i, V_j) \), αν μια τιμή του \( V_i \) δεν έχει αντίστοιχη τιμή στο \( V_j \), διαγράφεται.  

2. **Προοπτικός Έλεγχος (Forward Checking)**  
   - Μετά από κάθε ανάθεση τιμής, ελέγχει και διαγράφει μη-συμβατές τιμές από τις υπόλοιπες μεταβλητές.  

🔹 **Παράδειγμα:**  

✅ Πρόβλημα προγραμματισμού εργασιών:  
  - **T1** πρέπει να γίνει μετά την **T4** (T1 > T4)  
  - **T3** πρέπει να ξεκινήσει πρώτη (Τ3 = 9)  
  - **T5** ξεκινά μετά τις 10 (Τ5 > 10)  
  - **Τ2** ξεκινά αμέσως με το τέλος της **Τ1** (T2 = T1 + 1)  
  - **T6** ολοκληρώνεται πριν τις 11 (Τ6 + 1 =< 11)  

✅ Εφαρμογή **AC3** και **Forward Checking** αφαιρεί μη έγκυρες τιμές από τα πεδία των μεταβλητών.

  - Τ1 in [12,13]   
  - Τ2 in [13,14]   
  - Τ3 in [9]   
  - Τ4 in [11,12]   
  - Τ5 in [11,12,13,14]   
  - Τ6 in [10]   
  
✅ Ξεκινά η αναζήτηση με ανάθεση τιμών στις μεταβλητές.

[12, 13, 9, 11, 14, 10]

---

### **4. Βελτιστοποίηση CSP Προβλημάτων**
1. **Αλγόριθμος Branch and Bound (B&B)**  
   - Διατηρεί το καλύτερο μέχρι στιγμής κόστος.  
   - Αν βρεθεί νέα λύση με χαμηλότερο κόστος, ενημερώνεται το ανώτερο όριο.  

🔹 **Παράδειγμα:**  

✅ Χρονοπρογραμματισμός συντήρησης σε μονάδα αερίου:  
  - Πρέπει να βρεθεί ένα πρόγραμμα με **το μικρότερο δυνατό χρόνο εκτέλεσης**.  

✅ Χρήση του **Branch and Bound** για την εύρεση της **βέλτιστης** λύσης.

---

### **5. Προγραμματισμός με Περιορισμούς (Constraint Programming - CP)**
✅ Νέα σχολή προγραμματισμού που βασίζεται στην περιγραφή περιορισμών και όχι ακολουθιακών βημάτων.  
✅ Χρησιμοποιεί γλώσσες όπως:
  - **MiniZinc** (γλώσσα μοντελοποίησης CSP προβλημάτων)
  - **Prolog με υποστήριξη περιορισμών (CLP)**
  - **IBM CPLEX, Gecode, Google OR-Tools**  

🔹 **Παράδειγμα MiniZinc:**  
```minizinc
var 1..4: A;
var 1..4: B;
var 1..4: C;
var 1..4: D;

constraint A > D;
constraint B > C;
constraint A > B;
include "alldifferent.mzn";
constraint alldifferent([A,B,C,D]);

solve satisfy;
```
✅ Περιγράφει το πρόβλημα CSP ενός βιομηχανικού μύλου και επιλύεται αυτόματα μέσω **MiniZinc**.

---

### **Συμπέρασμα**
🔹 Τα προβλήματα ικανοποίησης περιορισμών είναι ιδιαίτερα χρήσιμα σε συνδυαστικά προβλήματα όπως **προγραμματισμός πόρων**, **χρονοπρογραμματισμός** και **βελτιστοποίηση**.  
🔹 Οι **αλγόριθμοι αναζήτησης** και **ευρετικοί μηχανισμοί** μπορούν να βελτιώσουν την απόδοση.  
🔹 Η **διάδοση περιορισμών** και ο **έλεγχος συνέπειας** μειώνουν τον χώρο αναζήτησης.  
🔹 Ο **προγραμματισμός με περιορισμούς (CP)** αποτελεί μια σύγχρονη και αποδοτική προσέγγιση.

---

# **Βίντεο:**  

## Constraint Satisfaction Problems

[Constraint Satisfaction Problems (CSPs) 1 - Overview | Stanford CS221: AI (Autumn 2021)](https://youtu.be/-IO4fPO0rxk?si=5zDCdeyP3Be2w8Sf)    
[Constraint Satisfaction Problems (CSPs) 2 - Definitions | Stanford CS221: AI (Autumn 2021)](https://youtu.be/uj5wCcHsSlA?si=Q8RegQn_4bwqKsZ7)   
[Constraint Satisfaction Problems (CSPs) 3 - Examples | Stanford CS221: AI (Autumn 2021)](https://youtu.be/Tu6BiZhMDCc?si=SbWPYCUwQ35JC0FD)   
[Constraint Satisfaction Problems (CSPs) 4 - Dynamic Ordering | Stanford CS221: AI (Autumn 2021)](https://youtu.be/Lyu8VzbIe_A?si=0CHVDFw9ufvcs_hp)   
[Constraint Satisfaction Problems (CSPs) 5 - Arc Consistency | Stanford CS221: AI (Autumn 2021)](https://youtu.be/5rlIYGJdPy4?si=z6W8KPZrQzxbaeee)   
[Constraint Satisfaction Problems (CSPs) 6 - Beam Search | Stanford CS221: AI (Autumn 2021)](https://youtu.be/XuWMeIHGkus?si=TBfDbJLlggGCA9Y3)   
[Constraint Satisfaction Problems (CSPs) 7 - Local Search | Stanford CS221: AI (Autumn 2021)](https://youtu.be/VwZKPlK6jUg?si=egX0blcyHzv0YC09)   
[An Introduction To Constraint Programming - Jacob Allen](https://www.youtube.com/watch?v=1FJy-ubE7UE)    
[Combinatorial Explosion](https://youtu.be/T0KYF-hvr0A?si=cgAXq4wdGgyG7XOq)   
[Hill Climbing Search](https://youtu.be/VoUotaCmDk4?si=iakOKoK6NdkWAR0E)   
[Min-Conflicts Algorithm for AI Class 2011](https://youtu.be/n0si9JVZMQ0?si=9fIKB3XX3UX97zf9)   
