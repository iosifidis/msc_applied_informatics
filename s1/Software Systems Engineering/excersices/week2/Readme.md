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

Γιατί αποτυγχάνουν τα έργα λογισμικού τόσο συχνά;
- ανεπαρκής προσδιορισμός απαιτήσεων -> προβληματική σχεδίαση   
- μη ρεαλιστικοί στόχοι του project   
- μη ακριβείς εκτιμήσεις απαιτούμενων πόρων   
- κακή αναφορά προόδου   
- ελλιπής χειρισμός ρίσκου   
- κακή επικοινωνία μεταξύ πελατών, προγρ/στών, χρηστών   
- έλλειψη εμπειρίας με τεχνολογία    
- αδυναμία χειρισμού πολυπλοκότητας      

---

## 2. Αρχές Αντικειμενοστραφούς Σχεδίασης
Οι παρακάτω αρχές προλαμβάνουν τα συμπτώματα κακής σχεδίασης.

### 2.1 Αρχή Ενσωμάτωσης (Encapsulation Principle)
**Ορισμός**: Η εσωτερική κατάσταση ενός αντικειμένου πρέπει να τροποποιείται μόνο μέσω της δημόσιας διασύνδεσής του.

**Πλεονεκτήματα**:
- Διατήρηση εγκυρότητας αντικειμένων.  
- Κατασκευή αντικειμένων με έγκυρα δεδομένα και μεθόδους που δεν παραβιάζουν αναλλοίωτες συνθήκες.  

