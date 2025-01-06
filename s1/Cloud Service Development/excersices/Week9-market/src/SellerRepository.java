package gr.uom.market_week9;

import org.springframework.data.jpa.repository.JpaRepository;

// Δηλώνει ότι αυτό το interface είναι ένα Repository για την οντότητα `Seller`.
// Το `JpaRepository` παρέχει βασικές CRUD λειτουργίες και άλλες χρήσιμες μεθόδους για τη διαχείριση της βάσης δεδομένων.
public interface SellerRepository extends JpaRepository<Seller, String> {

    // Το `JpaRepository` αυτοματοποιεί τη δημιουργία SQL ερωτημάτων για κοινές λειτουργίες,
    // όπως αποθήκευση, ανάκτηση, ενημέρωση και διαγραφή εγγραφών.
    
    // Δεν απαιτείται πρόσθετος κώδικας για βασικές λειτουργίες,
    // εκτός αν χρειάζονται προσαρμοσμένες μεθόδους αναζήτησης ή λειτουργίες.
}
