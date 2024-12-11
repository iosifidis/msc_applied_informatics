package gr.uom.market_week8;

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
	     // Προσθήκη αρχικών προϊόντων στη βάση δεδομένων μέσω του `MarketService`.
	     ms.addProduct(new Product(1, "Kaseri", 4));
	     ms.addProduct(new Product(2, "Salata", 5));
	     ms.addProduct(new Product(3, "Gala", 1));
	
	     // Εκτυπώνει μήνυμα επιβεβαίωσης στην κονσόλα μόλις ολοκληρωθεί η αρχικοποίηση.
	     System.out.println("DB has been created to MarketService!!!");
	 }
}

