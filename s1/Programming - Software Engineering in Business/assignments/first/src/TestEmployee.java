public class TestEmployee {
	
    public static void main(String[] args) {
    	
        // Δημιουργία 2 αντικειμένων-υπάλληλους
        Employee employee1 = new Employee("Ελένη Παπαδοπούλου", "ΠΕ", 2, 25, true, 2, 1800);
        Employee employee2 = new Employee("Νίκος Παπαδόπουλος", "ΠΕ", 1, 4, true, 2, 1200);

        // Εμφάνιση στοιχείων των 2 υπαλλήλων
        employee1.displayEmployeeDetails();
        employee2.displayEmployeeDetails();

        // Μείωση αριθμού παιδιών της 1ης υπαλλήλου
        System.out.println("*** Αλλαγή του αριθμού ανήλικων παιδιών του/της Ελένη Παπαδοπούλου");
        employee1.updateNumberOfChildren(-3); // Περιμένουμε σφάλμα επειδή ο αριθμός παιδιών γίνεται αρνητικός
        System.out.println("*** Αλλαγή του αριθμού ανήλικων παιδιών του/της Ελένη Παπαδοπούλου\n");
        employee1.updateNumberOfChildren(-2); // Αφαιρούμε 2 παιδιά (μηδενίζεται ο αριθμός παιδιών)

        // Το επίδομα για κάθε έτος υπηρεσίας είναι 10 ευρώ και για κάθε ανήλικο παιδί 30 ευρώ
        // Ενημέρωση του επιδόματος και εμφάνιση εκ νέου των στοιχείων για κάθε υπάλληλο
        System.out.println("*** Υπολογισμός μηνιαίου εισοδήματος του/της Ελένη Παπαδοπούλου");
        employee1.updateBonus(10, 30);
        employee1.displayEmployeeDetails();

        System.out.println("*** Υπολογισμός μηνιαίου εισοδήματος του/της Νίκος Παπαδόπουλος");
        employee2.updateBonus(10, 30);
        employee2.displayEmployeeDetails();
    }
}
