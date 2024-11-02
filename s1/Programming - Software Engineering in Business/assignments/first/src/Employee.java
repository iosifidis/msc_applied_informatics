public class Employee {
	
    // Ιδιότητες
    private String name;
    private String educationLevel;
    private int hasMaster;
    private int yearsOfService;
    private boolean maritalStatus;
    private int numberOfChildren;
    private int salary;
    private int bonus;

    // Κατασκευαστής
    public Employee(String n, String level, int master, int years, 
                    boolean isMarried, int numOfChildren, int money) {
        name = n;
        educationLevel = level;
        hasMaster = master;
        yearsOfService = years;
        maritalStatus = isMarried;
        numberOfChildren = numOfChildren;
        salary = money;
        bonus = 0; // Μηδενίζω το bonus
    }


	// Accessor methods (getters) - Μέθοδοι πρόσβασης
    public String getName() {
        return name;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public int getMasterStudies() {
        return hasMaster;
    }

    public int getYearsOfService() {
        return yearsOfService;
    }

    public boolean isMarried() {
        return maritalStatus;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public int getSalary() {
        return salary;
    }

    public int getBonus() {
        return bonus;
    }

    // Mutator methods (setters) - Μέθοδοι μετάλλαξης
    public void setEducationLevel(String level) {
        educationLevel = level;
    }

    public void setMasterStudies(int master) {
        hasMaster = master;
    }

    public void increaseYearsOfService() {
        yearsOfService++;
    }

    public void setMaritalStatus(boolean married) {
    	maritalStatus = married;
    }

    // Μέθοδος ενημέρωσης του αριθμού των παιδιών
    public void updateNumberOfChildren(int change) {
    	int children = numberOfChildren + change;
        if (children >= 0) {
            numberOfChildren += change;
        } else {
            System.out.println("Λάθος! Δεν επιτρέπεται αρνητικός αριθμός παιδιών.");
        }
    }

    // Μέθοδος ενημέρωσης του επιδόματος (με τυπικές παραμέτρους τα έτη προϋπηρεσίας και τον αριθμό των παιδιών) 
    public void updateBonus(int perYearBonus, int perChildBonus) {
        
    	int bonusForMaster = 0;
        if (hasMaster == 1) {
            bonusForMaster = 50;
        } else if (hasMaster == 2) {
            bonusForMaster = 100;
        }

        int maritalBonus = 0;
        if (maritalStatus == true) {
        	maritalBonus = 50;
        }
        
        bonus = (perYearBonus * yearsOfService) 
                         + maritalBonus 
                         + (numberOfChildren * perChildBonus) 
                         + bonusForMaster;
    }

    // Μέθοδος υπολογισμού μηνιαίου εισοδήματος (μισθός + επίδομα)
    public int calculateMonthlyIncome() {
        return salary + bonus;
    }

    // Μέθοδος εμφάνισης στοιχείων υπαλλήλου
    public void displayEmployeeDetails() {
        System.out.println("==============================");
        System.out.println("Ονοματεπώνυμο: " + name);
        System.out.println("Βαθμίδα Εκπαίδευσης: " + educationLevel);
        System.out.print("Μεταπτυχιακές σπουδές: ");
        switch (hasMaster) {
            case 0:
            	System.out.println("∆εν έχει");
            	break;
            case 1:
            	System.out.println("Μεταπτυχιακό δίπλωμα");
            	break;
            case 2:
            	System.out.println("∆ιδακτορικό δίπλωμα");
            	break;
        }
        System.out.println("Έτη υπηρεσίας: " + yearsOfService);
        
        String married = "";
        if (maritalStatus == true) {
        	married = "Έγγαμος";
        }
        else {
        	married = "Άγαμος";
        }
        System.out.println("Οικογενειακή κατάσταση: " + married);
        System.out.println("Αριθμός ανήλικων παιδιών: " + numberOfChildren);
        System.out.println("Μισθός: " + salary + " euros");
        System.out.println("Μηνιαία εισόδημα: " + calculateMonthlyIncome() + " euros");
        System.out.println("==============================");
    }
}
