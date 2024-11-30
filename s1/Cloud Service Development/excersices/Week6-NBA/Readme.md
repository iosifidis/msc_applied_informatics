# Δημιουργία Εφαρμογής Διαχείρισης NBA

Θα δημιουργήσετε ένα σύστημα NBA Management, το οποίο θα ανακτά και θα επεξεργάζεται δεδομένα από μια βάση δεδομένων MySQL. Η βάση δεδομένων θα φιλοξενείται στο XAMPP και θα περιέχει δεδομένα για ομάδες, παίκτες και στατιστικά. Σκοπός είναι να δημιουργήσετε ένα REST API για την ανάκτηση των κορυφαίων παικτών σύμφωνα με διαφορετικά στατιστικά κριτήρια.

<hr>

## Απαιτήσεις Άσκησης

### 1. Βάση Δεδομένων

Χρησιμοποιήστε τον παρακάτω SQL κώδικα για να δημιουργήσετε τη βάση δεδομένων στον XAMPP (MySQL).

Η βάση θα περιέχει τους εξής πίνακες:   
- `players`: Πληροφορίες για παίκτες και τις ομάδες τους.   
- `teams`: Πληροφορίες για τις ομάδες και την πόλη τους.   
- `statistics`: Στατιστικά για κάθε παίκτη.   

```
-- Δημιουργία βάσης δεδομένων και πινάκων
CREATE DATABASE IF NOT EXISTS nba_db;

USE nba_db;

CREATE TABLE `players` (
  `Name` varchar(100) NOT NULL,
  `Team` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `statistics` (
  `Name` varchar(100) NOT NULL,
  `points` int(11) NOT NULL,
  `rebounds` int(11) NOT NULL,
  `assists` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `teams` (
  `Name` varchar(100) NOT NULL,
  `City` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Προσθήκη δεδομένων
INSERT INTO `players` (`Name`, `Team`) VALUES
('Larry Bird', 'Celtics'),
('Kevin McHale', 'Celtics'),
('Robert Parish', 'Celtics'),
('Dennis Johnson', 'Celtics'),
('Sam Vincent', 'Celtics'),
('Magic Johnson', 'Lakers'),
('James Worthy', 'Lakers'),
('Kareem Abdul Jabbar', 'Lakers'),
('Byron Scott', 'Lakers'),
('Bob McAdoo', 'Lakers');

INSERT INTO `statistics` (`Name`, `points`, `rebounds`, `assists`) VALUES
('Magic Johnson', 1800, 1500, 900),
('Byron Scott', 1256, 654, 123),
('James Worthy', 1223, 1233, 156),
('Bob McAdoo', 327, 2345, 18),
('Kareem Abdul Jabbar', 2346, 2222, 22),
('Sam Vincent', 686, 233, 789),
('Dennis Johnson', 2300, 453, 678),
('Larry Bird', 2800, 1865, 1065),
('Kevin McHale', 3112, 1980, 123),
('Robert Parish', 896, 2531, 122);

INSERT INTO `teams` (`Name`, `City`) VALUES
('Lakers', 'Los Angeles'),
('Celtics', 'Boston');

```

### 2. Υλοποίηση RESTful API

- Back-End Framework: Χρησιμοποιήστε Spring Boot για την ανάπτυξη του API.   
- Database Connection: Συνδέστε το σύστημα με τη MySQL βάση δεδομένων που δημιουργήσατε στον XAMPP.   
- REST API Endpoints: Δημιουργήστε έναν Controller με τα εξής endpoints:   
  1. `getTopScorers`: Επιστρέφει τους παίκτες με τους περισσότερους πόντους.   
  2. `getTopRebounders`: Επιστρέφει τους παίκτες με τα περισσότερα ριμπάουντ.   
  3. `getTopPassers`: Επιστρέφει τους παίκτες με τις περισσότερες ασίστ.   
  4. `getTopPlayers`: Επιστρέφει τους κορυφαίους παίκτες με βάση το άθροισμα πόντων, ριμπάουντ και ασίστ (points + rebounds + assists).   

