# ΟΔΗΓΙΕΣ

1. Ξεκινάμε νέο project από τη διεύθυνση: [https://start.spring.io/](https://start.spring.io/). Φροντίζουμε να έχει τα εξής:

- Maven   
- Java   
- Spring Boot 3.4.0   
- Packaging JAR   
- Java 17 ή 21
- Dependencies   
  - Spring Web   
  
2. Αφού το εισάγουμε στο Eclipse πρέπει να περιμένουμε ώστε να κατεβάσει όλα τα απαραίτητα και να φύγουν τυχόν κόκκινα.  

3. Εάν είναι να κάνουμε σύνδεση με mySQL τότε στο αρχείο `pom.xml` πρέπει να προσθέσουμε τον παρακάτω κώδικα:

```
        <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.25</version>
		</dependency>
```

4. Ανάλογα με την εκφώνηση, δημιουργήστε τις κλάσεις με τις ιδιότητες και ΜΟΝΟ getters.   

5. Δημιουργήστε την κλάση Service (πχ NBAService) και υλοποιήστε την λογική της εφαρμογής με τα endpoints.

Χρησιμοποιούμε το annotation **@Service**.  

Οι πρώτες γραμμές θα είναι δημιουργία μιας λίστας και set για να αποθηκεύσω την λίστα με τα αντικείμενα:  

```
List<Team> tList = new ArrayList<Team>();
	
	public void setTeam(ArrayList<Team> t) {
		tList =t;	
	}
```

στην συνέχεια γίνεται η υλοποίηση των endpoints, πχ θέλω να πάρω μια λίστα με topscorers (έτσι λέγεται το endpoint)

```
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
```

5. Δημιουργήστε έναν Controller που συνδέει τη λογική του Service με τα endpoints.

Χρησιμοποιούμε τα annotation:

```
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
```

Πρώτη γραμμή, δημιουργούμε ένα Service που θα χρησιμοποιήσουμε συνέχεια. Χρησιμοποιούμε και το annotation **@Autowired**. Το όνομα του service είναι όπως το δημιουργήσαμε στο βήμα 4.

```
@Autowired
private NBAService nbaS;
```

Στη συνέχεια χρησιμοποιούμε το annoitation **@GetMapping** δηλώνοντας το path του endpoint. ΠΧ @GetMapping(path="/topScorers"). Εκεί δηλώνουμε ότι θα επιστραφεί από το service η μέθοδος που υλοποιήσαμε για το getTopScorers.

```
	@GetMapping(path="/topScorers")
	public ArrayList<Player> getTopScorers() {
		return nbaS.getTopScorers();
	}
```

6. Υλοποιήστε μια κλάση `NBAServiceConfig` που φορτώνει τα δεδομένα από τη βάση και τα αποθηκεύει στη μνήμη.

Αρχικά δηλώνουμε το annotation **@Configuration**.  

Πρώτη γραμμή, δημιουργούμε ένα Service που θα χρησιμοποιήσουμε συνέχεια. Χρησιμοποιούμε και το annotation **@Autowired**. Το όνομα του service είναι όπως το δημιουργήσαμε στο βήμα 4.

```
@Autowired
private NBAService nbaS;
```

Στην αρχή η κλάση θα δηλωθεί:

```
public class NBAServiceConfig implements CommandLineRunner
```

όμως η **NBAServiceConfig** θα βγάλει σφάλμα γιατί θέλει υλοποίηση:

```
public void run(String... args) throws Exception {
		nbaS.setTeam(loadFromDB());
		System.out.println("DB has been loaded to NBAService!!!");
	}
```

την **loadFromDB()** θα βγάλει σφάλμα γιατί πρέπει να την υλοποιήσουμε παρακάτω:

```
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
```

Εάν υπήρχε περίπτωση να γράψω στην βάση δεδομένων (παράδειγμα με Students), τότε στην **Service** προσθέτω κάτι του στυλ:

```
    public void addStudent(String name, int age, String location) throws Exception {
        getStudents();
        sList.add(new Student(name, age, location));

        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/university";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");

        String q = "insert into student values (?,?,?)";
        PreparedStatement preparedStmt = conn.prepareStatement(q);
        preparedStmt.setString(1, name);
        preparedStmt.setInt(2, age);
        preparedStmt.setString(3, location);
        preparedStmt.execute();
    }
```