Βίντεο: 
- [Encapsulation in Java Tutorial #83](https://youtu.be/cU94So54cr8?si=gN-1BYVpYEVQNSmR),    
- [Java encapsulation 💊](https://youtu.be/eboNNUADeIc?si=UqwPlCz3D2HKTe6O)

### 2.2 Αρχή Χαμηλής Σύζευξης (Low Coupling Principle)
**Ορισμός**: Οι μονάδες πρέπει να έχουν τη μικρότερη δυνατή σύζευξη.

**Πλεονεκτήματα**:
- Ευκολότερη υλοποίηση, έλεγχος και συντήρηση.  
- Επαναχρησιμοποίηση χωρίς να επηρεάζονται άλλες μονάδες.  

**Μέτρηση**:
- Χρήση μετρικής "Coupling Between Objects" (CBO): Μετρά τον αριθμό των εξαρτήσεων μεταξύ κλάσεων.

**Δύο σημαντικές έννοιες στην Τεχνολογία Λογισμικού είναι**:
- η **σύζεξη (coupling)**. Αναφέρεται στο βαθμό εξάρτησης μεταξύ δύο συστατακών
- η **συνεκτικότητα (cohesion)**. Αναφέρεται στο βαθμό εσωτερικής λειτουργικής συνάφειας μεταξύ των τμημάτων ενός συστατικού

Βίντεο:   
- [Loose vs Tight Coupling](https://youtu.be/uWseUdUqM5U?si=dC5zvsmTlltEMGJC)

### 2.3 Αρχή Μοναδικής Αρμοδιότητας (Single Responsibility Principle - SRP)
**Ορισμός**: Μία κλάση πρέπει να έχει μόνο έναν λόγο να αλλάξει.

**Πλεονεκτήματα**:
- Αποφυγή σύζευξης αρμοδιοτήτων.  
- Μικρότερη πολυπλοκότητα μέσω διαχωρισμού αρμοδιοτήτων σε διαφορετικές κλάσεις.  

**Μέτρηση**:
- Μετρική "Lack of Cohesion between methods" (LCOM): Ποσοτικοποίηση του βαθμού συνεκτικότητας μιας κλάσης

Βίντεο:    
- [Low Level Design 105 | Single Responsibility Principle in SOLID | 2022 | System Design](https://youtu.be/-T0H--a8WnY?si=TBn52u8vB3S-7MP2)

### 2.4 Αρχή Ανοιχτής-Κλειστής Σχεδίασης (Open-Closed Principle - OCP)
**Ορισμός**: Οι οντότητες λογισμικού πρέπει να είναι ανοιχτές για επέκταση αλλά κλειστές για τροποποίηση.

**Προσέγγιση**:
- Χρήση αφαιρέσεων και σχεδιαστικών προτύπων όπως Strategy και Template Method.

**Παραβίαση OCP**:
- Τροποποίηση υπάρχοντος κώδικα αντί για επέκτασή του.

#### Βίντεο
- [Solid Open Closed Principle Example](https://youtu.be/j9G-1TF9KkQ?si=b-Z06UO_MOOW3Gd7)   
- [Open/Closed Principle Explained - SOLID Design Principles](https://youtu.be/-ptMtJAdj40?si=YZ8185uOo2K0NP3t)    
- [Understanding the Open Closed Principle](https://youtu.be/Ryhy7333mqQ?si=8X_z8FW0LZek_koD)   

#### 2.4.1 STRATEGY Design Pattern

Το Strategy Design Pattern είναι ένα από τα πιο δημοφιλή συμπεριφορικά πρότυπα σχεδίασης (Behavioral Design Patterns) στην αντικειμενοστραφή προγραμματισμό. Χρησιμοποιείται για να επιτρέψει την επιλογή μιας συμπεριφοράς ενός αντικειμένου κατά τη διάρκεια της εκτέλεσης του προγράμματος, διαχωρίζοντας τον αλγόριθμο από την κλάση που τον χρησιμοποιεί.

**Σκοπός**:   
Το Strategy Pattern παρέχει έναν τρόπο ορισμού μιας οικογένειας αλγορίθμων, την τοποθέτησή τους σε ξεχωριστές κλάσεις και την εναλλαγή τους δυναμικά ανάλογα με τις ανάγκες του προγράμματος.

**Βασικά Συστατικά**:
- Strategy Interface: Ορίζει μια κοινή διεπαφή για όλους τους αλγόριθμους που θα χρησιμοποιηθούν.   
- Concrete Strategies: Υλοποιούν διαφορετικές εκδοχές του αλγορίθμου.   
- Context: Είναι η κλάση που χρησιμοποιεί τις στρατηγικές. Το Context περιέχει μια αναφορά στη Strategy και εκτελεί τη στρατηγική μέσω αυτής.   

**Δομή**:
- Ο πελάτης (client) επιλέγει τη στρατηγική.   
- Η στρατηγική εγχέεται στο Context (συνήθως μέσω constructor ή setter).   
- Το Context χρησιμοποιεί την επιλεγμένη στρατηγική χωρίς να γνωρίζει την υλοποίησή της.  

**Παράδειγμα με Java**:   
Έστω ότι φτιάχνουμε ένα πρόγραμμα υπολογισμού κόστους για διαφορετικές μεθόδους μεταφοράς (π.χ., Οδήγηση, Πτήση, Ποδήλατο).
```
// Step 1: Δημιουργία της Strategy Interface
public interface TransportStrategy {
    double calculateCost(double distance);
}

// Step 2: Υλοποίηση Concrete Strategies
public class CarStrategy implements TransportStrategy {
    @Override
    public double calculateCost(double distance) {
        return distance * 0.5; // Κόστος ανά χιλιόμετρο για οδήγηση
    }
}

public class FlightStrategy implements TransportStrategy {
    @Override
    public double calculateCost(double distance) {
        return distance * 1.2; // Κόστος ανά χιλιόμετρο για πτήση
    }
}

public class BicycleStrategy implements TransportStrategy {
    @Override
    public double calculateCost(double distance) {
        return 0; // Δωρεάν μεταφορά με ποδήλατο
    }
}

// Step 3: Το Context
public class TravelCostCalculator {
    private TransportStrategy strategy;

    public TravelCostCalculator(TransportStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(TransportStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateCost(double distance) {
        return strategy.calculateCost(distance);
    }
}

// Step 4: Χρήση από τον Client
public class Main {
    public static void main(String[] args) {
        TravelCostCalculator calculator = new TravelCostCalculator(new CarStrategy());
        System.out.println("Car cost: " + calculator.calculateCost(100));

        calculator.setStrategy(new FlightStrategy());
        System.out.println("Flight cost: " + calculator.calculateCost(100));

        calculator.setStrategy(new BicycleStrategy());
        System.out.println("Bicycle cost: " + calculator.calculateCost(100));
    }
}
```

**Πλεονεκτήματα**:
- Ανοιχτό για επεκτάσεις: Μπορείς να προσθέσεις νέες στρατηγικές χωρίς να αλλάξεις την υπάρχουσα υλοποίηση.   
- Μείωση πολυπλοκότητας: Εξαλείφονται τα μεγάλα `if-else` ή `switch` statements.   
- Ευελιξία: Οι στρατηγικές μπορούν να εναλλάσσονται δυναμικά κατά την εκτέλεση.   

**Μειονεκτήματα**:
- Αύξηση αριθμού κλάσεων: Προστίθενται περισσότερες κλάσεις για κάθε στρατηγική.   
- Πρόσθετη πολυπλοκότητα: Εισάγεται εξάρτηση μεταξύ του Context και των στρατηγικών.

Με το Strategy Pattern, η αρχή του "Open-Closed Principle" εφαρμόζεται άμεσα, διαχωρίζοντας τις ευθύνες και διατηρώντας το σύστημα ευέλικτο και επεκτάσιμο.

Βίντεο:    
- [The Strategy Pattern Explained and Implemented in Java | Behavioral Design Patterns | Geekific](https://youtu.be/Nrwj3gZiuJU?si=14a6_R2KtY9WniTH)

#### 2.4.2 TEPLATE METHOD Design Pattern

Το Template Method Design Pattern είναι ένα από τα συμπεριφορικά πρότυπα σχεδίασης (Behavioral Design Patterns) και χρησιμοποιείται για να ορίσει το σκελετό ενός αλγορίθμου σε μια υπερκλάση, αφήνοντας κάποιες λεπτομέρειες να υλοποιούνται από τις υποκλάσεις. Αυτό επιτρέπει την επαναχρησιμοποίηση κώδικα και τη δυνατότητα προσαρμογής συγκεκριμένων τμημάτων του αλγορίθμου.

**Σκοπός**:   
Το Template Method Pattern διασφαλίζει ότι η βασική ροή ενός αλγορίθμου παραμένει αναλλοίωτη, ενώ επιτρέπει στις υποκλάσεις να προσαρμόζουν ή να επεκτείνουν τμήματα του αλγορίθμου, όπου αυτό είναι απαραίτητο.

**Βασικά Συστατικά**:
- Abstract Class: Περιέχει τη μέθοδο "πρότυπο" (template method) και συγκεκριμένες μεθόδους που καθορίζουν τη βασική ροή.   
- Concrete Classes: Υλοποιούν τις αφηρημένες ή επεκτάσιμες μεθόδους της υπερκλάσης.

**Δομή**:
- Ορίζεται ένα template method στην abstract class που περιέχει τη γενική ροή του αλγορίθμου.    
- Μερικά βήματα της ροής είτε είναι υλοποιημένα στην υπερκλάση είτε ορίζονται ως abstract (ή protected) για υλοποίηση από τις υποκλάσεις.

**Παράδειγμα με Java**:   
Έστω ότι φτιάχνουμε ένα σύστημα παρασκευής ροφημάτων, όπως τσάι και καφές. Η διαδικασία έχει κάποια κοινά βήματα (π.χ., βράσιμο νερού), αλλά και βήματα που διαφέρουν ανάλογα με το ρόφημα.
```
// Step 1: Δημιουργία της Abstract Class
public abstract class BeverageTemplate {

    // Template Method - Καθορίζει τη βασική ροή
    public final void prepareBeverage() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    // Concrete Method - Κοινή για όλα τα ροφήματα
    private void boilWater() {
        System.out.println("Boiling water...");
    }

    // Abstract Methods - Πρέπει να υλοποιηθούν από τις υποκλάσεις
    protected abstract void brew();

    protected abstract void addCondiments();

    // Concrete Method - Κοινή για όλα τα ροφήματα
    private void pourInCup() {
        System.out.println("Pouring into cup...");
    }
}

// Step 2: Υλοποίηση των Concrete Classes
public class Tea extends BeverageTemplate {
    @Override
    protected void brew() {
        System.out.println("Steeping the tea...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon...");
    }
}

public class Coffee extends BeverageTemplate {
    @Override
    protected void brew() {
        System.out.println("Dripping coffee through filter...");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk...");
    }
}

// Step 3: Χρήση από τον Client
public class Main {
    public static void main(String[] args) {
        BeverageTemplate tea = new Tea();
        System.out.println("Preparing tea:");
        tea.prepareBeverage();

        System.out.println("\nPreparing coffee:");
        BeverageTemplate coffee = new Coffee();
        coffee.prepareBeverage();
    }
}
```

**Εκτέλεση του Κώδικα**:   
Η μέθοδος `prepareBeverage()` παραμένει αμετάβλητη και εφαρμόζει την κοινή ροή. Οι υποκλάσεις `Tea` και `Coffee` υλοποιούν μόνο τα βήματα που διαφέρουν, δηλαδή τις μεθόδους `brew()` και `addCondiments()`.

**Πλεονεκτήματα**:   
- Επαναχρησιμοποίηση Κώδικα: Κοινά βήματα του αλγορίθμου υλοποιούνται μία φορά στην υπερκλάση.   
- Ευκολία Επέκτασης: Νέα υποκλάσεις μπορούν να προσθέσουν συμπεριφορά χωρίς να τροποποιήσουν την υπάρχουσα λογική.   
- Σταθερή Ροή: Διασφαλίζει ότι η βασική ροή του αλγορίθμου παραμένει αμετάβλητη.

**Μειονεκτήματα**:   
- Δυσκολία Συντήρησης: Αν οι αφηρημένες κλάσεις γίνουν πολύπλοκες, μπορεί να δυσκολέψουν την κατανόηση και τη συντήρηση.    
- Ακατάλληλη για Απλούς Αλγορίθμους: Εισάγει περιττή πολυπλοκότητα όταν ο αλγόριθμος δεν απαιτεί επέκταση.

Το Template Method Pattern εφαρμόζει την αρχή "Don't Repeat Yourself" (DRY), εξασφαλίζοντας τη σωστή δομή και οργάνωση του κώδικα, ενώ επιτρέπει την επέκταση όπου είναι απαραίτητη.

Βίντεο: 
- [The Template Method Pattern Explained Implemented in Java | Behavioral Design Patterns | Geekific](https://youtu.be/cGoVDzHvD4A?si=EU4VdGE25r1-1TT8),    
- [Template Method - Design Patterns in 5 minutes](https://youtu.be/6y_j4TqxDQc?si=69cLzBwSiyEF_qQA)

### 2.5 Αρχή Υποκατάστασης Liskov (Liskov Substitution Principle - LSP)
**Ορισμός**: Οι παράγωγες κλάσεις πρέπει να μπορούν να αντικαταστήσουν τις βασικές χωρίς να επηρεάζεται η ορθή λειτουργία του συστήματος.

Η LSP διασφαλίζει ότι οι υποκλάσεις μπορούν να αντικαταστήσουν τις υπερκλάσεις τους χωρίς να επηρεάζεται η ορθότητα του προγράμματος. Με άλλα λόγια, ένα αντικείμενο της υποκλάσης πρέπει να μπορεί να χρησιμοποιηθεί οπουδήποτε αναμένεται ένα αντικείμενο της υπερκλάσης, χωρίς να αλλάζει η συμπεριφορά του συστήματος.

**Αρχές της LSP**:   
- Οι υποκλάσεις δεν πρέπει να παραβιάζουν τη "σύμβαση" της υπερκλάσης.   
- Οι μέθοδοι της υποκλάσης πρέπει να συμπεριφέρονται συνεπώς με τις μεθόδους της υπερκλάσης.   
- Οι πελάτες που χρησιμοποιούν την υπερκλάση δεν πρέπει να χρειάζονται επιπλέον πληροφορίες για να λειτουργούν με τις υποκλάσεις.    

**Παραδείγματα Παραβίασης**:
- Χρήση κληρονομικότητας που αλλάζει τη συμπεριφορά του συστήματος.   
- Παράδειγμα υπερκλάση `Rectangle` και την υποκλάση `Square`.   

**Λύση**:
- Σχεδιασμός με βάση τις αναλλοίωτες συνθήκες και τη συμβατότητα με τη βασική κλάση.

Βίντεο: 
- [Liskov Substitution Principle Explained - SOLID Design Principles](https://youtu.be/dJQMqNOC4Pc?si=n1DQURfq21aA4qIF),    
- [Understanding the Liskov Substitution Principle](https://youtu.be/Mmy1EUKC_iE?si=2qsMLhQkkfuWCw4e),     
- [Low Level Design 107 | Liskov Substitution Principle | 2022 | System Design](https://youtu.be/HbGDobtxzWk?si=Z4DZ06X97GGJ4pOt)

### 2.6 Αρχή Αντιστροφής Εξαρτήσεων (Dependency Inversion Principle - DIP)
**Ορισμός**:
- Οι μονάδες υψηλού επιπέδου δεν πρέπει να εξαρτώνται από μονάδες χαμηλού επιπέδου.  
- Οι λεπτομέρειες υλοποίησης πρέπει να εξαρτώνται από αφαιρέσεις.  

**Πρακτικές**:
- Διαστρωμάτωση συστημάτων.  
- Χρήση διασυνδέσεων για τον περιορισμό των εξαρτήσεων.  

**Βελτιωμένη διαστρωμάτωση**:   
Η βελτιωμένη διαστρωμάτωση επιτυγχάνεται με τη χρήση της Αρχής Αντιστροφής Εξαρτήσεων, καθώς αποσυνδέει τις υλοποιήσεις από τη λογική του συστήματος, ενισχύοντας την ευελιξία και τη συντηρησιμότητα. Αντί οι ανώτερες τάξεις να εξαρτώνται άμεσα από συγκεκριμένες υλοποιήσεις, εξαρτώνται από αφαιρετικές συμβάσεις που καθορίζουν τη συμπεριφορά. Αυτό επιτρέπει την αντικατάσταση εξαρτήσεων με ελάχιστες αλλαγές στον κώδικα, διευκολύνει τη δοκιμασία μονάδων (unit testing) με χρήση mock αντικειμένων και μειώνει τη σύζευξη, προσδίδοντας μεγαλύτερη ανεξαρτησία στα στρώματα του συστήματος.

Βίντεο: 
- [Dependency Inversion Principle Explained - SOLID Design Principles](https://youtu.be/9oHY5TllWaU?si=G0mDej2vvRuzO36R),    
- [Low Level Design 109 | Dependency Inversion Principle | 2022 | System Design](https://youtu.be/_CQuOfIqaGE?si=OBPDXY3QOj_DU0e5),    
- [Dependency Inversion: What, Why & How? | By Example](https://youtu.be/-3Z9L6sIAMM?si=t8r1bqBsh_0xmj3-)

### 2.7 Αρχή Διαχωρισμού Διασυνδέσεων (Interface Segregation Principle - ISP)
**Ορισμός**: Πολλές εξειδικευμένες διασυνδέσεις είναι προτιμότερες από μία γενική.

**Πλεονεκτήματα**:
- Αποφυγή εξαναγκασμού πελατών να εξαρτώνται από μεθόδους που δεν χρησιμοποιούν.  
- Μείωση περιττής πολυπλοκότητας μέσω του διαχωρισμού των διασυνδέσεων.  

Βίντεο:
- [Low Level Design 108 | Interface Segregation Principle | 2022 | System Design](https://youtu.be/-E-E6pd-psU?si=SAjG5YyT095d0Uuz)   
- [Interface Segregation Principle EXPLAINED SIMPLY | SOLID Principles #4](https://youtu.be/h5tIblPJo8k?si=mjx0OwC5GOYo6KVb)   
- [The Interface Segregation Principle Code Example](https://youtu.be/gExP_HLRh1I?si=zlsLQZGo-Jd22q0h)
- [Interface Segregation Explained - SOLID Principles E4](https://youtu.be/O6Z1xBWrepk?si=VSdR8cszhqffSnct)

#### 2.7.1 Διαχωρισμός μέσω Αποστολής Μηνυμάτων (Delegation)

Ο Διαχωρισμός μέσω Αποστολής Μηνυμάτων (Delegation) είναι ένα πρότυπο σχεδίασης που σχετίζεται με την ανάθεση συγκεκριμένων ευθυνών από ένα αντικείμενο σε ένα άλλο, αντί να προσπαθεί να τα χειριστεί όλα μόνο του. Πρόκειται για έναν μηχανισμό που επιτρέπει την επαναχρησιμοποίηση της συμπεριφοράς ενός αντικειμένου από άλλα αντικείμενα, χωρίς την ανάγκη κληρονομικότητας.

**Κύρια Χαρακτηριστικά**:
- Ανάθεση Ευθυνών: Ένα αντικείμενο (το delegator) αναθέτει μια εργασία σε ένα άλλο αντικείμενο (το delegate) που ειδικεύεται στη συγκεκριμένη εργασία.   
- Ανεξαρτησία από την Κληρονομικότητα: Αντί να επεκτείνει μια κλάση, το αντικείμενο αναθέτει την εργασία, προάγοντας τη σύνθεση έναντι της κληρονομικότητας.   
- Ευελιξία: Εύκολη αντικατάσταση ή προσαρμογή της λειτουργικότητας αλλάζοντας το delegate αντικείμενο.

**Πλεονεκτήματα**:   
- Μειωμένη Σύζευξη: Ελαχιστοποιείται η εξάρτηση μεταξύ αντικειμένων.  
- Επαναχρησιμοποίηση Κώδικα: Το delegate αντικείμενο μπορεί να χρησιμοποιηθεί από πολλούς delegators.  
- Ευκολία Συντήρησης: Οι αλλαγές στη λειτουργικότητα γίνονται σε ένα σημείο (στο delegate).

**Παράδειγμα με Java**:   
Ας υποθέσουμε ότι έχουμε ένα αντικείμενο `Printer` που αναλαμβάνει την εκτύπωση κειμένου. Ένα άλλο αντικείμενο `TextEditor` θέλει να παρέχει αυτή τη λειτουργικότητα χωρίς να την υλοποιεί από μόνο του.
```
// Το αντικείμενο που κάνει την εργασία (Delegate)
public class Printer {
    public void print(String text) {
        System.out.println("Printing: " + text);
    }
}

// Το αντικείμενο που αναθέτει την εργασία (Delegator)
public class TextEditor {
    private Printer printer; // Σύνθεση

    public TextEditor(Printer printer) {
        this.printer = printer;
    }

    public void printText(String text) {
        printer.print(text); // Ανάθεση της εργασίας
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        TextEditor editor = new TextEditor(printer);

        editor.printText("Hello, delegation pattern!");
    }
}
```

Στο παραπάνω παράδειγμα, το `TextEditor` δεν υλοποιεί τη λειτουργία εκτύπωσης αλλά αναθέτει την εργασία στο `Printer`. Αυτό ενισχύει την επαναχρησιμοποίηση και την ευελιξία.

Η αντιπροσώπευση μέσω αποστολής μηνυμάτων είναι ιδιαίτερα χρήσιμη σε περιπτώσεις όπου θέλουμε να διατηρήσουμε την αρχή "Single Responsibility" ή να μειώσουμε την πολυπλοκότητα της κληρονομικότητας.

Βίντεο:
- [How Delegates Work in Java](https://youtu.be/JWVjmyo6e5Y?si=MhpEvyZt0R63KGIY)

#### 2.7.2 Διαχωρισμός μέσω Πολλαπλής Κληρονομικότητας

Ο Διαχωρισμός μέσω Πολλαπλής Κληρονομικότητας είναι ένας τρόπος επαναχρησιμοποίησης κώδικα και λειτουργικότητας, όπου μια κλάση μπορεί να κληρονομήσει χαρακτηριστικά και μεθόδους από περισσότερες από μία υπερκλάσεις. Αν και η πολλαπλή κληρονομικότητα παρέχει ευελιξία και δυνατότητες επανεκμετάλλευσης, μπορεί να δημιουργήσει προβλήματα πολυπλοκότητας, όπως συγκρούσεις μεθόδων ή δεδομένων, γνωστά ως "Diamond Problem".

**Κύρια Χαρακτηριστικά**:
- Πολλαπλές Πηγές Κληρονομικότητας: Η κλάση-υποκλάση μπορεί να κληρονομήσει από δύο ή περισσότερες υπερκλάσεις.   
- Επαναχρησιμοποίηση Κώδικα: Επιτρέπει σε μια υποκλάση να επωφεληθεί από τη λειτουργικότητα πολλών διαφορετικών κλάσεων.   
- Πολυπλοκότητα Διαχείρισης: Μπορεί να προκύψουν συγκρούσεις αν οι υπερκλάσεις έχουν κοινές μεθόδους ή πεδία.

**Πλεονεκτήματα**:
- Επαναχρησιμοποίηση Κώδικα: Παρέχει πρόσβαση σε λειτουργικότητα από πολλές διαφορετικές κλάσεις.   
- Ευελιξία: Ενισχύει τον σχεδιασμό, επιτρέποντας σύνθετες ιεραρχίες κλάσεων.   
- Μείωση Διπλού Κώδικα: Οι ίδιες λειτουργίες μπορούν να κληρονομούνται από πολλές υπερκλάσεις.

**Προβλήματα**:   
- Diamond Problem: Προκύπτει όταν μια υποκλάση κληρονομεί από δύο κλάσεις που κληρονομούν από την ίδια βάση, προκαλώντας ασάφεια για τη μέθοδο ή το πεδίο που πρέπει να χρησιμοποιηθεί.   
- Πολυπλοκότητα Διατήρησης: Το σχέδιο του κώδικα γίνεται πιο περίπλοκο, καθιστώντας δύσκολη τη συντήρηση.   
- Ασυμβατότητα: Ορισμένες γλώσσες αποφεύγουν την υποστήριξη πολλαπλής κληρονομικότητας λόγω αυτών των προβλημάτων.   

**Παράδειγμα με Java Interfaces**:   
Η Java επιλύει την απουσία πολλαπλής κληρονομικότητας μέσω των interfaces:
```
interface A {
    default void show() {
        System.out.println("Interface A");
    }
}

interface B {
    default void show() {
        System.out.println("Interface B");
    }
}

class C implements A, B {
    @Override
    public void show() {
        A.super.show(); // Επιλογή της μεθόδου του A
    }
}

public class Main {
    public static void main(String[] args) {
        C obj = new C();
        obj.show(); // Εμφανίζει: Interface A
    }
}
```

Ο διαχωρισμός μέσω πολλαπλής κληρονομικότητας είναι χρήσιμος για την επαναχρησιμοποίηση κώδικα και την ενίσχυση της ευελιξίας, αλλά απαιτεί προσεκτική διαχείριση για την αποφυγή πολυπλοκότητας και σφαλμάτων. Οι μοντέρνες γλώσσες, όπως η Java, προτείνουν τη χρήση συνθέσεων και interfaces για τη μείωση των μειονεκτημάτων.

---

## 3. Συμπεράσματα
Η εφαρμογή αυτών των αρχών:
- Προάγει τη σωστή σχεδίαση λογισμικού.  
- Μειώνει την πολυπλοκότητα και το κόστος συντήρησης.  
- Αυξάνει την ευελιξία και την επαναχρησιμοποίηση του κώδικα.  

Η κατανόηση και εφαρμογή τους απαιτεί εμπειρία αλλά αποτελεί το θεμέλιο για τη δημιουργία ποιοτικού λογισμικού.

---

## Κώδικας

[Αρχή Ανοικτής-Κλειστής Σχεδίασης (Open-Closed Principle)](open-close-principle.md)

