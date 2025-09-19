import java.util.*;

public class DatabaseService{
     
    // The url of the database we will connect to
    private final String databaseTarget;

    /*
     * Initializes the DatabaseService object
     * @param databaseTarget the url of the database that we will connect to
     */
    public DatabaseService(String databaseTarget){
        this.databaseTarget = databaseTarget;
    }

    /**
     * Simulates saving all current orders to a database by printing SQL INSERT statements.
     * Displays a connection message and then an INSERT line per product.
     */
    public void saveOrdersToDatabase(List<Product> orders) {
        // Fake database save
        System.out.printf("Pretending to connect to %s%n", databaseTarget);

        // Iterate over each Product to simulate individual INSERT operations.
        for (Product p : orders) {
            // Print a generated SQL INSERT statement using the product's fields.
            System.out.printf("INSERT INTO orders VALUES('%s', %.2f, %.2f)%n", p.productName(), p.retailCost(), p.wholesaleCost());
        }
    }
}