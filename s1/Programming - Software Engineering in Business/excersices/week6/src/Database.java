import java.util.ArrayList;

public class Database
{
	// Τις σβήνω
//    private ArrayList<CD> cds;
//    private ArrayList<DVD> dvds;

	private ArrayList<Item> items = new ArrayList<>();
	
	// Δεν χρειάζεται κατασκευαστής αφού τον αρχικοποίησα πάνω
//    public Database()
//    {
//    }

    public void addItem(Item theItem)
    {
        items.add(theItem);
    }

    
    // Δεν χρειάζεται. Τροποποίησα και την addCD σε addItem 
//    public void addDVD(DVD theDVD)
//    {
//        dvds.add(theDVD);
//    }

    public void list()
    {
        // Κάθε φορά που διατρέχει τον βρόγχο, δεν θα ασχοείται καθόλου με τον τύπο.
    	// Ανάλογα με τον τύπο, θα παίρνει την print είτε από την CD είτε DVD
        // print list of CDs
        for(Item anItem : items) {
            anItem.print();
            System.out.println();   // empty line between items
        }
        
        // Τροποποιώ και την cd και αυτή δεν χρειάζεται
//        // print list of DVDs
//        for(DVD dvd : dvds) {
//            dvd.print();
//            System.out.println();   // empty line between items
//        }
    }
}