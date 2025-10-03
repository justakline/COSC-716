package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Item;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;
import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;
import objectAdventure.core.player.Player;
import objectAdventure.core.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static objectAdventure.core.item.ItemInteractionEventType.GET;


/**
 * The Handler_Take class is responsible for managing item-taking actions
 * within the game. It allows players to pick up individual items or all available
 * items from the current room and handleNext interactions with those items if needed.
 *
 * <p>This class encapsulates the logic for "take" actions, keeping the GameController clean
 * and modular. It ensures proper validation and item transfer handling.</p>
 */
class Handler_Take extends AbstractCommandHandler {

    private final Service_Player playerService;
    private final Service_Room roomService;
    private final Service_Item itemService;


    /**
     * Constructs a new Handler_Take instance with the given GameController dependency.
     *
     * @param playerService The player service.
     * @param roomService   The room service.
     * @param itemService   The item service.
     */
    Handler_Take(Service_Player playerService, Service_Room roomService, Service_Item itemService) {
        super("GET", "TAKE");
        this.playerService = playerService;
        this.roomService = roomService;
        this.itemService = itemService;
    }


    /**
     * Handles a single-item "take" action based on the player's command. This method
     * validates the presence of the specified item in the current room and ensures
     * it's suitable for transfer to the player's inventory.
     *
     * @param playerCommand The user's input containing the name of the item to take.
     * @return A String representing the result of the take operation, with success or failure information.
     */
    @Override
    public String handleAction(PlayerCommand playerCommand) {
        if (!roomService.isRoomPresent(playerService.getCurrentRoomId())) {
            Logger.getGlobal().warning("Player is not in a room, can't take items.");
            return "Taking nonexistent item from nonexistent location mysteriously fails!";
        } else if (playerCommand.noun().isBlank())
            // If no noun is provided, return a message indicating the need for a specific item
            return "What do you want to take?";
        else if ("ALL".equalsIgnoreCase(playerCommand.noun()))
            // If the player wants to take all items, delegate to the takeAllItems method
            return takeAllItems(playerCommand);
        else
            // Attempt to take the specified item
            return takeItem(playerCommand);
    }


    /**
     * Attempts to take a single item based on the player's command record.
     * It checks if the item exists in the current room and processes the take action.
     *
     * @param playerCommand The command issued by the player containing the item alias.
     * @return A String result of the take operation, indicating success or failure messages.
     */
    private String takeItem(PlayerCommand playerCommand) {
        // Attempt to locate the item from the player's input alias in the current room
        var itemList = roomService.getRoomFromID(playerService.getCurrentRoomId()).getItemList();
        var itemFromAlias = itemService.getItemFromAlias(playerCommand.noun(), itemList);

        // Attempt to take the item if it exists, otherwise return a failure message
        return itemFromAlias
                .map(item -> takeItem(item, playerCommand)) // Item found, process the take action
                .orElse(format("I see no '%s' here!!!", playerCommand.noun()));
    }

    /**
     * Processes the logic for transferring a single item from the room to the player's inventory.
     * It handles item interactions, checks for conditions like immovability, and performs the transfer.
     *
     * @param item          The item to be taken.
     * @param playerCommand The command issued by the player.
     * @return A String result of the take operation, indicating success or failure messages.
     */
    private String takeItem(Item item, PlayerCommand playerCommand) {
        // Declare the response message variable for the take operation
        String responseMessage;

        // Get the item's display name for building messages
        String itemDisplayName = item.getItemDisplayName();

        if (item.isAnchored()) {
            // If item is immovable, inform the player.
            return format("Despite your valiant attempts, the %s is unmovable.", itemDisplayName);
        }

        // Check if the item is movable (not anchored)

        // Trigger an interaction event for the GET action
        Player player = playerService.getPlayer();
        Room currentRoom = roomService.getRoomFromID(playerService.getCurrentRoomId());

        ItemInteractionResult result = item.itemInteractionHandler(
                new ItemInteractionEvent(GET, playerCommand, player)
        );

        // If interaction is successful, attempt to transfer the item to the player's inventory
        if (result.bSuccess()) {
            boolean xfered = itemService.transferItem(currentRoom, player, item);

            // Determine the appropriate response based on transfer and interaction success
            responseMessage = xfered
                    ? result.message().isBlank()
                    // If the interaction provides no custom message, use default
                    ? format("You picked up the %s.", item.getItemDisplayName())
                    : result.message()
                    : "You can't take a non-transferable item!";
        } else {
            // Handle interaction failure for picking up the item
            responseMessage = result.message().isBlank()
                    ? format("You can't take the %s.", item.getItemDisplayName())
                    : result.message();
        }

        return responseMessage; // Return the final take result message
    }

    /**
     * Handles the "take all" action, attempting to take all items present in the current room.
     * Iterates through items in the room and performs the necessary logic for each.
     *
     * @param playerCommand The "take all" command issued by the player.
     * @return A String containing the result of the operation, detailing what was taken or failed.
     */
    String takeAllItems(PlayerCommand playerCommand) {
        // Get the list of items from the current room
        List<Item> itemList = roomService.getRoomFromID(playerService.getCurrentRoomId()).getItemList();

        // If no items are present in the room, return an appropriate message
        if (itemList.isEmpty()) {
            return "No items to take.";
        } else {

            // Create a stream from the item list for processing (duplicate to avoid concurrent modification).
            var itemStream = new ArrayList<>(itemList).stream();

            // Attempt to take each item and combine the results
            return itemStream
                    .map(item -> item.getItemAliases().isEmpty()
                            // Check if the item lacks aliases for identification
                            ? item.getClass().getCanonicalName() + " has no aliases."
                            // Provide fallback information
                            : this.takeItem(item, playerCommand))
                    .collect(Collectors.joining("\n")); // Combine all results into one message
        }
    }

}
