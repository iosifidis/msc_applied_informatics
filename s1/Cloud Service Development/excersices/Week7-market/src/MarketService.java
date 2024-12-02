package gr.uom.market_week6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MarketService {

	List<Product> pList;
	
	
	public List<Product> getAllProducts() {
		return pList;
	}

	public void addProduct(Product pr) throws Exception {
	    if (pList == null) {
	        pList = new ArrayList<>();
	    }
	    pList.add(pr); // Προσθέτει το προϊόν στη λίστα
	    saveToDB(pr);  // Αποθηκεύει στη βάση
	}

	private void saveToDB(Product pr) throws Exception {
	    // Σύνδεση στη βάση δεδομένων
	    String myDriver = "org.gjt.mm.mysql.Driver";
	    String myUrl = "jdbc:mysql://localhost/market";
	    Connection conn = null;
	    Class.forName(myDriver);
	    conn = DriverManager.getConnection(myUrl, "root", "");

	    // Ελέγξτε αν η σύνδεση είναι επιτυχής
	    if (conn != null) {
	        System.out.println("Connection established successfully.");
	    }

	    // Προετοιμασία του SQL query για εισαγωγή δεδομένων
	    String query = "INSERT INTO products (code, name, price) VALUES (?, ?, ?)";
	    PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	    preparedStmt.setInt(1, pr.getCode());  // Ανάθεση του κωδικού του προϊόντος
	    preparedStmt.setString(2, pr.getName());  // Ανάθεση του ονόματος του προϊόντος
	    preparedStmt.setInt(3, pr.getPrice());  // Ανάθεση της τιμής του προϊόντος
	      
	    // Εκτέλεση του query
	    preparedStmt.executeUpdate();  // Χρησιμοποιούμε το executeUpdate αντί για execute
	        
	    System.out.println("Product added successfully.");
	    // Κλείσιμο σύνδεσης
	    if (conn != null) {
	         conn.close();
	    }
	    
	}

		

	public void setList(ArrayList<Product> loadFromDB) {
		pList = loadFromDB;
	}
	
	public Product findProduct(String productName) {
	    for (Product product : pList) {
	        if (product.getName() != null && product.getName().equalsIgnoreCase(productName)) {
	            return product;
	        }
	    }
	    // Επιστροφή null ή άλλου αντικειμένου αν δεν βρεθεί το προϊόν
	    return null;
	}

}
