package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;

/**
 * Handles debug commands issued by the player, allowing them to
 * access debugging information about the game state.
 *
 * <p>This handler processes debug commands and delegates to the
 * GameController for the appropriate debug information.</p>
 */
class Handler_Debug extends AbstractCommandHandler {

    private final Service_Room roomService;
    private final Service_Player playerService;

    /**
     * Constructs a new Handler_Debug instance with the given GameController dependency.
     *
     * @param roomService   The room service.
     * @param playerService The player service.
     */
    Handler_Debug(Service_Room roomService, Service_Player playerService) {
        super("DEBUG");
        this.roomService = roomService;
        this.playerService = playerService;
    }

    /**
     * Handles debug commands by returning the requested debug information.
     *
     * @param playerCommand The debug command issued by the player
     * @return A response string containing the requested debug information
     */
    @Override
    protected String handleAction(PlayerCommand playerCommand) {
        String noun = playerCommand.noun();

        return switch (noun) {
            case "ROOM" -> roomService.DEBUG_getFormattedRoomInfo(playerService.getCurrentRoomId(), playerService.getFormattedInventoryItemString());
            case "MAP" -> roomService.DEBUG_getFormattedMapContents();
            default -> "I don't know how to DEBUG %s.".formatted(noun);
        };
    }
}
