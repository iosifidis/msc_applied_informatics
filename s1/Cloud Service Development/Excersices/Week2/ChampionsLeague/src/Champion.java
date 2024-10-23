
public class Champion {

	// Ιδιότητες
	private String name;
	private String year;
	private String url;
	
	// Κατασκευαστής
	public Champion(String n, String y, String e) {
		name = n;
		year = y;
		url = e;
	}
	
	
	// Μέθοδος εκτύπωσης μιας εγγραφής στο τερματικό
	public void printInfo() {
	System.out.println("\t" + name + " was a champion in: " + year + ", logo:" + url);
	}
	
}
