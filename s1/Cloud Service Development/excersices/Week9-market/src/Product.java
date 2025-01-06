package gr.uom.market_week9;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

//Δηλώνει ότι αυτή η κλάση είναι μια οντότητα (entity) που αντιπροσωπεύει έναν πίνακα στη βάση δεδομένων.
@Entity
public class Product {

	 // Δηλώνει ότι το πεδίο `code` είναι το πρωτεύον κλειδί (primary key) του πίνακα.
	 @Id
	 private Integer code; // Χρήση `Integer` αντί για `int` για υποστήριξη null τιμών.
	
	 // Το όνομα του προϊόντος.
	 private String name;
	
	 // Η τιμή του προϊόντος.
	 // Χρήση `Integer` για μεγαλύτερη ευελιξία και συμβατότητα με τη βάση δεδομένων.
	 private Integer price;
	
	 // Συσχέτιση πολλών προς πολλά (Many-to-Many) με την οντότητα `Sale`.
	 // Δημιουργία πίνακα σύνδεσης `product_sale` για τη διαχείριση της σχέσης.
	 @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	 @JoinTable(
	     name = "product_sale", // Όνομα του πίνακα σύνδεσης.
	     joinColumns = @JoinColumn(name = "product_name"), // Στήλη για το προϊόν.
	     inverseJoinColumns = @JoinColumn(name = "sale_name") // Στήλη για την πώληση.
	 )
	 private Set<Sale> sales = new HashSet<Sale>();
	
	 // Προεπιλεγμένος κατασκευαστής (default constructor) που αρχικοποιεί τα πεδία με default τιμές.
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
	
	 // Μέθοδος για προσθήκη μιας πώλησης (Sale) στο προϊόν.
	 public void addSale(Sale s) {
	     sales.add(s); // Προσθήκη της πώλησης στη λίστα πωλήσεων.
	     s.addPoduct(this); // Ενημέρωση της αντίστροφης σχέσης στην οντότητα `Sale`.
	 }
	
	 // Getter μέθοδος για το σύνολο των πωλήσεων.
	 public Set<Sale> getSales() {
	     return sales;
	 }
	
	 // Getter μέθοδος για το πεδίο `code` (κωδικός προϊόντος).
	 public int getCode() {
	     return code;
	 }
	
	 // Getter μέθοδος για το πεδίο `name` (όνομα προϊόντος).
	 public String getName() {
	     return name;
	 }
	
	 // Getter μέθοδος για το πεδίο `price` (τιμή προϊόντος).
	 public int getPrice() {
	     return price;
	 }
	
	 // Μέθοδος `toString` για αναπαράσταση του προϊόντος ως συμβολοσειρά.
	 // Αποσχολιασμένη, μπορεί να ενεργοποιηθεί αν χρειαστεί για debugging ή εκτυπώσεις.
	// @Override
	// public String toString() {
	//     return "Product{name='" + name + "'}";
	// }
}
