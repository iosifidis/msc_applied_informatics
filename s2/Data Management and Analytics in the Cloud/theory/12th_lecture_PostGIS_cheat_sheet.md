## PostGIS Cheat Sheet: Χωρικές Σχέσεις & Συνδέσεις

### Εισαγωγή
Αυτός ο οδηγός περιέχει τις βασικές εντολές PostGIS για την ανάλυση χωρικών σχέσεων και την εκτέλεση χωρικών συνδέσεων (spatial joins), απαραίτητες για τη διαχείριση και αναλυτική δεδομένων στο υπολογιστικό νέφος.

---

### 1. Βασικές Συναρτήσεις Χωρικών Σχέσεων (Επιστρέφουν `TRUE`/`FALSE`)

Ελέγχουν πώς αλληλεπιδρούν δύο γεωμετρίες στο χώρο.

*   **`ST_Equals(geometry A, geometry B)`**
    *   **Περιγραφή:** Ελέγχει αν δύο γεωμετρίες είναι χωρικά ίσες (ίδιου τύπου και πανομοιότυπες συντεταγμένες). Η κατεύθυνση αγνοείται.
    *   **Χρήση:** `SELECT ST_Equals(geom1, geom2);`

*   **`ST_Intersects(geometry A, geometry B)`**
    *   **Περιγραφή:** Επιστρέφει `TRUE` αν τα όρια ή τα εσωτερικά των δύο γεωμετριών τέμνονται (έχουν κοινό χώρο).
    *   **Χρήση:** `SELECT ST_Intersects(neighborhood.geom, station.geom);`
    *   **Σημείωση:** Η πιο συχνά χρησιμοποιούμενη για γενικούς ελέγχους τομής.

*   **`ST_Disjoint(geometry A, geometry B)`**
    *   **Περιγραφή:** Επιστρέφει `TRUE` αν οι δύο γεωμετρίες **δεν** τέμνονται καθόλου (δεν μοιράζονται κανένα σημείο). Είναι το αντίθετο του `ST_Intersects`.
    *   **Χρήση:** `SELECT ST_Disjoint(area1.geom, area2.geom);`
    *   **Σημείωση:** Συχνά είναι πιο αποδοτικό να χρησιμοποιείτε `NOT ST_Intersects(...)` αντί `ST_Disjoint(...)` λόγω χρήσης δεικτών.

*   **`ST_Crosses(geometry A, geometry B)`**
    *   **Περιγραφή:** Επιστρέφει `TRUE` αν η τομή έχει διάσταση μικρότερη από τη μέγιστη διάσταση των αρχικών γεωμετριών, και το σύνολο της τομής βρίσκεται εντός των εσωτερικών και των δύο αρχικών γεωμετριών.
    *   **Συχνές περιπτώσεις:** Γραμμή διασχίζει Πολύγωνο, Γραμμή διασχίζει Γραμμή.
    *   **Χρήση:** `SELECT ST_Crosses(road.geom, river.geom);`

*   **`ST_Overlaps(geometry A, geometry B)`**
    *   **Περιγραφή:** Επιστρέφει `TRUE` αν δύο γεωμετρίες της **ίδιας διάστασης** έχουν κοινό χώρο, αλλά καμία δεν περιέχεται πλήρως στην άλλη.
    *   **Συχνές περιπτώσεις:** Πολύγωνο με Πολύγωνο, Γραμμή με Γραμμή.
    *   **Χρήση:** `SELECT ST_Overlaps(land_plot1.geom, land_plot2.geom);`

*   **`ST_Touches(geometry A, geometry B)`**
    *   **Περιγραφή:** Επιστρέφει `TRUE` αν δύο γεωμετρίες αγγίζουν στα όριά τους, αλλά τα εσωτερικά τους δεν τέμνονται.
    *   **Χρήση:** `SELECT ST_Touches(parcel_a.geom, parcel_b.geom);`

