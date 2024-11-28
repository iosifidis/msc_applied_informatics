# Τεκμηρίωση για όλο το project Student Management API με αυτόματη φόρτωση της λίστας των φοιτητών/τριών

## Τι Κάνει ο Κώδικας

Η εφαρμογή είναι ένα απλό REST API που χρησιμοποιεί Spring Boot για τη διαχείριση μιας λίστας φοιτητών. Παρέχει:  

- Endpoints για την προσθήκη φοιτητών, την ανάκτηση δεδομένων ενός φοιτητή και την εμφάνιση όλων των φοιτητών.   
- Ενσωμάτωση με βάση δεδομένων MySQL για την αποθήκευση και ανάκτηση δεδομένων.   
- Αυτόματο φόρτωμα δεδομένων φοιτητών από τη βάση κατά την εκκίνηση της εφαρμογής.   

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

Είναι ο REST Controller που ορίζει τα endpoints της εφαρμογής.

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
  
### Endpoints:
- `/`: Επιστρέφει ένα μήνυμα "Hello World".   
- `/list`: Επιστρέφει μια στατική λίστα με strings.   
- `/askName/{name}`: Χαιρετάει έναν χρήστη με το όνομά του.   
- `/student`: Δημιουργεί και επιστρέφει ένα αντικείμενο Student βάσει παραμέτρων.   
- `/students`: Επιστρέφει όλους τους φοιτητές από τη μνήμη.   
- `/addStudent`: Προσθέτει έναν νέο φοιτητή και τον αποθηκεύει στη βάση δεδομένων.  

**ΠΡΟΣΟΧΗ**: Όταν η μέθοδος καλεί την κλάση του Service, και έχει να κάνει με κλήση της βάσης δεδομένων, πάντα να προστίθεται `throws Exception` γιατί η βάση μπορεί να μην υπάρχει ή να μην μπορεί να κάνει σύνδεση με τον server της βάσης.  
  
## 4. Κλάση HelloService

- Κλάση υπηρεσίας που περιέχει τη λογική της εφαρμογής.   
- Διατηρεί μια λίστα φοιτητών στη μνήμη.   

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
  - `getAllStudents()`: Επιστρέφει όλους τους φοιτητές από τη μνήμη.   
  - `saveToDB()`: Αποθηκεύει έναν φοιτητή στη βάση δεδομένων.   
  - `setList()`: Ορίζει τη λίστα των φοιτητών από εξωτερική πηγή.  

## 5. Κλάση HelloServiceConfig

- Κλάση ρύθμισης που εκτελείται κατά την εκκίνηση της εφαρμογής.   
- Φορτώνει όλους τους φοιτητές από τη βάση δεδομένων και τους αποθηκεύει στη μνήμη μέσω της HelloService.   

```
@Configuration
public class HelloServiceConfig implements CommandLineRunner{
	
	@Autowired
	private HelloService hs;

	@Override
	public void run(String... args) throws Exception {
		hs.setList(loadFromDB());
	}
	
	private ArrayList<Student> loadFromDB() throws Exception { // Αλλάζω void σε ArrayList<Student>
		  ArrayList<Student> sList = new ArrayList<Student>();
		  
	      // create our mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost/university";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM students";

	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        String name = rs.getString("name");
	        String location = rs.getString("location");
	        int age = rs.getInt("age");
	        sList.add(new Student(name, age, location));
	      }
	      st.close();
		
	      // Επιστρέφω την λίστα
	      return sList;
	      
	    
	}
	
	
}
```

Μεταφέρω την `loadFromDB()` (στο προηγούμενο παράδειγμα λεγόταν `getStudents()`) στο Config

## Διάγραμμα Ενότητας

<pre>
+-------------------------------------+
|       Student (Δεδομένα)            |
+-------------------------------------+
                ^
                |
+-------------------------------+      +-------------------------------+
|       HelloService            |<---->|       HelloServiceConfig      |
|  - Διαχειρίζεται τη λογική    |      |  - Φορτώνει δεδομένα από DB   |
|  - Αλληλεπιδρά με τη βάση     |      |  - Αρχικοποιεί HelloService   |
+-------------------------------+      +-------------------------------+
                ^
                |
+-------------------------------+
|       HelloController         |
|  - Ορίζει τα API endpoints    |
|  - Αντιστοιχεί στη λογική     |
+-------------------------------+


</pre>

## Γιατί Χρησιμοποιούνται οι Κλάσεις Service και Config

- Service

  - Σκοπός: Συγκεντρώνει τη λογική της εφαρμογής.   
  - Λόγος: Διατηρεί τους controllers καθαρούς, αναθέτοντας τη λογική στην υπηρεσία.   

- Config

  - Σκοπός: Φορτώνει δεδομένα φοιτητών από τη βάση στη μνήμη κατά την εκκίνηση.   
  - Λόγος: Εξασφαλίζει ότι η εφαρμογή είναι έτοιμη να λειτουργήσει με δεδομένα, χωρίς να απαιτείται χειροκίνητη εισαγωγή ή επιστροφή κενών αποτελεσμάτων.   

## Γιατί Φορτώνεται η Λίστα από τη Βάση στην Εκκίνηση;

