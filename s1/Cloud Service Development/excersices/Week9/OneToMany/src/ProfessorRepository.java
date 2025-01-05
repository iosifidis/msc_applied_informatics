package com.example.demo.hello;

import org.springframework.data.jpa.repository.JpaRepository;

//Το interface ProfessorRepository επεκτείνει το JpaRepository για να παρέχει βασικές λειτουργίες για τη διαχείριση της οντότητας Professor
//Η κλάση Professor θα συνδεθεί με τη βάση δεδομένων μέσω αυτού του repository
//Ο τύπος της οντότητας είναι Professor και το τύπο του πρωτεύοντος κλειδιού είναι String (το πεδίο "name")
public interface ProfessorRepository extends JpaRepository<Professor, String> {
	// Δεν χρειάζεται να προσθέσουμε υλοποιήσεις εδώ. Οι βασικές λειτουργίες (όπως save, findById, delete, κλπ.)
	// παρέχονται αυτόματα από το JpaRepository.
}