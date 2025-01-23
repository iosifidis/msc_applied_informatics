import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// ΕΡΩΤΗΜΑ Β
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