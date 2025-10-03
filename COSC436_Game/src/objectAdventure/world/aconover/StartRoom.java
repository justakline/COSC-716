package objectAdventure.world.jkline11; // Replace 'student123' with your username

import objectAdventure.common.Utils;
import objectAdventure.core.item.Item;
import objectAdventure.core.room.Room;

/**
 * [Your room description here]
 *
 * @author [Your Name], COSC436/COSC716
 */
public class YourRoomName extends Room {
    
    /**
     * Constructor for the room.
     *
     * @param roomId The ID of the room (use your assigned class ID)
     * @param roomName The display name of your room
     */
    public YourRoomName(final int roomId, final String roomName) {
        super(roomId, roomName);

        // Set this to your name
        setRoomAuthor("Your Full Name");

        initRoomItems();
        initRoomDescription();
    }

    /**
     * Initializes the room items.
     */
    private void initRoomItems() {
        // Add items to your room here
        // Example: Item myItem = new MyCustomItem();
        // Example: this.addItem(myItem);
    }

    /**
     * Sets up the room description.
     *
     */
    private void initRoomDescription() {
        // Create your room's description text
        String description = """
                Write your room description here using this multi-line string format.
                Describe what the player sees when they enter your room.
                Make it interesting and engaging!
                """;

        // The following two lines simply create a nicely formatted for the description. 
        String roomNameAndAuthor = getRoomName() + "\n" + getRoomAuthor();
        final String output = Utils.boxifyText(roomNameAndAuthor) + '\n' + description;
        
        // Set the complete room description
        setRoomDescription(output);
    }
}