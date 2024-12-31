# Αρχές Σχεδίασης

## Βασικές Αρχές Σχεδίασης

### Αρχή Ενθυλάκωσης (Encapsulation Principle)
- **Ορισμός**: Η εσωτερική κατάσταση ενός αντικειμένου πρέπει να τροποποιείται μόνο μέσω της δημόσιας διεπαφής του.
- **Πλεονεκτήματα**:
  - Διατήρηση εγκυρότητας αντικειμένων.
  - Αποφυγή σοβαρών προβλημάτων δοκιμών και αποσφαλμάτωσης.
- **Παράδειγμα**:
```cpp
class TimeStamp {
private:
    int hour;
    int minute;
    int second;

public:
    void incrementHour() {
        hour++;
        if (hour == 24) hour = 0;
    }
};
```

### Αρχή Χαμηλής Σύζευξης (Low Coupling Principle)
- **Ορισμός**: Μείωση των εξαρτήσεων μεταξύ κλάσεων.
- **Πλεονεκτήματα**:
  - Ευκολότερη κατανόηση και επαναχρησιμοποίηση κώδικα.
  - Καλύτερη συντηρησιμότητα.

### Αρχή Ενιαίας Ευθύνης (Single Responsibility Principle - SRP)
- **Ορισμός**: Κάθε κλάση πρέπει να έχει μόνο έναν λόγο για να αλλάξει.
- **Παραδείγματα**:
  - Διαχωρισμός γεωμετρικών υπολογισμών από την εμφάνιση γραφικών.

### Αρχή Ανοιχτό-Κλειστό (Open-Closed Principle - OCP)
- **Ορισμός**: Οι κλάσεις πρέπει να είναι ανοιχτές για επέκταση, αλλά κλειστές για τροποποίηση.
- **Τρόποι συμμόρφωσης**:
  - Χρήση υποκλάσεων (inheritance).
  - Εφαρμογή σχεδιαστικών μοτίβων (π.χ., Strategy).

### Αρχή Αντιστροφής Εξάρτησης (Dependency Inversion Principle - DIP)
- **Ορισμός**:
  - Οι κλάσεις υψηλού επιπέδου δεν πρέπει να εξαρτώνται από κλάσεις χαμηλού επιπέδου.
  - Οι υλοποιήσεις πρέπει να εξαρτώνται από αφαιρέσεις (interfaces).
- **Πλεονεκτήματα**:
  - Μεγαλύτερη ευελιξία και επεκτασιμότητα.

## Παράδειγμα: Αντιστροφή Εξάρτησης (DIP)

### Το Πρόβλημα
Έχουμε έναν ελεγκτή (Controller) που ενεργοποιεί μια λάμπα (LampAlarm). Αν θέλουμε να αλλάξουμε τη συσκευή που ελέγχεται (π.χ., από λάμπα σε κινητήρα), ο κώδικας του ελεγκτή πρέπει να τροποποιηθεί, γεγονός που παραβιάζει το DIP.

### Η Λύση
Χρησιμοποιούμε μια διεπαφή (`ControllerServer`) για να αποσυνδέσουμε την υλοποίηση από την αφαίρεση.

### Παράδειγμα Κώδικα
```java
// Διεπαφή
interface ControllerServer {
    void turnOn();
    void turnOff();
}

// Υλοποίηση για λάμπα
class LampAlarm implements ControllerServer {
    @Override
    public void turnOn() {
        System.out.println("Lamp is turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Lamp is turned off.");
    }
}

// Ελεγκτής
class Controller {
    private ControllerServer device;

    public Controller(ControllerServer device) {
        this.device = device;
    }

    public void activate() {
        device.turnOn();
    }

    public void deactivate() {
        device.turnOff();
    }
}

// Χρήση
public class Main {
    public static void main(String[] args) {
        ControllerServer lamp = new LampAlarm();
        Controller controller = new Controller(lamp);

        controller.activate();
        controller.deactivate();
    }
}
```

### Πλεονεκτήματα της Λύσης
- Ευελιξία: Ο ελεγκτής μπορεί να λειτουργεί με οποιαδήποτε συσκευή που υλοποιεί τη διεπαφή `ControllerServer`.
- Μείωση της εξάρτησης από συγκεκριμένες υλοποιήσεις.

