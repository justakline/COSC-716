package objectAdventure.core.command.service;

import objectAdventure.core.item.Item;
import objectAdventure.core.player.Player;

import java.util.List;

import static objectAdventure.core.DescriptionType.SHORT;

/**
 * Service class responsible for player-related operations.
 * This class handles player inventory management and player state operations.
 * <p>
 * Follows the Single Responsibility Principle by focusing solely on player operations.
 */
public class Service_Player {

    private final Player player;

    /**
     * Constructor for Service_Player.
     *
     * @param player The player instance to manage
     */
    public Service_Player(Player player) {
        this.player = player;
    }

    /**
     * Retrieves the active player object.
     *
     * @return the Player instance representing the currently active player.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets a comma-delimited list of player inventory items.
     *
     * @return a string containing the player's inventory items.
     */
    public String getFormattedInventoryItemString() {
        final List<Item> inventory = player.getItemList();

        if (inventory.isEmpty()) {
            return "You are empty-handed.";
        } else {
            return Service_Item.getFormattedItemList(inventory, SHORT);
        }
    }

    /**
     * Gets the current room ID for the player.
     *
     * @return the current room ID of the player
     */
    public int getCurrentRoomId() {
        return player.getCurrentRoomId();
    }

    /**
     * Sets the player's current room ID.
     *
     * @param roomId the new room ID for the player
     */
    public void setCurrentRoomId(int roomId) {
        player.setCurrentRoomId(roomId);
    }
}
