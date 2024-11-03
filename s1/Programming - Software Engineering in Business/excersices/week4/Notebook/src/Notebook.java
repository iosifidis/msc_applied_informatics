import java.util.ArrayList;

//Η κλάση Notebook αποθηκεύει πολλές σημειώσεις
public class Notebook {

	private String owner; // Ιδιοκτήτης του σημειωματάριου
	
	// Χρησιμοποιούμε ArrayList<String> για αποθήκευση σημειώσεων, ο τύπος <> καθορίζει ότι αποθηκεύουμε Strings
	private ArrayList<String> notes = new ArrayList<>();

	// Κατασκευαστής που αρχικοποιεί τον ιδιοκτήτη του σημειωματάριου
	public Notebook(String owner) {
		this.owner = owner;
	}
	
	// Μέθοδος για εισαγωγή νέας σημείωσης στην ArrayList
	public void storeNote(String note) {
		
		notes.add(note); // Προσθήκη της σημείωσης στο τέλος της λίστας
	}
	
	// Μέθοδος για εκτύπωση όλων των σημειώσεων
	public void listNotes() {
		System.out.println("===This is " + owner + " notebook===");
		
		// Επανάληψη για εκτύπωση κάθε σημείωσης. Μέγεθος του πίνακα με την μέθοδο size()
		for(int i=0; i<notes.size(); i++) {
			// Κρατάμε σε μεταβλητή το περιεχόμενο της i-οστής σημείωσης (notes.get(i))
			String aNote = notes.get(i);
			 // Εμφανίζουμε την σημείωση με αύξοντα αριθμό
			System.out.println((i+1) + ". " + aNote);
			
			// Εναλλακτικά: System.out.println(notes.get(i));
	
//		// Εναλλακτικός τρόπος με Foreach (Τι τύπος είναι τα αντικείμενα μέσα στον πίνακα : όνομα πίνακα
//		for(String aNote : notes) {
//			System.out.println(aNote);
		}
			
	}
	
	// Μέθοδος για εμφάνιση συγκεκριμένης σημείωσης βάσει του αριθμού γραμμής
	public void showNote(int noteNumber) {
		
		// Έλεγχος αν ο αριθμός σημείωσης είναι εντός ορίων των σημειώσεων
		if(noteNumber>=0 && noteNumber<notes.size()) {
			System.out.println(notes.get(noteNumber));
		}
		else {
			System.out.println("Not a valid note number"); // Μήνυμα λάθους αν ο αριθμός δεν είναι έγκυρος
		}
	}
	
	// Μέθοδος για διαγραφή συγκεκριμένης σημείωσης βάσει του αριθμού γραμμής
	public void removeNote(int noteNumber) {
		
		// Έλεγχος αν ο αριθμός σημείωσης είναι εντός ορίων των σημειώσεων
		if(noteNumber>=0 && noteNumber<notes.size()) {
			notes.remove(noteNumber); // Αφαιρούμε τη σημείωση από την ArrayList με την μέθοδο .remove()
		}
		else {
			System.out.println("Not a valid note number"); // Μήνυμα λάθους αν ο αριθμός δεν είναι έγκυρος
		}
	}
}

/*
Η κλάση Notebook χρησιμοποιείται για τη διαχείριση σημειώσεων ενός χρήστη. Η ArrayList αποθηκεύει σημειώσεις (ως String), 
προσφέροντας ευέλικτη διαχείριση, αφού η λίστα μπορεί να προσαρμόζεται δυναμικά σε μέγεθος. 
Η κλάση διαθέτει μεθόδους για:

1. Εισαγωγή σημειώσεων μέσω της storeNote, η οποία προσθέτει νέες σημειώσεις στη λίστα.
2. Εκτύπωση όλων των σημειώσεων με την listNotes, χρησιμοποιώντας επαναληπτική δομή για εμφάνιση κάθε σημείωσης.
3. Εμφάνιση συγκεκριμένης σημείωσης με την showNote, βάσει του αριθμού γραμμής, ελέγχοντας αν η γραμμή είναι εντός ορίων.
4. Διαγραφή συγκεκριμένης σημείωσης με την removeNote, επίσης βάσει αριθμού γραμμής με αντίστοιχο έλεγχο για εγκυρότητα.
*/