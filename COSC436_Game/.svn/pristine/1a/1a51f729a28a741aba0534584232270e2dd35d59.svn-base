package objectAdventure.core.room;

import objectAdventure.common.Utils;
import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemContainer;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * The Room class is the base class for all rooms in the game.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public abstract class Room implements ItemContainer {

    private final Integer roomId;
    private final String roomName;
    private final List<Item> itemList;

    private String roomAuthor = "The author wishes to remain anonymous.";
    private String roomDescription = "This room does not yet have a description";
    private String roomImageResourcePath = null;

    /**
     * Constructor for the abstract room.
     *
     * @param roomId   The ID of the room.
     * @param roomName The name of the room.
     */
    protected Room(int roomId, String roomName) {
        this.roomId = roomId;
        this.roomName = roomName == null ? "Unnamed Room" : roomName;
        this.itemList = new LinkedList<>();
    }

    /**
     * Get the image associated with a room.
     */
    public void displayRoomImage() {
        if (this.roomImageResourcePath != null) {
            Utils.displayImagePopup(this.roomName, this.roomImageResourcePath);
        } else {
            System.err.println("No image available for this room.");
        }
    }

    /**
     * Get the description of a room.
     *
     * @return the Room Description
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Set the description of a room.
     *
     * @param description the description of the room.
     */
    protected void setRoomDescription(final String description) {
        this.roomDescription = description;
    }

    /**
     * Get the ID of the room.
     *
     * @return The RoomID
     */
    public Integer getRoomId() {
        return this.roomId;
    }

    /**
     * Get the Name of the room.
     *
     * @return The Short Name of the Room
     */
    public String getRoomName() {
        return this.roomName;
    }


    /**
     * Adds an item to the room.
     *
     * @param item The item to add to the room.
     */
    @Override
    public void addItem(final Item item) {
        this.itemList.add(item);
    }

    /**
     * Removes an item from the room.
     *
     * @return The items in the room.
     */
    @Override
    public boolean removeItem(final Item item) {
        if (!item.isAnchored()) {
            return this.itemList.remove(item);
        }

        return false;
    }

    /**
     * Returns a copy of the item List.
     *
     * @return The items in the room.
     */
    @Override
    public List<Item> getItemList() {
        return unmodifiableList(itemList);
    }

    /**
     * Gets the room author information.
     *
     * @return The Author of the room
     */
    public String getRoomAuthor() {
        return this.roomAuthor;
    }

    /**
     * Set the room author's name.
     *
     * @param roomAuthor The room author's name
     */
    protected void setRoomAuthor(String roomAuthor) {
        this.roomAuthor = roomAuthor;
    }

    /**
     * Set the image resource path for the room.
     *
     * @param imageResourcePath The path to the image resource.
     */
    protected void setRoomImageResourcePath(String imageResourcePath) {
        this.roomImageResourcePath = imageResourcePath;
    }
}
