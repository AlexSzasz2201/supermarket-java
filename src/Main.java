import supermarket.cart.ShoppingCart;
import supermarket.inventory.ProductInventory;
import supermarket.products.BeverageProduct;
import supermarket.products.DairyProduct;
import supermarket.products.Fat;
import supermarket.products.GroceryProduct;
import supermarket.products.Sugarlevel;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ShoppingCart cart = new ShoppingCart();
    private static ProductInventory inventory = new ProductInventory();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to the Supermarket Application!");

        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    viewAndAddFromInventory();
                    break;
                case 2:
                    addCustomProductToInventory();
                    break;
                case 3:
                    updateProductQuantity();
                    break;
                case 4:
                    removeProductFromCart();
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    checkoutCart();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            // Add a small pause to make output more readable
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== SUPERMARKET MENU =====");
        System.out.println("1. View inventory and add to cart");
        System.out.println("2. Add custom product to inventory");
        System.out.println("3. Update product quantity in cart");
        System.out.println("4. Remove product from cart");
        System.out.println("5. View cart");
        System.out.println("6. Checkout");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }

    private static void viewAndAddFromInventory() {
        inventory.displayInventory();

        System.out.print("\nEnter product ID to add to cart (0 to cancel): ");
        int productId;
        try {
            productId = Integer.parseInt(scanner.nextLine());
            if (productId == 0) {
                return;
            }

            GroceryProduct product = inventory.getProductByIndex(productId - 1);
            if (product == null) {
                System.out.println("Invalid product ID!");
                return;
            }

            System.out.printf("Selected: %s ($%.2f)\n", product.getName(), product.getPrice());
            System.out.print("Enter quantity: ");

            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity <= 0) {
                    System.out.println("Quantity must be positive!");
                    return;
                }

                if (!inventory.isProductAvailable(product.getName(), quantity)) {
                    System.out.println("Not enough stock available! Available: "
                            + inventory.getAvailableStock(product.getName()));
                    return;
                }

                cart.addProduct(product, quantity);
                inventory.updateStock(product.getName(), -quantity);
                System.out.println("Product added to cart successfully!");

            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid product ID!");
        }
    }

    private static void addCustomProductToInventory() {
        System.out.println("\n===== ADD CUSTOM PRODUCT =====");
        System.out.println("Select product type:");
        System.out.println("1. Regular Grocery Product");
        System.out.println("2. Beverage Product");
        System.out.println("3. Dairy Product");
        System.out.print("Enter your choice: ");

        int productType;
        try {
            productType = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Returning to main menu.");
            return;
        }

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        // Check if product with this name already exists
        for (GroceryProduct product : inventory.getProducts()) {
            if (product.getName().equals(name)) {
                System.out.println("A product with this name already exists!");
                return;
            }
        }

        double price = 0;
        System.out.print("Enter price: ");
        try {
            price = Double.parseDouble(scanner.nextLine());
            if (price < 0) {
                System.out.println("Price cannot be negative. Using 0.");
                price = 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Using default price of 0.");
        }

        double discount = 0;
        System.out.print("Enter discount percentage: ");
        try {
            discount = Double.parseDouble(scanner.nextLine());
            if (discount < 0 || discount > 100) {
                System.out.println("Discount must be between 0 and 100. Using 0.");
                discount = 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid discount. Using default discount of 0%.");
        }

        System.out.print("Enter stock quantity: ");
        int stockQuantity;
        try {
            stockQuantity = Integer.parseInt(scanner.nextLine());
            if (stockQuantity < 0) {
                System.out.println("Stock cannot be negative. Using 0.");
                stockQuantity = 0;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Using default of 0.");
            stockQuantity = 0;
        }

        GroceryProduct product = null;

        switch (productType) {
            case 1:
                product = new GroceryProduct(name, price, discount);
                break;
            case 2:
                System.out.println("Select sugar level:");
                displayEnumOptions(Sugarlevel.values());
                int sugarChoice = getUserChoice() - 1;

                if (sugarChoice >= 0 && sugarChoice < Sugarlevel.values().length) {
                    Sugarlevel sugarLevel = Sugarlevel.values()[sugarChoice];
                    product = new BeverageProduct(name, price, discount, sugarLevel);
                } else {
                    System.out.println("Invalid sugar level. Using ZERO as default.");
                    product = new BeverageProduct(name, price, discount, Sugarlevel.ZERO);
                }
                break;
            case 3:
                System.out.println("Select fat level:");
                displayEnumOptions(Fat.values());
                int fatChoice = getUserChoice() - 1;

                if (fatChoice >= 0 && fatChoice < Fat.values().length) {
                    Fat fat = Fat.values()[fatChoice];
                    product = new DairyProduct(name, price, discount, fat);
                } else {
                    System.out.println("Invalid fat level. Using SKIMMED as default.");
                    product = new DairyProduct(name, price, discount, Fat.SKIMMED);
                }
                break;
            default:
                System.out.println("Invalid product type. Product not added.");
                return;
        }

        inventory.addProductToInventory(product, stockQuantity);
        System.out.println("Product added to inventory successfully!");
    }

    private static void updateProductQuantity() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        System.out.println("\n===== UPDATE QUANTITY =====");
        cart.displayCart();

        System.out.print("\nEnter product name to update: ");
        String productName = scanner.nextLine();

        // Check if product is in cart
        boolean found = false;
        int currentQuantity = 0;
        for (int i = 0; i < cart.getItems().size(); i++) {
            if (cart.getItems().get(i).getProduct().getName().equals(productName)) {
                found = true;
                currentQuantity = cart.getItems().get(i).getQuantity();
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found in cart!");
            return;
        }

        System.out.print("Enter new quantity (0 to remove): ");
        try {
            int newQuantity = Integer.parseInt(scanner.nextLine());
            if (newQuantity < 0) {
                System.out.println("Quantity cannot be negative!");
                return;
            }

            // Calculate the difference in quantity
            int quantityDifference = newQuantity - currentQuantity;

            // If adding more items, check inventory
            if (quantityDifference > 0) {
                if (!inventory.isProductAvailable(productName, quantityDifference)) {
                    System.out.println("Not enough stock available! Available: "
                            + inventory.getAvailableStock(productName));
                    return;
                }
            }

            // Update cart quantity
            cart.updateQuantity(productName, newQuantity);

            // Update inventory
            inventory.updateStock(productName, -quantityDifference);

            if (newQuantity == 0) {
                System.out.println("Product removed from cart!");
            } else {
                System.out.println("Quantity updated successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity!");
        }
    }

    private static void removeProductFromCart() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        System.out.println("\n===== REMOVE PRODUCT =====");
        cart.displayCart();

        System.out.print("\nEnter product name to remove: ");
        String productName = scanner.nextLine();

        // Check if product is in cart and get its quantity
        boolean found = false;
        int quantityInCart = 0;
        for (int i = 0; i < cart.getItems().size(); i++) {
            if (cart.getItems().get(i).getProduct().getName().equals(productName)) {
                found = true;
                quantityInCart = cart.getItems().get(i).getQuantity();
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found in cart!");
            return;
        }

        // Return items to inventory
        inventory.updateStock(productName, quantityInCart);

        // Remove from cart
        cart.removeProduct(productName);
        System.out.println("Product removed from cart successfully!");
    }

    private static void checkoutCart() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty! Nothing to checkout.");
            return;
        }

        System.out.println("\n===== CHECKOUT =====");
        cart.displayCart();

        System.out.printf("\nTotal amount: $%.2f\n", cart.calculateTotal());
        System.out.print("Confirm payment (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y") || confirmation.equals("yes")) {
            System.out.println("\nPayment successful!");
            System.out.println("Thank you for your purchase!");

            // Empty the cart after successful checkout
            cart = new ShoppingCart();
        } else {
            System.out.println("Payment cancelled. Your items remain in the cart.");
        }
    }

    private static <T> void displayEnumOptions(T[] values) {
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ". " + values[i]);
        }
        System.out.print("Enter your choice: ");
    }
}