
public class ElectricVehicle extends Vehicle {

	private int batteries;
	
	// Κατασκευαστής
	public ElectricVehicle(int weight, int length, int seats, int batteries) {
		super(weight, length, seats);
		this.batteries = batteries;
	}
	
	// Υπολογισμός μιλίων (πολυμοορφισμός)
	public double calculateMileage() {
		return 75 * batteries;
	}
	
	
}
