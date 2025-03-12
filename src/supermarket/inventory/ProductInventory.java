package supermarket.inventory;

import supermarket.products.BeverageProduct;
import supermarket.products.DairyProduct;
import supermarket.products.Fat;
import supermarket.products.GroceryProduct;
import supermarket.products.Sugarlevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductInventory {
    private ArrayList<GroceryProduct> products;
    private Map<String, Integer> stock;

    public ProductInventory() {
        this.products = new ArrayList<>();
        this.stock = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        // Add some default products

        // Regular grocery products
        addProductToInventory(new GroceryProduct("Bread", 2.99, 0), 20);
        addProductToInventory(new GroceryProduct("Rice", 3.49, 5), 15);
        addProductToInventory(new GroceryProduct("Pasta", 1.99, 0), 25);

        // Beverage products
        addProductToInventory(new BeverageProduct("Cola", 1.49, 0, Sugarlevel.ADDED_SUGAR), 30);
        addProductToInventory(new BeverageProduct("Diet Cola", 1.49, 0, Sugarlevel.ZERO), 30);
        addProductToInventory(new BeverageProduct("Orange Juice", 2.99, 0, Sugarlevel.NO_ADDED_SUGAR), 15);
        addProductToInventory(new BeverageProduct("Apple Juice", 2.49, 10, Sugarlevel.NO_ADDED_SUGAR), 15);

        // Dairy products
        addProductToInventory(new DairyProduct("Full Cream Milk", 3.99, 0, Fat.FULLCREAM), 20);
        addProductToInventory(new DairyProduct("Semi-Skimmed Milk", 3.79, 0, Fat.HALFCREAM), 20);
        addProductToInventory(new DairyProduct("Skimmed Milk", 3.59, 0, Fat.SKIMMED), 15);
        addProductToInventory(new DairyProduct("Cheddar Cheese", 4.99, 5, Fat.FULLCREAM), 10);
        addProductToInventory(new DairyProduct("Low-fat Yogurt", 1.99, 0, Fat.SKIMMED), 12);
    }

    public void addProductToInventory(GroceryProduct product, int quantity) {
        products.add(product);
        stock.put(product.getName(), quantity);
    }

    public GroceryProduct getProductByIndex(int index) {
        if (index >= 0 && index < products.size()) {
            return products.get(index);
        }
        return null;
    }

    public boolean isProductAvailable(String productName, int requestedQuantity) {
        for (GroceryProduct product : products) {
            if (product.getName().equals(productName)) {
                Integer availableQuantity = stock.get(productName);
                return availableQuantity != null && availableQuantity >= requestedQuantity;
            }
        }
        return false;
    }

    public boolean updateStock(String productName, int quantityChange) {
        Integer currentStock = stock.get(productName);
        if (currentStock != null) {
            int newStock = currentStock + quantityChange;
            if (newStock >= 0) {
                stock.put(productName, newStock);
                return true;
            }
        }
        return false;
    }

    public int getAvailableStock(String productName) {
        Integer stockQuantity = stock.get(productName);
        return stockQuantity != null ? stockQuantity : 0;
    }

    public ArrayList<GroceryProduct> getProducts() {
        return products;
    }

    public void displayInventory() {
        System.out.println("\n===== PRODUCT INVENTORY =====");
        System.out.println("ID\tName\t\t\tPrice\tDiscount\tStock\tType");
        System.out.println("----------------------------------------------------------------------------------");

        for (int i = 0; i < products.size(); i++) {
            GroceryProduct product = products.get(i);
            String type = "Grocery";
            if (product instanceof BeverageProduct) {
                type = "Beverage";
            } else if (product instanceof DairyProduct) {
                type = "Dairy";
            }

            String name = product.getName();
            // Ensure name is displayed with proper spacing
            String displayName = name.length() > 15 ? name.substring(0, 12) + "..." : name;
            while (displayName.length() < 15) {
                displayName += " ";
            }

            System.out.printf("%d\t%s\t$%.2f\t%.0f%%\t\t%d\t%s\n",
                    i + 1,
                    displayName,
                    product.getPrice(),
                    product.getDiscount(),
                    stock.get(product.getName()),
                    type);
        }
        System.out.println("----------------------------------------------------------------------------------");
    }
}