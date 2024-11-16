// Efstathios Iosifidis - mai25017

// Κλάση δανειολήπτη
public class Loaner {
	
	// Ιδιότητες
    private String name;
    private Contact homeAddress;
    private Contact workAddress;
    private int amountDue;
    private int reminder;

    // Κατασκευαστής 1: τα αντικείμενα Contact δημιουργούνται μέσα στο σώμα του κατασκευαστή
    public Loaner(String aName, String haddress, String htown, int hzipCode, String hphoneNumber,
                  String waddress, String wtown, int wzipCode, String wphoneNumber, int amount) {
        this.name = aName;
        this.homeAddress = new Contact(haddress, htown, hzipCode, hphoneNumber);
        this.workAddress = new Contact(waddress, wtown, wzipCode, wphoneNumber);
        this.amountDue = amount;
        this.reminder = 0;
    }

    // Κατασκευαστής 2: περνάμε αναφορές στα 2 αντικείμενα Contact μέσω των παραμέτρων
    public Loaner(String theName, Contact thehomeAddress, Contact theWorkAddress, int theAmountDue) {
        this.name = theName;
        this.homeAddress = thehomeAddress;
        this.workAddress = theWorkAddress;
        this.amountDue = theAmountDue;
        this.reminder = 0;
    }

    // Μέθοδο μετάλλαξης
    public void reduceAmount(int amount) {
        this.amountDue -= amount;
    }

    // Μέθοδο μετάλλαξης για το πεδίο reminder
    public void incrementReminder() {
        if (amountDue > 0) {
        	reminder++;
        }
        else {
        	reminder = 0;
        }
    }

    // Μέθοδοι
    public void receipt(int amountPaid) {
        System.out.println("\n********** Receipt ******************");
        System.out.println("O/H: " + name + " paid " + amountPaid);
        System.out.println(homeAddress.getFullAddress());
        System.out.println("The rest is " + amountDue + " Euro");
        System.out.println("*************************************\n");
    }

    public void payment(int amount) {
        reduceAmount(amount);
        receipt(amount);
    }

    public void message(Contact address) {
        System.out.println("\n********** Remind " + (reminder + 1) + " **********");
        System.out.println(name);
        System.out.println(address.getFullAddress());
        System.out.println("Please pay " + amountDue + " Euro by the end of the month");
        System.out.println("*************************************\n");
    }

    public void RecordedMessage(Contact address) {
        System.out.println("\n********** Recorded message **********");
        System.out.println("********** Remind " + (reminder + 1) + " **********");
        System.out.println("I am calling " + address.getPhoneNumber());
        System.out.println(name);
        System.out.println("Please pay " + amountDue + " Euro by the end of the month");
        System.out.println("*************************************\n");
    }

    public void Reminder() {
    	
        // Ελέγχουμε αν το ποσό είναι μηδενικό
        if (amountDue == 0) {
            reminder = 0; // Επαναφορά του reminder σε 0 αφού το δάνειο εξοφλήθηκε
            return; // Τερματίζουμε τη μέθοδο, αφού δεν υπάρχει οφειλή
        }

        // Προχωράμε στην αποστολή υπενθύμισης μόνο αν υπάρχει υπόλοιπο ποσό
        switch (reminder) {
            case 0 : {
                message(workAddress);
                incrementReminder();
                break;
            }
            case 1 : {
                message(homeAddress);
                incrementReminder();
                break;
            }
            case 2 : {
            	RecordedMessage(workAddress);
                incrementReminder();
                break;
            }
            case 3 : {
            	RecordedMessage(homeAddress);
                incrementReminder();
                break;
            }
            case 4 : {
                RecordedMessage(homeAddress);
                System.out.println("********** Last warning!!! **********");
                System.out.println("The remaining amount of your loan will be subtracted from your salary.");
                System.out.println("*************************************");
                incrementReminder();
                break;
            }
            default : {
                System.out.println("No further reminders allowed.");
                break;
            }
        }
    }
}