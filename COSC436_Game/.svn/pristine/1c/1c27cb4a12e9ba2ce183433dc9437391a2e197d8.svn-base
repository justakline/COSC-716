package objectAdventure.core.command.handler;
// Chain of Responsibility pattern base handler

import objectAdventure.core.command.PlayerCommand;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * Base interface for command handlers in the Chain of Responsibility pattern.
 * Each handler can process a command or pass it to the next handler in the chain.
 *
 * <p>This interface extends ActionHandler to maintain compatibility with existing
 * handler implementations while adding chain functionality.</p>
 */
public abstract class AbstractCommandHandler {

    // Set of verbs that this handler can process, stored in uppercase for case-insensitive matching.
    private final HashSet<String> handledVerbs = new HashSet<>();
    /**
     * The next handler in the chain.
     */
    protected AbstractCommandHandler nextHandler;

    /**
     * Constructs a new AbstractCommandHandler instance with the specified verbs.
     * The verbs are converted to uppercase to ensure case-insensitive handling.
     *
     * @param handledVerbs The verbs that this handler can process
     */
    protected AbstractCommandHandler(String... handledVerbs) {
        for (String verb : handledVerbs) {
            this.handledVerbs.add(verb.toUpperCase());
        }
    }

    /**
     * Constructs a new AbstractCommandHandler instance with the specified verbs.
     *
     * @param handledVerbs The verbs that this handler can process
     */
    protected AbstractCommandHandler(Collection<String> handledVerbs) {
        handledVerbs.forEach(verb -> this.handledVerbs.add(verb.toUpperCase()));
    }

    /**
     * Add verb to handler the handler.
     *
     * @param verb The verb to add
     */
    protected void addHandledVerb(String verb) {
        handledVerbs.add(verb.toUpperCase());
    }

    /**
     * Sets the next handler in the chain.
     *
     * @param nextHandler The next handler to be called if this handler cannot process the command
     * @return The next handler for method chaining
     */
    protected AbstractCommandHandler setNext(AbstractCommandHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    /**
     * Handles the command if this handler can process it, otherwise passes it to the next handler.
     *
     * @param playerCommand The command to be processed
     * @return An Optional containing the response string if the command was handled, empty otherwise
     */
    public Optional<String> handleNext(PlayerCommand playerCommand) {
        if (canHandle(playerCommand)) {
            return Optional.of(handleAction(playerCommand));
        } else if (nextHandler != null) {
            return nextHandler.handleNext(playerCommand);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Determines if this handler can process the given command.
     *
     * @param playerCommand The command to check
     * @return true if this handler can process the command, false otherwise
     */
    boolean canHandle(PlayerCommand playerCommand) {
        return handledVerbs.contains(playerCommand.verb().toUpperCase());
    }

    /**
     * Processes the command. This method should only be called if canHandle returns true.
     *
     * @param playerCommand The command to process
     * @return The response string from processing the command
     */
    abstract String handleAction(PlayerCommand playerCommand);
}
