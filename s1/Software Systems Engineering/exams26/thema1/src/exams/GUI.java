package exams;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JFrame {
    
    // Components
    private JPanel panel, inputPanel, dataPanel, sortPanel;
    private JTextField firstNameField, lastNameField, gpaField;
    private JButton storeButton, sortButton;
    private JList<Student> listView;
    private DefaultListModel<Student> model;
    private JScrollPane scrollPane;
    private ButtonGroup buttonGroup;
    private JRadioButton rdbtnBubble, rdbtnSelection, rdbtnInsert;
    
    // Logic
    private StudentList studentData; 
    private SortAlgorithm sortAlgorithm;

    public GUI() {
        // 1. Initialize the Data Manager
        studentData = new StudentList();
        
        // 2. Initialize the UI Model and populate it from StudentList
        model = new DefaultListModel<>();
        refreshDisplay(); // Helper method to sync UI with Data
        
        // --- Standard UI Setup (same as before) ---
        listView = new JList<>(model);
        scrollPane = new JScrollPane(listView);
        
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        gpaField = new JTextField(5);
        storeButton = new JButton("Add");
        
        inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:")); inputPanel.add(firstNameField);
        inputPanel.add(new JLabel("Last:")); inputPanel.add(lastNameField);
        inputPanel.add(new JLabel("GPA:")); inputPanel.add(gpaField);
        inputPanel.add(storeButton);
        
        dataPanel = new JPanel(new BorderLayout());
        dataPanel.add(scrollPane, BorderLayout.CENTER);
        
        // --- Sort Panel Setup ---
        sortPanel = new JPanel(new GridLayout(2, 2));
        buttonGroup = new ButtonGroup();
        
        rdbtnBubble = new JRadioButton("BubbleSort");
        rdbtnSelection = new JRadioButton("SelectionSort");
        rdbtnInsert = new JRadioButton("InsertionSort");
        sortButton = new JButton("Sort");
        
        buttonGroup.add(rdbtnBubble);
        buttonGroup.add(rdbtnSelection);
        buttonGroup.add(rdbtnInsert);
        
        // Instantiate Sorters directly 
        rdbtnBubble.addActionListener(e -> sortAlgorithm = new BubbleSort());
        rdbtnSelection.addActionListener(e -> sortAlgorithm = new SelectionSort());
        rdbtnInsert.addActionListener(e -> sortAlgorithm = new InsertionSort());
        
        // Default selection
        rdbtnBubble.setSelected(true);
        sortAlgorithm = new BubbleSort();
        
        sortPanel.add(rdbtnBubble);
        sortPanel.add(rdbtnSelection);
        sortPanel.add(rdbtnInsert);
        sortPanel.add(sortButton);
        
        // --- Main Layout ---
        panel = new JPanel(new BorderLayout());
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(dataPanel, BorderLayout.CENTER);
        panel.add(sortPanel, BorderLayout.SOUTH);
        
        this.setContentPane(panel);
        
        // Listeners
        storeButton.addActionListener(new StoreButtonListener());
        sortButton.addActionListener(new SortButtonListener());
        
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    // --- Helper Method to Sync Data to UI ---
    private void refreshDisplay() {
        model.clear();
        for (Student s : studentData.getList()) {
            model.addElement(s);
        }
    }

    // --- Inner Classes ---
    
    class StoreButtonListener implements ActionListener {
    	
        public void actionPerformed(ActionEvent e) {
            try {
                String f = firstNameField.getText();
                String l = lastNameField.getText();
                double g = Double.parseDouble(gpaField.getText());
                
                // Add to our custom list
                studentData.add(new Student(f, l, g));
                
                // Refresh UI
                refreshDisplay();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(GUI.this, "Invalid Input");
            }
        }
    }
    
    class SortButtonListener implements ActionListener {
    	
        public void actionPerformed(ActionEvent e) {
            if (sortAlgorithm == null) return;

            // 1. Get array from our StudentList
            Object[] array = studentData.getList().toArray();
            
            // 2. Sort the array
            sortAlgorithm.sort(array);
            
            // 3. Update StudentList with new order
            studentData.clear();
            for (Object obj : array) {
                studentData.add((Student)obj);
            }
            
            // 4. Update UI
            refreshDisplay();
        }
    }
}