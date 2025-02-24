// Electronics class extends Product
public class Electronics extends Product {
    // Private fields specific to Electronics
    private String brand;// Brand of the electronics product
    private int warranty;// Warranty period of the electronics product

    // Constructor for creating an Electronics object
    public Electronics(String productID, String productName, int noOfAvailableItems, double price,
                       String brand, int warranty) {
        super(productID, productName, noOfAvailableItems, price);
        // Call the constructor of the parent class (Product)
        this.brand = brand;
        this.warranty = warranty;
    }
    // Getter method for retrieving the brand of the electronics product
    public String getBrand() {
        return brand;
    }
    // Getter method for retrieving the warranty period of the electronics product
    public int getWarranty() {
        return warranty;
    }

    // Placeholder method
    public int getIntValue() {
        return 0;
    }

}


