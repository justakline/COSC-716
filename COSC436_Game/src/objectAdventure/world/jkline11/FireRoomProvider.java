package objectAdventure.world.jkline11; // Replace 'student123' with your username

import objectAdventure.core.room.Room;
import objectAdventure.core.room.RoomProvider;

/**
 * Service provider implementation for YourRoomName.
 *
 * @author [Your Name], COSC436/COSC716
 */
public class FireRoomProvider implements RoomProvider {

    /**
     * Creates and returns your room instance.
     *
     * @return A configured room instance with your assigned ID and chosen name.
     */
    @Override
    public Room createRoom() {
        return new FireRoom(3, "Fire Room");
    }
}