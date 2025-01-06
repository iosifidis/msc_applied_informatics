package gr.uom.market_week9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

//Δηλώνει ότι αυτή η κλάση είναι μια κλάση ρύθμισης (configuration class) στο Spring.
//Αυτή η κλάση μπορεί να περιέχει beans και άλλες ρυθμίσεις της εφαρμογής.
@Configuration
public class MarketServiceConfig implements CommandLineRunner {

	 // Αυτόματη έγχυση του service `MarketService`.
	 // Το Spring παρέχει αυτόματα μια υλοποίηση της `MarketService` στον κατάλληλο χρόνο.
	 @Autowired
	 private MarketService ms;
	
	 // Η μέθοδος `run` εκτελείται αυτόματα κατά την εκκίνηση της εφαρμογής.
	 // Χρησιμοποιείται για την αρχικοποίηση δεδομένων ή άλλες ενέργειες εκκίνησης.
	 @Override
	 public void run(String... args) throws Exception {
	     // Δημιουργία και προσθήκη πωλητών στο σύστημα.
	     Seller s1 = new Seller("Apostolis");
	     ms.addSeller(s1);
	     Seller s2 = new Seller("Daniel");
	     ms.addSeller(s2);
	     Seller s3 = new Seller("Alex");
	     ms.addSeller(s3);
	
	     // Δημιουργία πωλήσεων και συσχέτιση τους με πωλητές.
	     Sale sa3 = new Sale(1, 3); // Πώληση με ID 1 και ποσότητα 3.
	     sa3.setSeller(s3);         // Συσχέτιση με τον πωλητή Alex.
	     Sale c4 = new Sale(2, 4);
	     c4.setSeller(s3);
	     Sale sa1 = new Sale(3, 5);
	     sa1.setSeller(s1);
	     Sale sa2 = new Sale(4, 6);
	     sa2.setSeller(s1);
	     Sale c5 = new Sale(5, 7);
	     c5.setSeller(s2);
	
	     // Δημιουργία προϊόντων και προσθήκη πωλήσεων στα προϊόντα.
	     Product p1 = new Product(1, "Gala", 1); // Δημιουργία προϊόντος "Γάλα" με ID 1 και κατηγορία 1.
	     p1.addSale(sa2); // Προσθήκη των πωλήσεων στο προϊόν.
	     p1.addSale(sa3);
	     ms.addProduct(p1); // Προσθήκη του προϊόντος στο σύστημα.
	
	     Product p2 = new Product(2, "Tiri", 4); // Δημιουργία προϊόντος "Τυρί".
	     p2.addSale(sa1);
	     p2.addSale(c4);
	     ms.addProduct(p2);
	
	     Product p3 = new Product(1, "Sokolata", 2); // Δημιουργία προϊόντος "Σοκολάτα".
	     p3.addSale(c5);
	     ms.addProduct(p3);
	
	     // Εκτύπωση μηνύματος επιβεβαίωσης στην κονσόλα μόλις ολοκληρωθεί η αρχικοποίηση.
	     System.out.println("DB has been created to MarketService!!!");
	 }
}