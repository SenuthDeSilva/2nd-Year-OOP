//Chamath_Samuditha_w1954016_20220467

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static WShoppingManager manager = new WShoppingManager();// Create an instance of WestminsterShoppingManager
    static List<Product> loadedItems;// List to store loaded products from file

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        File file = new File("text.txt");
// Create a File object for data storage
        System.out.println("Welcome to the Westminster Shopping cart!!!\n");
        Scanner in = new Scanner(System.in);
// Scanner for user input

        boolean cont = true;// Variable to control the program loop

        while (cont) {
            manager.Menu();// Display the management menu
            System.out.println("Enter the Choice : ");
            String preference = in.next();
            switch (preference) {
                case "1":
                    manager.addProductToCart();// Add a product to the shopping cart
                    break;
                case "2":
                    manager.deleteTheProduct();// Delete a product from the system
                    break;
                case "3":
                    manager.printProducts();// Print the list of products
                    break;
                case "4":
                    manager.saveProductToFile(); // Save the product list to a file
                    break;
                case "5":
                    System.out.println("System Terminated!");
                    cont = false;// Terminate the program
                    break;
                case "6":
                    manager.loadProductFromFile();// Load products from a file
                    break;
                case "7":
                    // Open a graphical user interface (GUI) for the shopping cart
                    SwingUtilities.invokeLater(() -> {
                        ShoppingGUI gui = new ShoppingGUI(manager);
                        gui.refreshTable();
                    });
                    break;


                default:
                    System.out.println("Input should be between 1 to 7!!");
                    break;
            }
        }
    }


}
