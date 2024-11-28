package com.example.demo.hello;

import java.sql.*;
import java.util.*;
import org.springframework.stereotype.*;

//Δηλώνει ότι αυτή η κλάση είναι μια υπηρεσία (Service) στο πλαίσιο του Spring
@Service
public class HelloService {

	// Λίστα φοιτητών που χρησιμοποιείται για αποθήκευση δεδομένων προσωρινά στη μνήμη
	List<Student> sList = new ArrayList<Student>();
	
	// Δημιουργεί και επιστρέφει ένα αντικείμενο Student με τις δοθείσες παραμέτρους
	public Student getStudent(String n, int a, String l) {
		return new Student(n, a, l); 
	}

	// Προσθέτει έναν νέο φοιτητή στη λίστα και αποθηκεύει τον φοιτητή στη βάση δεδομένων
	public void addStudent(String name, int age, String location) throws Exception {
		// loadFromDB(); // Το LOAD το μετέφερα γιατί το κάνει στο Config
		
		// Προσθήκη φοιτητή στη λίστα
		sList.add(new Student(name, age, location));
		// Αποθήκευση φοιτητή στη βάση δεδομένων
		saveToDB(new Student(name, age, location));
	}

	// Επιστρέφει όλους τους φοιτητές από τη λίστα
	public List<Student> getAllStudents() throws Exception {
		return sList;
	}

	// Ιδιωτική μέθοδος για αποθήκευση ενός φοιτητή στη βάση δεδομένων
	private void saveToDB(Student s)  throws Exception{
		  
	  // Δημιουργία σύνδεσης με τη βάση δεδομένων MySQL
      String myDriver = "org.gjt.mm.mysql.Driver";   // Οδηγός JDBC
      String myUrl = "jdbc:mysql://localhost/university";   // URL της βάσης
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
      
      // Προετοιμασία SQL ερωτήματος για εισαγωγή δεδομένων στη βάση
      String query = "insert into students(name,age,location) values (?, ?, ?)"; // Βάλαμε το (name,age,location)
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      
      // Ορισμός τιμών για τα πεδία name, age, location
      preparedStmt.setString (1, s.getName());
      preparedStmt.setInt (2, s.getAge());
      preparedStmt.setString   (3, s.getLocation());
      
      // Εκτέλεση του ερωτήματος
      preparedStmt.execute();
	}

	// Θέτει μια νέα λίστα φοιτητών, φορτωμένη από εξωτερική πηγή, στη μεταβλητή sList
	public void setList(ArrayList<Student> loadFromDB) {
		sList = loadFromDB;
		
	}
}
