# Εισαγωγή στη Διαχείριση Βάσεων Δεδομένων με JPA

Η ενότητα αυτή εστιάζει στις βασικές αρχές και τεχνικές της διαχείρισης βάσεων δεδομένων με το Java Persistence API (JPA). Ακολουθεί μια λεπτομερής περιγραφή των θεμάτων που θα διδαχθούν στους φοιτητές.

---

## Θεωρία

### 1. Εισαγωγή στο JPA
- **Τι είναι το JPA:**
  - Τεχνολογία της Java για τη χαρτογράφηση αντικειμένων σε βάσεις δεδομένων (Object-Relational Mapping - ORM).
  - Παρέχει έναν ενιαίο τρόπο επικοινωνίας μεταξύ της εφαρμογής και της βάσης δεδομένων.
- **Πλεονεκτήματα του JPA:**
  - Αυτοματοποιεί τη διαδικασία χαρτογράφησης.
  - Απλοποιεί την ανάπτυξη και συντήρηση της εφαρμογής.
  - Υποστηρίζει πολλαπλές βάσεις δεδομένων.

### 2. Βασικές Αναπαραστάσεις
#### 2.1 Οντότητες (@Entity)
- Ορισμός μιας οντότητας.
- Σημασία του αναγνωριστικού (primary key) με χρήση του @Id annotation.

#### 2.2 Αναφορές και Σχέσεις
- **One-to-Many**:
  - Χρήση @OneToMany και @ManyToOne για τη σύνδεση π.χ. καθηγητών και μαθημάτων.
  - Παραδείγματα κώδικα.
- **Many-to-Many**:
  - Χρήση @ManyToMany για τη σύνδεση π.χ. μαθημάτων και φοιτητών.
  - Δημιουργία ενδιάμεσου πίνακα με @JoinTable.
- **Cascade και Fetch Types:**
  - Διαφορετικοί τύποι κατηγοριών (“PERSIST”, “MERGE”).
  - Επιλογή τύπου φόρτωσης (LAZY, EAGER).

#### 2.3 Χρήση Αναnotations
- Χρήση annotations όπως @Entity, @Table, @Column για τη χαρτογράφηση οντοτήτων σε πίνακες βάσης δεδομένων.

### 3. Παραμετροποίηση και Σύνδεση Βάσης Δεδομένων
- **application.properties:**
  - Ρυθμίσεις για τη σύνδεση στη βάση δεδομένων (URL, username, password).
  - Ενεργοποίηση της αυτόματης δημιουργίας σχημάτων με `spring.jpa.hibernate.ddl-auto=create`.
- **Παραδείγματα Σύνδεσης:**
  - Ρυθμίσεις για MySQL ή PostgreSQL.

### 4. Δημιουργία CRUD Εφαρμογής με JPA
- **Βασικές CRUD Λειτουργίες:**
  - Δημιουργία, ανάγνωση, ενημέρωση και διαγραφή δεδομένων με τα αντίστοιχα repository interfaces (π.χ. JpaRepository).
- **Παραδείγματα κώδικα:**
  - Εισαγωγή δεδομένων σε πίνακες.
  - Ανάκτηση δεδομένων με queries.

---

## Εργαστηριακό Μέρος

### Ενδεικτική Εφαρμογή – Διαχείριση Πανεπιστημίου
#### 1. Σενάριο
- Καταχώρηση φοιτητών, μαθημάτων και καθηγητών.
- Συσχέτιση φοιτητών με μαθήματα μέσω Many-to-Many σχέσης.
- Συσχέτιση μαθημάτων με καθηγητές μέσω One-to-Many σχέσης.

#### 2. Κώδικας Παραδείγματος
- **Οντότητα Φοιτητή:**
```java
@Entity
public class Student {
    @Id
    private String name;
    private int age;
    private String location;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_name"),
        inverseJoinColumns = @JoinColumn(name = "course_name")
    )
    private Set<Course> courses = new HashSet<>();

    public void addCourse(Course c) {
        courses.add(c);
    }
}
```

- **Οντότητα Μαθήματος:**
```java
@Entity
public class Course {
    @Id
    private String name;
    private int semester;

    @ManyToOne
    @JoinColumn(name = "professor_name")
    private Professor lecturer;

    public void setProfessor(Professor p) {
        lecturer = p;
    }
}
```

#### 3. Εκτέλεση Εφαρμογής
- Δημιουργία οντοτήτων και συσχέτιση τους.
- Επεξήγηση των αποτελεσμάτων.

---

## Συμπεράσματα
Κατανόηση:   
1. Πώς να σχεδιάζουν αντικειμενοστραφείς σχέσεις σε επίπεδο βάσης δεδομένων.
2. Τη χρήση των βασικών annotations του JPA.
3. Τη δημιουργία εφαρμογών με πολλές οντότητες και συσχετίσεις.
4. Τη σύνδεση και παραμετροποίηση του JPA σε πραγματικές εφαρμογές.

---

## ΠΑΡΑΔΕΙΓΜΑΤΑ:   

- [ManyToMany](ManyToMany)   
- [OneToMany](OneToMany)
