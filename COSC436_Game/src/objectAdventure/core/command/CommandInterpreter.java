package objectAdventure.core.command;

import java.util.Optional;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!! NOTE !!!!!!!!!!!!!!!!!!!!!!!!!!!
 * <p>This class is intended to be improved upon by using a more sophisticated design pattern such
 * as the 'Interpreter' pattern, perhaps in combination with other collaborating patterns such as
 * 'Command', 'Chain of Responsibility', and/or 'Visitor' patterns!!!</p>
 *
 * <p>Aside from Directions and a few helpful exceptions (noted in "showCommands()"), all commands
 * are two words (verb noun).</p>
 *
 * @param getController The game getController that manages the game state and logic.
 * @author Adam J. Conover, COSC436/COSC716
 */
public record CommandInterpreter(GameController getController) {

    /**
     * Parse player input.
     *
     * @param inputLine The line typed by the user
     * @return An {@link Optional} containing the response string resulting from the command execution.
     */
    public Optional<String> processCommand(final String inputLine) {
        // Bail out if null
        if (inputLine == null) {
            return Optional.empty();
        }

        // Ensure the input is uppercase and trimmed
        String normalizedLine = getController.preProcessInput(inputLine).trim().toUpperCase();

        // Bail out if blank
        if (normalizedLine.isBlank()) {
            return Optional.empty();
        }

        // Tokenize the string
        var tokenizedInput = PlayerCommand.tokenizeInputString(normalizedLine);

        // Process command
        if (tokenizedInput.isPresent()) {
            PlayerCommand playerCommand = tokenizedInput.get();
            return processCommand(playerCommand);
        } else {
            return Optional.empty();
        }
    }
    /**
     * Processes a command issued by the player using the Chain of Responsibility pattern.
     * The method delegates to the GameController's command chain to handleNext the command
     * processing. If no handler in the chain can process the command, an UnknownCommandException
     * is thrown.
     *
     * @param playerCommand The command issued by the player. Contains the verb and noun extracted
     *                      from the player's input.
     * @return An {@link Optional} containing the response string resulting from the command execution.
     * If the command does not produce a text response (e.g., movement commands), the Optional
     * will be empty.
     */
    private Optional<String> processCommand(final PlayerCommand playerCommand) {
        return getController.processCommand(playerCommand)
                            .filter(response -> !response.isEmpty());
    }
}
