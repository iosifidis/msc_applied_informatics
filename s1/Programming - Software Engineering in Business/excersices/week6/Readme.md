## Κληρονομικότητα, Πολυμορφισμός & Υποσκέλιση

#### Ορισμός Κληρονομικότητας
Η κληρονομικότητα (inheritance) επιτρέπει τον ορισμό μιας νέας κλάσης ως επέκταση μιας υπάρχουσας. Η κλάση που επεκτείνεται ονομάζεται **υπερκλάση** (superclass) και η νέα κλάση ονομάζεται **υποκλάση** (subclass). Η υποκλάση κληρονομεί τα πεδία και τις μεθόδους της υπερκλάσης, ενώ μπορεί να ορίζει και δικά της χαρακτηριστικά.

#### Πλεονεκτήματα Κληρονομικότητας
1. **Αποφυγή επανάληψης κώδικα:** Πανομοιότυπα τμήματα κώδικα γράφονται μόνο μία φορά στην υπερκλάση.   
2. **Επαναχρησιμοποίηση κώδικα:** Ο υπάρχων κώδικας μπορεί να χρησιμοποιηθεί σε νέες υλοποιήσεις μέσω της υποκλάσης.   
3. **Ευκολότερη συντήρηση:** Οι αλλαγές στην υπερκλάση εφαρμόζονται αυτόματα σε όλες τις υποκλάσεις.   
4. **Επεκτασιμότητα:** Η προσθήκη νέων χαρακτηριστικών γίνεται πιο απλή και δομημένη.   

#### Ιεραρχίες Κληρονομικότητας
- Περισσότερες από μία υποκλάσεις μπορούν να κληρονομούν από την ίδια υπερκλάση.   
- Υποκλάσεις μπορούν να αποτελούν υπερκλάσεις για άλλες κλάσεις, δημιουργώντας ιεραρχίες κληρονομικότητας.   

#### Κληρονομικότητα στη Java
- Η λέξη-κλειδί `extends` χρησιμοποιείται για να δηλωθεί η σχέση κληρονομικότητας.   
- Τα πεδία και οι μέθοδοι που δηλώνονται ως `public` είναι προσπελάσιμα από τις υποκλάσεις.   
- Τα `private` πεδία δεν είναι προσπελάσιμα από τις υποκλάσεις, εκτός εάν παρέχονται μέθοδοι πρόσβασης (getters/setters).   
- Η λέξη-κλειδί `super` χρησιμοποιείται για την πρόσβαση στις μεθόδους και τα πεδία της υπερκλάσης.   

#### Πολυμορφισμός
Ο πολυμορφισμός (polymorphism) επιτρέπει τη χρήση ενός αντικειμένου με διαφορετικούς τρόπους ανάλογα με τον τύπο του. Οι δύο βασικές κατηγορίες είναι:
- **Πολυμορφισμός μεθόδων:** Η ίδια κλήση μεθόδου μπορεί να εκτελέσει διαφορετικές υλοποιήσεις ανάλογα με τη δυναμική κλάση του αντικειμένου.   
- **Πολυμορφικές μεταβλητές:** Μια μεταβλητή μπορεί να αναφέρεται σε αντικείμενα διαφορετικών τύπων εντός της ίδιας ιεραρχίας κληρονομικότητας.   

#### Υποσκέλιση (Overriding)
Η υποσκέλιση συμβαίνει όταν μια υποκλάση επανακαθορίζει μια μέθοδο της υπερκλάσης. Τα αντικείμενα της υποκλάσης εκτελούν τη δική τους υλοποίηση όταν η μέθοδος καλείται.

Παραδείγματα:
1. Υπερκλάση:
```java
public class Item {
    public void print() {
        System.out.println("Item details");
    }
}
```
2. Υποκλάση:
```java
public class CD extends Item {
    @Override
    public void print() {
        System.out.println("CD details");
    }
}
```

#### Δυναμική Αναζήτηση Μεθόδων
- Η Java χρησιμοποιεί τον δυναμικό τύπο (dynamic type) ενός αντικειμένου για να καθορίσει ποια μέθοδος θα εκτελεστεί.   
- Ο στατικός τύπος (static type) της μεταβλητής χρησιμοποιείται μόνο κατά τη μεταγλώττιση.   

#### Πλεονεκτήματα Πολυμορφισμού
- Ενισχύει την επεκτασιμότητα και την ευελιξία του κώδικα.   
- Επιτρέπει την υλοποίηση δυναμικών λειτουργιών που εξαρτώνται από τον τύπο του αντικειμένου.   

#### Κλήση της `super` σε Μεθόδους
Η λέξη-κλειδί `super` επιτρέπει την κλήση της υλοποίησης της υπερκλάσης από την υποκλάση:
```java
@Override
public void print() {
    super.print();
    System.out.println("Additional details");
}
```

Αυτή η προσέγγιση διατηρεί τη γενική λειτουργικότητα της υπερκλάσης και προσθέτει εξειδικευμένα χαρακτηριστικά.


