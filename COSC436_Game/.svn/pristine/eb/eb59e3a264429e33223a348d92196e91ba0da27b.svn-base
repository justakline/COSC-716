package objectAdventure.core.item;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.player.Player;

/**
 * For now, this is just a record class for the ItemInteractionEvent.
 * <p>
 * A record is used to allow for additional data to be added to the class in the future
 * without breaking any consumers of Events.
 *
 * @param event         The ItemInteractionEventType as defined in ItemInteractionEventType.
 * @param playerCommand The PlayerCommand that was used to interact with the item.
 * @param player        The Player that interacted with the item.
 */
public record ItemInteractionEvent(ItemInteractionEventType event,
                                   PlayerCommand playerCommand,
                                   Player player) {

}
