# Τεκμηρίωση για το project Student Management με χρήση Repository

## 1. Περιγραφή

Το project Student Management παρέχει μια εφαρμογή που επιτρέπει την προβολή και τη διαχείριση των φοιτητών μέσω ενός REST API. Η εφαρμογή επιτρέπει στους χρήστες να βλέπουν όλους τους φοιτητές και να προσθέτουν νέους. Το backend της εφαρμογής είναι υλοποιημένο με Spring Boot, ενώ το frontend χρησιμοποιεί JavaScript και jQuery για την επικοινωνία με το API. Επιπλέον, έχει προστεθεί το Swagger για την τεκμηρίωση και τη δοκιμή των API endpoints.

<hr>

## 2. Ενημερωμένα Endpoints

- /students
  - Ρόλος: Επιστρέφει τη λίστα όλων των φοιτητών από τη βάση δεδομένων.  
  - Παράδειγμα URL: `http://localhost:8080/students`  

Απάντηση (JSON):
```
[
    {
        "name": "John Doe",
        "age": 21,
        "location": "Athens"
    },
    {
        "name": "Jane Smith",
        "age": 23,
        "location": "Thessaloniki"
    }
]
```

Κώδικας Control:
```
@GetMapping(path = "/students")
public List<Student> getAllStudent() throws Exception {
    return hs.getAllStudents();
}
```

Ενώ στο Service γίνεται η αλλαγή:

```
public List<Student> getAllStudents() throws Exception {
        return studentRepository.findAll(); // // ΑΛΛΑΓΗ ΕΔΩ με .findAll()
    }
```

- /addStudent
  - Ρόλος: Προσθέτει έναν νέο φοιτητή στη βάση δεδομένων.
  - Παράδειγμα URL: `http://localhost:8080/addStudent`

Σώμα που προσθέτει (JSON):
```
{
    "name": "John Doe",
    "age": 21,
    "location": "Athens"
}
```

Κώδικας Control:
```
@PostMapping(path = "/addStudent")
public void addStudent(@RequestBody Student st) throws Exception {
    hs.addStudent(st);
}
```

Ενώ στο Service γίνεται η αλλαγή:

```
   public void addStudent(Student s) throws Exception {
        studentRepository.save(s); // ΑΛΛΑΓΗ ΕΔΩ με .save(s)
    }
```

ΠΡΟΣΟΧΗ: Στην κλάση Student ο κώδικας γίνεται:

```
@Entity 
public class Student {
	
	@Id 
	private String name;
```

όπου τα annotations σημαίνουν:
- `@Entity`: Ενδεικνύει ότι αυτή η κλάση είναι οντότητα στη βάση δεδομένων   
- `@Id`: Σημαίνει ότι αυτό το πεδίο είναι το πρωτεύον κλειδί της οντότητας

<hr>

## 3. Testing μέσω Swagger UI
Για ευκολότερο testing των endpoints, έχει προστεθεί το Swagger UI:

Προσθήκη Dependencies (στο αρχείο pom.xml):
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.15</version>
</dependency>
```

Ρυθμίσεις στο αρχείο application.properties:
```
springdoc.api-docs.path=/api
springdoc.swagger-ui.path=/api-ui
```
URL Swagger:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Στο Swagger UI, τα διαθέσιμα endpoints `/students` και `/addStudent` εμφανίζονται με περιγραφές και δυνατότητα εκτέλεσης HTTP αιτημάτων.

<hr>

## 4. Ενημερωμένη JavaScript Λειτουργία

Η συνάρτηση addStudent() αποστέλλει POST αίτημα για να προσθέσει έναν φοιτητή:

```
function addStudent() {  
    var settings = {
        "url": "http://localhost:8080/addStudent",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
            "name": document.getElementById("name").value,
            "age": document.getElementById("age").value,
            "location": document.getElementById("location").value
        }),
    };

    $.ajax(settings).done(function (response) {
        showAll();
    });
}
```

<hr>

## 5. Τελικό Διάγραμμα

```
+---------------------+
| DemoApplication     |
| (Entry Point)       |
+---------------------+
           |
           v
