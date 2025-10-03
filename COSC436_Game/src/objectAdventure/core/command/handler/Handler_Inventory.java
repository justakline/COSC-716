package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Player;

/**
 * Handles inventory commands issued by the player, allowing them to view
 * their current inventory contents.
 *
 * <p>This handler processes inventory display commands and delegates to
 * the GameController to get the formatted inventory string.</p>
 */
class Handler_Inventory extends AbstractCommandHandler {

    private final Service_Player playerService;

    /**
     * Constructs a new Handler_Inventory instance with the given GameController dependency.
     *
     * @param playerService The Player service.
     */
    Handler_Inventory(Service_Player playerService) {
        super("I", "INVENTORY");
        this.playerService = playerService;
    }


    /**
     * Handles inventory commands by returning the player's current inventory.
     *
     * @param playerCommand The inventory command issued by the player
     * @return A formatted string showing the player's inventory contents
     */
    @Override
    protected String handleAction(PlayerCommand playerCommand) {
        return "Inventory:%n%s".formatted(playerService.getFormattedInventoryItemString());
    }
}
