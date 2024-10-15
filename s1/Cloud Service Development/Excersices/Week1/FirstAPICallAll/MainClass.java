
public class MainClass {

	public static void main(String[] args) {
		
		String url= "https://reqres.in/api/users/";
	
		try {
			// Δημιουργώ ένα αντικείμενο handler για να πάρω τα αποτελέσματα
			HttpHandler handler = new HttpHandler();
			
		
			// Παίρνει τον αριθμό των εγγραφών για να το χρησιμοποιήσει στην δομή επανάληψης.
			int length = handler.getLength(url);
						
			// Δομή επανάληψης για να εμφανίσει όλες τις εγγραφές
			for (int i=0; i<length; i++) {
				String myResponse = handler.getResponse(url, i);
				System.out.println("Name: " + myResponse);
			}
		
		}catch (Exception e) {
	            e.printStackTrace();
	        }

	}

}
