package objectAdventure;

import objectAdventure.core.command.CommandInterpreter;
import objectAdventure.core.command.GameController;
import objectAdventure.core.command.UserInputLoop;
import objectAdventure.core.player.Player;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NOTE: The main Entry point to the program…
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class Main {

    // Default logging level.
    // SEE: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/System.Logger.Level.html
    private static final Level LOG_LEVEL = Level.WARNING;

    // Set up logging as early as possible.
    static {
        System.setErr(System.out); // Redirect all error messages to the console.
        Logger.getGlobal().setLevel(LOG_LEVEL); // Set the default logging level.
    }

    // Private constructor to prevent instantiation. (Utility Class)
    private Main() {
    }

    /**
     * Main Method for game.
     *
     * @param args Not Used
     */
    public static void main(String[] args) {
        // Create the player and the game getController.
        var player = new Player("Player");
        var controller = new GameController(player);

        // Create the command interpreter and start the user input loop.
        var interpreter = new CommandInterpreter(controller);

        // Start the user input loop with the command processor and a scanner for input.
        UserInputLoop.startInputLoop(interpreter, new Scanner(System.in));
    }
}
