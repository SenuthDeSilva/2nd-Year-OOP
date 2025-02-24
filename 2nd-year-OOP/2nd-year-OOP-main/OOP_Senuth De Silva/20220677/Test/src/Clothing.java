// Class representing a clothing product, extending the Product class
public class Clothing extends Product {
    private String size;
    private String color;
    // Constructor to initialize the attributes of a clothing product
    public Clothing(String productID, String productName, int noOfAvailableItems, double price,
                    String size, String color) {
        super(productID, productName, noOfAvailableItems, price);
        this.size = size;
        this.color = color;
    }
    // Getter method to retrieve the size of the clothing
    public String getSize() {
        return size;
    }
    // Getter method to retrieve the color of the clothing
    public String getColor() {
        return color;
    }
}
