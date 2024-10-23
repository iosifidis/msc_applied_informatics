
public class Main {

	public static void main(String[] args) {
		
		// Ορίζω τον σύνδεσμο όπου βρίσκεται η υπηρεσία
        String url= "http://localhost/msc/week2/getSLChampion.php";

        // Χειρισμός σφάλματος με try/catch
        try {
        	// Δημιουργώ αντικείμενο HttpHandler
            HttpHandler httpHandler = new HttpHandler();
            // Από το αντικείμενο εκτελώ την μέθοδο getResponse για να πάρω το αντικείμενο ChampionsList
            ChampionsList cList = httpHandler.getResponse(url);
            
            // Εκτυπώνω το αποτέλεσμα, χρησιμοποιώντας την μέθοδο printInfo επί του αντικειμένου cList
            cList.printInfo();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}