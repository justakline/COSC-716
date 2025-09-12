import java.util.*;

/**
 * A 'record' representing a product with a name, retail cost, and wholesale cost.
 * This class encapsulates product data and provides read-only access via getters.
 *
 * @param productName   Stores the human-readable name of the product.
 * @param retailCost    Holds the price charged to customers for this product.
 * @param wholesaleCost Holds the price the business pays to acquire this product.
 */
record Product(String productName, double retailCost, double wholesaleCost) {
    // No additional methods or fields needed; the record automatically provides
    // constructor, getters, equals, hashCode, and toString methods.
}


/**
 * Manages a collection of Product orders and provides operations to add, remove,
 * persist, and present them. This class contains simple business logic for
 * handling an in-memory order list and demonstrates basic persistence and output.
 */
class OrderProcessor {
    // Holds the current list of Product orders in memory.
    private final List<Product> orders = new ArrayList<>();

    // Database connection. (We are just pretending it's a database.)
    private final String databaseTarget = "<Some Path to a Database>";

    // Header text printed at the top of an invoice.
    private final static String RETAIL_INVOICE_HEADER = "================== Retail Invoice ==================";
    private final static String RETAIL_INVOICE_FOOTER = "====================================================";
    private final static String WHOLESALE_INVOICE_HEADER = "====================== Invoice =====================";
    private final static String WHOLESALE_INVOICE_FOOTER = "====================================================";

    /**
     * Adds a product to the current collection of orders.
     *
     * @param product the Product to insert into the orders list
     */
    public void addOrder(Product product) {
        // Append the given Product to the end of the orders list.
        orders.add(product);
    }

    /**
     * Removes a product from the current collection of orders.
     * If the product is not found, no change occurs.
     *
     * @param product the Product to remove from the orders list
     */
    public void removeOrder(Product product) {
        // Remove the first occurrence of the given Product from the orders list.
        orders.remove(product);
    }


    /**
     * Simulates saving all current orders to a database by printing SQL INSERT statements.
     * Displays a connection message and then an INSERT line per product.
     */
    public void saveOrdersToDatabase() {
        // Fake database save
        System.out.printf("Pretending to connect to %s%n", databaseTarget);

        // Iterate over each Product to simulate individual INSERT operations.
        for (Product p : orders) {
            // Print a generated SQL INSERT statement using the product's fields.
            System.out.printf("INSERT INTO orders VALUES('%s', %.2f, %.2f)%n", p.productName(), p.retailCost(), p.wholesaleCost());
        }
    }


    /**
     * Prints a formatted invoice for all current orders to the standard output.
     * Each product is listed with its name, retail cost, and wholesale cost.
     * The invoice includes a header and footer for the invoice.
     */
    public void printRetailInvoice() {
        // Print the invoice header line.
        System.out.println(RETAIL_INVOICE_HEADER);

        // Initialize a counter to number each product line item.
        int count = 1;
        double totalCost = 0.0;

        // Loop through each Product to print its details.
        for (Product p : orders) {
            // Calculate the total cost for the order.
            totalCost += p.retailCost();

            // Print the numbered line with product name and both cost values.
            System.out.printf("%d. %-10s | Retail Price: $%.2f %n", count++, p.productName(), p.retailCost());
        }

        // Print the total cost of all products.
        System.out.println("----------------------------------------------------");
        System.out.printf("Total: $%.2f%n", totalCost);

        // Print the invoice footer line.
        System.out.println(RETAIL_INVOICE_FOOTER);
    }


    /**
     * Prints a formatted invoice for all current orders to the standard output.
     * Each product is listed with its name, retail cost, and wholesale cost.
     * The invoice includes a header and footer for the invoice.
     */
    public void printWholesalerInvoice() {
        // Print the invoice header line.
        System.out.println(WHOLESALE_INVOICE_HEADER);

        // Initialize a counter to number each product line item.
        int count = 1;
        double totalCost = 0.0;

        // Loop through each Product to print its details.
        for (Product p : orders) {
            // Calculate the total cost for the order.
            totalCost += p.wholesaleCost();

            // Print the numbered line with product name and both cost values.
            System.out.printf("%d. %-10s | Wholesale: $%.2f | MSRP: $%.2f%n", count++, p.productName(), p.wholesaleCost(), p.retailCost());
        }

        System.out.println("----------------------------------------------------");
        if (count > 10 || totalCost > 1000.0) {
            System.out.println("Discount applied!");
            totalCost *= 0.9; // Apply a 10% discount
        }

        System.out.printf("Total: $%.2f%n", totalCost);

        // Print the invoice footer line.
        System.out.println(WHOLESALE_INVOICE_FOOTER);
    }
}
