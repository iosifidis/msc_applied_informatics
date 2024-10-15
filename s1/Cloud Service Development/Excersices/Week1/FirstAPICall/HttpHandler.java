import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class HttpHandler {

	public String getResponse(String url) throws Exception {
		// Tο throws Exception τα εισάγω από το σφάλμα που βρίσκει στο Unirest.
		
		
		// Τον Κώδικα αυτόν τον πήρα από το Postman.
		
		// Δεν θα δώσει timeout ποτέ. 
		Unirest.setTimeouts(0, 0);
				
		// Εδώ μπορεί να γίνει μια κλήση στο url και μπορεί να είναι κλειστός ο server και να δώσει σφάλμα (exception). Χρειάζεται διαχείριση
		// Μπορούμε να δώσουμε try>catch
		HttpResponse<String> response = Unirest.get(url).asString(); 
		// get είναι static. Γι'αυτό είναι με italic. 
		// Γενικά μια μέθοδος χρειάζεται ένα αντικείμενο για να την καλέσω. Εδώ ΔΕΝ φτιάχνω αντικείμενο για να την καλέσω.
		
		String data = response.getBody();    // Από το response πάρε μόνο το body.
		
		// Επιστρέφει τα δεδομένα του σώματος
		return data;
	}
}
