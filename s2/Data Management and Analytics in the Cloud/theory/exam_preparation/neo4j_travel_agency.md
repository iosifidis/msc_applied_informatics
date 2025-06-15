# Α. Μοντελοποίηση σε Neo4j για το Ταξιδιωτικό Γραφείο

### **Μοντελοποίηση Βάσης Δεδομένων για Ταξιδιωτικό Γραφείο σε Neo4j**

Για τη μοντελοποίηση του ταξιδιωτικού γραφείου σε Neo4j, θα αναπαραστήσουμε τις οντότητες ως **κόμβους (Nodes)** και τις συνδέσεις μεταξύ τους ως **σχέσεις (Relationships)**. Κάθε κόμβος και σχέση θα έχουν **ιδιότητες (Properties)** που περιγράφουν τα χαρακτηριστικά τους. Αυτό το μοντέλο είναι ευέλικτο και βελτιστοποιημένο για την αποτελεσματική απάντηση των ερωτήσεων που θέσατε, καθώς και για την εξερεύνηση πολύπλοκων συνδέσεων μεταξύ των δεδομένων.

#### **Κόμβοι (Nodes) και οι Ιδιότητές τους:**

1.  **`Customer` (Πελάτης):** Αναπαριστά τους πελάτες του γραφείου.
    *   `customerId`: string (Μοναδικό αναγνωριστικό πελάτη, π.χ. 'C001')   
    *   `firstName`: string (Όνομα)   
    *   `lastName`: string (Επώνυμο)   
    *   `email`: string (Διεύθυνση email)   
    *   `phone`: string (Τηλέφωνο)   

2.  **`Booking` (Κράτηση):** Αναπαριστά μία ολοκληρωμένη κράτηση που έχει κάνει ένας πελάτης, η οποία μπορεί να περιλαμβάνει διάφορες υπηρεσίες (πτήσεις, ξενοδοχεία κλπ.).
    *   `bookingId`: string (Μοναδικό αναγνωριστικό κράτησης, π.χ. 'B001')   
    *   `bookingDate`: date (Ημερομηνία δημιουργίας κράτησης)   
    *   `startDate`: date (Ημερομηνία έναρξης του ταξιδιού/πακέτου αυτής της κράτησης)   
    *   `endDate`: date (Ημερομηνία λήξης του ταξιδιού/πακέτου αυτής της κράτησης)   
    *   `totalPrice`: float (Συνολικό κόστος κράτησης)   
    *   `status`: string (Κατάσταση κράτησης, π.χ., 'Confirmed', 'Pending', 'Cancelled')   

3.  **`Flight` (Πτήση):** Αναπαριστά μία συγκεκριμένη πτήση.
    *   `flightId`: string (Μοναδικό αναγνωριστικό πτήσης, π.χ. 'F001')   
    *   `flightNumber`: string (Αριθμός πτήσης, π.χ., 'A3620')   
    *   `departureDateTime`: datetime (Ημερομηνία και ώρα αναχώρησης)   
    *   `arrivalDateTime`: datetime (Ημερομηνία και ώρα άφιξης)   
    *   `price`: float (Τιμή εισιτηρίου ανά άτομο για αυτή την πτήση)   
    *   `airline`: string (Αεροπορική εταιρεία)   
    *   `durationMinutes`: int (Διάρκεια πτήσης σε λεπτά)   

4.  **`Hotel` (Ξενοδοχείο):** Αναπαριστά ένα συγκεκριμένο ξενοδοχείο.
    *   `hotelId`: string (Μοναδικό αναγνωριστικό ξενοδοχείου, π.χ. 'H001')   
    *   `name`: string (Όνομα ξενοδοχείου)   
    *   `starRating`: int (Αστέρια ξενοδοχείου, 1-5)   
    *   `address`: string (Διεύθυνση ξενοδοχείου)   
    *   `pricePerNight`: float (Τιμή ανά διανυκτέρευση για ένα στάνταρ δωμάτιο)   
    *   `amenities`: listOfStrings (Παροχές, π.χ., ['Pool', 'WiFi', 'Parking'])   

5.  **`RentalCar` (Ενοικιαζόμενο Αυτοκίνητο):** Αναπαριστά ένα συγκεκριμένο ενοικιαζόμενο αυτοκίνητο.
    *   `carId`: string (Μοναδικό αναγνωριστικό αυτοκινήτου, π.χ. 'CAR001')   
    *   `make`: string (Κατασκευαστής)   
    *   `model`: string (Μοντέλο)   
    *   `year`: int (Έτος κατασκευής)   
    *   `type`: string (Τύπος αυτοκινήτου, π.χ., 'Compact', 'SUV', 'Luxury')   
    *   `dailyRate`: float (Ημερήσια χρέωση ενοικίασης)   
    *   `licensePlate`: string (Αριθμός κυκλοφορίας)   

6.  **`Excursion` (Εκδρομή):** Αναπαριστά μία οργανωμένη εκδρομή.
    *   `excursionId`: string (Μοναδικό αναγνωριστικό εκδρομής, π.χ. 'E001')   
    *   `name`: string (Όνομα εκδρομής)   
    *   `description`: string (Περιγραφή εκδρομής)   
    *   `durationHours`: float (Διάρκεια εκδρομής σε ώρες)   
    *   `price`: float (Τιμή ανά άτομο για την εκδρομή)   
    *   `excursionDate`: date (Ημερομηνία εκδρομής)   
    *   `capacity`: int (Μέγιστη χωρητικότητα συμμετεχόντων)   

7.  **`TourGuide` (Ξεναγός):** Αναπαριστά έναν ξεναγό που αναλαμβάνει εκδρομές.
    *   `guideId`: string (Μοναδικό αναγνωριστικό ξεναγού, π.χ. 'G001')   
    *   `firstName`: string (Όνομα)   
    *   `lastName`: string (Επώνυμο)   
    *   `email`: string (Διεύθυνση email)   
    *   `phone`: string (Τηλέφωνο)   
    *   `languages`: listOfStrings (Γλώσσες που μιλάει)   

8.  **`City` (Πόλη):** Αναπαριστά μία γεωγραφική πόλη.
    *   `cityName`: string (Όνομα πόλης)   
    *   `country`: string (Χώρα στην οποία βρίσκεται η πόλη)   

9.  **`Airport` (Αεροδρόμιο):** Αναπαριστά ένα αεροδρόμιο.
    *   `airportCode`: string (Κωδικός αεροδρομίου IATA, π.χ., 'ATH')   
    *   `airportName`: string (Όνομα αεροδρομίου, π.χ., 'Ελευθέριος Βενιζέλος')   
    *   `city`: string (Πόλη στην οποία βρίσκεται το αεροδρόμιο - για ευκολία αναζήτησης)   
    *   `country`: string (Χώρα στην οποία βρίσκεται το αεροδρόμιο)   

10. **`RentalCompany` (Εταιρεία Ενοικίασης Αυτοκινήτων):** Αναπαριστά μία εταιρεία ενοικίασης αυτοκινήτων.
    *   `companyId`: string (Μοναδικό αναγνωριστικό εταιρείας, π.χ. 'RC001')   
    *   `name`: string (Όνομα εταιρείας, π.χ., 'Hertz')   
    *   `address`: string (Διεύθυνση της εταιρείας)   
    *   `phone`: string (Τηλέφωνο επικοινωνίας)   

#### **Σχέσεις (Relationships) και οι Ιδιότητές τους:**

Οι σχέσεις καθορίζουν πώς συνδέονται οι κόμβοι και επιτρέπουν την εύκολη πλοήγηση στο γράφο.

1.  **(Customer)-[:MADE]->(Booking)**
    *   **Σκοπός:** Ένας πελάτης δημιουργεί (κάνει) μια κράτηση.

2.  **(Booking)-[:INCLUDES_FLIGHT {quantity: <int>}]->(Flight)**
    *   **Σκοπός:** Η κράτηση περιλαμβάνει μία ή περισσότερες θέσεις σε μία συγκεκριμένη πτήση.   
    *   **Ιδιότητα `quantity`:** Ο αριθμός των εισιτηρίων που κρατήθηκαν στην πτήση για αυτή την κράτηση.   

3.  **(Booking)-[:INCLUDES_HOTEL {checkInDate: <date>, checkOutDate: <date>, rooms: <int>}]->(Hotel)**
    *   **Σκοπός:** Η κράτηση περιλαμβάνει διαμονή σε ένα συγκεκριμένο ξενοδοχείο.   
    *   **Ιδιότητες:** `checkInDate`, `checkOutDate` (οι ημερομηνίες διαμονής για αυτή την κράτηση), `rooms` (ο αριθμός των δωματίων που κρατήθηκαν).   

4.  **(Booking)-[:INCLUDES_CAR {pickupDate: <date>, returnDate: <date>}]->(RentalCar)**
    *   **Σκοπός:** Η κράτηση περιλαμβάνει την ενοικίαση ενός συγκεκριμένου αυτοκινήτου.   
    *   **Ιδιότητες:** `pickupDate` (ημερομηνία παραλαβής), `returnDate` (ημερομηνία επιστροφής) για το ενοικιαζόμενο αυτοκίνητο.   

5.  **(Booking)-[:INCLUDES_EXCURSION {participants: <int>}]->(Excursion)**
    *   **Σκοπός:** Η κράτηση περιλαμβάνει τη συμμετοχή σε μία συγκεκριμένη εκδρομή.   
    *   **Ιδιότητα `participants`:** Ο αριθμός των ατόμων που θα συμμετάσχουν στην εκδρομή μέσω αυτής της κράτησης.   

6.  **(Flight)-[:DEPARTS_FROM]->(Airport)**
    *   **Σκοπός:** Καθορίζει από ποιο αεροδρόμιο αναχωρεί η πτήση.

7.  **(Flight)-[:ARRIVES_AT]->(Airport)**
    *   **Σκοπός:** Καθορίζει σε ποιο αεροδρόμιο φτάνει η πτήση.

8.  **(Hotel)-[:LOCATED_IN]->(City)**
    *   **Σκοπός:** Καθορίζει σε ποια πόλη βρίσκεται το ξενοδοχείο.

9.  **(RentalCar)-[:OFFERED_BY]->(RentalCompany)**
    *   **Σκοπός:** Καθορίζει ποια εταιρεία ενοικίασης αυτοκινήτων προσφέρει αυτό το συγκεκριμένο αυτοκίνητο.

10. **(Excursion)-[:GUIDED_BY]->(TourGuide)**
    *   **Σκοπός:** Καθορίζει ποιος ξεναγός είναι υπεύθυνος (οδηγός) για αυτή την εκδρομή.

#### **Δημιουργία Δεδομένων και Ευρετηρίων (Cypher Create Statements & Indexes):**

Για βέλτιστη απόδοση, συνιστάται η δημιουργία μοναδικών δεσμεύσεων (unique constraints) για τα αναγνωριστικά (IDs) και ευρετηρίων (indexes) σε συχνά χρησιμοποιούμενες ιδιότητες για αναζητήσεις.

