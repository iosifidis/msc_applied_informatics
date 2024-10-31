public class DataSet {
	
	// Ιδιότητες
	private int counter;
	private double sum;
	private Measurable maximum; // Αντικείμενο τύπου Measurable
	private Measurable minimum; // Αντικείμενο τύπου Measurable
	
	// Κατασκευαστής
	public DataSet() {
		// Αρχικοποίηση μεταβλητών
		counter = 0;
		sum = 0;
		maximum = null;
		minimum = null;
	}
	
	// Κάναμε την μέθοδο να προσθέτει αντικείμενο τύπου Measurable. Αλλαγή της μεθόδου getMeasure() 
	public void addItem(Measurable x) {
		// Προσθήκη του μετρήσιμου στοιχείου στο άθροισμα
		sum += x.getMeasure();
		
		// Ενημέρωση του maximum αν το στοιχείο έχει μεγαλύτερη τιμή
		if(counter == 0 || x.getMeasure() > maximum.getMeasure())
			maximum = x;
		
		// Ενημέρωση του minimum αν το στοιχείο έχει μικρότερη τιμή
		if(counter == 0 || x.getMeasure() < minimum.getMeasure())
			minimum = x;
		counter++;
	}
	
	public double getAverage() {
		// Επιστρέφει το μέσο όρο ή 0 αν δεν υπάρχουν στοιχεία (αμυντικός προγραμματισμός)
		if(counter == 0)
			return 0;
		return sum / counter;
	}
	
	// Μέθοδος τύπου Measurable
	public Measurable getMaximum() {
		return maximum;
	}
	
	// Μέθοδος τύπου Measurable
	public Measurable getMinimum() {
		return minimum;
	}
	
	
}
