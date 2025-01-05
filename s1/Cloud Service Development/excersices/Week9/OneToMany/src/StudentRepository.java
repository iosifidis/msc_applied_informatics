package com.example.demo.hello;

import org.springframework.data.jpa.repository.JpaRepository;

//Το interface StudentRepository επεκτείνει το JpaRepository για να παρέχει βασικές λειτουργίες για τη διαχείριση της οντότητας Student
//Η κλάση Student θα συνδεθεί με τη βάση δεδομένων μέσω αυτού του repository
//Ο τύπος της οντότητας είναι Student και το τύπο του πρωτεύοντος κλειδιού είναι String (το πεδίο "name")
public interface StudentRepository extends JpaRepository<Student, String> {
	// Δεν χρειάζεται να προσθέσουμε υλοποιήσεις εδώ. Οι βασικές λειτουργίες (όπως save, findById, delete, κλπ.)
	// παρέχονται αυτόματα από το JpaRepository.
}