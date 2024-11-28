package com.example.demo.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

//Δηλώνει την κλάση ως Spring Configuration, η οποία μπορεί να εκτελεί συγκεκριμένο κώδικα κατά την εκκίνηση
@Configuration
public class HelloServiceConfig implements CommandLineRunner{
	
	// Αυτόματη έγχυση της υπηρεσίας HelloService
	@Autowired
	private HelloService hs;

	// Η μέθοδος run εκτελείται αυτόματα κατά την εκκίνηση της εφαρμογής
	@Override
	public void run(String... args) throws Exception {
		// Φορτώνει δεδομένα από τη βάση και τα θέτει στη λίστα της HelloService
		hs.setList(loadFromDB());
	}
	
	// Ιδιωτική μέθοδος που φορτώνει δεδομένα φοιτητών από τη βάση δεδομένων
	private ArrayList<Student> loadFromDB() throws Exception { // Αλλάζω void σε ArrayList<Student>
		
		// Δημιουργία λίστας για αποθήκευση των φοιτητών που θα φορτωθούν
		ArrayList<Student> sList = new ArrayList<Student>();
		  
		// Δημιουργία σύνδεσης με τη βάση δεδομένων MySQL
	    String myDriver = "org.gjt.mm.mysql.Driver"; // Οδηγός JDBC
	    String myUrl = "jdbc:mysql://localhost/university";  // URL της βάσης δεδομένων
	    Class.forName(myDriver);
	    Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	    // our SQL SELECT query. 
	    // if you only need a few columns, specify them by name instead of using "*"
	    
	    // SQL SELECT ερώτημα για την ανάκτηση δεδομένων από τον πίνακα students
	    String query = "SELECT * FROM students";

	    // Δημιουργία δήλωσης (Statement) για την εκτέλεση του ερωτήματος
	    Statement st = conn.createStatement();
	      
	    // Εκτέλεση του ερωτήματος και αποθήκευση των αποτελεσμάτων σε ResultSet
	    ResultSet rs = st.executeQuery(query);
	      
	    // Επανάληψη μέσα στα αποτελέσματα (result set)
	    while (rs.next())
	     {
	       // Ανάκτηση των δεδομένων για κάθε φοιτητή
	       String name = rs.getString("name");
	       String location = rs.getString("location");
	       int age = rs.getInt("age");
	       // Προσθήκη του φοιτητή στη λίστα
	       sList.add(new Student(name, age, location));
	     }
	    
	    // Κλείσιμο του Statement
	    st.close();
		
	    // Επιστροφή της λίστας με τους φοιτητές
	    return sList;  
	    
	}
	
	
}