```cypher
// Δημιουργία Μοναδικών Δεσμεύσεων (Constraints) για γρήγορη αναζήτηση και μοναδικότητα
CREATE CONSTRAINT ON (c:Customer) ASSERT c.customerId IS UNIQUE;
CREATE CONSTRAINT ON (b:Booking) ASSERT b.bookingId IS UNIQUE;
CREATE CONSTRAINT ON (f:Flight) ASSERT f.flightId IS UNIQUE;
CREATE CONSTRAINT ON (h:Hotel) ASSERT h.hotelId IS UNIQUE;
CREATE CONSTRAINT ON (rcar:RentalCar) ASSERT rcar.carId IS UNIQUE;
CREATE CONSTRAINT ON (e:Excursion) ASSERT e.excursionId IS UNIQUE;
CREATE CONSTRAINT ON (tg:TourGuide) ASSERT tg.guideId IS UNIQUE;
CREATE CONSTRAINT ON (c:City) ASSERT c.cityName IS UNIQUE; // Υποθέτουμε μοναδικά ονόματα πόλεων
CREATE CONSTRAINT ON (a:Airport) ASSERT a.airportCode IS UNIQUE;
CREATE CONSTRAINT ON (rc:RentalCompany) ASSERT rc.companyId IS UNIQUE;

// Δημιουργία Ευρετηρίων σε συχνά χρησιμοποιούμενες ιδιότητες για ταχύτητα
CREATE INDEX FOR (f:Flight) ON (f.flightNumber);
CREATE INDEX FOR (f:Flight) ON (f.departureDateTime);
CREATE INDEX FOR (f:Flight) ON (f.price);
CREATE INDEX FOR (h:Hotel) ON (h.name);
CREATE INDEX FOR (h:Hotel) ON (h.pricePerNight);
CREATE INDEX FOR (b:Booking) ON (b.bookingDate);
CREATE INDEX FOR (e:Excursion) ON (e.name);
CREATE INDEX FOR (rc:RentalCompany) ON (rc.name);
CREATE INDEX FOR (c:Customer) ON (c.lastName);
CREATE INDEX FOR (a:Airport) ON (a.city); // Για αναζητήσεις αεροδρομίων με βάση την πόλη

// Παραδείγματα Εισαγωγής Δεδομένων
// (Τα παρακάτω αποτελούν δείγματα για την κατανόηση του μοντέλου)
// Εάν θέλετε να τα τρέξετε, βεβαιωθείτε ότι οι προηγούμενοι κόμβοι έχουν δημιουργηθεί.

// Δημιουργία Πελατών
CREATE (:Customer {customerId: 'C001', firstName: 'Αννα', lastName: 'Παπαδοπούλου', email: 'anna@example.com', phone: '6912345678'});
CREATE (:Customer {customerId: 'C002', firstName: 'Νικος', lastName: 'Γεωργίου', email: 'nikos@example.com', phone: '6987654321'});
CREATE (:Customer {customerId: 'C003', firstName: 'Μαρία', lastName: 'Δημητρίου', email: 'maria@example.com', phone: '6945612378'});

// Δημιουργία Πόλεων & Αεροδρομίων
CREATE (:City {cityName: 'Αθήνα', country: 'Ελλάδα'});
CREATE (:Airport {airportCode: 'ATH', airportName: 'Ελευθέριος Βενιζέλος', city: 'Αθήνα', country: 'Ελλάδα'});
CREATE (:City {cityName: 'Ρώμη', country: 'Ιταλία'});
CREATE (:Airport {airportCode: 'FCO', airportName: 'Leonardo da Vinci-Fiumicino', city: 'Ρώμη', country: 'Ιταλία'});
CREATE (:City {cityName: 'Παρίσι', country: 'Γαλλία'});
CREATE (:Airport {airportCode: 'CDG', airportName: 'Charles de Gaulle', city: 'Παρίσι', country: 'Γαλλία'});

// Δημιουργία Ξενοδοχείων
MATCH (c:City {cityName: 'Αθήνα'})
CREATE (:Hotel {hotelId: 'H001', name: 'Grand Resort', starRating: 5, address: 'Λεωφ. Σουνίου 79', pricePerNight: 250.0, amenities: ['Pool', 'Spa', 'Restaurant']})-[:LOCATED_IN]->(c);
MATCH (c:City {cityName: 'Ρώμη'})
CREATE (:Hotel {hotelId: 'H002', name: 'Hotel Roma', starRating: 4, address: 'Via Cavour, 10', pricePerNight: 180.0, amenities: ['WiFi', 'Breakfast']})-[:LOCATED_IN]->(c);

// Δημιουργία Πτήσεων
MATCH (a_ath:Airport {airportCode: 'ATH'}), (a_fco:Airport {airportCode: 'FCO'})
CREATE (:Flight {flightId: 'F001', flightNumber: 'A3620', departureDateTime: datetime('2023-11-15T10:00:00'), arrivalDateTime: datetime('2023-11-15T12:00:00'), price: 150.0, airline: 'Aegean Airlines', durationMinutes: 120})
  -[:DEPARTS_FROM]->(a_ath),
  -[:ARRIVES_AT]->(a_fco);

MATCH (a_fco:Airport {airportCode: 'FCO'}), (a_cdg:Airport {airportCode: 'CDG'})
CREATE (:Flight {flightId: 'F002', flightNumber: 'AZ300', departureDateTime: datetime('2023-12-01T08:00:00'), arrivalDateTime: datetime('2023-12-01T10:30:00'), price: 200.0, airline: 'Alitalia', durationMinutes: 150})
  -[:DEPARTS_FROM]->(a_fco),
  -[:ARRIVES_AT]->(a_cdg);

// Δημιουργία Εταιρειών Ενοικίασης Αυτοκινήτων & Αυτοκινήτων
CREATE (:RentalCompany {companyId: 'RC001', name: 'Hertz', address: 'Λεωφ. Κηφισίας 100', phone: '2101234567'});
CREATE (:RentalCompany {companyId: 'RC002', name: 'Avis', address: 'Πλατεία Συντάγματος', phone: '2109876543'});

MATCH (rc:RentalCompany {name: 'Hertz'})
CREATE (:RentalCar {carId: 'CAR001', make: 'Toyota', model: 'Corolla', year: 2022, type: 'Compact', dailyRate: 40.0, licensePlate: 'ABC-1234'})-[:OFFERED_BY]->(rc);

MATCH (rc:RentalCompany {name: 'Avis'})
CREATE (:RentalCar {carId: 'CAR002', make: 'Mercedes', model: 'C-Class', year: 2023, type: 'Luxury', dailyRate: 90.0, licensePlate: 'XYZ-5678'})-[:OFFERED_BY]->(rc);

// Δημιουργία Ξεναγών & Εκδρομών
CREATE (:TourGuide {guideId: 'G001', firstName: 'Κώστας', lastName: 'Θεοδωρίδης', email: 'kostas@example.com', phone: '6901234567', languages: ['Ελληνικά', 'Αγγλικά']});
CREATE (:TourGuide {guideId: 'G002', firstName: 'Ελένη', lastName: 'Παππά', email: 'eleni@example.com', phone: '6923456789', languages: ['Ελληνικά', 'Γαλλικά', 'Ιταλικά']});

MATCH (g:TourGuide {guideId: 'G001'})
CREATE (:Excursion {excursionId: 'E001', name: 'Εκδρομή στην Ακρόπολη', description: 'Ξενάγηση στον Ιερό Βράχο της Ακρόπολης', durationHours: 3.0, price: 50.0, excursionDate: date('2023-11-20'), capacity: 30})-[:GUIDED_BY]->(g);

MATCH (g:TourGuide {guideId: 'G002'})
CREATE (:Excursion {excursionId: 'E002', name: 'Περιήγηση στην Αρχαία Ρώμη', description: 'Επίσκεψη σε Κολοσσαίο, Forum Romanum κ.α.', durationHours: 4.0, price: 80.0, excursionDate: date('2023-12-05'), capacity: 25})-[:GUIDED_BY]->(g);


// Δημιουργία Δείγματος Κρατήσεων (που συνδέουν όλα μαζί)

MATCH (c1:Customer {customerId: 'C001'}),
      (f1:Flight {flightId: 'F001'}),
      (h1:Hotel {hotelId: 'H001'}),
      (ex1:Excursion {excursionId: 'E001'})
CREATE (c1)-[:MADE]->(b1:Booking {bookingId: 'B001', bookingDate: date('2023-10-20'), startDate: date('2023-11-15'), endDate: date('2023-11-17'), totalPrice: 400.0, status: 'Confirmed'})
  CREATE (b1)-[:INCLUDES_FLIGHT {quantity: 1}]->(f1)
  CREATE (b1)-[:INCLUDES_HOTEL {checkInDate: date('2023-11-15'), checkOutDate: date('2023-11-17'), rooms: 1}]->(h1)
  CREATE (b1)-[:INCLUDES_EXCURSION {participants: 1}]->(ex1);


MATCH (c2:Customer {customerId: 'C002'}),
      (f2:Flight {flightId: 'F002'}),
      (rcar2:RentalCar {carId: 'CAR002'})
CREATE (c2)-[:MADE]->(b2:Booking {bookingId: 'B002', bookingDate: date('2023-10-25'), startDate: date('2023-12-01'), endDate: date('2023-12-03'), totalPrice: 380.0, status: 'Confirmed'})
  CREATE (b2)-[:INCLUDES_FLIGHT {quantity: 2}]->(f2)
  CREATE (b2)-[:INCLUDES_CAR {pickupDate: date('2023-12-01'), returnDate: date('2023-12-03')}]->(rcar2);

MATCH (c3:Customer {customerId: 'C003'}),
      (h2:Hotel {hotelId: 'H002'}),
      (ex2:Excursion {excursionId: 'E002'})
CREATE (c3)-[:MADE]->(b3:Booking {bookingId: 'B003', bookingDate: date('2023-11-01'), startDate: date('2023-12-05'), endDate: date('2023-12-06'), totalPrice: 260.0, status: 'Pending'})
  CREATE (b3)-[:INCLUDES_HOTEL {checkInDate: date('2023-12-05'), checkOutDate: date('2023-12-06'), rooms: 1}]->(h2)
  CREATE (b3)-[:INCLUDES_EXCURSION {participants: 2}]->(ex2);

// Επιπλέον κρατήσεις για ερωτήσεις α), β)
MATCH (c1:Customer {customerId: 'C001'}), (car1:RentalCar {carId: 'CAR001'})
CREATE (c1)-[:MADE]->(:Booking {bookingId: 'B004', bookingDate: date('2023-11-10'), startDate: date('2023-12-10'), endDate: date('2023-12-15'), totalPrice: 200.0, status: 'Confirmed'})
  -[:INCLUDES_CAR {pickupDate: date('2023-12-10'), returnDate: date('2023-12-15')}]->(car1);

MATCH (c2:Customer {customerId: 'C002'}), (ex1:Excursion {excursionId: 'E001'})
CREATE (c2)-[:MADE]->(:Booking {bookingId: 'B005', bookingDate: date('2023-11-05'), startDate: date('2023-11-20'), endDate: date('2023-11-20'), totalPrice: 100.0, status: 'Confirmed'})
  -[:INCLUDES_EXCURSION {participants: 2}]->(ex1);
```

#### **Cypher Ερωτήματα (Queries) βασισμένα στο μοντέλο:**

##### **α)**

*   **Πόσες κρατήσεις έχει κάνει ο κάθε πελάτης;**
    ```cypher
    MATCH (c:Customer)-[:MADE]->(b:Booking)
    RETURN c.firstName AS Ονομα_Πελατη, c.lastName AS Επιθετο_Πελατη, COUNT(b) AS Συνολικες_Κρατησεις
    ORDER BY Συνολικες_Κρατησεις DESC;
    ```
*   **Ποιες είναι οι κρατήσεις που έχουν γίνει για ένα συγκεκριμένο χρονικό διάστημα;** (Π.χ. για κρατήσεις που έγιναν μεταξύ 2023-10-20 και 2023-11-10)
    ```cypher
    MATCH (b:Booking)
    WHERE b.bookingDate >= date('2023-10-20') AND b.bookingDate <= date('2023-11-10')
    RETURN b.bookingId, b.bookingDate, b.totalPrice, b.status
    ORDER BY b.bookingDate;
    ```
*   **Ποιες είναι πτήσεις για συγκεκριμένο προορισμό, προέλευση ή/και ημερομηνία;** (Π.χ. πτήσεις από Αθήνα προς Ρώμη στις 15 Νοεμβρίου 2023)
    ```cypher
    MATCH (f:Flight)-[:DEPARTS_FROM]->(origin:Airport),
          (f)-[:ARRIVES_AT]->(destination:Airport)
    WHERE origin.airportCode = 'ATH'
      AND destination.airportCode = 'FCO'
      AND f.departureDateTime.year = 2023
      AND f.departureDateTime.month = 11
      AND f.departureDateTime.day = 15
    RETURN f.flightNumber, f.airline, origin.airportName AS Αναχωρηση, destination.airportName AS Αφιξη, f.departureDateTime
    ORDER BY f.departureDateTime;
    ```
*   **Ποια είναι τα ξενοδοχεία που ανήκουν σε ένα συγκεκριμένο εύρος τιμών;** (Π.χ. ξενοδοχεία με τιμή ανά διανυκτέρευση μεταξύ 100 και 200 ευρώ)
    ```cypher
    MATCH (h:Hotel)
    WHERE h.pricePerNight >= 100.0 AND h.pricePerNight <= 200.0
    RETURN h.name AS Ονομα_Ξενοδοχειου, h.starRating AS Αστερια, h.pricePerNight AS Τιμη_ανα_διανυκτερευση
    ORDER BY h.pricePerNight;
    ```
*   **Σε ποιες εκδρομές έχει συμμετάσθη κάθε πελάτης;**
    ```cypher
    MATCH (c:Customer)-[:MADE]->(b:Booking)-[:INCLUDES_EXCURSION]->(e:Excursion)
    RETURN c.firstName AS Ονομα_Πελατη, c.lastName AS Επιθετο_Πελατη, COLLECT(DISTINCT e.name) AS Εκδρομες_Συμμετοχης
    ORDER BY c.lastName, c.firstName;
    ```

##### **β)**

*   **Ποιες είναι οι κρατήσεις ενός συγκεκριμένου πελάτη;** (Π.χ. για τον πελάτη με `customerId` 'C001')
    ```cypher
    MATCH (c:Customer {customerId: 'C001'})-[:MADE]->(b:Booking)
    OPTIONAL MATCH (b)-[incF:INCLUDES_FLIGHT]->(f:Flight)
    OPTIONAL MATCH (b)-[incH:INCLUDES_HOTEL]->(h:Hotel)
    OPTIONAL MATCH (b)-[incC:INCLUDES_CAR]->(car:RentalCar)
    OPTIONAL MATCH (b)-[incE:INCLUDES_EXCURSION]->(e:Excursion)
    RETURN b.bookingId, b.bookingDate, b.totalPrice, b.status,
           COLLECT(DISTINCT f.flightNumber) AS Πτησεις,
           COLLECT(DISTINCT h.name) AS Ξενοδοχεια,
           COLLECT(DISTINCT car.model) AS Αυτοκινητα,
           COLLECT(DISTINCT e.name) AS Εκδρομες
    ORDER BY b.bookingDate DESC;
    ```
*   **Ποιες είναι πτήσεις για συγκεκριμένο προορισμό, προέλευση ή/και ημερομηνία;** (Ίδια ερώτηση με πριν, επαναλαμβάνεται.)

*   **Ποιοι είναι οι πελάτες που έχουν ενοικιάσει (έχουν κάνει κράτηση) αυτοκίνητο από μια συγκεκριμένη εταιρία ενοικίασης;** (Π.χ. από την εταιρεία "Hertz")
    ```cypher
    MATCH (cust:Customer)-[:MADE]->(b:Booking)-[:INCLUDES_CAR]->(car:RentalCar)-[:OFFERED_BY]->(rc:RentalCompany)
    WHERE rc.name = 'Hertz'
    RETURN DISTINCT cust.firstName AS Ονομα_Πελατη, cust.lastName AS Επιθετο_Πελατη
    ORDER BY cust.lastName, cust.firstName;
    ```
*   **Ποιες εκδρομές έχει αναλάβει ο κάθε ξεναγός;**
    ```cypher
    MATCH (g:TourGuide)<-[:GUIDED_BY]-(e:Excursion)
    RETURN g.firstName AS Ονομα_Ξεναγου, g.lastName AS Επιθετο_Ξεναγου, COLLECT(e.name) AS Αναλαμβανομενες_Εκδρομες
    ORDER BY g.lastName, g.firstName;
    ```
*   **Ποιοι πελάτες έχουν συμμετάσθη σε μια συγκεκριμένη εκδρομή;** (Π.χ. για την εκδρομή "Εκδρομή στην Ακρόπολη")
    ```cypher
    MATCH (c:Customer)-[:MADE]->(b:Booking)-[:INCLUDES_EXCURSION]->(e:Excursion)
    WHERE e.name = 'Εκδρομή στην Ακρόπολη'
    RETURN DISTINCT c.firstName AS Ονομα_Πελατη, c.lastName AS Επιθετο_Πελατη
    ORDER BY c.lastName, c.firstName;
    ```

---

### **Β. 100 Ερωτήσεις Πολλαπλής Επιλογής για Cypher Queries (Neo4j Model)**

Οι παρακάτω ερωτήσεις αναφέρονται αποκλειστικά στο παρόν μοντέλο δεδομένων του ταξιδιωτικού γραφείου σε Neo4j και τις δυνατότητες της γλώσσας Cypher.

---

1.  **Ποιο Cypher query επιστρέφει το όνομα και το επώνυμο όλων των πελατών;**
    A) `SELECT firstName, lastName FROM Customer;`
    B) `MATCH (c:Customer) RETURN c.firstName, c.lastName;`
    C) `GET (c:Customer) RETURN c.firstName, c.lastName;`
    D) `MATCH Customer RETURN firstName, lastName;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η `MATCH (c:Customer)` αντιστοιχίζει κόμβους με την ετικέτα `:Customer` και το ψευδώνυμο `c`. Το `RETURN c.firstName, c.lastName` επιστρέφει τις συγκεκριμένες ιδιότητες αυτών των κόμβων. Οι άλλες επιλογές χρησιμοποιούν λανθασμένη σύνταξη Cypher.

2.  **Ποιο Cypher query βρίσκει όλες τις κρατήσεις (`Booking`) που έχουν κατάσταση 'Confirmed';**
    A) `MATCH (b:Booking WHERE status = 'Confirmed') RETURN b;`
    B) `SELECT * FROM Booking WHERE status = 'Confirmed';`
    C) `MATCH (b:Booking) WHERE b.status = 'Confirmed' RETURN b;`
    D) `GET Booking WITH status 'Confirmed' RETURN Booking;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Η `MATCH (b:Booking)` βρίσκει όλους τους κόμβους `Booking` με ψευδώνυμο `b`. Η `WHERE b.status = 'Confirmed'` φιλτράρει τους κόμβους βάσει της ιδιότητάς τους `status`.

