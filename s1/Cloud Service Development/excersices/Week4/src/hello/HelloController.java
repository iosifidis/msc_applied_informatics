package com.example.demo.hello;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Ορισμός ασφαλείας για την πρόσβαση από οποιαδήποτε πηγή
@CrossOrigin(origins="*", allowedHeaders="*")

//Ανακοίνωση της κλάσης ως Rest Controller, υπεύθυνο για τη διαχείριση των αιτημάτων
@RestController
public class HelloController {


	
	// Ορίζει ότι φτιάχνουμε μεταβλητή χωρίς να την φτιάξουμε. Καλούμε μια μέθοδο ondemand
	// Ανάθεση του Service μέσω του @Autowired
	@Autowired
	private HelloService hs;
	
	// Endpoint για την υποδοχή αιτήματος με παράμετρο το όνομα
    // Καλείται ως: http://localhost:8080/askName/Nikos
	@GetMapping(path="/askName/{name}")
	public String askName(@PathVariable(value="name") String name) {
		return "Hello " + name;
	}

//	// Call as follows:
//	// http://localhost:8080/student?name=Gio&age=24&location=Salonica
//	@GetMapping(path="/student") // Θέλω να επιστρέφει φοιτητή (αντικείμενο). Επιστρέφει JSON
//	public Student getStudent(@RequestParam(value="name") String name,
//			@RequestParam(value="age") int age,
//			@RequestParam(value="location") String location) {
//		
//		// Χρήση του service
//		return hs.getStudent(name, age, location);
//		//Student s = new Student(name, age, location);
//		//return s;
//	} 


	// Endpoint για την προσθήκη νέου φοιτητή μέσω αιτήματος GET
    // Καλείται ως: http://localhost:8080/addstudent?name=Gio&age=24&location=Salonica
	@GetMapping(path="/addstudent") // Θέλω να επιστρέφει φοιτητή (αντικείμενο). Επιστρέφει JSON
	public void addStudent(@RequestParam(value="name") String name,
			@RequestParam(value="age") int age,
			@RequestParam(value="location") String location) {
		
		// Χρήση του service
		hs.addStudent(name, age, location);

	} 

	// Endpoint για την ανάκτηση όλων των φοιτητών ως JSON
    // Καλείται ως: http://localhost:8080/allStudents
	@GetMapping(path="/allStudents")
	public ArrayList<Student> allStudents(){
		return hs.getAllStudents();
	}
	
}
