package supermarket.cart;

import supermarket.products.GroceryProduct;

public class CartItem {
    private GroceryProduct product;
    private int quantity;

    public CartItem(GroceryProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getActualPrice() * quantity;
    }

    public String display() {
        return product.display() +
                "\nQuantity: " + quantity +
                "\nTotal: $" + String.format("%.2f", getTotalPrice());
    }

    public GroceryProduct getProduct() {
        return product;
    }

    public void setProduct(GroceryProduct product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}