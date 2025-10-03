package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Map;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;
import objectAdventure.core.map.Direction;

import static java.lang.System.out;

/**
 * Handles movement commands issued by the player, allowing them to move
 * between rooms in different directions.
 *
 * <p>This handler processes directional commands and delegates the actual
 * movement logic to the GameController.</p>
 */
class Handler_Move extends AbstractCommandHandler {

    private final Service_Player playerService;
    private final Service_Map mapService;
    private final Service_Room roomService;


    /**
     * Constructs a new Handler_Move instance with the given GameController dependency.
     *
     * @param playerService The player service.
     * @param mapService    The map service.
     * @param roomService   The room service.
     */
    Handler_Move(Service_Player playerService, Service_Map mapService, Service_Room roomService) {
        super(Direction.getDirectionStrings());
        super.addHandledVerb("GO");

        this.playerService = playerService;
        this.mapService = mapService;
        this.roomService = roomService;
    }


    /**
     * Handles movement commands by moving the player in the specified direction.
     *
     * @param playerCommand The movement command issued by the player
     * @return An empty string since movement commands don't produce text responses
     */
    @Override
    String handleAction(PlayerCommand playerCommand) {
        final boolean withVerb = playerCommand.verb().equalsIgnoreCase("GO");

        if (withVerb && playerCommand.noun().isBlank()) {
            return "Go where?";
        }

        String direction = withVerb ? playerCommand.noun() : playerCommand.verb();
        Direction.directionFromLexeme(direction).ifPresent(this::movePlayer);

        // Movement commands don't produce text responses, they just change the player's location
        return "";
    }

    /**
     * Moves the player in the specified direction.
     *
     * @param dir The direction to move the player.
     */
    private void movePlayer(final Direction dir) {
        final var currentRoomId = playerService.getCurrentRoomId();
        final var exits = mapService.getExitConnections(currentRoomId);

        assert (mapService.doesRoomExist(currentRoomId)) : "The current Room does not exist! How did you get here!?";

        // Check if the exit exists, and if so, teleport the player.
        if (exits.containsKey(dir)) {
            moveToNewRoom(exits.get(dir));
        } else {
            out.printf("Ouch! (There is no exit: %s)%n", dir.toString());
        }
    }

    /**
     * Sets the player's new room number.
     *
     * @param newRoomId The destination room number for the player.
     */
    public void moveToNewRoom(final int newRoomId) {
        final int currentRoomId = playerService.getCurrentRoomId();

        // Validate the room movement using map service
        if (!mapService.doesRoomExist(newRoomId)) {
            return;
        }

        // Update the player's current room using player service
        playerService.setCurrentRoomId(newRoomId);

        // Notify rooms of the transition using room service
        roomService.notifyRoomTransition(playerService.getPlayer(), currentRoomId, newRoomId);
    }
}
