import java.util.List;

public interface DiscountStrategy {
    // Compute New total
    double computeDiscount(List<Product> orders);
}
