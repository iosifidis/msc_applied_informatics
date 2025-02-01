import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InputForm extends JFrame {

	private ArrayList<School> schools;
	private JTextField schoolField;
	private JPanel panel;

	private JButton ektypwsi;
	
	public InputForm(ArrayList<School> schools) {
		
		this.schools = schools;
		
		panel = new JPanel();
		
		schoolField = new JTextField("Eisagete to onoma ths sxolikhs monadas pou sas endiaferei");
		
		ektypwsi = new JButton("Ektypwsi");
		
		panel.add(schoolField);
		panel.add(ektypwsi);
		
		this.setContentPane(panel);
	
		
		this.setSize(500, 250);
		this.setVisible(true);
		this.setTitle("Input Form");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ektypwsi.addActionListener(e -> {
            String schoolName = schoolField.getText();
            SwingUtilities.invokeLater(() -> printTeacherInfo(schoolName));
        });
	}

	private void printTeacherInfo(String schoolName) {
        for (School school : schools) {
            if (school.getName().equals(schoolName)) {
                System.out.println("=====| INFORMATION OF SCHOOL: " + school.getName() + " |=====");
                System.out.println("**********************************************************");
                System.out.println("=====|   NAME  :  SALARY  |=====");
                System.out.println("----------------------------------");
                for (Teacher teacher : school.getTeachers()) {
                    System.out.println("=====| " + teacher.getSurname() +" : "+ teacher.calculateSalary() + " |=====");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "School not found: " + schoolName, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
