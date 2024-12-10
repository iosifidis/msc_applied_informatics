package gr.uom.market_week7;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Σηματοδοτεί την κύρια κλάση της εφαρμογής Spring Boot
@SpringBootApplication
public class MarketWeek7Application {

	 // Το κύριο σημείο εκκίνησης (entry point) της εφαρμογής
	 public static void main(String[] args) {
	     // Εκκινεί την εφαρμογή Spring Boot με βάση την τρέχουσα κλάση
	     SpringApplication.run(MarketWeek7Application.class, args);
	 }
}