- Δημιουργία Υπηρεσίας: Δημιουργήστε την κλάση NBAService:   
  - Η κλάση πρέπει να περιέχει μια λίστα από ομάδες (Teams).   
  - Κάθε ομάδα (Team) περιέχει τους παίκτες της (Players).   
  - Κάθε παίκτης (Player) περιέχει τα στατιστικά του (Stats).   
  - Υλοποιήστε τους 4 endpoints ώστε να επιστρέφουν αποτελέσματα χρησιμοποιώντας μόνο τα δεδομένα στη μνήμη, χωρίς κλήσεις στη βάση δεδομένων.   

- Διαχείριση Δεδομένων: Δημιουργήστε την κλάση NBAServiceConfig:   
  - Η κλάση πρέπει να φορτώνει τα δεδομένα από τη βάση κατά την εκκίνηση της εφαρμογής.   
  - Τα δεδομένα της βάσης πρέπει να μεταφέρονται στη μνήμη και να αποθηκεύονται στην `NBAService`.   

- Front-End Requirements:

  - Δημιουργήστε ένα απλό HTML/JavaScript Front-End για την εμφάνιση των δεδομένων.   
  - Προδιαγραφές Front-End:   

    - Ένας τίτλος (Header) για την κατηγορία στατιστικών. Αρχική τιμή: “Select Stats Category…”.   
    - Ένας πίνακας για την εμφάνιση των δεδομένων.   
    - Τέσσερα κουμπιά που καλούν τα παραπάνω endpoints:   
        - `Top Scorers`   
        - `Top Rebounders`   
        - `Top Passers`   
        - `Top Players`   

    - Με το πάτημα κάθε κουμπιού, τα δεδομένα που επιστρέφονται από το αντίστοιχο endpoint να εμφανίζονται στον πίνακα.

## Οδηγίες Υλοποίησης

1. Ρύθμιση Spring Boot Project:

  - Δημιουργήστε ένα Spring Boot project με το Maven.  
  - Προσθέστε εξαρτήσεις για MySQL (mysql-connector-java) και Spring Data JPA.   

2. Κλάσεις:

  - Δημιουργήστε τις κλάσεις `Player`, `Team`, και `Statistics` ως Java Entities.

3. Υπηρεσία (Service):

  - Δημιουργήστε την υπηρεσία `NBAService` που θα επικοινωνεί με τη βάση και θα εξυπηρετεί τα requests.

4. Ελεγκτής (Controller):

  - Δημιουργήστε τον `NBAController` για να υλοποιήσετε τα απαιτούμενα endpoints.
  
5. Configuration (Config):

  - Δημιουργήστε το `NBAServiceConfig` για να φορτώνει τις εγγραφές από τη βάση δεδομένων και τις αποθηκεύει στη μνήμη μέσω της Service.   

6. Αρχείο `pom.xml`:

  - Προσθέστε στο αρχείο `pom.xml` το παρακάτω για την σύνδεση με την βάση δεδομένων:

```
        <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.25</version>
		</dependency>
```

7. Front-End Υλοποίηση:

  - Δημιουργήστε ένα αρχείο index.html με την εξής βασική δομή:

```
<!DOCTYPE html>
<html>
<head>
    <title>NBA Stats</title>
    <script>
        function fetchData(endpoint) {
            fetch(endpoint)
                .then(response => response.json())
                .then(data => populateTable(data))
                .catch(err => console.error(err));
        }

        function populateTable(data) {
            const table = document.getElementById("statsTable");
            table.innerHTML = ""; // Clear previous data
            data.forEach(player => {
                const row = table.insertRow();
                Object.values(player).forEach(val => {
                    const cell = row.insertCell();
                    cell.textContent = val;
                });
            });
        }
    </script>
</head>
<body>
    <h1>Select Stats Category...</h1>
    <button onclick="fetchData('/topScorers')">Top Scorers</button>
    <button onclick="fetchData('/topRebounders')">Top Rebounders</button>
    <button onclick="fetchData('/topPassers')">Top Passers</button>
    <button onclick="fetchData('/topPlayers')">Top Players</button>
    <table id="statsTable" border="1">
        <thead>
            <tr>
                <th>Name</th>
                <th>Points</th>
                <th>Rebounds</th>
                <th>Assists</th>
            </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</body>
</html>

```




