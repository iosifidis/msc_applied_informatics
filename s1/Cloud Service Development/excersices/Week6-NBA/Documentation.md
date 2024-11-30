# Γενικές Οδηγίες Επίλυσης Παρόμοιων Προβλημάτων
Ακολουθούν αναλυτικές οδηγίες για το πώς να προσεγγίσετε την ανάπτυξη ενός πληροφοριακού συστήματος όπως το παραπάνω. Αυτές οι οδηγίες μπορούν να προσαρμοστούν και σε άλλα παρόμοια προβλήματα:

## 1. Κατανόηση Προβλήματος και Ανάλυση

Πριν ξεκινήσετε την υλοποίηση, πρέπει να κατανοήσετε πλήρως το πρόβλημα.

Ρωτήστε τον εαυτό σας:

- Ποια είναι τα βασικά αντικείμενα (entities) του συστήματος;   
- Ποιες σχέσεις συνδέουν αυτά τα αντικείμενα;   
- Ποια είναι η λειτουργικότητα που πρέπει να υλοποιηθεί (endpoints, λειτουργίες κ.λπ.);   

**Στο συγκεκριμένο πρόβλημα:**

- Οντότητες: `Team`, `Player`, `Stats`.   
- Σχέσεις:   
  - Μία ομάδα έχει πολλούς παίκτες (1-N).   
  - Ένας παίκτης έχει στατιστικά (1-1).   
- Λειτουργικότητα:   
  - Υπολογισμός κορυφαίων παικτών βάσει πόντων, ασίστ, ριμπάουντ, και συνολικών στατιστικών.   

## 2. Σχεδίαση Βάσης Δεδομένων

Ξεκινήστε με τον σχεδιασμό της βάσης δεδομένων (γενικά θα δίνεται ως δεδομένο):

- Ορίστε τα αντικείμενα ως πίνακες.   
- Ορίστε τα πεδία κάθε πίνακα.   
- Προσθέστε σχέσεις (π.χ. foreign keys).   

Παράδειγμα SQL για το πρόβλημα:

```
CREATE DATABASE NBA;

CREATE TABLE Team (
    Team_ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    City VARCHAR(50) NOT NULL
);

CREATE TABLE Player (
    Player_ID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Team_ID INT,
    FOREIGN KEY (Team_ID) REFERENCES Team(Team_ID)
);

CREATE TABLE Stats (
    Player_ID INT PRIMARY KEY,
    Points INT NOT NULL,
    Assists INT NOT NULL,
    Rebounds INT NOT NULL,
    FOREIGN KEY (Player_ID) REFERENCES Player(Player_ID)
);
```

Αφού θα συνδεθούμε με βάση δεδομένων, προσθέστε στο αρχείο `pom.xml` το παρακάτω κώδικα:

```
        <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.25</version>
		</dependency>
```

## 3. Δημιουργία Στρώματος Υπηρεσιών (Service Layer)

Η λογική του προβλήματος πρέπει να διαχωριστεί από τη βάση δεδομένων. Χρησιμοποιήστε ένα `Service` για να διαχειριστείτε τα δεδομένα στη μνήμη.

Σκεφτείτε:

- Πώς θα φορτώσετε δεδομένα από τη βάση;   
- Πώς θα οργανώσετε τα δεδομένα στη μνήμη για γρήγορη πρόσβαση;   
- Πώς θα εξυπηρετήσετε κάθε endpoint;   

Στο συγκεκριμένο πρόβλημα:   
Δημιουργήστε μια κλάση `NBAService` που:

- Περιέχει μια λίστα από `Team`.   
- Κάθε ομάδα περιέχει μια λίστα από `Player`.   
- Κάθε παίκτης περιέχει τα `Stats`.   

## 4. Υλοποίηση Κλάσεων

Δημιουργήστε κλάσεις που αντιπροσωπεύουν τα δεδομένα σας.

