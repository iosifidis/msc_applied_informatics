# Οδηγίες Υλοποίησης της Άσκησης: Market Service

## Περιγραφή της Άσκησης
Η άσκηση αφορά τη δημιουργία ενός συστήματος για τη διαχείριση προϊόντων, πωλήσεων και πωλητών, με τη χρήση του Spring Boot. Περιλαμβάνει:

1. Δημιουργία οντοτήτων `Product`, `Sale`, και `Seller`.
2. Συσχετίσεις μεταξύ αυτών των οντοτήτων.
3. Δημιουργία repository για την αποθήκευση δεδομένων.
4. Υλοποίηση υπηρεσιών (services) για επιχειρηματική λογική.
5. Δημιουργία controller για την έκθεση REST API.
6. Ρύθμιση αρχικών δεδομένων στην εκκίνηση της εφαρμογής.

---

## Βήματα Εγκατάστασης

1. **Δημιουργία Project**:
   - Δημιουργήστε ένα νέο Spring Boot project μέσω [Spring Initializr](https://start.spring.io/).
   - Προσθέστε εξαρτήσεις για:
     - `Spring Web`

2. **Ρύθμιση Βάσης Δεδομένων**:
   - Ενημερώστε το αρχείο `application.properties`:
```properties
springdoc.api-docs.path=/api
springdoc.swagger.ui.path=/api-ui 
     
spring.datasource.url=jdbc:mysql://localhost/market-week9
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=org.gjt.mm.mysql.Driver
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```
 
3. Ενημέρωση του `pom.xml`

Προσθέστε τις παρακάτω εξαρτήσεις στο αρχείο `pom.xml` για να διασφαλίσετε ότι όλες οι απαιτούμενες βιβλιοθήκες είναι διαθέσιμες:

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

URL Swagger:

[http://localhost:8080/swagger-ui/index.html]](http://localhost:8080/swagger-ui/index.html)

Στο Swagger, εμφανίζονται τα διαθέσιμα endpoints με περιγραφές και δυνατότητα εκτέλεσης HTTP αιτημάτων.

---

## Αναλυτικά Βήματα

### 1. Δημιουργία Οντοτήτων (Entities)

#### 1.1 Δημιουργία της Κλάσης `Product`
- Αντιπροσωπεύει ένα προϊόν με πεδία `code`, `name`, και `price`.
- Περιλαμβάνει:
  - Συσχέτιση `@ManyToMany` με `Sale` για να συνδέει προϊόντα με πωλήσεις.
  - Μεθόδους getter/setter και έναν κατασκευαστή για αρχικοποίηση των πεδίων.

#### 1.2 Δημιουργία της Κλάσης `Sale`
- Αντιπροσωπεύει μια πώληση με πεδία `code` και `quantity`.
- Περιλαμβάνει:
  - Συσχέτιση `@ManyToMany` με `Product` για να συνδέει πωλήσεις με προϊόντα.
  - Συσχέτιση `@ManyToOne` με `Seller` για να συσχετίζει πωλήσεις με πωλητές.

#### 1.3 Δημιουργία της Κλάσης `Seller`
- Αντιπροσωπεύει έναν πωλητή με πεδίο `name`.
- Περιλαμβάνει:
  - Συσχέτιση `@OneToMany` με `Sale` για να συνδέει πωλητές με τις πωλήσεις τους.

### 2. Δημιουργία Repository
- Υλοποιήστε interfaces που επεκτείνουν το `JpaRepository`:
  - `ProductRepository` για την οντότητα `Product`.
  - `SellerRepository` για την οντότητα `Seller`.
- Παρέχετε πρόσθετες μεθόδους, όπως `findByName` για αναζήτηση προϊόντων.

### 3. Δημιουργία Υπηρεσίας (Service)

#### 3.1 Δημιουργία της Κλάσης `MarketService`
- Περιέχει επιχειρηματική λογική για:
  - Προσθήκη προϊόντων και πωλητών.
  - Εύρεση προϊόντων με βάση το όνομά τους.
  - Ανάκτηση όλων των προϊόντων από το repository.

### 4. Δημιουργία Controller

#### 4.1 Δημιουργία της Κλάσης `MarketController`
- Εκθέτει endpoints για:
  - Ανάκτηση όλων των προϊόντων μέσω `GET /allproducts`.
  - Προσθήκη προϊόντος μέσω `POST /addproduct`.
  - Εύρεση προϊόντος μέσω `GET /findproduct`.

### 5. Ρύθμιση Αρχικών Δεδομένων

#### 5.1 Δημιουργία της Κλάσης `MarketServiceConfig`
- Υλοποιεί το `CommandLineRunner` για την εισαγωγή αρχικών δεδομένων στην εκκίνηση της εφαρμογής.

---

### 6. Διάγραμμα κλάσεων

```plaintext
+---------------------------------------+
|   MarketController                    |
+---------------------------------------+
| - marketService: MarketService        |
|---------------------------------------|
| + getAllProducts(): List<Product>     |
| + getProductDetails(id: int): Product |
| + addProduct(product: Product): void  |
| + addSeller(seller: Seller): void     |
+---------------------------------------+
                ▲
                |
                | Uses
                |
+----------------------------------------+
|   MarketService                        |
+----------------------------------------+
| - productRepository: ProductRepository |
| - sellerRepository: SellerRepository   |
|----------------------------------------|
| + getAllProducts(): List<Product>      |
| + findProduct(name: String): Product   |
| + addProduct(product: Product): void   |
| + addSeller(seller: Seller): void      |
+----------------------------------------+
            ▲
            |
            | Aggregates
            |
+-------------------------------------+           +-------------------------------+
| ProductRepository                   |           | SellerRepository              |
|-------------------------------------|           |-------------------------------|
| JpaRepository<Product, Integer>     |           | JpaRepository<Seller, String> |
|-------------------------------------|           |-------------------------------|
| + findByName(name: String): Product |           |                               |
+-------------------------------------+           +-------------------------------+

+------------------------+
|   Product              |
+------------------------+
| - code: Integer        |
| - name: String         |
| - price: Integer       |
|------------------------|
| + getCode(): int       |
| + getName(): String    |
| + getPrice(): int      |
| + addSale(Sale): void  |
| + getSales(): Set<Sale>|
+------------------------+
            ▲
            |
            | @ManyToMany(mappedBy="products")
            ▼
+----------------------------+
|   Sale                     |
+----------------------------+
| - code: int                |
| - quantity: int            |
|----------------------------|
| + getCode(): int           |
| + getQuantity(): int       |
| + getSeller(): Seller      |
| + setSeller(Seller): void  |
| + addProduct(Product): void|
+----------------------------+
            ▲
            |
            | @ManyToOne
            ▼
+-------------------------+
|   Seller                |
+-------------------------+
| - name: String          |
|-------------------------|
| + getName(): String     |
| + addSale(Sale): void   |
| + getSales(): List<Sale>|
+-------------------------+

+----------------------------+
|   MarketServiceConfig      |
+----------------------------+
| - ms: MarketService        |
|----------------------------|
| + run(String... args): void|
+----------------------------+
                ▲
                |
                | Uses
                |
+------------------+
|   MarketService  |
+------------------+
```

#### Επεξήγηση του Διαγράμματος

1. Κλάση `Product`
- Αντιπροσωπεύει ένα προϊόν.  
- Συσχετίζεται με την κλάση `Sale` μέσω μιας σχέσης `@ManyToMany`.   
- Περιλαμβάνει πεδία όπως `code`, `name`, και `price`.

2. Κλάση `Sale`
- Αντιπροσωπεύει μια πώληση.   
- Συνδέεται με πολλαπλά προϊόντα μέσω της σχέσης `@ManyToMany`.   
- Συνδέεται με έναν πωλητή μέσω της σχέσης `@ManyToOne`.   

3. Κλάση `Seller`
- Αντιπροσωπεύει έναν πωλητή.   
- Συνδέεται με πολλαπλές πωλήσεις μέσω της σχέσης @OneToMany.

4. Κλάση `MarketController`
- Το MarketController είναι η βασική κλάση που επικοινωνεί με τον πελάτη μέσω endpoints.
- Περιλαμβάνει λειτουργίες όπως `getAllProducts`, `getProductDetails`, `addProduct`, και `addSeller`.

5. Κλάση `MarketService`
- Παρέχει λειτουργικότητα για τη διαχείριση προϊόντων και πωλητών.   
- Χρησιμοποιεί τα repositories `ProductRepository` και `SellerRepository`.

6. Κλάσεις `Repositories`
- Παρέχουν βασικές λειτουργίες CRUD μέσω του `JpaRepository`.

7. Κλάση `MarketServiceConfig`
- Αρχικοποιεί δεδομένα κατά την εκκίνηση της εφαρμογής μέσω της μεθόδου `run`.

#### Χρήσιμες Σχέσεις
- `@ManyToMany`: Τα προϊόντα και οι πωλήσεις σχετίζονται πολλαπλά.   
- `@OneToMany`: Οι πωλητές σχετίζονται με πολλαπλές πωλήσεις.   
- `@ManyToOne`: Κάθε πώληση συνδέεται με έναν μόνο πωλητή.   
- Χρήση (Uses): Υποδεικνύει ότι μια κλάση χρησιμοποιεί μια άλλη (π.χ., το MarketController χρησιμοποιεί το MarketService).   
- Ανασύνθεση (Aggregates): Δείχνει ότι μια κλάση περιέχει άλλη ως εξάρτηση (π.χ., το MarketService περιέχει τα repositories).

