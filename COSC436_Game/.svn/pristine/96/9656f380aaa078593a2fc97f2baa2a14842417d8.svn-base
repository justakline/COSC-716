package objectAdventure.core.command;

// AJC_TODO: Add handling of an auxiliaryClause. E.g.: "handleNext Look under table", "drop item on item"

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Represents a command issued by the player.
 * This record holds the original input string, the verb, and the noun extracted from the input.
 *
 * @param originalInput The original input string from the player.
 * @param verb          The verb extracted from the input string.
 * @param noun          The noun extracted from the input string.
 */
public record PlayerCommand(String originalInput, String verb, String noun) {

    // Regex for tokenizing input. (It is simply split on spaces.)
    private static final Pattern INPUT_TOKEN_PATTERN = Pattern.compile("(\\S+)(.*)");

    /**
     * Tokenizes the input string into a PlayerCommand.
     * This method attempts to parse the given input string into a command consisting of a verb and a noun.
     * The input string must contain at least one word representing the verb and optionally followed by additional words
     * representing the noun.
     *
     * @param originalInput The original input string from the player. It is expected to contain at least one word
     *                      representing the verb, optionally followed by additional words representing the noun.
     * @return An Optional containing a PlayerCommand if the input matches the expected pattern,
     * otherwise an empty Optional if the input does not contain at least a verb.
     */
    public static Optional<PlayerCommand> tokenizeInputString(String originalInput) {
        // Create a matcher that will match the input string against the compiled regex pattern
        final var matcher = INPUT_TOKEN_PATTERN.matcher(originalInput);

        // Check if the matcher finds a match in the input string
        if (matcher.find()) {
            // Extract the first group (verb) and trim any leading/trailing whitespace
            var verb = matcher.group(1).trim();

            // Extract the second group (noun) and trim any leading/trailing whitespace
            var noun = matcher.group(2).trim();

            // Return an Optional containing a new PlayerCommand object with the original input, verb, and noun
            return Optional.of(new PlayerCommand(originalInput, verb, noun));
        } else {
            // If no match is found, return an empty Optional
            return Optional.empty();
        }
    }
}
