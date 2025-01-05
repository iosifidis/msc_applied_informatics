package com.example.demo.hello;

import java.util.*;
import javax.persistence.*;

// Η κλάση Professor αντιπροσωπεύει έναν καθηγητή και είναι μια οντότητα που αποθηκεύεται στη βάση δεδομένων
@Entity
public class Professor {

    // Το πεδίο name είναι το πρωτεύον κλειδί για τον καθηγητή
    @Id
    private String name;

    // Το πεδίο rank αντιπροσωπεύει τη θέση ή τον τίτλο του καθηγητή (π.χ., Καθηγητής, Αναπληρωτής Καθηγητής κλπ.)
    private String rank;

    // Η σχέση One-to-Many μεταξύ καθηγητών και μαθημάτων
    // Ένας καθηγητής μπορεί να διδάσκει πολλά μαθήματα
    @OneToMany(mappedBy = "lecturer", 
               cascade = CascadeType.ALL, // Όταν διαγράφεται ο καθηγητής, τα μαθήματα που του ανήκουν διαγράφονται επίσης
               fetch = FetchType.LAZY)    // Καθυστέρηση φόρτωσης των μαθημάτων (δεν φορτώνονται μέχρι να ζητηθούν)
    private List<Course> courses = new ArrayList<Course>();

    // Προεπιλεγμένος κατασκευαστής για τη χρήση του JPA
    public Professor() {}

    // Κατασκευαστής για την αρχικοποίηση του ονόματος και της θέσης του καθηγητή
    public Professor(String n, String r) {
        name = n;
        rank = r;
    }

    // Getters για τα πεδία name και rank της κλάσης Professor
    public String getName() { return name; }
    public String getRank() { return rank; }

    // Μέθοδος για να προσθέσουμε ένα μάθημα στον καθηγητή
    public void addCourse(Course c) {
        courses.add(c); // Προσθέτουμε το μάθημα στη λίστα των μαθημάτων του καθηγητή
    }
}
