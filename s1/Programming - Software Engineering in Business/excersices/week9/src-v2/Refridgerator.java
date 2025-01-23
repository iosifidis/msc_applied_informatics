
public class Refridgerator extends Container {

	private double power;
	
	// Κατασκευαστής
	public Refridgerator(String code, String destination, double power) {
		super(code, destination);
		this.power=power;
	}

	// Μέθοδος υπολογισμού χρέωσης (πολυμοορφισμός) (ΕΡΩΤΗΜΑ Α)
	@Override
	public double calculateCharge() {
		return 2000*power;
	}

}