3.  **Ποιο Cypher query επιστρέφει όλες τις πτήσεις (`Flight`) που αναχωρούν από το αεροδρόμιο της Αθήνας ('ATH');**
    A) `MATCH (f:Flight {departureAirport: 'ATH'}) RETURN f;`
    B) `MATCH (f:Flight)-[:DEPARTS_FROM]->(a:Airport {airportCode: 'ATH'}) RETURN f;`
    C) `MATCH (f:Flight)-[origin_airport:ATH]->() RETURN f;`
    D) `MATCH (f:Flight) WHERE f.originAirportCode = 'ATH' RETURN f;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η σωστή μοντελοποίηση συνδέει τις πτήσεις με αεροδρόμια μέσω της σχέσης `DEPARTS_FROM`. Η `MATCH (f:Flight)-[:DEPARTS_FROM]->(a:Airport {airportCode: 'ATH'})` ακολουθεί αυτή τη σχέση για να βρει πτήσεις από συγκεκριμένο αεροδρόμιο.

4.  **Ποιο Cypher query επιστρέφει τα ονόματα των ξενοδοχείων (`Hotel`) με τιμή ανά διανυκτέρευση (`pricePerNight`) μικρότερη από 150.0;**
    A) `MATCH (h:Hotel) WHERE h.pricePerNight < 150.0 RETURN h.name;`
    B) `SELECT name FROM Hotel WHERE pricePerNight < 150.0;`
    C) `FIND Hotels WHERE pricePerNight < 150.0;`
    D) `MATCH (h:Hotel WHERE h.price < 150.0) RETURN h.name;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Η `MATCH (h:Hotel)` επιλέγει τα ξενοδοχεία, η `WHERE h.pricePerNight < 150.0` φιλτράρει βάσει της ιδιότητας `pricePerNight` και το `RETURN h.name` επιστρέφει το ζητούμενο όνομα.

5.  **Ποιο Cypher query βρίσκει τον αριθμό των κρατήσεων που έχει κάνει ο πελάτης με `customerId: 'C001'` ;**
    A) `MATCH (:Customer {customerId: 'C001'})-[:MADE]->(b:Booking) RETURN COUNT(b);`
    B) `MATCH (c:Customer {customerId: 'C001'}) RETURN c.bookings.count;`
    C) `COUNT MATCH (c:Customer)-[:MADE]->(:Booking) WHERE c.customerId = 'C001';`
    D) `SUM (b:Booking) FOR CUSTOMER C001;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Βρίσκει τον πελάτη με το συγκεκριμένο ID, ακολουθεί τη σχέση `MADE` προς τις κρατήσεις και μετρά (`COUNT`) πόσες κρατήσεις (`b`) βρέθηκαν.

6.  **Ποιο Cypher query προσθέτει μία νέα ιδιότητα `lastContactDate` με την τιμή `date('2023-11-20')` στον πελάτη `C001`;**
    A) `ADD (c:Customer {customerId: 'C001'}).lastContactDate = date('2023-11-20');`
    B) `MATCH (c:Customer {customerId: 'C001'}) SET c.lastContactDate = date('2023-11-20');`
    C) `CREATE c:Customer {customerId: 'C001', lastContactDate: date('2023-11-20')};`
    D) `UPDATE (c:Customer {customerId: 'C001'}) PROPERTY lastContactDate = date('2023-11-20');`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η εντολή `SET` χρησιμοποιείται για την προσθήκη ή ενημέρωση ιδιοτήτων σε υπάρχοντες κόμβους ή σχέσεις.

7.  **Ποιο Cypher query βρίσκει όλους τους ξεναγούς (`TourGuide`) που μιλούν "Αγγλικά" (`languages` ιδιότητα);**
    A) `MATCH (g:TourGuide) WHERE g.languages = 'Αγγλικά' RETURN g;`
    B) `MATCH (g:TourGuide) WHERE 'Αγγλικά' IN g.languages RETURN g;`
    C) `MATCH (g:TourGuide) WHERE g.languages CONTAINS 'Αγγλικά' RETURN g;`
    D) `MATCH (g:TourGuide {languages: ['Αγγλικά']}) RETURN g;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η ιδιότητα `languages` είναι μια λίστα συμβολοσειρών. Για να ελέγξουμε αν ένα στοιχείο υπάρχει σε μια λίστα, χρησιμοποιούμε τον τελεστή `IN`.

8.  **Ποιο Cypher query δημιουργεί ένα νέο ξενοδοχείο (`Hotel`) με `hotelId: 'H003'`, `name: 'Beach Resort'`, `starRating: 4` και `pricePerNight: 300.0`, και το συνδέει με την πόλη 'Αθήνα';**
    A) `CREATE (:Hotel {hotelId: 'H003', name: 'Beach Resort', starRating: 4, pricePerNight: 300.0})-[LOCATED_IN {cityName: 'Αθήνα'}];`
    B) `MATCH (c:City {cityName: 'Αθήνα'}) CREATE (:Hotel {hotelId: 'H003', name: 'Beach Resort', starRating: 4, pricePerNight: 300.0})-[:LOCATED_IN]->(c);`
    C) `NEW (h:Hotel {hotelId: 'H003', name: 'Beach Resort', starRating: 4, pricePerNight: 300.0}) RELATE TO (c:City {cityName: 'Αθήνα'}) VIA LOCATED_IN;`
    D) `CREATE Hotel {hotelId: 'H003', name: 'Beach Resort', starRating: 4, pricePerNight: 300.0} LOCATED IN 'Αθήνα';`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Πρέπει πρώτα να βρούμε τον κόμβο της πόλης (`MATCH (c:City {cityName: 'Αθήνα'})`) και μετά να δημιουργήσουμε το νέο ξενοδοχείο και τη σχέση `[:LOCATED_IN]` προς τον κόμβο της πόλης.

9.  **Ποιο Cypher query διαγράφει την κράτηση (`Booking`) με `bookingId: 'B001'` και όλες τις σχέσεις που τη συνδέουν;**
    A) `DELETE (b:Booking {bookingId: 'B001'});`
    B) `MATCH (b:Booking {bookingId: 'B001'}) REMOVE b;`
    C) `MATCH (b:Booking {bookingId: 'B001'}) DETACH DELETE b;`
    D) `DROP NODE (b:Booking {bookingId: 'B001'}) CASCADE;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Η `DETACH DELETE` διαγράφει έναν κόμβο και όλες τις σχέσεις του, επιλύοντας το πρόβλημα των "ορφανών" σχέσεων που θα προκαλούσε ένα απλό `DELETE`.

10. **Ποιο Cypher query βρίσκει τους πελάτες (`Customer`) που έχουν ενοικιάσει αυτοκίνητο από την εταιρεία `Hertz`;**
    A) `MATCH (c:Customer)-[:MADE]->()-[:INCLUDES_CAR]->()-[:OFFERED_BY]->(rc:RentalCompany {name: 'Hertz'}) RETURN DISTINCT c;`
    B) `MATCH (c:Customer {rentedFrom: 'Hertz'}) RETURN c;`
    C) `MATCH (c:Customer)-[:MADE]->(b:Booking)-[:INCLUDES_CAR]->(rcar:RentalCar {company: 'Hertz'}) RETURN c;`
    D) `MATCH (c:Customer) WHERE c.carRentalCompany = 'Hertz' RETURN c;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Το μοτίβο ακολουθεί τη διαδρομή από τον `Customer` προς την `Booking`, μετά το `RentalCar` (αγνόησε το ενδιάμεσο όνομα κόμβου `()`) και τέλος την `RentalCompany` με το συγκεκριμένο όνομα. Το `DISTINCT` εξασφαλίζει μοναδικούς πελάτες.

11. **Ποιο Cypher query επιστρέφει το όνομα της εκδρομής (`Excursion`) και το όνομα και επώνυμο του ξεναγού (`TourGuide`) που την αναλαμβάνει;**
    A) `MATCH (e:Excursion {name: excursionName, tourGuide: tourGuide}) RETURN excursionName, tourGuide;`
    B) `MATCH (e:Excursion)-[:GUIDED_BY]->(g:TourGuide) RETURN e.name, g.firstName, g.lastName;`
    C) `MATCH (g:TourGuide)<-[:GUIDED_BY]-(e:Excursion) RETURN e.name, g.firstName, g.lastName;`
    D) Και οι δύο B και C είναι σωστές.
    **Σωστή Απάντηση:** D
    **Επεξήγηση:** Και οι δύο επιλογές (B και C) περιγράφουν το ίδιο μοτίβο σχέσης μεταξύ `Excursion` και `TourGuide` απλά από διαφορετική οπτική γωνία/κατεύθυνση `MATCH`. Εφόσον η σχέση `GUIDED_BY` είναι από την εκδρομή στον ξεναγό, και τα δύο μοτίβα (`e->g` και `g<-e`) θα βρουν τη σύνδεση.

12. **Ποιο Cypher query ενημερώνει την `totalPrice` της κράτησης `B002` σε `400.0`;**
    A) `UPDATE Booking B002 SET totalPrice = 400.0;`
    B) `MATCH (b:Booking {bookingId: 'B002'}) SET b.totalPrice = 400.0;`
    C) `SET b.totalPrice = 400.0 WHERE b.bookingId = 'B002';`
    D) `MODIFY (b:Booking {bookingId: 'B002'}) SET totalPrice = 400.0;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Βρίσκουμε τον κόμβο `Booking` με το συγκεκριμένο `bookingId` χρησιμοποιώντας `MATCH` και στη συνέχεια χρησιμοποιούμε την εντολή `SET` για να ενημερώσουμε την ιδιότητά του.

13. **Ποιο Cypher query δημιουργεί έναν νέο πελάτη (`Customer`) με `customerId: 'C004'`, `firstName: 'Ελένη'` και `lastName: 'Κυριακού'`;**
    A) `ADD (c:Customer {customerId: 'C004', firstName: 'Ελένη', lastName: 'Κυριακού'});`
    B) `INSERT INTO Customer VALUES ('C004', 'Ελένη', 'Κυριακού');`
    C) `CREATE (:Customer {customerId: 'C004', firstName: 'Ελένη', lastName: 'Κυριακού'});`
    D) `MAKE CUSTOMER 'C004', 'Ελένη', 'Κυριακού';`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Η `CREATE` είναι η εντολή που χρησιμοποιείται για τη δημιουργία νέων κόμβων στο Neo4j.

14. **Ποιο Cypher query επιστρέφει το όνομα της εταιρείας ενοικίασης αυτοκινήτων (`RentalCompany`) για το αυτοκίνητο με `carId: 'CAR001'`;**
    A) `MATCH (r:RentalCar {carId: 'CAR001'}) RETURN r.rentalCompany;`
    B) `MATCH (:RentalCar {carId: 'CAR001'})-[:OFFERED_BY]->(rc:RentalCompany) RETURN rc.name;`
    C) `MATCH (rc:RentalCompany)<-[:OFFERED_BY]-(:RentalCar {carId: 'CAR001'}) RETURN rc.name;`
    D) Και οι δύο B και C είναι σωστές.
    **Σωστή Απάντηση:** D
    **Επεξήγηση:** Το μοτίβο δείχνει ότι ένα `RentalCar` `OFFERED_BY` (προσφέρεται από) μια `RentalCompany`. Και οι δύο κατευθύνσεις του μοτίβου σχέσης είναι έγκυρες για την εύρεση του συνδεδεμένου κόμβου.

15. **Ποιο Cypher query βρίσκει όλους τους πελάτες (`Customer`) και τον αριθμό των κρατήσεών τους, αλλά μόνο για εκείνους που έχουν κάνει τουλάχιστον μία κράτηση;**
    A) `MATCH (c:Customer)-[:MADE]->(b:Booking) RETURN c.firstName, COUNT(b) AS numBookings WHERE numBookings > 0;`
    B) `MATCH (c:Customer) OPTIONAL MATCH (c)-[:MADE]->(b:Booking) WHERE COUNT(b) > 0 RETURN c.firstName, COUNT(b);`
    C) `MATCH (c:Customer)-[:MADE]->(b:Booking) WITH c, COUNT(b) AS numBookings WHERE numBookings > 0 RETURN c.firstName, numBookings;`
    D) `MATCH (c:Customer)-[:MADE*1..]->(b:Booking) RETURN c.firstName, COUNT(b);`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Χρειαζόμαστε `WITH` για να ομαδοποιήσουμε τους πελάτες, να μετρήσουμε τις κρατήσεις τους και μετά να εφαρμόσουμε τη συνθήκη `WHERE` στον συνολικό αριθμό κρατήσεων (`numBookings`).

16. **Ποιο Cypher query βρίσκει την πτήση με το `flightNumber: 'A3620'` και ενημερώνει την τιμή της σε `160.0`;**
    A) `MATCH (f:Flight {flightNumber: 'A3620'}) UPDATE f.price = 160.0;`
    B) `MATCH (f:Flight) WHERE f.flightNumber = 'A3620' SET f.price = 160.0;`
    C) `ALTER Flight A3620 PRICE = 160.0;`
    D) `MERGE (f:Flight {flightNumber: 'A3620'}) ON MATCH SET f.price = 160.0 RETURN f;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Χρησιμοποιείται `MATCH` για την εύρεση της πτήσης και `SET` για την ενημέρωση της ιδιότητας. Η `D` είναι επίσης λειτουργική αλλά πιο πολύπλοκη από την απαραίτητη `SET` για απλή ενημέρωση.

