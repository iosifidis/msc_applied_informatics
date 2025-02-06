

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class InputFrame extends JFrame {
    private JPanel panel;
    private JPanel input;
    private JPanel buttonPanel;

    private JLabel descriptionLabel;
    private JLabel priceLabel;

    private JTextField descriptionField;
    private JTextField priceField;

    private JButton printProductPriceButton;
    private JButton printProductsButton;

    private ArrayList<Product> products;

    public InputFrame(eShop thesShop) {
        products = thesShop.getAllProducts();

        panel = new JPanel(new GridLayout(2, 1));
        input = new JPanel();
        buttonPanel = new JPanel();

        descriptionLabel = new JLabel("Description");
        priceLabel = new JLabel("Price");

        descriptionField = new JTextField(20);
        priceField = new JTextField(15);

        printProductPriceButton = new JButton("Print Product Price");
        printProductsButton = new JButton("Print Products");

        input.add(descriptionLabel);
        input.add(descriptionField);
        input.add(priceLabel);
        input.add(priceField);
        input.setBorder(BorderFactory.createLineBorder(Color.black));

        buttonPanel.add(printProductPriceButton);
        buttonPanel.add(printProductsButton);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        panel.add(input);
        panel.add(buttonPanel);

        this.setContentPane(panel);

        ProductButtonListener productListener = new ProductButtonListener();
        printProductPriceButton.addActionListener(productListener);

        PriceButtonListener priceListener = new PriceButtonListener();
        printProductsButton.addActionListener(priceListener);

        this.setVisible(true);
        this.setSize(320, 260);
        this.setLocation(200, 0);
        this.setTitle("Input");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class ProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String description = descriptionField.getText();
            for (Product product : products) {
                if (product.getDescription().equalsIgnoreCase(description)) {
                    System.out.println("The price for " + description + " is " + product.calculatePrice());
                }
            }
        }
    }

    class PriceButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String priceText = priceField.getText();
            try {
                double price = Double.parseDouble(priceText);
                for (Product product : products) {
                    if (product.calculatePrice() > price) {
                        System.out.println("Product " + product.getDescription() + " Price " + product.calculatePrice());
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price format");
            }
        }
    }
}