Παράδειγμα:
```
// Η NBA Application (δεν την πειράζουμε)
@SpringBootApplication
public class NbApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbApplication.class, args);
	}

}
======================================
// Οντότητα Team
public class Team {
	
	String name;
	String city;
	ArrayList<Player> pList = new ArrayList<Player>();
	
	
	public Team(String name, String city, ArrayList<Player> pList) {
		this.name = name;
		this.city = city;
		this.pList = pList;
	}

	public Team(String name, String city) {
		this.name = name;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public ArrayList<Player> getpList() {
		return pList;
	}
    // Μέθοδος addPlayer στην λίστα
	public void addPlayer(Player player) {
		pList.add(player);
	}
======================================
// Οντότητα Player
public class Player {
	String name;
	Statistics stats;
	
	public Player(String name, Statistics stats) {
		this.name = name;
		this.stats = stats;
	}
	public String getName() {
		return name;
	}
	public Statistics getStats() {
		return stats;
	}
}
======================================
// Οντότητα Stats
public class Statistics {
	
	int points;
	int rebounds;
	int assists;
	
	public Statistics(int points, int rebounds, int assists) {
		this.points = points;
		this.rebounds = rebounds;
		this.assists = assists;
	}

	public int getPoints() {
		return points;
	}

	public int getRebounds() {
		return rebounds;
	}

	public int getAssists() {
		return assists;
	}
}	
```

## 5. Εφαρμογή Business Logic (Service)

Η λογική για τα endpoints πρέπει να υλοποιηθεί στη `NBAService`.

Παράδειγμα Υλοποίησης Λειτουργικότητας:

```
@Service
public class NBAService {
	
	List<Team> tList = new ArrayList<Team>();
	
	public void setTeam(ArrayList<Team> t) {
		tList =t;	
	}
	
	public ArrayList<Player> getTopScorers() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		for(int i=0;i<tList.size();i++) {
				allPlayers.addAll(tList.get(i).getpList());
		}
		for(int i=0;i<allPlayers.size()-1;i++) {
			for(int j=i;j<allPlayers.size();j++) {
				if(allPlayers.get(j).getStats().getPoints() > allPlayers.get(i).getStats().getPoints()) {
					Player temp = allPlayers.get(j);
					allPlayers.set(j, allPlayers.get(i));
					allPlayers.set(i, temp);
				}
			}
		}
		return allPlayers;
	}
	
	public ArrayList<Player> getTopRebounders() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		for(int i=0;i<tList.size();i++) {
			allPlayers.addAll(tList.get(i).getpList());
		}
		for(int i=0;i<allPlayers.size()-1;i++) {
			for(int j=i;j<allPlayers.size();j++) {
				if(allPlayers.get(j).getStats().getRebounds() > allPlayers.get(i).getStats().getRebounds()) {
					Player temp = allPlayers.get(j);
					allPlayers.set(j, allPlayers.get(i));
					allPlayers.set(i, temp);
				}
			}
		}
		return allPlayers;
	}
	
	public ArrayList<Player> getTopPassers() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		for(int i=0;i<tList.size();i++) {
			allPlayers.addAll(tList.get(i).getpList());
		}
		for(int i=0;i<allPlayers.size()-1;i++) {
			for(int j=i;j<allPlayers.size();j++) {
				if(allPlayers.get(j).getStats().getAssists() > allPlayers.get(i).getStats().getAssists()) {
					Player temp = allPlayers.get(j);
					allPlayers.set(j, allPlayers.get(i));
					allPlayers.set(i, temp);
				}
			}
		}
		return allPlayers;
	}
	
	public ArrayList<Player> getTopPlayers() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		for(int i=0;i<tList.size();i++) {
			allPlayers.addAll(tList.get(i).getpList());
		}
		for(int i=0;i<allPlayers.size()-1;i++) {
			for(int j=i;j<allPlayers.size();j++) {
				if(allPlayers.get(j).getStats().getPoints()+allPlayers.get(j).getStats().getRebounds()+allPlayers.get(j).getStats().getAssists() > allPlayers.get(i).getStats().getPoints()+allPlayers.get(i).getStats().getRebounds()+allPlayers.get(i).getStats().getAssists()) {
					Player temp = allPlayers.get(j);
					allPlayers.set(j, allPlayers.get(i));
					allPlayers.set(i, temp);
				}
			}
		}
		return allPlayers;
	}
}

```

## 6. Δημιουργία Controller

Δημιουργήστε έναν Controller που συνδέει τη λογική του Service με τα endpoints.

Παράδειγμα:

