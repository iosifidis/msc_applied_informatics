import java.util.Iterator;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class HttpHandler {
	
	public ChampionsList getResponse(String url) throws Exception {

		// Δημιουργούμε μια αντικείμενο από την κλάση ChampionsList
		ChampionsList cList = new ChampionsList();
	
		// Τον κώδικα, τον λαμβάνουμε από το Postman
		// Επειδή μπορεί να δίνει σφάλμα αν δεν υπάρχει η url, εισάγουμε το throws Exception στην getResponse
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.get(url).asString();
	
		// Λαμβάνουμε το JSON string και το μετατρέπουμε σε αντικείμενο JSONObject για ευκολότερη διαχείριση.
		String data = response.getBody();
		JSONObject json = new JSONObject(data);
		
		// Με μια while να πάρουμε μια μια τις εγγραφές από το JSONObject που λάβαμε ως απόκριση.
		Iterator<String> keys = json.keys();
		// Όσο το κλειδί έχει επόμενο (hasNext)
		while(keys.hasNext()) {
			// Εξάγουμε τον χρόνο και το έμβλημα
			String key = keys.next();
			String year = json.getJSONObject(key).getString("year").toString();
			String emblem = json.getJSONObject(key).getString("image").toString();
			
			// Δημιουργώ αντικείμενο Champion με τα δεδομένα από την απόκριση 
			// και το προσθέτω στην λίστα ChampionsList
			cList.addChampion(new Champion(key, year, emblem));
		}
		return cList;
	}
}
