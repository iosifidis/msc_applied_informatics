package com.example.demo.hello;

import java.util.*;
import javax.persistence.*;

//Η κλάση Student αντιπροσωπεύει έναν φοιτητή και είναι μια οντότητα που αποθηκεύεται στη βάση δεδομένων
@Entity
public class Student {
 
	 // Το πεδίο name είναι το πρωτεύον κλειδί για τη βάση δεδομένων
	 @Id
	 private String name;
	 
	 // Πεδίο για την ηλικία του φοιτητή
	 private int age;
	 
	 // Πεδίο για την τοποθεσία του φοιτητή
	 private String location;
	 
	 // Η σχέση Many-to-Many μεταξύ φοιτητών και μαθημάτων
	 @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	 @JoinTable(
	     name = "student_courses", // Ονομάζουμε τον πίνακα συσχέτισης
	     joinColumns = @JoinColumn(name = "student_name"), // Ονομάζουμε την στήλη για τον φοιτητή
	     inverseJoinColumns = @JoinColumn(name = "course_name") // Ονομάζουμε την στήλη για το μάθημα
	 )
	 private Set<Course> courses = new HashSet<Course>(); // Χρησιμοποιούμε ένα Set για την αποθήκευση των μαθημάτων του φοιτητή
	
	 // Προεπιλεγμένος κατασκευαστής (για τη χρήση του JPA)
	 public Student() {}
	
	 // Κατασκευαστής με παραμέτρους για την αρχικοποίηση των πεδίων
	 public Student(String n, int a, String l) {
	     name = n;
	     age = a;
	     location = l;
	 }
	
	 // Μέθοδος για να προσθέσουμε ένα μάθημα στον φοιτητή
	 public void addCourse(Course c) {
	     courses.add(c);   // Προσθέτουμε το μάθημα στη συλλογή των μαθημάτων του φοιτητή
	     c.addStudent(this); // Ενημερώνουμε το μάθημα ότι ο φοιτητής το παρακολουθεί
	 }
	 
	 // Getters για τα πεδία της κλάσης
	 public String getName() { return name; }
	 public int getAge() { return age; }
	 public String getLocation() { return location; }
	 public Set<Course> getCourses() { return courses; }
}