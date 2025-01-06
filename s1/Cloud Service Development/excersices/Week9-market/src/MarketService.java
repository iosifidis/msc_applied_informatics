package gr.uom.market_week9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Δηλώνει ότι η κλάση είναι μια υπηρεσία (service) του Spring.
//Το Spring διαχειρίζεται αυτή την κλάση και την καθιστά διαθέσιμη για έγχυση εξαρτήσεων (dependency injection).
@Service
public class MarketService {

	 // Αυτόματη έγχυση της εξάρτησης για το repository της αγοράς (MarketRepository).
	 @Autowired
	 private ProductRepository productRepository;
	 
	 @Autowired
	 private SellerRepository sellerRepository;
	
	 // Δημιουργεί και επιστρέφει ένα αντικείμενο τύπου Product με τα δοθέντα χαρακτηριστικά.
	 public Product getProduct(int n, String a, int l) {
	     return new Product(n, a, l); 
	 }
	
	 // Επιστρέφει τη λίστα όλων των προϊόντων από το repository.
	 // Η μέθοδος μπορεί να προκαλέσει Exception αν κάτι πάει στραβά με την ανάκτηση δεδομένων.
	 public List<Product> getAllProducts() throws Exception {
	     return productRepository.findAll();
	 }
	
	 
	
	 // Αναζητά και επιστρέφει ένα προϊόν με βάση το όνομά του.
	 // Η μέθοδος μπορεί να προκαλέσει Exception αν κάτι πάει στραβά κατά την αναζήτηση.
	 public Product findProduct(String pr) throws Exception {
	     return productRepository.findByName(pr);
	 }
	 
	// Προσθέτει ένα νέο προϊόν στο repository.
		 // Η μέθοδος μπορεί να προκαλέσει Exception αν κάτι πάει στραβά κατά την αποθήκευση.
		 public void addProduct(Product pr) throws Exception {
			 Optional<Product> byId = productRepository.findById(pr.getCode());
			 if(!byId.isPresent())
				 productRepository.save(pr);
		 }
	 
	 public void addSeller(Seller s) throws Exception {
		  Optional<Seller> byId = sellerRepository.findById(s.getName());
		  if(!byId.isPresent())
		   sellerRepository.save(s);
		 }
}

