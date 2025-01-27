### Walkthrough για την επίλυση παρόμοιων ασκήσεων με Spring Boot και JPA

Αυτό το walkthrough εξηγεί πώς να λύσετε παρόμοιες ασκήσεις χρησιμοποιώντας Spring Boot και JPA (Java Persistence API). Θα καλύψουμε τις βασικές έννοιες, τις σχέσεις μεταξύ των οντοτήτων και πώς να χρησιμοποιήσετε αυτές τις σχέσεις για να δημιουργήσετε μια εφαρμογή Spring Boot.

---

### 1. **Δομή της Εφαρμογής**
Μια τυπική εφαρμογή Spring Boot αποτελείται από τα ακόλουθα στοιχεία:
- **Κύρια κλάση εφαρμογής**: Εκκινητής της εφαρμογής.
- **Controllers**: Διαχειρίζονται τα HTTP αιτήματα και επιστρέφουν αποτελέσματα.
- **Services**: Περιέχουν τη λογική της εφαρμογής.
- **Repositories**: Διαχειρίζονται την πρόσβαση στη βάση δεδομένων.
- **Entities**: Αντιπροσωπεύουν τα δεδομένα της εφαρμογής και τις σχέσεις μεταξύ τους.

---

### 2. **Οντότητες (Entities) και Σχέσεις**
Οι οντότητες αντιπροσωπεύουν πίνακες στη βάση δεδομένων. Οι σχέσεις μεταξύ των οντοτήτων ορίζονται με βάση τις ανάγκες της εφαρμογής.

#### Βασικές Σχέσεις:
1. **One-to-Many (1:N)**:
   - Μια οντότητα σχετίζεται με πολλές εγγραφές μιας άλλης οντότητας.
   - Παράδειγμα: Ένας πωλητής (`Seller`) μπορεί να έχει πολλές πωλήσεις (`Sale`).
   - Υλοποίηση:
     ```java
     @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private List<Sale> sales = new ArrayList<>();
     ```

2. **Many-to-One (N:1)**:
   - Πολλές εγγραφές μιας οντότητας σχετίζονται με μια εγγραφή μιας άλλης οντότητας.
   - Παράδειγμα: Πολλές πωλήσεις (`Sale`) μπορεί να ανήκουν σε έναν πωλητή (`Seller`).
   - Υλοποίηση:
     ```java
     @ManyToOne
     @JoinColumn(name = "seller_name")
     private Seller seller;
     ```

3. **Many-to-Many (N:M)**:
   - Πολλές εγγραφές μιας οντότητας σχετίζονται με πολλές εγγραφές μιας άλλης οντότητας.
   - Παράδειγμα: Ένα προϊόν (`Product`) μπορεί να σχετίζεται με πολλές πωλήσεις (`Sale`) και μια πώληση μπορεί να σχετίζεται με πολλά προϊόντα.
   - Υλοποίηση:
     ```java
     @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
     @JoinTable(
         name = "product_sale",
         joinColumns = @JoinColumn(name = "product_name"),
         inverseJoinColumns = @JoinColumn(name = "sale_name")
     )
     private Set<Sale> sales = new HashSet<>();
     ```

---

### 3. **Repositories**
Τα repositories χρησιμοποιούνται για την πρόσβαση στη βάση δεδομένων. Το Spring Data JPA παρέχει έτοιμες μεθόδους για CRUD (Create, Read, Update, Delete) λειτουργίες.

- **Παράδειγμα**:
  ```java
  public interface ProductRepository extends JpaRepository<Product, Integer> {
      Product findByName(String pr);
  }
  ```
  - Η μέθοδος `findByName` δημιουργείται αυτόματα από το Spring Data JPA με βάση τη σύμβαση ονοματοδοσίας.

---

### 4. **Services**
Τα services περιέχουν τη λογική της εφαρμογής. Χρησιμοποιούν τα repositories για να αλληλεπιδρούν με τη βάση δεδομένων.

