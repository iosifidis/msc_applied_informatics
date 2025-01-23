# Επίλυση του Θέματος Containers and Ships

## Βήμα 1: Δημιουργία της Main
Στη μέθοδο `main`:

```java
public static void main(String[] args) {
    // TODO Auto-generated method stub
}
```

## Βήμα 2: Μοντελοποίηση
**Δημιουργία Abstract Κλάσης**
Δημιουργήστε μια **abstract** κλάση με ιδιότητες και **abstract** μέθοδο:

```
    private int weight;
	private int length;
	private int seats;
	
	// Κατασκευαστής
	public Vehicle(int weight, int length, int seats) {
		this.weight = weight;
		this.length = length;
		this.seats = seats;
	}
	
	// Υπολογισμός μιλίων (πολυμοορφισμός)
	//Την φτιάχνω αφηρημένη (abstract) και μετατρέπω και την κλάση αφηρημένη
	public abstract double calculateMileage();

```

**Υλοποίηση Συγκεκριμένων Κλάσεων**
Δημιουργήστε κλάσεις που κληρονομούν την **abstract** (προσθέτω το **extends Container**). Υλιποιήστε και την αφηρημένη (abstract) μέθοδο με **@Override**:

@Override   
public double calculateCharge() {

Θα είναι:

```
public class ElectricVehicle extends Vehicle {

	private int batteries;
	
	// Κατασκευαστής
	public ElectricVehicle(int weight, int length, int seats, int batteries) {
		super(weight, length, seats);
		this.batteries = batteries;
	}
	
	// Υπολογισμός μιλίων (πολυμοορφισμός)
	@Override
	public double calculateMileage() {
		return 75 * batteries;
	}
}
```

```
public class GasVehicle extends Vehicle {

	private int cc;
	private int tankCapacity;
	private double fuelConsumption;
	
	// Κατασκευαστής
	public GasVehicle(int weight, int length, int seats, int cc, int tankCapacity, double fuelConsumption) {
		super(weight, length, seats);
		this.cc = cc;
		this.tankCapacity = tankCapacity;
		this.fuelConsumption = fuelConsumption;
	}
	
	// Υπολογισμός μιλίων (πολυμοορφισμός)
	@Override
	public double calculateMileage() {
		return 100 * (tankCapacity / fuelConsumption);
	}
}
```

## Βήμα 3: Δημιουργία Κλάσης Company
Η κλάση Company διαχειρίζεται τα Vehicles:

```
public class Company {
	
	private String name;
	
	// Στόλος αυτοκινήτων της εταιρίας
	private ArrayList<Vehicle> fleet = new ArrayList<>();

	// Κατασκευαστής
	public Company(String name) {
		this.name = name;
	}
	
	// Μέθοδος ΠΡΟΣΘΗΚΗΣ αυτοκινήτου στον στόλο
	public void addVehicle(Vehicle aVehicle) {
		fleet.add(aVehicle);
	}
	
	// Μέθοδος ΥΠΟΛΟΓΙΣΜΟΥ μιλίων
	public double calculateTotalMileage() {
		
		double total = 0;
		for(Vehicle vehicle: fleet) 
			total += vehicle.calculateMileage();

		return total;
		
	}

}
```

## Βήμα 4: Ενέργειες στη Main
Δημιουργήστε μια εταιρία και πολλά αυτοκίνητα, και προσθέστε τα στον στόλο της εταιρίας. Στείλτε την εταιρία στην γραφική διεπαφή:

```
public static void main(String[] args) {

        //Δημιουργήστε εδώ τα αντικείμενα που ζητούνται στο Β ερώτημα του 3ου θέματος
    	GasVehicle veh1 = new GasVehicle(1200, 420, 5, 1500, 55, 5.9);
    	GasVehicle veh2 = new GasVehicle(900, 410, 2, 2000, 45, 8.5);
    	ElectricVehicle veh3 = new ElectricVehicle(1600, 380, 4, 6);
    	ElectricVehicle veh4 = new ElectricVehicle(1450, 390, 5, 8);
    	
    	// Δημιουργία εταιρίας
    	Company companyX = new Company("Company X");
    	
    	// Προσθήκη στόλου στην εταιρία
    	companyX.addVehicle(veh1);
    	companyX.addVehicle(veh2);
    	companyX.addVehicle(veh3);
    	companyX.addVehicle(veh4);
    	
    	// Εκκίνηση της γραφικής διεπαφής
        new GUI(companyX);
}
```

## Βήμα 5: Δημιουργία Γραφικής Διασύνδεσης
**Δημιουργία Κλάσης GUI**
Η γραφική διασύνδεση υλοποιείται ως εξής:

1. GUI: Δημιουργία παραθύρου (**ΜΕΣΑ ΣΤΟΝ ΚΑΤΑΣΚΕΥΑΣΤΗ**).
```
	this.setVisible(true);
	this.setSize(500, 500);
	this.setTitle("GUI");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
```

2.  GUI: Δημιουργία του panel και γραφικών συστατικών (**ΕΞΩ ΑΠΟ ΤΟΝ ΚΑΤΑΣΚΕΥΑΣΤΗ**).
```
    //2. GUI: Δημιουργία του panel
	private JPanel panel = new JPanel();
	
	// 2. GUI: Δημιουργία γραφικών συστατικών
	private JButton button = new JButton("CalculateTotalMileage");
	private JTextField textField = new JTextField(20); // Πλαίσιο κειμένου για να εγγραφεί το αποτέλεσμα
```

3. Εισαγωγή γραφικών συστατικών στο panel (**ΜΕΣΑ ΣΤΟΝ ΚΑΤΑΣΚΕΥΑΣΤΗ**).
```
    //3. GUI:  Εισαγωγή γραφικών συστατικών στο panel
    panel.add(button);
	panel.add(textField);
```

4. Προσαρμογή του panel πάνω στο παράθυρο (**ΜΕΣΑ ΣΤΟΝ ΚΑΤΑΣΚΕΥΑΣΤΗ**).
```
        //4. GUI:  Προσαρμογή του panel πάνω στο παράθυρο
		this.setContentPane(panel);
```

5. Σύνδεση της δομής που στέλνει η main (εδώ έχουμε αντικείμενο εταιρία) στην γραφική διεπαφή.

ΕΚΤΟΣ ΚΑΤΑΣΚΕΥΑΣΤΗ δημιουργώ αντικείμενο εταιρία:
```
    // Δημιουργία μιας εταιρίας. Αυτό θα υποδεχτεί την εταιρία που στέλνει η Main
	private Company company;
```
Εντός κατασκευαστή, συνδέω το πλοίο που έστειλε η main με το αντικείμενο πλοίο που έχω προσθέσει. Με αυτόν τον τρόπο μπορώ να έχω πρόσβαση στο "περιεχόμενο" του πλοίου.
```
    // ΣΥΝΔΕΣΗ της εταιρίας που στέλνει η MAIN και λαμβάνει το GUI
	company = aCompany;
```

**Προσθήκη λειτουργίας στο κουμπί**

1. Δημιουργία κλάσης Ακροατή. Προσοχή να περιέχει το **implements ActionListener**. Η κλάση ακροατή είναι **εκτός** του κατασκευαστή.
```
	// Δημιουργία κλάσης Ακροατή (βήμα 1)
	class ButtonListener implements ActionListener {
		
		//Μέθοδος που θα εκτελείται όταν πατάμε το πλήκτρο (βήμα 2)
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// αποθήκευση των συνολικών μιλίων της εταιρίας που έχουμε κατασκευάσει στη main
			String resultAsText = Double.toString(company.calculateTotalMileage());
			
			// προβολή στο πλαίσιο κειμένου 
			textField.setText(resultAsText);
		}
```

2. Δημιουργώ ακροατή **εντός** του κατασκευαστή.
```
        // Δημιουργώ ακροατή (βήμα 3ο)
		ButtonListener listener = new ButtonListener();
```

3. Η σύνδεση του κουμπιού με τον ακροατή συμβάντων (**εντός** του κατασκευαστή).
```
        //Σύνδεση με τον ακροατή συμβάντων (βήμα 4)
		button.addActionListener(listener);
```

### ΟΛΟΚΛΗΡΗ Η ΚΛΑΣΗ ΓΡΑΦΙΚΗΣ ΔΙΕΠΑΦΗΣ

```
public class GUI extends JFrame{
	
	//2. GUI: Δημιουργία του panel
	private JPanel panel = new JPanel();
	
	// 2. GUI: Δημιουργία γραφικών συστατικών
	private JButton button = new JButton("CalculateTotalMileage");
	private JTextField textField = new JTextField(20);
	
	// Δημιουργία μιας εταιρίας. Αυτό θα υποδεχτεί την εταιρία που στέλνει η Main
	private Company company;

	public GUI(Company aCompany) {
		
	   // ΣΥΝΔΕΣΗ της εταιρίας που στέλνει η MAIN και λαμβάνει το GUI
	   company = aCompany;
	
	   //3. GUI:  Εισαγωγή γραφικών συστατικών στο panel
	   panel.add(button);
	   panel.add(textField);
	   
	   //4. GUI:  Προσαρμογή του panel πάνω στο παράθυρο
	   this.setContentPane(panel);
	   
	   //1. GUI: Δημιουργία παραθύρου
	   this.setVisible(true);
	   this.setSize(500, 500);
	   this.setTitle("GUI");
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   //---------------------------------------------------
	   // ΠΡΟΣΘΗΚΗ ΛΕΙΤΟΥΡΓΙΚΟΤΗΤΑΣ ΣΤΟ ΚΟΥΜΠΙ
	   //---------------------------------------------------
	   
	   // Δημιουργώ ακροατή (βήμα 3ο)
	   ButtonListener listener = new ButtonListener();
			
	   //Σύνδεση με τον ακροατή συμβάντων (βήμα 4)
	   button.addActionListener(listener);
	}

	// Δημιουργία κλάσης Ακροατή (βήμα 1)
	class ButtonListener implements ActionListener {
	
		//Μέθοδος που θα εκτελείται όταν πατάμε το πλήκτρο (βήμα 2)
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// αποθήκευση των συνολικών μιλίων της εταιρίας που έχουμε κατασκευάσει στη main
			String resultAsText = Double.toString(company.calculateTotalMileage());
			
			// προβολή στο πλαίσιο κειμένου 
			textField.setText(resultAsText);
		}	
	}
}
```

