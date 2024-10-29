
public class NumberDisplay {

    private int value; // Η τρέχουσα τιμή (π.χ., ώρα ή λεπτό)
    private int limit; // Όριο που καθορίζει το μέγιστο επιτρεπτό για το value πριν μηδενιστεί
    
    // Κατασκευαστής με όριο χωρίς αρχική τιμή. Το rollOverLimit ονομάζεται τυπική παράμετρος
    public NumberDisplay(int rollOverLimit) {
        limit = rollOverLimit; // Θέτουμε το όριο
        value = 0; // Μηδενική αρχική τιμή
    }

	
    // Υπερφόρτωση κατασκευαστή (όταν έχω 2 κατασκευαστές) 
    // Εισαγωγή ορίου και αρχικής τιμής για το value
    public NumberDisplay(int rollOverLimit, int v) {
        limit = rollOverLimit;
        value = v;
    }
	
    // Getter για την επιστροφή της τρέχουσας τιμής του value
    public int getValue() {
        return value;
    }

    // Setter για την αλλαγή της τιμής του value εφόσον είναι εντός των ορίων
	public void setValue(int replacementValue) {
		
		// Έλεγχος για εγκυρότητα της τιμής πριν την ανάθεση
		// Πρέπει να εξασφαλίσουμε ότι η τιμή που θα έρθει θα είναι σωστή και μετά να την αλλάξω.
		 if((replacementValue >= 0) && (replacementValue < limit)) {
			 value = replacementValue;
		 }
	}
	
    // Getter για το όριο του value
    public int getLimit() {
        return limit;
    }

    // Setter για την αλλαγή του ορίου
    public void setLimit(int limit) {
        this.limit = limit;
    }   
	
    // Επιστρέφει την τιμή ως αλφαριθμητικό με δύο χαρακτήρες (π.χ., "07" αντί "7")
    public String getDisplayValue() {
        // Επιστρέφουμε την τιμή με μηδενικό πριν από αριθμούς μικρότερους του 10
        if(value < 10) {
            return "0" + value;
        } else {
            return "" + value;
        }
    }
	
    // Μέθοδος για αύξηση της τιμής κατά 1
    public void increment() {
        value++; // Αυξάνουμε κατά 1 την τιμή
        // Ελέγχουμε αν η τιμή υπερβαίνει το όριο, και την μηδενίζουμε αν το ξεπεράσει
        if (value == limit) {
            value = 0;
        }
        // Εναλλακτικά μπορούμε να χρησιμοποιήσουμε την έκφραση: value = (value + 1) % limit;
    }
}
