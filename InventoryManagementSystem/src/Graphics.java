import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Graphics extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[4];
    private GUIExcecution g = new GUIExcecution();
    private String productName, productCategory;
    private int productQuantity;
    private ProductNode product;

    public Graphics(String title) {
        JPanel panelContainer = new JPanel();
        JLabel labelHeading = new JLabel();
        Border border = BorderFactory.createLineBorder(new Color(255, 0, 0), 2);
        Font font = new Font("Consolas", Font.BOLD, 25);
        ImageIcon icon = new ImageIcon("icon.jpg");

        // Pop Up Dialog
        JOptionPane.showMessageDialog(null, "Please add products to the inventory", "Inventory Status", 2);

        // Header Label
        labelHeading = new JLabel("Welcome to Inventory Management System".toUpperCase());
        labelHeading.setFont(font);
        labelHeading.setBorder(border);
        labelHeading.setBounds(0, 0, 700, 150);
        labelHeading.setForeground(new Color(200, 100, 100));
        labelHeading.setBackground(new Color(0, 0, 0));
        labelHeading.setOpaque(true);
        labelHeading.setHorizontalAlignment(JLabel.CENTER);
        labelHeading.setVerticalAlignment(JLabel.CENTER);

        // Button1
        buttons[0] = new JButton("Add a Product");
        buttons[0].setFont(new Font("Consolas", Font.BOLD, 20));
        buttons[0].setBounds(0, 0, 100, 50);
        buttons[0].setFocusable(false);
        buttons[0].addActionListener(this);

        // Button2
        buttons[1] = new JButton("Change to Category");
        buttons[1].setFont(new Font("Consolas", Font.BOLD, 20));
        buttons[1].setBounds(100, 0, 100, 50);
        buttons[1].setFocusable(false);
        buttons[1].setEnabled(false);
        buttons[1].addActionListener(this);

        // Button3
        buttons[2] = new JButton("Remove a Product");
        buttons[2].setFont(new Font("Consolas", Font.BOLD, 20));
        buttons[2].setBounds(0, 150, 100, 50);
        buttons[2].setFocusable(false);
        buttons[2].setEnabled(false);
        buttons[2].addActionListener(this);

        // Button4
        buttons[3] = new JButton("Display Inventory");
        buttons[3].setFont(new Font("Consolas", Font.BOLD, 20));
        buttons[3].setBounds(100, 150, 100, 50);
        buttons[3].setFocusable(false);
        buttons[3].setEnabled(false);
        buttons[3].addActionListener(this);

        panelContainer.setBounds(45, 175, 600, 250);
        panelContainer.setLayout(new GridLayout(2, 2, 10, 10));
        panelContainer.add(buttons[0]);
        panelContainer.add(buttons[1]);
        panelContainer.add(buttons[2]);
        panelContainer.add(buttons[3]);

        this.setTitle(title);
        this.setIconImage(icon.getImage());
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.add(labelHeading);
        this.add(panelContainer);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean isClicked = false;

        // int counter = 0;

        if (e.getSource() == buttons[0]) {
            isClicked = true;

            while (true) {
                productName = JOptionPane.showInputDialog(null, "Product Name");

                productCategory = JOptionPane.showInputDialog(null, "Product Category");

                try {
                    productQuantity = Integer.parseInt(JOptionPane.showInputDialog(null, "Product Quantity"));
                } catch (Exception ex) {
                    // TODO: handle exception
                    productQuantity = 1;
                }

                product = new ProductNode(g.generateProductCode(), productName, productCategory,
                        productQuantity);

                g.addProduct(product);

                int addContinue = JOptionPane.showConfirmDialog(null, "Do you want to add more products?");

                if (addContinue != 0) {
                    break;
                }
            }

        }
        if (isClicked) {
            buttons[1].setEnabled(true);
            buttons[2].setEnabled(true);
            buttons[3].setEnabled(true);
        }
        if (e.getSource() == buttons[1]) {
            // organizing into category
            boolean productFound = false;
            try {
                String pn = JOptionPane.showInputDialog(null, "Name of the product to be changed");
                String newCategory = JOptionPane.showInputDialog(null, "What is the new category?");
                for (String productCode : g.inventoryMap.keySet()) {
                    product = g.inventoryMap.get(productCode);
                    if (pn.equals(product.getProductName())) {
                        productFound = true;
                        g.organizeIntoCategory(productCode, newCategory);
                        JOptionPane.showMessageDialog(null, "Product Category Successfully Changed", "Inventory Status",
                                2);
                    }
                }
            } catch (Exception ex) {
                throw new IllegalStateException("Null Error!");
            }
            if (!productFound) {
                JOptionPane.showMessageDialog(null, "Sorry! Product Not Found!", "Inventory Status", 0);
            }
        }
        if (e.getSource() == buttons[2]) {
            // removing the product
            boolean productFound = false;
            try {
                String pn = JOptionPane.showInputDialog(null, "Name of the product to be removed");
                for (String productCode : g.inventoryMap.keySet()) {
                    product = g.inventoryMap.get(productCode);
                    if (pn.equals(product.getProductName())) {
                        productFound = true;
                        g.removeProduct(product);
                        JOptionPane.showMessageDialog(null, "Product Successfully Removed", "Inventory Status", 2);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Null Error!\n" + ex);
            }
            if (!productFound) {
                JOptionPane.showMessageDialog(null, "Sorry! Product Not Found!", "Inventory Status", 0);
            }
        }
        if (e.getSource() == buttons[3]) {
            // displaying Inventory
            if (g.productCounter == 0) {
                JOptionPane.showMessageDialog(null, "There is no product in the inventory", "Inventory Status", 0);
            } else {
                g.displayInventory();
            }
        }
    }
}

class GUIExcecution extends InventoryManagementSystem {
    public String generateProductCode() {
        StringBuilder sb = new StringBuilder();
        Random rng = new Random();
        int length = 10;

        for (int i = 0; i < length / 2; i++) {
            sb.append((char) (rng.nextInt(26) + 65));
            sb.append(rng.nextInt(10));
        }

        return sb.toString();
    }
}