*   **`ST_Within(geometry A, geometry B)`**
    *   **Περιγραφή:** Επιστρέφει `TRUE` αν η γεωμετρία `A` περιέχεται πλήρως εντός της γεωμετρίας `B`.
    *   **Χρήση:** `SELECT ST_Within(point_location.geom, city_boundary.geom);`

*   **`ST_Contains(geometry A, geometry B)`**
    *   **Περιγραφή:** Επιστρέφει `TRUE` αν η γεωμετρία `A` περιέχει πλήρως τη γεωμετρία `B`. (Αντίθετο του `ST_Within`).
    *   **Χρήση:** `SELECT ST_Contains(region.geom, sub_region.geom);`

---

### 2. Συναρτήσεις Απόστασης & Εγγύτητας

*   **`ST_Distance(geometry A, geometry B)`**
    *   **Περιγραφή:** Υπολογίζει την ελάχιστη 2D καρτεσιανή απόσταση μεταξύ δύο γεωμετριών.
    *   **Επιστρέφει:** `float` (μονάδες του SRID).
    *   **Χρήση:** `SELECT ST_Distance(ST_GeomFromText('POINT(0 0)'), ST_GeomFromText('POINT(3 4)'));` (Αποτέλεσμα: 5.0)

*   **`ST_DWithin(geometry A, geometry B, radius)`**
    *   **Περιγραφή:** Επιστρέφει `TRUE` αν οι δύο γεωμετρίες βρίσκονται εντός της καθορισμένης ακτίνας (`radius`). Είναι πολύ αποδοτικό και χρησιμοποιεί χωρικούς δείκτες.
    *   **Επιστρέφει:** `TRUE`/`FALSE`.
    *   **Χρήση:** `SELECT name FROM nyc_streets WHERE ST_DWithin(geom, ST_GeomFromText('POINT(583571 4506714)', 26918), 10);` (Ονόματα οδών σε απόσταση 10 μέτρων)

---

### 3. Συναρτήσεις Μετατροπής & Μετρήσεων

*   **`ST_AsText(geometry)`**
    *   **Περιγραφή:** Μετατρέπει μια γεωμετρία σε αναπαράσταση Well-Known Text (WKT).
    *   **Χρήση:** `SELECT ST_AsText(geom) FROM my_table WHERE id = 1;`

*   **`ST_GeomFromText(text, [srid])`**
    *   **Περιγραφή:** Μετατρέπει μια αναπαράσταση WKT σε γεωμετρία PostGIS. Πρέπει να καθορίσετε το SRID.
    *   **Χρήση:** `SELECT ST_GeomFromText('POINT(10 20)', 4326);`

*   **`ST_Area(geometry)`**
    *   **Περιγραφή:** Υπολογίζει το εμβαδόν ενός πολυγώνου.
    *   **Χρήση:** `SELECT ST_Area(neighborhood.geom) FROM nyc_neighborhoods WHERE name = 'Manhattan';`

*   **`ST_Length(geometry)`**
    *   **Περιγραφή:** Υπολογίζει το μήκος μιας γραμμής.
    *   **Χρήση:** `SELECT ST_Length(road.geom) FROM nyc_streets WHERE name = 'Broadway';`

---

### 4. Χωρικές Συνδέσεις (Spatial Joins)

Συνδυασμός πληροφοριών από διαφορετικούς πίνακες χρησιμοποιώντας χωρικές σχέσεις ως συνθήκη σύνδεσης (`ON`).

*   **Γενική Σύνταξη:**
    ```sql
    SELECT <στήλες>
    FROM TableA AS A
    JOIN TableB AS B
    ON ST_SpatialFunction(A.geom, B.geom)
    WHERE <συνθήκες>
    GROUP BY <στήλες>
    ORDER BY <στήλες>;
    ```

