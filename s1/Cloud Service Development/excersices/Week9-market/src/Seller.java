package gr.uom.market_week9;

import java.util.*;
import javax.persistence.*;

// Δηλώνει ότι αυτή η κλάση είναι μια οντότητα (entity) που αντιπροσωπεύει έναν πίνακα στη βάση δεδομένων.
@Entity
public class Seller {

    // Δηλώνει ότι το πεδίο `name` είναι το πρωτεύον κλειδί (primary key) του πίνακα.
    @Id
    private String name;

    // Συσχέτιση ενός προς πολλά (One-to-Many) με την οντότητα `Sale`.
    // Η συσχέτιση ορίζεται από το πεδίο `seller` στην οντότητα `Sale` (mappedBy).
    // Χρήση cascade για αυτόματη διαχείριση των πωλήσεων (π.χ., δημιουργία, διαγραφή).
    // Το FetchType.LAZY φορτώνει τις πωλήσεις μόνο όταν χρειάζονται.
    @OneToMany(
        mappedBy = "seller",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    private List<Sale> sales = new ArrayList<>();

    // Προεπιλεγμένος κατασκευαστής (default constructor) για αρχικοποίηση της κλάσης.
    public Seller() {}

    // Κατασκευαστής με ορίσματα για αρχικοποίηση του ονόματος του πωλητή.
    public Seller(String n) {
        name = n;
    }

    // Getter μέθοδος για το πεδίο `name` (όνομα πωλητή).
    public String getName() {
        return name;
    }

    // Μέθοδος για προσθήκη μιας πώλησης (Sale) στον πωλητή.
    // Ενημερώνει τη λίστα των πωλήσεων και ορίζει την αντίστροφη σχέση στον `Sale`.
    public void addSale(Sale s) {
        sales.add(s); // Προσθήκη της πώλησης στη λίστα.
        s.setSeller(this); // Ορισμός του τρέχοντος πωλητή στην πώληση.
    }
}
