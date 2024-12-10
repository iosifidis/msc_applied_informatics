package gr.uom.market_week7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

//Δηλώνει ότι αυτή η κλάση είναι υπηρεσία (service) και διαχειρίζεται τις λειτουργίες της αγοράς
@Service
public class MarketService {

	 // Λίστα που περιέχει όλα τα προϊόντα
	 private List<Product> pList;
	
	 // Μέθοδος για την επιστροφή όλων των προϊόντων από τη λίστα
	 public List<Product> getAllProducts() {
	     return pList;
	 }
	
	 // Ιδιωτική μέθοδος για την αποθήκευση ενός προϊόντος στη βάση δεδομένων
	 private void saveToDB(Product pr) throws Exception {
	     // Ορισμός του driver για τη σύνδεση με MySQL
	     String myDriver = "org.gjt.mm.mysql.Driver";
	     String myUrl = "jdbc:mysql://localhost/market";
	     Connection conn = null;
	     Class.forName(myDriver); // Φόρτωση της κλάσης του driver
	     conn = DriverManager.getConnection(myUrl, "root", ""); // Δημιουργία σύνδεσης
	
	     // Έλεγχος εάν η σύνδεση είναι επιτυχής
	     if (conn != null) {
	         System.out.println("Connection established successfully.");
	     }
	
	     // Δημιουργία του SQL query για την εισαγωγή δεδομένων
	     String query = "INSERT INTO products (code, name, price) VALUES (?, ?, ?)";
	     PreparedStatement preparedStmt = conn.prepareStatement(query);
	
	     // Ανάθεση τιμών στα placeholders του query
	     preparedStmt.setInt(1, pr.getCode());  // Κωδικός προϊόντος
	     preparedStmt.setString(2, pr.getName());  // Όνομα προϊόντος
	     preparedStmt.setInt(3, pr.getPrice());  // Τιμή προϊόντος
	
	     // Εκτέλεση του query
	     preparedStmt.executeUpdate();  // Ενημερώνει τη βάση δεδομένων
	
	     System.out.println("Product added successfully.");
	     
	     // Κλείσιμο της σύνδεσης
	     if (conn != null) {
	         conn.close();
	     }
	 }
	
	 // Μέθοδος για την προσθήκη προϊόντος στη λίστα και τη βάση δεδομένων
	 public void addProduct(Product pr) throws Exception {
	     // Εάν η λίστα είναι κενή, αρχικοποιείται
	     if (pList == null) {
	         pList = new ArrayList<>();
	     }
	     pList.add(pr); // Προσθήκη του προϊόντος στη λίστα
	     saveToDB(pr);  // Αποθήκευση του προϊόντος στη βάση δεδομένων
	 }
	
	 // Μέθοδος για την ενημέρωση της λίστας προϊόντων από δεδομένα της βάσης δεδομένων
	 public void setList(ArrayList<Product> loadFromDB) {
	     pList = loadFromDB;
	 }
	
	 // Μέθοδος για την εύρεση προϊόντος με βάση το όνομά του
	 public String findProduct(String pr) {

		 String result = "Το προϊόν " + pr + " δεν βρέθηκε.";
		 
//			// Έλεγχος αν το όνομα είναι άκυρο
//		    if (pr == null || pr.isBlank()) {
//		        result = "Σφάλμα: Δεν δόθηκε όνομα προϊόντος.";
//		    }

		    // Εύρεση του προϊόντος. Αναζητά το προϊόν στη λίστα συγκρίνοντας τα ονόματα
		    for (Product product : pList) {
		         if (product.getName().equalsIgnoreCase(pr)) {
		        	 result = "Το προϊόν " + pr + " βρέθηκε."; // Επιστροφή του προϊόντος αν βρεθεί
		         }

		    }
		    return result;
	 }
}
