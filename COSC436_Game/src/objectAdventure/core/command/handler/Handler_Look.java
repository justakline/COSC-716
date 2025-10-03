package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;
import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionEventType;
import objectAdventure.core.room.RoomEventListener;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static objectAdventure.core.DescriptionType.SHORT;

/**
 * Handles "look" commands issued by the player, allowing them to observe their surroundings
 * or examine specific items in the game. This class interacts with the game state to provide
 * detailed descriptions based on the current game context.
 */
class Handler_Look extends AbstractCommandHandler {

    private final Service_Player playerService;
    private final Service_Room roomService;

    /**
     * Constructs a new Handler_Look instance with the given GameController dependency.
     *
     * @param playerService The player service.
     * @param roomService   The room service.
     */
    Handler_Look(Service_Player playerService, Service_Room roomService) {
        super("L", "LOOK");
        this.playerService = playerService;
        this.roomService = roomService;
    }


    /**
     * Processes a "handleLook" command issued by the player, which allows the player to
     * observe their surroundings or get a detailed description of a specified item.
     *
     * @param command The player command containing the "handleLook" action and the
     *                optional target noun.
     * @return A string describing either the current room and its contents (if no
     * noun is provided) * or the detailed description of the specified
     * item. If the specified item is not found, * a message indicating its
     * absence is returned.
     */
    @Override
    public String handleAction(PlayerCommand command) {
        // If no specific noun is given (i.e., user just typed "LOOK")
        // Handle the look command using the Handler_Look.
        if (!roomService.isRoomPresent(playerService.getCurrentRoomId()))
            return "You peer deeply into nothingness!\n(The room you are in does not exist!)";


        String noun = command.noun();
        assert noun != null : "The noun should not be null!  (Either empty string, valid item alias, or \"ROOM\"))";

        if (noun.isBlank() || noun.equalsIgnoreCase("ROOM")) {
            return lookAtRoom();
        } else {
            return lookAtItem(command, noun);
        }
    }

    /**
     * Retrieves the description of an item based on the player's command and the current game state.
     * This method looks for items either in the player's inventory or in the current room
     * that match the specified noun, and returns their detailed descriptions.
     * Notifies the items that they are being looked at before retrieving their descriptions.
     * If no matching item is found, a message indicating the item's absence is returned.
     *
     * @param command The player command specifying the action to look at an item,
     *                including the noun representing the item to be looked at.
     * @param noun    The name of the item to be looked at.
     * @return A string containing the detailed description(s) of the matching item(s), or
     * a message indicating that the specified item was not found.
     */
    private String lookAtItem(PlayerCommand command, String noun) {
        var playerItemList = playerService.getPlayer().getItemList();
        var roomItemList = roomService.getRoomFromID(playerService.getCurrentRoomId()).getItemList();
        var allItemsFromItemAlias = getAllItemsFromItemAlias(noun, playerItemList, roomItemList);

        // Notify the items that they are being looked at.
        allItemsFromItemAlias.forEach(item -> item.itemInteractionHandler(
                new ItemInteractionEvent(ItemInteractionEventType.LOOK, command, playerService.getPlayer())
        ));

        // Return the item descriptions or a message indicating that the item was not found.
        return allItemsFromItemAlias.stream()
                                    // Transform each item into its detailed description
                                    .map(Item::getItemFullDescription)

                                    // Joins all item descriptions, separated by newlines.
                                    .collect(Collectors.collectingAndThen(Collectors.joining("\n"),
                                                                          str -> str.isBlank()
                                                                                  // If no items match the noun.
                                                                                  ? format("You don't see %s here.",
                                                                                           noun)
                                                                                  // Otherwise, return the joined descriptions
                                                                                  : str
                                    ));
    }


    /**
     * Generates a description of the current room the player is in, including its
     * detailed description and a list of visible items in the room.
     * If the current room implements the RoomEventListener, it also notifies the
     * room that the player is observing it.
     *
     * @return A string containing the current room's description followed by
     * the list of items visible in the room.
     */
    private String lookAtRoom() {
        // Invoke the playerLookingAtRoom method on the current room
        if (roomService.getRoomFromID(playerService.getCurrentRoomId()) instanceof RoomEventListener listener) {
            listener.playerLookingAtRoom(playerService.getPlayer());
        }

        return "%s%n%nYou See:%n%s".formatted(
                // Gets the description of the current room the player is in
                roomService.getRoomFromID(playerService.getCurrentRoomId()).getRoomDescription(),
                // Gets the display names of all items in the room (in SHORT format)
                roomService.getRoomItemDisplayNames(playerService.getCurrentRoomId(), SHORT));
    }


    /**
     * Gets a list of items based on the alias.
     *
     * @param noun           The target object name.
     * @param itemCollection The list of items to search.
     * @return a list of item objects matching the alias.
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    private List<? extends Item> getAllItemsFromItemAlias(final String noun,
                                                          Collection<? extends Item>... itemCollection) {
        // Combine lists of room and player items.
        return Arrays
                .stream(itemCollection)
                .flatMap(Collection::stream)
                .filter(item -> Item.getUpperCaseAliases(item).contains(noun.toUpperCase()))
                .toList();
    }


}
