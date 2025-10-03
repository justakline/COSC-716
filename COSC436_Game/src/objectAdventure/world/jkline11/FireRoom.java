package objectAdventure.world.jkline11;

import objectAdventure.common.Utils;
import objectAdventure.core.item.Item;
import objectAdventure.core.room.Room;

/**
 * The StartRoom is the first room the player will see when they start the game.
 *
 * @author Adam J. Conover, COSC436/COSC716
 */
public class FireRoom extends Room {
    /**
     * Constructor for the room.
     * <p>
     * This is private because we are using a simple static initializer to create the new room.
     *
     * @param roomId The ID of the room.
     */
    public FireRoom(final int roomId, final String roomName) {
        super(roomId, roomName);

        // This should be set to your name.
        setRoomAuthor("Justin Kline");

        initRoomItems();
        initRoomDescription();
    }

    /**
     * Initializes the room items.
     */
    private void initRoomItems() {
        Item sword = new FireSword();
        this.addItem(sword);
    }

    /**
     * Sets up the room description.
     */
    private void initRoomDescription() {
        // NOTE: This text should be replaced with your own!!!
        String description = """
                The room is large and swealtering. There is a soot filled fireplace
                located at the south wall. A flame burns white and hot while the air
                grows evermore intense.""";

        // Construct the room display header.
        String roomNameAndAuthor = "%s\n%s".formatted(getRoomName(), getRoomAuthor());
        final String roomDisplayHeader = Utils.boxifyText(roomNameAndAuthor);

        // Set the Room description.
        setRoomDescription(roomDisplayHeader + '\n' + description);
    }

}