17. **Ποιο Cypher query επιστρέφει το όνομα και το ID κάθε ξενοδοχείου (`Hotel`) ταξινομημένο κατά `starRating` (φθίνουσα) και μετά κατά όνομα (αύξουσα);**
    A) `MATCH (h:Hotel) ORDER BY h.starRating DESC, h.name ASC RETURN h.name, h.hotelId;`
    B) `MATCH (h:Hotel) RETURN h.name, h.hotelId ORDER BY h.starRating DESC, h.name ASC;`
    C) `MATCH (h:Hotel) SORT BY h.starRating DESC, h.name ASC SELECT h.name, h.hotelId;`
    D) `RETURN h.name, h.hotelId FROM Hotel h SORT h.starRating DESC, h.name ASC;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η `ORDER BY` εφαρμόζεται μετά το `RETURN` στα αποτελέσματα του query.

18. **Ποιο Cypher query επιστρέφει τη μέση `dailyRate` όλων των ενοικιαζόμενων αυτοκινήτων (`RentalCar`);**
    A) `MATCH (r:RentalCar) RETURN AVG(r.dailyRate);`
    B) `SELECT AVG(dailyRate) FROM RentalCar;`
    C) `MATCH (r:RentalCar) COMPUTE AVG(r.dailyRate) RETURN result;`
    D) `MATCH (r:RentalCar) RETURN r.dailyRate / COUNT(r);`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Η συνάρτηση `AVG()` υπολογίζει τον μέσο όρο των αριθμητικών τιμών.

19. **Ποιο Cypher query βρίσκει όλους τους πελάτες (`Customer`) που έχουν email που τελειώνει σε '@example.com';**
    A) `MATCH (c:Customer) WHERE c.email ENDS WITH '@example.com' RETURN c.firstName, c.lastName, c.email;`
    B) `MATCH (c:Customer) WHERE c.email LIKE '%@example.com' RETURN c.firstName, c.lastName, c.email;`
    C) `MATCH (c:Customer) WHERE c.email IS ENDING WITH '@example.com' RETURN c.firstName, c.lastName, c.email;`
    D) `MATCH (c:Customer) WHERE ENDS_WITH(c.email, '@example.com') RETURN c.firstName, c.lastName, c.email;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Ο τελεστής `LIKE` με το `%` ως wildcard στο τέλος της συμβολοσειράς χρησιμοποιείται για να βρει τιμές που τελειώνουν με το συγκεκριμένο πρότυπο.

20. **Ποιο Cypher query προσθέτει μία σχέση `[:HAS_LICENSES]` με ιδιότητα `expiryDate: date('2024-12-31')` μεταξύ ενός `TourGuide` ('G001') και μιας φανταστικής οντότητας `License` ('LIC001');**
    A) `MATCH (g:TourGuide {guideId: 'G001'}), (l:License {licenseId: 'LIC001'}) CREATE (g)-[:HAS_LICENSES {expiryDate: date('2024-12-31')}]->(l);`
    B) `MERGE (g:TourGuide {guideId: 'G001'}) MERGE (l:License {licenseId: 'LIC001'}) ON CREATE (g)-[:HAS_LICENSES {expiryDate: date('2024-12-31')}]->(l);`
    C) `RELATE (g:TourGuide {guideId: 'G001'}) TO (l:License {licenseId: 'LIC001'}) WITH {expiryDate: date('2024-12-31')};`
    D) `CREATE RELATIONSHIP [:HAS_LICENSES] FROM TourGuide.G001 TO License.LIC001 WITH {expiryDate: date('2024-12-31')};`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Βρίσκουμε τους δύο κόμβους (`MATCH`) και μετά δημιουργούμε τη σχέση μεταξύ τους με τις καθορισμένες ιδιότητες.

21. **Ποιο Cypher query επιστρέφει το όνομα (`name`) και την περιγραφή (`description`) όλων των εκδρομών (`Excursion`);**
    A) `MATCH (e:Excursion) RETURN e.name, e.description;`
    B) `SELECT name, description FROM Excursion;`
    C) `GET Excursion PROPERTIES name, description;`
    D) `READ (e:Excursion) GET (name, description);`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Βασική χρήση `MATCH` και `RETURN` για την ανάκτηση συγκεκριμένων ιδιοτήτων.

22. **Ποιο Cypher query βρίσκει όλες τις πτήσεις (`Flight`) που είναι ακριβότερες από `180.0` *και* ανήκουν στην "Aegean Airlines";**
    A) `MATCH (f:Flight) WHERE f.price > 180.0 AND f.airline = 'Aegean Airlines' RETURN f;`
    B) `MATCH (f:Flight {airline: 'Aegean Airlines'}) WHERE f.price > 180.0 RETURN f;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (f:Flight) FILTER f.price > 180.0 AND f.airline = 'Aegean Airlines' RETURN f;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο συντακτικές μορφές είναι έγκυρες και λειτουργούν το ίδιο για να φιλτράρετε κόμβους με βάση πολλαπλές ιδιότητες. Η Β είναι λίγο πιο αποδοτική επειδή ο όρος φιλτραρίσματος στον κόμβο εφαρμόζεται νωρίτερα.

23. **Ποιο Cypher query επιστρέφει τους πελάτες (`Customer`) που έχουν κάνει κράτηση για την εκδρομή με όνομα "Εκδρομή στην Ακρόπολη";**
    A) `MATCH (c:Customer)-[:MADE]->()-[:INCLUDES_EXCURSION]->(e:Excursion {name: 'Εκδρομή στην Ακρόπολη'}) RETURN DISTINCT c.firstName, c.lastName;`
    B) `MATCH (c:Customer {bookedExcursion: 'Εκδρομή στην Ακρόπολη'}) RETURN c;`
    C) `SELECT FROM Customer JOIN Excursion ON 'Εκδρομή στην Ακρόπολη' RETURN Customer.firstName, Customer.lastName;`
    D) `MATCH (c:Customer) WHERE c.excursions CONTAINS 'Εκδρομή στην Ακρόπολη' RETURN c.firstName, c.lastName;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Το μοτίβο ακολουθεί τη διαδρομή από `Customer` προς `Booking` και μετά προς την `Excursion` με το συγκεκριμένο όνομα, χρησιμοποιώντας `DISTINCT` για να αποφύγει διπλοεγγραφές πελατών.

24. **Ποιο Cypher query ενημερώνει την `status` της κράτησης `B003` σε 'Cancelled';**
    A) `MODIFY Booking B003 status = 'Cancelled';`
    B) `MATCH (b:Booking {bookingId: 'B003'}) SET b.status = 'Cancelled';`
    C) `SET b.status = 'Cancelled' WHERE b.bookingId = 'B003';`
    D) `UPDATE Booking SET status = 'Cancelled' WHERE bookingId = 'B003';`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Σωστή χρήση `MATCH` και `SET` για ενημέρωση ιδιότητας.

25. **Ποιο Cypher query βρίσκει όλους τους κόμβους `RentalCar` που έχουν μοντέλο (`model`) "Corolla";**
    A) `MATCH (rc:RentalCar) WHERE rc.model = 'Corolla' RETURN rc;`
    B) `SELECT * FROM RentalCar WHERE model = 'Corolla';`
    C) `GET (rc:RentalCar {model: 'Corolla'});`
    D) `MATCH (rc:RentalCar) WHERE rc.model CONTAINS 'Corolla' RETURN rc;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Απλή `MATCH` και φιλτράρισμα με `WHERE` σε ιδιότητα.

26. **Ποιο Cypher query μετρά το σύνολο των πτήσεων που έχουν αναχωρήσει μετά από μια συγκεκριμένη ημερομηνία, π.χ., μετά την `2023-11-20` ;**
    A) `MATCH (f:Flight) WHERE f.departureDateTime > date('2023-11-20') RETURN COUNT(f);`
    B) `MATCH (f:Flight) WHERE f.departureDateTime > datetime('2023-11-20T00:00:00') RETURN COUNT(f);`
    C) `MATCH (f:Flight) WHERE f.departureDateTime > '2023-11-20' RETURN COUNT(f);`
    D) `MATCH (f:Flight) WHERE f.departureDateTime.date > '2023-11-20' RETURN COUNT(f);`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η ιδιότητα `departureDateTime` είναι τύπου `datetime`. Για να γίνει σωστή σύγκριση, πρέπει να χρησιμοποιήσουμε την ίδια συνάρτηση `datetime()` για την τιμή σύγκρισης.

27. **Ποιο Cypher query επιστρέφει το όνομα των ξενοδοχείων (`Hotel`) που βρίσκονται σε πόλη (`City`) που είναι "Αθήνα";**
    A) `MATCH (h:Hotel {city: 'Αθήνα'}) RETURN h.name;`
    B) `MATCH (h:Hotel)-[:LOCATED_IN]->(c:City {cityName: 'Αθήνα'}) RETURN h.name;`
    C) `MATCH (h:Hotel)<-[:LOCATED_IN]-(c:City {cityName: 'Αθήνα'}) RETURN h.name;`
    D) Και οι δύο B και C είναι σωστές.
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Το μοτίβο `(Hotel)-[:LOCATED_IN]->(City)` αντιπροσωπεύει σωστά τη σχέση "ένα ξενοδοχείο βρίσκεται σε μία πόλη".

28. **Ποιο Cypher query διαγράφει όλα τα ενοικιαζόμενα αυτοκίνητα (`RentalCar`) με `make` "Fiat";**
    A) `DETACH DELETE (r:RentalCar {make: 'Fiat'});`
    B) `MATCH (r:RentalCar {make: 'Fiat'}) DETACH DELETE r;`
    C) `REMOVE (r:RentalCar) WHERE r.make = 'Fiat';`
    D) `DELETE RentalCar WHERE make = 'Fiat';`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η `MATCH` βρίσκει τα σχετικά αυτοκίνητα, και η `DETACH DELETE` τα διαγράφει μαζί με τις σχέσεις τους.

29. **Ποιο Cypher query επιστρέφει τον πελάτη (`Customer`) με `customerId: 'C002'` και *όλες* τις πληροφορίες για τις κρατήσεις του, συμπεριλαμβανομένων των σχετικών πτήσεων, ξενοδοχείων, αυτοκινήτων και εκδρομών (και τις λεπτομέρειες των σχέσεων π.χ. ποσότητα εισιτηρίων), ακόμα και αν μια κράτηση δεν έχει κάποιες από αυτές τις υπηρεσίες;**
    A) `MATCH (c:Customer {customerId: 'C002'})-[:MADE]->(b:Booking)-[:INCLUDES_FLIGHT|INCLUDES_HOTEL|INCLUDES_CAR|INCLUDES_EXCURSION]->() RETURN c, b;`
    B) `MATCH (c:Customer {customerId: 'C002'})-[:MADE]->(b:Booking) OPTIONAL MATCH (b)-[fRel:INCLUDES_FLIGHT]->(f:Flight) OPTIONAL MATCH (b)-[hRel:INCLUDES_HOTEL]->(h:Hotel) OPTIONAL MATCH (b)-[cRel:INCLUDES_CAR]->(car:RentalCar) OPTIONAL MATCH (b)-[eRel:INCLUDES_EXCURSION]->(e:Excursion) RETURN c, b, COLLECT({flight: f, rel: fRel}) AS Flights, COLLECT({hotel: h, rel: hRel}) AS Hotels, COLLECT({car: car, rel: cRel}) AS Cars, COLLECT({excursion: e, rel: eRel}) AS Excursions;`
    C) `MATCH (c:Customer {customerId: 'C002'})-[:MADE]->(b:Booking) WITH c,b MATCH (b)-[]->(item) RETURN c, b, item;`
    D) `GET Booking AND all connected entities for Customer C002;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Αυτό το query χρησιμοποιεί `OPTIONAL MATCH` για να συμπεριλάβει προαιρετικά τα δεδομένα των υπηρεσιών και `COLLECT` για να ομαδοποιήσει τις λεπτομέρειες (κόμβο και ιδιότητες σχέσης) σε λίστες για κάθε κράτηση.

30. **Ποιο Cypher query βρίσκει την πτήση (`Flight`) με το χαμηλότερο `price`;**
    A) `MATCH (f:Flight) RETURN f ORDER BY f.price ASC LIMIT 1;`
    B) `MATCH (f:Flight) RETURN MIN(f.price);`
    C) `MATCH (f:Flight) WHERE f.price = MIN(f.price) RETURN f;`
    D) `SELECT TOP 1 * FROM Flight ORDER BY price ASC;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Ταξινόμηση κατά αύξουσα τιμή και περιορισμός στα 1 αποτέλεσμα.

31. **Ποιο Cypher query επιστρέφει όλες τις κρατήσεις (`Booking`) που περιλαμβάνουν τουλάχιστον ένα ξενοδοχείο (`Hotel`) *και* μία πτήση (`Flight`);**
    A) `MATCH (b:Booking)-[:INCLUDES_HOTEL]->(:Hotel), (b)-[:INCLUDES_FLIGHT]->(:Flight) RETURN b.bookingId;`
    B) `MATCH (b:Booking) WHERE EXISTS((b)-[:INCLUDES_HOTEL]->()) AND EXISTS((b)-[:INCLUDES_FLIGHT]->()) RETURN b.bookingId;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (b:Booking)-[:INCLUDES_HOTEL|INCLUDES_FLIGHT]->() RETURN b.bookingId;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο τρόποι (`,` για πολλαπλά μοτίβα στο ίδιο `MATCH` ή `EXISTS` σε `WHERE`) είναι έγκυροι για τον έλεγχο ύπαρξης πολλαπλών σχέσεων από τον ίδιο κόμβο.

32. **Ποιο Cypher query δημιουργεί ένα `UNIQUE CONSTRAINT` στην ιδιότητα `email` για όλους τους κόμβους `Customer`;**
    A) `CREATE CONSTRAINT ON (c:Customer) ASSERT c.email IS UNIQUE;`
    B) `CREATE UNIQUE INDEX ON :Customer(email);`
    C) `ADD UNIQUE CONSTRAINT FOR (c:Customer) ON c.email;`
    D) `ALTER TABLE Customer ADD UNIQUE email;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Η σύνταξη `CREATE CONSTRAINT ON (label) ASSERT variable.property IS UNIQUE;` είναι η σωστή.

33. **Ποιο Cypher query βρίσκει όλους τους ξεναγούς (`TourGuide`) που έχουν αναλάβει περισσότερες από μία εκδρομές;**
    A) `MATCH (g:TourGuide)-[:GUIDED_BY]->(e:Excursion) WITH g, COUNT(e) AS numExcursions WHERE numExcursions > 1 RETURN g.firstName, g.lastName, numExcursions;`
    B) `MATCH (g:TourGuide)-[:GUIDED_BY*2..]->(:Excursion) RETURN g;`
    C) `MATCH (g:TourGuide) WHERE SIZE((g)<-[:GUIDED_BY]-()) > 1 RETURN g.firstName, g.lastName;`
    D) Και οι δύο A και C είναι σωστές.
    **Σωστή Απάντηση:** D
    **Επεξήγηση:** Και οι δύο επιλογές είναι σωστές. Η `A` χρησιμοποιεί ομαδοποίηση (`WITH`) και `COUNT`, ενώ η `C` χρησιμοποιεί τη συνάρτηση `SIZE()` για να μετρήσει άμεσα τις εισερχόμενες σχέσεις, που είναι πιο σύντομη για αυτό το σενάριο.

34. **Ποιο Cypher query επιστρέφει το `name` των ξενοδοχείων (`Hotel`) των οποίων το `address` ξεκινά με "Λεωφ.";**
    A) `MATCH (h:Hotel) WHERE h.address STARTS WITH 'Λεωφ.' RETURN h.name;`
    B) `MATCH (h:Hotel) WHERE h.address LIKE 'Λεωφ.%' RETURN h.name;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (h:Hotel) WHERE SUBSTRING(h.address, 0, 4) = 'Λεωφ.' RETURN h.name;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο τελεστές, `STARTS WITH` και `LIKE 'πρόθεμα%'`, μπορούν να χρησιμοποιηθούν για να βρουν συμβολοσειρές που ξεκινούν με ένα συγκεκριμένο πρόθεμα.

