import java.util.ArrayList;

public class Company {
	
	private String name;
	
	// Στόλος αυτοκινήτων της εταιρίας
	private ArrayList<Vehicle> fleet = new ArrayList<>();

	// Κατασκευαστής
	public Company(String name) {
		this.name = name;
	}
	
	// Μέθοδος προσθήκης αυτοκινήτου στον στόλο
	public void addVehicle(Vehicle aVehicle) {
		fleet.add(aVehicle);
	}
	
	// Μέθοδος υπολογισμού μιλίων
	public double calculateTotalMileage() {
		
		double total = 0;
		for(Vehicle vehicle: fleet) 
			total += vehicle.calculateMileage();

		return total;
		
	}

}
