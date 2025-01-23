import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		//Φτιάχνω μια λίστα
		ArrayList<Ship> ships = new ArrayList<>();
		
		//Φτιάχνω αντικείμενα πλοία
		Ship ship1 = new Ship("Zeus", 2);
		Ship ship2 = new Ship("Cobacabana", 100);
		Ship ship3 = new Ship("Tinos", 220);
		Ship ship4 = new Ship("Hercules", 180);
		Ship ship5 = new Ship("SuperStar", 240);
		Ship ship6 = new Ship("Olympia", 320);

		//Προσθέτω τα πλοία στην λίστα
		ships.add(ship1);
		ships.add(ship2);
		ships.add(ship3);
		ships.add(ship4);
		ships.add(ship5);
		ships.add(ship6);
		
		// Εκκίνηση παραθύρου και στέλνω το ArrayList ships στη γραφική διασύνδεση μέσω του κατασκευαστή
		new ContainerFrame(ships);

		
		
		//======== ΕΡΩΤΗΜΑΤΑ Α και Β =================
//		//Φτιάχνω αντικείμενο πλοίο με 450 κοντέινερ (ΕΡΩΤΗΜΑ Β)
//				Ship ship1 = new Ship("Zeus", 450);
//
//				//Φτιάχνω αντικείμενα container		
//				Bulk container1 = new Bulk("XY101", "Barcelona", 980);
//				Refridgerator container2 = new Refridgerator("XX250", "Piraeus", 11.7);
//				Bulk container3 = new Bulk("AB430", "Genova", 1700);
//				Refridgerator container4 = new Refridgerator("KL330", "Chania", 5.8);
//				
//				// Καταχωρώ τα containers στο πλοίο
//				ship1.addContainer(container1);
//				ship1.addContainer(container2);
//				ship1.addContainer(container3);
//				ship1.addContainer(container4);
//
//				// Εκκίνηση παραθύρου και στέλνω το αντικείμενο πλοίο στη γραφική διασύνδεση μέσω του κατασκευαστή
//				ChargeCalculator chargeFrame = new ChargeCalculator(ship1);	

	
	}

}
