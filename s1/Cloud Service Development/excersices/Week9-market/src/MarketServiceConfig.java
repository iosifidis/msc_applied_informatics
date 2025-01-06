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
//Μπορεί να περιέχει beans και άλλες ρυθμίσεις της εφαρμογής.
@Configuration
public class MarketServiceConfig implements CommandLineRunner {

	 // Αυτόματη έγχυση του service `MarketService`.
	 @Autowired
	 private MarketService ms;
	
	 // Η μέθοδος `run` εκτελείται αυτόματα κατά την εκκίνηση της εφαρμογής.
	 // Μπορεί να χρησιμοποιηθεί για αρχικοποίηση δεδομένων ή άλλες ενέργειες εκκίνησης.
	 @Override
	 public void run(String... args) throws Exception {
		 	Seller s1 = new Seller("Apostolis");
			ms.addSeller(s1);
			Seller s2 = new Seller("Daniel");
			ms.addSeller(s2);
			Seller s3 = new Seller("Alex");
			ms.addSeller(s3);
			
			Sale sa3 = new Sale(1, 3);
			sa3.setSeller(s3);
			Sale c4 = new Sale(2, 4);
			c4.setSeller(s3);
			Sale sa1 = new Sale(3, 5);
			sa1.setSeller(s1);
			Sale sa2 = new Sale(4, 6);
			sa2.setSeller(s1);
			Sale c5 = new Sale(5, 7);
			c5.setSeller(s2);

			
			Product p1 = new Product(1, "Gala",1);
			p1.addSale(sa2);
			p1.addSale(sa3);
			ms.addProduct(p1);
			
			Product p2 = new Product(2,"Tiri",4);
			p2.addSale(sa1);
			p2.addSale(c4);
			ms.addProduct(p2);
			
			Product p3 = new Product(1,"Sokolata",2);
			p3.addSale(c5);
			ms.addProduct(p3);
	
	     // Εκτυπώνει μήνυμα επιβεβαίωσης στην κονσόλα μόλις ολοκληρωθεί η αρχικοποίηση.
	     System.out.println("DB has been created to MarketService!!!");
	 }
}

