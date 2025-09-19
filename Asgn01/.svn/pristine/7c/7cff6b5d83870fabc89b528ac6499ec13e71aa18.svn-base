import java.util.List;

public class VolumeDiscount implements DiscountStrategy{

    // If more than 3 items are in the order, then the resulting total cost is 90% of actual total.
    @Override
    public double computeDiscount(List<Product> orders) {
        double totalCost = 0;

        for (Product p : orders){
            totalCost += p.retailCost();
        }

        return orders.size() > 3? totalCost * 0.9: totalCost;
    }
}
