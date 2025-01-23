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
public abstract class Container {
    private String code;
    private String destination;

    public Container(String code, String destination) {
        this.code = code;
        this.destination = destination;
    }

    public abstract double calculateCharge();
}
```

**Υλοποίηση Συγκεκριμένων Κλάσεων**
Δημιουργήστε κλάσεις που κληρονομούν την **abstract** (προσθέτω το **extends Container**). Υλιποιήστε και την αφηρημένη (abstract) μέθοδο με **@Override**:

@Override   
    public double calculateCharge() {

Θα είναι:

```
public class Bulk extends Container {
    private double weight;

    public Bulk(String code, String destination, double weight) {
        super(code, destination);
        this.weight = weight;
    }

    @Override
    public double calculateCharge() {
        return 10 * weight;
    }
}
```

```
public class Refrigerator extends Container {
    private double power;

    public Refrigerator(String code, String destination, double power) {
        super(code, destination);
        this.power = power;
    }

    @Override
    public double calculateCharge() {
        return 2000 * power;
    }
}
```

## Βήμα 3: Δημιουργία Κλάσης Ship
Η κλάση Ship διαχειρίζεται τα containers:

```
public class Ship {
    
    // Το αμπάρι του πλοίου. Δείχνει σε ένα αντικείμενο container (αρχή υποκατάστασης, λαμβάνει και τους δυο τύπους)
    private ArrayList<Container> containers = new ArrayList<>();
    
    private int capacity;

    public Ship(int capacity) {
        this.capacity = capacity;
    }

    // ΠΡΟΣΘΗΚΗ της abstract κλάσης
    public void addContainer(Container container) {
        if (containers.size() < capacity) {
            containers.add(container);
            System.out.println("The container has been loaded");
        } else {
            System.out.println("Sorry, the ship is full");
        }
    }

    // ΥΠΟΛΟΓΙΣΜΟΣ συνολικής χρέωσης. Διατρέχω την λίστα
    public double calculateTotalCharge() {
        double total = 0;
        for (Container container : containers) {
            total += container.calculateCharge();
        }
        return total;
    }
}
```

## Βήμα 4: Ενέργειες στη Main
Δημιουργήστε πλοίο και containers, και προσθέστε τα στο πλοίο. Στείλτε το πλοίο στην γραφική διεπαφή:

```
public static void main(String[] args) {
    Ship ship1 = new Ship(450);
    Bulk bulk1 = new Bulk("CYZ1011", "Barcelona", 50);
    Refrigerator fridge1 = new Refrigerator("FRG2023", "Athens", 2);

    ship1.addContainer(bulk1);
    ship1.addContainer(fridge1);

    // Εκκίνηση παραθύρου και στέλνω το αντικείμενο πλοίο στη γραφική διασύνδεση μέσω του κατασκευαστή
    ChargeCalculator chargeFrame = new ChargeCalculator(ship1);
}
```

## Βήμα 5: Δημιουργία Γραφικής Διασύνδεσης
**Δημιουργία Κλάσης GUI**
Η γραφική διασύνδεση υλοποιείται ως εξής:

1. GUI: Δημιουργία παραθύρου (**ΜΕΣΑ ΣΤΟΝ ΚΑΤΑΣΚΕΥΑΣΤΗ**).
```
		this.setVisible(true);
		this.setSize(200,400);
		this.setTitle("Create and Load Containers");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
```

2.  GUI: Δημιουργία του panel και γραφικών συστατικών. ΠΡΟΣΟΧΗ, γίνεται σύνδεση με τη δομή που στέλνει η main (**ΕΞΩ ΑΠΟ ΤΟΝ ΚΑΤΑΣΚΕΥΑΣΤΗ**).
```
    //2. GUI: Δημιουργία του panel
	private JPanel panel = new JPanel();
	
	// 2. GUI: Δημιουργία γραφικών συστατικών (ΕΡΩΤΗΜΑ Β)
	private JButton calcButton = new JButton("Calculate Charge");
```

3. Εισαγωγή γραφικών συστατικών στο panel (**ΜΕΣΑ ΣΤΟΝ ΚΑΤΑΣΚΕΥΑΣΤΗ**).
```
    //3. GUI:  Εισαγωγή γραφικών συστατικών στο panel
    panel.add(calcButton);
