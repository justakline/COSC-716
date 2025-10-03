package objectAdventure.core.command;

import objectAdventure.core.command.handler.AbstractCommandHandler;
import objectAdventure.core.command.handler.CommandHandlerBuilder;
import objectAdventure.core.command.service.Service_Item;
import objectAdventure.core.command.service.Service_Map;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;
import objectAdventure.core.map.GameMap;
import objectAdventure.core.map.RoomList;
import objectAdventure.core.player.Player;
import objectAdventure.core.room.SecretTestingRoom.SecretTestingRoom;

import java.util.Optional;

/**
 * The GameController class is responsible for managing the game state, processing player commands,
 * and coordinating interactions between the player, rooms, items, and the game map.
 * It uses the Chain of Responsibility pattern to handle commands flexibly.
 */
public class GameController  {

    // Chain of Responsibility handlers
    private final AbstractCommandHandler commandChain;

    // Service classes for better separation of concerns
    private final Service_Player playerService;
    private final Service_Room roomService;
    private final Service_Item itemService;
    private final Service_Map mapService;

    /**
     * Constructor for the GameController.
     *
     * @param player The player object.
     */
    public GameController(Player player) {
        // Initialize the GameController
        // Game state and interactions.
        GameMap gameMap = new GameMap();
        RoomList rooms = RoomList.newInstance();

        // Initialize service classes
        this.playerService = new Service_Player(player);
        this.roomService = new Service_Room(rooms);
        this.itemService = new Service_Item();
        this.mapService = new Service_Map(gameMap);

        // Initialize the Chain of Responsibility using Builder pattern
        this.commandChain = CommandHandlerBuilder.create()
                                                 .addPlayerService(playerService)
                                                 .addRoomService(roomService)
                                                 .addItemService(itemService)
                                                 .addMapService(mapService)
                                                 .build();

        player.setCurrentRoomId(0);

        // Add the secret testing room to the room list
        rooms.addRoom(SecretTestingRoom.newInstance(99, "Secret Testing Room"));
    }

    /**
     * Gets the player service.
     *
     * @return the player service
     */
    public Service_Player getPlayerService() {
        return playerService;
    }

    /**
     * Gets the room service.
     *
     * @return the room service
     */
    public Service_Room getRoomService() {
        return roomService;
    }

    /**
     * Gets the item service.
     *
     * @return the item service
     */
    public Service_Item getItemService() {
        return itemService;
    }

    /**
     * Gets the map service.
     *
     * @return the map service
     */
    public Service_Map getMapService() {
        return mapService;
    }

    /**
     * Processes a command using the Chain of Responsibility pattern.
     *
     * @param playerCommand The command to process
     * @return An Optional containing the response string if handled, empty otherwise
     */
    Optional<String> processCommand(PlayerCommand playerCommand) {
        if (playerCommand == null) {
            return Optional.empty();
        }

        return commandChain.handleNext(playerCommand);
    }

    /**
     * Allows rooms to preprocess the raw input string before it is handled by the main game shell.
     * This can be used for custom parsing or manipulation of input.
     *
     * @param inputLine The raw input line.
     * @return The manipulated input line.
     */
    String preProcessInput(String inputLine) {
        return roomService.preProcessInput(inputLine, playerService.getCurrentRoomId());
    }
}
