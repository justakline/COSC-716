package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Item;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;
import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEventType;
import objectAdventure.core.player.Player;
import objectAdventure.core.room.Room;

import java.util.List;

/**
 * Handles item interaction commands issued by the player. This handler serves
 * as the default handler in the chain, processing any commands that represent
 * interactions with items in the game.
 *
 * <p>This handler processes item interaction commands by looking up the verb
 * in the ItemInteractionEventType enum and delegating to the GameController
 * for the actual interaction logic.</p>
 */
class Handler_ItemInteraction extends AbstractCommandHandler {

    private final Service_Player playerService;
    private final Service_Room roomService;
    private final Service_Item itemService;

    /**
     * Constructs a new Handler_ItemInteraction instance with the given GameController dependency.
     *
     * @param playerService The player service.
     * @param roomService   The room service.
     * @param itemService   The item service.
     */
    Handler_ItemInteraction(Service_Player playerService, Service_Room roomService, Service_Item itemService) {
        this.playerService = playerService;
        this.roomService = roomService;
        this.itemService = itemService;
    }

    /**
     * Determines if this handler can process the given player command.
     * This implementation always returns true, as this handler is intended
     * to be the default handler for any non-core interaction command.
     *
     * @param playerCommand The command issued by the player
     * @return true, indicating this handler can process any command
     */
    @Override
    protected boolean canHandle(PlayerCommand playerCommand) {
        return !playerCommand.noun().isBlank(); // Need a noun to interact with an item
    }

    /**
     * Handles item interaction commands by delegating to the GameController.
     * This method should only be called if canHandle returns true, ensuring
     * that the verb is a valid item interaction.
     *
     * @param playerCommand The item interaction command issued by the player
     * @return A response string indicating the result of the item interaction
     */
    @Override
    protected String handleAction(PlayerCommand playerCommand) {
        // Since canHandle already verified this is a valid interaction, this should not fail
        final ItemInteractionEventType eventType = ItemInteractionEventType.actionFromLexeme(playerCommand.verb());

        // Note: If a verb unknown to the game is given, this will return UNKNOWN.

        return interactWithItem(eventType, playerCommand);
    }

    /**
     * Interact with an item based on the player's command and the event type.
     * Searches for the item in the player's inventory and the current room's item list.
     * If the item is found, an interaction is performed; otherwise, returns a default message.
     *
     * @param event         The type of event triggering the interaction (e.g., use, examine).
     * @param playerCommand The command issued by the player, containing the target item's alias.
     * @return The result of the interaction as a string, or a message indicating that the item is not found.
     */
    public String interactWithItem(ItemInteractionEventType event, PlayerCommand playerCommand) {
        final Player player = playerService.getPlayer();
        final Room currentRoom = roomService.getRoomFromID(playerService.getCurrentRoomId());

        final List<Item> playerItemList = player.getItemList();
        final List<Item> roomItems = currentRoom.getItemList();

        // Get the item from the alias, searching both the player's inventory and the current room's items.
        var item = itemService.getItemFromAlias(playerCommand.noun(), playerItemList, roomItems);

        return item.map(target -> itemService.interactWithItem(player, playerCommand, event, target))
                   .orElse("I don't see any %s here.".formatted(playerCommand.noun()));
    }
}