```

4. Προσαρμογή του panel πάνω στο παράθυρο (**ΜΕΣΑ ΣΤΟΝ ΚΑΤΑΣΚΕΥΑΣΤΗ**).
```
        //4. GUI:  Προσαρμογή του panel πάνω στο παράθυρο
		this.setContentPane(panel);
```

5. Σύνδεση της δομής που στέλνει η main (εδώ έχουμε αντικείμενο πλοίο) στην γραφική διεπαφή.

ΕΚΤΟΣ ΚΑΤΑΣΚΕΥΑΣΤΗ δημιουργώ αντικείμενο πλοίο:
```
// Δημιουργία ενός πλοίου. Αυτό θα υποδεχτεί το πλοίο που στέλνει η Main
	private Ship ship;
```
Εντός κατασκευαστή, συνδέω το πλοίο που έστειλε η main με το αντικείμενο πλοίο που έχω προσθέσει. Με αυτόν τον τρόπο μπορώ να έχω πρόσβαση στο "περιεχόμενο" του πλοίου.
```
// ΣΥΝΔΕΣΗ του πλοίου που στέλνει η MAIN και λαμβάνει το GUI
	ship = aShip;
```

**Προσθήκη λειτουργίας στο κουμπί**

1. Δημιουργία κλάσης Ακροατή. Προσοχή να περιέχει το **implements ActionListener**. Η κλάση ακροατή είναι **εκτός** του κατασκευαστή.
```
	// Δημιουργία κλάσης Ακροατή (βήμα 1)
	class ButtonListener implements ActionListener {
		
		//Μέθοδος που θα εκτελείται όταν πατάμε το πλήκτρο (βήμα 2)
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//εκτύπωση της συνολικής χρέωσης για το πλοίο
			//που έχουμε κατασκευάσει στη main
			System.out.println("Total Charge: " + ship.calculateTotalCharge());
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
		calcButton.addActionListener(listener);
```

### ΟΛΟΚΛΗΡΗ Η ΚΛΑΣΗ ΓΡΑΦΙΚΗΣ ΔΙΕΠΑΦΗΣ

```
public class ChargeCalculator extends JFrame {
	
	//2. GUI: Δημιουργία του panel
	private JPanel panel = new JPanel();
	
	// 2. GUI: Δημιουργία γραφικών συστατικών (ΕΡΩΤΗΜΑ Β)
	private JButton calcButton = new JButton("Calculate Charge");
	
	// Δημιουργία ενός πλοίου. Αυτό θα υποδεχτεί το πλοίο που στέλνει η Main
	private Ship ship;

	// ΚΑΤΑΣΚΕΥΑΣΤΗΣ
	public ChargeCalculator(Ship aShip) {
		
		// ΣΥΝΔΕΣΗ του πλοίου που στέλνει η MAIN και λαμβάνει το GUI
		ship = aShip;
		
		
		//3. GUI:  Εισαγωγή γραφικών συστατικών στο panel
		panel.add(calcButton);
			
		//4. GUI:  Προσαρμογή του panel πάνω στο παράθυρο
		this.setContentPane(panel);
		
		//1. GUI: Δημιουργία παραθύρου
		this.setVisible(true);
		this.setSize(200,400);
		this.setTitle("Create and Load Containers");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//---------------------------------------------------
		// ΠΡΟΣΘΗΚΗ ΛΕΙΤΟΥΡΓΙΚΟΤΗΤΑΣ ΣΤΟ ΚΟΥΜΠΙ
		//---------------------------------------------------
		
		// Δημιουργώ ακροατή (βήμα 3ο)
		ButtonListener listener = new ButtonListener();
		
		//Σύνδεση με τον ακροατή συμβάντων (βήμα 4)
		calcButton.addActionListener(listener);
		
	}
	
	// Δημιουργία κλάσης Ακροατή (βήμα 1)
	class ButtonListener implements ActionListener {
		
		//Μέθοδος που θα εκτελείται όταν πατάμε το πλήκτρο (βήμα 2)
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//εκτύπωση της συνολικής χρέωσης για το πλοίο
			//που έχουμε κατασκευάσει στη main
			System.out.println("Total Charge: " + ship.calculateTotalCharge());
		}

		
	}

}
```

## Παρατηρήσεις
Οι εκτυπώσεις γίνονται στην κονσόλα.
Επεκτείνετε το GUI αν απαιτούνται περισσότερες λειτουργίες (π.χ. προσθήκη πλοίων/containers μέσω της διασύνδεσης).

