import java.util.ArrayList;

public class ExaminationBook {
	
    // Λίστες υποψηφίων για κάθε πτυχίο
    private ArrayList<Candidate> FCE; // Υποψήφιοι για το "Cambridge First Certificate"
    private ArrayList<Candidate> PCE; // Υποψήφιοι για το "Cambridge Proficiency Certificate"

    // Κατασκευαστής που αρχικοποιεί τις λίστες
    public ExaminationBook() {
        FCE = new ArrayList<>();
        PCE = new ArrayList<>();
    }

    // Προσθήκη υποψηφίου στη σωστή λίστα βάσει του τίτλου πτυχίου
    public void addCandidate(Candidate aCandidate) {
    	
    	String title = aCandidate.getTitle();
    	// Αγνόηση κεφαλαίων πεζών ενώ η equals πρέπει να είναι ακριβώς
        if (title.equalsIgnoreCase("Cambridge First Certificate")) { // Όχι == γιατί ελέγχει τις θέσεις μνήμης που θα είναι διαφορετικές
            FCE.add(aCandidate);
        } else if (title.equalsIgnoreCase("Cambridge Proficiency Certificate")) {
            PCE.add(aCandidate);
        } else {
            System.out.println("There is no such certificate!");
        }
    }
    
	
    // Επιστροφή της λίστας FCE
    public ArrayList<Candidate> getFCE() {
        return FCE;
    }

    // Επιστροφή της λίστας PCE
    public ArrayList<Candidate> getPCE() {
        return PCE;
    }
	
    // Επιστροφή λίστας υποψηφίων που δεν έχουν πληρώσει
	public ArrayList<Candidate> rejectedCandidates(ArrayList<Candidate> list) {
		
		ArrayList<Candidate> blackList = new ArrayList<>();
		
		for(Candidate c : list) {
			
			if(c.getMoney() == 0) {
				blackList.add(c);
			}
		}
		
		return blackList;
	}
	
    // Εμφάνιση των υποψηφίων στο FCE που έχουν πληρώσει
    public void showFCECandidates() {
        int num = 0; // Πλήθος υποψηφίων που έχουν πληρώσει
        int sum = 0; // Συνολικό ποσό χρημάτων

        for (Candidate aCandidate : FCE) {
            if (aCandidate.getMoney() > 0) {
                System.out.println(aCandidate.getName());
                num++;
                sum += aCandidate.getMoney();
            }
        }

        System.out.println("Πλήθος υποψηφίων: " + num);
        System.out.println("Σύνολο χρημάτων: " + sum);
    }
	
}