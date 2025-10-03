package objectAdventure.core.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The Direction enum is used to represent the directions that a player can move in the game.
 * Each direction has a long name and an abbreviation.
 *
 * <p>Example usage:
 * <code>
 * Direction north = Direction.NORTH;
 * System.out.println(north.getLongName()); // Outputs: North
 * </code>
 *
 * <p>Author: Adam J. Conover, COSC436/COSC716</p>
 */
public enum Direction {

    /**
     * Represents the North direction.
     */
    NORTH("North", 'N'),

    /**
     * Represents the South direction.
     */
    SOUTH("South", 'S'),

    /**
     * Represents the East direction.
     */
    EAST("East", 'E'),

    /**
     * Represents the West direction.
     */
    WEST("West", 'W'),

    /**
     * Represents the Up direction.
     */
    UP("Up", 'U'),

    /**
     * Represents the Down direction.
     */
    DOWN("Down", 'D');

    /**
     * The full name of the direction.
     */
    private final String longName;

    /**
     * The single-character abbreviation of the direction.
     */
    private final char abbreviation;

    /**
     * Constructs a Direction enum with the specified long name and abbreviation.
     *
     * @param longName     the full name of the direction
     * @param abbreviation the single-character abbreviation of the direction
     */
    Direction(String longName, char abbreviation) {
        this.longName = longName;
        this.abbreviation = abbreviation;
    }

    /**
     * Get a list of all valid direction strings.
     *
     * @return a list of all valid direction strings.
     */
    public static List<String> getDirectionStrings() {
        ArrayList<String> directions = new java.util.ArrayList<>();

        for (Direction direction : Direction.values()) {
            directions.add(direction.longName);
            directions.add(String.valueOf(direction.abbreviation));
        }

        return directions;
    }

    /**
     * Gets the direction object based on the text that was entered.
     *
     * @param lexeme the string name of the direction to map to a real Direction object.
     * @return the Direction object created from the string as an Optional which will be empty if
     * the direction string could not be parsed.
     */
    public static Optional<Direction> directionFromLexeme(final String lexeme) {
        // Just to be safe...
        var string = lexeme.trim().toUpperCase();

        // Search through ALL lexemes looking for a match with early bailout.
        for (var Direction : Direction.values()) {
            // Can use just the first few characters if desired.
            if (Direction.longName.toUpperCase().startsWith(string)) {
                return Optional.of(Direction);
            }
        }

        return Optional.empty();
    }

    /**
     * Gets the long name of a direction for display in textual descriptions.
     *
     * @return the full name of the direction
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Gets the abbreviated name for user prompts and quick entry.
     *
     * @return the abbreviated name of the direction
     */
    public char getAbbreviation() {
        return abbreviation;
    }

    /**
     * The "Long Name" is the human-readable version.
     */
    @Override
    public String toString() {
        return this.longName;
    }

}
