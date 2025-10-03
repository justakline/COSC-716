package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;

/**
 * Handles display/show commands issued by the player, allowing them to
 * display various game elements like room images.
 *
 * <p>This handler processes display commands and delegates to the
 * GameController for the appropriate display actions.</p>
 */
class Handler_Display extends AbstractCommandHandler {

    private final Service_Room roomService;
    private final Service_Player playerService;


    /**
     * Constructs a new Handler_Display instance with the given GameController dependency.
     *
     * @param roomService   The room service.
     * @param playerService The player service.
     */
    Handler_Display(Service_Room roomService, Service_Player playerService) {
        super("DISPLAY", "SHOW");
        this.roomService = roomService;
        this.playerService = playerService;
    }


    /**
     * Handles display commands by showing the requested element.
     *
     * @param playerCommand The display command issued by the player
     * @return A response string or empty string if no text response is needed
     */
    @Override
    public String handleAction(PlayerCommand playerCommand) {
        String noun = playerCommand.noun();

        if ("ROOM".equalsIgnoreCase(noun)) {
            roomService.getRoomFromID(playerService.getCurrentRoomId()).displayRoomImage();
            return ""; // No text response needed for image display
        } else {
            return "I don't know how to DISPLAY %s.".formatted(noun);
        }
    }
}
