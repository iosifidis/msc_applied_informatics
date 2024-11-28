# Τεκμηρίωση για όλο το project Student Management API

Ο κώδικας συνδέεται σε μια βάση δεδομένων MySQL χρησιμοποιώντας JDBC (Java Database Connectivity) για να διαχειριστεί δεδομένα φοιτητών. Παρέχει δύο βασικές λειτουργίες:

1. Λήψη όλων των φοιτητών από τη βάση δεδομένων:   
 - Με τη μέθοδο `getStudents()`, δημιουργεί μια σύνδεση με τη βάση, εκτελεί ένα `SELECT` ερώτημα, και αποθηκεύει τα αποτελέσματα σε μια λίστα (`sList`).   

2. Εγγραφή νέου φοιτητή στη βάση:   
 - Με τη μέθοδο `addStudent(name, age, location)`, δημιουργεί μια νέα εγγραφή στη βάση δεδομένων χρησιμοποιώντας ένα `INSERT INTO` ερώτημα με `PreparedStatement`.

## 1. Κλάση DemoApplication

```
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

- Αυτή είναι η κύρια κλάση του Spring Boot project.   
- Ορίζει το `@SpringBootApplication`, το οποίο ενεργοποιεί τις εξής λειτουργίες:   
  - Αυτόματη διαμόρφωση (auto-configuration).   
  - Εντοπισμός component και bean.   
  - Ενεργοποίηση του Spring context.   
- Η `main` μέθοδος εκκινεί την εφαρμογή.   

## 2. Κλάση Student

```
public class Student {
    private String name;
    private int age;
    private String location;

    public Student(String n, int a, String l) {
        name = n;
        age = a;
        location = l;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getLocation() { return location; }
}
```

- Ρόλος: Αναπαριστά έναν φοιτητή με πεδία για όνομα, ηλικία, και τοποθεσία.   
- Getters: Τα πεδία της κλάσης παρέχονται μέσω getters, ώστε να μπορούν να επιστραφούν ως JSON όταν η κλάση χρησιμοποιείται σε REST APIs.   
- Constructor: Επιτρέπει την εύκολη δημιουργία αντικειμένων `Student`.   

## 3. Κλάση HelloController

```
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {

    @Autowired
    private HelloService hs;

    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/list")
    public List<String> printList() {
        List<String> sList = new ArrayList<>();
        sList.add("Hello World");
        sList.add("My name is Apostolis");
        sList.add("My surname is Ampatzoglou");
        return sList;
    }

    @GetMapping(path = "/askName/{name}")
    public String askName(@PathVariable(value = "name") String name) {
        return "Hello " + name;
    }

    @GetMapping(path = "/student")
    public Student getStudent(@RequestParam(value = "name") String name,
                              @RequestParam(value = "age") int age,
                              @RequestParam(value = "location") String location) {
        return hs.getStudent(name, age, location);
    }

    @GetMapping(path = "/students")
    public List<Student> getAllStudent() throws Exception {
        hs.getStudents();
        return hs.sList;
    }

    @GetMapping(path = "/addStudent")
    public void addStudent(@RequestParam(value = "name") String name,
                           @RequestParam(value = "age") int age,
                           @RequestParam(value = "location") String location) throws Exception {
        hs.addStudent(name, age, location);
    }
}
```

- Ορίζονται τα REST API endpoints που χειρίζονται αιτήματα προς την εφαρμογή.   
- Χρησιμοποιούνται Spring annotations όπως:   
  - `@RestController`: Υποδεικνύει ότι η κλάση είναι ελεγκτής για RESTful αιτήματα.   
  - `@CrossOrigin`: Επιτρέπει την πρόσβαση από οποιονδήποτε τομέα.   
  - `@GetMapping`: Δημιουργεί endpoints με HTTP GET αιτήματα.   
  - `@PathVariable` και `@RequestParam`: Επιτρέπει την ανάκτηση παραμέτρων από το URL ή το query string.   

- Κάθε endpoint παρέχει διαφορετική λειτουργία:   
  - Επιστροφή φοιτητή (ως JSON).   
  - Προσθήκη φοιτητή στη βάση.   
  - Επιστροφή όλων των φοιτητών.   

**ΠΡΟΣΟΧΗ**: Όταν η μέθοδος καλεί την κλάση του Service, και έχει να κάνει με κλήση της βάσης δεδομένων, πάντα να προστίθεται `throws Exception` γιατί η βάση μπορεί να μην υπάρχει ή να μην μπορεί να κάνει σύνδεση με τον server της βάσης.  
  
## 4. Κλάση HelloService

```
@Service
public class HelloService {

    List<Student> sList = new ArrayList<>();

    public Student getStudent(String n, int a, String l) {
        return new Student(n, a, l);
    }

    public void getStudents() throws Exception {
        sList = new ArrayList<>();
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/university";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");

        String q = "SELECT * FROM students";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(q);

        while (rs.next()) {
            String name = rs.getString("name");
            String location = rs.getString("location");
            int age = rs.getInt("age");
            sList.add(new Student(name, age, location));
        }
        st.close();
    }

    public void addStudent(String name, int age, String location) throws Exception {
        getStudents();
        sList.add(new Student(name, age, location));

        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/university";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");

