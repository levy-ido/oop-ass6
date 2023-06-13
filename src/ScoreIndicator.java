import biuoop.DrawSurface;

/**
 * Displays the game's score.
 */
public class ScoreIndicator implements Sprite {
    private final Counter scoreCounter;
    private final Text text;

    /**
     * Constructs a new ScoreIndicator.
     * @param scoreCounter A Counter representing the game's score
     * @param text A Text to display the game's score with
     */
    public ScoreIndicator(Counter scoreCounter, Text text) {
        this.scoreCounter = scoreCounter;
        this.text = text;
    }
    @Override
    public void drawOn(DrawSurface d) {
        text.setText("Score: " + this.scoreCounter.getValue());
        text.drawOn(d);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }
}
