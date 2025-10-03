package objectAdventure.core.item;

import java.util.List;

import static java.lang.System.out;

/**
 * Interface for any object that can possess items.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public interface ItemContainer {

    /**
     * Add a single item to the thing, which can possess items.
     *
     * @param item The item to add to the room.
     */
    void addItem(Item item);

    /**
     * Remove a single item from objects, which can possess items.
     *
     * @param item The item to add to the room.
     * @return Optional.of item if the item was successfully removed from the
     * room, Optional.empty() otherwise.
     */
    boolean removeItem(Item item);

    /**
     * Get a list of all items in the room.
     *
     * @return A list of any items found in the room
     */
    List<Item> getItemList();

    /**
     * Displays all items within the container and their associated aliases.
     * This method is intended for debugging purposes, as indicated by the "DEBUG_" prefix.
     * It iterates through all items in the container and prints each item's aliases.
     * <p>
     * This method does not take any parameters as it uses the container's own item list.
     * <p>
     * The output is printed to the standard output stream (System.out).
     * Each line of output shows an alias and the item it represents.
     * <p>
     * Note: This method assumes that the container has a getItemList() method
     * and that each Item has a getItemAliases() method.
     */
    default void DEBUG_showAllItemAliases() {
        for (var item : this.getItemList()) {
            for (var alias : item.getItemAliases()) {
                out.printf("\"%s\" is an alias for object: %s%n", alias, item);
            }
        }
    }

    /**
     * Check for the existence of an item.
     *
     * @param item The item to check for.
     * @return true, if the item is in the room, false otherwise.
     */
    default boolean hasItem(Item item) {
        return this.getItemList().contains(item);
    }
}
