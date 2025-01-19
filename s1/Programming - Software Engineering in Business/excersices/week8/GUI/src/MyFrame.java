import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
	
	//Βήμα 1. Δημιουργία υποδοχέα
	private JPanel panel = new JPanel();
	
	//Βήμα 2. Δημιουργία γρ. συστατικών
	private JTextField textField = new JTextField("Enter your name");
	private JButton button = new JButton("Press Me");
		
	public MyFrame() {
		
		//Βήμα 3. Προσθήκη γρ. συστατικών στον υποδοχέα
		panel.add(textField);
		panel.add(button);
		
		
		//Βήμα 4. Προσαρμογή υποδοχέα στο παράθυρο
		this.setContentPane(panel);
		
		//Event Handling Βήμα 3. Κατασκευή ακροατή
		ButtonListener listener = new ButtonListener();
		
		//Event Handling Βήμα 4. Καταχώρηση ακροατή στην πήγη των συμβάντων
		button.addActionListener(listener);
		
		// ΒΑΣΙΚΟΣ ΚΩΔΙΚΑΣ ΓΙΑ ΝΑ ΞΕΚΙΝΗΣΕΙ ΤΟ ΠΑΡΑΘΥΡΟ
		this.setVisible(true);
		this.setSize(400, 400);
		this.setTitle("My first frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Event Handling Βήμα 1. Δημιουργία κλάσης ακροατή
	class ButtonListener implements ActionListener {

		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Event Handling Βήμα 2. Συγγραφή εκτελούμενου κώδικα
			System.out.println("Hello my friend " + textField.getText());
		}
		
	}

}
