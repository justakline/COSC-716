package objectAdventure.core.command.service;

import objectAdventure.core.DescriptionType;
import objectAdventure.core.item.ItemContainer;
import objectAdventure.core.map.RoomList;
import objectAdventure.core.player.Player;
import objectAdventure.core.room.InputInterceptor;
import objectAdventure.core.room.NoSuchRoomException;
import objectAdventure.core.room.Room;
import objectAdventure.core.room.RoomEventListener;

import static objectAdventure.core.DescriptionType.LONG;

/**
 * Service class responsible for room-related operations.
 * This class handles room management, room information retrieval, and room transitions.
 * <p>
 * Follows the Single Responsibility Principle by focusing solely on room operations.
 */
public class Service_Room {

    private final RoomList rooms;

    /**
     * Constructor for Service_Room.
     *
     * @param rooms The room list to manage
     */
    public Service_Room(RoomList rooms) {
        this.rooms = rooms;
    }

    /**
     * Gets debugging information for a specific room.
     *
     * @param roomId the room ID to get info for
     * @return a string containing the room's ID and author.
     */
    public String getRoomInfo(int roomId) {
        try {
            final var theRoom = this.getRoomFromID(roomId);
            return "Room ID: %s, Room Author: %s".formatted(theRoom.getRoomId(), theRoom.getRoomAuthor());
        } catch (NoSuchRoomException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Returns a Room object for the given room ID.
     *
     * @param roomId The ID of the room to retrieve
     * @return the Room object corresponding to the specified ID.
     * @throws NoSuchRoomException if the room doesn't exist
     */
    public Room getRoomFromID(final int roomId) {
        final var room = rooms.getRoomFromID(roomId);
        return room.orElseThrow(() -> new NoSuchRoomException(roomId));
    }

    /**
     * Gets item descriptions for a specific room.
     *
     * @param roomId the room ID
     * @param type   The description type.
     * @return a string containing the item descriptions.
     */
    public String getRoomItemDisplayNames(int roomId, DescriptionType type) {
        try {
            Room room = this.getRoomFromID(roomId);
            return this.getRoomItemDisplayNames(room, type);
        } catch (NoSuchRoomException e) {
            return "No items in a non-existent room.";
        }
    }

    /**
     * Gets the item descriptions for a specified room.
     *
     * @param theRoom The room with the items.
     * @param type    The description type.
     * @return a string containing the item descriptions.
     */
    private String getRoomItemDisplayNames(final ItemContainer theRoom, DescriptionType type) {
        if (theRoom.getItemList().isEmpty()) {
            return "Nothing of Interest.";
        } else {
            return Service_Item.getFormattedItemList(theRoom.getItemList(), type);
        }
    }

    /**
     * Checks if a room with a given ID exists.
     *
     * @param roomID The ID of the Room.
     * @return true if the room exists, false otherwise.
     */
    public boolean isRoomPresent(int roomID) {
        return rooms.getRoomFromID(roomID).isPresent();
    }

    /**
     * Handles room event notifications when a player moves between rooms.
     *
     * @param player     the player making the move
     * @param fromRoomId the room the player is leaving
     * @param toRoomId   the room the player is entering
     */
    public void notifyRoomTransition(Player player, int fromRoomId, int toRoomId) {
        final var fromRoom = this.rooms.getRoomFromID(fromRoomId);
        final var toRoom = this.rooms.getRoomFromID(toRoomId);

        // Notify the rooms of the player's movement.
        fromRoom.ifPresent(room -> {
            if (room instanceof RoomEventListener listener) listener.playerLeavingRoom(player);
        });

        // Notify the rooms of the player's movement.
        toRoom.ifPresent(room -> {
            if (room instanceof RoomEventListener listener) listener.playerEnteringRoom(player);
        });
    }

    /**
     * Processes input through room-specific interceptors if available.
     *
     * @param inputLine     The raw input line.
     * @param currentRoomId The current room ID
     * @return The manipulated input line.
     */
    public String preProcessInput(String inputLine, int currentRoomId) {
        if (this.isRoomPresent(currentRoomId)) {
            Room currentRoom = this.getRoomFromID(currentRoomId);
            if (currentRoom instanceof InputInterceptor room) {
                return room.interceptInput(inputLine).trim();
            }
        }
        return inputLine;
    }

    /**
     * Debugging call to show the contents of all rooms. (Delegate Method)
     *
     * @return a formatted string listing the complete contents of all rooms.
     */
    public String DEBUG_getFormattedMapContents() {
        return this.rooms.DEBUG_GetAllMapContents();
    }

    /**
     * Helper for debugging - gets formatted room information.
     *
     * @param roomId                the room ID
     * @param playerInventoryString formatted player inventory string
     * @return Formatted room description, room contents, and Inventory
     */
    public String DEBUG_getFormattedRoomInfo(int roomId, String playerInventoryString) {
        Room currentRoom = getRoomFromID(roomId);
        return """
                Room Author: %s
                
                Room Description:
                %s
                
                Items in Room:
                %s
                
                Items in Inventory:
                %s""".formatted(currentRoom.getRoomAuthor(),
                                currentRoom.getRoomDescription(),
                                getRoomItemDisplayNames(roomId, LONG),
                                playerInventoryString);
    }
}
