# Adding New Rooms and Items to the Game Environment

## TL;DR - Quick Start
**What you need to do:**
1. **Create 3 files** in `src/objectAdventure/world/[your-username]/`:
   - `YourRoom.java` (extends Room)
   - `YourRoomProvider.java` (implements RoomProvider)
   - `YourItem.java` (implements Item)
2. **Edit 1 file**: Add your provider class name to `src/META-INF/services/objectAdventure.core.room.RoomProvider`
3. **Use your assigned room ID** from class
4. **Don't modify** any `core` package files

**Key points:**
- Copy the pattern from `objectAdventure.world.aconover` package
- Item aliases cannot contain spaces (use hyphens: "magic-sword" not "magic sword")
- Your room appears automatically once registered via ServiceLoader

-----------------------------------------------------------------------------------------------------------------

## Overview
This guide explains how to add new rooms and items to the adventure game. You will create your own package under 
`objectAdventure.world` and follow the established patterns shown in the `objectAdventure.world.aconover` package.

**IMPORTANT:** Do not modify any files in the `objectAdventure.core` package. These are core game files that should remain unchanged.

## Part 1: Adding New Rooms

### Step 1: Create Your Package Structure
1. Navigate to `src/objectAdventure/world/`
2. Create a new package with your username (e.g., `student123` if your username is student123)
3. Your classes should be in a package located in: `src/objectAdventure/world/student123/`
4. The corresponding "package directive" would be: `package objectAdventure.world.student123`

### Step 2: Know Your Room ID
- Each room must have a unique ID number
- Use the room ID assigned to you in class
- This ID will be used to identify your room in the game map

### Step 3: Create Your Room Class
Create a new Java class that extends `Room`. Use the following template based on `StartRoom.java`:

```java
package objectAdventure.world.student123; // Replace 'student123' with your username

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
```

### Step 4: Create Your Room Provider Class
Create a provider class that implements `RoomProvider`. This allows the game to automatically discover your room:

```java
package objectAdventure.world.student123; // Replace 'student123' with your username

import objectAdventure.core.room.Room;
import objectAdventure.core.room.RoomProvider;

/**
 * Service provider implementation for YourRoomName.
 *
 * @author [Your Name], COSC436/COSC716
 */
public class YourRoomNameProvider implements RoomProvider {

    /**
     * Creates and returns your room instance.
     *
     * @return A configured room instance with your assigned ID and chosen name.
     */
    @Override
    public Room createRoom() {
        return new YourRoomName(YOUR_ASSIGNED_ROOM_ID, "Your Room Display Name");
    }
}
```

### Step 5: Register Your Room Provider
1. Open the file `src/META-INF/services/objectAdventure.core.room.RoomProvider`
2. Add a new line with your provider's full class name:
   ```
   objectAdventure.world.student123.YourRoomNameProvider
   ```
3. Make sure there are no extra spaces or characters

### Example Complete Room Implementation
Here's what your files should look like (using username 'student123' and room ID 101):

**MyLibrary.java:**
```java
package objectAdventure.world.student123;

import objectAdventure.common.Utils;
import objectAdventure.core.item.Item;
import objectAdventure.core.room.Room;

public class MyLibrary extends Room {
    public MyLibrary(final int roomId, final String roomName) {
        super(roomId, roomName);
        setRoomAuthor("John Smith");
        initRoomItems();
        initRoomDescription();
    }

    private void initRoomItems() {
        Item book = new AncientBook();
        this.addItem(book);
    }

    private void initRoomDescription() {
        String description = """
                You find yourself in a dimly lit library with towering bookshelves.
                Dust motes dance in the rays of sunlight streaming through tall windows.
                The air smells of old parchment and forgotten knowledge.
                """;

        String roomNameAndAuthor = getRoomName() + "\n" + getRoomAuthor();
        setRoomDescription(Utils.boxifyText(roomNameAndAuthor) + '\n' + description);
    }
}
```

**MyLibraryProvider.java:**
```java
package objectAdventure.world.student123;

import objectAdventure.core.room.Room;
import objectAdventure.core.room.RoomProvider;

public class MyLibraryProvider implements RoomProvider {
    @Override
    public Room createRoom() {
        return new MyLibrary(999, "The Ancient Library");
    }
}
```

## Part 2: Adding Items to Your Room

### Understanding the Item Interface
Items in the game implement the `Item` interface, which requires:
- `getItemFullDescription()`: Detailed description shown when examining the item
- `getItemDisplayName()`: Short name shown in lists and inventory
- `getItemAliases()`: List of names players can use to reference the item (no spaces allowed!)

### Step 1: Create Your Item Class
Create a new Java class in your package that implements `Item`:

```java
package objectAdventure.world.student123; // Replace with your username

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.List;

/**
 * [Description of your item]
 *
 * @author [Your Name], COSC436/COSC716
 */
public class YourItemName implements Item {

    private final String description;
    private final String displayName;

    /**
     * Constructor for your item
     */
    public YourItemName() {
        this.displayName = "Your Item Display Name";
        this.description = "A detailed description of your item that players see when they examine it.";
    }

    /**
     * Get the full description of the item.
     *
     * @return The detailed item description.
     */
    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    /**
     * Get the short display name of the item.
     *
     * @return The short item name for lists and inventory.
     */
    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }

    /**
     * Get the list of item aliases (names players can use to reference this item).
     * IMPORTANT: Aliases must NOT contain spaces! Use hyphens or underscores instead.
     *
     * @return List of aliases for this item.
     */
    @Override
    public List<String> getItemAliases() {
        return List.of("alias1", "alias-2", "another-name");
    }

    /**
     * Make the item non-moveable if needed (optional)
     * @return true if the item cannot be picked up
     */
    @Override
    public boolean isAnchored() {
        return false; // Set to true if item should stay in the room
    }
}
```

