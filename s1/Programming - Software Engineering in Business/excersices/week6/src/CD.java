
public class CD extends Item
{
    private String artist;
    private int numberOfTracks;

	// Κατασκευαστής
    public CD(String theTitle, String theArtist, int tracks, int time)
    {
    	super(theTitle, time);
        artist = theArtist;
        numberOfTracks = tracks;

    }

    // Overriding - Επικάλυψη
    public void print() 
    {
    	System.out.print("CD: "); // Πρόσθεσα την ετικέτα CD
    	super.print(); // Καλεί την print από την superclass
        System.out.println("    " + artist);
        System.out.println("    tracks: " + numberOfTracks);
    }
    
 /*
  * Πολυμορφική μέθοδος 
    public void print()
    {
        System.out.print("CD: " + title + " (" + playingTime + " mins)");
        if(gotIt) {
            System.out.println("*");
        }
        
        else {
            System.out.println();
        }
        System.out.println("    " + artist);
        System.out.println("    tracks: " + numberOfTracks);
        System.out.println("    " + comment);
    }
    */
}