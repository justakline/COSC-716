package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;

/**
 * Default handler that catches all unrecognized commands.
 * This handler is placed at the end of the "chain of responsibility"
 * to provide feedback when no other handler can process a command.
 */
class Handler_Default extends AbstractCommandHandler {

    /**
     * Constructs a new Handler_Default instance.
     * This handler doesn't specify any verbs since it should catch all commands.
     */
    Handler_Default() {
        super(); // No specific verbs - will catch everything
    }

    /**
     * Always returns true since this is the default handler that should
     * catch any command that no other handler could process.
     *
     * @param playerCommand The command to check
     * @return always true
     */
    @Override
    boolean canHandle(PlayerCommand playerCommand) {
        return true; // Handle any command that reaches this handler
    }

    /**
     * Returns an appropriate message indicating the command was not understood.
     *
     * @param playerCommand The unrecognized command
     * @return A helpful message suggesting the user try the help command
     */
    @Override
    String handleAction(PlayerCommand playerCommand) {
        final String tmplt = "I don't understand the command '%s'. Try 'help' or '?' for available commands.";
        return String.format(tmplt, playerCommand.originalInput());
    }
}
