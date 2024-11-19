## Τεκμηρίωση για το project Student Management API

Αυτός ο κώδικας περιγράφει μια εφαρμογή Spring Boot που υλοποιεί ένα σύστημα διαχείρισης φοιτητών με τη χρήση ενός RESTful API. Ας αναλύσουμε τις κλάσεις, τις λειτουργίες τους, και τη χρήση του Service Layer.

## 1. Κλάση DemoApplication

Η κύρια κλάση της εφαρμογής που εκκινεί το Spring Boot.

```
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

Ρόλος:   
- Ορίζει την εκκίνηση της εφαρμογής.   

- Χρησιμοποιεί το annotation `@SpringBootApplication` για να ενεργοποιήσει λειτουργίες όπως:   
 - Component Scanning: Σαρώνονται και εντοπίζονται όλες οι κλάσεις που φέρουν Spring annotations.   
 - Auto-Configuration: Αυτόματη ρύθμιση παραμέτρων για το project.   

## 2. Κλάση HelloService

Η Service Layer κλάση που διαχειρίζεται την επιχειρηματική λογική.

```
@Service
public class HelloService {
    
    ArrayList<Student> sList = new ArrayList<>();

    public Student getStudent(String n, int a, String l) {
        return new Student(n, a, l);
    }

    public void addStudent(String n, int a, String l) {
        sList.add(new Student(n, a, l));
    }

    public ArrayList<Student> getAllStudents() {
        return sList;
    }
}
```

Ρόλος:  
- Διαχωρισμός Λογικής: Ο κώδικας που αφορά την επιχειρηματική λογική βρίσκεται εδώ, διαχωρίζοντας το από τον controller.   
- Αποθήκευση Δεδομένων: Διαχειρίζεται μια λίστα `sList` που περιέχει όλους τους φοιτητές.   
- Λειτουργίες:   
 - Δημιουργία αντικειμένου `Student` μέσω της μεθόδου `getStudent`.   
 - Προσθήκη φοιτητή στη λίστα με τη μέθοδο `addStudent`.   
 - Ανάκτηση όλων των φοιτητών μέσω της `getAllStudents`.   

## 3. Κλάση `HelloController`

Ο Controller που διαχειρίζεται τα αιτήματα από τον χρήστη και κατευθύνει τις ενέργειες προς την `HelloService`.

```
@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class HelloController {

    @Autowired
    private HelloService hs;

    @GetMapping(path="/askName/{name}")
    public String askName(@PathVariable(value="name") String name) {
        return "Hello " + name;
    }

    @GetMapping(path="/addstudent")
    public void addStudent(
            @RequestParam(value="name") String name,
            @RequestParam(value="age") int age,
            @RequestParam(value="location") String location) {
        hs.addStudent(name, age, location);
    }

    @GetMapping(path="/allStudents")
    public ArrayList<Student> allStudents() {
        return hs.getAllStudents();
    }
}
```

Ρόλος:   
- Δέχεται HTTP αιτήματα και κατευθύνει τη ροή δεδομένων στην HelloService.  

- Endpoints:   
1. `/askName/{name}`:   
 - Επιστρέφει προσωποποιημένο μήνυμα.   
 - Παράδειγμα URL: `http://localhost:8080/askName/Stathis`   
 - Απάντηση: `"Hello Stathis"`   

2. `/addstudent`:
 - Προσθέτει φοιτητή στη λίστα.   
 - Παράδειγμα URL: `http://localhost:8080/addstudent?name=Gio&age=24&location=Salonica`   

3. `/allStudents`:
 - Επιστρέφει όλους τους φοιτητές ως JSON.   
 - Παράδειγμα URL: `http://localhost:8080/allStudents`   
 - Απάντηση:

```
[
  {"name": "Gio", "age": 24, "location": "Salonica"}
]
```

### Γιατί Service Layer;

- Καλύτερη Αρχιτεκτονική: Εξασφαλίζει τον διαχωρισμό ευθυνών μεταξύ του `Controller` και της επιχειρηματικής λογικής.   
- Ευκολία Ελέγχου: Μπορούμε εύκολα να κάνουμε unit testing στην `HelloService` ανεξάρτητα από τον controller.   
- Επαναχρησιμοποίηση Κώδικα: Η `HelloService` μπορεί να χρησιμοποιηθεί από πολλούς controllers.   

