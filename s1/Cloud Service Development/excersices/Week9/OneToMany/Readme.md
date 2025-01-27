# Walkthrough for the Spring Boot Project: Student, Course, and Professor Management System

## Εισαγωγή
Η εργασία αυτή αφορά τη δημιουργία ενός συστήματος διαχείρισης φοιτητών, μαθημάτων και καθηγητών χρησιμοποιώντας το Spring Boot Framework. Το έργο περιλαμβάνει τη δημιουργία οντοτήτων για φοιτητές, μαθήματα και καθηγητές, καθώς και την σύνδεση αυτών μέσω του JPA και της βάσης δεδομένων.

Στο παρακάτω walkthrough, θα εξηγήσουμε κάθε βήμα της υλοποίησης του έργου, καθώς και τις απαραίτητες αλλαγές στο αρχείο `pom.xml` και `application.properties`.

## Βήμα 1: Δημιουργία του Maven Project

1. Δημιουργήστε ένα νέο Maven project στο Spring Boot με το όνομα `Students-OneToMany-week9`.
2. Επιλέξτε το Spring Web και το Spring Data JPA για τη σύνδεση με τη βάση δεδομένων.

Αυτό το έργο χρησιμοποιεί τις παρακάτω βιβλιοθήκες:
- Spring Web


## Βήμα 2: Ρυθμίσεις στο pom.xml

Ανοίξτε το αρχείο `pom.xml` και προσθέστε τις παρακάτω εξαρτήσεις για να χρησιμοποιήσετε το Spring Boot, Spring Data JPA:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.25</version>
	</dependency>
    
    <dependency>
		    <groupId>org.springdoc</groupId>
		    <artifactId>springdoc-openapi-ui</artifactId>
		    <version>1.6.15</version>
		</dependency>
</dependencies>
```

Αυτές οι εξαρτήσεις περιλαμβάνουν το Spring Boot Starter για τη βάση δεδομένων, το Web Starter για την κατασκευή REST API.

## Βήμα 3: Ρυθμίσεις στο application.properties

Ανοίξτε το αρχείο `src/main/resources/application.properties` και προσθέστε τις παρακάτω ρυθμίσεις:

```properties
springdoc.api-docs.path=/api
springdoc.swagger.ui.path=/api-ui

spring.datasource.url=jdbc:mysql://localhost/university
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=org.gjt.mm.mysql.Driver
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

URL Swagger:

[http://localhost:8080/swagger-ui/index.html]](http://localhost:8080/swagger-ui/index.html)

Στο Swagger, εμφανίζονται τα διαθέσιμα endpoints με περιγραφές και δυνατότητα εκτέλεσης HTTP αιτημάτων.

## Βήμα 4: Δημιουργία Οντοτήτων

### Student Entity (Φοιτητής)

Η οντότητα `Student` περιλαμβάνει τα πεδία `name`, `age` και `location`. Στην περίπτωση αυτή, ο φοιτητής μπορεί να έχει πολλά μαθήματα (many-to-many σχέση):

```java
@Entity
public class Student {
    @Id
    private String name;
    private int age;
    private String location;
    
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="student_courses", 
               joinColumns = @JoinColumn(name="student_name"),
               inverseJoinColumns = @JoinColumn(name="course_name"))
    private Set<Course> courses = new HashSet<Course>();
}
```

### Course Entity (Μάθημα)

Η οντότητα `Course` περιλαμβάνει τα πεδία `name`, `semester` και τη σχέση με τους φοιτητές μέσω του `many-to-many` και με τον καθηγητή μέσω της σχέσης `many-to-one`:

```java
@Entity
public class Course {
    @Id
    private String name;
    private int semester;
    
    @ManyToMany(mappedBy="courses")
    private Set<Student> students = new HashSet<Student>();
    
    @ManyToOne
    @JoinColumn(name="professor_name")
    private Professor lecturer; 
}
```

### Professor Entity (Καθηγητής)

Η οντότητα `Professor` περιλαμβάνει τα πεδία `name` και `rank`, με τη σχέση `one-to-many` με τα μαθήματα:

```java
@Entity
public class Professor {
    @Id
    private String name;
    private String rank;
    
    @OneToMany(mappedBy="name", 
               cascade= CascadeType.ALL,
               fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<Course>();
}
```

## Βήμα 5: Δημιουργία των Repositories

Χρησιμοποιούμε τα `JpaRepository` interfaces για να αλληλεπιδράσουμε με τη βάση δεδομένων.

### StudentRepository

```java
public interface StudentRepository extends JpaRepository<Student, String> {
}
```

### ProfessorRepository

```java
public interface ProfessorRepository extends JpaRepository<Professor, String> {
}
```

## Βήμα 6: Δημιουργία του Service Layer

Στο `HelloService`, δημιουργούμε τις μεθόδους για την προσθήκη φοιτητών, καθηγητών και μαθημάτων στη βάση δεδομένων.

```java
@Service
public class HelloService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessorRepository profRepository;

    public void addStudent(Student s) throws Exception {
        Optional<Student> byId = studentRepository.findById(s.getName());
        if (!byId.isPresent()) {
            studentRepository.save(s);
        }
    }

    public void addProfessor(Professor p) throws Exception {
        Optional<Professor> byId = profRepository.findById(p.getName());
        if (!byId.isPresent()) {
            profRepository.save(p);
        }
    }

    public List<Student> getAllStudents() throws Exception {
        return studentRepository.findAll();
    }
}
```

## Βήμα 7: Δημιουργία του Controller

Ο controller παρέχει τα API endpoints για την αλληλεπίδραση με την εφαρμογή.

```java
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {

    @Autowired
    private HelloService hs;

    @GetMapping(path="/students")
    public List<Student> getAllStudents() throws Exception {
        return hs.getAllStudents();
    }

    @PostMapping(path="/addStudent")
    public void addStudent(@RequestBody Student st) throws Exception {
        hs.addStudent(st);
    }
}
```

## Βήμα 8: Ρυθμίσεις στην Κλάση `HelloServiceConfig`

Η κλάση `HelloServiceConfig` χρησιμοποιείται για την αρχικοποίηση δεδομένων (φοιτητών, καθηγητών και μαθημάτων) κατά την εκκίνηση της εφαρμογής:

```java
@Configuration
public class HelloServiceConfig implements CommandLineRunner {
    @Autowired
    private HelloService hs;

    @Override
    public void run(String... args) throws Exception {
        Professor p1 = new Professor("Apostolis", "Associate Prof");
        hs.addProfessor(p1);
        Professor p2 = new Professor("Daniel", "Assistant Prof");
        hs.addProfessor(p2);

        Course c1 = new Course("SQA", 5);
        c1.setProfessor(p1);
        hs.addCourse(c1);

        Student s1 = new Student("Nikos", 28, "Thessaloniki");
        s1.addCourse(c1);
        hs.addStudent(s1);
    }
}
```

## Συμπεράσματα

Με την ολοκλήρωση του παραπάνω walkthrough, δημιουργήσαμε μια εφαρμογή Spring Boot για τη διαχείριση φοιτητών, μαθημάτων και καθηγητών. Το έργο περιλαμβάνει τη δημιουργία οντοτήτων, την αλληλεπίδραση με τη βάση δεδομένων μέσω JPA και την παροχή RESTful APIs για την προσθήκη και εμφάνιση δεδομένων.

