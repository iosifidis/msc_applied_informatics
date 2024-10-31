
// Προσθέτω το implements Measurable
public class Athlete implements Measurable {
	private double pace;

	public Athlete(double pace) {
		// Αρχικοποίηση του ρυθμού
		this.pace = pace;
	}

	//Αλλάζω από getPace σε getMeasure
	public double getMeasure() {
		// Επιστρέφει τον ρυθμό του αθλητή
		return pace;
	}
}
