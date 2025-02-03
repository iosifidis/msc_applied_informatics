## Κληρονομικότητα και Πολυμορφισμός

#### Κληρονομικότητα (Inheritance)
Η κληρονομικότητα είναι ένας από τους βασικότερους μηχανισμούς του αντικειμενοστραφούς προγραμματισμού και επιτρέπει τη δημιουργία νέων κλάσεων με βάση υπάρχουσες. 

- **Υπερκλάση (Superclass):** Η κλάση από την οποία κληρονομεί μια άλλη.
- **Υποκλάση (Subclass):** Η κλάση που κληρονομεί ιδιότητες και μεθόδους από την υπερκλάση.

##### Παράδειγμα
```java
public class Employee {
    private String name;
    private double salary;

    public Employee(String text, double amount) {
        name = text;
        salary = amount;
    }

    public void printStatement() {
        System.out.println("Employee: " + name + " with salary: " + salary);
    }
}

public class AdministrativeEmployee extends Employee {
    private int overtimeHours;
    private double overtimeWage;

    public AdministrativeEmployee(String text, double amount, int hours, double wage) {
        super(text, amount);
        overtimeHours = hours;
        overtimeWage = wage;
    }

    @Override
    public void printStatement() {
        super.printStatement();
        System.out.println("and extra income: " + (overtimeHours * overtimeWage));
    }
}
```

- Η κλάση `AdministrativeEmployee` κληρονομεί τις ιδιότητες και μεθόδους της `Employee` και προσθέτει νέες.

##### Ιδιότητες της Κληρονομικότητας
1. **Επαναχρησιμοποίηση Κώδικα:** Οι υποκλάσεις επαναχρησιμοποιούν κώδικα της υπερκλάσης.
2. **Προστατευμένη Πρόσβαση:** Χρήση του `protected` για πρόσβαση σε ιδιότητες της υπερκλάσης από τις υποκλάσεις.
3. **Κλήση Κατασκευαστή Υπερκλάσης:** Η λέξη-κλειδί `super()` καλεί τον κατασκευαστή της υπερκλάσης.

#### Πολυμορφισμός (Polymorphism)
Ο πολυμορφισμός επιτρέπει τη χρήση ενός αντικειμένου με διαφορετικές μορφές, ανάλογα με την κλάση του.

##### Παράδειγμα Πολυμορφικής Κλήσης
```java
public static void main(String[] args) {
    ArrayList<Employee> employees = new ArrayList<>();
    employees.add(new Employee("John", 900));
    employees.add(new AdministrativeEmployee("Bob", 800, 15, 10));

    for (Employee e : employees) {
        e.printStatement();
    }
}
```
- Η μέθοδος `printStatement()` καλείται πολυμορφικά: κάθε αντικείμενο χρησιμοποιεί τη δική του υλοποίηση.

#### Δυναμική Διασύνδεση (Dynamic Binding)
- Η επιλογή της κατάλληλης μεθόδου γίνεται κατά το χρόνο εκτέλεσης.
- Η Java καθυστερεί την απόφαση μέχρι να είναι γνωστός ο τύπος του αντικειμένου.

##### Παράδειγμα Δυναμικής Διασύνδεσης
```java
public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Alice", 1200);
        Employee emp2 = new AdministrativeEmployee("Bob", 1500, 10, 20);

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);

        for (Employee e : employees) {
            e.printStatement();
        }
    }
}
```
Σε αυτό το παράδειγμα:
- Κατά τη διάρκεια της μεταγλώττισης, η Java βλέπει την αναφορά `e` ως τύπου `Employee`.
- Κατά την εκτέλεση, η Java αναγνωρίζει ότι το `emp2` είναι τύπου `AdministrativeEmployee` και καλεί τη δική του μέθοδο `printStatement()`.

