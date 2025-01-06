package gr.uom.market_week9;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

//Επιτρέπει αιτήσεις από οποιαδήποτε προέλευση (origin) και οποιαδήποτε headers.
//Χρήσιμο για την υποστήριξη CORS (Cross-Origin Resource Sharing).
@CrossOrigin(origins = "*", allowedHeaders = "*")

//Δηλώνει ότι αυτή η κλάση είναι ένας REST Controller.
//Οι μέθοδοι της κλάσης μπορούν να χειριστούν HTTP αιτήματα και να επιστρέφουν JSON ή άλλα δεδομένα.
@RestController
public class MarketController {

	 // Αυτόματη έγχυση της υπηρεσίας `MarketService`.
	 @Autowired
	 private MarketService ms;
	
	 // Διαχειρίζεται αιτήματα GET στο endpoint `/allproducts`.
	 // Επιστρέφει τη λίστα όλων των προϊόντων μέσω της μεθόδου `getAllProducts` του `MarketService`.
	 @GetMapping(path = "/allproducts")
	 public List<Product> getAllProducts() throws Exception {
	     return ms.getAllProducts();
	 }
	
	 // Διαχειρίζεται αιτήματα POST στο endpoint `/addproduct`.
	 // Προσθέτει ένα νέο προϊόν λαμβάνοντας το αντικείμενο `Product` από το σώμα του αιτήματος (request body).
	 @PostMapping(path = "/addproduct")
	 public void addProduct(@RequestBody Product pr) throws Exception {
	     ms.addProduct(pr);
	 }
	
	 // Διαχειρίζεται αιτήματα GET στο endpoint `/findproduct`.
	 // Αναζητά προϊόν με βάση το όνομα που παρέχεται ως παράμετρος.
	 @GetMapping(path = "/findproduct")
	 public String findProduct(String name) throws Exception {
	     // Χρήση της υπηρεσίας `MarketService` για την εύρεση του προϊόντος.
	     Product product = ms.findProduct(name);
	
	     // Επιστροφή κατάλληλου μηνύματος με βάση την ύπαρξη του προϊόντος.
	     if (product != null) {
	         return "Το προϊόν βρέθηκε."; // Επιστροφή επιτυχίας
	     } else {
	         return "Το προϊόν δεν βρέθηκε."; // Επιστροφή αποτυχίας
	     }
	 }
}

