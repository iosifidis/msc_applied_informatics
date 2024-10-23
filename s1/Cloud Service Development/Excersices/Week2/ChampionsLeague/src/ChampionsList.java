import java.util.ArrayList;

public class ChampionsList {
	
	// Μια λίστα από αντικείμενα Champion
	private ArrayList<Champion> cList = new ArrayList<Champion>();
	
	// Μέθοδος προσθήκης αντικειμένου τύπου Champion στην λίστα
	public void addChampion(Champion c) {
		cList.add(c);
	}
	
	// Μέθοδος εκτύπωσης ΟΛΩΝ των ομάδων στο τερματικό
	public void printInfo() {
		System.out.println("List of Champions");
		// Εκτέλεση for για να εκτυπωθούν όλα τα αντικείμενα της λίστας
		for (int i=0; i<cList.size();i++) {
			cList.get(i).printInfo();
		}
	
	}

}
