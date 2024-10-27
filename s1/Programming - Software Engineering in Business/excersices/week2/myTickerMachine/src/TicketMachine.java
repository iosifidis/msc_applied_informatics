

public class TicketMachine {
	
	// fields - πεδία or instance variables - μεταβλητές στιγμιοτύπου
    // Έχουν εμβέλεια μέσα στην κλάση. 
    // Η διάρκεια ζωής τους είναι όσο ζει το αντικείμενο.
	// private δηλώνει ότι κάθε ένα από τα παραπάνω πεδία θα είναι προσπελάσιμο μόνο από την κλάση στην οποία δηλώνεται
	private int price; // η τιμή των εισιτηρίων που εκδίδει η μηχανή
	private int balance; /* ΑΘΡΟΙΣΤΗΣ: το σύνολο των χρημάτων που έχει εισάγει ο χρήστης μέχρι στιγμής */
	private int total; /* το σύνολο των χρημάτων που έχουν συγκεντρωθεί στη μηχανή */
	
    // αρχή ενθυλάκωσης: δεν θέλουμε να αφήνουμε εκτεθειμένα τα αντικείμενα.
    // Να είναι προστατευμένα και να διαχειρίζονται ΜΟΝΟ μέσα στην κλάση.
	
	// Constructor - Κατασκευαστής. Είναι void γιατί δεν επιστρέφει κάτι.
	// Ο κατασκευαστής έχει το ίδιο όνομα με την κλάση
	// αρχικοποίηση: εκχώρηση των κατάλληλων τιμών στα πεδία ενός αντικειμένου
    // ticketCost καλείται τυπική παράμετρος. Η τιμή δεν είναι εκ των προτέρων γνωστή.
    public TicketMachine(int ticketCost){
        price = ticketCost;
        balance = 0;
        total = 0;
    }
    
    /*Η συγκεκριμένη εντολή έχει ως αποτέλεσμα την πρόσθεση της
	τιμής της παραμέτρου coin στην υπάρχουσα τιμή της
	μεταβλητής balance.*/
    public void insertMoney(int coin){
    	
        // Έλεγχος αξίας κέρματος (αποδοχή 5, 10, 20, 50 cents
        if ( coin == 5 || coin == 10 || coin == 20 || coin == 50){
            balance = balance + coin;
            //balance += coin; // Συνήθως έτσι γράφεται
        }
        else{
            System.out.println("Το " + coin + " δεν είναι έγκυρο. Το έφαγε το μηχάνημα");
        }
    }
    
    // Accessor method (μέθοδος πρόσβασης) or getter. 
    // Η μέθοδος θα έχει τύπο ανάλογα με τι τύπο μεταβλητής επιστρέφω.
    public int getPrice(){
        return price;
    }
    
    //Mutator method or setter - Μέθοδος μετάλαξης
    public void setPrice(int cost){
        // Κάνω έλεγχο εγκυρότητας της παραμέτρου
        if ( cost > 0 ){
            price = cost;
        }
        else{
            // Αντικείμενο System.out (είναι αντικείμενο, όμως δεν χρειάζεται να δημιουργήσουμε νέα κλάση και μετά αντικείμενο.)
            // Καλούμε τη μέθοδο .println επί του αντικειμένου System.out
            System.out.println("Not a valid ticket price");
        }
    }
    
    // Μέθοδος εκτύπωσης
    public void printTicket(){
        
    	// Έλεγχος εάν έχει εισάγει το ακριβές αντίτιμο
        if (balance >= price){
            System.out.println("Τσίμπα το τικέτο σου των " + price + " cents."); // εκτύπωση
            total += balance; // Μεταβλητή που προσθέτουμε όλα τα χρήματα της μηχανής.
            balance = 0; // Μηδενίζω το ποσό που εισήχθη στην μηχανή
        }
        else{
            //int remain = price - balance;
            System.out.println("Μάστορα χρωστάς " + (price - balance) + " cents. Δώσε και άλλο μαρούλι.");
        }
    }
    
    public int refundBalance(){

    	int amountToRefund;
    	amountToRefund = balance;
    	balance = 0;
    	return amountToRefund;
    }
    
}
