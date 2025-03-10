# **Σημειώσεις Μαθήματος: Newton’s Lab**

## **1. Εισαγωγή στο Ηλιακό Σύστημα**
- Το Ηλιακό Σύστημα περιλαμβάνει τον Ήλιο και όλα τα αντικείμενα που κινούνται σε τροχιά γύρω του.
- Αυτά περιλαμβάνουν πλανήτες, αστεροειδείς, κομήτες, δορυφόρους κ.λπ.
- Οι τροχιές καθορίζονται από το βαρυτικό πεδίο του Ήλιου.

## **2. Βαρύτητα, Μάζα, Βάρος**
- **Βαρύτητα**: Η δύναμη που ασκείται μεταξύ των σωμάτων λόγω της μάζας τους.
- **Μάζα**: Το μέτρο της αντίστασης ενός σώματος σε μεταβολές της κίνησής του.
- **Βάρος**: Η δύναμη έλξης λόγω βαρύτητας. Είναι μεγαλύτερο όταν ένα σώμα έχει μεγαλύτερη μάζα ή βρίσκεται πιο κοντά σε άλλο σώμα.

## **3. Αντικειμενοστρεφής Προγραμματισμός και Ηλιακό Σύστημα**
- Τα αντικείμενα στο Ηλιακό Σύστημα διαφέρουν σε:
  - **Μέγεθος**
  - **Μάζα**
  - **Σύνθεση**
  - **Ταχύτητα**
- Η κοινή τους δομή επιτρέπει την αξιοποίηση της **αφαίρεσης** και της **κληρονομικότητας** στην ανάπτυξη προσομοιώσεων.
- Σχεδιάζεται μια βασική κλάση `Body` που περιλαμβάνει τις βασικές ιδιότητες των σωμάτων.

## **4. Δημιουργία και Μελέτη Αντικειμένων**
- Μέθοδοι δημιουργίας αντικειμένων στην κλάση `Space`.
- Επιθεώρηση των ιδιοτήτων ενός πλανήτη.
- Μελέτη των δημόσιων μεθόδων της κλάσης `Body`.

## **5. Υποστηρικτικές Κλάσεις**
- **`SmoothMover`**: Εφαρμόζει ομαλή κίνηση χρησιμοποιώντας ακριβείς (double) συντεταγμένες.
- **`Vector`**: Υλοποιεί διάνυσμα κίνησης με καρτεσιανές και πολικές συντεταγμένες.
- **Άλλες κλάσεις**: Παρέχουν δυναμικές πληροφορίες μέσω web services.

## **6. Υλοποίηση Κίνησης**
- Η κλάση `SmoothMover` επιτρέπει ομαλή κίνηση μέσω πραγματικών αριθμών.
- **Διάνυσμα κίνησης**:
  - Χρησιμοποιεί την τρέχουσα κατεύθυνση και ταχύτητα.
  - Η μέθοδος `move()` κινεί το αντικείμενο σύμφωνα με το διάνυσμα.
- Η κλάση `Vector` υποστηρίζει τόσο **καρτεσιανή** όσο και **πολική** αναπαράσταση.

## **7. Προσθήκη Κίνησης στους Πλανήτες**
- Η κίνηση ενός πλανήτη καθορίζεται από το διάνυσμα κίνησης.
- Οι πλανήτες αρχικά κινούνται σε ευθεία τροχιά.
- Για να προσομοιωθεί η βαρυτική έλξη, πρέπει να υπολογιστούν οι δυνάμεις που δρουν πάνω σε κάθε σώμα.

## **8. Βαρύτητα και Κίνηση των Πλανητών**
- **Νόμος της παγκόσμιας έλξης (Νεύτωνας)**:
  - Η δύναμη βαρύτητας είναι ανάλογη με το γινόμενο των μαζών των δύο σωμάτων.
  - Είναι αντιστρόφως ανάλογη του τετραγώνου της απόστασης μεταξύ τους.
- **Υπολογισμός βαρύτητας**:
  - Εύρεση των σωμάτων στον κόσμο.
  - Υπολογισμός απόστασης με το **Πυθαγόρειο Θεώρημα**.
  - Υπολογισμός δύναμης με τον τύπο του Νεύτωνα.
  - Ενημέρωση του διανύσματος κίνησης.

## **9. Σταθερότητα του Ηλιακού Συστήματος**
- Πειραματισμός με:
  - **Τη σταθερά της βαρύτητας**
  - **Τη μάζα των σωμάτων**
  - **Την αρχική τους κίνηση**
- Σφάλματα προσομοίωσης:
  - Όλα τα σώματα θα έπρεπε να δρουν παράλληλα.
  - Η δύναμη θα έπρεπε να υπολογίζεται πριν την κίνηση όλων των σωμάτων.

## **10. Επεκτάσεις**
- Νέα κλάση **`Obstacle`**.
- Τροποποίηση της **`Body`**.
- Προσθήκη **σταθερών εμποδίων**.
- Περισσότεροι πλανήτες.
- Εφαρμογή **ήχου** στην προσομοίωση.

