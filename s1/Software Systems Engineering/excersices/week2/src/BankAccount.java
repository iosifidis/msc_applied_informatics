
// Προσθέτω το implements Measurable
public class BankAccount implements Measurable {
	private double balance;

	public BankAccount(double balance) {
		// Αρχικοποίηση του υπολοίπου
		this.balance = balance;
	}
	
	public double getMeasure() {
		// Επιστρέφει το υπόλοιπο του λογαριασμού
		return balance;
	}
}
