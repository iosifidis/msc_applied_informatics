import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JFrame{
	
	//2. GUI: Δημιουργία του panel
	private JPanel panel = new JPanel();
	
	// 2. GUI: Δημιουργία γραφικών συστατικών
	private JButton button = new JButton("CalculateTotalHDDSize");
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
			String resultAsText = Double.toString(company.getTotalStorage());
			
			// προβολή στο πλαίσιο κειμένου 
			textField.setText(resultAsText);
		}
		
	}

}