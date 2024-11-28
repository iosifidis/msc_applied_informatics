package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

//Ο controller είναι υπεύθυνος για την διαχείριση των endpoints
//Χρήση του @RestController για να καθοριστεί ως Spring MVC controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {

	// Χρήση του @Autowired για την εξάρτηση της HelloService
	@Autowired
	private HelloService hs;
	
	// Defines that this method is called with GET Request
	// Endpoint για να επιστρέφει ένα μήνυμα "Hello World"
	@GetMapping
	public String helloWorld() {
		return "Hello World";  // Επιστρέφει ένα string ως απόκριση
	}
	
	// With the path, it changes from root to a specific path: http://localhost:8080/hello
	// else it would respond to http://localhost:8080
	// Endpoint που επιστρέφει μια λίστα strings
    // URL: http://localhost:8080/list
	@GetMapping(path="/list")
	public List<String> printList() {
		List<String> sList = new ArrayList<String>();
		sList.add("Hello World");
		sList.add("My name is Apostolis");
		sList.add("My surname is Ampatzoglou");
		return sList;  // Επιστρέφει τη λίστα με τις συμβολοσειρές
	}
	
	// Endpoint για να επιστρέφει χαιρετισμό βασισμένο σε όνομα
    // Παράδειγμα κλήσης: http://localhost:8080/askName/Nikos
	@GetMapping(path="/askName/{name}")
	public String askName(@PathVariable(value="name") String name) {
		return "Hello " + name; // Επιστρέφει ένα προσωποποιημένο μήνυμα
	}

	// Endpoint που επιστρέφει ένα αντικείμενο Student με βάση τα ερωτήματα
    // Παράδειγμα κλήσης: http://localhost:8080/student?name=Gio&age=24&location=Salonica
	@GetMapping(path="/student")
	public Student getStudent(@RequestParam(value="name") String name,
			@RequestParam(value="age") int age,
			@RequestParam(value="location") String location) {
		
		// Dependency Injection, with autowired
		// Καλεί τη μέθοδο της υπηρεσίας HelloService
		return hs.getStudent(name, age, location);
	} 

	// Endpoint που επιστρέφει τη λίστα όλων των φοιτητών από τη βάση
    // URL: http://localhost:8080/students
	@GetMapping(path="/students")
	public List<Student> getAllStudent() throws Exception {
		hs.getStudents(); // Παίρνει τους φοιτητές από τη βάση
        return hs.sList;  // Επιστρέφει τη λίστα φοιτητών
	} 
	
	// Endpoint για προσθήκη φοιτητή στη βάση δεδομένων
    // Παράδειγμα κλήσης: http://localhost:8080/addStudent?name=John&age=22&location=Athens
	@GetMapping(path="/addStudent")
	public void addStudent(@RequestParam(value="name") String name,
			@RequestParam(value="age") int age,
			@RequestParam(value="location") String location) throws Exception {
		hs.addStudent(name, age, location); // Προσθέτει τον φοιτητή μέσω HelloService
	}
}
