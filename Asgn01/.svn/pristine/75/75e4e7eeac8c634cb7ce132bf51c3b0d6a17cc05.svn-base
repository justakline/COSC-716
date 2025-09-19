
import java.util.*;
public class OrderViewer{

    // Header text printed at the top of an invoice.
    private final static String RETAIL_INVOICE_HEADER = "================== Retail Invoice ==================";
    private final static String RETAIL_INVOICE_FOOTER = "====================================================";
    private final static String WHOLESALE_INVOICE_HEADER = "====================== Invoice =====================";
    private final static String WHOLESALE_INVOICE_FOOTER = "====================================================";


    // Initializes the orderViewer
    public OrderViewer() {

    }

    // Prints the retail prices of all the products
    public void printRetailInvoice(List<Product> orders, DiscountStrategy discountStrategy) {
        // Print the invoice header line.
        System.out.println(RETAIL_INVOICE_HEADER);

        // Initialize a counter to number each product line item.
        int count = 1;
        double totalCost = discountStrategy.computeDiscount(orders);

        // Loop through each Product to print its details.
        for (Product p : orders) {

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
    public void printWholesalerInvoice(List<Product> orders, DiscountStrategy discountStrategy) {
        // Print the invoice header line.
        System.out.println(WHOLESALE_INVOICE_HEADER);

        // Initialize a counter to number each product line item.
        int count = 1;
        double totalCost = discountStrategy.computeDiscount(orders);

        // Loop through each Product to print its details.
        for (Product p : orders) {
            // Calculate the total cost for the order.

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