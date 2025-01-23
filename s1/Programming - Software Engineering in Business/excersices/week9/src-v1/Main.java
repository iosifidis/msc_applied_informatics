
public class Main {

	public static void main(String[] args) {
		
		//Φτιάχνω αντικείμενο πλοίο με 450 κοντέινερ (ΕΡΩΤΗΜΑ Β)
		Ship ship1 = new Ship("Zeus", 450);

		//Φτιάχνω αντικείμενα container		
		Bulk container1 = new Bulk("XY101", "Barcelona", 980);
		Refridgerator container2 = new Refridgerator("XX250", "Piraeus", 11.7);
		Bulk container3 = new Bulk("AB430", "Genova", 1700);
		Refridgerator container4 = new Refridgerator("KL330", "Chania", 5.8);
		
		// Καταχωρώ τα containers στο πλοίο
		ship1.addContainer(container1);
		ship1.addContainer(container2);
		ship1.addContainer(container3);
		ship1.addContainer(container4);

		// Εκκίνηση παραθύρου και στέλνω το αντικείμενο πλοίο στη γραφική διασύνδεση μέσω του κατασκευαστή
		ChargeCalculator chargeFrame = new ChargeCalculator(ship1);
	}

}
