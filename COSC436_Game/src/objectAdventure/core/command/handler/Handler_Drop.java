package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Item;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;
import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static objectAdventure.core.item.ItemInteractionEventType.DROP;

/**
 * The Handler_Drop class is responsible for managing all item-dropping actions
 * within the game. It processes player commands related to dropping items
 * (both single items and "drop all" commands), validates the actions, and
 * communicates with the GameController to handleNext the transfer of items.
 *
 * <p>This class helps decouple the drop-related functionality from the GameController,
 * making the design more modular and allowing for easier maintenance and testing.</p>
 */
class Handler_Drop extends AbstractCommandHandler {

    private final Service_Player playerService;
    private final Service_Room roomService;
    private final Service_Item itemService;

    /**
     * Constructs a new Handler_Drop instance with the given GameController dependency.
     *
     * @param playerService The player service.
     * @param roomService   The room service.
     * @param itemService   The item service.
     */
    Handler_Drop(Service_Player playerService, Service_Room roomService, Service_Item itemService) {
        super("DROP");
        this.playerService = playerService;
        this.roomService = roomService;
        this.itemService = itemService;
    }


    /**
     * Handles a single-item drop action based on the player's command.
     * This method checks whether the specified item exists in the player's inventory,
     * and if found, attempts to drop it in the current room.
     *
     * @param playerCommand The player's input containing the item to drop.
     * @return A string result of the drop action, either success or failure messages.
     */
    public String handleAction(PlayerCommand playerCommand) {
        if (!roomService.isRoomPresent(playerService.getCurrentRoomId())) {
            Logger.getGlobal().warning("Player is not in a room, can't drop items.");
            return "You can't drop items here!";
        }

        if (playerCommand.noun().isBlank())
            // If no noun is provided, return a message indicating the need for a specific item
            return "What do you want to drop?";
        else if ("ALL".equalsIgnoreCase(playerCommand.noun()))
            // If the player wants to take all items, delegate to the takeAllItems method
            return dropAllItems(playerCommand);
        else
            // Attempt to take the specified item
            return dropItem(playerCommand);
    }


    /**
     * Attempts to drop a single item based on the player's command record.
     * It checks if the item exists in the player's inventory and processes the drop action.
     *
     * @param playerCommand The user's input containing the name of the item to drop.
     * @return A String representing the result of the drop operation, with success or failure information.
     */
    private String dropItem(PlayerCommand playerCommand) {
        // Fetch the item to drop using its alias from the player's inventory
        Collection<Item> itemList = playerService.getPlayer().getItemList();
        String noun = playerCommand.noun();

        // Get the item from the player's inventory based on the noun provided
        final var itemFromAlias = itemService.getItemFromAlias(noun, itemList);

        // Attempt to drop the item or return an appropriate failure message
        return itemFromAlias
                .map(item -> dropItem(item, playerCommand)) // If the item is found, proceed to drop it
                .orElse(format("You don't have a '%s' to drop.", noun));
    }


    /**
     * Handles the actual drop action for an individual item, ensuring any necessary
     * interactions or transfer processes are executed correctly.
     *
     * @param item          The item to drop from the player's inventory.
     * @param playerCommand The command issued by the player that initiated the drop.
     * @return A string result of the drop operation, including success or failure messages.
     */
    private String dropItem(Item item, PlayerCommand playerCommand) {
        // Get the item's display name for use in messages
        String itemDisplayName = item.getItemDisplayName();

        // Trigger the interaction event associated with "DROP"
        ItemInteractionResult result = item.itemInteractionHandler(
                new ItemInteractionEvent(DROP, playerCommand, playerService.getPlayer())
        );

        String responseMessage;

        // If the interaction was successful, proceed to transfer the item to the current room
        if (result.bSuccess()) {
            // Attempt to move the item from the player's inventory to the room
            boolean xfered = itemService.transferItem(
                    playerService.getPlayer(), // Source: Player's inventory
                    roomService.getRoomFromID(playerService.getCurrentRoomId()), // Destination: Current room
                    item // The item being transferred
            );

            // Construct appropriate success or failure messages
            // (While many people will from on the use of nested ternary expressions, they are perfectly intelligible
            //  with proper line breaks and indentation.)
            responseMessage = xfered
                    ? result.message().isBlank() // Use the interaction's custom message if provided
                    ? format("You dropped the %s.", itemDisplayName)
                    : result.message()
                    : "You couldn't drop the item!"; // Transfer failed
        } else {
            // Construct a failure message if the interaction itself was unsuccessful
            responseMessage = result.message().isBlank()
                    ? format("You can't drop the %s.", itemDisplayName)
                    : result.message();
        }

        return responseMessage; // Return the final message to the player
    }

    /**
     * Handles the "drop all" action, which involves dropping all items currently held
     * by the player into the current room.
     *
     * @param playerCommand The command issued by the player to drop all items.
     * @return A string result of the drop-all operation, including messages for each item dropped.
     */
    public String dropAllItems(PlayerCommand playerCommand) {
        // Get a copy of the player's inventory as a list (to avoid concurrent modification)
        List<Item> itemList = new ArrayList<>(playerService.getPlayer().getItemList());

        // If the inventory is empty, return an appropriate message
        if (itemList.isEmpty()) {
            return "You're not holding anything to drop.";
        } else {
            // Attempt to drop each item one by one and collect drop action messages
            return itemList.stream()
                           .map(item -> dropItem(item, playerCommand)) // Process each item drop
                           .collect(Collectors.joining("\n")); // Combine all messages into a single response
        }
    }
}
