
public class Item {

	// Παίρνω ότι έχει η CD και κρατάω ότι είναι κοινό
    private String title;
    private int playingTime;
    private boolean gotIt;
    private String comment;

    public Item(String theTitle, int time)
    {
        title = theTitle;
        playingTime = time;
        gotIt = false;
        comment = "<no comment>";
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    
    public String getComment()
    {
        return comment;
    }

    public void setOwn(boolean ownIt)
    {
        gotIt = ownIt;
    }

    public boolean getOwn()
    {
        return gotIt;
    }

    // Πολυμορφική μέθοδος (ποια μορφή θα εκτελεστεί, εξαρτάται από το αντικείμενο)
    public void print()
    {
        System.out.print(title + " (" + playingTime + " mins)"); // Έσβησα την ετικέτα Item
        if(gotIt) {
            System.out.println("*");
        }
        
        else {
            System.out.println();
        }

        System.out.println("    " + comment);
    }
    
}