package gr.uom.market_week7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration  // Δηλώνει ότι αυτή η κλάση περιέχει ρυθμίσεις και bean definitions.
public class MarketConfig implements CommandLineRunner {

    // Χρήση του MarketService μέσω @Autowired για εξάρτηση (dependency injection)
    @Autowired
    private MarketService ms;
    
    // Η μέθοδος run εκτελείται αυτόματα κατά την εκκίνηση της εφαρμογής
    @Override
    public void run(String... args) throws Exception {
        // Φορτώνει τη λίστα προϊόντων από τη βάση δεδομένων και την αποθηκεύει στο MarketService
        ms.setList(loadFromDB());
        // Εκτύπωση μηνύματος για επιβεβαίωση ότι τα δεδομένα φορτώθηκαν επιτυχώς
        System.out.println("DB has been loaded to MarketService!!!");
    }

    // Ιδιωτική μέθοδος για τη φόρτωση προϊόντων από τη βάση δεδομένων
    private ArrayList<Product> loadFromDB() throws Exception {
        // Δημιουργία λίστας για την αποθήκευση των προϊόντων
        ArrayList<Product> pList = new ArrayList<Product>();
        
        // Ορισμός του driver για τη σύνδεση με MySQL
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/market";
        Class.forName(myDriver); // Φόρτωση της κλάσης του driver
        Connection conn = DriverManager.getConnection(myUrl, "root", ""); // Δημιουργία σύνδεσης
        
        // Ερώτημα SQL για την ανάκτηση όλων των προϊόντων
        String query = "SELECT * FROM products";

        // Δημιουργία δήλωσης (statement) για την εκτέλεση του ερωτήματος
        Statement st = conn.createStatement();
        
        // Εκτέλεση του ερωτήματος και αποθήκευση των αποτελεσμάτων σε ResultSet
        ResultSet rs = st.executeQuery(query);
        
        // Επανάληψη για την επεξεργασία κάθε γραμμής του αποτελέσματος
        while (rs.next()) {
            // Ανάκτηση δεδομένων από τη γραμμή και δημιουργία αντικειμένου Product
            int code = rs.getInt("code");  
            String name = rs.getString("name");
            int price = rs.getInt("price");
            pList.add(new Product(code, name, price)); // Προσθήκη του προϊόντος στη λίστα
        }
        
        // Κλείσιμο του statement για απελευθέρωση πόρων
        st.close();
        
        // Επιστροφή της λίστας προϊόντων
        return pList;
    }
}
