package objectAdventure.core.item;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The ItemInteractionEvent enum represents the different types of interactions that can be
 * performed on an Item.<br/><br/>
 * <p>
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
 * @author Adam J. Conover, COSC436/COSC716
 */
public enum ItemInteractionEventType {
    /**
     * LOOK event (CommandProcessor Parsed)
     */
    LOOK,

    /**
     * Get an Item. (CommandProcessor Parsed)
     */
    GET,

    /**
     * Drop an Item. (CommandProcessor Parsed)
     */
    DROP,

    /**
     * Inspect an Item. ("Inspect", "Examine")
     */
    INSPECT("Inspect", "Examine"),

    /**
     * Use an Item. ("Use")
     */
    USE("Use"),

    /**
     * Push or Pull an Item. ("Push")
     */
    PUSH("Push"),

    /**
     * Push or Pull an Item. ("Pull")
     */
    PULL("Push"),

    /**
     * Activate or deactivate an Item. ("Activate", "Enable")
     */
    ACTIVATE("Activate", "Enable"),

    /**
     * Deactivate an Item. ("Deactivate", "Disable")
     */
    DEACTIVATE("Deactivate", "Disable"),

    /**
     * Open or close an Item. ("Open")
     */
    OPEN("Open"),

    /**
     * Close an Item. ("Close")
     */
    CLOSE("Close"),

    /**
     * Repair or destroy an Item. ("Fix", "Repair", "Mend")
     */
    REPAIR("Fix", "Repair", "Mend"),

    /**
     * Destroy an Item. ("Break", "Destroy", "Smash")
     */
    DESTROY("Break", "Destroy", "Smash"),

    /**
     * Lock an Item.
     */
    LOCK("Lock"),

    /**
     * Unlock an Item.
     */
    UNLOCK("Unlock"),

    /**
     * Eat, Drink, or Consume an Item.
     */
    CONSUME("Eat", "Drink", "Consume"),

    /**
     * Do NOT taunt Happy Fun Ball!!!
     *
     * @see <a
     * href="https://en.m.wikipedia.org/wiki/Happy_Fun_Ball">https://en.m.wikipedia.org/wiki/Happy_Fun_Ball</a>
     * @see <a href="https://www.youtube.com/watch?v=GmqeZl8OI2M">https://www.youtube.com/watch?v=GmqeZl8OI2M</a>
     */
    TAUNT("Taunt"),

    /**
     * A placeholder for undefined or unrecognized item interaction events.
     */
    UNKNOWN("(Undefined)");


    // The string representation of the command.
    private final Set<String> commandList;

    /**
     * Constructor for the ItemInteractionEvent enum.
     *
     * @param commandStrings The string representation of the command.
     */
    ItemInteractionEventType(final String... commandStrings) {
        this.commandList = Arrays
                .stream(commandStrings)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }


    /**
     * For actions with associated verbs handled directly by the command processor.
     */
    ItemInteractionEventType() {
        this.commandList = Collections.emptySet();
    }

    /**
     * Gets the direction object based on the text that was entered.
     *
     * @param lexeme the string name of the direction to map to a real Direction object.
     * @return the Direction object created from the string as an Optional, which will be empty if
     * the direction string could not be parsed.
     */
    public static ItemInteractionEventType actionFromLexeme(final String lexeme) {
        for (var action : values()) {
            if (action.commandList.contains(lexeme.trim())) {
                return action;
            }
        }

        return UNKNOWN;
    }

    /**
     * Get a comma-separated list of the aliases for the command.
     *
     * @return The comma-separated list of aliases for the command.
     */
    public String getAliases() {
        return this.commandList.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
    }

    /**
     * Gets the string representation of the command for debugging purposes.
     *
     * @return The string representation of the command.
     */
    @Override
    public String toString() {
        return "Verb{" + "commandString=" + this.name() + '}';
    }
}
