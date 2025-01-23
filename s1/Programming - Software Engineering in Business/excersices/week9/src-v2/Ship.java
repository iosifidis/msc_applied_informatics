import java.util.ArrayList;

public class Ship {

	// Ιδιότητες
	private String name;
	private int capacity;
	
	// Το αμπάρι του πλοίου. Δείχνει σε ένα αντικείμενο container (αρχή υποκατάστασης, λαμβάνει και τους δυο τύπους)
	private ArrayList<Container> containers = new ArrayList<>();
	
	
	// Κατασκευαστής
		public Ship(String name, int capacity) {
			this.name = name;
			this.capacity = capacity;
		}
	
	// Για να πάρω τα ονόματα των πλοίων
		public String getName(){
			return name;
		}
	
	// Μέθοδος προσθήκης container αν χωράει στο πλοίο	(ΕΡΩΤΗΜΑ Α)
	public void addContainer (Container aContainer) {
		if (containers.size() < capacity) {
			containers.add(aContainer);
		}
		else {
			System.out.println("Sorry the ship is full");
		}
	}
		
	// Υπολογισμός συνολικής χρέωσης (ΕΡΩΤΗΜΑ Α)
	public double calculateTotalCharge() {
		double total = 0;
		for (Container container : containers) {
			total += container.calculateCharge();
		}
		return total;
	}
		
}
