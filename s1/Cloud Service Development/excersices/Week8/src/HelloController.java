package com.example.demo.hello;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

// Since this is the controller, it is the place where we add our end-points
//Αυτός ο ελεγκτής χειρίζεται τα αιτήματα που αφορούν τους φοιτητές μέσω REST.
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {

	@Autowired
	private HelloService hs;

	// Αντιμετωπίζει το αίτημα GET προς το endpoint "/students"
	// Επιστρέφει τη λίστα με όλους τους φοιτητές από το service.
	@GetMapping(path="/students")
	public List<Student> getAllStudent()  throws Exception{
		return hs.getAllStudents();
	} 
	
	// Αντιμετωπίζει το αίτημα POST προς το endpoint "/addStudent"
	// Επιτρέπει την προσθήκη νέου φοιτητή στην βάση μέσω ενός JSON body.
	@PostMapping(path="/addStudent")
	public void addStudent(@RequestBody Student st) throws Exception {
		hs.addStudent(st);
	}
}
