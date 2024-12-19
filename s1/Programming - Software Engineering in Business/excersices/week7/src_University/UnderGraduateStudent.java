// Θέλω στον Student να προσθέσω μια ιδιότητα year. Αν την γράψω στην Student θα κληρονομήσουν και οι GraduateStudent και PhDStudent
//που δεν το θέλω. Οπότε φτιάχνω μια νέα υποκλάση UnderGradStudent

public class UnderGraduateStudent extends Student {
	
	private int year;

	public UnderGraduateStudent(String name, String id, int year) {
		super(name, id);
		this.year = year;
	}
	
	// Επικάλυψη / επαναορισμός μεθόδου
	public void printInfo() {
		super.printInfo();
		System.out.println("Year: " + year);
		
	}
	
	//Αρχή υποκατάστασης: Τα αντικείμενα μιας υποκλάσης είναι και αντικείμενο της υπερκλάσης
	public void printType() {
		System.out.println("Hello, I am an undergraduate");
	}

}
