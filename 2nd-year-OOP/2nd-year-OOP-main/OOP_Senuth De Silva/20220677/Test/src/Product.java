

import java.io.Serializable;
// Abstract class representing a generic product
public abstract class Product implements Serializable {
    private String productID;
    private String productName;
    private int noOfAvailableItems;
    private double price;
    // Constructor to initialize the common attributes of a product
    public Product(String productID, String productName, int noOfAvailableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.noOfAvailableItems = noOfAvailableItems;
        this.price = price;
    }
    // Getter method to retrieve the product ID
    public String getProductID() {
        return productID;
    }
    // Getter method to retrieve the product name
    public String getProductName() {
        return productName;
    }
    // Getter method to retrieve the number of available items
    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }
    // Getter method to retrieve the price of the product
    public double getPrice() {
        return price;
    }
}
