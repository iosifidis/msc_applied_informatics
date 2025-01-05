package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

//Η κλάση HelloService είναι η υπηρεσία που διαχειρίζεται τη λογική του business για τους φοιτητές και τους καθηγητές
@Service
public class HelloService {

	 // Εισάγουμε το repository για τους φοιτητές, για να αλληλεπιδράσουμε με τη βάση δεδομένων
	 @Autowired
	 private StudentRepository studentRepository;
	
	 // Εισάγουμε το repository για τους καθηγητές, για να αλληλεπιδράσουμε με τη βάση δεδομένων
	 @Autowired
	 private ProfessorRepository profRepository;
	
	 // Μέθοδος για να προσθέσουμε έναν φοιτητή στην βάση δεδομένων
	 public void addStudent(Student s) throws Exception {
	     // Ελέγχουμε αν ο φοιτητής με το ίδιο όνομα υπάρχει ήδη στη βάση
	     Optional<Student> byId = studentRepository.findById(s.getName());
	     if (!byId.isPresent()) // Αν δεν υπάρχει, τον προσθέτουμε
	         studentRepository.save(s);
	 }
	 
	 // Μέθοδος για να προσθέσουμε έναν καθηγητή στην βάση δεδομένων
	 public void addProfessor(Professor p) throws Exception {
	     // Ελέγχουμε αν ο καθηγητής με το ίδιο όνομα υπάρχει ήδη στη βάση
	     Optional<Professor> byId = profRepository.findById(p.getName());
	     if (!byId.isPresent()) // Αν δεν υπάρχει, τον προσθέτουμε
	         profRepository.save(p);
	 }    
	
	 // Μέθοδος για να πάρουμε όλους τους φοιτητές από τη βάση δεδομένων
	 public List<Student> getAllStudents() throws Exception {
	     // Επιστρέφουμε τη λίστα με όλους τους φοιτητές
	     return studentRepository.findAll();
	 }
}