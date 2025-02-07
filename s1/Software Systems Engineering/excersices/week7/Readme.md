# Refactoring με το Σχέδιο Bridge

## Σχεδιαστικές Αποφάσεις

### Τύποι Αποφάσεων
1. **Σιωπηρές, μη καταγεγραμμένες**
   - Πληροφορίες που θεωρούνται αυτονόητες.
2. **Ρητές, μη καταγεγραμμένες**
   - Πληροφορίες που χάνονται με την πάροδο του χρόνου.
3. **Ρητές, καταγεγραμμένες**
   - Προτιμητέες, ώστε να αποφεύγεται η επανάληψη προηγούμενων λαθών.

### Πλεονεκτήματα Καταγεγραμμένων Αποφάσεων
- Εξηγούν γιατί μια αρχιτεκτονική είναι καλή.
- Εστιάζουν στις κρίσιμες απαιτήσεις.
- Παρέχουν πλαίσιο και υπόβαθρο για μελλοντικές επεκτάσεις.

## Βασικές Μετρικές Αντικειμενοστραφούς Προγραμματισμού
- **Πολυπλοκότητα**
  - Υπολογίζεται μέσω Κυκλωματικής Πολυπλοκότητας.
- **Σύζευξη Μεταξύ Αντικειμένων (Coupling)**
  - Πόσο εξαρτώνται οι κλάσεις μεταξύ τους.
- **Έλλειψη Συνοχής Μεθόδων (Lack of Cohesion of Methods)**
  - Υπολογίζεται μέσω των συνόλων μεταβλητών που χρησιμοποιούνται από τις μεθόδους μιας κλάσης.

## Παράδειγμα: Refactoring με το Σχέδιο Bridge

### Το Πρόβλημα
Μια εταιρεία θέλει να αναπτύξει ένα σύστημα πληρωμών για τους υπαλλήλους της, αλλά υπάρχουν διαφορετικές μέθοδοι πληρωμής:
- Ανά ώρα εργασίας.
- Με σταθερό μηνιαίο μισθό.

Αρχική υλοποίηση:
- Η κλάση `Employee` περιλαμβάνει όλη τη λογική για υπολογισμό πληρωμών.
- Δύσκολη συντήρηση και επεκτασιμότητα.

### Η Λύση με το Bridge
Χρησιμοποιούμε το σχέδιο **Bridge** για να διαχωρίσουμε την αφαίρεση (π.χ. υπάλληλος) από την υλοποίηση (π.χ. μέθοδος πληρωμής).

### Παράδειγμα Κώδικα
```java
// Αφαίρεση
abstract class Employee {
    protected PaymentMethod paymentMethod;

    public Employee(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public abstract void calculatePay();
}

// Εξειδικευμένη Αφαίρεση
class HourlyEmployee extends Employee {
    public HourlyEmployee(PaymentMethod paymentMethod) {
        super(paymentMethod);
    }

    @Override
    public void calculatePay() {
        paymentMethod.pay();
    }
}

class SalariedEmployee extends Employee {
    public SalariedEmployee(PaymentMethod paymentMethod) {
        super(paymentMethod);
    }

    @Override
    public void calculatePay() {
        paymentMethod.pay();
    }
}

// Υλοποίηση
interface PaymentMethod {
    void pay();
}

class HourlyPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Calculating hourly payment...");
    }
}

class MonthlyPayment implements PaymentMethod {
    @Override
    public void pay() {
        System.out.println("Calculating monthly salary...");
    }
}

// Χρήση
public class Main {
    public static void main(String[] args) {
        Employee hourlyEmployee = new HourlyEmployee(new HourlyPayment());
        Employee salariedEmployee = new SalariedEmployee(new MonthlyPayment());

        hourlyEmployee.calculatePay();
        salariedEmployee.calculatePay();
    }
}
```

### Πλεονεκτήματα του Bridge
- Διευκολύνει την επεκτασιμότητα.
- Μειώνει τη σύζευξη μεταξύ αφαίρεσης και υλοποίησης.
- Επιτρέπει την ανεξάρτητη ανάπτυξη της λογικής υπολογισμού και των τύπων πληρωμών.

---

### Βίντεο

- [Bridge Design Pattern: Easy Guide For Beginners](https://youtu.be/xhhZzx2SD70?si=RZYnFk5bhHdxomBc)   
- [Bridge - Design Patterns in 5 minutes](https://youtu.be/Q3R0zZfXit0?si=wu-jL0cB0TZv_EEo)   
- [Bridge Design Pattern in detail | Interview Question (Structural Design Pattern)](https://youtu.be/7pvhfHN1zY0?si=TPpp_h3pY5od6utk)    

### ΕΞΗΣΗΣΗ Bridge   

[Bride](bridge/)
