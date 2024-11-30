// Efstathios Iosifidis mai25017

public class Film {
	
	// Ιδιότητες
	private String title;
	private String director;
	private int playingTime; 
	private boolean candidacy;
	private String showDate;
	
	// Κατασκευαστής ερώτημα 2
	public Film(String title, String director, int playingTime, String showDate) {
		
		this.title = title;
		this.director = director;
		this.playingTime = playingTime;
		this.candidacy = false;
		this.showDate = showDate;
	}

	// Κατασκευαστής ερώτημα 3
    public Film(String title) {
        this.title = title;
        this.director = "";
        this.playingTime = 0;
        this.showDate = "";
        this.candidacy = false;
    }

    // μεθόδους μετάλλαξης (setters) για όλα τα πεδία. Ερώτημα 4
    public void setTitle(String title) {
		this.title = title;
	}
    
    public void setDirector(String director) {
		this.director = director;
	}

	public void setPlayingTime(int playingTime) {
		this.playingTime = playingTime;
	}

	public void setCandidacy(boolean candidacy) {
		this.candidacy = candidacy;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	
	// μεθόδους πρόσβασης (getters) για όλα τα πεδία. Ερώτημα 5
    public String getTitle() {
		return title;
	}
	
	public String getDirector() {
		return director;
	}

	public int getPlayingTime() {
		return playingTime;
	}


	public boolean getCandidacy() {
		return candidacy;
	}

	public String getShowDate() {
		return showDate;
	}

	// μέθοδο print ερώτημα 6
    public void print() {
        System.out.println(title + ", Director " + director);
        System.out.println("\t" + playingTime + " minutes");
        System.out.println("\t" + showDate);
        if (candidacy) {
            System.out.println("\tCandidate for Oscar!");
        }
    }

}
