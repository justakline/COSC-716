package objectAdventure.core.player;

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemContainer;

import java.util.LinkedList;
import java.util.List;

/**
 * The Player class is the base class for all players in the game. Players may
 * have a name and a list of items.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class Player implements ItemContainer {

    // The Name of the player
    private final String playerName;

    // The List of Items the player is carrying.
    private final List<Item> inventory;

    // The ID of the room the player is in.
    private int currentRoomID;
    private int previousRoomID;

    /**
     * Constructor for default player.
     *
     * @param playerName the name of the player
     */
    public Player(String playerName) {
        this.playerName = playerName;
        this.inventory = new LinkedList<>();
        this.currentRoomID = 0;
        this.previousRoomID = 0;
    }


    /**
     * Retrieves the ID of the room the player was in prior to the current room.
     *
     * @return the ID of the previous room
     */
    public int getPreviousRoomID() {
        return previousRoomID;
    }


    /**
     * Retrieves the ID of the room that the player is currently in.
     *
     * @return the ID of the current room the player is located in.
     */
    public int getCurrentRoomId() {
        return currentRoomID;
    }

    /**
     * Set the current room ID of the player.
     *
     * @param newRoomID the currentRoomID to set
     */
    public void setCurrentRoomId(int newRoomID) {
        this.previousRoomID = this.currentRoomID;
        this.currentRoomID = newRoomID;
    }

    /**
     * Add an item to the player's inventory.
     *
     * @param item item to add to inventory
     */
    @Override
    public void addItem(Item item) {
        inventory.add(item);
    }

    /**
     * Remove an item from the player's inventory.
     *
     * @param item item to remove from inventory.
     */
    @Override
    public boolean removeItem(Item item) {
        if (!item.isAnchored()) {
            return inventory.remove(item);
        }
        return false;
    }

    /**
     * Get the list of items the player is carrying.
     *
     * @return the list of items.
     */
    @Override
    public List<Item> getItemList() {
        return this.inventory;
    }


    /**
     * Get the player's name.
     *
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "Player{" + "currentRoom=" + currentRoomID + "}";
    }

}
