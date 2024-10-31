public class Main {
	public static void main(String[] args) {
		
		// Δημιουργία ενός αντικειμένου DataSet για αποθήκευση δεδομένων τύπου Measurable
		DataSet ds = new DataSet();
		
		// Δημιουργία αντικειμένων BankAccount με αρχικά υπόλοιπα
		BankAccount ba1 = new BankAccount(1250);
		BankAccount ba2 = new BankAccount(1500);
		BankAccount ba3 = new BankAccount(800);
	
		// Δημιουργία αντικειμένων Athlete με τιμές ρυθμού (pace)
		Athlete a1 = new Athlete(5.5);
		Athlete a2 = new Athlete(4);
		Athlete a3 = new Athlete(6);
		
		// Προσθήκη αντικειμένων Athlete στο DataSet
		ds.addItem(a1);
		ds.addItem(a2);
		ds.addItem(a3);
		
		// Εμφάνιση του μέσου όρου, της μέγιστης και ελάχιστης τιμής από τα αντικείμενα Athlete
		System.out.println("Average is: " + ds.getAverage());
		System.out.println("Max is: " + ds.getMaximum().getMeasure());
		System.out.println("Min is: " + ds.getMinimum().getMeasure());
	}
}
