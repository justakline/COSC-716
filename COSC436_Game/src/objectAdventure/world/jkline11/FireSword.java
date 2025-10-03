package objectAdventure.world.jkline11; 
import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.List;

/**
 *  A long, sharp blade radiating with unatural and unwavering heat.
                The edge glows a dim-ember light, and the hilt is wrapped with scorched,
                fireproof leather. 
 *
 * @author jkline11, COSC436/COSC716
 */
public class FireSword implements Item {

    private final String description;
    private final String displayName;

    /**
     * Constructor for your item
     */
    public FireSword() {
        this.displayName = "Eternal Fire Sword";
        this.description ="""
                A long, sharp blade radiating with unatural and unwavering heat.
                The edge glows a dim-ember light, and the hilt is wrapped with scorched,
                fireproof leather. 
                """;
    }

    /**
     * Get the full description of the item.
     *
     * @return The detailed item description.
     */
    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    /**
     * Get the short display name of the item.
     *
     * @return The short item name for lists and inventory.
     */
    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }

    /**
     * Get the list of item aliases (names players can use to reference this item).
     * IMPORTANT: Aliases must NOT contain spaces! Use hyphens or underscores instead.
     *
     * @return List of aliases for this item.
     */
    @Override
    public List<String> getItemAliases() {
        return List.of("Fire Sword", "Sword", "FireSword", "Hot Sword");
    }

    /**
     * Make the item non-moveable if needed (optional)
     * @return true if the item cannot be picked up
     */
    @Override
    public boolean isAnchored() {
        return false; // Set to true if item should stay in the room
    }

    /**
 * Handle player interactions with this item.
 *
 * @param itemInteractionEvent The interaction event
 * @return The result of the interaction
 */
@Override
public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
    return switch (itemInteractionEvent.event()) {
            // Do something when the item is used
        case USE -> ItemInteractionResult.success("You use the " + this.getItemDisplayName() + " successfully!");

        // Provide additional detail when examined
        case INSPECT -> ItemInteractionResult.success("The blade glows a dim yellow-red.");

        case CONSUME -> ItemInteractionResult.failure("Your tongue is burned and cut slightly.");
        case TAUNT -> ItemInteractionResult.success("Your sword strikes fear in your enemies.");
        
        // Add more cases as needed for other actions:
        // TAKE, DROP, ACTIVATE, DEACTIVATE, OPEN, CLOSE, REPAIR, DESTROY,
        // CONSUME, LOCK, UNLOCK, PULL, TAUNT
        
        // Default behavior for unhandled actions
        default -> ItemInteractionResult.failure("You can't do that with the " + this.getItemDisplayName());
    };
}
}