```
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class NBAController {
	
	@Autowired
	private NBAService nbaS;
	
	@GetMapping(path="/topScorers")
	public ArrayList<Player> getTopScorers() {
		return nbaS.getTopScorers();
	}
	
	@GetMapping(path="/topRebounders")
	public ArrayList<Player> getTopRebounders() {
		return nbaS.getTopRebounders();
	}
	
	@GetMapping(path="/topPassers")
	public ArrayList<Player> getTopPassers() {
		return nbaS.getTopPassers();
	}
	
	@GetMapping(path="/topPlayers")
	public ArrayList<Player> getTopPlayers() {
		 return nbaS.getTopPlayers();
	}
}
```

## 7. Φόρτωση Δεδομένων από Βάση

Υλοποιήστε μια κλάση `NBAServiceConfig` που φορτώνει τα δεδομένα από τη βάση και τα αποθηκεύει στη μνήμη.

Παράδειγμα:

```
@Configuration
public class NBAServiceConfig implements CommandLineRunner {
	
	@Autowired
	private NBAService nbaS;
	
	public void run(String... args) throws Exception {
		nbaS.setTeam(loadFromDB());
		System.out.println("DB has been loaded to NBAService!!!");
	}

	private ArrayList<Team> loadFromDB() throws Exception {
		ArrayList<Team> tList = new ArrayList<Team>();
		ArrayList<Player> pList = new ArrayList<Player>();
		
	      // create our mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost/nba_db";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	      
	      String query = "SELECT * FROM teams";
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while(rs.next()) {
		        String name = rs.getString("name");
		        String city = rs.getString("city");
		        tList.add(new Team(name,city));
	      }
	      st.close();
	      
	      String query2 = "SELECT * FROM players,statistics where players.name = statistics.name";
	      Statement st1 = conn.createStatement();
	      ResultSet rs1 = st1.executeQuery(query2);
	      
	      while(rs1.next()) {
		        String name = rs1.getString("name");
		        String team = rs1.getString("team");
		        int points = rs1.getInt("points");
		        int assists = rs1.getInt("assists");
		        int rebounds = rs1.getInt("rebounds");
		        
		        int pos = 0;
		        for(int i=0;i<tList.size();i++) {
		        	if(tList.get(i).getName().equals(team)) {
		        		pos = i;
		        		break;
		        	}
		        }
		        
	   		  	tList.get(pos).addPlayer(new Player(name, new Statistics(points,rebounds,assists)));
	   	  }
	      st1.close();
		
		return tList;
	}
	
}
```

## 8. Front End

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NBA Stats</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <h1 id="header">Select Stats Category...</h1>

    <!-- Buttons to fetch stats -->
    <button onclick="showStats('topScorers')">Top Scorers</button>
    <button onclick="showStats('topRebounders')">Top Rebounders</button>
    <button onclick="showStats('topPassers')">Top Passers</button>
    <button onclick="showStats('topPlayers')">Top Players</button>

    <!-- Table to display stats -->
    <table border="1" id="resultsTable">
        <thead>
            <tr>
                <th>Name</th>
                <th>Points</th>
                <th>Rebounds</th>
                <th>Assists</th>
            </tr>
        </thead>
        <tbody>
            <!-- Data will be populated here -->
        </tbody>
    </table>

    <script>
        // Function to fetch and display stats
        function showStats(endpoint) {
            document.getElementById("header").innerText = `Showing: ${endpoint.replace('top', '')}`;
            $.ajax({
                url: `http://localhost:8080/${endpoint}`,
                method: "GET",
                success: function (response) {
                    console.log("Response from server:", response); // Log the response for debugging
                    updateTable(response);
                },
                error: function () {
                    alert("Failed to fetch data. Please check the server or endpoint URL.");
                }
            });
        }

        // Function to update the table with stats
        function updateTable(json) {
            const result = Array.isArray(json) ? json : JSON.parse(json); // Ensure JSON is parsed
            const tableBody = document.getElementById("resultsTable").getElementsByTagName("tbody")[0];

            // Clear existing rows
            tableBody.innerHTML = "";

            // Populate table with new data
            result.forEach(player => {
                const row = tableBody.insertRow();
                const nameCell = row.insertCell(0);
                const pointsCell = row.insertCell(1);
                const reboundsCell = row.insertCell(2);
                const assistsCell = row.insertCell(3);

                nameCell.innerText = player.name;
                pointsCell.innerText = player.stats.points || "N/A";
                reboundsCell.innerText = player.stats.rebounds || "N/A";
                assistsCell.innerText = player.stats.assists || "N/A";
            });
        }
    </script>
</body>
</html>
```


