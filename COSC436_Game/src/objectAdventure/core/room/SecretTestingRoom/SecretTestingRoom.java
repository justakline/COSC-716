package objectAdventure.core.room.SecretTestingRoom;

import objectAdventure.core.item.SecretTestingItem.SecretTestingItem;
import objectAdventure.core.player.Player;
import objectAdventure.core.room.InputInterceptor;
import objectAdventure.core.room.Room;
import objectAdventure.core.room.RoomEventListener;

import static java.lang.System.out;

/**
 * A room for testing and demo purposes.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public final class SecretTestingRoom extends Room implements InputInterceptor, RoomEventListener {

    private static final String BASE_DESCRIPTION = "This is just a room for testing purposes.";

    private static final String INITIAL_DESCRIPTION_AUGMENTATION = """
            Nothing to see here, please move along.
            (Sometimes, the game may lie to you!).""";

    /**
     * Constructor accepting a room ID.
     *
     * @param id       The room ID.
     * @param roomName The room name.
     */
    public SecretTestingRoom(final int id, String roomName) {
        super(id, roomName);

        super.setRoomDescription("%s%n%s".formatted(BASE_DESCRIPTION, INITIAL_DESCRIPTION_AUGMENTATION));

        super.setRoomImageResourcePath("/objectAdventure/core/room/SecretTestingRoom/RoomImage.png");

      /*
           Note that passing "this" out of a constructor is generally a bad practice, and a "Factory

           Method" or even "Builder Pattern" is better suited for situations where this necessary.

           For a description of the potential problem see: see:
           http://www.javapractices.com/topic/TopicAction.do?Id=252

           In other words, the following code is not recommended:
           <code>
           SecretTestingItem secretTestingItem = new SecretTestingItem();
           super.addItem(secretTestingItem);
           gameInstructions.addObserver(this);
           </code>
       */
    }

    /**
     * Factory method to create the room.
     *
     * @param roomId   The room ID.
     * @param roomName The room name.
     * @return The new room object.
     */
    public static Room newInstance(final int roomId, String roomName) {
        var thisRoom = new SecretTestingRoom(roomId, roomName);
        var testingItem = new SecretTestingItem();

        // add items to room before invoking the constructor
        thisRoom.addItem(testingItem);

        // Return the room instance.
        return thisRoom;
    }

    /**
     * Retrieves the author of the room.
     *
     * @return The name of the room author.
     */
    @Override
    public String getRoomAuthor() {
        return "Adam J. Conover";
    }

    /**
     * Notifies a player entering the secret testing room. Provides a warning message
     * about entering a new room and specifies the previous room they were in.
     *
     * @param player The player entering the room. The player's previous room ID will be used
     *               to display context-specific information.
     */
    @Override
    public void playerEnteringRoom(Player player) {
        int currentRoomId = player.getCurrentRoomId();
        int previousRoom = player.getPreviousRoomID();

        out.printf("Beware!!! You're entering a secret testing room (%02d) from room %02d%n%n",
                   currentRoomId,
                   previousRoom);
    }

    /**
     * Invoked when a player leaves the room.
     *
     * @param player The player leaving the room.
     */
    @Override
    public void playerLeavingRoom(Player player) {
        int destinationRoomId = player.getCurrentRoomId();
        out.printf("Goodbye!  (Player leaving for room %02d)%n", destinationRoomId);

        // reset the description upon leaving the room.
        this.setRoomDescription(BASE_DESCRIPTION);
    }


    /**
     * Example of user input interception. (Room could have a custom parser and then allow the game
     * shell to continue.)
     *
     * @param inputLine The original input line.
     * @return The revised input line.
     */
    @Override
    public String interceptInput(String inputLine) {
        if (inputLine.equalsIgnoreCase("info")) {
            out.println("This is not an information kiosk!");
            return null;
        } else {
            return inputLine;
        }
    }
}
