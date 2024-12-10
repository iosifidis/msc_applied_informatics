package gr.uom.market_week7;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

//Ενεργοποιεί το CORS (Cross-Origin Resource Sharing) για όλους τους προορισμούς (origins) και κεφαλίδες (headers)
@CrossOrigin(origins = "*", allowedHeaders = "*")
//Δηλώνει ότι αυτή η κλάση είναι REST Controller
@RestController
public class MarketController {

	 // Εξάρτηση του MarketService μέσω @Autowired για παροχή υπηρεσιών
	 @Autowired
	 private MarketService ms;
	 
	 // Endpoint για την ανάκτηση όλων των προϊόντων
	 @GetMapping(path = "/allproducts")
	 public List<Product> getAllProducts() throws Exception {
	     // Επιστρέφει τη λίστα προϊόντων από την υπηρεσία MarketService
	     return ms.getAllProducts();
	 } 
	 
	 // Endpoint για την προσθήκη νέου προϊόντος
	 @PostMapping(path = "/addproduct")
	 public String addProduct(@RequestBody Product pr) throws Exception {
	     // Καλεί την υπηρεσία MarketService για την προσθήκη του προϊόντος
	     ms.addProduct(pr);
	     // Επιστρέφει μήνυμα επιτυχίας
	     return "Το προϊόν προστέθηκε με επιτυχία!";
	 }
	 
	 // Endpoint για την εύρεση προϊόντος με βάση το όνομά του
	 @GetMapping(path = "/findproduct")
	 public String findProduct(String name) {
		 return ms.findProduct(name);
	 }
}