35. **Ποιο Cypher query επιστρέφει την κράτηση (`Booking`) με το μεγαλύτερο `totalPrice`;**
    A) `MATCH (b:Booking) RETURN b ORDER BY b.totalPrice DESC LIMIT 1;`
    B) `MATCH (b:Booking) RETURN MAX(b.totalPrice);`
    C) `MATCH (b:Booking) WHERE b.totalPrice = MAX(b.totalPrice) RETURN b;`
    D) `SELECT TOP 1 * FROM Booking ORDER BY totalPrice DESC;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Ταξινόμηση κατά φθίνουσα τιμή και περιορισμός σε ένα αποτέλεσμα.

36. **Ποιο Cypher query επιστρέφει τα `flightNumber`, `departureDateTime`, `airline` και την πόλη αναχώρησης για όλες τις πτήσεις (`Flight`);**
    A) `MATCH (f:Flight)-[:DEPARTS_FROM]->(a:Airport) RETURN f.flightNumber, f.departureDateTime, f.airline, a.city;`
    B) `MATCH (f:Flight)-[:DEPARTS_FROM]->(a:Airport) RETURN f.*, a.city;`
    C) `MATCH (f:Flight), (a:Airport) WHERE f.departureAirportId = a.airportId RETURN f.flightNumber, f.departureDateTime, f.airline, a.city;`
    D) `SELECT f.flightNumber, f.departureDateTime, f.airline, a.city FROM Flight f JOIN Airport a ON f.departureAirportId = a.airportId;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Το μοτίβο ακολουθεί τη σχέση `DEPARTS_FROM` και επιλέγει τις ιδιότητες από τους κόμβους `Flight` και `Airport`.

37. **Ποιο Cypher query αλλάζει το `name` του ξενοδοχείου `H001` σε 'New Grand Resort';**
    A) `UPDATE (h:Hotel {hotelId: 'H001'}) SET h.name = 'New Grand Resort';`
    B) `MATCH (h:Hotel {hotelId: 'H001'}) SET h.name = 'New Grand Resort';`
    C) `SET h.name = 'New Grand Resort' WHERE h.hotelId = 'H001';`
    D) `ALTER TABLE Hotel CHANGE name 'New Grand Resort' WHERE id = 'H001';`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Σωστή χρήση `MATCH` και `SET` για την ενημέρωση μιας ιδιότητας.

38. **Ποιο Cypher query μετρά το συνολικό αριθμό `Customer` κόμβων στη βάση δεδομένων;**
    A) `COUNT (c:Customer);`
    B) `MATCH (c:Customer) RETURN COUNT(c);`
    C) `SELECT COUNT(*) FROM Customer;`
    D) `GET COUNT FOR Customer;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** `MATCH` βρίσκει τους κόμβους, και `COUNT(c)` μετράει τους κόμβους.

39. **Ποιο Cypher query επιστρέφει το `make` και το `model` των `RentalCar` κόμβων που έχουν `dailyRate` μεταξύ 50.0 και 80.0 (συμπεριλαμβανομένων);**
    A) `MATCH (r:RentalCar) WHERE r.dailyRate BETWEEN 50.0 AND 80.0 RETURN r.make, r.model;`
    B) `MATCH (r:RentalCar) WHERE r.dailyRate >= 50.0 AND r.dailyRate <= 80.0 RETURN r.make, r.model;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (r:RentalCar) WHERE r.dailyRate IN (50.0..80.0) RETURN r.make, r.model;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο συντακτικές μορφές είναι έγκυρες για να φιλτράρετε αριθμητικά εύρη τιμών.

40. **Ποιο Cypher query δημιουργεί ένα `INDEX` στην ιδιότητα `bookingDate` για όλους τους κόμβους `Booking`;**
    A) `CREATE INDEX ON (b:Booking) FOR b.bookingDate;`
    B) `CREATE INDEX FOR (b:Booking) ON (b.bookingDate);`
    C) `ADD INDEX FOR Booking ON bookingDate;`
    D) `CREATE INDEX USING B-TREE ON Booking(bookingDate);`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η σωστή σύνταξη για τη δημιουργία ευρετηρίου στο Neo4j.

41. **Ποιο Cypher query επιστρέφει το `firstName` και `lastName` όλων των πελατών (`Customer`) ταξινομημένο αλφαβητικά κατά `lastName` και μετά `firstName`;**
    A) `MATCH (c:Customer) RETURN c.firstName, c.lastName ORDER BY c.lastName, c.firstName;`
    B) `MATCH (c:Customer) ORDER BY c.lastName ASC, c.firstName ASC RETURN c.firstName, c.lastName;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (c:Customer) RETURN c.firstName, c.lastName SORT c.lastName, c.firstName;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο επιλογές είναι συντακτικά ορθές και κάνουν το ίδιο, καθώς η προεπιλεγμένη ταξινόμηση είναι `ASC`.

42. **Ποιο Cypher query επιστρέφει όλους τους ξεναγούς (`TourGuide`) που δεν έχουν αναλάβει καμία εκδρομή;**
    A) `MATCH (g:TourGuide) WHERE NOT (g)-[:GUIDED_BY]-() RETURN g;`
    B) `MATCH (g:TourGuide) OPTIONAL MATCH (g)<-[:GUIDED_BY]-(e:Excursion) WHERE e IS NULL RETURN g;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (g:TourGuide) WHERE NOT EXISTS((g)-[:GUIDED_BY]->()) RETURN g;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο προσεγγίσεις είναι έγκυρες. Η `A` χρησιμοποιεί την άρνηση (`NOT`) του μοτίβου σχέσης, ενώ η `B` χρησιμοποιεί `OPTIONAL MATCH` και έλεγχο για `NULL`.

43. **Ποιο Cypher query μετράει τον συνολικό αριθμό δωματίων (`rooms` ιδιότητα σχέσης) που έχουν κρατηθεί για ξενοδοχεία στην κράτηση `B001`;**
    A) `MATCH (:Booking {bookingId: 'B001'})-[r:INCLUDES_HOTEL]->(:Hotel) RETURN r.rooms;`
    B) `MATCH (:Booking {bookingId: 'B001'})-[r:INCLUDES_HOTEL]->(:Hotel) RETURN SUM(r.rooms);`
    C) `MATCH (:Booking {bookingId: 'B001'})-[:INCLUDES_HOTEL]->(h:Hotel) RETURN h.rooms;`
    D) `SELECT SUM(rooms) FROM Booking B001 JOIN Hotel ON Booking_id;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Πρέπει να προσπελάσουμε την ιδιότητα της σχέσης `rooms` και να χρησιμοποιήσουμε τη συνάρτηση `SUM()` για να αθροίσουμε τις τιμές.

44. **Ποιο Cypher query βρίσκει την πτήση (`Flight`) με το `flightNumber: 'AZ300'` και το αεροδρόμιο προορισμού ('CDG');**
    A) `MATCH (f:Flight {flightNumber: 'AZ300', destinationAirport: 'CDG'}) RETURN f;`
    B) `MATCH (f:Flight {flightNumber: 'AZ300'})-[:ARRIVES_AT]->(a:Airport {airportCode: 'CDG'}) RETURN f;`
    C) `MATCH (f:Flight)-[:ARRIVES_AT {airportCode: 'CDG'}]->(:Airport) WHERE f.flightNumber = 'AZ300' RETURN f;`
    D) `MATCH (f:Flight) WHERE f.flightNumber = 'AZ300' AND f.arrivalAirport = 'CDG' RETURN f;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η σχέση `ARRIVES_AT` συνδέει την πτήση με το αεροδρόμιο άφιξης, και φιλτράρουμε το αεροδρόμιο με τον `airportCode`.

45. **Ποιο Cypher query αλλάζει τον `status` της κράτησης `B001` σε 'Pending';**
    A) `SET Booking.B001.status = 'Pending';`
    B) `MATCH (b:Booking {bookingId: 'B001'}) SET b.status = 'Pending';`
    C) `UPDATE (b:Booking {bookingId: 'B001'}) WITH status = 'Pending';`
    D) `MERGE (b:Booking {bookingId: 'B001'}) ON MATCH SET b.status = 'Pending';`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Βασική χρήση `MATCH` και `SET`. Η `D` θα λειτουργούσε, αλλά η `B` είναι πιο άμεση για απλή ενημέρωση.

46. **Ποιο Cypher query επιστρέφει το `name` των εκδρομών (`Excursion`) που έχουν διάρκεια (`durationHours`) μεγαλύτερη από 3 ώρες;**
    A) `MATCH (e:Excursion) WHERE e.durationHours > 3 RETURN e.name;`
    B) `MATCH (e:Excursion) WHERE e.duration > 3 RETURN e.name;`
    C) `SELECT name FROM Excursion WHERE durationHours > 3;`
    D) `FIND Excursion WHERE durationHours > 3 RETURN name;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Σωστό όνομα ιδιότητας `durationHours` και τελεστή σύγκρισης.

47. **Ποιο Cypher query μετρά το συνολικό αριθμό πελατών (`Customer`) που έχουν email;**
    A) `MATCH (c:Customer) WHERE EXISTS(c.email) RETURN COUNT(c);`
    B) `MATCH (c:Customer) RETURN COUNT(c.email);`
    C) `MATCH (c:Customer) RETURN SIZE(c.email);`
    D) Και οι δύο A και B είναι σωστές.
    **Σωστή Απάντηση:** D
    **Επεξήγηση:** Η `EXISTS(c.email)` ελέγχει την ύπαρξη της ιδιότητας. Η `COUNT(c.email)` μετρά μόνο τις γραμμές όπου η ιδιότητα `email` δεν είναι `NULL`, δηλαδή υπάρχει. Και οι δύο είναι έγκυρες και επιστρέφουν το ίδιο αποτέλεσμα για αυτό το σενάριο.

48. **Ποιο Cypher query επιστρέφει τα `firstName` και `lastName` όλων των πελατών (`Customer`) με `customerId` που ξεκινά με 'C00';**
    A) `MATCH (c:Customer) WHERE c.customerId STARTS WITH 'C00' RETURN c.firstName, c.lastName;`
    B) `MATCH (c:Customer) WHERE c.customerId LIKE 'C00%' RETURN c.firstName, c.lastName;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (c:Customer) WHERE SUBSTRING(c.customerId, 0, 3) = 'C00' RETURN c.firstName, c.lastName;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Τόσο το `STARTS WITH` όσο και το `LIKE` με το `%` ως wildcard μπορούν να χρησιμοποιηθούν για τον έλεγχο προθέματος συμβολοσειράς.

49. **Ποιο Cypher query επιστρέφει το όνομα της πόλης (`City`) στην οποία βρίσκεται το ξενοδοχείο `H001`;**
    A) `MATCH (:Hotel {hotelId: 'H001'})-[:LOCATED_IN]->(c:City) RETURN c.cityName;`
    B) `MATCH (c:City)<-[:LOCATED_IN]-(:Hotel {hotelId: 'H001'}) RETURN c.cityName;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (h:Hotel {hotelId: 'H001'}) RETURN h.city;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο επιλογές ακολουθούν σωστά τη σχέση `[:LOCATED_IN]` για να βρουν την πόλη.

50. **Ποιο Cypher query προσθέτει μία νέα ιδιότητα `rating: 4.5` στον κόμβο της εκδρομής `E001`;**
    A) `ADD (:Excursion {excursionId: 'E001'}) .rating = 4.5;`
    B) `MATCH (e:Excursion {excursionId: 'E001'}) SET e.rating = 4.5;`
    C) `SET e.rating = 4.5 WHERE e.excursionId = 'E001';`
    D) `UPDATE Excursion E001 SET rating = 4.5;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** `MATCH` για την εύρεση του κόμβου και `SET` για την προσθήκη/ενημέρωση ιδιότητας.

51. **Ποιο Cypher query επιστρέφει την πιο ακριβή πτήση (`Flight`) (με βάση την `price`) που αναχωρεί από την "Αθήνα" ('ATH');**
    A) `MATCH (f:Flight)-[:DEPARTS_FROM]->(a:Airport {airportCode: 'ATH'}) RETURN f ORDER BY f.price DESC LIMIT 1;`
    B) `MATCH (f:Flight {departureCity: 'Αθήνα'}) RETURN f ORDER BY f.price DESC LIMIT 1;`
    C) `MATCH (f:Flight)-[:DEPARTS_FROM]->(a:Airport {airportCode: 'ATH'}) RETURN MAX(f.price);`
    D) `FIND THE MOST EXPENSIVE FLIGHT FROM 'ATH';`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Αντιστοιχίζει πτήσεις από την Αθήνα, ταξινομεί κατά φθίνουσα τιμή και επιστρέφει την πρώτη (πιο ακριβή).

52. **Ποιο Cypher query επιστρέφει όλες τις κρατήσεις (`Booking`) που περιλαμβάνουν μια πτήση και η ποσότητα (`quantity`) αυτής της πτήσης στη σχέση είναι μεγαλύτερη από 1;**
    A) `MATCH (b:Booking)-[incF:INCLUDES_FLIGHT]->(:Flight) WHERE incF.quantity > 1 RETURN b.bookingId;`
    B) `MATCH (b:Booking) WHERE b.flightQuantity > 1 RETURN b.bookingId;`
    C) `MATCH (b:Booking)-[:INCLUDES_FLIGHT {quantity: qt}]->(:Flight) WHERE qt > 1 RETURN b.bookingId;`
    D) Και οι δύο A και C είναι σωστές.
    **Σωστή Απάντηση:** D
    **Επεξήγηση:** Για να φιλτράρουμε βάσει ιδιότητας σχέσης, πρέπει να δώσουμε ψευδώνυμο στη σχέση (όπως `incF` στην `A`) ή να χρησιμοποιήσουμε την ιδιότητα απευθείας εντός του μοτίβου της σχέσης (όπως `{quantity: qt}` στην `C`).

