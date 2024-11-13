
public class Main {
	
	// Η κύρια μέθοδος όπου εκτελείται το πρόγραμμα
	public static void main(String[] args) {
		
		
		// Καλούμε τη στατική μέθοδο getCounter χωρίς να δημιουργήσουμε αντικείμενο της κλάσης Soldier,
		// αφού οι στατικές μέθοδοι καλούνται με το όνομα της κλάσης.
		System.out.println("Number of Soldiers: " + Soldier.getCounter());
		
		// Δημιουργία δύο αντικειμένων της κλάσης Soldier με συγκεκριμένα IDs
		Soldier S1 = new Soldier("YX101");
		Soldier S2 = new Soldier("ZL202");
		
		// Εκτύπωση του αριθμού των στρατιωτών μέσω της στατικής μεθόδου getCounter
		System.out.println("Number of Soldiers: " + Soldier.getCounter());
		
		// Καλούμε τη στατική μέθοδο getCounter μέσω ενός αντικειμένου (S1).
		// Αυτή είναι μια έγκυρη σύνταξη, αλλά η ορθή πρακτική είναι να καλέσουμε τη μέθοδο μέσω του ονόματος της κλάσης.
		System.out.println("Number of Soldiers: " + S1.getCounter());

		// Σωστότερη πρακτική: χρήση Soldier.getCounter() αντί για S1.getCounter().
	}

}