## 4. Κλάση Student
Αντιπροσωπεύει την οντότητα "Φοιτητής" με ιδιότητες `name`, `age`, και `location`.

```
public class Student {
    
    private String name;
    private int age;
    private String location;

    public Student(String n, int a, String l) {
        this.name = n;
        this.age = a;
        this.location = l;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getLocation() {
        return location;
    }
}
```

Ρόλος:
- Αντικείμενο που αποθηκεύει τις πληροφορίες ενός φοιτητή.   
- Παρέχει getters που χρησιμοποιούνται από το Spring για να μετατρέψει τα δεδομένα σε JSON.   

## Διάγραμμα Ενότητας

<pre>
+---------------------+
| DemoApplication     |
| (Entry Point)       |
+---------------------+
           |
           v
+---------------------+
| HelloController     |
| (REST API)          |
|  - /askName         |
|  - /addstudent      |
|  - /allStudents     |
+---------------------+
           |
           v
+---------------------+
| HelloService        |
| (Business Logic)    |
|  - addStudent       |
|  - getStudent       |
|  - getAllStudents   |
+---------------------+
           |
           v
+---------------------+
| Student             |
| (Data Model)        |
|  - name, age, loc.  |
+---------------------+
</pre>

## Παράδειγμα Εκτέλεσης

1. Εκκίνηση Εφαρμογής

- Τρέξτε την κλάση `DemoApplication`.   
- Η εφαρμογή εκκινεί στη θύρα `8080`.   

2. Κλήσεις στα Endpoints

- `GET /askName/Nikos`   
Απάντηση: `"Hello Nikos"`

- `GET /addstudent?name=Maria&age=22&location=Athens`   
Προσθήκη φοιτητή.

- `GET /allStudents`
Επιστρέφει
```
[
  {"name": "Maria", "age": 22, "location": "Athens"}
]
```

## Συμπεράσματα

- Η δομή αυτή παρέχει καθαρό διαχωρισμό μεταξύ Controller, Service, και Model.   
- Η χρήση του Service Layer εξασφαλίζει επεκτασιμότητα και εύκολη συντήρηση του κώδικα.   

# Λίστα με Annotations και Επεξήγηση

## @RestController

Χρησιμοποιείται στην κλάση HelloController για να ορίσει την κλάση ως Controller που διαχειρίζεται HTTP αιτήματα σε ένα RESTful API. Το @RestController συνδυάζει τα @Controller και @ResponseBody, υποδεικνύοντας ότι οι μέθοδοι θα επιστρέφουν δεδομένα (όχι HTML) που θα μετατραπούν αυτόματα σε JSON απόκριση.

## @GetMapping

Χρησιμοποιείται στις μεθόδους του Controller (askName, addStudent, allStudents) για να ορίσει τις μεθόδους που θα διαχειρίζονται τα HTTP GET αιτήματα. Το @GetMapping καθορίζει τη διαδρομή (URL path) που πρέπει να χρησιμοποιηθεί για να καλέσουμε την κάθε μέθοδο.

## @RequestParam

Χρησιμοποιείται στις μεθόδους addStudent για να αναγνωρίσει παραμέτρους στο URL. Το @RequestParam χρησιμοποιείται για να δεχτεί παραμέτρους από το query string (π.χ., ?name=Gio&age=24&location=Salonica) και να τις περάσει ως μεταβλητές στις μεθόδους.

## @PathVariable

