package gr.uom.nba_app;

public class Player {
	
	String name;
	Statistics stats;
	
	public Player(String name, Statistics stats) {
		this.name = name;
		this.stats = stats;
	}

	public String getName() {
		return name;
	}

	public Statistics getStats() {
		return stats;
	}
	
	
	

}
