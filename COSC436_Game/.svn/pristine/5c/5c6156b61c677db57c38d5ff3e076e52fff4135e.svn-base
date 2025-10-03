package objectAdventure.core.map;

import objectAdventure.core.room.NoSuchRoomException;
import objectAdventure.core.room.Room;
import objectAdventure.core.room.RoomInitializer;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The RoomList class is a singleton class that contains all the rooms in the game.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class RoomList {

    private static final Logger LOGGER = Logger.getGlobal();
    private static final int SECRET_ROOM = 99;
    private static RoomList instance;
    private final Map<Integer, Room> roomMap = new TreeMap<>();

    /**
     * Prevent instantiation, this is a singleton class.
     */
    private RoomList() {
        // Force usage of factory method.
    }

    /**
     * Singleton method for construction of the RoomList Object before adding any rooms to it. (Uses
     * a double-checked locking pattern, though it would only be useful if this were a multithreaded
     * application.)
     *
     * @return a fully constructed room list object
     */
    public static synchronized RoomList newInstance() {
            if (instance == null) {
                // Create a new instance of the RoomList.
                var roomList = new RoomList();
                
                // Initialize the rooms in the game.
                RoomInitializer.initRooms(roomList);
                
                instance = roomList;
            }

            return instance;
    }

    /**
     * Get the room object from the ID.
     *
     * @param roomId the ID of the room to retrieve.
     * @return The room object from the ID.
     * @throws NoSuchRoomException thrown if the room does not exist.
     */
    public Optional<Room> getRoomFromID(int roomId) {
        if (this.exists(roomId)) {
            return Optional.of(roomMap.get(roomId));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Does the room exist in the map?
     *
     * @param roomId RoomID to check
     * @return true if exists, false otherwise.
     */
    private boolean exists(int roomId) {
        return roomMap.containsKey(roomId);
    }


    /**
     * Add a Room Object to the map.
     *
     * @param room The room object being added to the map.
     */
    public void addRoom(Room room) {
        Integer roomId = room.getRoomId();

        if (roomId == null) {
            final String msgTmpl = "Room '%s' has a null Room ID. Not adding to Room List.";
            LOGGER.log(Level.SEVERE, msgTmpl.formatted(room.getRoomName()));
            return;
        }

        // Check if the room already exists in the room list and if it is of a different class
        if (roomId != SECRET_ROOM && roomMap.containsKey(roomId)) {
            final String msgTmpl = "Room '%d' already exists in the RoomList. Not adding '%s' (Class: %s) to Room List.";
            LOGGER.log(Level.SEVERE, msgTmpl.formatted(roomId, room.getRoomName(), room.getClass().getSimpleName()));
        } else {
            // Add the room to the room list
            roomMap.put(roomId, room);

            // Log the addition of the room.
            LOGGER.log(Level.CONFIG, "Added room {0}: {1}", new Object[]{roomId, room});
        }
    }


    /**
     * Show the contents of all rooms in the game.
     *
     * @return formatted string listing the complete contents of the room.
     */
    public String DEBUG_GetAllMapContents() {
        StringBuilder sb = new StringBuilder();

        // Append the game contents header
        sb.append("Game Contents:\n");

        // Iterate through each room in the room list and build room and item details
        for (var room : roomMap.values()) {
            String className = room.getClass().getSimpleName();
            sb.append("\tRoom %02d (%s): %s\n"
                              .formatted(room.getRoomId(), room.getRoomAuthor(), className));

            // Iterate through each item in the room
            for (var item : room.getItemList()) {
                // Append item details
                sb.append("\t\tItem: %s (Aliases: %s)%n"
                                  .formatted(item.getClass().getSimpleName(),
                                             item.getItemAliases()));
            }
        }

        return sb.toString();
    }
}