- Λόγος: Εξασφαλίζει ότι η εφαρμογή είναι πλήρως λειτουργική κατά την εκκίνηση, με δεδομένα ήδη διαθέσιμα για τα endpoints όπως `/students` και `/askName`.   
- Οφέλη: Βελτιώνει τη χρηστικότητα και αποφεύγει κενά αποτελέσματα ή χειροκίνητη προετοιμασία δεδομένων.   

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

2. `@RestController`:   

 - Δηλώνει ότι η κλάση είναι REST Controller.   
 - Συνδυάζει τα @Controller και @ResponseBody.   
 
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
 
9. `@Configuration`:  
 
 - Υποδεικνύει ότι η κλάση περιέχει ρυθμίσεις του Spring. 
 
<hr />
 
## Πώς Ένα Service Μπορεί να Αρχικοποιηθεί

### Spring Dependency Injection

Η αρχικοποίηση ενός service στο Spring Boot δεν γίνεται με τη δημιουργία αντικειμένων μέσω `new`, αλλά μέσω μηχανισμών Dependency Injection που παρέχει το Spring Framework. Αυτό επιτυγχάνεται με τα εξής:

1. Σήμανση της κλάσης ως `@Service`

 - Το Spring δημιουργεί αυτόματα ένα bean της κλάσης και το διαχειρίζεται καθ' όλη τη διάρκεια ζωής της εφαρμογής.   

Παράδειγμα:
```
@Service
public class HelloService {
    // Business logic
}
```

2. Χρήση του `@Autowired`   

 - Η εξάρτηση εγχέεται στην κλάση που τη χρειάζεται, χωρίς να χρειάζεται χειροκίνητη αρχικοποίηση.   

Παράδειγμα:
```
@RestController
public class HelloController {
    @Autowired
    private HelloService hs; // Injected by Spring
}
```

## Ρόλος του `@Configuration` και του `CommandLineRunner`

Η χρήση της κλάσης `HelloServiceConfig` ως `@Configuration` με την υλοποίηση του `CommandLineRunner` εξασφαλίζει ότι μια συγκεκριμένη εργασία θα εκτελεστεί κατά την εκκίνηση της εφαρμογής.

1. Ρόλος του `@Configuration`

 - Δηλώνει ότι η κλάση περιέχει bean definitions και ρυθμίσεις.   

Παράδειγμα:
```
@Configuration
public class HelloServiceConfig { ... }
```

2. Ρόλος του `CommandLineRunner`

 - Εκτελεί κώδικα αμέσως μετά την εκκίνηση της εφαρμογής.   
 - Στη συγκεκριμένη εφαρμογή χρησιμοποιείται για τη φόρτωση δεδομένων από τη βάση στη λίστα των φοιτητών.   

```@Override
public void run(String... args) throws Exception {
    hs.setList(loadFromDB()); // Load data into the service
}
```

## Που Θα Βρίσκεται η loadFromDB (στο προηγούμενο παράδειγμα λεγόταν `getStudents()`)

Η μέθοδος `loadFromDB` βρίσκεται στην κλάση `HelloServiceConfig`, καθώς ο ρόλος της είναι να φορτώνει δεδομένα από τη βάση δεδομένων κατά την εκκίνηση της εφαρμογής. Η θέση της εδώ είναι σωστή επειδή:   

1. Συνδυάζεται με το `CommandLineRunner`:   

 - Εκτελείται αυτόματα στην αρχή της εφαρμογής.

2. Ρυθμίζει τα δεδομένα στο service:   

 - Καλεί τη μέθοδο `setList` της `HelloService` για να μεταφέρει τα δεδομένα στη μνήμη.
 
3. Χωρισμός Ευθυνών:   

 - Η μέθοδος αυτή είναι υπεύθυνη αποκλειστικά για τη φόρτωση δεδομένων και όχι για την επιχειρησιακή λογική της εφαρμογής.

Παράδειγμα της `loadFromDB`:

```
private ArrayList<Student> loadFromDB() throws Exception {
    ArrayList<Student> sList = new ArrayList<>();
    String myDriver = "org.gjt.mm.mysql.Driver";
    String myUrl = "jdbc:mysql://localhost/university";
    Class.forName(myDriver);
    Connection conn = DriverManager.getConnection(myUrl, "root", "");

    String query = "SELECT * FROM students";
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(query);

    while (rs.next()) {
        String name = rs.getString("name");
        String location = rs.getString("location");
        int age = rs.getInt("age");
        sList.add(new Student(name, age, location));
    }
    st.close();
    return sList;
}
```

## Γιατί Να Φορτώνεται η Λίστα από τη Βάση κατά την Εκκίνηση

1. Ετοιμότητα Δεδομένων:

 - Η εφαρμογή είναι άμεσα λειτουργική, καθώς οι λίστες δεδομένων είναι ήδη στη μνήμη.

2. Αποφυγή Κενού Αποτελέσματος:

 - Η λίστα δεν είναι κενή όταν ο χρήστης καλεί το endpoint /students.

3. Βελτίωση Απόδοσης:

 - Η ανάκτηση δεδομένων από τη βάση γίνεται μόνο μία φορά κατά την εκκίνηση, μειώνοντας τη συχνότητα σύνδεσης στη βάση δεδομένων.

4. Αποσύνδεση Ευθυνών:

 - Το HelloService επικεντρώνεται στη λογική της εφαρμογής, ενώ η διαχείριση της φόρτωσης δεδομένων γίνεται από την HelloServiceConfig.
