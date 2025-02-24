import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class ShoppingGUI extends JFrame {

    private WShoppingManager manager;//declares manager
    private DefaultTableModel tableModel;//declares tablemodel
    private JComboBox<String> categoryFilterComboBox;
    //a graphical component used to present a list of options in a drop-down menu, and it appears to be specifically for filtering products by category.
    private JPanel detailsPanel;  // New panel for displaying product details
    private JButton addToCartButton;  // Button to add items to the cart
    private JButton viewCartButton;  // Button to view the shopping cart

    public ShoppingGUI(WShoppingManager manager) { // Constructor for the ShoppingGUI class
        this.manager = manager;
        initComponents();
    }

    private void initComponents() { // Method to initialize the components of the GUI
        setTitle("Shopping GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        String[] columns = {"Product ID", "Product Name", "Available Items", "Price", "Category"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        categoryFilterComboBox = new JComboBox<>();
        categoryFilterComboBox.addItem("All");
        categoryFilterComboBox.addItem("Electronics");
        categoryFilterComboBox.addItem("Clothing");


        categoryFilterComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                refreshTable();
            }
        });

        filterPanel.add(new JLabel("Category Filter: "));
        filterPanel.add(categoryFilterComboBox);

        filterPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));

        topPanel.add(filterPanel, BorderLayout.WEST);

        JPanel sortButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryFilterComboBox.getSelectedItem();
                sortProducts(selectedCategory);
                refreshTable();
            }
        });

        sortButtonPanel.add(sortButton);
        topPanel.add(sortButtonPanel, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Initialize the detailsPanel
        detailsPanel = new JPanel(new BorderLayout());
        panel.add(detailsPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(800, 600));
        add(panel);
        pack();
        setVisible(true);

        // Add a ListSelectionListener to the table
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                displayProductDetails(selectedRow);
            }
        });

    }

    private void displayProductDetails(int selectedRow) { // Method to display product
        String productId = (String) tableModel.getValueAt(selectedRow, 0);
        Product selectedProduct = manager.getProductById(productId);

        if (selectedProduct != null) {
            detailsPanel.removeAll(); // Clear the previous details
            JLabel detailsLabel = new JLabel("Product Details:");
            JTextArea detailsTextArea = new JTextArea();
            detailsTextArea.append("Product ID: " + selectedProduct.getProductID() + "\n");
            detailsTextArea.append("Product Name: " + selectedProduct.getProductName() + "\n");
            detailsTextArea.append("Available Items: " + selectedProduct.getNoOfAvailableItems() + "\n");
            detailsTextArea.append("Price: " + selectedProduct.getPrice() + "\n");
            detailsTextArea.append("Category: " + (selectedProduct instanceof Electronics ? "Electronics" : "Clothing") + "\n");

            detailsPanel.add(detailsLabel, BorderLayout.NORTH);
            detailsPanel.add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);
            detailsPanel.revalidate(); // Refresh the panel
        }
    }

    public void refreshTable() { // Method to refresh
        tableModel.setRowCount(0);
        String selectedCategory = (String) categoryFilterComboBox.getSelectedItem();
        List<Product> productList = manager.getProductList();

        for (Product product : productList) {
            boolean addToTable = false;
            if (selectedCategory.equals("All")) {
                addToTable = true;
            } else if (selectedCategory.equals("Electronics") && product instanceof Electronics) {
                addToTable = true;
            } else if (selectedCategory.equals("Clothing") && product instanceof Clothing) {
                addToTable = true;
            }

            if (addToTable) {
                String[] rowData = {product.getProductID(), product.getProductName(),
                        String.valueOf(product.getNoOfAvailableItems()), String.valueOf(product.getPrice()),
                        product instanceof Electronics ? "Electronics" : "Clothing"};
                tableModel.addRow(rowData);
            }
        }
    }

    private void sortProducts(String selectedCategory) { // Method to sort products
        List<Product> sortedList = new ArrayList<>(manager.getProductList());

        if ("All".equals(selectedCategory)) {
            Collections.sort(sortedList, Comparator.comparing(Product::getProductName));
        } else if ("Electronics".equals(selectedCategory)) {
            Collections.sort(sortedList, Comparator.comparing(Product::getProductName));
        } else if ("Clothing".equals(selectedCategory)) {
            Collections.sort(sortedList, Comparator.comparing(Product::getProductName));
        }

        manager.setProductList(sortedList);
        refreshTable();
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WShoppingManager manager = new WShoppingManager();// Create an instance of WShoppingManager and ShoppingGUI
            ShoppingGUI gui = new ShoppingGUI(manager);
            gui.refreshTable();
        });
    }
}
