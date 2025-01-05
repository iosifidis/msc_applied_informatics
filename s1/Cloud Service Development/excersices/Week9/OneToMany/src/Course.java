package com.example.demo.hello;

import java.util.*;
import javax.persistence.*;

// Η κλάση Course αντιπροσωπεύει ένα μάθημα και είναι μια οντότητα που αποθηκεύεται στη βάση δεδομένων
@Entity
public class Course {

    // Το πεδίο name είναι το πρωτεύον κλειδί για το μάθημα (το όνομα του μαθήματος)
    @Id
    private String name;

    // Πεδίο για το εξάμηνο στο οποίο προσφέρεται το μάθημα
    private int semester;

    // Η σχέση Many-to-Many μεταξύ μαθημάτων και φοιτητών
    @ManyToMany(mappedBy = "courses") // Το mappedBy δηλώνει ότι αυτή η σχέση είναι η αντίστροφη της ήδη υλοποιημένης στο Student
    private Set<Student> students = new HashSet<Student>(); // Χρησιμοποιούμε ένα Set για την αποθήκευση των φοιτητών που παρακολουθούν το μάθημα

    // Η σχέση Many-to-One μεταξύ μαθήματος και καθηγητή (κάθε μάθημα έχει έναν καθηγητή)
    @ManyToOne
    @JoinColumn(name = "professor_name") // Η στήλη που αποθηκεύει τον καθηγητή στο μάθημα
    private Professor lecturer;

    // Προεπιλεγμένος κατασκευαστής (για τη χρήση του JPA)
    public Course() {}

    // Κατασκευαστής με παραμέτρους για την αρχικοποίηση του ονόματος και του εξαμήνου του μαθήματος
    public Course(String n, int s) {
        name = n;
        semester = s;
    }

    // Μέθοδος για να θέσουμε τον καθηγητή που διδάσκει το μάθημα
    public void setProfessor(Professor p) {
        lecturer = p;
    }

    // Getters για τα πεδία της κλάσης Course
    public String getName() { return name; }
    public int getSemester() { return semester; }
    public Professor getProfessor() { return lecturer; }

    // Μέθοδος για να προσθέσουμε έναν φοιτητή στο μάθημα
    public void addStudent(Student s) {
        students.add(s);   // Προσθέτουμε τον φοιτητή στη συλλογή των μαθημάτων
    }
}
