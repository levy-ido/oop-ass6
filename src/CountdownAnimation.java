import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * Represents a countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final SpriteCollection gameScreen;
    private int count;
    private final Sleeper sleeper;
    private final long displayForMS;
    private final Text text;
    /**
     * Displays the given game screen for numOfSeconds seconds, and a countdown from countFrom to 1, where each number
     * will appear on the screen for numOfSeconds / countFrom seconds.
     * @param numOfSeconds A double representing the duration of the countdown animation.
     * @param countFrom An integer to start counting from
     * @param gameScreen A SpriteCollection object representing the game's screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.count = countFrom;
        this.sleeper = new Sleeper();
        this.displayForMS = (long) ((numOfSeconds / countFrom) * 1000);
        int x = (int) GameLevel.WIDTH / 2;
        int y = (int) GameLevel.HEIGHT / 2;
        this.text = new Text(null, Color.WHITE, x, y, 64);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        this.text.setText(String.valueOf(this.count));
        this.text.drawOn(d);
        this.sleeper.sleepFor(this.displayForMS);
        --this.count;
    }

    @Override
    public boolean shouldStop() {
        return this.count == 0;
    }
}
