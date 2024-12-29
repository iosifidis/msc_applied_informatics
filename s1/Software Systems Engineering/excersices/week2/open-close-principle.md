# Αρχή Ανοικτής-Κλειστής Σχεδίασης (Open-Closed Principle)

---

## Βασικές Έννοιες

- **Αρχή Ανοικτής-Κλειστής Σχεδίασης:**
  - Οι μονάδες λογισμικού (π.χ. κλάσεις) πρέπει να είναι:
    - **Ανοικτές για επέκταση**: Δυνατότητα προσθήκης νέας λειτουργικότητας.
    - **Κλειστές για τροποποίηση**: Ο υπάρχων κώδικας δεν αλλάζει.

- **Σημασία:**
  - Υποστηρίζει την εύκολη συντήρηση λογισμικού.
  - Ελαχιστοποιεί την πιθανότητα εισαγωγής σφαλμάτων κατά τις αλλαγές.
  - Ευνοεί τη χρήση αφαιρέσεων (abstractions) για τη γενίκευση της λειτουργικότητας.

---

## Τεχνικές Υλοποίησης

1. **Χρήση Αφαιρέσεων:**
   - Αφαιρετικοί τύποι όπως `abstract classes` και `interfaces` επιτρέπουν τον "χειρισμό" κοινών χαρακτηριστικών.
   - Παράδειγμα:
     - Μια αφηρημένη κλάση "Φοιτητής" για κλάσεις όπως "Προπτυχιακός", "Μεταπτυχιακός".

2. **Διασυνδέσεις:**
   - Ορισμός κοινών μεθόδων (π.χ. `getQuantity()`) για την επίτευξη γενίκευσης.
   - Δεσμεύουν τις υποκλάσεις να παρέχουν συγκεκριμένη υλοποίηση.

3. **Πολυμορφισμός:**
   - Επιτρέπει τη δυναμική επιλογή της κατάλληλης υλοποίησης σε χρόνο εκτέλεσης.
   - Βελτιώνει την ευελιξία του λογισμικού.

---

## Παράδειγμα Υλοποίησης

### Βασική Δομή
- Κλάση `Statistics` που χειρίζεται αντικείμενα τύπου `Quantifiable` μέσω της μεθόδου `register()`.

#### Κώδικας Διασύνδεσης
```java
public interface Quantifiable {
   double getQuantity();
}
```

#### Κλάση Statistics
```java
import java.util.ArrayList;
import java.util.Collections;

public class Statistics {
   private ArrayList<Double> values;
   private Quantifiable maximum;
   private Quantifiable minimum;

   public Statistics() {
      values = new ArrayList<>();
      maximum = null;
      minimum = null;
   }

   public void register(Quantifiable item) {
      values.add(item.getQuantity());
      if (maximum == null || maximum.getQuantity() < item.getQuantity()) {
         maximum = item;
      }
      if (minimum == null || minimum.getQuantity() > item.getQuantity()) {
         minimum = item;
      }
   }

   public double getMedian() {
      Collections.sort(values);
      int middle = values.size() / 2;
      if (values.size() % 2 == 1) {
         return values.get(middle);
      } else {
         return (values.get(middle - 1) + values.get(middle)) / 2;
      }
   }

   public Quantifiable getMaximum() {
      return maximum;
   }

   public Quantifiable getMinimum() {
      return minimum;
   }
}
```

#### Υλοποίηση Κλάσεων
```java
public class BankAccount implements Quantifiable {
   private double balance;

   public BankAccount(double amount) {
      balance = amount;
   }

   public double getQuantity() {
      return balance;
   }
}

public class Student implements Quantifiable {
   private double GPA;

   public Student(double grade) {
      GPA = grade;
   }

   public double getQuantity() {
      return GPA;
   }
}
```

---

## Πλεονεκτήματα της Βελτιωμένης Σχεδίασης

### Γενίκευση Λειτουργικότητας:

- Η κλάση `Statistics` μπορεί να χειρίζεται οποιοδήποτε αντικείμενο που υλοποιεί τη διασύνδεση `Quantifiable`.

### Ευκολία Επέκτασης:

- Νέοι τύποι αντικειμένων (π.χ. `Stock`, `Athlete`) μπορούν να προστεθούν χωρίς αλλαγές στον υπάρχοντα κώδικα.

### Συμμόρφωση με την Αρχή Ανοικτής-Κλειστής Σχεδίασης:

- Προσθήκη νέου κώδικα χωρίς τροποποίηση του υπάρχοντος.

---

## Συμπεράσματα
- Η χρήση αφαιρέσεων και πολυμορφισμού αποτελεί το "κλειδί" για την υλοποίηση της αρχής.   
- Ο κατάλληλος σχεδιασμός διασφαλίζει την επεκτασιμότητα και τη σταθερότητα των μονάδων λογισμικού.   
- Η συμμόρφωση με την αρχή συμβάλλει στη μείωση του κόστους συντήρησης και του χρόνου ανάπτυξης.
