package objectAdventure.core.item;

import java.util.List;
import java.util.logging.Logger;

/**
 * The Item interface is the base interface for all items in the game.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
@SuppressWarnings("SameReturnValue")
public interface Item {

    /**
     * Get the list of aliases, normalized to uppercase.
     *
     * @param item The item
     * @return A list of aliases, normalized to uppercase.F
     */
    static List<String> getUpperCaseAliases(Item item) {
        return item.getItemAliases().stream().map(String::toUpperCase).toList();
    }

    /**
     * Returns the full description of the item for use when looking at the item.
     *
     * @return The full item description
     */
    String getItemFullDescription();

    /**
     * The short description of the item for display in lists, etc.
     *
     * @return A short item description.
     */
    String getItemDisplayName();

    /**
     * Is the item anchored to the room?
     * <p>
     * Note, this is mainly a convenience to allow for stationary items without
     * needing to override the {@link #itemInteractionHandler(ItemInteractionEvent)}
     * method simply to reject a GET action. A typical use case would be a for
     *
     * @return return true if the item can be moved.
     */
    default boolean isAnchored() {
        return false;
    }

    /**
     * Short Names lists for use in picking up and removing items.
     * If the item has no aliases, the class name is used.
     * ITEM ALIASES MUST NOT CONTAIN SPACES!
     *
     * @return A list containing the aliases (short names) for an item.
     */
    default List<String> getItemAliases() {
        return List.of(this.getClass().getSimpleName());
    }

    /**
     * Notify the game core the item has been interacted with.
     *
     * @param itemInteractionEvent The event that triggered the interaction.
     * @return The result of the interaction as an ItemInteractionResult object.
     */
    default ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
        Logger.getGlobal().fine("DEBUG: " + itemInteractionEvent.event());

        final String itemDisplayName = this.getItemDisplayName();

        return switch (itemInteractionEvent.event()) {
            case GET -> ItemInteractionResult.success("You picked up the %s".formatted(itemDisplayName));

            case DROP -> ItemInteractionResult.success("You dropped the %s".formatted(itemDisplayName));

            default -> ItemInteractionResult.failure("Undefined action on %s.".formatted(itemDisplayName));
        };
    }
}
