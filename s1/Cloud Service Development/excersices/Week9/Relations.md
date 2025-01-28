# Εξήγηση των σχέσεων

Στη Spring (και γενικότερα στη Java Persistence API - JPA), οι σχέσεις μεταξύ οντοτήτων (entities) ορίζονται με χρήση των σχολίων (annotations) `@ManyToMany`, `@OneToMany`, και `@ManyToOne`. Αυτά τα σχόλια χρησιμοποιούνται για να περιγράψουν πώς οι οντότητες σχετίζονται μεταξύ τους στη βάση δεδομένων.

## 1. `@ManyToMany`
Χρησιμοποιείται όταν μια οντότητα μπορεί να σχετίζεται με πολλές εμφανίσεις μιας άλλης οντότητας και αντίστροφα. Για παράδειγμα, ένας φοιτητής μπορεί να είναι εγγεγραμμένος σε πολλά μαθήματα και ένα μάθημα μπορεί να έχει πολλούς φοιτητές.

Παράδειγμα:
```
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    // Getters and Setters
}

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    // Getters and Setters
}
```


### 1.1 @ManyToMany
```
@ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
```

**Εξήγηση**:
- `@JoinTable`: Χρησιμοποιείται για να ορίσει έναν πίνακα σύνδεσης (join table) στη σχέση `@ManyToMany`. Αυτός ο πίνακας συνδέει τις δύο οντότητες (στην περίπτωσή μας, `Student` και `Course`).   

- `name = "student_course"`: Το όνομα του πίνακα σύνδεσης στη βάση δεδομένων. Εδώ ονομάζεται `student_course`, επειδή συνδέει τους πίνακες `Student` και `Course`.   

- `joinColumns = @JoinColumn(name = "student_id")`: Καθορίζει τη στήλη στον πίνακα σύνδεσης που θα περιέχει το ξένο κλειδί (foreign key) για την πρώτη οντότητα (`Student`). Το όνομα της στήλης είναι `student_id`, επειδή αναφέρεται στο πρωτεύον κλειδί (primary key) του `Student`.

- `inverseJoinColumns = @JoinColumn(name = "course_id")`: Καθορίζει τη στήλη στον πίνακα σύνδεσης που θα περιέχει το ξένο κλειδί για τη δεύτερη οντότητα (`Course`). Το όνομα της στήλης είναι `course_id`, επειδή αναφέρεται στο πρωτεύον κλειδί του `Course`.

**Γιατί τα ονόματα**:
- Το όνομα του πίνακα (`student_course`) είναι περιγραφικό και δείχνει ότι συνδέει τους πίνακες `Student` και `Course`.   

- Τα ονόματα των στηλών (`student_id` και `course_id`) είναι συμβατικά και ακολουθούν την πρακτική της χρήσης του ονόματος της οντότητας ακολουθούμενου από `_id`.


### 1.2 `@ManyToMany(mappedBy = "courses")`
```
@ManyToMany(mappedBy = "courses")
```

**Εξήγηση**:
- `@ManyToMany`: Δηλώνει μια σχέση πολλά-προς-πολλά μεταξύ δύο οντοτήτων.   

- `mappedBy = "courses"`: Δείχνει ότι η σχέση ελέγχεται από την άλλη πλευρά (στην περίπτωσή μας, από την ιδιότητα `courses` της κλάσης `Student`). Αυτό σημαίνει ότι η κλάση `Course` δεν είναι υπεύθυνη για τη διαχείριση της σχέσης, αλλά απλώς αναφέρεται σε αυτήν.

**Γιατί το όνομα**:
- Το όνομα `courses` χρησιμοποιείται επειδή στην κλάση `Student`, η ιδιότητα που περιέχει τη λίστα των μαθημάτων ονομάζεται `courses`. Αυτό είναι το πεδίο που "ελέγχει" τη σχέση.   

---

## 2. `@OneToMany`
Χρησιμοποιείται όταν μια οντότητα σχετίζεται με πολλές εμφανίσεις μιας άλλης οντότητας. Για παράδειγμα, ένας συγγραφέας μπορεί να έχει γράψει πολλά βιβλία.

Παράδειγμα:
```
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    // Getters and Setters
}

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    // Getters and Setters
}
```

### 2.1 @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
```
@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
```

**Εξήγηση**:
- `@OneToMany`: Δηλώνει μια σχέση ένα-προς-πολλά μεταξύ δύο οντοτήτων (στην περίπτωσή μας, `Author` και `Book`).   

- `mappedBy = "author"`: Δείχνει ότι η σχέση ελέγχεται από την άλλη πλευρά (στην περίπτωσή μας, από την ιδιότητα `author` της κλάσης `Book`). Αυτό σημαίνει ότι η κλάση `Author` δεν είναι υπεύθυνη για τη διαχείριση της σχέσης, αλλά απλώς αναφέρεται σε αυτήν.  

