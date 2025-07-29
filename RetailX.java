import java.time.LocalDate;
import java.util.*;

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

class Customer {
    int id;
    String name;
    String contact;
    double balance;

    Customer(int id, String name, String contact, double balance) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.balance = balance;
    }

    void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Contact: " + contact + " | Balance: " + balance);
    }
}

class SaleItem {
    Product product;
    int quantity;
    double price;

    SaleItem(Product product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}

class Sale {
    int saleId;
    Customer customer;
    List<SaleItem> items;
    double totalAmount;
    LocalDate date;

    Sale(int saleId, Customer customer, List<SaleItem> items, double totalAmount, LocalDate date) {
        this.saleId = saleId;
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
        this.date = date;
    }
}

public class RetailX {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Product> productList = new ArrayList<>();
    static int productIdCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== RetailX Product Management ===");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> viewProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> {
                    System.out.println("Exiting RetailX... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addProduct() {
        System.out.print("Enter product name: ");
        sc.nextLine(); // consume newline
        String name = sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        System.out.print("Enter cost price: ");
        double cost = sc.nextDouble();

        System.out.print("Enter selling price: ");
        double price = sc.nextDouble();

        Product p = new Product(productIdCounter++, name, category, quantity, cost, price);
        productList.add(p);
        System.out.println("✅ Product added successfully!");
    }

    static void viewProducts() {
        if (productList.isEmpty()) {
            System.out.println("❌ No products found.");
            return;
        }
        System.out.println("\n--- Product List ---");
        for (Product p : productList) {
            p.display();
        }
    }

    static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int id = sc.nextInt();
        for (Product p : productList) {
            if (p.id == id) {
                sc.nextLine(); // clear buffer
                System.out.print("Enter new name: ");
                p.name = sc.nextLine();

                System.out.print("Enter new category: ");
                p.category = sc.nextLine();

                System.out.print("Enter new quantity: ");
                p.quantity = sc.nextInt();

                System.out.print("Enter new cost price: ");
                p.costPrice = sc.nextDouble();

                System.out.print("Enter new selling price: ");
                p.sellingPrice = sc.nextDouble();

                System.out.println("✅ Product updated!");
                return;
            }
        }
        System.out.println("❌ Product not found.");
    }

    static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int id = sc.nextInt();
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.id == id) {
                iterator.remove();
                System.out.println("🗑️ Product deleted successfully!");
                return;
            }
        }
        System.out.println("❌ Product not found.");
    }
}
