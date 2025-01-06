package gr.uom.market_week9;

import java.util.HashSet;
import java.util.Set;

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
	
	 
	 @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
		@JoinTable(name="product_sale", 
				   joinColumns = @JoinColumn(name="product_name"),
				   inverseJoinColumns = @JoinColumn(name="sale_name"))
		private Set<Sale> sales = new HashSet<Sale>();
	 
	 
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
	 
	 public void addSale(Sale s) {
			sales.add(s);
			s.addPoduct(this);
		}
	
	 public Set<Sale> getSales() {return sales;}
	
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
