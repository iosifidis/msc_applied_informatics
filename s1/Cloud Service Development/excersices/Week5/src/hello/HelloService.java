package com.example.demo.hello;

import java.sql.*;
import java.util.*;
import org.springframework.stereotype.*;

//Δηλώνουμε την κλάση ως service component του Spring
@Service
public class HelloService {

	// Λίστα για αποθήκευση φοιτητών
	List<Student> sList = new ArrayList<Student>();
	
	// Μέθοδος που δημιουργεί και επιστρέφει έναν φοιτητή
    public Student getStudent(String n, int a, String l) {
        return new Student(n, a, l); // Δημιουργία νέου αντικειμένου Student
    }

//	public void addStudent(String name, int age, String location) {
//		sList.add(new Student(name, age, location));
//	}
	
    // Μέθοδος που συνδέεται στη βάση δεδομένων και λαμβάνει όλους τους φοιτητές
	public void getStudents() throws Exception {
		  // Διαγράφουμε την προηγούμενη λίστα για να αποφύγουμε διπλότυπα δεδομένα
		  sList = new ArrayList<Student>();
		
		  // Δημιουργία σύνδεσης με τη βάση δεδομένων MySQL
	      String myDriver = "org.gjt.mm.mysql.Driver"; // Driver σύνδεσης για MySQL
	      String myUrl = "jdbc:mysql://localhost/university"; // URL της βάσης δεδομένων
	      // Λέμε στην java να φορτώσει αυτόν τον driver για να γίνει η σύνδεση
	      Class.forName(myDriver); // Φορτώνουμε τον driver
	      
	      // Πετάει σφάλμα οπότε βάζουμε throws Exception
	      
	      // Σύνδεση με βάση jdbc
	      Connection conn = DriverManager.getConnection(myUrl,"root","");
	      
	      // Ερώτημα για την ανάκτηση όλων των φοιτητών
	      String q = "SELECT * FROM students";
	      
	      // Εκτέλεση του ερωτήματος και αποθήκευση του αποτελέσματος
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset παίρνω το αποτέλεσμα και αποθηκεύω
	      ResultSet rs = st.executeQuery(q);
	      
  	      // Επεξεργασία των δεδομένων του αποτελέσματος
	      // iterate through the java resultset - επαναλαμβανόμενα παίρνω ένα ένα τα στοιχεία
	      while (rs.next()) // Μέχρι το αποτέλεσμα που πήρα να έχει επόμενο
	      {
	        String name = rs.getString("name"); // Ανάκτηση του ονόματος
	        String location = rs.getString("location"); // Ανάκτηση της τοποθεσίας
	        int age = rs.getInt("age"); // Ανάκτηση της ηλικίας
	        sList.add(new Student(name, age, location)); // Δημιουργία αντικειμένου Student και προσθήκη στη λίστα
	      }
	      st.close();		// Κλείσιμο της σύνδεσης
	}
	
	// Μέθοδος που προσθέτει έναν φοιτητή στη βάση δεδομένων
	public void addStudent(String name, int age, String location) throws Exception {
		// Ενημέρωση της λίστας με τους υπάρχοντες φοιτητές από τη βάση
		getStudents();
		
		// Προσθήκη του νέου φοιτητή στη λίστα
		sList.add(new Student(name, age, location));
		
		
		// Δημιουργία σύνδεσης με τη βάση δεδομένων MySQL
	    String myDriver = "org.gjt.mm.mysql.Driver"; // Driver σύνδεσης για MySQL
	    String myUrl = "jdbc:mysql://localhost/university"; // URL της βάσης δεδομένων
	    // Λέμε στην java να φορτώσει αυτόν τον driver για να γίνει η σύνδεση
	    Class.forName(myDriver); // Φορτώνουμε τον driver
	     
	    // Πετάει σφάλμα οπότε βάζουμε throws Exception
	      
	    // Σύνδεση με βάση jdbc
	    Connection conn = DriverManager.getConnection(myUrl,"root","");
	    
	    // Ερώτημα στην βάση
	    
	    // Καρφωτό ερώτημα. Δεν θα δουλέψει μάλλον
//        String q = "INSERT INTO `students` (`name`, `age`, `location`) VALUES ('Stathis', 49, 'Salonica')";
	    	    
        // create the java statement Εκτέλεση του ερωτήματος
//	    Statement st = conn.createStatement();
//	    st.executeQuery(q);
//	    
//        st.close();		// Κλείνω το connection 
	    
  	    // Προετοιμασμένη δήλωση για εισαγωγή δεδομένων
	    String q = "insert into students values (?,?,?)";
	    PreparedStatement preparedStmt = conn.prepareStatement(q);
	    
	    // Αντικατάσταση παραμέτρων στο ερώτημα
	    preparedStmt.setString (1, name);
	    preparedStmt.setInt(2,  age);
	    preparedStmt.setString (3, location);
	    
	    // Εκτέλεση του ερωτήματος
	    preparedStmt.execute();
	    
	    
	}
}
