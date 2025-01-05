package com.example.demo.hello;

import org.springframework.boot.*;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;

//Η κλάση αυτή παρέχει τη διαμόρφωση για το HelloService και εκτελεί ενέργειες όταν το Spring Boot ξεκινάει
@Configuration
public class HelloServiceConfig implements CommandLineRunner {
 
	 // Εισάγουμε την υπηρεσία HelloService μέσω του μηχανισμού Dependency Injection του Spring
	 @Autowired
	 private HelloService hs;
	 
	 // Η μέθοδος run εκτελείται αυτόματα όταν το Spring Boot ξεκινάει
	 @Override
	 public void run(String... args) throws Exception {
	     
	     // Δημιουργούμε και προσθέτουμε καθηγητές στο σύστημα
	     Professor p1 = new Professor("Apostolis", "Associate Prof");
	     hs.addProfessor(p1);
	     Professor p2 = new Professor("Daniel", "Assistant Prof");
	     hs.addProfessor(p2);
	     Professor p3 = new Professor("Alex", "Prof");
	     hs.addProfessor(p3);
	     
	     // Δημιουργούμε και προσθέτουμε μαθήματα στο σύστημα, ορίζοντας τον καθηγητή για κάθε μάθημα
	     Course c3 = new Course("OOP", 3);
	     c3.setProfessor(p3);
	     Course c4 = new Course("SE", 4);
	     c4.setProfessor(p3);
	     Course c1 = new Course("SQA", 5);
	     c1.setProfessor(p1);
	     Course c2 = new Course("Mobile", 6);
	     c2.setProfessor(p1);
	     Course c5 = new Course("Patterns", 7);
	     c5.setProfessor(p2);
	
	     // Δημιουργούμε φοιτητές και τους προσθέτουμε στα μαθήματα
	     Student s1 = new Student("Nikos",28,"Thessaloniki");
	     s1.addCourse(c2);  // Ο φοιτητής Nikos παρακολουθεί το μάθημα "Mobile"
	     s1.addCourse(c3);  // Ο φοιτητής Nikos παρακολουθεί το μάθημα "OOP"
	     hs.addStudent(s1); // Προσθέτουμε τον φοιτητή στο σύστημα
	     
	     Student s2 = new Student("Elvira",35,"Thessaloniki");
	     s2.addCourse(c1);  // Η Elvira παρακολουθεί το μάθημα "SQA"
	     s2.addCourse(c4);  // Η Elvira παρακολουθεί το μάθημα "SE"
	     hs.addStudent(s2); // Προσθέτουμε την Elvira στο σύστημα
	     
	     Student s3 = new Student("Sofia",36,"Groningen");
	     s3.addCourse(c5);  // Η Sofia παρακολουθεί το μάθημα "Patterns"
	     hs.addStudent(s3); // Προσθέτουμε τη Sofia στο σύστημα
	 }
}