- **Παράδειγμα**:
  ```java
  @Service
  public class MarketService {
      @Autowired
      private ProductRepository productRepository;

      public List<Product> getAllProducts() throws Exception {
          return productRepository.findAll();
      }

      public void addProduct(Product pr) throws Exception {
          Optional<Product> byId = productRepository.findById(pr.getCode());
          if (!byId.isPresent())
              productRepository.save(pr);
      }
  }
  ```

---

### 5. **Controllers**
Οι controllers διαχειρίζονται τα HTTP αιτήματα και επιστρέφουν αποτελέσματα. Χρησιμοποιούν τα services για να εκτελέσουν τη λογική της εφαρμογής.

- **Παράδειγμα**:
  ```java
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
  }
  ```

---

### 6. **Αρχικοποίηση Δεδομένων**
Μπορείτε να χρησιμοποιήσετε την κλάση `CommandLineRunner` για να αρχικοποιήσετε δεδομένα κατά την εκκίνηση της εφαρμογής.

- **Παράδειγμα**:
  ```java
  @Configuration
  public class MarketServiceConfig implements CommandLineRunner {
      @Autowired
      private MarketService ms;

      @Override
      public void run(String... args) throws Exception {
          Seller s1 = new Seller("Apostolis");
          ms.addSeller(s1);

          Product p1 = new Product(1, "Gala", 1);
          ms.addProduct(p1);
      }
  }
  ```

---

### 7. **Βήματα για την Επίλυση Παρόμοιων Ασκήσεων**
1. **Ορισμός Οντοτήτων**:
   - Δημιουργήστε τις κλάσεις που αντιπροσωπεύουν τις οντότητες (π.χ., `Product`, `Seller`, `Sale`).
   - Ορίστε τις σχέσεις μεταξύ τους (One-to-Many, Many-to-One, Many-to-Many).

2. **Δημιουργία Repositories**:
   - Δημιουργήστε interfaces που επεκτείνουν το `JpaRepository` για κάθε οντότητα.

3. **Υλοποίηση Services**:
   - Γράψτε τη λογική της εφαρμογής στα services, χρησιμοποιώντας τα repositories.

4. **Δημιουργία Controllers**:
   - Δημιουργήστε endpoints για να διαχειρίζεστε τα HTTP αιτήματα.

5. **Αρχικοποίηση Δεδομένων**:
   - Χρησιμοποιήστε την `CommandLineRunner` για να προσθέσετε αρχικά δεδομένα στη βάση.

6. **Δοκιμή και Εκτέλεση**:
   - Εκτελέστε την εφαρμογή και δοκιμάστε τα endpoints χρησιμοποιώντας εργαλεία όπως το Postman.

---

### 8. **Παραδείγματα Σχέσεων**
- **One-to-Many**: Ένας πωλητής (`Seller`) έχει πολλές πωλήσεις (`Sale`).
- **Many-to-One**: Πολλές πωλήσεις (`Sale`) ανήκουν σε έναν πωλητή (`Seller`).
- **Many-to-Many**: Ένα προϊόν (`Product`) μπορεί να σχετίζεται με πολλές πωλήσεις (`Sale`) και μια πώληση μπορεί να σχετίζεται με πολλά προϊόντα.

---

### 9. **Συμβουλές**
- Χρησιμοποιήστε το `@Autowired` για την αυτόματη έγχυση εξαρτήσεων (dependency injection).
- Χρησιμοποιήστε το `@CrossOrigin` για να επιτρέψετε αιτήσεις από διαφορετικές πηγές (CORS).
- Χρησιμοποιήστε το `@RequestBody` για να λαμβάνετε δεδομένα από το σώμα του αιτήματος (request body).

---

Αυτό το walkthrough σας βοηθά να κατανοήσετε τις βασικές έννοιες και να λύσετε παρόμοιες ασκήσεις χρησιμοποιώντας Spring Boot και JPA.
