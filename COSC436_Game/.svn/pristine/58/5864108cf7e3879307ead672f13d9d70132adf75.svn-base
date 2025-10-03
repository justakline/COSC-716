package objectAdventure.core.command.service;

import objectAdventure.common.Utils;
import objectAdventure.core.DescriptionType;
import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.item.*;
import objectAdventure.core.player.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for item-related operations.
 * This class handles item transfers, item interactions, and item searches.
 * <p>
 * Follows the Single Responsibility Principle by focusing solely on item operations.
 */
public class Service_Item {

    /**
     * Constructor for Service_Item.
     * No dependencies to inject.
     */
    public Service_Item() {
    }

    /**
     * Get a list of items in a list display format.
     *
     * @param itemCollection  The collection of items
     * @param descriptionType Either "DescriptionType.SHORT" or "DescriptionType.LONG"
     * @return The list of items in a string format
     */
    public static String getFormattedItemList(Collection<? extends Item> itemCollection,
                                              DescriptionType descriptionType) {
        return itemCollection
                .stream()
                .map(switch (descriptionType) {
                    case SHORT -> Item::getItemDisplayName;
                    case LONG -> Item::getItemFullDescription;
                })
                .map(Utils::capitalize)
                .map("*  "::concat)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Transfers an item from one container to another.
     *
     * @param from The Item possessor to take the item from.
     * @param to   The Item possessor to give the item to.
     * @param item The item to be transferred.
     * @return true if the transfer was successful, false otherwise
     */
    public boolean transferItem(final ItemContainer from, final ItemContainer to, final Item item) {
        if (!item.isAnchored()) {
            from.removeItem(item);
            to.addItem(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Handles the interaction of a player with a specified item based on the provided action and command.
     * This method attempts to execute the interaction with the item and returns the result message.
     *
     * @param player        The player who is performing the interaction.
     * @param playerCommand The command issued by the player, containing details of the action and target.
     * @param action        The type of interaction the player wants to perform with the item.
     * @param item          The item on which the interaction is performed.
     * @return A string containing the result of the interaction. If the interaction is successful, the custom
     *         interaction result message is returned. If the interaction fails or the result message is blank,
     *         a default message is returned.
     */
    public String interactWithItem(Player player, PlayerCommand playerCommand,
                                   ItemInteractionEventType action,
                                   Item item) {

        // Interact with the item using the specified action.
        ItemInteractionEvent itemInteractionEvent = new ItemInteractionEvent(action, playerCommand, player);
        ItemInteractionResult interactionResult = item.itemInteractionHandler(itemInteractionEvent);

        // Return the interactionResult message if the interaction was successful.
        if (interactionResult.bSuccess()) {
            return interactionResult.message();
        } else {
            // Return a default message if the interactionResult message is blank.
            return interactionResult.message().isBlank()
                    // default message if no custom message is provided.
                    ? "You can't %s the %s.".formatted(action.getAliases(), playerCommand)
                    : interactionResult.message();
        }
    }

    /**
     * Get an item object based on the alias. If there are multiple matches, the
     * first one found will be returned.
     *
     * @param lexeme   The target object name.
     * @param itemList The list of items to search.
     * @return An Optional containing the item object if found, otherwise an empty Optional.
     */
    @SafeVarargs
    public final Optional<? extends Item> getItemFromAlias(final String lexeme,
                                                           Collection<? extends Item>... itemList) {
        return Arrays.stream(itemList)
                     .flatMap(Collection::stream)
                     .filter(item -> item.getItemAliases().stream().anyMatch(lexeme::equalsIgnoreCase))
                     .findFirst();
    }
}