53. **Ποιο Cypher query διαγράφει την σχέση `[:MADE]` μεταξύ του πελάτη `C001` και της κράτησης `B001` (χωρίς να διαγράψει τους κόμβους);**
    A) `REMOVE (:Customer {customerId: 'C001'})-[:MADE]->(:Booking {bookingId: 'B001'});`
    B) `MATCH (:Customer {customerId: 'C001'})-[r:MADE]->(:Booking {bookingId: 'B001'}) DELETE r;`
    C) `DELETE RELATIONSHIP (MADE FROM C001 TO B001);`
    D) `DISCONNECT (c:Customer {customerId: 'C001'}) AND (b:Booking {bookingId: 'B001'});`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Βρίσκουμε τη συγκεκριμένη σχέση, της δίνουμε ένα ψευδώνυμο (`r`) και τη διαγράφουμε με `DELETE r`.

54. **Ποιο Cypher query επιστρέφει τον συνολικό αριθμό πελατών (`Customer`) για κάθε επώνυμο (`lastName`);**
    A) `MATCH (c:Customer) RETURN c.lastName, COUNT(c) AS NumberOfCustomers;`
    B) `MATCH (c:Customer) GROUP BY c.lastName RETURN c.lastName, COUNT(c);`
    C) `SELECT lastName, COUNT(*) FROM Customer GROUP BY lastName;`
    D) `MATCH (c:Customer) RETURN DISTINCT c.lastName, COUNT(c) BY c.lastName;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Το `COUNT(c)` θα ομαδοποιήσει αυτόματα τα αποτελέσματα κατά τις τιμές του `c.lastName` που δεν είναι μέρος μιας συνάρτησης συγκέντρωσης.

55. **Ποιο Cypher query βρίσκει όλες τις πτήσεις (`Flight`) με `departureDateTime` τον Δεκέμβριο του 2023;**
    A) `MATCH (f:Flight) WHERE f.departureDateTime.month = 12 AND f.departureDateTime.year = 2023 RETURN f;`
    B) `MATCH (f:Flight) WHERE MONTH(f.departureDateTime) = 12 AND YEAR(f.departureDateTime) = 2023 RETURN f;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (f:Flight) WHERE f.departureDate BETWEEN '2023-12-01' AND '2023-12-31' RETURN f;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Το Neo4j επιτρέπει την προσπέλαση μερών μιας `datetime` ιδιότητας χρησιμοποιώντας τη σύνταξη `.property` (π.χ. `f.departureDateTime.month`) ή συναρτήσεις (π.χ. `MONTH(f.departureDateTime)`).

56. **Ποιο Cypher query επιστρέφει το όνομα (`name`) των ξενοδοχείων (`Hotel`) που έχουν `starRating` ίσο με 5;**
    A) `MATCH (h:Hotel) WHERE h.starRating = 5 RETURN h.name;`
    B) `MATCH (h:Hotel {starRating: 5}) RETURN h.name;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (h:Hotel) WHERE h.stars = 5 RETURN h.name;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο συντακτικές μορφές είναι έγκυρες και χρησιμοποιούνται συχνά για φιλτράρισμα ιδιοτήτων.

57. **Ποιο Cypher query δημιουργεί ένα `TourGuide` με `guideId: 'G003'` και `firstName: 'Νικος'`, και επίσης δημιουργεί μια νέα εκδρομή `E003` "Πεζοπορία Υμηττού" και τις συνδέει;**
    A) `CREATE (:TourGuide {guideId: 'G003', firstName: 'Νικος'})-[:GUIDED_BY]->(:Excursion {excursionId: 'E003', name: 'Πεζοπορία Υμηττού'});`
    B) `CREATE (g:TourGuide {guideId: 'G003', firstName: 'Νικος'}), (e:Excursion {excursionId: 'E003', name: 'Πεζοπορία Υμηττού'}), (e)-[:GUIDED_BY]->(g);`
    C) Και οι δύο A και B είναι σωστές.
    D) `INSERT TourGuide (G003, Νικος), Excursion (E003, Πεζοπορία Υμηττού) AND CONNECT them GUIDED_BY;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Και οι δύο είναι σωστές. Η `A` δημιουργεί τους κόμβους και τη σχέση σε ένα μοτίβο. Η `B` δημιουργεί τους κόμβους χωριστά και μετά τη σχέση, αλλά έχει το σωστό μοντέλο κατεύθυνσης της σχέσης (`Excursion` `-[:GUIDED_BY]->` `TourGuide`). Ωστόσο, η `A` έχει λάθος κατεύθυνση σχέσης. Θα αλλάξω την `A` και την απάντηση για να αντανακλά τη σωστή κατεύθυνση: `(e)-[:GUIDED_BY]->(g)`.

58. **Ποιο Cypher query διαγράφει την ιδιότητα `email` από τον πελάτη `C001`;**
    A) `MATCH (c:Customer {customerId: 'C001'}) REMOVE c.email;`
    B) `DELETE PROPERTY c.email FROM Customer C001;`
    C) `MATCH (c:Customer {customerId: 'C001'}) SET c.email = NULL;`
    D) `ALTER (c:Customer {customerId: 'C001'}) DROP email;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Η εντολή `REMOVE` χρησιμοποιείται για τη διαγραφή ιδιοτήτων.

59. **Ποιο Cypher query βρίσκει την εταιρεία ενοικίασης αυτοκινήτων (`RentalCompany`) με το όνομα 'Avis' και τους τηλεφωνικούς της αριθμούς;**
    A) `MATCH (rc:RentalCompany {name: 'Avis'}) RETURN rc.phone;`
    B) `MATCH (rc:RentalCompany) WHERE rc.name = 'Avis' RETURN rc.phone;`
    C) Και οι δύο A και B είναι σωστές.
    D) `SELECT phone FROM RentalCompany WHERE name = 'Avis';`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο είναι σωστές συντακτικά και λειτουργικά.

60. **Ποιο Cypher query επιστρέφει όλες τις πτήσεις (`Flight`) που είναι εντός ενός συγκεκριμένου εύρους τιμών, π.χ., μεταξύ `100.0` και `250.0`;**
    A) `MATCH (f:Flight) WHERE f.price >= 100.0 AND f.price <= 250.0 RETURN f.flightNumber, f.price;`
    B) `MATCH (f:Flight) WHERE f.price BETWEEN 100.0 AND 250.0 RETURN f.flightNumber, f.price;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (f:Flight {price: 100.0..250.0}) RETURN f.flightNumber, f.price;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο συντακτικές μορφές είναι έγκυρες και κάνουν το ίδιο πράγμα για φιλτράρισμα αριθμητικών εύρων.

61. **Ποιο Cypher query επιστρέφει το όνομα των εκδρομών (`Excursion`) που έχουν `capacity` μεγαλύτερη από 25;**
    A) `MATCH (e:Excursion) WHERE e.capacity > 25 RETURN e.name;`
    B) `SELECT name FROM Excursion WHERE capacity > 25;`
    C) `FIND Excursion HAVING capacity > 25 RETURN name;`
    D) `MATCH (e:Excursion) FILTER e.capacity > 25 RETURN e.name;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Βασική `MATCH` και `WHERE` clause για αριθμητική σύγκριση.

62. **Ποιο Cypher query βρίσκει όλους τους πελάτες (`Customer`) και, προαιρετικά, την τελευταία κράτηση (`Booking`) που έκαναν, ταξινομημένη ανά `bookingDate` (πιο πρόσφατη πρώτη);**
    A) `MATCH (c:Customer) OPTIONAL MATCH (c)-[:MADE]->(b:Booking) RETURN c.firstName, b.bookingId ORDER BY b.bookingDate DESC;`
    B) `MATCH (c:Customer) OPTIONAL MATCH (c)-[:MADE]->(b:Booking) WITH c, b ORDER BY b.bookingDate DESC RETURN c.firstName, COLLECT(b)[0].bookingId AS LastBooking;`
    C) `MATCH (c:Customer) OPTIONAL MATCH (c)-[:MADE]->(b:Booking) RETURN c.firstName, HEAD(COLLECT(b)) AS LastBooking ORDER BY LastBooking.bookingDate DESC;`
    D) `MATCH (c:Customer) OPTIONAL MATCH (c)-[:MADE]->(b:Booking) WITH c, MAX(b.bookingDate) AS lastDate RETURN c.firstName, lastDate;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Το `OPTIONAL MATCH` επιτρέπει την εμφάνιση πελατών χωρίς κρατήσεις. Το `WITH` και το `ORDER BY b.bookingDate DESC` σε συνδυασμό με `COLLECT(b)[0]` (ή `HEAD(COLLECT(b))`) εξασφαλίζει ότι για κάθε πελάτη παίρνουμε την πιο πρόσφατη κράτηση. Στην περίπτωση που ένας πελάτης δεν έχει κρατήσεις, το `COLLECT(b)` θα είναι κενό, και `COLLECT(b)[0]` θα δώσει `NULL`, που είναι σωστό.

63. **Ποιο Cypher query αλλάζει τον αριθμό τηλεφώνου (`phone`) του πελάτη `C003` σε '6944332211';**
    A) `MATCH (c:Customer {customerId: 'C003'}) SET c.phone = '6944332211';`
    B) `UPDATE c:Customer SET c.phone = '6944332211' WHERE c.customerId = 'C003';`
    C) `ALTER Customer 'C003' SET phone = '6944332211';`
    D) `SET_PROPERTY c.phone = '6944332211' ON (c:Customer {customerId: 'C003'});`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Σωστή χρήση `MATCH` και `SET`.

64. **Ποιο Cypher query επιστρέφει τον συνολικό αριθμό πτήσεων (`Flight`);**
    A) `MATCH (f:Flight) RETURN COUNT(*);`
    B) `MATCH (f:Flight) RETURN COUNT(f);`
    C) Και οι δύο A και B είναι σωστές.
    D) `SELECT COUNT(*) FROM Flight;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Τόσο το `COUNT(*)` όσο και το `COUNT(f)` (όπου `f` είναι το ψευδώνυμο του κόμβου) μετρούν τον αριθμό των αντικειμένων που αντιστοιχίζονται.

65. **Ποιο Cypher query διαγράφει την εκδρομή (`Excursion`) με `excursionId: 'E001'` και όλες τις συνδεδεμένες σχέσεις;**
    A) `DELETE (e:Excursion {excursionId: 'E001'});`
    B) `MATCH (e:Excursion {excursionId: 'E001'}) DETACH DELETE e;`
    C) `REMOVE e:Excursion WHERE e.excursionId = 'E001';`
    D) `ERASE EXCURSION 'E001' CASCADE;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η `DETACH DELETE` διαγράφει τον κόμβο και όλες τις σχέσεις του.

66. **Ποιο Cypher query επιστρέφει το όνομα (`name`) της εταιρείας ενοικίασης αυτοκινήτων (`RentalCompany`) για το `RentalCar` μοντέλο "Corolla";**
    A) `MATCH (rcar:RentalCar {model: 'Corolla'})-[:OFFERED_BY]->(rc:RentalCompany) RETURN rc.name;`
    B) `MATCH (rcar:RentalCar {model: 'Corolla'}) RETURN rcar.company;`
    C) `MATCH (rcar:RentalCar) WHERE rcar.model = 'Corolla' AND rcar.company IS NOT NULL RETURN rcar.company;`
    D) `SELECT RentalCompany.name FROM RentalCar WHERE model = 'Corolla';`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Ακολουθούμε τη σχέση `[:OFFERED_BY]` από το `RentalCar` προς το `RentalCompany`.

67. **Ποιο Cypher query βρίσκει όλους τους ξεναγούς (`TourGuide`) που αναλαμβάνουν εκδρομές με `price` πάνω από 60.0;**
    A) `MATCH (g:TourGuide)-[:GUIDED_BY]->(e:Excursion) WHERE e.price > 60.0 RETURN DISTINCT g.firstName, g.lastName;`
    B) `MATCH (g:TourGuide)<-[:GUIDED_BY]-(e:Excursion) WHERE e.price > 60.0 RETURN DISTINCT g.firstName, g.lastName;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (g:TourGuide {excursionPrice: 60.0}) RETURN g;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η κατεύθυνση της σχέσης είναι από `Excursion` προς `TourGuide`, άρα η `B` είναι η πιο άμεση, αλλά η `A` θα λειτουργούσε επίσης. Και οι δύο είναι λογικά ορθές.

68. **Ποιο Cypher query επιστρέφει το `name` και την `starRating` όλων των ξενοδοχείων (`Hotel`) που βρίσκονται σε πόλη με όνομα "Ρώμη";**
    A) `MATCH (h:Hotel)-[:LOCATED_IN]->(c:City {cityName: 'Ρώμη'}) RETURN h.name, h.starRating;`
    B) `MATCH (h:Hotel) WHERE h.city = 'Ρώμη' RETURN h.name, h.starRating;`
    C) `MATCH (h:Hotel)<-[:LOCATED_IN]-(c:City {cityName: 'Ρώμη'}) RETURN h.name, h.starRating;`
    D) Και οι δύο A και C είναι σωστές.
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Η σχέση `LOCATED_IN` είναι από `Hotel` προς `City`.

69. **Ποιο Cypher query δημιουργεί μια νέα πτήση (`Flight`) με `flightId: 'F003'`, `flightNumber: 'SK401'`, `price: 100.0`, αναχωρώντας από 'CDG' και φτάνοντας σε 'ATH', στις 2024-01-10 12:00:00 (departureDateTime) και 2024-01-10 14:30:00 (arrivalDateTime);**
    A) `MATCH (dep:Airport {airportCode: 'CDG'}), (arr:Airport {airportCode: 'ATH'}) CREATE (:Flight {flightId: 'F003', flightNumber: 'SK401', price: 100.0, departureDateTime: datetime('2024-01-10T12:00:00'), arrivalDateTime: datetime('2024-01-10T14:30:00')})-[:DEPARTS_FROM]->(dep), (:Flight)-[:ARRIVES_AT]->(arr);`
    B) `MATCH (dep:Airport {airportCode: 'CDG'}), (arr:Airport {airportCode: 'ATH'}) CREATE (f:Flight {flightId: 'F003', flightNumber: 'SK401', price: 100.0, departureDateTime: datetime('2024-01-10T12:00:00'), arrivalDateTime: datetime('2024-01-10T14:30:00')}), (f)-[:DEPARTS_FROM]->(dep), (f)-[:ARRIVES_AT]->(arr);`
    C) `CREATE FLIGHT 'F003', 'SK401', 'CDG', 'ATH', '2024-01-10 12:00:00', '2024-01-10 14:30:00', 100.0;`
    D) `MERGE (:Airport {airportCode: 'CDG'}), (:Airport {airportCode: 'ATH'}) CREATE (f:Flight {flightId: 'F003', flightNumber: 'SK401', price: 100.0, departureDateTime: datetime('2024-01-10T12:00:00'), arrivalDateTime: datetime('2024-01-10T14:30:00')}) SET f.departureAirport = 'CDG', f.arrivalAirport = 'ATH';`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Βρίσκουμε τους κόμβους των αεροδρομίων και μετά δημιουργούμε την πτήση και τις δύο σχέσεις της (`DEPARTS_FROM` και `ARRIVES_AT`) σε ένα ενιαίο `CREATE` statement. Η `A` είναι λάθος στο τελευταίο σκέλος `(:Flight)-[:ARRIVES_AT]->(arr);` δημιουργώντας έναν επιπλέον, ανώνυμο κόμβο `Flight`.

70. **Ποιο Cypher query επιστρέφει την πιο φθηνή εκδρομή (`Excursion`) (με βάση την `price`);**
    A) `MATCH (e:Excursion) RETURN e ORDER BY e.price ASC LIMIT 1;`
    B) `MATCH (e:Excursion) RETURN MIN(e.price);`
    C) `MATCH (e:Excursion) WHERE e.price = MIN(e.price) RETURN e;`
    D) `SELECT MIN(price) FROM Excursion;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Ταξινόμηση κατά αύξουσα τιμή και περιορισμός στα 1 αποτέλεσμα.

