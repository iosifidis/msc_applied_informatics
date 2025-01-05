package com.example.demo.hello;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Το StudentRepository είναι ένα interface που επεκτείνει το JpaRepository.
 * Παρέχει βασικές CRUD λειτουργίες (δημιουργία, ανάγνωση, ενημέρωση, διαγραφή) 
 * και αλληλεπίδραση με τη βάση δεδομένων για την οντότητα Student.
 * 
 * Το Spring Data JPA αναλαμβάνει την αυτόματη υλοποίηση των μεθόδων,
 * οπότε δεν χρειάζεται να γράψουμε κώδικα για τις βασικές λειτουργίες.
 */
public interface StudentRepository extends JpaRepository<Student, String> {

	// Δεν χρειάζεται να προσθέσουμε κάποια μέθοδο εδώ,
	// καθώς το JpaRepository περιλαμβάνει ήδη πολλές έτοιμες λειτουργίες,
	// όπως save, findById, findAll, deleteById κ.λπ.
}

