import org.json.*;

public class MainClass {

	public static void main(String[] args) {
		String url= "https://reqres.in/api/users/2";
		
		
		try {
			// Δημιουργώ ένα αντικείμενο handler για να πάρω τα αποτελέσματα
			HttpHandler handler = new HttpHandler();
			
			// Έχει τοποθετήσει όλο το αποτέλεσμα της απάντησης
			String myResponse = handler.getResponse(url);
			//System.out.println(myResponse);
			
			// Πρέπει να σπάσω το JSON Stream
			JSONObject json = new JSONObject(myResponse);
			
			// Πρέπει από το key data που βρίσκεται το όνομα και επίθετο, να πάρω την τιμή του.
			// Από το την τιμή του data πρέπει να πάρω τα πεδία first_name, last_name και να τα ενώσω
			// Από το αντικείμενο JSONObject json, εισάγω το όρισμα “data” 
			// και από αυτό λαμβάνω με getString το κλειδί first_name. 
			// Το ίδιο κάνω και με το key του last_name.
			String fullname = json.getJSONObject("data").getString("first_name") 
											+ " " +
										json.getJSONObject("data").getString("last_name");
						
			System.out.println("Parsed Name: " + fullname);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
