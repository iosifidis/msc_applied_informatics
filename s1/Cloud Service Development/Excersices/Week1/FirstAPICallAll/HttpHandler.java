import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class HttpHandler {
	
	public String getResponse(String url, int i) throws Exception {
		// Tο throws Exception τα εισάγω από το σφάλμα που βρίσκει στο Unirest.
		
		// Τον Κώδικα αυτόν τον πήρα από το Postman.
				
		// Δεν θα δώσει timeout ποτέ. 
		Unirest.setTimeouts(0, 0);
						
		// Εδώ μπορεί να γίνει μια κλήση στο url και μπορεί να είναι κλειστός ο server και να δώσει σφάλμα (exception). Χρειάζεται διαχείριση
		// Μπορούμε να δώσουμε try>catch
		HttpResponse<String> response = Unirest.get(url).asString();
		
		// Από το response πάρε μόνο το body. 
		String data = response.getBody();
		
		// Parse τα δεδομένα JSON
		JSONObject json = new JSONObject(data);
		
		// Λήψη του πίνακα "data" array
		JSONArray people = json.getJSONArray("data");
		
		// Λήψη του αντικειμένου νο i
		JSONObject person = (JSONObject) people.get(i);
		
		// Εξάγετε το όνομα και το επίθετο και σύνδεση σε μια μεταβλητή
		String fullname = person.getString("first_name") 
				+ " " +
				person.getString("last_name");
		
		
		return fullname;
		
	}

	public int getLength(String url) throws Exception {
		
		// Ορίζω μια μεταβλητή που θα κρατάει τον αριθμό των εγγραφών που επιστρέφει η κλήση.
		String total = "0";
		
		// Δεν θα δώσει timeout ποτέ. 
				Unirest.setTimeouts(0, 0);
								
		// Εδώ μπορεί να γίνει μια κλήση στο url και μπορεί να είναι κλειστός ο server και να δώσει σφάλμα (exception). Χρειάζεται διαχείριση
		// Μπορούμε να δώσουμε try>catch
		HttpResponse<String> response = Unirest.get(url).asString();
			
		// Από το response πάρε μόνο το body. 
		String data = response.getBody();
		
		// Parse να πάρω τα αποτελέσματα από τα δεδομένα που έχουν επιστραφεί από την κλήση
		JSONObject json = new JSONObject(data);
				
		// Παίρνω τον αριθμό των εγγραφών που περιμένω να έρθουν
		total = json.get("per_page").toString();
		
		// Εκτύπωση πόσοι επεστράφησαν
		System.out.println("Length=" + total);
				
		// Η μέθοδος επιστρέφει τον αριθμό των εγγραφών (το κάνει σε int γιατί επάνω το όρισε ως string)
		return Integer.parseInt(total);
		
	}
	
}
