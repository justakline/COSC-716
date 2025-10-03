package objectAdventure.core.command.handler;

import objectAdventure.core.command.PlayerCommand;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * Handles log level commands issued by the player, allowing them to
 * dynamically change the logging level of the application.
 *
 * <p>This handler processes log commands and sets the global logger level
 * based on the provided parameter.</p>
 */
class Handler_LoggerSettings extends AbstractCommandHandler {

    /**
     * Constructs a new Handler_LoggerSettings instance.
     */
    Handler_LoggerSettings() {
        super("LOG");
    }


    /**
     * Handles log commands by setting the global logger level.
     *
     * @param playerCommand The log command issued by the player
     * @return A response string indicating the result of the log level change
     */
    @Override
    protected String handleAction(PlayerCommand playerCommand) {
        String noun = playerCommand.noun();

        try {
            Logger.getGlobal().setLevel(Level.parse(noun));
            return format("Log level set to: %s", noun);
        } catch (IllegalArgumentException _ex) {
            return format("Invalid log level: %s", noun);
        }
    }
}
