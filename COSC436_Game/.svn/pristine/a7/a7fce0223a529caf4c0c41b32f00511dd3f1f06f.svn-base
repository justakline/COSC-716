package objectAdventure.core.room;

import objectAdventure.core.map.RoomList;

import java.util.ServiceLoader;

/**
 * Note that this REALLY doesn't belong in this package in terms of "good design" (it
 * should in the Room package or perhaps with the Core package). However, it's placed
 * here to make it easier to edit as everyone adds their Room Implementations
 * and makes them available to the shell.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class RoomInitializer {

    private RoomInitializer() {
        // Utility classes should not be instantiated.
    }

    /*
     * Initialization method for all rooms in the game.
     *
     * NOTE: As some want to constantly rearrange imports, use the "Fully Qualified
     * Name" (FQN) in this class to avoid merge conflicts.
     *
     * Usage:
     * To add a new room to the game, use the following pattern:
     *
     * rooms.addRoom(FullyQualifiedClassName.newInstance(roomId, "Room Name"));
     *
     * - FullyQualifiedClassName: Replace this with the fully qualified name of the room class.
     *   (e.g., objectAdventure.world.yourusername.YourRoomClass)
     *
     * - roomId: A unique integer ID assigned to the room.
     *   Ensure that the ID does not conflict with IDs already in use.
     *
     * - "Room Name": A short description or display name for the room.
     *   This will be shown to the player when they enter the room.
     *
     * Example:
     * rooms.addRoom(objectAdventure.world.aconover.StartRoom.newInstance(0, "The Lobby"));
     *
     * Guidelines:
     * 1. Always use the fully qualified class name for the room to avoid "import conflicts" when merging this file.
     * 2. Use a room ID that has been assigned to you to prevent duplicate IDs.
     * 3. Replace "Room Name" with a meaningful name to describe the room.
     * 4. Add your implementation below the existing examples as required.
     *
     * @param rooms The list of rooms in the game.
     */

    /**
     * Initialize the rooms in the game using Java Service Provider Interface.
     * This method automatically discovers and loads all RoomProvider implementations
     * using ServiceLoader, eliminating the need to manually add each room.
     * <p/>
     * Room providers are loaded in priority order (lower numbers = higher priority).
     * Students should create their own RoomProvider implementations and register
     * them in META-INF/services/objectAdventure.core.room.RoomProvider.
     *
     * @param rooms The list of rooms in the game.
     */
    public static void initRooms(RoomList rooms) {
        // Load all RoomProvider implementations using ServiceLoader
        ServiceLoader<RoomProvider> roomProviders = ServiceLoader.load(RoomProvider.class);

        // Iterate through the loaded providers and add each room to the game.
        roomProviders.forEach(provider -> rooms.addRoom(provider.createRoom()));

        /* **************************************************************************
         * MIGRATION NOTES FOR STUDENTS:
         * 
         * The old manual room registration has been replaced with Service Provider
         * Interface (SPI). To add your rooms:
         * 
         * 1. Create a RoomProvider implementation in your package:
         *    public class YourRoomProvider implements RoomProvider {
         *        @Override
         *        public Room createRoom() {
         *            return new YourRoom(yourRoomId, "Your Room Name");
         *        }
         *    }
         * 
         * 2. Register it in META-INF/services/objectAdventure.core.room.RoomProvider
         *    by adding a line with your fully qualified class name:
         *    your.package.name.YourRoomProvider
         * 
         * This eliminates merge conflicts and makes room registration automatic.
         * **************************************************************************
         */
    }
}
