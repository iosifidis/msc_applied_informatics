
public class ClockDisplay {

    // Ιδιότητες
    private NumberDisplay hours; // Αντικείμενο της κλάσης NumberDisplay που κρατά την τιμή των ωρών, με όριο 24
    private NumberDisplay minutes; // Αντικείμενο της κλάσης NumberDisplay που κρατά την τιμή των λεπτών, με όριο 60
    private String displayString; // Αναπαριστά την ώρα ως κείμενο, π.χ., "12:34"
    
 // Κατασκευαστής χωρίς αρχική ώρα (ξεκινά από "00:00")
    public ClockDisplay() {
        // Δημιουργία των αντικειμένων NumberDisplay με τα επιτρεπόμενα όρια για ώρες και λεπτά
        hours = new NumberDisplay(24); // Όριο 24 για τις ώρες (0-23)
        minutes = new NumberDisplay(60); // Όριο 60 για τα λεπτά (0-59)
        
        // Εσωτερική κλήση της μεθόδου updateDisplay για ενημέρωση του displayString
        updateDisplay();
    }
	
    // Κατασκευαστής με αρχικές τιμές για την ώρα και τα λεπτά
    public ClockDisplay(int hour, int minute) {
        // Δημιουργία των αντικειμένων NumberDisplay με συγκεκριμένες αρχικές τιμές
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        
        // Θέτουμε τις αρχικές τιμές ωρών και λεπτών, εφόσον είναι εντός ορίων
        hours.setValue(hour);
        minutes.setValue(minute);
        
        // Εσωτερική κλήση της μεθόδου updateDisplay για ενημέρωση του displayString
        updateDisplay();
    }
    
	// Επειδή επαναλαμβάνεται ένας κώδικας, κάνω νέα μέθοδο.
    // Βάζω private γιατί είναι εσωτερική χρήση. Είναι μέθοδος που ενημερώνει την τιμή του displayString
    // Συνδυάζει την τιμή των ωρών και λεπτών και δημιουργεί την μορφή "ώρες:λεπτά"
    private void updateDisplay() {
    	
    	// Στέλνουμε μηνύματα για να λάβουμε την τιμή των ιδιοτήτων αντικειμένων 
        displayString = hours.getDisplayValue() + ":" + minutes.getDisplayValue();
    }
    
    // Δημόσια μέθοδος για αύξηση του χρόνου κατά 1 λεπτό
    public void timeTick() {
        // Αυξάνει τα λεπτά κατά 1
        minutes.increment();
        // Αν τα λεπτά μηδενιστούν (δηλαδή φτάσουν από το 59 στο 0), αυξάνονται οι ώρες κατά 1
        if (minutes.getValue() == 0) { // Εάν τα λεπτά μηδενιστούν, αυξάνονται οι ώρες
            hours.increment();
        }
   
        // Χρησιμοποιούμε την μέθοδο που φτιάξαμε όπου επαναλαμβάνεται ο κώδικας. 
        // Ενημέρωση της ώρας με την μέθοδο updateDisplay (εσωτερική κλήση μεθόδου)
        updateDisplay();
    }
    
    // Getter μέθοδος για επιστροφή της ώρας ως κείμενο
    public String getTime() {
        return displayString;
    }
    
}