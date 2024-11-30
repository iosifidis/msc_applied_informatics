package gr.uom.nba_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service // Δηλώνει ότι αυτή η κλάση είναι service component στο Spring Framework
public class NBAService {
	
	// Λίστα με τις ομάδες
	List<Team> tList = new ArrayList<Team>();
	
	// Μέθοδος για να ορίσουμε τη λίστα των ομάδων
	public void setTeam(ArrayList<Team> t) {
		tList =t;	
	}
	
	// Επιστρέφει τους παίκτες με τους περισσότερους πόντους ταξινομημένους κατά φθίνουσα σειρά
	public ArrayList<Player> getTopScorers() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		// Προσθέτει όλους τους παίκτες από όλες τις ομάδες στη λίστα allPlayers
		for(int i=0;i<tList.size();i++) {
				allPlayers.addAll(tList.get(i).getpList());
		}
		
		// Ταξινομεί τους παίκτες κατά φθίνουσα σειρά με βάση τους πόντους (points)
		for(int i=0;i<allPlayers.size()-1;i++) {
			for(int j=i;j<allPlayers.size();j++) {
				if(allPlayers.get(j).getStats().getPoints() > allPlayers.get(i).getStats().getPoints()) {
					// Αντικατάσταση θέσεων για να γίνει ταξινόμηση
					Player temp = allPlayers.get(j);
					allPlayers.set(j, allPlayers.get(i));
					allPlayers.set(i, temp);
				}
			}
		}
		return allPlayers; // Επιστρέφει την ταξινομημένη λίστα
	}
	
	// Επιστρέφει τους παίκτες με τα περισσότερα rebounds ταξινομημένους κατά φθίνουσα σειρά
	public ArrayList<Player> getTopRebounders() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		// Προσθέτει όλους τους παίκτες από όλες τις ομάδες στη λίστα allPlayers
		for(int i=0;i<tList.size();i++) {
			allPlayers.addAll(tList.get(i).getpList());
		}
		
		// Ταξινομεί τους παίκτες κατά φθίνουσα σειρά με βάση τα rebounds
		for(int i=0;i<allPlayers.size()-1;i++) {
			for(int j=i;j<allPlayers.size();j++) {
				if(allPlayers.get(j).getStats().getRebounds() > allPlayers.get(i).getStats().getRebounds()) {
					// Αντικατάσταση θέσεων για να γίνει ταξινόμηση
					Player temp = allPlayers.get(j);
					allPlayers.set(j, allPlayers.get(i));
					allPlayers.set(i, temp);
				}
			}
		}
		return allPlayers; // Επιστρέφει την ταξινομημένη λίστα
	}
	
	// Επιστρέφει τους παίκτες με τις περισσότερες assists ταξινομημένους κατά φθίνουσα σειρά
	public ArrayList<Player> getTopPassers() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		// Προσθέτει όλους τους παίκτες από όλες τις ομάδες στη λίστα allPlayers
		for(int i=0;i<tList.size();i++) {
			allPlayers.addAll(tList.get(i).getpList());
		}
		
		// Ταξινομεί τους παίκτες κατά φθίνουσα σειρά με βάση τις assists
		for(int i=0;i<allPlayers.size()-1;i++) {
			for(int j=i;j<allPlayers.size();j++) {
				if(allPlayers.get(j).getStats().getAssists() > allPlayers.get(i).getStats().getAssists()) {
					// Αντικατάσταση θέσεων για να γίνει ταξινόμηση
					Player temp = allPlayers.get(j);
					allPlayers.set(j, allPlayers.get(i));
					allPlayers.set(i, temp);
				}
			}
		}
		return allPlayers; // Επιστρέφει την ταξινομημένη λίστα
	}

	// Επιστρέφει τους "top players" με βάση το άθροισμα των points, rebounds και assists
	public ArrayList<Player> getTopPlayers() {
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		// Προσθέτει όλους τους παίκτες από όλες τις ομάδες στη λίστα allPlayers
		for(int i=0;i<tList.size();i++) {
			allPlayers.addAll(tList.get(i).getpList());
		}
		
		// Ταξινομεί τους παίκτες κατά φθίνουσα σειρά με βάση το άθροισμα των στατιστικών (points, rebounds, assists)
		for(int i=0;i<allPlayers.size()-1;i++) {
			for(int j=i;j<allPlayers.size();j++) {
				if(allPlayers.get(j).getStats().getPoints()+allPlayers.get(j).getStats().getRebounds()+allPlayers.get(j).getStats().getAssists() > allPlayers.get(i).getStats().getPoints()+allPlayers.get(i).getStats().getRebounds()+allPlayers.get(i).getStats().getAssists()) {
					// Αντικατάσταση θέσεων για να γίνει ταξινόμηση
					Player temp = allPlayers.get(j);
					allPlayers.set(j, allPlayers.get(i));
					allPlayers.set(i, temp);
				}
			}
		}
		return allPlayers; // Επιστρέφει την ταξινομημένη λίστα
	}

}
