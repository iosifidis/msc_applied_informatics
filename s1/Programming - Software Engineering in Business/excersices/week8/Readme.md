# Χρήση Αφαιρέσεων και Διασυνδέσεων

#### Ορισμός Αφαίρεσης:
Η αφαίρεση αποτελεί τη διαδικασία ενσωμάτωσης των κοινών χαρακτηριστικών και συμπεριφορών ενός συνόλου αντικειμένων σε μια υπερκλάση.
- **Παραδείγματα**:
  - Συστήματα που χρησιμοποιούν αφηρημένες κλάσεις για κοινές ιδιότητες, όπως π.χ. στην κλάση `Φοιτητής`, όπου κοινά χαρακτηριστικά των υποκλάσεων (προπτυχιακοί, μεταπτυχιακοί, διδακτορικοί) κληρονομούνται από μια γενική κλάση.

#### Πλεονεκτήματα:
- Αποφυγή επανάληψης κώδικα.
- Ενίσχυση της επαναχρησιμοποίησης και της ευελιξίας του κώδικα.

#### Προβλήματα:
- Η προσθήκη νέων τύπων δεδομένων συχνά απαιτεί αλλαγές στον πηγαίο κώδικα, παραβιάζοντας την Αρχή Ανοικτής-Κλειστής Σχεδίασης (Open-Closed Principle).
- Αυξάνονται τα κόστη ανάπτυξης και τα περιθώρια σφαλμάτων.

---

### Διασύνδεση (Interface) ως Λύση

#### Τι είναι η Διασύνδεση:
Η διασύνδεση είναι μια σύμβαση που ορίζει αφηρημένες μεθόδους τις οποίες κάθε κλάση που την υλοποιεί υποχρεούται να παρέχει.

- **Παραδείγματα**:
  - Η διασύνδεση `Measurable` απαιτεί την υλοποίηση της μεθόδου `getMeasure()`, η οποία επιστρέφει ένα ποσοτικό χαρακτηριστικό ενός αντικειμένου.

#### Πλεονεκτήματα:
- Δημιουργία ευέλικτου και επεκτάσιμου κώδικα.
- Δυνατότητα επαναχρησιμοποίησης κλάσεων σε διαφορετικά σενάρια.
- Συμβατότητα με την Αρχή Ανοικτής-Κλειστής Σχεδίασης.

---

### Υλοποίηση στη Java

1. **Διασύνδεση `Measurable`:**
   ```java
   public interface Measurable {
       double getMeasure();
   }
   ```

2. **Τροποποιημένη κλάση `DataSet`:**
   ```java
   public class DataSet {
       private int counter;
       private double sum;
       private Measurable maximum;
       private Measurable minimum;

       public DataSet() {
           sum = 0;
           counter = 0;
           maximum = null;
           minimum = null;
       }

       public void add(Measurable item) {
           sum += item.getMeasure();
           if (counter == 0) {
               maximum = item;
               minimum = item;
           } else if (maximum.getMeasure() < item.getMeasure()) {
               maximum = item;
           } else if (minimum.getMeasure() > item.getMeasure()) {
               minimum = item;
           }
           counter++;
       }

       public double getAverage() {
           return counter == 0 ? 0 : sum / counter;
       }

       public Measurable getMaximum() {
           return maximum;
       }

       public Measurable getMinimum() {
           return minimum;
       }
   }
   ```

3. **Παράδειγμα Υποκλάσεων που υλοποιούν τη διασύνδεση:**
   ```java
   public class BankAccount implements Measurable {
       private double balance;

       public BankAccount(double balance) {
           this.balance = balance;
       }

       @Override
       public double getMeasure() {
           return balance;
       }
   }

   public class Student implements Measurable {
       private double GPA;

       public Student(double GPA) {
           this.GPA = GPA;
       }

       @Override
       public double getMeasure() {
           return GPA;
       }
   }
   ```

---

### Συμπεράσματα

1. **Αποφυγή Επαναλαμβανόμενου Κώδικα:**
   Με τη χρήση διασυνδέσεων, όπως το `Measurable`, η κλάση `DataSet` μπορεί να χειριστεί αντικείμενα διαφορετικών τύπων χωρίς αλλαγές στον πηγαίο κώδικα.

2. **Επέκταση Λειτουργικότητας:**
   Η προσθήκη νέων τύπων, όπως `Coin` ή `Athlete`, απαιτεί μόνο την υλοποίηση της μεθόδου `getMeasure()` στη νέα κλάση.

3. **Συμμόρφωση με την Αρχή Ανοικτής-Κλειστής Σχεδίασης:**
   Η σχεδίαση επιτρέπει την προσθήκη νέας λειτουργικότητας μέσω νέου κώδικα, χωρίς αλλαγές στις υπάρχουσες κλάσεις.


