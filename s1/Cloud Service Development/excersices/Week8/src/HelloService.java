package com.example.demo.hello;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class HelloService {

	// Inject το repository για αλληλεπίδραση με τη βάση δεδομένων.
	@Autowired
    private StudentRepository studentRepository; // ΕΚΑΝΑ ΑΥΤΗ ΤΗΝ ΑΛΛΑΓΗ

    // Δημιουργεί ένα νέο αντικείμενο Student με τα δεδομένα που δίνονται.
    public Student getStudent(String n, int a, String l) {
        return new Student(n, a, l);
    }

    // Προσθέτει έναν νέο φοιτητή στη βάση δεδομένων.
    // Χρησιμοποιείται το StudentRepository για να αποθηκευτεί το αντικείμενο.
    public void addStudent(Student s) throws Exception {
        studentRepository.save(s); // Αποθηκεύει τον φοιτητή στη βάση δεδομένων. ΑΛΛΑΞΑ ΚΑΙ ΑΥΤΟ
    }

    // Επιστρέφει τη λίστα όλων των φοιτητών από τη βάση δεδομένων.
    // Χρησιμοποιείται το StudentRepository για ανάκτηση δεδομένων.
    public List<Student> getAllStudents() throws Exception {
        return studentRepository.findAll(); // Επιστρέφει όλα τα εγγεγραμμένα αντικείμενα Student. ΑΛΛΑΞΑ ΚΑΙ ΑΥΤΟ
    }

    // Οι μέθοδοι loadFromDB και saveToDB έχουν αφαιρεθεί γιατί η λειτουργικότητα τους
    // καλύπτεται πλέον από το JPA Repository που διαχειρίζεται τη βάση δεδομένων.
}
	
