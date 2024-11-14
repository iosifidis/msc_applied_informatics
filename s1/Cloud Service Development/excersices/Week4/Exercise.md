# Άσκηση: Ανάπτυξη API Διαχείρισης Φοιτητών με Spring Boot

## Στόχος
Η άσκηση έχει ως στόχο τη δημιουργία ενός απλού RESTful API για τη διαχείριση δεδομένων φοιτητών χρησιμοποιώντας το Spring Boot framework. Θα υλοποιήσετε μια εφαρμογή που μπορεί να προσθέσει και να ανακτήσει φοιτητές σε JSON μορφή, κάνοντας χρήση της τεχνικής Dependency Injection και διαχωρίζοντας τις λειτουργίες σε layers (Controller και Service).

## Οδηγίες Υλοποίησης

### 1. Δημιουργία Κλάσης `Student`
   - Ορίστε την κλάση `Student` με τα παρακάτω πεδία:
     - `name`: Το όνομα του φοιτητή (String).
     - `age`: Η ηλικία του φοιτητή (int).
     - `location`: Η τοποθεσία του φοιτητή (String).
   - Δημιουργήστε constructors και methods τύπου *getter* για κάθε πεδίο, έτσι ώστε τα δεδομένα να μπορούν να επιστραφούν σε JSON μορφή από το API.

### 2. Δημιουργία Κλάσης `HelloService`
   - Δημιουργήστε την κλάση `HelloService` με το annotation `@Service`.
   - Στην κλάση αυτή, ορίστε τις ακόλουθες μεθόδους:
     - **`getStudent(String name, int age, String location)`**: Δημιουργεί και επιστρέφει ένα αντικείμενο `Student` με τα στοιχεία που δόθηκαν ως παράμετροι.
     - **`addStudent(String name, int age, String location)`**: Προσθέτει ένα νέο αντικείμενο `Student` σε μια λίστα φοιτητών.
     - **`getAllStudents()`**: Επιστρέφει τη λίστα με όλους τους φοιτητές.

### 3. Δημιουργία Κλάσης `HelloController`
   - Δημιουργήστε την κλάση `HelloController` με το annotation `@RestController`.
   - Χρησιμοποιήστε το `@Autowired` για να εγχύσετε την κλάση `HelloService` στον Controller.
   - Ορίστε τα εξής endpoints:
     - **`GET /askName/{name}`**: Επιστρέφει ένα χαιρετισμό για το όνομα που δίνεται ως path variable.
     - **`GET /addstudent?name={name}&age={age}&location={location}`**: Προσθέτει ένα νέο φοιτητή με βάση τις παραμέτρους που παρέχονται.
     - **`GET /allStudents`**: Επιστρέφει σε JSON μορφή τη λίστα με όλους τους φοιτητές.

### 4. Bonus - Υλοποίηση Client σε JavaScript
   - Δημιουργήστε έναν απλό client σε JavaScript που θα αλληλεπιδρά με το API σας.
   - Ο client θα πρέπει να μπορεί να προσθέσει έναν φοιτητή και να εμφανίσει τη λίστα με όλους τους φοιτητές κάνοντας αιτήσεις στο API.

## Τεχνικές Λεπτομέρειες
- Χρησιμοποιήστε το Spring Boot framework.
- Το API πρέπει να επιστρέφει δεδομένα σε JSON μορφή.
- Η τεχνική Dependency Injection πρέπει να χρησιμοποιηθεί για την αυτόματη έγχυση του Service στον Controller.
- Για τον client σε JavaScript, χρησιμοποιήστε `fetch()` για να κάνετε τα αιτήματα προς το API.

## Πόροι για Μελέτη
- **Dependency Injection**: [Wikipedia](https://en.wikipedia.org/wiki/Dependency_injection)

## Παραδοτέα
- Το project Spring Boot με τις κλάσεις `Student`, `HelloService`, και `HelloController`.
- Ένας JavaScript client που επικοινωνεί με το API.

Καλή επιτυχία!