##### Πώς λειτουργεί:
1. Η μέθοδος `printStatement()` καθορίζεται δυναμικά ανάλογα με τον πραγματικό τύπο του αντικειμένου (π.χ., `Employee` ή `AdministrativeEmployee`).
2. Η χρήση της δυναμικής διασύνδεσης επιτρέπει διαφορετικές συμπεριφορές με την ίδια κλήση.

##### Οφέλη:
- Αυξημένη ευελιξία και επεκτασιμότητα του κώδικα.
- Μείωση της ανάγκης για πολυάριθμους ελέγχους τύπων και ειδικές υλοποιήσεις.

#### Αρχή της Υποκατάστασης (Substitution Principle)
- Αντικείμενα υποκλάσεων μπορούν να χρησιμοποιηθούν όπου αναμένεται αντικείμενο της υπερκλάσης.
```java
Employee ref = new AdministrativeEmployee("Alice", 1000, 20, 15);
```

#### Πλεονεκτήματα της Κληρονομικότητας και του Πολυμορφισμού
1. **Ευελιξία:** Ο κώδικας είναι πιο γενικός και επεκτάσιμος.
2. **Συντηρησιμότητα:** Αλλαγές σε μια υπερκλάση επηρεάζουν όλες τις υποκλάσεις.
3. **Απλότητα:** Αποφυγή επανάληψης κώδικα.

#### Σημεία Προσοχής
- **Περιορισμοί της Κληρονομικότητας:**
  - Η πολλαπλή κληρονομικότητα δεν υποστηρίζεται στη Java (αντίθετα υποστηρίζονται τα interfaces).
  - Μεγάλες ιεραρχίες μπορεί να δυσκολεύουν τη συντήρηση.
- **Κατάλληλη Σχεδίαση:** Η σχέση "is-a" πρέπει να είναι λογική (π.χ., "Ο υπάλληλος είναι μια επιχείρηση" δεν έχει νόημα).

Χρησιμοποιώντας κληρονομικότητα και πολυμορφισμό, μπορούμε να κατασκευάσουμε κώδικα που είναι επεκτάσιμος, κατανοητός, και ευέλικτος στις αλλαγές.

### ΑΦΗΡΗΜΕΝΗ ΚΛΑΣΗ (abstract)

- **Τι είναι**: Μια abstract class είναι μια κλάση που μπορεί να περιέχει τόσο abstract methods (χωρίς υλοποίηση) όσο και concrete methods (με υλοποίηση). Δεν μπορεί να δημιουργηθεί άμεσα αντικείμενο από μια abstract class.   

- **Σκοπός**: Παρέχει μια βάση (template) για κλάσεις που σχετίζονται μεταξύ τους ιεραρχικά. Μπορεί να έχει state (μεταβλητές στιγμιοτύπου) και να παρέχει κοινή λειτουργικότητα στις υποκλάσεις.

**Χαρακτηριστικά**:
- Μπορεί να περιέχει μεταβλητές στιγμιοτύπου (instance variables).   
- Μπορεί να περιέχει constructors.   
- Μπορεί να περιέχει static methods.   
- Μια κλάση μπορεί να κληρονομήσει μόνο μία abstract class (μονοκληρονομικότητα).

**Χρήση**:

- Όταν θέλουμε να δημιουργήσουμε μια βάση για παρόμοιες κλάσεις.   
- Όταν θέλουμε να παρέχουμε default συμπεριφορά και να αφήσουμε τις υποκλάσεις να υλοποιήσουν μόνο τα απαραίτητα.   

Παράδειγμα:

```
abstract class Animal {
    abstract void makeSound(); // Abstract method
    void eat() {
        System.out.println("This animal eats food.");
    }
}
class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof!");
    }
}
```

**Βίντεο:**   
- [Abstract Class In Java Tutorial #79](https://www.youtube.com/watch?v=52frlN8webg)   
- [Abstract Classes and Methods in Java Explained in 7 Minutes](https://www.youtube.com/watch?v=HvPlEJ3LHgE)    
- [Java abstraction 👻](https://www.youtube.com/watch?v=Lvnb83qt57g)
