package supermarket.products;

public class BeverageProduct extends GroceryProduct {
    private Sugarlevel sugarLevel;

public BeverageProduct(String name, double price, double discount, Sugarlevel sugarLevel) {
    super(name, price, discount);
    this.sugarLevel = sugarLevel;
}

public String display() {
    return (super.display() + "\nSugar Level: " + this.getSugarLevel());
}

public Sugarlevel getSugarLevel(){
    return sugarLevel;
}

public void setSugarLevel(Sugarlevel sugarLevel) {
    this.sugarLevel = sugarLevel;
}

}

