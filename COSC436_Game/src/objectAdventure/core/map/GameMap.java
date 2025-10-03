package objectAdventure.core.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.List.of;
import static objectAdventure.core.map.Direction.*;

/**
 * The GameMap contains a list of rooms, and the connections between them.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class GameMap {

    // The order that the connections are specified in the adjacency lists.
    private static final Direction[] CONNECTION_ORDER = {NORTH, EAST, SOUTH, WEST, UP, DOWN};

    // The map containing the room adjacency information.
    private final Map<Integer, List<Integer>> map = new HashMap<>();

    /**
     * A Map&lt;Integer, List&lt;Integer&gt;&gt; containing the room connections.
     * <p>
     * Construct an adjacency list of all rooms in the map. Direction Order: (N, E, S, W, U, D)
     */
    @SuppressWarnings("MagicNumber")
    public GameMap() {
        // Note: This uses a map of lists to represent an adjacency list graph representation. It
        // could have done in an array of arrays, but lists are more flexible, as rooms can be added
        // with any ID. Though that is not yet implemented anywhere in the shell, using a map allows
        // rooms to be added, or connections modified, dynamically at runtime.

        // Rooms 0-99 are for the map
        map.put(0, of(9, 16, 22, -1, -1, 99));
        map.put(1, of(-1, 2, -1, -1, -1, -1));
        map.put(2, of(-1, 3, 7, 1, -1, -1));
        map.put(3, of(-1, -1, 9, 2, -1, -1));
        map.put(4, of(-1, 5, -1, -1, -1, -1));
        map.put(5, of(-1, -1, 12, 4, -1, -1));
        map.put(6, of(-1, 7, -1, -1, -1, -1));
        map.put(7, of(2, -1, -1, 6, -1, -1));
        map.put(8, of(-1, 9, 15, -1, -1, -1));
        map.put(9, of(3, 10, 0, 8, -1, -1));
        map.put(10, of(-1, 11, -1, 9, -1, -1));
        map.put(11, of(-1, 12, 17, 10, -1, -1));
        map.put(12, of(5, -1, 18, 11, -1, -1));
        map.put(13, of(-1, 14, -1, -1, -1, -1));
        map.put(14, of(-1, 15, 20, 13, -1, -1));
        map.put(15, of(8, -1, -1, 14, -1, -1));
        map.put(16, of(-1, 17, 23, 0, -1, -1));
        map.put(17, of(11, -1, 24, 16, -1, -1));
        map.put(18, of(12, -1, 25, -1, -1, -1));
        map.put(19, of(-1, 20, 26, -1, -1, -1));
        map.put(20, of(14, 21, -1, 19, -1, -1));
        map.put(21, of(-1, 22, 28, 20, -1, -1));
        map.put(22, of(0, -1, 29, 21, -1, -1));
        map.put(23, of(16, -1, -1, -1, -1, -1));
        map.put(24, of(17, 25, 30, -1, -1, -1));
        map.put(25, of(18, -1, -1, 24, -1, -1));
        map.put(26, of(19, -1, -1, -1, -1, -1));
        map.put(27, of(-1, 28, -1, -1, -1, -1));
        map.put(28, of(21, -1, -1, 27, -1, -1));
        map.put(29, of(22, 30, -1, -1, -1, -1));
        map.put(30, of(24, -1, -1, 29, -1, -1));
        map.put(99, of(-1, -1, -1, -1, 0, -1));

        // Rooms 100-199 are rooms that only be "teleported" to/from.
        List<Integer> defaultExits = List.of(-1, -1, -1, -1, -1, -1);
        java.util.stream.IntStream.range(100, 199).forEach(roomID -> map.put(roomID, defaultExits));
    }


    /**
     * Mostly for debugging, but check to see if a room with a given ID exists in on the map.
     *
     * @param roomId The room ID to query.
     * @return {@code true} if the room (roomId) exists, {@code false}  otherwise.
     */
    public boolean doesExist(int roomId) {
        return map.containsKey(roomId) && !map.get(roomId).isEmpty();
    }

    /**
     * Get a list of all the exit connections from a given room.
     * <p>
     * NOTE: This was done "dynamically" (direction set recalculated with every move) to allow for
     * less complexity in altering the map dynamically. For example, finding secret passages, etc.
     * <p>
     * However, the room connection/direction mappings could easily be built during the construction
     * of this class. In the absence of adding another "Wrapper Class", the signature of the main
     * TreeMap would then be…
     * <p>
     * Map&lt;Integer, Map&lt;Direction, Integer&gt;&lt; map…
     *
     * @param roomId the roomID being queried for connections.
     * @return A map with the direction as the key, and the connected room as the value.
     */
    public Map<Direction, Integer> getExitConnections(Integer roomId) {
        var roomList = this.map.get(roomId);

        // Room Connections
        var connections = new HashMap<Direction, Integer>();

        // Build a list of connections by iterating over the list of room exits.
        java.util.stream.IntStream.range(0, values().length)
                                  .filter(i -> roomList.get(i) >= 0)
                                  .forEach(i -> connections.put(CONNECTION_ORDER[i], roomList.get(i)));

        return connections;
    }

}
