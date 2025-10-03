package objectAdventure.core.command;

import objectAdventure.core.map.Direction;
import objectAdventure.core.player.Player;
import objectAdventure.core.room.NoSuchRoomException;
import objectAdventure.core.room.Room;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.System.err;
import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * The UserInputLoop class handles the main user input loop.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class UserInputLoop {

    /**
     * Constructs a private instance of the UserInputLoop class to enforce non-instantiability.
     *
     * <p>This constructor is private to prevent external instantiation of the UserInputLoop class.
     * The class is meant to house static methods related to processing user inputs,
     * and thus its instantiation is unnecessary.</p>
     */
    private UserInputLoop() {
    }

    /**
     * The main user input loop. This can eventually be refactored into a more universal "command
     * processor". But for now, we are only dealing with movement, so the input is only compass
     * directions.
     *
     * @param commandInterpreter The command processor used for the input loop.
     * @param scanner            The scanner used in the input loop.
     */
    public static void startInputLoop(CommandInterpreter commandInterpreter, Scanner scanner) {
        int currentRoomId = java.lang.Integer.MIN_VALUE;

        GameController controller = commandInterpreter.getController();

        do {
            // AJC_TODO: Keep a list of rooms visited to avoid long descriptions once seen.
            // Show the room description only on room changes.
            if (controller.getPlayerService().getCurrentRoomId() != currentRoomId) {
                try {
                    out.println(controller.getRoomService().getRoomFromID(controller.getPlayerService().getCurrentRoomId()).getRoomDescription());
                } catch (NoSuchRoomException ex) {
                    out.println(ex.getMessage());
                }
                currentRoomId = controller.getPlayerService().getCurrentRoomId();
            }

            // Prompt the user.
            String inputLine = displayUserPrompt(controller, scanner);

            if (inputLine.isBlank()) {
                out.println("Type a command or direction, or 'help' ('?') for a list of commands.");
                continue;
            }

            // Break out of the loop if the user is a quitter.
            if ("q".equals(inputLine)) {
                out.println("Use an uppercase Q (or type \"quit\") to quit.");
                continue;
            }

            // Break out of the loop if the user is a quitter.
            if ("Q".equals(inputLine) || "QUIT".equalsIgnoreCase(inputLine)) {
                // Quit the loop without any further processing!
                break;
            }

            // Process the command.
            try {
                var result = commandInterpreter.processCommand(inputLine.toUpperCase());
                result.ifPresent(UserInputLoop::displayCommandResult);
            } catch (NoSuchRoomException ex) {
                err.printf("Invalid Room: %s%n", ex.getMessage());
            }

            Logger.getGlobal().fine(getGameDebugInfo(controller));
        } while (true);  // A debatable practice but eliminates a bunch of nested (or cascading) "if" statements!
    }

    /**
     * Display the result of the command, only if there is a result.
     *
     * @param result The text result of the command.
     */
    private static void displayCommandResult(String result) {
        if (result != null && !result.isBlank()) {
            out.println(result);
        }
    }

    /**
     * Display the user Prompt.
     *
     * @param controller The game getController.
     * @param input      The Scanner source for the input.
     */
    private static String displayUserPrompt(GameController controller, Scanner input) {
        // Build a string of the available exit directions.
        List<Direction> exitDirections = controller.getMapService().getExitDirections(controller.getPlayerService().getCurrentRoomId());

        out.println();

        // Display/Prompt
        try {
            Room room = controller.getRoomService().getRoomFromID(controller.getPlayerService().getCurrentRoomId());
            out.printf("=====> In Room %d: %s <=====%n", room.getRoomId(), room.getRoomName());
        } catch (NoSuchRoomException ex) {
            out.printf("=====> In Room %d <=====%n", controller.getPlayerService().getCurrentRoomId());
        }

        out.printf("Exits: %s%n".formatted(
                exitDirections
                        .stream()
                        .map(Direction::getLongName)
                        .map(String::valueOf)
                        .collect(joining(", "))));

        out.print("[Enter Command]: ");

        return input.nextLine().trim();
    }

    /**
     * Display the current info for the room and player.
     *
     * @param controller The game getController containing the game state information,
     */
    private static String getGameDebugInfo(GameController controller) {
        final String roomInfo = controller.getRoomService()
                                          .getRoomInfo(controller
                                                               .getPlayerService()
                                                               .getCurrentRoomId());

        final Player player = controller.getPlayerService().getPlayer();

        return """
        Room Information:
        %s
        Player Information:
        %s
        """.formatted(roomInfo, player);
    }
}
