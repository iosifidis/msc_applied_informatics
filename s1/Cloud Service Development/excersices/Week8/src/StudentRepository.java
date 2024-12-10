package com.example.demo.hello;
import org.springframework.data.jpa.repository.JpaRepository;

//Δημιουργεί το repository για τον entity Student, παρέχοντας βασικές λειτουργίες CRUD.
public interface StudentRepository extends JpaRepository<Student, String> {
 // Το JpaRepository παρέχει έτοιμες μεθόδους όπως save, findAll, findById, deleteById κ.λπ.
 // Δεν χρειάζεται να υλοποιηθεί τίποτα επιπλέον εκτός εάν απαιτούνται custom queries.
}