+----------------------+
| HelloController      |
| (REST API)           |
|  - /students (GET)   |
|  - /addStudent (POST)|
+----------------------+
           |
           v
+---------------------+
| HelloService        |
| (Business Logic)    |
|  - addStudent       |
|  - getAllStudents   |
+---------------------+
           |
           v
+---------------------+
| Student             |
| (Data Model)        |
|  - name, age, loc.  |
+---------------------+
```

<hr>

## 6. Συμπεράσματα
- Το POST endpoint αντικατέστησε το GET για την προσθήκη φοιτητών, εξασφαλίζοντας καλύτερη χρήση των HTTP μεθόδων.  
- Το Swagger UI διευκολύνει την τεκμηρίωση και τον έλεγχο των API.   
- Η λειτουργία του frontend ενημερώθηκε με τη χρήση του POST endpoint.   

<hr>

## 7. Χρησιμοποιούμενα Annotations
- `@RestController`: Δημιουργεί RESTful API.   
- `@GetMapping`: Χειρίζεται HTTP GET αιτήματα.   
- `@PostMapping`: Χειρίζεται HTTP POST αιτήματα.   
- `@RequestBody`: Δέχεται JSON δεδομένα από το body του αιτήματος.   
- `@Autowired`: Κάνει inject το service class στον controller.   

<hr>

## 8. Repository Pattern

Το Repository Pattern είναι ένα design pattern που απομονώνει τη διαχείριση της πρόσβασης στα δεδομένα και διευκολύνει την εναλλαγή μεταξύ διαφορετικών τύπων δεδομένων χωρίς να επηρεάζεται η εφαρμογή. Ο κύριος ρόλος ενός repository είναι να παρέχει μια αποστειρωμένη και ενιαία διεπαφή για την αποθήκευση και την ανάκτηση αντικειμένων από τη βάση δεδομένων. Με την αντικατάσταση των custom μεθόδων loadFromDB και saveToDB με ένα JpaRepository, απλοποιείται ο κώδικας και βελτιώνεται η συντηρησιμότητα. Το JpaRepository παρέχει έτοιμες μεθόδους, όπως save(), findAll(), findById(), και deleteById(), που μειώνουν τον κώδικα και εξαλείφουν την ανάγκη για επιπλέον SQL ή χειροκίνητη διαχείριση της βάσης δεδομένων. Αυτές οι αλλαγές κάνουν τον κώδικα πιο ευέλικτο και διευκολύνουν την προσθήκη νέων λειτουργιών ή την αντικατάσταση της υποκείμενης βάσης δεδομένων χωρίς να επηρεάζεται η υπόλοιπη εφαρμογή.

Στο αρχείο pom.xml προσθέσαμε:
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```

Στο αρχείο application.properties προσθέτω τον κώδικα (τα ελληνικά σχόλια δεν θα τα πάρει):

```
# Ρυθμίσεις σύνδεσης με βάση δεδομένων για το Spring Boot
spring.datasource.url=jdbc:mysql://localhost/university-repo
# Όνομα χρήστη για τη σύνδεση με MySQL
spring.datasource.username=root
# Κωδικός πρόσβασης για τον χρήστη MySQL
spring.datasource.password=
# JDBC driver για MySQL
spring.datasource.driver-class-name=org.gjt.mm.mysql.Driver

# create = Δημιουργεί νέο schema και προσθέτει πίνακες.
# update = Ενημερώνει το υπάρχον schema για να ταιριάζει με τις κλάσεις οντοτήτων.
spring.jpa.hibernate.ddl-auto=create
# Εμφάνιση SQL ερωτημάτων που παράγει το Hibernate
spring.jpa.show-sql=true
```
