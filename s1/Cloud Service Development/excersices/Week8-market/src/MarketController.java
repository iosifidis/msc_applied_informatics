package gr.uom.market_week8;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MarketController {
	
	@Autowired
	private MarketService ms;
	
	@GetMapping(path="/allproducts")
	public List<Product> getAllProducts() throws Exception{
		return ms.getAllProducts();
	} 
	
	@PostMapping(path="/addproduct")
	public void addProduct(@RequestBody Product pr) throws Exception {
		ms.addProduct(pr);
	}
	
	 @GetMapping(path = "/findproduct")
	 public String findProduct(String name) throws Exception {
		 Product product = ms.findProduct(name);
		 
		 if (product != null) {
		        return "Το προϊόν βρέθηκε."; // Επιστροφή του προϊόντος
		    } else {
		        return "Το προϊόν δεν βρέθηκε."; // Μήνυμα αποτυχίας
		    }
	 }
}
