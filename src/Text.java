import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A simple text object.
 */
public class Text implements Sprite {
    private String text;
    private final Color textColor;
    private final int x;
    private final int y;
    private final int fontSize;

    /**
     * Constructs a new Text with the given parameters.
     * @param text A String representing the text to display
     * @param textColor A Color representing the text's color
     * @param x An integer representing the text's upper left corner x-coordinate
     * @param y An integer representing the text's upper left corner y-coordinate
     * @param fontSize An integer specifying the text's font size
     */
    public Text(String text, Color textColor, int x, int y, int fontSize) {
        this.text = text;
        this.textColor = textColor;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.textColor);
        d.drawText(this.x, this.y, this.text, this.fontSize);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }

    /**
     * Sets this text's text to the given text.
     * @param text A String to set this text's text to
     */
    public void setText(String text) {
        this.text = text;
    }
}
