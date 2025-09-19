import java.util.List;

public class GrossDiscount implements DiscountStrategy{


    // If the total order is more than $500 then the resulting total cost is 95% of actual total.
    @Override
    public double computeDiscount(List<Product> orders) {
        double totalCost = 0;

        for (Product p : orders){
            totalCost += p.retailCost();
        }

        return totalCost > 500? totalCost * 0.95: totalCost;
    }


}
