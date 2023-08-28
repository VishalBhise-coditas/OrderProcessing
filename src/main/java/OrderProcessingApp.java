import domain.Order;
import domain.Product;
import service.OrderProcessor;
import service.impl.OrderProcessorImpl;

import java.util.Scanner;

public class OrderProcessingApp {

    private Scanner scanner = new Scanner(System.in);
    OrderProcessor orderProcessor = new OrderProcessorImpl();
    private int orderCounter = 1;

    private void run() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            executeChoice(choice);
        }
    }

    private void displayMenu() {
        System.out.println("Select an operation:");
        System.out.println("1. Add a new product");
        System.out.println("2. Remove a product");
        System.out.println("3. Place an order");
        System.out.println("4. Cancel an order");
        System.out.println("5. List all products");
        System.out.println("6. List all orders");
        System.out.println("7. Exit");
    }

    private int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private void executeChoice(int choice) {
        switch (choice) {
            case 1:
                addNewProduct();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                placeOrder();
                break;
            case 4:
                cancelOrder();
                break;
            case 5:
                listAllProducts();
                break;
            case 6:
                listAllOrders();
                break;
            case 7:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please select again.");
        }
    }

    private void listAllOrders() {
        System.out.println("All orders:");
        for (Order order : orderProcessor.listAllOrders().values()) {
            System.out.println("Order ID: " + order.getId());
            for (Product product : order.getProducts()) {
                System.out.println(" - " + product.getName() + " - $" + product.getPrice());
            }
            System.out.println("Total Price: $" + order.getTotalPrice());
        }
    }

    private void cancelOrder() {
        System.out.print("Enter order ID to cancel: ");
        String orderId = scanner.nextLine();
        orderProcessor.cancelOrder(orderId);
    }

    private void placeOrder() {
        System.out.println("Available products:");
        listAllProducts();
        Order order = createOrder();
        boolean isPlaced = orderProcessor.placeOrder(order);
        if(isPlaced){
            System.out.println("Order placed successfully.");
        }else {
            System.out.println("No products in the order.");
        }
    }

    private void listAllProducts() {
        System.out.println("All products:");
        for (Product product : orderProcessor.listAllProducts().values()) {
            System.out.println(product.getId() + " - " + product.getName() + " - $" + product.getPrice());
        }
    }

    private Order createOrder() {
        Order order = new Order("Order" + orderCounter++);
        String productId;

        do {
            System.out.print("Enter product ID to add to the order (enter 'done' to finish): ");
            productId = scanner.nextLine();

            if (!productId.equalsIgnoreCase("done")) {
                Product product = orderProcessor.getProduct(productId);
                if (product != null) {
                    order.addProduct(product);
                    System.out.println("Product added to the order.");
                } else {
                    System.out.println("Product not found.");
                }
            }
        } while (!productId.equalsIgnoreCase("done"));

        return order;
    }

    private void removeProduct() {
        System.out.print("Enter product ID to remove: ");
        String id = scanner.nextLine();
        boolean isRemoved = orderProcessor.removeProduct(id);
        if(isRemoved){
            System.out.println("Product removed successfully.");
        }else {
            System.out.println("Product not found.");
        }
    }

    private void addNewProduct() {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        orderProcessor.addNewProduct(id, name, price);
        System.out.println("Product added successfully.");
    }

    public static void main(String[] args) {
        OrderProcessingApp processor = new OrderProcessingApp();
        processor.run();
    }

}
