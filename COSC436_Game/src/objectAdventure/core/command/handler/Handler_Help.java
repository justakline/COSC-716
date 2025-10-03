package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;
import objectAdventure.core.item.ItemInteractionEventType;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import static objectAdventure.core.item.ItemInteractionEventType.DROP;
import static objectAdventure.core.item.ItemInteractionEventType.GET;

class Handler_Help extends AbstractCommandHandler {

    /* The help text for the game. */
    final private static String COMMAND_HELP_TEMPLATE = """
            All commands are either two words (verb noun) or a single word such as "look" or "north"
            
                Movement:
                  N(orth) | S(outh) | E(ast) | W(est) | U(p) | D(own)
            
                Item Interactions:
                  ( TAKE | DROP ) ( <item> | ALL )
                  (%s) <item>
            
                Room Interactions:
                    SHOW ROOM  # Displays an image of the room, if available.
            
                Debugging:
                  Show Game Elements:
                    DEBUG ( ROOM | MAP )
                  Change the logging level: [See Main.java]
                    LOG ( <Java Logging Level> ALL..OFF )
            
                 Other:
                    I                # ("Inventory": Show Player Inventory)
                    L {item}         # ("Look": Show Room Description & Room Items)
                    T [room id]      # ("Teleport": Jump to RoomID)
                    ?                # (This List)""";

    /**
     * A private constructor to prevent instantiation.
     */
    Handler_Help() {
        super("HELP", "?");
    }

    /**
     * Generates a formatted string containing the sorted aliases of all item interaction event types,
     * excluding the GET and DROP event types.
     *
     * @param getAliasesFunction A function that accepts an ItemInteractionEventType and returns a
     *                           string representing its alias(es).
     * @return A string consisting of the aliases of all applicable item interaction event types,
     * separated by the pipe ("|") character.
     */
    private static String getItemInteractionHelp(Function<ItemInteractionEventType, String> getAliasesFunction) {
        // Get all enum values from ItemInteractionEvent
        return Arrays.stream(ItemInteractionEventType.values())
                     .filter(value -> value != GET)  // In relocation help.
                     .filter(value -> value != DROP)  // In relocation help.
                     .map(getAliasesFunction)
                     .sorted()
                     .collect(Collectors.joining("|"));
    }


    /**
     * Return the help text for the game.
     *
     * @return The help text for the game.
     */
    @Override
    String handleAction(PlayerCommand playerCommand) {
        return String.format(COMMAND_HELP_TEMPLATE, getItemInteractionHelp(ItemInteractionEventType::name));
    }
}
