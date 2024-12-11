# Τεκμηρίωση για το Project Market Management με χρήση Repository

## 1. Περιγραφή

Το project Market Management παρέχει μια εφαρμογή που επιτρέπει την προβολή και διαχείριση προϊόντων μέσω ενός REST API. Οι χρήστες μπορούν να βλέπουν όλα τα διαθέσιμα προϊόντα, να προσθέτουν νέα προϊόντα και να αναζητούν προϊόντα βάσει ονόματος. Το backend της εφαρμογής είναι υλοποιημένο με Spring Boot, ενώ υποστηρίζεται η χρήση Spring Data JPA για την αλληλεπίδραση με τη βάση δεδομένων.

<hr>

## 2. Repository Pattern

Το Repository Pattern απομονώνει τη διαχείριση δεδομένων από την επιχειρησιακή λογική. Στο project αυτό, το JpaRepository παρέχει έτοιμες μεθόδους, όπως `save()`, `findAll()` και `findByName()`, μειώνοντας την ανάγκη για custom SQL queries. Το pattern αυτό διευκολύνει τη συντήρηση και επέκταση του κώδικα, καθώς επιτρέπει τη χρήση διαφορετικών βάσεων δεδομένων χωρίς αλλαγές στο υπόλοιπο σύστημα.

Εξαρτήσεις (pom.xml):
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Ρυθμίσεις για τη βάση δεδομένων (application.properties):
```
spring.datasource.url=jdbc:mysql://localhost/market-repo
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=org.gjt.mm.mysql.Driver
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

<hr>

## 3. Ενημερωμένα Endpoints και Κλάσεις

### Κλάση `MarketRepository`

Το `MarketRepository` επεκτείνει το `JpaRepository`, το οποίο παρέχει έτοιμες μεθόδους για τη διαχείριση δεδομένων της οντότητας `Product`.

```
public interface MarketRepository extends JpaRepository<Product, Integer> {
    Product findByName(String pr); // Custom query για εύρεση προϊόντος βάσει ονόματος
}
```

### Κλάση `Product`

Η κλάση `Product` ορίζει την οντότητα που αντιπροσωπεύει τα προϊόντα. Περιέχει τα πεδία `code` (πρωτεύον κλειδί), `name` και `price`.

```
@Entity
public class Product {
    @Id
    private Integer code;
    private String name;
    private Integer price;

    // Constructors, Getters, και Setters
}
```

όπου τα annotations σημαίνουν:

- `@Entity`: Ενδεικνύει ότι αυτή η κλάση είναι οντότητα στη βάση δεδομένων   
- @Id`: Σημαίνει ότι αυτό το πεδίο είναι το πρωτεύον κλειδί της οντότητας   

### Κλάση `MarketService`

Η κλάση `MarketService` περιέχει την επιχειρησιακή λογική. Χρησιμοποιεί το `MarketRepository` για την αλληλεπίδραση με τη βάση δεδομένων.

Σβήνω την λίστα ` private List<Product> pList` και προσθέτω `private MarketRepository productRepository` ο κώδικας γίνεται:

```
@Service
public class MarketService {
    @Autowired
    private MarketRepository productRepository;

    public List<Product> getAllProducts() throws Exception {
        return productRepository.findAll();  // // ΑΛΛΑΓΗ ΕΔΩ με .findAll()
    }

    public void addProduct(Product pr) throws Exception {
        productRepository.save(pr);  // ΑΛΛΑΓΗ ΕΔΩ με .save(pr)
    }

    public Product findProduct(String name) throws Exception {
        return productRepository.findByName(name); // // ΑΛΛΑΓΗ ΕΔΩ με .findByName()
    }
}
```

### Κλάση `MarketController`

Η κλάση `MarketController` παρέχει endpoints για τη διαχείριση προϊόντων:

- `GET /allproducts`: Επιστρέφει όλα τα προϊόντα.   
- `POST /addproduct`: Προσθέτει ένα νέο προϊόν.   
- `GET /findproduct`: Αναζητά προϊόν βάσει ονόματος.   

