/**
 * Test class for OrderProcessing.
 * <p>
 * (Of course, *real* unit tests would be much better, but this is fine for such a simple program.)
 */
public class MAIN {
    /**
     * !!!! DEMONSTRATION AND TEST OF THE ORDER PROCESSOR CLASS. !!!!
     * <p>
     * NOTE: MODIFY THIS METHOD TO ACCOMMODATE THE REFACTORED ORDER PROCESSOR CLASS.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create an OrderProcessor instance to manage the demo orders.
        OrderProcessor processor = new OrderProcessor();

        // Create sample products
        Product laptop = new Product("Laptop", 999.99, 750.00);
        Product phone = new Product("Smartphone", 599.99, 400.00);
        Product tablet = new Product("Tablet", 299.99, 200.00);

        // Add products to orders
        processor.addOrder(laptop);
        processor.addOrder(phone);
        processor.addOrder(tablet);

        // Print wholesaler invoice
        System.out.println("Initial Wholesaler Invoice:");
        processor.printWholesalerInvoice();
        System.out.println();

        // Print current invoice
        System.out.println("Initial Retail Invoice:");
        processor.printRetailInvoice();
        System.out.println();

        // Remove one product
        processor.removeOrder(phone);

        // Print updated invoice
        System.out.println("\nRetail Invoice after removing phone:");
        processor.printRetailInvoice();
        System.out.println();


        // SIMULATE persisting the current orders to a database.
        processor.saveOrdersToDatabase();
    }
}
