
// Interface for managing shopping-related operations
import java.io.IOException;

public interface ShoppingManager {
    // Method to add a product to the shopping cart
    void addProductToCart();
    // Method to delete a product from the shopping cart
    void deleteTheProduct();
    // Method to print the list of available products
    void printProducts();
    // Method to save the list of products to a file
    void saveProductToFile() throws IOException;
// Throws IOException if an I/O error occurs during the process
}
