package gr.uom.nba_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration   // Δηλώνει αυτή την κλάση ως configuration class για να χρησιμοποιηθεί από το Spring για τη διαχείριση των beans.
public class NBAServiceConfig implements CommandLineRunner {   // Υλοποιεί την interface CommandLineRunner για να τρέξει τον κώδικα μόλις ξεκινήσει η εφαρμογή.
	
	@Autowired   // Εισάγουμε την υπηρεσία NBAService για να μπορούμε να τη χρησιμοποιήσουμε μέσα στην κλάση.
	private NBAService nbaS;
	
	// Η μέθοδος run() τρέχει μόλις ξεκινήσει η εφαρμογή και φορτώνει τις ομάδες και τους παίκτες από την βάση δεδομένων.
	public void run(String... args) throws Exception {
		nbaS.setTeam(loadFromDB());   // Καλεί τη μέθοδο loadFromDB() για να φορτώσει τα δεδομένα και να τα περάσει στην υπηρεσία NBAService.
		System.out.println("DB has been loaded to NBAService!!!");   // Ενημερώνει το κονσόλα ότι τα δεδομένα έχουν φορτωθεί στην υπηρεσία.
	}

	// Η μέθοδος αυτή φορτώνει τα δεδομένα των ομάδων και των παικτών από την βάση δεδομένων.
	private ArrayList<Team> loadFromDB() throws Exception {
		ArrayList<Team> tList = new ArrayList<Team>();  // Δημιουργούμε έναν κατάλογο για τις ομάδες.
		ArrayList<Player> pList = new ArrayList<Player>();  // Δημιουργούμε έναν κατάλογο για τους παίκτες (χρησιμοποιείται για την αποθήκευση των παικτών από κάθε ομάδα).
		
		  // Δημιουργία σύνδεσης με την MySQL βάση δεδομένων.
	      String myDriver = "org.gjt.mm.mysql.Driver";  // Ο οδηγός της βάσης δεδομένων.
	      String myUrl = "jdbc:mysql://localhost/nba_db";   // Η διεύθυνση της βάσης δεδομένων.
	      Class.forName(myDriver);   // Φορτώνει τον οδηγό της βάσης δεδομένων.
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");   // Δημιουργεί τη σύνδεση με τη βάση δεδομένων.
	      
  	      // Εκτελούμε το πρώτο query για να πάρουμε τις ομάδες από τη βάση δεδομένων.
	      String query = "SELECT * FROM teams";
	      Statement st = conn.createStatement(); // Δημιουργούμε ένα Statement για την εκτέλεση του query.
	      ResultSet rs = st.executeQuery(query); // Εκτελούμε το query και παίρνουμε τα αποτελέσματα.
	      
	   // Επεξεργαζόμαστε το αποτέλεσμα και προσθέτουμε τις ομάδες στη λίστα tList.
	      while(rs.next()) {
		        String name = rs.getString("name");  // Παίρνουμε το όνομα της ομάδας.
		        String city = rs.getString("city");   // Παίρνουμε την πόλη της ομάδας.
		        tList.add(new Team(name,city));   // Δημιουργούμε νέο αντικείμενο Team και το προσθέτουμε στην λίστα.
	      }
	      st.close(); // Κλείνουμε το Statement για την πρώτη ερώτηση.
	      
	   // Εκτελούμε το δεύτερο query για να πάρουμε τους παίκτες και τα στατιστικά τους από τη βάση δεδομένων.
	      String query2 = "SELECT * FROM players,statistics where players.name = statistics.name";
	      Statement st1 = conn.createStatement();  // Δημιουργούμε ένα Statement για την εκτέλεση του δεύτερου query.
	      ResultSet rs1 = st1.executeQuery(query2);   // Εκτελούμε το δεύτερο query και παίρνουμε τα αποτελέσματα.
	      
	   // Επεξεργαζόμαστε το αποτέλεσμα για τους παίκτες και προσθέτουμε τους στην αντίστοιχη ομάδα.
	      while(rs1.next()) {
	    	  	String name = rs1.getString("name"); // Παίρνουμε το όνομα του παίκτη.
		        String team = rs1.getString("team"); // Παίρνουμε το όνομα της ομάδας του παίκτη.
		        int points = rs1.getInt("points"); // Παίρνουμε τους πόντους του παίκτη.
		        int assists = rs1.getInt("assists"); // Παίρνουμε τις ασίστ του παίκτη.
		        int rebounds = rs1.getInt("rebounds"); // Παίρνουμε τα rebound του παίκτη.
		        
		        // Βρίσκουμε την ομάδα στην οποία ανήκει ο παίκτης.
		        int pos = 0;
		        for(int i=0;i<tList.size();i++) {
		        	if(tList.get(i).getName().equals(team)) { // Συγκρίνουμε το όνομα της ομάδας με το όνομα της ομάδας του παίκτη.
		        		pos = i; // Αν βρούμε την ομάδα, αποθηκεύουμε τη θέση της.
		        		break;
		        	}
		        }
		        
		     // Προσθέτουμε τον παίκτη στην αντίστοιχη ομάδα με τα στατιστικά του.
	   		  	tList.get(pos).addPlayer(new Player(name, new Statistics(points,rebounds,assists)));
	   	  }
	      st1.close(); // Κλείνουμε το Statement για το δεύτερο query.
		
		return tList;  // Επιστρέφουμε τη λίστα των ομάδων που περιέχουν και τους παίκτες τους.
	}
	
}
