import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A simple text object.
 */
public class Text implements Sprite {
    private String text;
    private final Color color;
    private final int x;
    private final int y;
    private final int fontSize;

    /**
     * Constructs a new Text object with the given text.
     * @param text A String representing the text to display
     * @param color A Color representing the color to draw the text with
     * @param x An integer representing the text's upper-left corner x-coordinate
     * @param y An integer representing the text's upper-left corner y-coordinate
     * @param fontSize An integer specifying the text's font size
     */
    public Text(String text, Color color, int x, int y, int fontSize) {
        this.text = text;
        this.color = color;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawText(this.x, this.y, this.text, this.fontSize);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }

    /**
     * Sets this Text's text to the give text.
     * @param text A String to set this Text's text to
     */
    public void setText(String text) {
        this.text = text;
    }
}