- `cascade = CascadeType.ALL`: Καθορίζει ότι όλες οι λειτουργίες (π.χ. `PERSIST`, `REMOVE`, `MERGE`, κ.λπ.) που εκτελούνται στην οντότητα `Author` θα διαδοθούν και στα σχετιζόμενα `Book`. Για παράδειγμα, αν διαγραφεί ένας `Author`, θα διαγραφούν και όλα τα `Book` που σχετίζονται με αυτόν.   

- `orphanRemoval = true`: Αν ένα `Book` δεν σχετίζεται πλέον με κάποιον `Author` (δηλαδή γίνεται "ορφανό"), θα διαγραφεί αυτόματα από τη βάση δεδομένων.

**Γιατί το όνομα**:
- Το όνομα `author` χρησιμοποιείται επειδή στην κλάση `Book`, η ιδιότητα που αναφέρεται στον συγγραφέα ονομάζεται `author`. Αυτό είναι το πεδίο που "ελέγχει" τη σχέση.

### 2.2 @JoinColumn(name = "author_id")
```
@JoinColumn(name = "author_id")
```

**Εξήγηση**:
- `@JoinColumn`: Χρησιμοποιείται για να καθορίσει τη στήλη στη βάση δεδομένων που θα χρησιμοποιηθεί ως ξένο κλειδί (foreign key).   

- `name = "author_id"`: Το όνομα της στήλης που θα περιέχει το ξένο κλειδί. Εδώ ονομάζεται `author_id`, επειδή αναφέρεται στο πρωτεύον κλειδί του `Author`.

**Γιατί το όνομα**:
- Το όνομα `author_id` είναι περιγραφικό και δείχνει ότι η στήλη περιέχει το ξένο κλειδί που συνδέει τον πίνακα `Book` με τον πίνακα `Author`.

---

## 3. `@ManyToOne`
Χρησιμοποιείται όταν πολλές εμφανίσεις μιας οντότητας σχετίζονται με μια εμφάνιση μιας άλλης οντότητας. Για παράδειγμα, πολλά βιβλία μπορεί να ανήκουν σε έναν συγγραφέα.

Παράδειγμα:
```
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    // Getters and Setters
}

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    // Getters and Setters
}
```

### 3.1 @ManyToOne
```
@ManyToOne
@JoinColumn(name = "author_id")
```

**Εξήγηση**:
- `@ManyToOne`: Δηλώνει μια σχέση πολλά-προς-ένα μεταξύ δύο οντοτήτων (στην περίπτωσή μας, `Book` και `Author`). Πολλά βιβλία μπορούν να ανήκουν σε έναν συγγραφέα.   

- `@JoinColumn(name = "author_id")`: Καθορίζει τη στήλη στη βάση δεδομένων που θα χρησιμοποιηθεί ως ξένο κλειδί. Εδώ ονομάζεται `author_id,` επειδή αναφέρεται στο πρωτεύον κλειδί του `Author`.   

**Γιατί το όνομα**:
- Το όνομα `author_id` είναι περιγραφικό και δείχνει ότι η στήλη περιέχει το ξένο κλειδί που συνδέει τον πίνακα `Book` με τον πίνακα `Author`.

---

## Συνοπτικά:
- Τα ονόματα (`student_course`, `student_id`, `course_id`, `author_id`, κ.λπ.) επιλέγονται για να είναι περιγραφικά και να ακολουθούν τις συμβάσεις της βάσης δεδομένων.   

- Η χρήση των σχολίων (`@JoinTable`, `@JoinColumn`, `mappedBy`, κ.λπ.) εξαρτάται από τη φύση της σχέσης (π.χ. `@ManyToMany`, `@OneToMany`, `@ManyToOne`) και από το ποια πλευρά της σχέσης ελέγχει τη διασύνδεση.

---

## Σύνταξη και Χρήση:

- `@ManyToMany`: Χρησιμοποιείται για πολλά-προς-πολλά σχέσεις. Συνήθως απαιτεί έναν πίνακα σύνδεσης (`@JoinTable`).   

- `@OneToMany`: Χρησιμοποιείται για μία-προς-πολλά σχέσεις. Η σχέση συνήθως αντιστοιχεί σε μια ξένη κλειδί στη σχεσιακή βάση δεδομένων.   

- `@ManyToOne`: Χρησιμοποιείται για πολλά-προς-μία σχέσεις. Η σχέση συνήθως αντιστοιχεί σε μια ξένη κλειδί στη σχεσιακή βάση δεδομένων.   


## Σημαντικές Παραμέτροι:

- `mappedBy`: Χρησιμοποιείται για να δηλώσει ότι η σχέση ελέγχεται από την άλλη πλευρά (π.χ. στην `@OneToMany`).   

- `cascade`: Καθορίζει τις λειτουργίες που πρέπει να διαδοθούν στις σχετικές οντότητες (π.χ. `CascadeType.ALL`).   

- `orphanRemoval`: Αν είναι `true`, οι οντότητες που δεν έχουν πλέον αναφορά θα διαγραφούν αυτόματα.   

Αυτά είναι τα βασικά που χρειάζεσαι για να ορίσεις σχέσεις μεταξύ οντοτήτων στη Spring και JPA.
