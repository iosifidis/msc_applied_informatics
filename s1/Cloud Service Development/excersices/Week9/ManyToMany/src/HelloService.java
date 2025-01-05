package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class HelloService {

	// Αναφορά στο repository για τη διαχείριση των αντικειμένων Student
	@Autowired
	private StudentRepository studentRepository;

	/**
	 * Προσθέτει έναν νέο φοιτητή στο σύστημα αν δεν υπάρχει ήδη.
	 * 
	 * @param s Το αντικείμενο Student που θα προστεθεί.
	 * @throws Exception Εξαίρεση αν προκύψει κάποιο πρόβλημα κατά την εκτέλεση.
	 */
	public void addStudent(Student s) throws Exception {
		// Έλεγχος αν ο φοιτητής υπάρχει ήδη στη βάση δεδομένων με βάση το όνομα
		Optional<Student> byId = studentRepository.findById(s.getName());
		if (!byId.isPresent()) // Αν δεν υπάρχει, αποθηκεύουμε τον νέο φοιτητή
			studentRepository.save(s);
	}

	/**
	 * Επιστρέφει μια λίστα με όλους τους φοιτητές που υπάρχουν στη βάση δεδομένων.
	 * 
	 * @return Λίστα αντικειμένων Student.
	 * @throws Exception Εξαίρεση αν προκύψει κάποιο πρόβλημα κατά την εκτέλεση.
	 */
	public List<Student> getAllStudents() throws Exception {
		// Επιστροφή όλων των φοιτητών από το repository
		return studentRepository.findAll();
	}
}
