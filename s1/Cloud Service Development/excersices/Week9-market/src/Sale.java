package gr.uom.market_week9;

import java.util.*;
import javax.persistence.*;

// Δηλώνει ότι αυτή η κλάση είναι μια οντότητα (entity) που αντιπροσωπεύει έναν πίνακα στη βάση δεδομένων.
@Entity
public class Sale {

    // Δηλώνει ότι το πεδίο `code` είναι το πρωτεύον κλειδί (primary key) του πίνακα.
    @Id
    private int code;

    // Το πεδίο `quantity` αντιπροσωπεύει την ποσότητα που αφορά η πώληση.
    private int quantity;

    // Συσχέτιση πολλών προς πολλά (Many-to-Many) με την οντότητα `Product`.
    // Η συσχέτιση αντιστοιχίζεται από την πλευρά του `Product` (mappedBy).
    @ManyToMany(mappedBy = "sales")
    private Set<Product> products = new HashSet<Product>();

    // Συσχέτιση πολλών προς ένα (Many-to-One) με την οντότητα `Seller`.
    // Δημιουργία στήλης `seller_name` για τη σύνδεση με τον πωλητή.
    @ManyToOne
    @JoinColumn(name = "seller_name")
    private Seller seller;

    // Προεπιλεγμένος κατασκευαστής (default constructor) που αρχικοποιεί τα πεδία.
    public Sale() {
    }

    // Κατασκευαστής με ορίσματα για αρχικοποίηση του κωδικού και της ποσότητας.
    public Sale(int c, int q) {
        code = c;
        quantity = q;
    }

    // Μέθοδος για να ορίσουμε τον πωλητή (Seller) που πραγματοποίησε την πώληση.
    public void setSeller(Seller s) {
        seller = s;
    }

    // Getter μέθοδος για το πεδίο `code` (κωδικός πώλησης).
    public int getCode() {
        return code;
    }

    // Getter μέθοδος για το πεδίο `quantity` (ποσότητα πώλησης).
    public int getQuantity() {
        return quantity;
    }

    // Getter μέθοδος για το πεδίο `seller` (πωλητής).
    public Seller getSeller() {
        return seller;
    }

    // Μέθοδος για προσθήκη ενός προϊόντος (Product) στη λίστα προϊόντων που σχετίζονται με την πώληση.
    public void addPoduct(Product p) {
        products.add(p);
    }
}
