package objectAdventure.core.command.service;

import objectAdventure.core.map.Direction;
import objectAdventure.core.map.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * Service class responsible for map and navigation-related operations.
 * This class handles map operations, exit directions, and room movement validation.<p>
 *
 * Follows the Single Responsibility Principle by focusing solely on map operations.
 */
public class Service_Map {

    static final Logger LOGGER = Logger.getGlobal();

    private final GameMap gameMap;

    /**
     * Constructor for Service_Map.
     *
     * @param gameMap The game map to manage
     */
    public Service_Map(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    /**
     * Gets the GameMap object.
     *
     * @return the GameMap instance associated with this service.
     */
    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * Gets a list of exit directions for a specific room.
     *
     * @param roomId the room ID to get exit directions for
     * @return a list of Direction instances.
     */
    public List<Direction> getExitDirections(int roomId) {
        final var directionSet = this.gameMap.getExitConnections(roomId).keySet();
        return new ArrayList<>(directionSet);
    }

    /**
     * Validates if a room exists in the game map.
     *
     * @param roomId The room ID to check for existence.
     * @return true if the room exists, false otherwise.
     */
    public boolean doesRoomExist(int roomId) {
        // If the room does not exist, do not allow the movement.
        if (!gameMap.doesExist(roomId)) {
            final String msg = 
                    format("The target Room (%d) does not exist.", roomId);
            
            LOGGER.warning(msg);
            return false;
        }

        return true;
    }

    /**
     * Gets the exit connections for a specific room.
     *
     * @param roomId The room ID to get exit connections for.
     * @return A map of Direction instances to room IDs.
     */
    public Map<Direction, Integer> getExitConnections(int roomId) {
        return gameMap.getExitConnections(roomId);
    }

}
