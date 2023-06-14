import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A simple text object.
 */
public class Text implements Sprite {
    private String content;
    private final Color color;
    private final int x;
    private final int y;
    private final int fontSize;

    /**
     * Constructs a new Text with the given parameters.
     * @param content A String representing the text to display
     * @param color A Color representing the text's color
     * @param x An integer representing the text's upper left corner x-coordinate
     * @param y An integer representing the text's upper left corner y-coordinate
     * @param fontSize An integer specifying the text's font size
     */
    public Text(String content, Color color, int x, int y, int fontSize) {
        this.content = content;
        this.color = color;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawText(this.x, this.y, this.content, this.fontSize);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }

    /**
     * Sets this text's content to the given string.
     * @param content A String to set this text's content to
     */
    public void setContent(String content) {
        this.content = content;
    }
}
