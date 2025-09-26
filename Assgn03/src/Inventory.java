
import java.util.ArrayList;
import java.util.List;


public class Inventory {
    // Implementation using a ArrayList but could be any subclass of List
    private List<Item> items = new ArrayList<>();

    public void addItem(Item i){
        //Implementation so this would runw ith the driver
        items.add(i);

    }

}
