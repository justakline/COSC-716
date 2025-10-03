package objectAdventure.world.aconover;

import objectAdventure.core.room.Room;
import objectAdventure.core.room.RoomProvider;

/**
 * Service provider implementation for the StartRoom.
 * This class implements the RoomProvider interface to allow
 * automatic discovery and loading of the StartRoom using
 * Java's ServiceLoader mechanism.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class StartRoomProvider implements RoomProvider {

    /**
     * Creates and returns a StartRoom instance.
     * The StartRoom serves as the initial room where players begin the game.
     *
     * @return A configured StartRoom instance with ID 0 and name "The Lobby".
     */
    @Override
    public Room createRoom() {
        return new StartRoom(0, "The Lobby");
    }
}
