package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

//Αυτή είναι η κλάση Controller όπου ορίζονται τα REST API endpoints.
//Χρησιμοποιούνται annotations για τη διαχείριση HTTP αιτημάτων και αποκρίσεων.
@CrossOrigin(origins = "*", allowedHeaders = "*") // Επιτρέπει αιτήματα από οποιοδήποτε domain (CORS policy).
@RestController // Δηλώνει ότι αυτή η κλάση είναι REST Controller για τη διαχείριση HTTP αιτημάτων.
public class HelloController {

	// Εισάγεται αυτόματα το αντικείμενο HelloService που διαχειρίζεται τη λογική της εφαρμογής.
	@Autowired
	private HelloService hs;


	/**
     * Endpoint GET για την ανάκτηση όλων των φοιτητών από τη βάση δεδομένων.
     * @return Λίστα αντικειμένων Student σε μορφή JSON.
     * @throws Exception Αν προκύψει σφάλμα κατά την ανάκτηση των δεδομένων από την υπηρεσία.
     */
	@GetMapping(path="/students")
	public List<Student> getAllStudent()  throws Exception{
		return hs.getAllStudents();
	} 
	
	/**
     * Endpoint POST για την προσθήκη νέου φοιτητή στη βάση δεδομένων.
     * Αντικαθιστά τη μέθοδο που χρησιμοποιούσε GET για την ίδια λειτουργία.
     * @param st Το αντικείμενο Student που περνάει στο σώμα του αιτήματος POST.
     * @throws Exception Αν προκύψει σφάλμα κατά την προσθήκη του φοιτητή στην υπηρεσία.
     */
	// Θα γίνει post request θα γίνει από GetMapping σε PostMapping και τρόπος που περνάμε τις παραμέτρους -> RequestBody περνάω student.
	@PostMapping(path="/addStudent")  // Διαχειρίζεται αιτήματα POST στο /addStudent.
	public void addStudent(@RequestBody Student st) throws Exception {
		hs.addStudent(st);
	}
}
