
public class DVD extends Item
{

    private String director;


    public DVD(String theTitle, String theDirector, int time)
    {
        super(theTitle, time);
        director = theDirector;

    }
    
    // Overriding - Επικάλυψη
    public void print() 
    {
    	System.out.print("DVD: "); // Πρόσθεσα την ετικέτα DVD
    	super.print(); // Καλεί την print από την superclass
		System.out.println("    " + director);
    }

/*
    public void print() 
	{
		System.out.print("DVD: " + title + " (" + playingTime + " mins)");
		if (gotIt) 
		{
			System.out.println("*");
		} 
		else 
		{
			System.out.println();
		}
		
		System.out.println("    " + director);
		System.out.println("    " + comment);
	}
	*/
}