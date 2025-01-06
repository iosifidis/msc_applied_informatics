package gr.uom.market_week9;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Η κύρια κλάση της εφαρμογής Spring Boot
@SpringBootApplication // Η ανατολή αυτή ενεργοποιεί τις βασικές λειτουργίες του Spring Boot όπως η αυτόματη ρύθμιση (auto-configuration).
public class MarketWeek9Application {

	 // Το σημείο εκκίνησης της εφαρμογής.
	 public static void main(String[] args) {
	     // Εκκίνηση της εφαρμογής Spring Boot χρησιμοποιώντας την κλάση MarketWeek8Application.
	     SpringApplication.run(MarketWeek9Application.class, args);
	 }
}
