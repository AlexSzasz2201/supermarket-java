package supermarket.products;

import java.util.ArrayList;

public class GroceryProduct {
    private String name;
    private double price;
    private double discount;

    public GroceryProduct(String name, double price, double discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public double getActualPrice() {
        //return price - (price * discount / 100);
        return this.getPrice() - (this.getPrice() * this.getDiscount() / 100);
    }

    public String display() {
        return ("Name: " + this.getName() + "\n" +
                "Price: " + this.getPrice() + "\n" +
                "Discount: " + this.getDiscount() + "%"
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public static void main(String[] args) {
        ArrayList<GroceryProduct> cart = new ArrayList<GroceryProduct>();

        GroceryProduct toast = new GroceryProduct("Toast", 2.5, 10);
        cart.add(toast);

        BeverageProduct coke = new BeverageProduct("Coke", 1.5, 0, Sugarlevel.ZERO);
        cart.add(coke);

        DairyProduct milk = new DairyProduct("Milk", 4, 0, Fat.FULLCREAM);
        cart.add(milk);

        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            total += cart.get(i).getActualPrice();
        }

        System.out.print("The total cost = " + total);


    }

}
