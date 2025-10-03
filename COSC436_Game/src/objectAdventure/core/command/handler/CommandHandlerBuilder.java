package objectAdventure.core.command.handler;

import objectAdventure.core.command.service.Service_Item;
import objectAdventure.core.command.service.Service_Map;
import objectAdventure.core.command.service.Service_Player;
import objectAdventure.core.command.service.Service_Room;

/**
 * Factory for creating the the command chain.
 */
public class CommandHandlerBuilder {

    private Service_Player playerService;
    private Service_Room roomService;
    private Service_Item itemService;
    private Service_Map mapService;


    /**
     * Private constructor to prevent exernal instantiation.
     */
    CommandHandlerBuilder() {
    }

    /**
     * Static factory method to create a new builder instance.
     *
     * @return A new CommandHandlerFactoryBuilder instance
     */
    public static CommandHandlerBuilder create() {
        return new CommandHandlerBuilder();
    }

    /**
     * Sets up the Chain of Responsibility for command handling.
     * Creates all command handlers and links them together in the proper order.
     *
     * @return The first handler in the chain
     */
    private AbstractCommandHandler setupCommandChain() {
        // Create all handlers
        AbstractCommandHandler handlerChain = new Handler_Move(playerService, mapService, roomService);

        // Set up the chain - order matters for priority
        handlerChain
                .setNext(new Handler_Inventory(playerService))
                .setNext(new Handler_Help())
                .setNext(new Handler_Take(playerService, roomService, itemService))
                .setNext(new Handler_Drop(playerService, roomService, itemService))
                .setNext(new Handler_Look(playerService, roomService))
                .setNext(new Handler_Display(roomService, playerService))
                .setNext(new Handler_Teleport(playerService, roomService, mapService))
                .setNext(new Handler_Debug(roomService, playerService))
                .setNext(new Handler_LoggerSettings())
                .setNext(new Handler_ItemInteraction(playerService, roomService, itemService))
                .setNext(new Handler_Default());

        return handlerChain; // Return the first handler in the chain
    }

    /**
     * Sets the player service for the factory.
     *
     * @param playerService The player service to use
     * @return This builder instance for method chaining
     */
    public CommandHandlerBuilder addPlayerService(Service_Player playerService) {
        this.playerService = playerService;
        return this;
    }

    /**
     * Sets the room service for the factory.
     *
     * @param roomService The room service to use
     * @return This builder instance for method chaining
     */
    public CommandHandlerBuilder addRoomService(Service_Room roomService) {
        this.roomService = roomService;
        return this;
    }

    /**
     * Sets the item service for the factory.
     *
     * @param itemService The item service to use
     * @return This builder instance for method chaining
     */
    public CommandHandlerBuilder addItemService(Service_Item itemService) {
        this.itemService = itemService;
        return this;
    }

    /**
     * Sets the map service for the factory.
     *
     * @param mapService The map service to use
     * @return This builder instance for method chaining
     */
    public CommandHandlerBuilder addMapService(Service_Map mapService) {
        this.mapService = mapService;
        return this;
    }

    /**
     * Builds and returns a configured CommandHandlerBuilder instance.
     * All required services must be set before calling this method.
     *
     * @return A new CommandHandlerBuilder configured with the provided services
     * @throws IllegalStateException if any required service is not set
     */
    public AbstractCommandHandler build() {
        validateServices();
        return setupCommandChain();
    }

    /**
     * Validates that all required services have been set.
     *
     * @throws IllegalStateException if any required service is missing
     */
    private void validateServices() {
        if (playerService == null) {
            throw new IllegalStateException("Player service must be set");
        }
        if (roomService == null) {
            throw new IllegalStateException("Room service must be set");
        }
        if (itemService == null) {
            throw new IllegalStateException("Item service must be set");
        }
        if (mapService == null) {
            throw new IllegalStateException("Map service must be set");
        }
    }
}