*   **Παράδειγμα: Σταθμός σε Γειτονιά (`ST_Contains`)**
    *   Ερώτημα: Ποια γειτονιά περιέχει τον σταθμό "Broad St";
    ```sql
    SELECT
        s.name AS subway_name,
        n.name AS neighborhood_name,
        n.boroname AS borough
    FROM nyc_neighborhoods AS n
    JOIN nyc_subway_stations AS s
    ON ST_Contains(n.geom, s.geom)
    WHERE s.name = 'Broad St';
    ```

*   **Παράδειγμα: Πληθυσμός σε Γειτονιές (`ST_Intersects` με `GROUP BY` & `SUM`)**
    *   Ερώτημα: Ποιος είναι ο συνολικός πληθυσμός και η φυλετική σύνθεση των γειτονιών του Manhattan;
    ```sql
    SELECT
        n.name AS neighborhood_name,
        SUM(c.popn_total) AS total_population,
        (100.0 * SUM(c.popn_white) / SUM(c.popn_total)) AS white_pct
    FROM nyc_neighborhoods AS n
    JOIN nyc_census_blocks AS c
    ON ST_Intersects(n.geom, c.geom)
    WHERE n.boroname = 'Manhattan'
    GROUP BY n.name
    ORDER BY white_pct DESC;
    ```

---

### 5. Βοηθητικές Συναρτήσεις SQL & Ρήτρες (Συχνά Χρησιμοποιούμενες με PostGIS)

*   **`SUM(expression)`**: Αθροιστική συνάρτηση που επιστρέφει το άθροισμα τιμών.
*   **`COUNT(expression)`**: Αθροιστική συνάρτηση που επιστρέφει το πλήθος των εγγραφών.
*   **`strpos(string, substring)`**: Επιστρέφει την αρχική θέση ενός υπο-συμβολοσειράς μέσα σε μια συμβολοσειρά (1-based index). Επιστρέφει 0 αν δεν βρεθεί.
*   **`round(numeric, integer)`**: Στρογγυλοποιεί έναν αριθμό σε συγκεκριμένο αριθμό δεκαδικών ψηφίων.
*   **`DISTINCT`**: Επιλέγει μόνο μοναδικές τιμές.
*   **`CREATE TABLE` / `INSERT INTO`**: Εντολές για τη δημιουργία πίνακα και την εισαγωγή δεδομένων.
*   **`SELECT`**: Επιλογή στηλών.
*   **`FROM`**: Καθορισμός πινάκων.
*   **`WHERE`**: Φιλτράρισμα γραμμών με συνθήκες.
*   **`ON`**: Καθορισμός συνθήκης σύνδεσης για `JOIN`.
*   **`GROUP BY`**: Ομαδοποίηση γραμμών που έχουν τις ίδιες τιμές σε καθορισμένες στήλες.
*   **`ORDER BY`**: Ταξινόμηση αποτελεσμάτων.
*   **`LIMIT`**: Περιορισμός του αριθμού των γραμμών που επιστρέφονται.

---

### 6. Σημαντικές Συμβουλές για Απόδοση

*   **Χωρικοί Δείκτες (Spatial Indexes):** Πάντα να δημιουργείτε GiST δείκτες στις στήλες `geometry` για ταχύτατη εκτέλεση χωρικών ερωτημάτων.
    *   `CREATE INDEX [idx_name] ON [table_name] USING GIST([geometry_column_name]);`
*   **SRID (Spatial Reference ID):** Βεβαιωθείτε ότι όλες οι γεωμετρίες σε ένα ερώτημα έχουν το ίδιο SRID για σωστές χωρικές συγκρίσεις (π.χ., 26918 για NYC Long Island).
*   **Αποδοτικότητα `ST_DWithin`:** Για ελέγχους εγγύτητας, προτιμήστε πάντα το `ST_DWithin` έναντι της δημιουργίας buffer και του `ST_Intersects`, καθώς το `ST_DWithin` είναι βελτιστοποιημένο να χρησιμοποιεί τους δείκτες.

---
