package objectAdventure.world.aconover;

import objectAdventure.core.item.Item;

import java.util.List;

/**
 * A sample item for demonstration purposes.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class DemoItem implements Item {

    // The descriptions of the item as displayed to the user.
    private final String description;

    // The name of the item, as displayed to the user.
    private final String displayName;

    /**
     * Constructor
     */
    public DemoItem() {
        this.displayName = "Demonstration Item";
        this.description = "An item for demonstration purposes.";
    }

    /**
     * Get the full description of the room.
     *
     * @return The item description.
     */
    @Override
    public String getItemFullDescription() {
        return this.description;
    }


    /**
     * Get the list of item aliases.
     * <p>
     * *** ITEM ALIASES MUST NOT CONTAIN SPACES! ***
     *
     * @return The list of item aliases.
     */
    @Override
    public List<String> getItemAliases() {
        return List.of("Demo", "Demo-Item", "Demonstration-Item", "Mysterious-thing");
    }

    /**
     * The short name of the item for display in lists, etc.
     *
     * @return A short item description that will show in inventory and looking around a room.
     */
    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }


//    /* Item Interaction Example
//     *
//     * The actual parameter is an enum (singleton) value of one of the following:
//     *
//     * TAKE("Get", "Take"): Take an Item.
//     * DROP("Drop"): Drop an Item.
//     * INSPECT("Inspect", "Examine"): Inspect an Item.
//     * USE("Use"): Use an Item.
//     * PUSH("Push"), PULL("Push"): Push or pull an Item.
//     * ACTIVATE("Activate", "Enable"), DEACTIVATE("Deactivate", "Disable"): Activate or deactivate an Item.
//     * OPEN("Open"), CLOSE("Close"): Open or close an Item.
//     * REPAIR("Fix", "Repair", "Mend"), DESTROY("Break", "Destroy", "Smash"): Repair or destroy an Item.
//     * CONSUME("Eat", "Drink", "Consume"): Eat, drink, or consume an Item.
//     * LOCK("Lock"), UNLOCK("Unlock"): Lock or unlock an Item.
//     * TAUNT("Taunt"); // Do NOT taunt Happy Fun Ball!!!
//     *
//     * @author Adam J. Conover, COSC436/COSC716
//     *
//     * @param itemInteractionEvent@return The ItemInteractionResult object.
//     * @see ItemInteractionEventType
//     **/
//    @Override
//    public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
//        return switch (itemInteractionEvent.event()) {
//
//            case GET -> {
//                // Do whatever you want when an item is picked up.
//                String previousName = this.getItemDisplayName();
//                this.displayName = "Looted Demo Item";
//
//                // Then return a success message.
//                yield ItemInteractionResult.success("Congratulations! You picked up the %s".formatted(previousName));
//            }
//
//            case USE -> {
//                // Do whatever you want when an item is picked up.
//                this.description = "A slightly used demonstration item.";
//
//                // Then return a success message.
//                yield ItemInteractionResult.success("You use the " + this.getItemDisplayName());
//            }
//
//            // Everything else should "succeed", but do nothing.
//            default ->
//                    ItemInteractionResult.failure("I don't know to do that with the %s.".formatted(this.getItemDisplayName()));
//        };
//    }
}
