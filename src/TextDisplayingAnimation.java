import biuoop.DrawSurface;

/**
 * A screen that displays text.
 */
public class TextDisplayingAnimation implements Animation {
    private final Text text;
    /**
     * Constructs a new TextDisplayingAnimation with the given text.
     * @param text A Text representing the text to display
     */
    public TextDisplayingAnimation(Text text) {
        this.text = text;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.text.drawOn(d);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
