# Java Supermarket Management System

A console-based supermarket management application demonstrating object-oriented programming principles in Java.

## Project Overview

This application simulates a supermarket system with inventory management, shopping cart functionality, and checkout process. It features a product hierarchy with different types of grocery items, quantity tracking, and a command-line interface for user interaction.

## Features

- *Product Hierarchy*: Base product class with specialized subclasses for different product types
  - GroceryProduct: Base class for all products
  - BeverageProduct: Products with sugar level attributes
  - DairyProduct: Products with fat content attributes

- *Inventory Management*
  - Pre-loaded product catalog
  - Stock tracking and availability checks
  - Custom product creation

- *Shopping Cart*
  - Add products with specified quantities
  - Update quantities with inventory validation
  - Remove products (returns stock to inventory)
  - Calculate total price with discounts

- *User Interface*
  - Console-based menu system
  - Product browsing and selection
  - Cart management
  - Checkout process

## Project Structure

```
src/ 
├── supermarket/
│   ├── products/
│   │   ├── GroceryProduct.java  (Base product class)
│   │   ├── BeverageProduct.java (Extends GroceryProduct)
│   │   ├── DairyProduct.java    (Extends GroceryProduct)
│   │   ├── Sugarlevel.java      (Enum for beverage sugar levels)
│   │   └── Fat.java             (Enum for dairy fat levels)
│   ├── cart/
│   │   ├── CartItem.java        (Product with quantity)
│   │   └── ShoppingCart.java    (Collection of cart items)
│   └── inventory/
│       └── ProductInventory.java (Product catalog and stock management)
└── Main.java                    (Application entry point and UI)
```

## Class Descriptions

- *GroceryProduct*: Base class for all products with name, price, and discount properties
- *BeverageProduct*: Extends GroceryProduct with sugar level information
- *DairyProduct*: Extends GroceryProduct with fat content information
- *CartItem*: Represents a product with quantity in the shopping cart
- *ShoppingCart*: Manages the collection of products the user intends to purchase
- *ProductInventory*: Maintains the store's product catalog and stock levels
- *Main*: Provides the user interface and orchestrates the application flow

## How to Run

1. Compile all Java files:
   ```
   javac Main.java supermarket/products/.java supermarket/cart/.java supermarket/inventory/*.java
   ```
   
2. Run the application:
   ```
   java Main
   ```

3. Follow the on-screen menu to interact with the system.

## Sample Usage

1. View the inventory of available products
2. Add products to your shopping cart
3. Adjust quantities of products in your cart
4. Remove products from your cart
5. Checkout to complete your purchase

## Object-Oriented Programming Concepts

This project demonstrates several key OOP concepts:

- *Inheritance*: Product types inherit from a base product class
- *Encapsulation*: Data is encapsulated within classes with appropriate getters/setters
- *Composition*: Shopping cart contains cart items, which contain products
- *Polymorphism*: Different product types implement the display method differently
- *Single Responsibility*: Each class has a focused purpose

## Future Enhancements

Potential features for future development:

- Persistent storage using files or database
- Product categories and filtering
- User authentication system
- Graphical user interface
- Sales reporting and analytics

## Author

Alexandru Szasz

## License

This project is open source and available under the MIT License.
   
