package gr.uom.nba_app;

public class Statistics {
	
	int points;
	int rebounds;
	int assists;
	
	public Statistics(int points, int rebounds, int assists) {
		this.points = points;
		this.rebounds = rebounds;
		this.assists = assists;
	}

	public int getPoints() {
		return points;
	}

	public int getRebounds() {
		return rebounds;
	}

	public int getAssists() {
		return assists;
	}

}