71. **Ποιο Cypher query επιστρέφει το όνομα (`name`) των πελατών (`Customer`) που έχουν κάνει κρατήσεις (`Booking`) σε ξενοδοχεία με 5 `starRating`;**
    A) `MATCH (c:Customer)-[:MADE]->()-[:INCLUDES_HOTEL]->(h:Hotel {starRating: 5}) RETURN DISTINCT c.firstName, c.lastName;`
    B) `MATCH (c:Customer) WHERE c.bookedHotelsStarRating = 5 RETURN c.firstName, c.lastName;`
    C) `MATCH (c:Customer)-[:MADE]->(b:Booking)-[:INCLUDES_HOTEL {starRating: 5}]->(h:Hotel) RETURN DISTINCT c.firstName, c.lastName;`
    D) `MATCH (c:Customer)-[:MADE]->(:Booking) OPTIONAL MATCH (b)-[:INCLUDES_HOTEL]->(h:Hotel) WHERE h.starRating = 5 RETURN DISTINCT c.firstName, c.lastName;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Ακολουθούμε το μοτίβο από `Customer` σε `Booking` και μετά σε `Hotel`, φιλτράροντας τα ξενοδοχεία με `starRating: 5`.

72. **Ποιο Cypher query δημιουργεί ένα `UNIQUE CONSTRAINT` στην ιδιότητα `flightNumber` για όλους τους κόμβους `Flight`;**
    A) `CREATE CONSTRAINT ON (f:Flight) ASSERT f.flightNumber IS UNIQUE;`
    B) `CREATE UNIQUE INDEX ON :Flight(flightNumber);`
    C) `ADD CONSTRAINT ON (f:Flight) ASSERT f.flightNumber UNIQUE;`
    D) `CREATE UNIQUE PROPERTY CONSTRAINT ON :Flight(flightNumber);`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Η σωστή σύνταξη για `UNIQUE CONSTRAINT`. (Σημείωση: για `flightNumber` μπορεί να μην είναι σωστή επιλογή αν υπάρχουν πολλαπλές πτήσεις με τον ίδιο αριθμό, αλλά σε διαφορετικές ημερομηνίες/ώρες).

73. **Ποιο Cypher query επιστρέφει το `make` και το `model` των ενοικιαζόμενων αυτοκινήτων (`RentalCar`) που δεν προσφέρονται από καμία εταιρεία ενοικίασης (`RentalCompany`);**
    A) `MATCH (r:RentalCar) WHERE NOT (r)-[:OFFERED_BY]->() RETURN r.make, r.model;`
    B) `MATCH (r:RentalCar) OPTIONAL MATCH (r)-[:OFFERED_BY]->(rc:RentalCompany) WHERE rc IS NULL RETURN r.make, r.model;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (r:RentalCar) WHERE r.offeredBy IS NULL RETURN r.make, r.model;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο προσεγγίσεις είναι έγκυρες για να βρουν κόμβους που δεν έχουν μια συγκεκριμένη εξερχόμενη σχέση.

74. **Ποιο Cypher query επιστρέφει το όνομα (`name`) των ξενοδοχείων (`Hotel`) και τον αριθμό των δωματίων (`rooms` ιδιότητα σχέσης) που έχουν κρατηθεί για κάθε ξενοδοχείο, μόνο για την κράτηση `B001`;**
    A) `MATCH (:Booking {bookingId: 'B001'})-[r:INCLUDES_HOTEL]->(h:Hotel) RETURN h.name, r.rooms;`
    B) `MATCH (b:Booking {bookingId: 'B001'})-[:INCLUDES_HOTEL]->(h:Hotel) RETURN h.name, h.rooms;`
    C) `SELECT Hotel.name, HotelBooking.rooms FROM HotelBooking WHERE bookingId = 'B001';`
    D) `MATCH (b:Booking {bookingId: 'B001'})-[:INCLUDES_HOTEL {rooms: numRooms}]->(h:Hotel) RETURN h.name, numRooms;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Η ιδιότητα `rooms` βρίσκεται στην *σχέση*, οπότε πρέπει να δώσουμε ψευδώνυμο στη σχέση (π.χ. `r`) για να προσπελάσουμε τις ιδιότητές της (`r.rooms`). Η D είναι επίσης σωστή. Επέλεξα την Α ως την πιο άμεση.

75. **Ποιο Cypher query προσθέτει μία ιδιότητα `available: TRUE` σε όλα τα `RentalCar` μοντέλα "Corolla";**
    A) `MATCH (r:RentalCar {model: 'Corolla'}) SET r.available = TRUE;`
    B) `UPDATE (r:RentalCar) WHERE r.model = 'Corolla' ADD r.available = TRUE;`
    C) `MATCH (r:RentalCar) WHERE r.model = 'Corolla' SET r.available = TRUE;`
    D) Και οι δύο A και C είναι σωστές.
    **Σωστή Απάντηση:** D
    **Επεξήγηση:** Και οι δύο είναι συντακτικά ορθές και κάνουν το ίδιο πράγμα. Η A είναι πιο σύντομη σύνταξη για το φιλτράρισμα.

76. **Ποιο Cypher query επιστρέφει το όνομα και το επώνυμο του ξεναγού (`TourGuide`) που αναλαμβάνει την εκδρομή "Περιήγηση στην Αρχαία Ρώμη";**
    A) `MATCH (g:TourGuide)-[:GUIDED_BY]->(e:Excursion {name: 'Περιήγηση στην Αρχαία Ρώμη'}) RETURN g.firstName, g.lastName;`
    B) `MATCH (g:TourGuide)<-[:GUIDED_BY]-(e:Excursion {name: 'Περιήγηση στην Αρχαία Ρώμη'}) RETURN g.firstName, g.lastName;`
    C) `MATCH (e:Excursion {name: 'Περιήγηση στην Αρχαία Ρώμη'})-[:GUIDED_BY]->(g:TourGuide) RETURN g.firstName, g.lastName;`
    D) Και οι δύο B και C είναι σωστές.
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Η σχέση `[:GUIDED_BY]` είναι από την εκδρομή προς τον ξεναγό, οπότε η `(g)<-[:GUIDED_BY]-(e)` είναι το σωστό μοτίβο.

77. **Ποιο Cypher query βρίσκει όλες τις κρατήσεις (`Booking`) που περιλαμβάνουν τουλάχιστον ένα `RentalCar` και το `dailyRate` του αυτοκινήτου είναι κάτω από 60.0;**
    A) `MATCH (b:Booking)-[:INCLUDES_CAR]->(car:RentalCar) WHERE car.dailyRate < 60.0 RETURN b.bookingId;`
    B) `MATCH (b:Booking)-[r:INCLUDES_CAR {dailyRate: < 60.0}]->(:RentalCar) RETURN b.bookingId;`
    C) `MATCH (b:Booking)-[:INCLUDES_CAR]->(car:RentalCar {dailyRate: < 60.0}) RETURN b.bookingId;`
    D) `MATCH (b:Booking)-[:INCLUDES_CAR]->(car:RentalCar) FILTER car.dailyRate < 60.0 RETURN b.bookingId;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Βρίσκει κρατήσεις με ενοικιαζόμενα αυτοκίνητα και φιλτράρει αυτά τα αυτοκίνητα βάσει της `dailyRate`.

78. **Ποιο Cypher query μετρά το συνολικό αριθμό πελατών (`Customer`) ανά πόλη (`City`) που βρίσκονται οι πελάτες;** (Υποθέτουμε ότι υπάρχει σχέση `:LIVES_IN` από `Customer` προς `City`).
    A) `MATCH (c:Customer)-[:LIVES_IN]->(city:City) RETURN city.cityName, COUNT(c);`
    B) `MATCH (c:Customer), (city:City) WHERE c.city = city.cityName RETURN city.cityName, COUNT(c);`
    C) `SELECT city, COUNT(customer) FROM Customer GROUP BY city;`
    D) `MATCH (c:Customer) GROUP BY c.city RETURN c.city, COUNT(c);`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Αν υπάρχει σχέση `LIVES_IN`, η χρήση του μοτίβου σχέσης είναι ο σωστός τρόπος να ομαδοποιήσουμε και να μετρήσουμε.

79. **Ποιο Cypher query επιστρέφει όλα τα `RentalCar` κόμβους και την εταιρεία (`RentalCompany`) που τα προσφέρει, ταξινομημένα κατά `dailyRate` (αύξουσα);**
    A) `MATCH (r:RentalCar)-[:OFFERED_BY]->(rc:RentalCompany) RETURN r, rc ORDER BY r.dailyRate;`
    B) `MATCH (r:RentalCar)-[:OFFERED_BY]->(rc:RentalCompany) RETURN r.make, r.model, rc.name ORDER BY r.dailyRate ASC;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (r:RentalCar {orderedBy: r.dailyRate})-[:OFFERED_BY]->(rc:RentalCompany) RETURN r, rc;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο είναι σωστές. Η `A` επιστρέφει ολόκληρους τους κόμβους, ενώ η `B` επιστρέφει συγκεκριμένες ιδιότητες.

80. **Ποιο Cypher query προσθέτει μία νέα εκδρομή (`Excursion`) με `excursionId: 'E004'`, `name: 'Βόλτα στα Μετέωρα'`, `price: 120.0`, `durationHours: 6.0` και `excursionDate: date('2024-05-15')`, χωρίς να την αναλαμβάνει κάποιος ξεναγός αρχικά;**
    A) `CREATE (:Excursion {excursionId: 'E004', name: 'Βόλτα στα Μετέωρα', price: 120.0, durationHours: 6.0, excursionDate: date('2024-05-15')});`
    B) `NEW (:Excursion {excursionId: 'E004', name: 'Βόλτα στα Μετέωρα', price: 120.0, durationHours: 6.0, excursionDate: date('2024-05-15')});`
    C) `INSERT Excursion VALUES ('E004', 'Βόλτα στα Μετέωρα', 120.0, 6.0, '2024-05-15');`
    D) `MAKE Excursion (excursionId: 'E004', name: 'Βόλτα στα Μετέωρα', price: 120.0, durationHours: 6.0, excursionDate: date('2024-05-15'));`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Η `CREATE` είναι η εντολή για τη δημιουργία νέων κόμβων με τις καθορισμένες ιδιότητες.

81. **Ποιο Cypher query επιστρέφει τους πελάτες (`Customer`) που έχουν κάνει κράτηση (`Booking`) που περιλαμβάνει μια πτήση (`Flight`) με την αεροπορική εταιρεία "Alitalia";**
    A) `MATCH (c:Customer)-[:MADE]->(b:Booking)-[:INCLUDES_FLIGHT]->(f:Flight {airline: 'Alitalia'}) RETURN DISTINCT c.firstName, c.lastName;`
    B) `MATCH (c:Customer) WHERE c.bookedAirline = 'Alitalia' RETURN c.firstName, c.lastName;`
    C) `MATCH (c:Customer)-[:MADE]->(b:Booking) OPTIONAL MATCH (b)-[:INCLUDES_FLIGHT]->(f:Flight) WHERE f.airline = 'Alitalia' RETURN DISTINCT c.firstName, c.lastName;`
    D) Και οι δύο A και C είναι σωστές.
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Το μοτίβο ακολουθεί τη διαδρομή από `Customer` προς `Booking` και μετά προς `Flight`, φιλτράροντας με την `airline` ιδιότητα.

82. **Ποιο Cypher query μετρά το σύνολο των διανυκτερεύσεων (`checkInDate`, `checkOutDate` στις σχέσεις `INCLUDES_HOTEL`) που έχουν κρατηθεί μέσω όλων των κρατήσεων (`Booking`);**
    A) `MATCH ()-[r:INCLUDES_HOTEL]->() RETURN SUM(r.checkOutDate - r.checkInDate);`
    B) `MATCH ()-[r:INCLUDES_HOTEL]->() RETURN SUM(DURATION.between(r.checkInDate, r.checkOutDate).days);`
    C) `MATCH (:Booking)-[r:INCLUDES_HOTEL]->(:Hotel) RETURN SUM(DURATION.between(r.checkInDate, r.checkOutDate).days);`
    D) Και οι δύο B και C είναι σωστές.
    **Σωστή Απάντηση:** D
    **Επεξήγηση:** Η συνάρτηση `DURATION.between()` είναι ο σωστός τρόπος υπολογισμού της διαφοράς μεταξύ ημερομηνιών/ωρών. Ο όρος `()-[r:INCLUDES_HOTEL]->()` είναι αρκετός για να βρει όλες τις σχέσεις, αλλά η προσθήκη `(:Booking)` και `(:Hotel)` καθιστά το μοτίβο πιο ακριβές και `C` είναι η πιο ολοκληρωμένη και συνεπής με το μοντέλο.

83. **Ποιο Cypher query βρίσκει όλα τα ξενοδοχεία (`Hotel`) που βρίσκονται στην πόλη "Ρώμη" και έχουν `starRating` 4;**
    A) `MATCH (h:Hotel)-[:LOCATED_IN]->(c:City {cityName: 'Ρώμη'}) WHERE h.starRating = 4 RETURN h.name;`
    B) `MATCH (h:Hotel {starRating: 4})-[:LOCATED_IN]->(c:City {cityName: 'Ρώμη'}) RETURN h.name;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (h:Hotel {city: 'Ρώμη', starRating: 4}) RETURN h.name;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο συντακτικές μορφές είναι έγκυρες. Η `B` ενσωματώνει το φιλτράρισμα ιδιότητας κατευθείαν στον ορισμό του κόμβου.

