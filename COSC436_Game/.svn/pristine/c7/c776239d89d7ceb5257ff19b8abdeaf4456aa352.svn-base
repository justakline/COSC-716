package objectAdventure.core.item.SecretTestingItem;

import objectAdventure.common.Utils;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The TalkingMap class is responsible for periodically displaying random jokes from a predefined list.
 */
public class TalkingMap {

    // Array of predefined jokes
    static private final String[] JOKES = {
            "V guvax V'z ybfg!",
            "V fjrne, V fnj n qentba nebhaq urer fbzrjurer... be jnf vg n fdhveery?",
            "Ner jr gurer lrg? Bu jnvg, V'z gur znc!",
            "Lbh xabj, sbyqvat zr onpx hc pbeerpgyl vf na nqiragher va vgfrys.",
            "Vs lbh trg ybfg, qba'g oynzr zr. V'z whfg n cvrpr bs cncre!",
            "Jul qb lbh xrrc gheavat zr hcfvqr qbja? Abegu vf hc!",
            "V unir n terng frafr bs qverpgvba... sbe fbzrbar jub pna'g zbir.",
            "Vs lbh frr n gernfher purfg, vg'f cebonoyl n genc. Whfg fnlvat.",
            "V ubcr lbh oebhtug fanpxf. Guvf wbhearl ybbxf ybat.",
            "Erzrzore, K znexf gur fcbg. Hayrff vg'f n glcb.",
            "Vs lbh svaq n fubegphg, yrg zr xabj. V pbhyq hfr n oernx.",
            "Lbh guvax anivtngvat vf uneq? Gel orvat n znc!",
            "Vs lbh frr n fvta gung fnlf 'Lbh ner urer', qba'g oryvrir vg."
    };
    // Random object to select jokes randomly
    private final Random rnd = new Random();

    // LinkedList to keep track of the last 5 jokes displayed
    private final LinkedList<String> recentJokes = new LinkedList<>();
    // Timer to schedule the joke display task
    private Timer timer;

    /**
     * Selects a unique joke that has not been displayed in the last 5 iterations.
     *
     * @return A unique joke string.
     */
    private String getUniqueJoke() {
        String joke;

        // Loop until a unique joke is found
        do {
            joke = JOKES[rnd.nextInt(JOKES.length)];
        } while (recentJokes.contains(joke));

        // Add the joke to the list of last jokes
        recentJokes.add(joke);

        // Ensure the list size does not exceed half the number of jokes
        if (recentJokes.size() > (JOKES.length / 2)) {
            recentJokes.removeFirst();
        }

        return Utils.rot13string(joke);
    }


    /**
     * Creates a TimerTask that prints a unique joke to the console.
     *
     * @return A TimerTask that prints a unique joke.
     */
    TimerTask getTask() {
        return new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nYour Map Says: " + getUniqueJoke());
            }
        };
    }

    /**
     * Starts the timer to display jokes at fixed intervals.
     */
    void startTalking() {
        if (timer != null) timer.cancel();
        timer = new Timer("GameMap_Talker");
        timer.schedule(getTask(), 1_000, 30_000);
    }

    /**
     * Stops the timer and cancels the joke display task.
     */
    void stopTalking() {
        if (timer != null)
            timer.cancel();
    }
}
