
public class Candidate {
	
    // Ιδιότητες του υποψηφίου
    private String name;   // Ονοματεπώνυμο υποψηφίου
    private String title;  // Τίτλος πτυχίου ("Cambridge First Certificate" ή "Cambridge Proficiency Certificate")
    private int money;     // Χρήματα που πλήρωσε για τις εξετάσεις

	
 // Κατασκευαστής που αρχικοποιεί όλα τα πεδία
	public Candidate(String name, String title, int money) {
		this.name = name;
		this.title = title;
		this.money = money;
	}
	
	// Κατασκευαστής που αρχικοποιεί το πεδίο name και αφήνει κενά τα υπόλοιπα
    public Candidate(String name) {
        this.name = name;
        this.title = "";
        this.money = 0;
    }

    // Μέθοδος για ρύθμιση του πεδίου title
    public void setTitle(String title) {
        this.title = title;
    }

    // Μέθοδος για ρύθμιση του πεδίου money
    public void setMoney(int money) {
        this.money = money;
    }

    // Μέθοδος που επιστρέφει το όνομα του υποψηφίου
    public String getName() {
        return name;
    }

    // Μέθοδος που επιστρέφει τον τίτλο του πτυχίου
    public String getTitle() {
        return title;
    }

    // Μέθοδος που επιστρέφει το ποσό που πλήρωσε ο υποψήφιος
    public int getMoney() {
        return money;
    }
	
	
	

}
