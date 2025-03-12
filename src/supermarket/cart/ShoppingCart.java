package supermarket.cart;

import supermarket.products.GroceryProduct;
import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(GroceryProduct product, int quantity) {
        // Check if the product is already in the cart
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                // Update quantity if product already exists
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        // Add new product if not found
        items.add(new CartItem(product, quantity));
    }

    public void removeProduct(String productName) {
        items.removeIf(item -> item.getProduct().getName().equals(productName));
    }

    public void updateQuantity(String productName, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(productName)) {
                if (quantity <= 0) {
                    removeProduct(productName);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("\n===== SHOPPING CART =====");
        if (items.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        System.out.println("Item\tName\t\t\tPrice\tQty\tDiscount\tTotal");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            GroceryProduct product = item.getProduct();

            String name = product.getName();
            // Ensure name is displayed with proper spacing
            String displayName = name.length() > 15 ? name.substring(0, 12) + "..." : name;
            while (displayName.length() < 15) {
                displayName += " ";
            }

            System.out.printf("%d\t%s\t$%.2f\t%d\t%.0f%%\t\t$%.2f\n",
                    i + 1,
                    displayName,
                    product.getPrice(),
                    item.getQuantity(),
                    product.getDiscount(),
                    item.getTotalPrice());
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println("Total Cart Value: $" + String.format("%.2f", calculateTotal()));
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }
}