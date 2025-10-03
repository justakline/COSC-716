package objectAdventure.core.item.SecretTestingItem;

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.List;

/**
 * An item for illustrating advanced item features.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class SecretTestingItem implements Item {

    private final TalkingMap talkingMap;
    private String displayName;
    private String description;
    private boolean isPlayerHoldingMap = false;
    private int pickupAttempts = 0;

    /**
     * Constructor
     */
    public SecretTestingItem() {
        this.displayName = "The GameMap";
        this.description = "A GameMap (PDF located in Game source folder).";
        this.talkingMap = new TalkingMap();
    }

    /**
     * THIS IS JUST A SAMPLE.
     *
     * @return The item description.
     */
    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }

    /**
     * This a list of nouns the player can use to reference the item.
     *
     * @return Aliases for item.
     */
    @Override
    public List<String> getItemAliases() {
        return List.of("Map", "Game-Map");
    }

    /* ----------------------------- Item Interaction Handler --------------------------------
     * The game core has notified the item it has been interacted with.
     *
     * NOTE: The Item could easily be BOTH an Observer AND Observable (observing events, which it
     * then forwards on to its own observers)! But, because EVERY item receives the same
     * information, making it an "observer" of the game getController is unnecessary. The observer
     * common makes most sense when only some Objects are observable and others are not
     * or Observable items are observed by many other kinds of objects.
     * ---------------------------------------------------------------------------------------
     * TAKE("Get", "Take"): Take an Item.<br/>
     * DROP("Drop"): Drop an Item.<br/>
     * INSPECT("Inspect", "Examine"): Inspect an Item.<br/>
     * USE("Use"): Use an Item.<br/>
     * PUSH("Push"), PULL("Push"): Push or pull an Item.<br/>
     * ACTIVATE("Activate", "Enable"), DEACTIVATE("Deactivate", "Disable"): Activate or deactivate an Item.<br/>
     * OPEN("Open"), CLOSE("Close"): Open or close an Item.<br/>
     * REPAIR("Fix", "Repair", "Mend"), DESTROY("Break", "Destroy", "Smash"): Repair or destroy an Item.<br/>
     * CONSUME("Eat", "Drink", "Consume"): Eat, drink, or consume an Item.<br/>
     * LOCK("Lock"), UNLOCK("Unlock"): Lock or unlock an Item.<br/>
     * TAUNT("Taunt"); // Do NOT taunt Happy Fun Ball!!!<br/>
     *
     * @param itemInteractionEvent@return The ItemInteractionResult object.
     * @see ItemInteractionEventType
     */
    @Override
    public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
        return switch (itemInteractionEvent.event()) {
            case DROP -> {
                isPlayerHoldingMap = false;

                // Stop the map talking.
                talkingMap.stopTalking();

                yield ItemInteractionResult.success("You'll be lost without me!!! (The map is dropped.)");
            }

            case GET -> {
                if (pickupAttempts < 1) {
                    pickupAttempts = 1; // no need to keep incrementing this.
                    this.displayName = "Discarded Map";

                    yield ItemInteractionResult.failure(
                            "The map slips through your fingers. Maybe you should try again!");
                } else {
                    isPlayerHoldingMap = true;
                    this.displayName = "Game Map";

                    // Start the map talking.
                    talkingMap.startTalking();

                    yield ItemInteractionResult.success("The map has been taken!");
                }
            }

            case USE -> {
                if (isPlayerHoldingMap) {
                    this.description = "A map that appears to have been refolded with great frustration.";

                    yield ItemInteractionResult.success("You successfully use the map.");
                } else {
                    yield ItemInteractionResult.failure("You must be holding the map to use it!");
                }
            }

            case INSPECT -> {
                if (isPlayerHoldingMap) {
                    this.description = "A thoroughly inspected map.";

                    yield ItemInteractionResult.success("You inspect the map.");
                } else {
                    yield ItemInteractionResult.failure("You must be holding the map to inspect it!");
                }
            }

            // NOTE the default case below should never be reached if as unknown actions are treated as UNKNOWN.
            //case UNKNOWN ->  ItemInteractionResult.failure("Unknown action handled by SecretTestingItem.");

            // Not implemented/handled for this item.
            default -> ItemInteractionResult.failure("SecretTestingItem does not respond to that action.");
        };

    }

}
