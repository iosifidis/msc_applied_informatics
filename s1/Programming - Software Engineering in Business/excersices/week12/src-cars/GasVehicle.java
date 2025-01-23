
public class GasVehicle extends Vehicle {

	private int cc;
	private int tankCapacity;
	private double fuelConsumption;
	
	// Κατασκευαστής
	public GasVehicle(int weight, int length, int seats, int cc, int tankCapacity, double fuelConsumption) {
		super(weight, length, seats);
		this.cc = cc;
		this.tankCapacity = tankCapacity;
		this.fuelConsumption = fuelConsumption;
	}
	
	// Υπολογισμός μιλίων (πολυμοορφισμός)
	public double calculateMileage() {
		return 100 * (tankCapacity / fuelConsumption);
	}
	
}
