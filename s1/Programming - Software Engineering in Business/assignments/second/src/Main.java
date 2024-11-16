// Efstathios Iosifidis - mai25017

public class Main {
    public static void main(String[] args) {
    	// Δημιουργία αντικειμένου με την χρήση του 2ου κατασκευαστή
        Contact workAddress1 = new Contact("Kassandrou 150", "Thessaloniki", 54634, "2310234567");
        Contact homeAddress1 = new Contact("Egnatia 40", "Thessaloniki", 44656, "2310897678");
        Loaner loaner1 = new Loaner("Papadopoulos Periklhs", homeAddress1, workAddress1, 5000);

        // Δημιουργία αντικειμένου με την χρήση του 1ου κατασκευαστή
        Loaner loaner2 = new Loaner("Efstathios Iosifidis", "Egnatias 156", "Thessaloniki", 54006, "2310000000", 
        		"Egnatias 186", "Thessaloniki", 54249, "6990000000", 4000);
        
        // Αποστολή 4 υπενθυμίσεις.
        for (int i = 0; i < 4; i++) {
        	loaner1.Reminder();
        }

        // Πληρωμή 2ου δανειστή
        loaner2.payment(4000);
        loaner2.Reminder();
        
        // 1η πληρωμή 1ου δανειστή και εμφάνιση μηνύματος
        loaner1.payment(2000);
        loaner1.Reminder();

        // 2η πληρωμή 1ου δανειστή και εμφάνση μηνύματος
        loaner1.payment(3000);
        loaner1.Reminder();
        
    }
}