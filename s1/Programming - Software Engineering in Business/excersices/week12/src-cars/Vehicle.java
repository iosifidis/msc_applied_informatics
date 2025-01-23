
public abstract class Vehicle {
	
	private int weight;
	private int length;
	private int seats;
	
	// Κατασκευαστής
	public Vehicle(int weight, int length, int seats) {
		this.weight = weight;
		this.length = length;
		this.seats = seats;
	}
	
	// Υπολογισμός μιλίων (πολυμοορφισμός)
	//Την φτιάχνω αφηρημένη (abstract) και μετατρέπω και την κλάση αφηρημένη
	public abstract double calculateMileage();
	

}