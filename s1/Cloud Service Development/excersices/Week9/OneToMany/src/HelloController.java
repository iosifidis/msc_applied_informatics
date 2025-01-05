package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

//Αντιμετωπίζουμε τις CORS ρυθμίσεις για να επιτρέψουμε αιτήματα από οποιονδήποτε προέλευση
@CrossOrigin(origins = "*", allowedHeaders = "*")
//Η κλάση αυτή είναι ο Controller, ο οποίος αναλαμβάνει την επεξεργασία των HTTP αιτημάτων
@RestController
public class HelloController {

	 // Χρησιμοποιούμε την αναγνώριση του Spring για να εισάγουμε την υπηρεσία (service) για τους φοιτητές
	 @Autowired
	 private HelloService hs;
	 
	 // Εδώ δημιουργούμε την εντολή GET για να επιστρέψουμε όλους τους φοιτητές από την υπηρεσία
	 @GetMapping(path="/students")
	 public List<Student> getAllStudent() throws Exception {
	     // Καλούμε την υπηρεσία για να πάρουμε τη λίστα των φοιτητών
	     return hs.getAllStudents();
	 } 
	 
	 // Εδώ δημιουργούμε την εντολή POST για να προσθέσουμε έναν νέο φοιτητή στην υπηρεσία
	 @PostMapping(path="/addStudent")
	 public void addStudent(@RequestBody Student st) throws Exception {
	     // Καλούμε την υπηρεσία για να προσθέσουμε τον φοιτητή
	     hs.addStudent(st);
	 }
}

