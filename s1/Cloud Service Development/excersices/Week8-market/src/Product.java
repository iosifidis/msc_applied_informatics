package gr.uom.market_week8;

import javax.persistence.*;

//Δηλώνει ότι αυτή η κλάση είναι μια οντότητα (entity) που αντιπροσωπεύει έναν πίνακα στη βάση δεδομένων.
@Entity
public class Product {

	 // Δηλώνει ότι το πεδίο `code` είναι το πρωτεύον κλειδί (primary key) του πίνακα.
	 @Id
	 private Integer code; // Αλλαγή από `int` σε `Integer` για να υποστηρίζεται η χρήση null τιμών.
	
	 // Το όνομα του προϊόντος.
	 private String name;
	
	 // Η τιμή του προϊόντος.
	 // Αλλαγή από `int` σε `Integer` για μεγαλύτερη ευελιξία και συμβατότητα με τη βάση δεδομένων.
	 private Integer price;
	
	 // Προεπιλεγμένος κατασκευαστής (default constructor) που αρχικοποιεί τα πεδία με τιμές default.
	 public Product() {
	     code = 0;
	     name = "";
	     price = 0;
	 }
	
	 // Κατασκευαστής με ορίσματα για αρχικοποίηση των πεδίων.
	 public Product(Integer c, String n, Integer t) {
	     code = c;
	     name = n;
	     price = t;
	 }
	
	 // Μέθοδος getter για το πεδίο `code`.
	 public int getCode() {
	     return code;
	 }
	
	 // Μέθοδος getter για το πεδίο `name`.
	 public String getName() {
	     return name;
	 }
	
	 // Μέθοδος getter για το πεδίο `price`.
	 public int getPrice() {
	     return price;
	 }
	
//    @Override
//    public String toString() {
//        return "Product{name='" + name + "'}";
//    }


}
