# Σημειώσεις: Αναδόμηση Λογισμικού (Refactoring)

## 1. Τι είναι Refactoring
Η αναδόμηση (Refactoring) είναι η διαδικασία τροποποίησης του λογισμικού έτσι ώστε:
- Να μην αλλάζει η εξωτερική του συμπεριφορά.   
- Να βελτιώνεται η εσωτερική του δομή.   

### Σκοπός και Πλεονεκτήματα
- **Καθαρισμός κώδικα**: Συστηματική βελτίωση που μειώνει τις πιθανότητες εισαγωγής σφαλμάτων.   
- **Βελτίωση σχεδίασης**: Αναδόμηση κώδικα με κακή σχεδίαση για την επίτευξη καλύτερης ποιότητας.   
- **Ευκολότερη συντήρηση**: Εύκολη προσθήκη νέων χαρακτηριστικών και διόρθωση σφαλμάτων.   

## 2. Βασικά Βήματα και Τεχνικές

### Παράδειγμα: Extract Method
- **Πρόβλημα**: Υπάρχει ένα τμήμα κώδικα που μπορεί να ομαδοποιηθεί.   
- **Λύση**: Δημιουργία νέας μεθόδου με περιγραφικό όνομα.   

**Πριν**:
```java
double price = 0;
if (movie.getType() == REGULAR) {
    price += 2;
    if (daysRented > 2) {
        price += (daysRented - 2) * 1.5;
    }
}
```

**Μετά**:
```java
double price = calculateRegularPrice(movie, daysRented);

private double calculateRegularPrice(Movie movie, int daysRented) {
    double price = 2;
    if (daysRented > 2) {
        price += (daysRented - 2) * 1.5;
    }
    return price;
}
```

### Παράδειγμα: Move Method
- **Πρόβλημα**: Μια μέθοδος χρησιμοποιείται περισσότερο από μια άλλη κλάση.   
- **Λύση**: Μεταφορά της μεθόδου στη σωστή κλάση.   

**Πριν**:
Η μέθοδος `amountFor` βρίσκεται στην κλάση `Customer`, αλλά χρησιμοποιεί δεδομένα της κλάσης `Rental`.
```java
class Customer {
    public double amountFor(Rental rental) {
        return rental.getMovie().getPriceCode() * rental.getDaysRented();
    }
}
```

**Μετά**:
Η μέθοδος μεταφέρεται στην κλάση `Rental`.
```java
class Rental {
    public double getCharge() {
        return getMovie().getPriceCode() * getDaysRented();
    }
}

class Customer {
    public double amountFor(Rental rental) {
        return rental.getCharge();
    }
}
```

### Παράδειγμα: Replace Temp with Query
- **Πρόβλημα**: Χρήση προσωρινής μεταβλητής για αποθήκευση αποτελέσματος υπολογισμού.   
- **Λύση**: Εξαγωγή του υπολογισμού σε μέθοδο και αντικατάσταση της μεταβλητής.   

**Πριν**:
```java
double totalAmount = 0;
for (Rental rental : rentals) {
    totalAmount += rental.getCharge();
}
```

**Μετά**:
```java
double totalAmount = getTotalCharge();

private double getTotalCharge() {
    double result = 0;
    for (Rental rental : rentals) {
        result += rental.getCharge();
    }
    return result;
}
```

## 3. Αντιμετώπιση Σύνθετων Συνθηκών
### Replace Conditional with Polymorphism
- **Πρόβλημα**: Χρήση σύνθετης λογικής (π.χ., εντολές `switch`).   
- **Λύση**: Χρήση πολυμορφισμού για αντικατάσταση των συνθηκών.   

**Πριν**:
```java
switch (movie.getPriceCode()) {
    case Movie.REGULAR:
        result += 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        break;
    case Movie.NEW_RELEASE:
        result += daysRented * 3;
        break;
}
```

**Μετά**:
Δημιουργία υποκλάσεων για κάθε κατηγορία ταινίας.
```java
abstract class Price {
    abstract double getCharge(int daysRented);
}

class RegularPrice extends Price {
    @Override
    double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }
}

class NewReleasePrice extends Price {
    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }
}
```

## 4. Σημαντικές Ενότητες για Αναδόμηση
- **Κληρονομικότητα**: Χρήση προτύπων όπως State/Strategy για αντικατάσταση ελέγχων.   
- **Οργάνωση δεδομένων**: Μετακίνηση δεδομένων και μεθόδων για ευκολότερη διαχείριση.   

## 5. Παραδείγματα Refactoring
Παραδείγματα από τη μέθοδο `statement` στην κλάση `Customer`:
- Αντικατάσταση σύνθετων λογικών με κλήσεις σε μεθόδους όπως `getCharge()`.   
- Επαναχρησιμοποίηση κώδικα για διαφορετικές εξόδους (π.χ., `htmlStatement()`).   

## 6. Συμπεράσματα
- Η αναδόμηση είναι κρίσιμη για τη διατήρηση της ποιότητας του λογισμικού.   
- Βελτιώνει τη συντηρησιμότητα, την επαναχρησιμοποίηση και την ευελιξία του συστήματος.   
- Συνιστάται η χρήση μικρών, συνεχών βελτιώσεων για μέγιστα αποτελέσματα.   


