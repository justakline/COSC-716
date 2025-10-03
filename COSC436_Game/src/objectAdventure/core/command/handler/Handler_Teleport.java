package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Map;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;

import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

/**
 * Handles teleport commands issued by the player, allowing them to
 * teleport directly to a room by its ID.
 *
 * <p>This handler processes teleport commands and delegates to the
 * GameController for the actual teleportation logic.</p>
 */
class Handler_Teleport extends AbstractCommandHandler {

    private final Service_Player playerService;
    private final Service_Room roomService;
    private final Service_Map mapService;


    /**
     * Constructs a new Handler_Teleport instance with the given GameController dependency.
     *
     * @param playerService The player service.
     * @param roomService   The room service.
     * @param mapService    The map service.
     */
    Handler_Teleport(Service_Player playerService, Service_Room roomService, Service_Map mapService) {
        super("T", "TELEPORT");
        this.playerService = playerService;
        this.roomService = roomService;
        this.mapService = mapService;
    }


    /**
     * Handles teleport commands by attempting to teleport the player to the specified room.
     *
     * @param playerCommand The teleport command issued by the player
     * @return A response string indicating the result of the teleportation attempt
     */
    @Override
    protected String handleAction(PlayerCommand playerCommand) {
        String noun = playerCommand.noun();

        try {
            var roomId = parseInt(noun);
            if (moveToNewRoom(roomId)) {
                return "Teleported to room: " + noun;
            } else {
                return "Teleportation to non-existent locations is not yet supported.";
            }
        } catch (NumberFormatException nfe) {
            Logger.getGlobal().warning("Invalid room ID: " + noun);
            return "You can only teleport to a room by its ID.";
        }
    }

    /**
     * Sets the player's new room number.
     *
     * @param newRoomId The destination room number for the player.
     * @return true if the change was successful, false otherwise.
     */
    boolean moveToNewRoom(final int newRoomId) {
        final int currentRoomId = playerService.getCurrentRoomId();

        // Validate the room movement using map service
        if (!mapService.doesRoomExist(newRoomId)) {
            return false;
        }

        // Update the player's current room using player service
        playerService.setCurrentRoomId(newRoomId);

        // Notify rooms of the transition using room service
        roomService.notifyRoomTransition(playerService.getPlayer(), currentRoomId, newRoomId);

        return true;
    }
}
