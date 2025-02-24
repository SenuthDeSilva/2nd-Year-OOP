

import java.io.*;
import java.util.*;

public class WShoppingManager implements ShoppingManager {
    private static List<Product> productList;// List to store all products
    private List<Electronics> electronicsList; // List to store electronics products
    private List<Clothing> clothingList;// List to store clothing products
    File file = new File("text.txt");// File object for data storage
    ObjectOutputStream out = null;
    ObjectInputStream inp = null;
    // Constructor initializes lists for products, electronics, and clothing
    public WShoppingManager() {
        this.setProductList(new ArrayList<Product>());
        this.setElectronicsList(new ArrayList<Electronics>());
        this.setClothingList(new ArrayList<Clothing>());
    }
    // Setters for the lists
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setElectronicsList(List<Electronics> electronicsList) {
        this.electronicsList = electronicsList;
    }
    // Getters for electronics and clothing lists
    public void setClothingList(List<Clothing> clothingList) {
        this.clothingList = clothingList;
    }

//    public List<Electronics> getElectronicsList() {return electronicsList; }

//    public List<Clothing> getClothingList() {return clothingList; }

    // Getter for the main product list
    public static List<Product> getProductList() {
        return productList;
    }



    // Method to add a product to the shopping cart
    @Override
    public void addProductToCart() {
        Scanner in = new Scanner(System.in);

        if (productList.size() < 50) {
            System.out.println("Enter 1 to Add Electronic\n" + "Enter 2 to Add Clothing");
            int option = in.nextInt();

            if (option == 1) {
                // Adding an electronics product
                try {
                    System.out.println("Enter ID:");
                    String ID = in.next();
                    ID = ID.toUpperCase();
                    System.out.println("Enter name: ");
                    String name = in.next();
                    //System.out.println("Enter no of items: ");
                    int items = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.print("Enter number of items: ");

                        if (in.hasNextInt()) {
                            items = in.nextInt();
                            validInput = true;
                        } else {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            in.next(); // Consume the invalid input to avoid an infinite loop
                        }
                    }

                    double price = 0;
                    validInput = false;

                    while (!validInput) {
                        System.out.print("Enter price: ");

                        if (in.hasNextDouble()) {
                            price = in.nextDouble();
                            validInput = true;
                        } else {
                            System.out.println("Invalid input. Please enter a valid double for the price.");
                            in.next(); // Consume the invalid input to avoid an infinite loop
                        }
                    }
                    System.out.println("Enter brand:");
                    String brand = in.next();

                    int warranty = 0;
                    validInput = false;

                    while (!validInput) {
                        System.out.print("Enter warranty: ");

                        if (in.hasNextInt()) {
                            warranty = in.nextInt();
                            validInput = true;
                        } else {
                            System.out.println("Invalid input. Please enter a valid integer for the warranty.");
                            in.next(); // Consume the invalid input to avoid an infinite loop
                        }
                    }

                    Electronics electronics = new Electronics(ID, name, items, price, brand, warranty);
                    productList.add(electronics);
                    electronicsList.add(electronics);
                    System.out.println("Successfully added --> " + ID);
                } catch (Exception e) {
                    System.out.println("Wrong Input. Try again please.\n");
                    in.nextLine(); // Consume the invalid input
                }
            } else if (option == 2) {
                // Adding a clothing product
                try {
                    System.out.println("Enter ID:");
                    String ID = in.next();
                    ID = ID.toUpperCase();
                    System.out.println("Enter name:");
                    String name = in.next();
                    System.out.println("Enter number of items:");
                    int items;
                    while (true) {
                        try {
                            items = in.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer for the number of items.");
                            in.next(); // Consume the invalid input
                        } catch (NoSuchElementException e){
                            System.out.println("Input not found. Please check your input.");
                        }catch (Exception e) {
                            System.out.println("An unexpected error occurred. Try again.");
                            e.printStackTrace(); // Print the stack trace for debugging
                        }
                    }
                    System.out.println("Enter price:");
                    double price;
                    while (true) {
                        try {
                            price = in.nextDouble();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid double for the price.");
                            in.next(); // Consume the invalid input
                        }
                    }
                    System.out.println("Enter size: (S, M, L)");
                    String size = null;
                    while (true) {

                        size = in.next().toLowerCase(); // Convert input to lowercase

                        if (size.equals("s") || size.equals("m") || size.equals("l")) {
                            // Valid size entered, break the loop
                            break;
                        } else {
                            System.out.println("Invalid size entered. Please enter 'S', 'M', or 'L'.");
                        }
                    }
                    System.out.println("Enter color:");
                    String color = in.next();

                    Clothing clothing = new Clothing(ID, name, items, price, size, color);
                    productList.add(clothing);
                    clothingList.add(clothing);
                    System.out.println("Successfully added --> " + ID);
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred. Try again please.");
                    in.nextLine(); // Consume the invalid input
                }
            }
        } else {
            System.out.println("Maximum product limit exceeded");
        }
    }

    // Method to delete a product from the shopping cart
    @Override
    public void deleteTheProduct() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter Product ID to delete:");
        String deleteID = in.next();
        String deleteIDUpperCase = deleteID.toUpperCase();

        boolean found = false;

        for (Product product : productList) {
            if (product.getProductID().equals(deleteIDUpperCase)) {
                productList.remove(product);
                found = true;
                System.out.println("Successfully deleted product with ID: " + deleteID);
                break;
            }
        }

        if (!found) {
            System.out.println("Product with ID " + deleteID + " not found.");
        }
    }
    // Method to print the list of products
    @Override
    public void printProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("List of Products:");
            System.out.println(
                    "-----------------------------------------------------------------------------------------");
            System.out.printf("%15s %20s %20s %10s %15s", "Product Id", "Product Name", "No of Available", "Price",
                    "Category");
            System.out.println();
            System.out.println(
                    "-----------------------------------------------------------------------------------------");

            for (Product p : productList) {
                if (p instanceof Electronics) {
                    System.out.format("%15s %20s %12s %18s %15s", p.getProductID(), p.getProductName(),
                            p.getNoOfAvailableItems(), p.getPrice(), "Electronics");
                    System.out.println();
                } else if (p instanceof Clothing) {
                    System.out.format("%15s %20s %12s %18s %15s", p.getProductID(), p.getProductName(),
                            p.getNoOfAvailableItems(), p.getPrice(), "Clothing");
                    System.out.println();
                }
            }

            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }

    // Method to save products to a file
    @Override
    public void saveProductToFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Product product : productList) {
                if (product instanceof Electronics) {
                    writer.println("Electronics");
                } else if (product instanceof Clothing) {
                    writer.println("Clothing");
                }


                writer.println(("Product ID: ")+product.getProductID());
                writer.println(("Product Name: ")+product.getProductName());
                writer.println(("Available Items: ")+product.getNoOfAvailableItems());
                writer.println(("Price: ")+product.getPrice());


                if (product instanceof Electronics) {
                    writer.println(("Brand: ")+((Electronics) product).getBrand());
                    writer.println(("Warranty: ")+((Electronics) product).getWarranty());
                } else if (product instanceof Clothing) {
                    writer.println(("Size: ")+((Clothing) product).getSize());
                    writer.println(("Colour: ")+((Clothing) product).getColor());
                }
                writer.println("-------------------------------------");
            }

            System.out.println("Saved changes to the file ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to load products from a file
    public void loadProductFromFile() throws IOException {
        System.out.printf("Method Called");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String category = line.trim();
                String productId = reader.readLine().trim().split(": ")[1];
                String productName = reader.readLine().trim().split(": ")[1];
                int availableItems = Integer.parseInt(reader.readLine().trim().split(": ")[1]);
                double price = Double.parseDouble(reader.readLine().trim().split(": ")[1]);

                if ("Electronics".equals(category)) {
                    String brand = reader.readLine().trim().split(": ")[1];
                    String warranty = reader.readLine().trim().split(": ")[1];
                    int intValue = Integer.parseInt(warranty);
                    Electronics electronics = new Electronics(productId, productName, availableItems, price, brand, intValue);
                    productList.add(electronics);
                } else if ("Clothing".equals(category)) {
                    String size = reader.readLine().trim().split(": ")[1];
                    String color = reader.readLine().trim().split(": ")[1];
                    Clothing clothing = new Clothing(productId, productName, availableItems, price, size, color);
                    productList.add(clothing);
                }
                reader.readLine();
            }

            System.out.println("Loaded products from the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Menu method to display options to the user
    public void Menu() {
        System.out.println("Press 1 to Add a product.\n" + "Press 2 to Delete a product\n" + "Press 3 to Print products\n"
                + "Press 4 to Add to file\n" + "press 5 to exit\n"+"Press 6 read from a  file\n" + "press 7 to run gui\n\n");
    }

    // Method to get a product by its ID
    public Product getProductById(String productId) {
        for (Product product : productList) {
            if (product.getProductID().equals(productId)) {
                if (product instanceof Electronics) {
                    Electronics electronicsProduct = (Electronics) product;
                    return new Electronics(
                            electronicsProduct.getProductID(),
                            electronicsProduct.getProductName(),
                            electronicsProduct.getNoOfAvailableItems(),
                            electronicsProduct.getPrice(),
                            electronicsProduct.getBrand(),
                            electronicsProduct.getIntValue()
                    );
                } else if (product instanceof Clothing) {
                    Clothing clothingProduct = (Clothing) product;
                    return new Clothing(
                            clothingProduct.getProductID(),
                            clothingProduct.getProductName(),
                            clothingProduct.getNoOfAvailableItems(),
                            clothingProduct.getPrice(),
                            clothingProduct.getSize(),
                            clothingProduct.getColor()
                    );
                }
            }
        }
        return null;  // Return null if no product with the given ID is found
    }

}

