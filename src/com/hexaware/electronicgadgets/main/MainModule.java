package com.hexaware.electronicgadgets.main;

import com.hexaware.electronicgadgets.entity.*;
import com.hexaware.electronicgadgets.service.*;
import com.hexaware.electronicgadgets.exception.*;
import com.hexaware.electronicgadgets.util.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class MainModule {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerService();
    private static final ProductService productService = new ProductService();
    private static final InventoryService inventoryService = new InventoryService();
    private static final OrderService orderService = new OrderService();
    private static final OrderDetailService orderDetailService = new OrderDetailService();
    private static final PaymentService paymentService = new PaymentService();

    private static final List<Product> productList = new ArrayList<>();
    private static final List<Order> orderList = new ArrayList<>();
    private static final Map<Integer, Inventory> inventoryMap = new TreeMap<>();

    public static void main(String[] args) {
        try (Connection conn = DBConnUtil.getConnection()) {
            System.out.println("Connected to TechShopDB successfully.");
            runApplication();
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    private static void runApplication() {
        while (true) {
            System.out.println("\n=== TechShop Menu ===");
            System.out.println("1. Register Customer");
            System.out.println("2. Manage Product Catalog");
            System.out.println("3. Manage Inventory");
            System.out.println("4. Place Customer Order");
            System.out.println("5. Track Order Status");
            System.out.println("6. View Sales Report");
            System.out.println("7. Update Customer Account");
            System.out.println("8. Process Payment");
            System.out.println("9. Search Product");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("❌ Invalid input. Please enter a number from 1 to 10.");
                continue;
            }

            int choice = Integer.parseInt(input);

            try {
                switch (choice) {
                    case 1 -> addCustomer();
                    case 2 -> addProduct();
                    case 3 -> addInventory();
                    case 4 -> placeOrder();
                    case 5 -> trackOrderStatus();
                    case 6 -> viewSalesReport();
                    case 7 -> updateCustomerAccount();
                    case 8 -> processPayment();
                    case 9 -> searchProductByName();
                    case 10 -> {
                        System.out.println("Exited TechShop...");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Enter customer ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Customer c = new Customer(id, first, last, email, phone, address);
        customerService.registerCustomer(c);
        System.out.println("Customer registered successfully.");
    }

    private static void addProduct() {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble(); scanner.nextLine();
        System.out.print("Enter category: ");
        String cat = scanner.nextLine();

        try {
            for (Product p : productList) {
                if (p.getProductName().equalsIgnoreCase(name)) {
                    throw new InvalidDataException("Duplicate product name not allowed.");
                }
            }

            Product p = new Product(id, name, desc, price, cat);
            productService.addProduct(p);
            productList.add(p);
            System.out.println("Product added successfully.");
        } catch (Exception e) {
            System.out.println("Failed to add product: " + e.getMessage());
        }
    }

    private static void addInventory() {
        System.out.print("Enter inventory ID: ");
        int inventoryId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter stock quantity: ");
        int quantity = scanner.nextInt();

        Inventory inv = new Inventory(inventoryId, productId, quantity, new Date());
        inventoryService.addInventory(inv);
        inventoryMap.put(productId, inv);
        System.out.println("Inventory updated successfully.");
    }

    private static void placeOrder() {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter number of items: ");
        int count = scanner.nextInt(); scanner.nextLine();

        double total = 0;

        for (int i = 0; i < count; i++) {
            System.out.print("Enter order detail ID: ");
            int detailId = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter product ID: ");
            int productId = scanner.nextInt(); scanner.nextLine();
            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt(); scanner.nextLine();

            Inventory inv = inventoryMap.get(productId);
            if (inv == null || !inv.isProductAvailable(qty)) {
                throw new InsufficientStockException("Not enough stock for product ID " + productId);
            }

            Product prod = productList.stream().filter(p -> p.getProductId() == productId).findFirst().orElse(null);
            if (prod == null) throw new InvalidDataException("Product not found.");

            total += prod.getPrice() * qty;

            OrderDetail detail = new OrderDetail(detailId, orderId, productId, qty);
            orderDetailService.addOrderDetail(detail);
            inv.removeFromInventory(qty);
            inventoryService.updateInventory(inv);
        }

        Order order = new Order(orderId, customerId, new Date(), total, "Placed");
        orderService.placeOrder(order);
        orderList.add(order);
        System.out.println("Order placed successfully. Total: Rs. " + total);
    }

    private static void trackOrderStatus() {
        System.out.print("Enter order ID to track: ");
        int orderId = scanner.nextInt();
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            System.out.println("Order Status: " + order.getStatus());
        } else {
            System.out.println("Order not found.");
        }
    }

    private static void viewSalesReport() {
        System.out.println("\n=== Sales Report ===");
        List<Order> orders = orderService.getAllOrders(); // Fetch from DB
        if (orders.isEmpty()) {
            System.out.println("No sales records available.");
        } else {
            orders.forEach(System.out::println);
        }
    }

    private static void updateCustomerAccount() {
        System.out.print("Enter customer ID to update: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Customer customer = customerService.getCustomer(id);
        if (customer != null) {
            System.out.print("New Email: ");
            String email = scanner.nextLine();
            System.out.print("New Phone: ");
            String phone = scanner.nextLine();
            System.out.print("New Address: ");
            String address = scanner.nextLine();
            customer.updateCustomerInfo(email, phone, address);
            customerService.updateCustomer(customer);
            System.out.println("Customer details updated.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void processPayment() {
        System.out.print("Enter order ID for payment: ");
        int orderId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Enter payment method (e.g., UPI, CARD, COD): ");
        String method = scanner.nextLine();
        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble(); scanner.nextLine();

        Payment payment = new Payment(orderId, method, amount);
        paymentService.recordPayment(payment);
        System.out.println("Payment of Rs. " + amount + " recorded successfully for Order ID: " + orderId);
    }

    private static void searchProductByName() {
        System.out.print("Enter product name to search: ");
        String name = scanner.nextLine().toLowerCase();

        productList.stream()
            .filter(p -> p.getProductName().toLowerCase().contains(name))
            .forEach(System.out::println);
    }
}