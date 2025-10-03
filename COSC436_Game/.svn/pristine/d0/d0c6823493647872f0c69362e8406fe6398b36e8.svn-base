package objectAdventure.core.room;

/**
 * Interface for rooms that can intercept input.
 *
 * <p>An input interceptor can be used to modify the input before it is processed. The String
 * returned by the interceptInput method will be used as the input, as if it were typed by the
 * user.</p>
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
@FunctionalInterface
public interface InputInterceptor {

    /**
     * Intercept the input line and return the modified input line.
     *
     * <p>For example:</p>
     * <pre>{@code
     *      public String interceptInput(String inputLine) {
     *          if ("info".equalsIgnoreCase(inputLine)) {
     *             out.println("This is not an information kiosk!");
     *             return null;  // Don't process the command any further
     *          } else if ("jump".equalsIgnoreCase(inputLine)) {
     *             out.println("You jump right though the ceiling into another room.");
     *             return "up"; // Process the command as if the user typed "up"
     *          } else {
     *             return inputLine; // Process the command as normal
     *          }
     *      }
     * }</pre>
     *
     * @param inputLine The input line to be intercepted.
     * @return The modified input line.
     */
    String interceptInput(String inputLine);
}
