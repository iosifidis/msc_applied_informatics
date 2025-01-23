
public class Main
{
    public static void main(String[] args) {
        
        //Δημιουργήστε εδώ τα αντικείμενα που ζητούνται στο Β ερώτημα του 3ου θέματος
    	GasVehicle veh1 = new GasVehicle(1200, 420, 5, 1500, 55, 5.9);
    	GasVehicle veh2 = new GasVehicle(900, 410, 2, 2000, 45, 8.5);
    	ElectricVehicle veh3 = new ElectricVehicle(1600, 380, 4, 6);
    	ElectricVehicle veh4 = new ElectricVehicle(1450, 390, 5, 8);
    	
    	// Δημιουργία εταιρίας
    	Company companyX = new Company("Company X");
    	
    	// Προσθήκη στόλου στην εταιρία
    	companyX.addVehicle(veh1);
    	companyX.addVehicle(veh2);
    	companyX.addVehicle(veh3);
    	companyX.addVehicle(veh4);
    	
    	// Εκκίνηση της γραφικής διεπαφής
        new GUI(companyX);
    }
}
