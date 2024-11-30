package gr.uom.nba_app;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")   // Επιτρέπει αιτήσεις από οποιοδήποτε origin (σταυροειδής προέλευση), για να υποστηρίζει CORS.
@RestController   // Δηλώνει ότι αυτή η κλάση είναι ένας REST controller, το οποίο επιτρέπει τη χρήση HTTP request mappings.
public class NBAController {
	
	@Autowired   // Χρησιμοποιείται για να κάνουμε dependency injection και να εισάγουμε την υπηρεσία NBAService.
	private NBAService nbaS;   // Αντικείμενο της υπηρεσίας NBAService που θα χρησιμοποιηθεί για να πάρουμε τα δεδομένα από την υπηρεσία.
	
	// Η μέθοδος αυτή επιστρέφει τη λίστα με τους κορυφαίους σκορερς (top scorers).
	@GetMapping(path="/topScorers")  // Ορίζει το endpoint για το GET request στον δρόμο "/topScorers".
	public ArrayList<Player> getTopScorers() {
		return nbaS.getTopScorers(); // Καλεί τη μέθοδο getTopScorers() από την υπηρεσία NBAService και επιστρέφει τη λίστα των παικτών.
	}
	
	// Η μέθοδος αυτή επιστρέφει τη λίστα με τους κορυφαίους rebounders (παίκτες με τα περισσότερα rebounds).
	@GetMapping(path="/topRebounders")   // Ορίζει το endpoint για το GET request στον δρόμο "/topRebounders".
	public ArrayList<Player> getTopRebounders() {
		return nbaS.getTopRebounders();   // Καλεί τη μέθοδο getTopRebounders() από την υπηρεσία NBAService και επιστρέφει τη λίστα των παικτών.
	}
	
	// Η μέθοδος αυτή επιστρέφει τη λίστα με τους κορυφαίους πασέρ (players with the most assists).
	@GetMapping(path="/topPassers")   // Ορίζει το endpoint για το GET request στον δρόμο "/topPassers".
	public ArrayList<Player> getTopPassers() {
		return nbaS.getTopPassers();   // Καλεί τη μέθοδο getTopPassers() από την υπηρεσία NBAService και επιστρέφει τη λίστα των παικτών.
	}
	
	// Η μέθοδος αυτή επιστρέφει τη λίστα με τους κορυφαίους παίκτες (top players με βάση το συνολικό άθροισμα πόντων, rebound, και assists).
	@GetMapping(path="/topPlayers")   // Ορίζει το endpoint για το GET request στον δρόμο "/topPlayers".
	public ArrayList<Player> getTopPlayers() {
		 return nbaS.getTopPlayers();   // Καλεί τη μέθοδο getTopPlayers() από την υπηρεσία NBAService και επιστρέφει τη λίστα των παικτών.
	}

}
