package supermarket.app;

import supermarket.products.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SupermarketApp {
    private static ArrayList<GroceryProduct> products = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Supermarket Application!");

        // Add some sample products
        initSampleProducts();

        // Run the application loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    running = false;
                    System.out.println("Thank you for using Supermarket App!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void initSampleProducts() {
        products.add(new GroceryProduct("Toast", 2.5, 10));
        products.add(new BeverageProduct("Coke", 1.5, 0, Sugarlevel.ZERO));
        products.add(new DairyProduct("Milk", 4.0, 0, Fat.FULLCREAM));
    }

    private static void displayMenu() {
        System.out.println("\n=== Supermarket Management System ===");
        System.out.println("1. View Products");
        System.out.println("2. Add Product");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void viewProducts() {
        System.out.println("\n=== Product List ===");
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            System.out.println("\nProduct #" + (i+1));
            System.out.println(products.get(i).display());
        }
    }

    private static void addProduct() {
        System.out.println("\n=== Add New Product ===");
        System.out.println("Select product type:");
        System.out.println("1. Regular Grocery Product");
        System.out.println("2. Beverage Product");
        System.out.println("3. Dairy Product");
        System.out.print("Enter choice: ");

        int typeChoice = getMenuChoice();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter discount percentage: ");
        double discount = Double.parseDouble(scanner.nextLine());

        switch (typeChoice) {
            case 1:
                products.add(new GroceryProduct(name, price, discount));
                break;
            case 2:
                System.out.println("Select sugar level:");
                System.out.println("1. LIGHT");
                System.out.println("2. ZERO");
                System.out.println("3. ADDED_SUGAR");
                System.out.println("4. NO_ADDED_SUGAR");
                System.out.print("Enter choice: ");
                int sugarChoice = getMenuChoice();
                Sugarlevel sugarLevel = Sugarlevel.values()[sugarChoice - 1];
                products.add(new BeverageProduct(name, price, discount, sugarLevel));
                break;
            case 3:
                System.out.println("Select fat level:");
                System.out.println("1. FULLCREAM");
                System.out.println("2. HALFCREAM");
                System.out.println("3. SKIMMED");
                System.out.print("Enter choice: ");
                int fatChoice = getMenuChoice();
                Fat fat = Fat.values()[fatChoice - 1];
                products.add(new DairyProduct(name, price, discount, fat));
                break;
            default:
                System.out.println("Invalid product type selected.");
                return;
        }

        System.out.println("Product added successfully!");
    }
}