### Step 2: Add Item Interactions (Optional)
You can make your items interactive by overriding the `itemInteractionHandler` method:

```java
/**
 * Handle player interactions with this item.
 *
 * @param itemInteractionEvent The interaction event
 * @return The result of the interaction
 */
@Override
public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
    return switch (itemInteractionEvent.event()) {
            // Do something when the item is used
        case USE -> ItemInteractionResult.success("You use the " + this.getItemDisplayName() + " successfully!");

        // Provide additional detail when examined
        case INSPECT -> ItemInteractionResult.success("Upon closer inspection, you notice something special about this item...");
        
        // Add more cases as needed for other actions:
        // TAKE, DROP, ACTIVATE, DEACTIVATE, OPEN, CLOSE, REPAIR, DESTROY,
        // CONSUME, LOCK, UNLOCK, PULL, TAUNT
        
        // Default behavior for unhandled actions
        default -> ItemInteractionResult.failure("You can't do that with the " + this.getItemDisplayName());
    };
}
```

### Step 3: Add Items to Your Room
In your room's `initRoomItems()` method, create and add your items:

```java
private void initRoomItems() {
    // Create your custom items
    Item book = new AncientBook();
    Item key = new MysteriousKey();
    Item chest = new TreasureChest();

    // Add them to the room
    this.addItem(book);
    this.addItem(key);
    this.addItem(chest);
}
```

### Example Complete Item Implementation

**AncientBook.java:**
```java
package objectAdventure.world.student123;

import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;

import java.util.List;

public class AncientBook implements Item {

    private String description;
    private final String displayName;
    private boolean hasBeenRead;

    public AncientBook() {
        this.displayName = "Ancient Leather-Bound Book";
        this.description = "A weathered book with strange symbols on the cover. The pages look ancient and fragile.";
        this.hasBeenRead = false;
    }

    @Override
    public String getItemFullDescription() {
        return this.description;
    }

    @Override
    public String getItemDisplayName() {
        return this.displayName;
    }

    @Override
    public List<String> getItemAliases() {
        return List.of("Book", "Ancient-Book", "Tome", "Leather-Book");
    }

    @Override
    public boolean isAnchored() {
        return false; // Can be picked up
    }

    /** OPTIONAL (for now) **/
    @Override
    public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent itemInteractionEvent) {
        return switch (itemInteractionEvent.event()) {
            case USE -> {
                if (!hasBeenRead) {
                    this.description = "You have read the ancient book. Its secrets are now known to you.";
                    this.hasBeenRead = true;
                    yield ItemInteractionResult.success("You carefully open the book and read its contents. Knowledge flows into your mind!");
                } else {
                    yield ItemInteractionResult.success("You have already read this book.");
                }
            }

            case INSPECT -> {
                yield ItemInteractionResult.success("The book appears to contain ancient spells and forgotten lore.");
            }

            default -> ItemInteractionResult.failure("You can't do that with the " + this.getItemDisplayName());
        };
    }
}
``` 

---
## Testing Your Implementation

1. **Compile your code**: Make sure your classes compile without errors
2. **Check the service registration**: Verify your provider is listed in the META-INF services file
3. **Test in game**: Run the game and navigate to your room to test:
    - Can you enter the room?
    - Does the room description display correctly?
    - Can you see and interact with your items?
    - Do the item aliases work for player commands?

---
## Common Mistakes to Avoid

1. **Package naming**: Use your actual username, not "student123"
2. **Room ID conflicts**: Make sure you use your assigned class room ID
3. **Item aliases with spaces**: Aliases cannot contain spaces - use hyphens or underscores
4. **Forgetting service registration**: Your room won't appear if not registered in META-INF services
5. **Modifying core files**: Never modify files in the `objectAdventure.core` package

---
## Summary Checklist

### For Adding a Room:
- [ ] Created package `objectAdventure.world.[username]`
- [ ] Created room class extending `Room`
- [ ] Created provider class implementing `RoomProvider`
- [ ] Used assigned room ID
- [ ] Registered provider in META-INF services file
- [ ] Set room author to your name
- [ ] Added room description

### For Adding Items:
- [ ] Created item class implementing `Item`
- [ ] Implemented required methods: `getItemFullDescription()`, `getItemDisplayName()`, `getItemAliases()`
- [ ] Ensured aliases have no spaces
- [ ] Added items to room in `initRoomItems()` method
- [ ] Tested item interactions (if implemented)

Follow these instructions carefully, and you'll have successfully added your own room and items to the adventure game!

---
## Troubleshooting
- My room doesn’t appear:
    - Ensure your provider class name is listed exactly in the SPI file and your package/class names match.
    - Confirm your provider returns new YourRoom(yourAssignedId, "Your Room Name").
- Duplicate room ID error:
    - Use the ID assigned to you in class. If a conflict is reported, contact your instructor.
- Movement paths:
    - Movement routes are defined by the preconfigured map and core engine. Students do not edit these; ask your instructor if you have questions about reachability during testing.
- Can’t pick up my item:
    - Check isAnchored(); anchored items are stationary by design.
- Item commands not recognized:
    - Ensure aliases contain no spaces and consider adding multiple alias variants.
