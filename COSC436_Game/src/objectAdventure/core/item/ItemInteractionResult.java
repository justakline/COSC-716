package objectAdventure.core.item;


/**
 * A record class for returning the result of an interaction with a game item.
 * <p>
 * This could have been modeled by subclassing an abstract class or interface,
 * but when your options are truly binary (success or failure), a record is
 * more concise and readable. If additional possibilities were to be added later,
 * this would almost certainly be refactored into a class hierarchy.
 *
 * @param bSuccess Whether the interaction was successful.
 * @param message  A message to display to the user.
 * @author Adam J. Conover, COSC436/COSC716
 */
public record ItemInteractionResult(boolean bSuccess, String message) {

    /**
     * A static factory method for creating an "action successful" result
     * object with a corresponding message to display to the user.
     *
     * @param message The message to display to the user.
     * @return A successful result.
     */
    public static ItemInteractionResult success(String message) {
        return new ItemInteractionResult(true, message);
    }

    /**
     * A static factory method for creating an "action successful" result
     * object without a message to display to the user.
     *
     * @return A successful result.
     */
    public static ItemInteractionResult success() {
        return new ItemInteractionResult(true, "");
    }

    /**
     * A static factory method for creating an "action failed" result
     * object with a corresponding message to display to the user.
     *
     * @param message The message to display to the user.
     * @return A failure result.
     */
    public static ItemInteractionResult failure(String message) {
        return new ItemInteractionResult(false, message);
    }

    /**
     * A static factory method for creating an "action failed" result
     * object without a message to display to the user.
     *
     * @return A failure result.
     */
    public static ItemInteractionResult failure() {
        return new ItemInteractionResult(false, "");
    }
}