        String q = "insert into student values (?,?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(q);
        preparedStmt.setString(1, name);
        preparedStmt.setInt(2, age);
        preparedStmt.setString(3, location);
        preparedStmt.execute();
    }
}
```

- Ρόλος: Υλοποιεί τη λογική διαχείρισης δεδομένων.

- Service Layer:
  - Χρησιμοποιείται για να διαχωρίσει τη λογική των δεδομένων από τον controller.   
  - Αλληλεπιδρά με τη βάση δεδομένων και επιστρέφει τα αποτελέσματα στον ελεγκτή.   

- Μέθοδοι:
  - `getStudents`: Συνδέεται με τη βάση και ανακτά όλους τους φοιτητές.   
  - `addStudent`: Προσθέτει φοιτητή στη βάση και ενημερώνει τη λίστα.  
  - Χρησιμοποιεί `PreparedStatement` για ασφαλή αποθήκευση δεδομένων.  

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
|  - /addStudent      |
|  - /students        |
|  - /list            |
+---------------------+
           |
           v
+---------------------+
| HelloService        |
| (Business Logic)    |
|  - addStudent       |
|  - getStudent       |
|  - getStudents      |
+---------------------+
           |
           v
+---------------------+
| Student             |
| (Data Model)        |
|  - String name      |
|  - int age          |
|  - String location  |
+---------------------+

</pre>

### Περιγραφή Διάγραμμα Ενότητας

1. DemoApplication:

 - Κύριο σημείο εισόδου (entry point) της εφαρμογής Spring Boot.   
 - Εκκινεί την εφαρμογή και κατευθύνει τις αιτήσεις στο σωστό controller.   

2. HelloController:

 - Υλοποιεί τα endpoints REST API.   
 - Παρέχει σημεία πρόσβασης για την προσθήκη, ανάκτηση ή εμφάνιση φοιτητών.   
 
3. HelloService:

 - Περιέχει τη λογική της εφαρμογής (business logic).   
 - Διαχειρίζεται τη σύνδεση με τη βάση δεδομένων και την επιχειρηματική λειτουργικότητα.   

4. Student:

 - Αναπαριστά το μοντέλο δεδομένων (data model) ενός φοιτητή.   
 - Χρησιμοποιείται από το service και τα API για την αποθήκευση και ανάκτηση δεδομένων.   

## Γιατί Χρησιμοποιούμε Service

- Το `Service Layer` δημιουργείται για να ακολουθήσουμε τη διαστρωμάτωση εφαρμογών:   
  - Controller: Χειρίζεται αιτήματα και αποκρίνεται.   
  - Service: Εφαρμόζει επιχειρηματική λογική.   
  - Repository/Database: Αποθηκεύει και ανακτά δεδομένα.   

- Με αυτό τον τρόπο:   
  - Η δομή γίνεται ευκολότερα επεκτάσιμη.   
  - Κάθε κλάση αναλαμβάνει συγκεκριμένες ευθύνες (Separation of Concerns).   

## Endpoints

1. Hello World: `http://localhost:8080/`  
2. Λίστα με Στατικές Τιμές: `http://localhost:8080/list`   
3. Προσωποποιημένο Μήνυμα: `http://localhost:8080/askName/John`   
4. Δημιουργία Αντικειμένου Φοιτητή: `http://localhost:8080/student?name=John&age=22&location=Athens`   
5. Λήψη Όλων των Φοιτητών από τη Βάση: `http://localhost:8080/students`   
6. Προσθήκη Νέου Φοιτητή: `http://localhost:8080/addStudent?name=John&age=22&location=Athens`   
  
## ΣΗΜΕΙΩΣΗ

Πρέπει να προσθέσουμε στο pom.xml αρχείο τα εξής (δες το στον φάκελο):

```
        <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.25</version>
		</dependency>
```

## Χρησιμοποιηθέντα Annotations

1. `@SpringBootApplication`:  

 - Δηλώνει την κύρια κλάση εκκίνησης για μια εφαρμογή Spring Boot.   
 - Περιλαμβάνει τα εξής:   

 - `@Configuration`: Σηματοδοτεί ότι η κλάση περιέχει ρυθμίσεις για την εφαρμογή.   
 - `@EnableAutoConfiguration`: Ενεργοποιεί την αυτόματη ρύθμιση Spring Beans.   
 - `@ComponentScan`: Εντοπίζει και καταχωρεί αυτόματα όλα τα Spring Components.   

2. `@RestController`:   

 - Δηλώνει ότι η κλάση είναι ελεγκτής (controller) για RESTful web services.   
 - Οι μέθοδοι της επιστρέφουν δεδομένα απευθείας σε JSON μορφή.   
 
3. `@CrossOrigin`:   

 - Ενεργοποιεί CORS (Cross-Origin Resource Sharing), επιτρέποντας την πρόσβαση στο API από οποιονδήποτε domain.   

4. `@Autowired`:   

 - Ενεργοποιεί την αυτόματη έγχυση εξαρτήσεων (Dependency Injection).   
 - Χρησιμοποιείται για την αυτόματη σύνδεση της κλάσης `HelloService` στον ελεγκτή `HelloController`.   

5. `@Service`:   

 - Δηλώνει ότι η κλάση `HelloService` είναι ένα Spring Service (λογικό επίπεδο της εφαρμογής).

6. `@GetMapping`:   

 - Δηλώνει ότι η μέθοδος αντιστοιχεί σε HTTP `GET` αίτημα.   
 - Μπορεί να περιλαμβάνει path για την αντιστοίχιση ενός συγκεκριμένου URL.   

7. `@PathVariable`:   

 - Χρησιμοποιείται για την απόσπαση δεδομένων από τη διαδρομή του URL (π.χ., `/askName/{name}`).

8. `@RequestParam`:   

 - Χρησιμοποιείται για την απόσπαση παραμέτρων query από το URL (π.χ., `/student?name=John&age=22`).
