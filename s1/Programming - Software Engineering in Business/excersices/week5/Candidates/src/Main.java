import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
        // Δημιουργία μιας ηλεκτρονικής βάσης υποψηφίων
        ExaminationBook registry = new ExaminationBook();
		
        // Δημιουργία δύο υποψηφίων με χρήση διαφορετικών κατασκευαστών
		Candidate c1 = new Candidate("Stelios");
		Candidate c2 = new Candidate("Maria", "Cambridge First Certificate", 100);

        // Ρύθμιση του τίτλου και του ποσού για τον πρώτο υποψήφιο
        c1.setTitle("Cambridge First Certificate");
        c1.setMoney(0);

        // Προσθήκη υποψηφίων στην ηλεκτρονική βάση
		registry.addCandidate(c1);
		registry.addCandidate(c2);
		
        // Εμφάνιση των υποψηφίων FCE που έχουν πληρώσει
		registry.showFCECandidates();
		
		// Σε δυο γραμμές
//		ArrayList<Candidate> fce = registry.getFCE();
//		ArrayList<Candidate> rejectedList = registry.rejectedCandidates(fce);

        // Εύρεση και εμφάνιση των υποψηφίων FCE που δεν έχουν πληρώσει
        ArrayList<Candidate> rejectedList = registry.rejectedCandidates(registry.getFCE());
    }

}
