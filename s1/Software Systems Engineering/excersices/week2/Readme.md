# Object-Oriented Design Principles

## 1. Σχεδίαση
Η σχεδίαση οποιουδήποτε τεχνικού έργου περιλαμβάνει:  
- **Αποσύνθεση** του συστήματος σε τμήματα (μονάδες).  
- **Καθορισμός σχέσεων** μεταξύ των τμημάτων.  
- **Ανάθεση αρμοδιοτήτων** σε κάθε τμήμα.  
- **Επικύρωση** ότι όλα τα τμήματα μαζί επιτυγχάνουν τους στόχους του συστήματος.  

Συμπτώματα κακής σχεδίασης:
- **Δυσκαμψία**: Δυσκολία στις αλλαγές, καθώς επηρεάζουν πολλά τμήματα.  
- **Ευθραυστότητα**: Μικρές αλλαγές προκαλούν σφάλματα σε διάφορα σημεία.  
- **Ακινησία**: Δυσκολία διαχωρισμού συστατικών για επαναχρησιμοποίηση.  
- **Έλλειψη ρευστότητας**: Λανθασμένες αλλαγές είναι ευκολότερες από τις σωστές.  
- **Περίττη πολυπλοκότητα**: Περιλαμβάνονται μη χρήσιμα στοιχεία.  
- **Περίττη επανάληψη**: Υπάρχουν επαναλαμβανόμενες δομές που μπορούν να ενοποιηθούν.  
- **Αδιαφάνεια**: Δυσκολία κατανόησης μονάδων.  

---

## 2. Αρχές Αντικειμενοστραφούς Σχεδίασης
Οι παρακάτω αρχές προλαμβάνουν τα συμπτώματα κακής σχεδίασης.

### 2.1 Αρχή Ενσωμάτωσης (Encapsulation Principle)
**Ορισμός**: Η εσωτερική κατάσταση ενός αντικειμένου πρέπει να τροποποιείται μόνο μέσω της δημόσιας διασύνδεσής του.

**Πλεονεκτήματα**:
- Διατήρηση εγκυρότητας αντικειμένων.  
- Κατασκευή αντικειμένων με έγκυρα δεδομένα και μεθόδους που δεν παραβιάζουν αναλλοίωτες συνθήκες.  

### 2.2 Αρχή Χαμηλής Σύζευξης (Low Coupling Principle)
**Ορισμός**: Οι μονάδες πρέπει να έχουν τη μικρότερη δυνατή σύζευξη.

**Πλεονεκτήματα**:
- Ευκολότερη υλοποίηση, έλεγχος και συντήρηση.  
- Επαναχρησιμοποίηση χωρίς να επηρεάζονται άλλες μονάδες.  

**Μέτρηση**:
- Χρήση μετρικής "Coupling Between Objects" (CBO): Μετρά τον αριθμό των εξαρτήσεων μεταξύ κλάσεων.

### 2.3 Αρχή Μοναδικής Αρμοδιότητας (Single Responsibility Principle - SRP)
**Ορισμός**: Μία κλάση πρέπει να έχει μόνο έναν λόγο να αλλάξει.

**Πλεονεκτήματα**:
- Αποφυγή σύζευξης αρμοδιοτήτων.  
- Μικρότερη πολυπλοκότητα μέσω διαχωρισμού αρμοδιοτήτων σε διαφορετικές κλάσεις.  

### 2.4 Αρχή Ανοιχτής-Κλειστής Σχεδίασης (Open-Closed Principle - OCP)
**Ορισμός**: Οι οντότητες λογισμικού πρέπει να είναι ανοιχτές για επέκταση αλλά κλειστές για τροποποίηση.

**Προσέγγιση**:
- Χρήση αφαιρέσεων και σχεδιαστικών προτύπων όπως Strategy και Template Method.

**Παραβίαση OCP**:
- Τροποποίηση υπάρχοντος κώδικα αντί για επέκτασή του.

### 2.5 Αρχή Υποκατάστασης Liskov (Liskov Substitution Principle - LSP)
**Ορισμός**: Οι παράγωγες κλάσεις πρέπει να μπορούν να αντικαταστήσουν τις βασικές χωρίς να επηρεάζεται η ορθή λειτουργία του συστήματος.

**Παραδείγματα Παραβίασης**:
- Χρήση κληρονομικότητας που αλλάζει τη συμπεριφορά του συστήματος.

**Λύση**:
- Σχεδιασμός με βάση τις αναλλοίωτες συνθήκες και τη συμβατότητα με τη βασική κλάση.

### 2.6 Αρχή Αντιστροφής Εξαρτήσεων (Dependency Inversion Principle - DIP)
**Ορισμός**:
- Οι μονάδες υψηλού επιπέδου δεν πρέπει να εξαρτώνται από μονάδες χαμηλού επιπέδου.  
- Οι λεπτομέρειες υλοποίησης πρέπει να εξαρτώνται από αφαιρέσεις.  

**Πρακτικές**:
- Διαστρωμάτωση συστημάτων.  
- Χρήση διασυνδέσεων για τον περιορισμό των εξαρτήσεων.  

### 2.7 Αρχή Διαχωρισμού Διασυνδέσεων (Interface Segregation Principle - ISP)
**Ορισμός**: Πολλές εξειδικευμένες διασυνδέσεις είναι προτιμότερες από μία γενική.

**Πλεονεκτήματα**:
- Αποφυγή εξαναγκασμού πελατών να εξαρτώνται από μεθόδους που δεν χρησιμοποιούν.  
- Μείωση περιττής πολυπλοκότητας μέσω του διαχωρισμού των διασυνδέσεων.  

---

## 3. Συμπεράσματα
Η εφαρμογή αυτών των αρχών:
- Προάγει τη σωστή σχεδίαση λογισμικού.  
- Μειώνει την πολυπλοκότητα και το κόστος συντήρησης.  
- Αυξάνει την ευελιξία και την επαναχρησιμοποίηση του κώδικα.  

Η κατανόηση και εφαρμογή τους απαιτεί εμπειρία αλλά αποτελεί το θεμέλιο για τη δημιουργία ποιοτικού λογισμικού.

