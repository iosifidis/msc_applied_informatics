package gr.uom.nba_app;

import java.util.ArrayList;

public class Team {
	
	String name;
	String city;
	ArrayList<Player> pList = new ArrayList<Player>();
	
	// Κατασκευαστής
	public Team(String name, String city, ArrayList<Player> pList) {
		this.name = name;
		this.city = city;
		this.pList = pList;
	}

	public Team(String name, String city) {
		this.name = name;
		this.city = city;
	}

	// Getters
	public String getName() {
		return name;
	}


	public String getCity() {
		return city;
	}


	public ArrayList<Player> getpList() {
		return pList;
	}
	
	// Η μέθοδος addPlayer προσθέτει έναν παίκτη στη λίστα των παικτών (pList).
	public void addPlayer(Player player) {
		pList.add(player); // Προσθέτει το αντικείμενο player στη λίστα pList.
	}
	

}
