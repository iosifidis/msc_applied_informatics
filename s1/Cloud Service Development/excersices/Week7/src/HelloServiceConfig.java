package com.example.demo.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.context.annotation.*;

@Configuration // Δηλώνει ότι αυτή η κλάση περιέχει ρυθμίσεις και bean definitions.
public class HelloServiceConfig implements CommandLineRunner {

    // Αυτόματη σύνδεση με το HelloService για χρήση των μεθόδων του.
    @Autowired
    private HelloService hs;

    /**
     * Εκτελείται αυτόματα κατά την εκκίνηση της εφαρμογής.
     * Φορτώνει δεδομένα από τη βάση και τα αποθηκεύει στη λίστα του HelloService.
     * @param args Επιχειρήματα από τη γραμμή εντολών.
     * @throws Exception Αν προκύψει σφάλμα κατά τη φόρτωση δεδομένων.
     */
    @Override
    public void run(String... args) throws Exception {
        hs.setList(loadFromDB()); // Φορτώνει τους φοιτητές από τη βάση και τους περνά στη λίστα.
        System.out.println("DB has been loaded to HelloService!!!"); // Μήνυμα επιτυχίας.
    }

    /**
     * Φορτώνει δεδομένα φοιτητών από τη βάση δεδομένων και τα επιστρέφει ως λίστα.
     * @return Λίστα φοιτητών από τη βάση.
     * @throws Exception Αν προκύψει σφάλμα κατά τη σύνδεση ή την ανάκτηση δεδομένων.
     */
    private ArrayList<Student> loadFromDB() throws Exception {
        ArrayList<Student> sList = new ArrayList<>(); // Δημιουργία κενής λίστας φοιτητών.

        // Σύνδεση με τη βάση δεδομένων MySQL.
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/university";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");

        // Ερώτημα SQL για την ανάκτηση όλων των φοιτητών.
        String query = "SELECT * FROM students";

        // Δημιουργία αντικειμένου Statement για εκτέλεση του ερωτήματος.
        Statement st = conn.createStatement();

        // Εκτέλεση του ερωτήματος και αποθήκευση των αποτελεσμάτων σε ResultSet.
        ResultSet rs = st.executeQuery(query);

        // Επανάληψη στα αποτελέσματα για τη δημιουργία αντικειμένων Student.
        while (rs.next()) {
            String name = rs.getString("name");         // Ανάκτηση του ονόματος.
            String location = rs.getString("location");// Ανάκτηση της τοποθεσίας.
            int age = rs.getInt("age");                // Ανάκτηση της ηλικίας.
            sList.add(new Student(name, age, location)); // Προσθήκη του φοιτητή στη λίστα.
        }

        st.close(); // Κλείσιμο του Statement.
        return sList; // Επιστροφή της λίστας φοιτητών.
    }
}
