package gr.uom.market_week8;

import org.springframework.data.jpa.repository.JpaRepository;

//Το repository για τη διαχείριση προϊόντων.
//Επεκτείνει το JpaRepository για να παρέχει βασικές λειτουργίες CRUD και άλλες συναρτήσεις για την οντότητα Product.
public interface MarketRepository extends JpaRepository<Product, Integer> {

	 // Δηλώνει μια μέθοδο για την εύρεση προϊόντος με βάση το όνομά του.
	 // Το Spring Data JPA δημιουργεί αυτόματα την υλοποίηση αυτής της μεθόδου με βάση τη σύμβαση ονοματοδοσίας (naming convention).
	 Product findByName(String pr);

}