```
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MarketController {
    @Autowired
    private MarketService ms;

    @GetMapping(path = "/allproducts")
    public List<Product> getAllProducts() throws Exception {
        return ms.getAllProducts();
    }

    @PostMapping(path = "/addproduct")
    public void addProduct(@RequestBody Product pr) throws Exception {
        ms.addProduct(pr);
    }

    @GetMapping(path = "/findproduct")
    public String findProduct(String name) throws Exception {
        Product product = ms.findProduct(name);
        return (product != null) ? "Το προϊόν βρέθηκε." : "Το προϊόν δεν βρέθηκε.";
    }
}
```

<hr>

## 4. Εισαγωγή δεδομένων κατά την εκκίνηση

Η κλάση `MarketServiceConfig` χρησιμοποιείται για την προσθήκη προκαθορισμένων προϊόντων στη βάση δεδομένων κατά την εκκίνηση της εφαρμογής εφόσον στο αρχείο `application.properties` έχουμε `spring.jpa.hibernate.ddl-auto=create`. Ο κώδικας είναι:

```
@Configuration
public class MarketServiceConfig implements CommandLineRunner {
    @Autowired
    private MarketService ms;

    @Override
    public void run(String... args) throws Exception {
        ms.addProduct(new Product(1, "Kaseri", 4));
        ms.addProduct(new Product(2, "Salata", 5));
        ms.addProduct(new Product(3, "Gala", 1));
        System.out.println("DB has been created to MarketService!!!");
    }
}
```

<hr>

## 5. Testing μέσω Swagger UI

Για τη διευκόλυνση του testing των endpoints, χρησιμοποιήθηκε το Swagger UI.

Προσθήκη εξάρτησης (pom.xml):
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.15</version>
</dependency>
```

Ρυθμίσεις Swagger (application.properties):
```
springdoc.api-docs.path=/api
springdoc.swagger-ui.path=/api-ui
```

URL Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

<hr>

## 6. Τελικό Διάγραμμα

```
+---------------------+
| MarketApplication   |
| (Entry Point)       |
+---------------------+
           |
           v
+-----------------------+
| MarketController      |
| (REST API)            |
|  - /allproducts (GET) |
|  - /addproduct (POST) |
|  - /findproduct (GET) |
+-----------------------+
           |
           v
+---------------------+
| MarketService       |
| (Business Logic)    |
+---------------------+
           |
           v
+---------------------+
| MarketRepository    |
| (Data Access)       |
+---------------------+
           |
           v
+---------------------+
| Product             |
| (Data Model)        |
+---------------------+
```

<hr>

## 7. Χρησιμοποιούμενα Annotations

- `@RestController`: Δημιουργεί έναν RESTful controller που διαχειρίζεται HTTP αιτήματα και επιστρέφει JSON ή XML.   
- `@CrossOrigin`: Επιτρέπει την ανταλλαγή δεδομένων με το frontend από διαφορετικά origins (CORS policy).   
- `@GetMapping`: Χειρίζεται αιτήματα HTTP GET για συγκεκριμένο endpoint.   
- `@PostMapping`: Χειρίζεται αιτήματα HTTP POST για συγκεκριμένο endpoint.   
- `@RequestBody`: Χρησιμοποιείται για την αυτόματη αντιστοίχιση του σώματος ενός HTTP αιτήματος σε ένα αντικείμενο Java.   
- `@Autowired`: Κάνει inject αντικείμενα (dependency injection) στα components.   
- `@Entity`: Σηματοδοτεί ότι η κλάση είναι μια οντότητα που αντιστοιχεί σε έναν πίνακα στη βάση δεδομένων.    
- `@Id`: Δηλώνει το πρωτεύον κλειδί μιας οντότητας.  
- `@Service`: Δηλώνει ότι η κλάση περιέχει επιχειρησιακή λογική και ανήκει στο επίπεδο του service layer.   
- `@Configuration`: Δηλώνει ότι η κλάση περιέχει bean definitions και ρυθμίσεις για την εφαρμογή.   
- `@CommandLineRunner`: Δηλώνει μια μέθοδο που θα εκτελείται αυτόματα κατά την εκκίνηση της εφαρμογής.   

<hr>

## 8. Συμπεράσματα

- Η χρήση του JpaRepository απλοποίησε τη διαχείριση δεδομένων.   
- Το Swagger UI διευκόλυνε την τεκμηρίωση και τον έλεγχο των API.   
- Η προσθήκη προκαθορισμένων δεδομένων κατά την εκκίνηση διευκολύνει τον έλεγχο της εφαρμογής.
