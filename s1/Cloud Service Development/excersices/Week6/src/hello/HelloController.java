package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

//Δηλώνει ότι αυτή η κλάση είναι Controller και θα περιέχει endpoints για το API
//@CrossOrigin επιτρέπει την πρόσβαση από άλλες προελεύσεις (CORS policy)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {

	// Αυτόματη έγχυση εξάρτησης (dependency injection) για την κλάση HelloService
	@Autowired
	private HelloService hs;
	
	// Ορίζει ένα endpoint που ανταποκρίνεται σε αιτήματα GET χωρίς συγκεκριμένη διαδρομή
    // Επιστρέφει το μήνυμα "Hello World"
	@GetMapping
	public String helloWorld() {
		return "Hello World";
	}
	
    // Ορίζει ένα endpoint GET στη διαδρομή "/list"
    // Επιστρέφει μια λίστα με μηνύματα κειμένου http://localhost:8080/hello
	@GetMapping(path="/list")
	public List<String> printList() {
		List<String> sList = new ArrayList<String>();
		sList.add("Hello World");
		sList.add("My name is Apostolis");
		sList.add("My surname is Ampatzoglou");
		return sList;
	}
	
    // Ορίζει ένα endpoint GET με δυναμική διαδρομή που περιέχει παράμετρο "name"
    // Παράδειγμα κλήσης: http://localhost:8080/askName/Nikos
	@GetMapping(path="/askName/{name}")
	public String askName(@PathVariable(value="name") String name) {
		return "Hello " + name;
	}

    // Ορίζει ένα endpoint GET με παραμέτρους query string: name, age, location
    // Παράδειγμα κλήσης: http://localhost:8080/student?name=Gio&age=24&location=Salonica
	@GetMapping(path="/student")
	public Student getStudent(@RequestParam(value="name") String name,
			@RequestParam(value="age") int age,
			@RequestParam(value="location") String location) {
		
		// Dependency Injection, with autowired
		// Επιστρέφει ένα αντικείμενο Student από την υπηρεσία HelloService
		return hs.getStudent(name, age, location);
	} 

	// Endpoint GET που επιστρέφει μια λίστα με όλους τους φοιτητές
    // Παράδειγμα κλήσης: http://localhost:8080/students
	@GetMapping(path="/students")
	public List<Student> getAllStudent()  throws Exception{
		return hs.getAllStudents();
	} 
	
   // Endpoint GET για προσθήκη ενός φοιτητή
    // Παράδειγμα κλήσης: http://localhost:8080/addStudent?name=John&age=22&location=Athens
	@GetMapping(path="/addStudent")
	public void addStudent(@RequestParam(value="name") String name,
			@RequestParam(value="age") int age,
			@RequestParam(value="location") String location) throws Exception {
		hs.addStudent(name, age, location);
	}
}