Χρησιμοποιείται στη μέθοδο askName για να δεχτεί ένα δυναμικό τμήμα του URL ως παράμετρο (π.χ., http://localhost:8080/askName/Nikos). Το @PathVariable επιτρέπει στη μέθοδο να αναγνωρίσει τη συγκεκριμένη τιμή που περιέχεται στο URL και να την αντιστοιχίσει στη μεταβλητή της μεθόδου.

## @CrossOrigin

Χρησιμοποιείται στην κλάση HelloController για να επιτρέψει τα αιτήματα από άλλες πηγές (CORS - Cross-Origin Resource Sharing). Το @CrossOrigin(origins="*", allowedHeaders="*") επιτρέπει τα αιτήματα από οποιοδήποτε domain και header, κάτι χρήσιμο για APIs που πρόκειται να χρησιμοποιηθούν από διαφορετικούς clients.

## @Service

Χρησιμοποιείται στην κλάση HelloService για να ορίσει ότι αυτή η κλάση είναι ένα Service, που αποτελεί μέρος της λογικής της εφαρμογής και δεν διαχειρίζεται τα αιτήματα άμεσα. Το @Service είναι ένα από τα stereotypes του Spring που βοηθά το framework να εντοπίσει και να διαχειριστεί την κλάση ως Spring bean.

## @Autowired

Χρησιμοποιείται για να επιτρέψει την αυτόματη έγχυση εξαρτήσεων από το Spring. Στην κλάση HelloController, το @Autowired επιτρέπει στο framework να εγχύσει ένα instance της HelloService μέσα στον Controller, έτσι ώστε να καλούνται οι μέθοδοι του Service χωρίς να δημιουργείται νέο instance της HelloService.

# Λίστα με Annotations και Επεξήγηση

## @RestController

Χρησιμοποιείται στην κλάση HelloController για να ορίσει την κλάση ως Controller που διαχειρίζεται HTTP αιτήματα σε ένα RESTful API. Το @RestController συνδυάζει τα @Controller και @ResponseBody, υποδεικνύοντας ότι οι μέθοδοι θα επιστρέφουν δεδομένα (όχι HTML) που θα μετατραπούν αυτόματα σε JSON απόκριση.

## @GetMapping

Χρησιμοποιείται στις μεθόδους του Controller (askName, addStudent, allStudents) για να ορίσει τις μεθόδους που θα διαχειρίζονται τα HTTP GET αιτήματα. Το @GetMapping καθορίζει τη διαδρομή (URL path) που πρέπει να χρησιμοποιηθεί για να καλέσουμε την κάθε μέθοδο.

## @RequestParam

Χρησιμοποιείται στις μεθόδους addStudent για να αναγνωρίσει παραμέτρους στο URL. Το @RequestParam χρησιμοποιείται για να δεχτεί παραμέτρους από το query string (π.χ., ?name=Gio&age=24&location=Salonica) και να τις περάσει ως μεταβλητές στις μεθόδους.

## @PathVariable

Χρησιμοποιείται στη μέθοδο askName για να δεχτεί ένα δυναμικό τμήμα του URL ως παράμετρο (π.χ., http://localhost:8080/askName/Nikos). Το @PathVariable επιτρέπει στη μέθοδο να αναγνωρίσει τη συγκεκριμένη τιμή που περιέχεται στο URL και να την αντιστοιχίσει στη μεταβλητή της μεθόδου.

## @CrossOrigin

Χρησιμοποιείται στην κλάση HelloController για να επιτρέψει τα αιτήματα από άλλες πηγές (CORS - Cross-Origin Resource Sharing). Το @CrossOrigin(origins="*", allowedHeaders="*") επιτρέπει τα αιτήματα από οποιοδήποτε domain και header, κάτι χρήσιμο για APIs που πρόκειται να χρησιμοποιηθούν από διαφορετικούς clients.

## @Service

Χρησιμοποιείται στην κλάση HelloService για να ορίσει ότι αυτή η κλάση είναι ένα Service, που αποτελεί μέρος της λογικής της εφαρμογής και δεν διαχειρίζεται τα αιτήματα άμεσα. Το @Service είναι ένα από τα stereotypes του Spring που βοηθά το framework να εντοπίσει και να διαχειριστεί την κλάση ως Spring bean.

## @Autowired

Χρησιμοποιείται για να επιτρέψει την αυτόματη έγχυση εξαρτήσεων από το Spring. Στην κλάση HelloController, το @Autowired επιτρέπει στο framework να εγχύσει ένα instance της HelloService μέσα στον Controller, έτσι ώστε να καλούνται οι μέθοδοι του Service χωρίς να δημιουργείται νέο instance της HelloService.
