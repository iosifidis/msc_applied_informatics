package com.example.demo.hello;

import java.sql.*;
import java.util.*;
import org.springframework.stereotype.*;

@Service  // Δηλώνει ότι αυτή η κλάση είναι Service (επίπεδο επιχειρησιακής λογικής).
public class HelloService {
	
	// Λίστα όπου αποθηκεύονται προσωρινά οι φοιτητές.
	List<Student> sList;
	
	/**
     * Δημιουργεί και επιστρέφει έναν νέο φοιτητή με βάση τα δεδομένα που δίνονται.
     * @param n Όνομα του φοιτητή.
     * @param a Ηλικία του φοιτητή.
     * @param l Τοποθεσία του φοιτητή.
     * @return Ένα νέο αντικείμενο Student.
     */
	public Student getStudent(String n, int a, String l) {
		return new Student(n, a, l); 
	}

	/**
     * Προσθέτει έναν φοιτητή στη λίστα και τον αποθηκεύει στη βάση δεδομένων.
     * @param s Το αντικείμενο Student που θα προστεθεί.
     * @throws Exception Αν προκύψει σφάλμα κατά την αποθήκευση στη βάση δεδομένων.
     */
	// Εδώ περνάμε αντικείμενο Student ΚΑΝΑΜΕ ΑΛΛΑΓΗ!!!
	public void addStudent(Student s) throws Exception {
		sList.add(s); // Προσθήκη του φοιτητή στη λίστα (εντός μνήμης).
        saveToDB(s);  // Αποθήκευση του φοιτητή στη βάση δεδομένων.
	}

	/**
     * Επιστρέφει όλους τους φοιτητές από τη λίστα.
     * @return Λίστα με όλους τους φοιτητές.
     * @throws Exception Αν προκύψει σφάλμα κατά την ανάκτηση δεδομένων.
     */
	public List<Student> getAllStudents() throws Exception {
		return sList;
	}
	
	/**
     * Αποθηκεύει έναν φοιτητή στη βάση δεδομένων.
     * @param s Το αντικείμενο Student που θα αποθηκευτεί.
     * @throws Exception Αν προκύψει σφάλμα κατά τη σύνδεση ή την αποθήκευση στη βάση.
     */
	private void saveToDB(Student s)  throws Exception{
		// Δημιουργία σύνδεσης με τη βάση δεδομένων MySQL.
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://localhost/university";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "");

        // Εντολή SQL για εισαγωγή φοιτητή στη βάση.
        String query = "insert into students values (?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, s.getName());    // Όνομα φοιτητή.
        preparedStmt.setInt(2, s.getAge());       // Ηλικία φοιτητή.
        preparedStmt.setString(3, s.getLocation());// Τοποθεσία φοιτητή.
        preparedStmt.execute(); // Εκτέλεση της εντολής.
    }

    /**
     * Ενημερώνει τη λίστα με φοιτητές που φορτώθηκαν από τη βάση δεδομένων.
     * @param loadFromDB Η λίστα με τους φοιτητές από τη βάση.
     */
    public void setList(ArrayList<Student> loadFromDB) {
        sList = loadFromDB;
	}
}
