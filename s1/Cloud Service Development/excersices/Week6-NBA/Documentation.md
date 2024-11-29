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
// Οντότητα Team
public class Team {
    private int teamId;
    private String name;
    private String city;
    private List<Player> players;

    // Constructors, Getters, Setters
}

// Οντότητα Player
public class Player {
    private int playerId;
    private String name;
    private Stats stats;

    // Constructors, Getters, Setters
}

// Οντότητα Stats
public class Stats {
    private int points;
    private int assists;
    private int rebounds;

    // Constructors, Getters, Setters
}
```

## 5. Εφαρμογή Business Logic

Η λογική για τα endpoints πρέπει να υλοποιηθεί στη `NBAService`.

Παράδειγμα Υλοποίησης Λειτουργικότητας:

```
public class NBAService {
    private List<Team> teams;

    public NBAService(List<Team> teams) {
        this.teams = teams;
    }

    public List<Player> getTopScorers() {
        return teams.stream()
            .flatMap(team -> team.getPlayers().stream())
            .sorted((p1, p2) -> Integer.compare(p2.getStats().getPoints(), p1.getStats().getPoints()))
            .limit(10)
            .collect(Collectors.toList());
    }

    // Παρόμοια για getTopRebounders, getTopPassers

    public List<Player> getTopPlayers() {
        return teams.stream()
            .flatMap(team -> team.getPlayers().stream())
            .sorted((p1, p2) -> Integer.compare(
                (p2.getStats().getPoints() + p2.getStats().getAssists() + p2.getStats().getRebounds()),
                (p1.getStats().getPoints() + p1.getStats().getAssists() + p1.getStats().getRebounds())
            ))
            .limit(10)
            .collect(Collectors.toList());
    }
}
```

## 6. Δημιουργία Controller

Δημιουργήστε έναν Controller που συνδέει τη λογική του Service με τα endpoints.

Παράδειγμα:

```
@RestController
@RequestMapping("/nba")
public class NBAController {
    private final NBAService nbaService;

    @Autowired
    public NBAController(NBAService nbaService) {
        this.nbaService = nbaService;
    }

    @GetMapping("/top-scorers")
    public List<Player> getTopScorers() {
        return nbaService.getTopScorers();
    }

    @GetMapping("/top-rebounders")
    public List<Player> getTopRebounders() {
        return nbaService.getTopRebounders();
    }

    @GetMapping("/top-passers")
    public List<Player> getTopPassers() {
        return nbaService.getTopPassers();
    }

    @GetMapping("/top-players")
    public List<Player> getTopPlayers() {
        return nbaService.getTopPlayers();
    }
}
```

## 7. Φόρτωση Δεδομένων από Βάση

Υλοποιήστε μια κλάση `NBAServiceConfig` που φορτώνει τα δεδομένα από τη βάση και τα αποθηκεύει στη μνήμη.

Παράδειγμα:

```
@Component
public class NBAServiceConfig {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public NBAService nbaService() {
        List<Team> teams = jdbcTemplate.query("SELECT * FROM Team", (rs, rowNum) -> {
            Team team = new Team();
            team.setTeamId(rs.getInt("Team_ID"));
            team.setName(rs.getString("Name"));
            team.setCity(rs.getString("City"));

            List<Player> players = jdbcTemplate.query(
                "SELECT * FROM Player WHERE Team_ID = ?",
                new Object[]{team.getTeamId()},
                (playerRs, playerRowNum) -> {
                    Player player = new Player();
                    player.setPlayerId(playerRs.getInt("Player_ID"));
                    player.setName(playerRs.getString("Name"));

                    Stats stats = jdbcTemplate.queryForObject(
                        "SELECT * FROM Stats WHERE Player_ID = ?",
                        new Object[]{player.getPlayerId()},
                        (statsRs, statsRowNum) -> new Stats(
                            statsRs.getInt("Points"),
                            statsRs.getInt("Assists"),
                            statsRs.getInt("Rebounds")
                        )
                    );
                    player.setStats(stats);
                    return player;
                }
            );
            team.setPlayers(players);
            return team;
        });

        return new NBAService(teams);
    }
}
```

## 8. Τεστ και Βελτιστοποίηση

Δοκιμάστε τη λειτουργικότητα:

- Δημιουργήστε δεδομένα για δοκιμή.   
- Ελέγξτε αν τα endpoints επιστρέφουν σωστά αποτελέσματα.   
- Χρησιμοποιήστε εργαλεία όπως Postman για API Testing.   

Με αυτές τις οδηγίες, μπορείτε να σχεδιάσετε και να υλοποιήσετε παρόμοια πληροφοριακά συστήματα.
