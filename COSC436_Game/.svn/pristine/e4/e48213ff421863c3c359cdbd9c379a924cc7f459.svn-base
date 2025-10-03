package objectAdventure.common;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Utility class providing various helper methods for string manipulation, text formatting,
 * list concatenation, image handling, and more.
 */
public final class Utils {

    // The scaling method to use when scaling images.
    private static final int SCALING_METHOD = Image.SCALE_SMOOTH;

    /**
     * Private constructor to prevent instantiation.
     */
    private Utils() {
        // Prevent instantiation, this is a utility class.
    }

    /**
     * Capitalize the first letter of the string.
     *
     * @param str The string to capitalize
     * @return The capitalized string
     */
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            if (str.length() == 1) {
                return str.toUpperCase();
            } else {
                return str.substring(0, 1).toUpperCase() + str.substring(1);
            }
        }
    }

    /**
     * Concatenate a list of lists into a single list.
     *
     * @param lists The lists to concatenate
     * @param <T>   The type of the list
     * @return The concatenated list
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> List<T> concatLists(List<? extends T>... lists) {
        return Stream.of(lists).flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Wrap text to a specified width.
     *
     * @param text  The text to wrap.
     * @param width The width of the wrapped text.
     * @return The wrapped text.
     * @see #wrapText(String, int, boolean)
     */
    public static String wrapText(String text, final int width) {
        return wrapText(text, width, true);
    }

    /**
     * Wrap input text to a specified width.
     *
     * <p>
     * Wraps input text to a specified width using an eager wrapping algorithm. An eager
     * wrapping algorithm wraps text as soon as the next word exceeds the given width,
     * ensuring lines are kept within the specified limit. Optionally, it can removeExtraSpaces
     * the text by removing existing newlines and extra spaces before performing the wrapping.
     *
     * <p>
     * NOTE: Algorithms, which attempt to keep the text as "fully justified" as possible,
     * but those are complex. I actually had to write code to do this in
     * <a href="https://en.wikipedia.org/wiki/Zilog_Z80">Z80</a> assembly in the late 1990s…
     * fun times! (If you google for "TI-73 math mastery modules", you can probably find
     * screenshots from that project.)
     *
     * @param text              The text to be wrapped.
     * @param width             The maximum width of the wrapped text.
     * @param removeExtraSpaces If true, removes existing newlines and extra spaces before wrapping.
     * @return The text wrapped within the specified width.
     */
    public static String wrapText(String text, final int width, final boolean removeExtraSpaces) {
        // Get rid of new-lines and extra spaces.
        if (removeExtraSpaces) {
            text = removeExtraWhiteSpace(text);
        }

        // String builder to hold the wrapped text.
        StringBuilder sb = new StringBuilder();

        // Split the text into words.
        String[] words = text.split("\\s");
        int col = 0;

        for (String word : words) {
            // If adding the next word would exceed the width, start a new line.
            if (col + word.length() > width) {
                sb.append('\n');
                col = 0;
            }

            // Add a space if this is not the start of a new line.
            if (col > 0) {
                sb.append(' ');
                col++;
            }

            // Add the word to the string builder.
            sb.append(word);
            col += word.length();
        }

        // Return the wrapped text.
        return sb.toString();
    }

    /**
     * Removes extra whitespace and newlines from the given text, replacing them with a single space.
     *
     * @param text The input string from which extra whitespace and newlines will be removed.
     * @return A string with all extra whitespace and newlines replaced by a single space.
     */
    private static String removeExtraWhiteSpace(String text) {
        text = text.replaceAll("\\s+", " ");
        return text;
    }


    /**
     * Display an image in a popup window.
     *
     * @param title     The title of the popup window
     * @param imagePath The path to the image resource
     */
    public static void displayImagePopup(String title, String imagePath) {

        // Check for headless mode.
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("Sorry, you cannot display images in a headless environment. ");
            return;
        }

        // Load the image from the resource.
        try (InputStream imageStream = Utils.class.getResourceAsStream(imagePath)) {

            // Check if the image resource was found.
            if (imageStream == null) {
                System.err.println("Image resource not found: " + imagePath);
                return;
            }

            // Set the look and feel to the system look and feel.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Read the image from the stream.
            final Image image = ImageIO.read(imageStream);

            // Create a new JFrame.
            final JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Create a new JLabel to display the image.
            final JLabel imageLabel = new JLabel();
            frame.add(imageLabel, BorderLayout.CENTER);

            // Get the dimensions of the screen.
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int maxImageWidth = (int) (screenSize.getWidth() * 0.75);
            int maxImageHeight = (int) (screenSize.getHeight() * 0.75);

            // Set the image to the proper scale.
            final Image scaledImage = getSizeBoundedImage(image, maxImageWidth, maxImageHeight);
            final ImageIcon icon = new ImageIcon(scaledImage);

            // Set the image icon
            imageLabel.setIcon(icon);

            // Add a component listener to resize the image when the frame is resized.
            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    // Get the new size of the frame.
                    int width = imageLabel.getWidth();
                    int height = imageLabel.getHeight();

                    // Scale the image to the new size.
                    Image resizedImage = image.getScaledInstance(width, height, SCALING_METHOD);

                    icon.setImage(resizedImage);
                }
            });


            // Pack the frame and display it.
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setAlwaysOnTop(true);
            frame.requestFocus();

        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath);
        }
    }


    /**
     * Scale an image to fit within the specified dimensions.
     *
     * @param image     The image to scale
     * @param maxWidth  The maximum width of the scaled image
     * @param maxHeight The maximum height of the scaled image
     * @return The scaled image
     */
    static Image getSizeBoundedImage(Image image, int maxWidth, int maxHeight) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        // Determine the scale factor to fit the image within the specified dimensions.
        double scaleFactor = Math.min((double) maxWidth / width, (double) maxHeight / height);

        // Calculate the new dimensions for the scaled image.
        int newWidth = (int) (width * scaleFactor);
        int newHeight = (int) (height * scaleFactor);

        // Create a new image with the scaled dimensions.
        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }


    /**
     * Wrap input text in an ASCII generated box.
     *
     * @param text The text to wrap
     * @return The wrapped text in an ASCII box
     */
    public static String boxifyText(String text) {
        return boxifyText(text, '+', '-', '|', 1);
    }

    /**
     * Wrap input text in an ASCII-generated box with centered lines.
     *
     * @param text       The text to wrap
     * @param corner     The character for the corners of the box
     * @param horizontal The character for the top and bottom borders
     * @param vertical   The character for the side borders
     * @param padding    The padding around the text
     * @return The wrapped text in an ASCII box
     */
    public static String boxifyText(String text, char corner, char horizontal, char vertical, int padding) {
        // Split the text into lines to handle multiline input
        String[] lines = text.split("\n");

        // Find the longest line to determine the width of the box
        int maxLineLength = Arrays.stream(lines).mapToInt(String::length).max().orElse(0);

        // Create the top and bottom of the box
        String horizontalLine = String.valueOf(horizontal).repeat(maxLineLength + padding * 2);
        String top = corner + horizontalLine + corner;
        String bottom = corner + horizontalLine + corner;

        // Create the padded and centered middle of the box
        StringBuilder middleBuilder = new StringBuilder();
        String padString = " ".repeat(padding);

        // Center each line in the box
        for (String line : lines) {
            int totalPadding = maxLineLength - line.length();
            int leftPadding = totalPadding / 2;
            int rightPadding = totalPadding - leftPadding;
            String centeredLine = " ".repeat(leftPadding) + line + " ".repeat(rightPadding);
            middleBuilder.append("%s%s%s%s%s%n".formatted(vertical, padString, centeredLine, padString, vertical));
        }

        // Return the full boxed text
        return "%s%n%s%s".formatted(top, middleBuilder.toString(), bottom);
    }

    /**
     * Applies ROT13 transformation to a given string.
     *
     * @param input The input string to transform.
     * @return The ROT13 transformed string.
     */
    public static String rot13string(String input) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            // If the character is a lowercase letter
            if (c >= 'a' && c <= 'z') {
                result.append(getRot13Char('a', c));
            }

            // If the character is an uppercase letter
            else if (c >= 'A' && c <= 'Z') {
                result.append(getRot13Char('A', c));
            }

            // If the character is neither, append it as is
            else {
                result.append(c);
            }
        }
        return result.toString();
    }


    /**
     * Applies ROT13 transformation to baseChar given character.
     *
     * @param baseChar     The base character to use for transformation.
     * @param charToRotate The character to transform.
     * @return The ROT13 transformed character.
     */
    private static char getRot13Char(char baseChar, char charToRotate) {
        return (char) (baseChar + (charToRotate - baseChar + 13) % 26);
    }
}