84. **Ποιο Cypher query επιστρέφει το `flightNumber` των πτήσεων (`Flight`) που αναχωρούν από την 'Αθήνα' (`ATH`) και φτάνουν στη 'Ρώμη' (`FCO`) την ίδια ημερομηνία (`departureDateTime` και `arrivalDateTime` την ίδια ημερομηνία);**
    A) `MATCH (f:Flight)-[:DEPARTS_FROM]->(:Airport {airportCode: 'ATH'}), (f)-[:ARRIVES_AT]->(:Airport {airportCode: 'FCO'}) WHERE f.departureDateTime.date = f.arrivalDateTime.date RETURN f.flightNumber;`
    B) `MATCH (f:Flight)-[:DEPARTS_FROM]->(a1:Airport {airportCode: 'ATH'}), (f)-[:ARRIVES_AT]->(a2:Airport {airportCode: 'FCO'}) WHERE f.departureDateTime.date = f.arrivalDateTime.date RETURN f.flightNumber;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (f:Flight {departureCity: 'ATH', arrivalCity: 'FCO'}) WHERE f.departureDate = f.arrivalDate RETURN f.flightNumber;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο είναι συντακτικά ορθές. Η `.date` επιστρέφει μόνο το μέρος της ημερομηνίας ενός `datetime` αντικειμένου για σύγκριση.

85. **Ποιο Cypher query βρίσκει την εταιρεία ενοικίασης αυτοκινήτων (`RentalCompany`) με τους περισσότερους τύπους αυτοκινήτων (`RentalCar`) που προσφέρει;**
    A) `MATCH (rc:RentalCompany)<-[:OFFERED_BY]-(car:RentalCar) WITH rc, COUNT(DISTINCT car.type) AS numCarTypes RETURN rc.name, numCarTypes ORDER BY numCarTypes DESC LIMIT 1;`
    B) `MATCH (rc:RentalCompany)-[:OFFERED_BY]->(car:RentalCar) WITH rc.name AS companyName, COUNT(DISTINCT car.type) AS typeCount RETURN companyName, typeCount ORDER BY typeCount DESC LIMIT 1;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (rc:RentalCompany)-[:OFFERED_BY]->(car:RentalCar) RETURN rc.name, COUNT(DISTINCT car.type) AS numTypes LIMIT 1;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο επιλογές μετρούν τους διακριτούς τύπους αυτοκινήτων για κάθε εταιρεία (`COUNT(DISTINCT car.type)`), ομαδοποιούν (`WITH`), ταξινομούν και επιστρέφουν το κορυφαίο αποτέλεσμα.

86. **Ποιο Cypher query προσθέτει την ιδιότητα `lastLoggedIn: datetime()` στον πελάτη `C001`;**
    A) `MATCH (c:Customer {customerId: 'C001'}) SET c.lastLoggedIn = datetime();`
    B) `UPDATE (c:Customer {customerId: 'C001'}) ADD lastLoggedIn = datetime();`
    C) `ADD PROPERTY lastLoggedIn TO Customer C001 = datetime();`
    D) `INSERT INTO c:Customer {customerId: 'C001', lastLoggedIn: datetime()};`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** `MATCH` για την εύρεση του κόμβου και `SET` για την προσθήκη/ενημέρωση ιδιότητας με τη χρήση της συνάρτησης `datetime()` για την τρέχουσα χρονική στιγμή.

87. **Ποιο Cypher query επιστρέφει τις πτήσεις (`Flight`) που είναι ακριβότερες από τον μέσο όρο τιμών όλων των πτήσεων;**
    A) `MATCH (f:Flight) RETURN f WHERE f.price > AVG(f.price);`
    B) `MATCH (f:Flight) WITH AVG(f.price) AS avgPrice MATCH (f2:Flight) WHERE f2.price > avgPrice RETURN f2;`
    C) `MATCH (f:Flight) WHERE f.price > (SELECT AVG(price) FROM Flight) RETURN f;`
    D) `MATCH (f:Flight) RETURN f.flightNumber, f.price WHERE f.price > (MATCH (f2:Flight) RETURN AVG(f2.price));`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Χρειαζόμαστε ένα `WITH` statement για να υπολογίσουμε τον μέσο όρο (`avgPrice`) στην πρώτη φάση, και μετά ένα δεύτερο `MATCH` (ή χρήση του `avgPrice` στην `WHERE` του ίδιου `MATCH`) για να φιλτράρουμε τις πτήσεις.

88. **Ποιο Cypher query βρίσκει τους πελάτες (`Customer`) που έχουν κάνει κρατήσεις (`Booking`) με τουλάχιστον μία εκδρομή (`Excursion`) και τουλάχιστον ένα ξενοδοχείο (`Hotel`);**
    A) `MATCH (c:Customer)-[:MADE]->(b:Booking) WHERE EXISTS((b)-[:INCLUDES_EXCURSION]->()) AND EXISTS((b)-[:INCLUDES_HOTEL]->()) RETURN DISTINCT c.firstName, c.lastName;`
    B) `MATCH (c:Customer)-[:MADE]->(b:Booking)-[:INCLUDES_EXCURSION]->(:Excursion), (b)-[:INCLUDES_HOTEL]->(:Hotel) RETURN DISTINCT c.firstName, c.lastName;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (c:Customer) WHERE (c)--(:Excursion) AND (c)--(:Hotel) RETURN DISTINCT c.firstName, c.lastName;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο τρόποι μπορούν να χρησιμοποιηθούν για να διασφαλίσουν την ύπαρξη πολλαπλών διαδρομών (με σχέσεις) από έναν κόμβο.

89. **Ποιο Cypher query επιστρέφει το `name` των ξενοδοχείων (`Hotel`) και το `cityName` των πόλεων στις οποίες βρίσκονται, μόνο για ξενοδοχεία με `starRating` 4 ή 5;**
    A) `MATCH (h:Hotel)-[:LOCATED_IN]->(c:City) WHERE h.starRating IN [4, 5] RETURN h.name, c.cityName;`
    B) `MATCH (h:Hotel {starRating: 4 OR starRating: 5})-[:LOCATED_IN]->(c:City) RETURN h.name, c.cityName;`
    C) `MATCH (h:Hotel)-[:LOCATED_IN]->(c:City) WHERE h.starRating = 4 OR h.starRating = 5 RETURN h.name, c.cityName;`
    D) Και οι δύο A και C είναι σωστές.
    **Σωστή Απάντηση:** D
    **Επεξήγηση:** Και οι δύο συντακτικές μορφές είναι έγκυρες για τον έλεγχο πολλαπλών τιμών μιας ιδιότητας (`IN` ή `OR`).

90. **Ποιο Cypher query επιστρέφει την πιο μακροχρόνια εκδρομή (`Excursion`) (με βάση την `durationHours`);**
    A) `MATCH (e:Excursion) RETURN e.name, e.durationHours ORDER BY e.durationHours DESC LIMIT 1;`
    B) `MATCH (e:Excursion) RETURN MAX(e.durationHours);`
    C) `MATCH (e:Excursion) WHERE e.durationHours = MAX(e.durationHours) RETURN e;`
    D) `SELECT TOP 1 * FROM Excursion ORDER BY durationHours DESC;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Ταξινόμηση κατά φθίνουσα διάρκεια και περιορισμός στο 1 αποτέλεσμα.

91. **Ποιο Cypher query βρίσκει όλους τους κόμβους `Airport` στην πόλη "Αθήνα";**
    A) `MATCH (a:Airport {city: 'Αθήνα'}) RETURN a.airportCode, a.airportName;`
    B) `MATCH (a:Airport) WHERE a.city = 'Αθήνα' RETURN a.airportCode, a.airportName;`
    C) Και οι δύο A και B είναι σωστές.
    D) `SELECT airportCode, airportName FROM Airport WHERE city = 'Αθήνα';`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο είναι συντακτικά ορθές και ισοδύναμες για φιλτράρισμα.

92. **Ποιο Cypher query επιστρέφει τους πελάτες (`Customer`) και τον συνολικό αριθμό εκδρομών (`Excursion`) που έχει συμμετάσχει ο καθένας;**
    A) `MATCH (c:Customer)-[:MADE]->()-[:INCLUDES_EXCURSION]->(e:Excursion) RETURN c.firstName, c.lastName, COUNT(e) AS NumberOfExcursions;`
    B) `MATCH (c:Customer) OPTIONAL MATCH (c)-[:MADE]->()-[:INCLUDES_EXCURSION]->(e:Excursion) RETURN c.firstName, c.lastName, COUNT(e) AS NumberOfExcursions;`
    C) Και οι δύο A και B είναι σωστές (με την `B` να περιλαμβάνει και τους πελάτες χωρίς εκδρομές με `NumberOfExcursions: 0`).
    D) `MATCH (c:Customer)-[:MADE*]->(:Excursion) RETURN c.firstName, c.lastName, COUNT(e);`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Το `OPTIONAL MATCH` είναι απαραίτητο για να συμπεριληφθούν πελάτες που δεν έχουν κάνει καμία εκδρομή (οπότε ο αριθμός εκδρομών τους θα είναι 0). Το `A` θα επιστρέψει μόνο τους πελάτες που έχουν τουλάχιστον μία εκδρομή.

93. **Ποιο Cypher query διαγράφει όλα τα ξενοδοχεία (`Hotel`) με `starRating` κάτω από 3;**
    A) `MATCH (h:Hotel) WHERE h.starRating < 3 DETACH DELETE h;`
    B) `DELETE (h:Hotel) WHERE h.starRating < 3;`
    C) `REMOVE (h:Hotel) WHERE h.starRating < 3;`
    D) `DROP Hotel WHERE starRating < 3;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** `MATCH` βρίσκει τους κόμβους, `WHERE` φιλτράρει, και `DETACH DELETE` διαγράφει τους κόμβους και τις σχέσεις τους.

94. **Ποιο Cypher query βρίσκει την εκδρομή (`Excursion`) με την υψηλότερη τιμή (`price`);**
    A) `MATCH (e:Excursion) RETURN e ORDER BY e.price DESC LIMIT 1;`
    B) `MATCH (e:Excursion) RETURN MAX(e.price) AS MaxPrice;`
    C) `MATCH (e:Excursion) WHERE e.price = MAX(e.price) RETURN e;`
    D) `SELECT Max(price) FROM Excursion;`
    **Σωστή Απάντηση:** A
    **Επεξήγηση:** Ταξινόμηση κατά φθίνουσα τιμή και περιορισμός στα 1 αποτέλεσμα.

95. **Ποιο Cypher query επιστρέφει το `make` και το `model` όλων των ενοικιαζόμενων αυτοκινήτων (`RentalCar`) με `year` 2023;**
    A) `MATCH (rcar:RentalCar {year: 2023}) RETURN rcar.make, rcar.model;`
    B) `MATCH (rcar:RentalCar) WHERE rcar.year = 2023 RETURN rcar.make, rcar.model;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (rcar:RentalCar) WHERE YEAR(rcar.manufactureDate) = 2023 RETURN rcar.make, rcar.model;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο είναι συντακτικά ορθές και κάνουν το ίδιο πράγμα για φιλτράρισμα ιδιοτήτων.

96. **Ποιο Cypher query μετρά το σύνολο των συμμετεχόντων (`participants` ιδιότητα σχέσης) σε όλες τις εκδρομές μέσω των κρατήσεων;**
    A) `MATCH ()-[r:INCLUDES_EXCURSION]->() RETURN SUM(r.participants);`
    B) `MATCH (:Booking)-[r:INCLUDES_EXCURSION]->(:Excursion) RETURN SUM(r.participants);`
    C) Και οι δύο A και B είναι σωστές.
    D) `SELECT SUM(participants) FROM ExcursionBooking;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Η ιδιότητα `participants` βρίσκεται στην σχέση `INCLUDES_EXCURSION`, οπότε πρέπει να προσπελάσουμε την ιδιότητα της σχέσης (`r.participants`) και να χρησιμοποιήσουμε τη συνάρτηση `SUM()` για να αθροίσουμε τις τιμές.

97. **Ποιο Cypher query αλλάζει το `name` του πελάτη `C001` σε "Αντωνία";**
    A) `SET (:Customer {customerId: 'C001'}).firstName = 'Αντωνία';`
    B) `MATCH (c:Customer {customerId: 'C001'}) SET c.firstName = 'Αντωνία';`
    C) `UPDATE Customer SET firstName = 'Αντωνία' WHERE customerId = 'C001';`
    D) `MODIFY (c:Customer {customerId: 'C001'}) ADD firstName = 'Αντωνία';`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Σωστή χρήση `MATCH` και `SET` για ενημέρωση ιδιότητας.

98. **Ποιο Cypher query επιστρέφει το όνομα των πελατών (`Customer`) που έχουν κάνει τουλάχιστον 3 κρατήσεις;**
    A) `MATCH (c:Customer)-[:MADE]->(b:Booking) WITH c, COUNT(b) AS numBookings WHERE numBookings >= 3 RETURN c.firstName, c.lastName;`
    B) `MATCH (c:Customer) WHERE SIZE((c)-[:MADE]->()) >= 3 RETURN c.firstName, c.lastName;`
    C) Και οι δύο A και B είναι σωστές.
    D) `MATCH (c:Customer) RETURN c.firstName, c.lastName WHERE COUNT(c)-[:MADE]->() >= 3;`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο είναι έγκυρες προσεγγίσεις. Η `A` χρησιμοποιεί `WITH` για να κάνει το count και μετά να φιλτράρει, ενώ η `B` χρησιμοποιεί την ενσωματωμένη συνάρτηση `SIZE()` για να μετρήσει άμεσα τις σχέσεις.

99. **Ποιο Cypher query βρίσκει την εκδρομή (`Excursion`) με το `name` "Εκδρομή στην Ακρόπολη" και επιστρέφει το `name` και το `description`;**
    A) `MATCH (e:Excursion {name: 'Εκδρομή στην Ακρόπολη'}) RETURN e.name, e.description;`
    B) `MATCH (e:Excursion) WHERE e.name = 'Εκδρομή στην Ακρόπολη' RETURN e.name, e.description;`
    C) Και οι δύο A και B είναι σωστές.
    D) `GET e.name, e.description FROM Excursion WHERE name = 'Εκδρομή στην Ακρόπολη';`
    **Σωστή Απάντηση:** C
    **Επεξήγηση:** Και οι δύο συντακτικές μορφές είναι έγκυρες και χρησιμοποιούνται συχνά για φιλτράρισμα ιδιοτήτων.

100. **Ποιο Cypher query διαγράφει όλα τα δεδομένα (κόμβους και σχέσεις) από τη βάση δεδομένων;**
    A) `DELETE ALL;`
    B) `MATCH (n) DETACH DELETE n;`
    C) `CLEAR GRAPH;`
    D) `REMOVE EVERYTHING;`
    **Σωστή Απάντηση:** B
    **Επεξήγηση:** Το `MATCH (n)` βρίσκει όλους τους κόμβους (`n`), και το `DETACH DELETE n` διαγράφει κάθε κόμβο μαζί με όλες τις σχέσεις του, καθαρίζοντας τη βάση.
