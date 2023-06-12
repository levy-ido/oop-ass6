import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * Represents a countdown animation.
 */
public class CountdownAnimation implements Animation {
    private final SpriteCollection gameScreen;
    private int count;
    private final Sleeper sleeper;
    private final long displayForMS;
    /**
     * Displays the given game screen for numOfSeconds seconds, and a countdown from countFrom to 1, where each number
     * will appear on the screen for numOfSeconds / countFrom seconds.
     * @param numOfSeconds An double representing the duration of the countdown animation.
     * @param countFrom An integer to start counting from
     * @param gameScreen A SpriteCollection object representing the game's screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
        this.count = countFrom;
        this.sleeper = new Sleeper();
        this.displayForMS = (long) ((numOfSeconds / countFrom) * 1000);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(this.count), 64);
        this.sleeper.sleepFor(this.displayForMS);
        --this.count;
    }

    @Override
    public boolean shouldStop() {
        return this.count == 0;
    }
}
