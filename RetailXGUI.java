import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Product {
    int id;
    String name;
    String category;
    int quantity;
    double costPrice;
    double sellingPrice;

    Product(int id, String name, String category, int quantity, double costPrice, double sellingPrice) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }

    void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Category: " + category +
                " | Qty: " + quantity + " | Cost: " + costPrice + " | Price: " + sellingPrice);
    }
}

public class RetailXGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private static ArrayList<Product> productList = new ArrayList<>();
    private static int productIdCounter = 1;

    private JTextField nameField, categoryField, quantityField, costField, priceField, idField;
    private JTextArea productDisplayArea;

    public RetailXGUI() {
        setTitle("RetailX Product Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        inputPanel.add(new JLabel("Product Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Cost Price:"));
        costField = new JTextField();
        inputPanel.add(costField);

        inputPanel.add(new JLabel("Selling Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Product ID (for update/delete):"));
        idField = new JTextField();
        inputPanel.add(idField);

        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Product");
        JButton viewButton = new JButton("View Products");
        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Display Area
        productDisplayArea = new JTextArea();
        productDisplayArea.setEditable(false);
        add(new JScrollPane(productDisplayArea), BorderLayout.SOUTH);
    }

    private void addProduct() {
        String name = nameField.getText();
        String category = categoryField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double cost = Double.parseDouble(costField.getText());
        double price = Double.parseDouble(priceField.getText());

        Product p = new Product(productIdCounter++, name, category, quantity, cost, price);
        productList.add(p);
        JOptionPane.showMessageDialog(this, "Product added successfully!");
        clearFields();
    }

    private void viewProducts() {
        productDisplayArea.setText("");
        if (productList.isEmpty()) {
            productDisplayArea.setText("No products found.");
            return;
        }
        for (Product p : productList) {
            productDisplayArea.append("ID: " + p.id + " | Name: " + p.name + " | Category: " + p.category +
                    " | Qty: " + p.quantity + " | Cost: " + p.costPrice + " | Price: " + p.sellingPrice + "\n");
        }
    }

    private void updateProduct() {
        int id = Integer.parseInt(idField.getText());
        for (Product p : productList) {
            if (p.id == id) {
                p.name = nameField.getText();
                p.category = categoryField.getText();
                p.quantity = Integer.parseInt(quantityField.getText());
                p.costPrice = Double.parseDouble(costField.getText());
                p.sellingPrice = Double.parseDouble(priceField.getText());
                JOptionPane.showMessageDialog(this, "Product updated!");
                clearFields();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found.");
    }

    private void deleteProduct() {
        int id = Integer.parseInt(idField.getText());
        for (Product p : productList) {
            if (p.id == id) {
                productList.remove(p);
                JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                clearFields();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Product not found.");
    }

    private void clearFields() {
        nameField.setText("");
        categoryField.setText("");
        quantityField.setText("");
        costField.setText("");
        priceField.setText("");
        idField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RetailXGUI gui = new RetailXGUI();
            gui.setVisible(true);
        });
    }
}
