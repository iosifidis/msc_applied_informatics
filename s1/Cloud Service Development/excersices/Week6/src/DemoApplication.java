package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Σημειώνει την κλάση ως σημείο εκκίνησης της εφαρμογής Spring Boot.
//Περιλαμβάνει και την αυτόματη ρύθμιση (auto-configuration) της εφαρμογής.
@SpringBootApplication
public class DemoApplication {

	// Η κύρια μέθοδος (main) που εκτελείται κατά την εκκίνηση της εφαρμογής.
	public static void main(String[] args) {
		// Εκκινεί την εφαρμογή Spring Boot με την κλάση DemoApplication ως βασική διαμόρφωση.
		SpringApplication.run(DemoApplication.class, args);
	}

}
