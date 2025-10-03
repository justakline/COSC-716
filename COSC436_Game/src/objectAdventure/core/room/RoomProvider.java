package objectAdventure.core.room;

/**
 * Service provider interface for room initialization.
 * Implementations of this interface will be automatically discovered
 * and loaded using Java's ServiceLoader mechanism, eliminating the need
 * to manually add each room to the RoomInitializer.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public interface RoomProvider {

    /**
     * Creates and returns a Room instance.
     * Each implementation should create their specific room with
     * the appropriate room ID and name.
     *
     * @return A configured Room instance ready to be added to the game.
     */
    Room createRoom();
}
