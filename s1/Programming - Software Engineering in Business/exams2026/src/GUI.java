import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class GUI extends JFrame {
    private JPanel panel;
    private JPanel input;
    private JPanel buttonPanel;

    private JLabel companyLabel;
    private JLabel discountLabel;

    private JTextField companyField;
    private JTextField discountField;

    private JButton printProductInfoButton;
    private JButton printSalesPriceButton;

    private ArrayList<Product> products;

    public GUI(Pharmacy thesShop) {
        products = thesShop.getAllProducts();

        panel = new JPanel(new GridLayout(2, 1));
        input = new JPanel();
        buttonPanel = new JPanel();

        companyLabel = new JLabel("Company Name");
        discountLabel = new JLabel("Discount (e.g. 0.2)");

        companyField = new JTextField(20);
        discountField = new JTextField(15);

        printProductInfoButton = new JButton("Print Company Products");
        printSalesPriceButton = new JButton("Print Sales Price");

        input.add(companyLabel);
        input.add(companyField);
        input.add(discountLabel);
        input.add(discountField);
        input.setBorder(BorderFactory.createLineBorder(Color.black));

        buttonPanel.add(printProductInfoButton);
        buttonPanel.add(printSalesPriceButton);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        panel.add(input);
        panel.add(buttonPanel);

        this.setContentPane(panel);

        ProductButtonListener productListener = new ProductButtonListener();
        printProductInfoButton.addActionListener(productListener);

        PriceButtonListener priceListener = new PriceButtonListener();
        printSalesPriceButton.addActionListener(priceListener);

        this.setVisible(true);
        this.setSize(320, 260);
        this.setLocation(200, 0);
        this.setTitle("Input");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class ProductButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String company = companyField.getText();
            if (company == null || company.trim().isEmpty()) {
                System.out.println("Company name is required");
                return;
            }
            boolean found = false;
            for (Product product : products) {
                if (product.getCompany().equalsIgnoreCase(company.trim())) {
                    product.printProductInfo();
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No products found for company: " + company.trim());
            }
        }
    }

    class PriceButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String company = companyField.getText();
            if (company == null || company.trim().isEmpty()) {
                System.out.println("Company name is required");
                return;
            }
            String discountText = discountField.getText();
            try {
                double discount = Double.parseDouble(discountText.trim());
                boolean found = false;
                for (Product product : products) {
                    if (product.getCompany().equalsIgnoreCase(company.trim())) {
                        double currentPrice = product.calculatePrice();
                        double salePrice = currentPrice * (1 - discount);
                        System.out.println("Product: " + product.getName()
                                + ", Current Price: " + currentPrice
                                + ", Sale Price: " + salePrice);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No products found for company: " + company.trim());
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid discount format");
            }
        }
    }
}