package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * Η κλάση HelloController είναι ένας REST controller που διαχειρίζεται τα API endpoints
 * για την οντότητα Student. Συνδέει τις αιτήσεις HTTP με τις μεθόδους του HelloService.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*") // Επιτρέπει την πρόσβαση CORS από οποιοδήποτε domain και header
@RestController // Δηλώνει ότι αυτή η κλάση είναι REST controller και μπορεί να εξυπηρετεί αιτήσεις HTTP
public class HelloController {

	@Autowired // Αυτόματη σύνδεση του HelloService για τη διαχείριση λογικής των δεδομένων
	private HelloService hs;

	/**
	 * Endpoint για την επιστροφή όλων των φοιτητών.
	 * 
	 * @return Λίστα με όλους τους φοιτητές.
	 * @throws Exception Εξαίρεση σε περίπτωση αποτυχίας κατά την εκτέλεση.
	 */
	@GetMapping(path = "/students") // Χαρτογραφεί τα αιτήματα GET στο /students
	public List<Student> getAllStudent() throws Exception {
		return hs.getAllStudents();
	}

	/**
	 * Endpoint για την προσθήκη ενός νέου φοιτητή.
	 * 
	 * @param st Το αντικείμενο Student που θα προστεθεί, λαμβάνεται από το σώμα του αιτήματος.
	 * @throws Exception Εξαίρεση σε περίπτωση αποτυχίας κατά την εκτέλεση.
	 */
	@PostMapping(path = "/addStudent") // Χαρτογραφεί τα αιτήματα POST στο /addStudent
	public void addStudent(@RequestBody Student st) throws Exception {
		hs.addStudent(st);
	}
}
