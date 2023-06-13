import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A colored rectangle.
 */
public class ColoredRectangle extends Rectangle implements Sprite {
    private final Color color;

    /**
     * Constructs a new ColoredRectangle with the given parameters.
     * @param x A double representing the colored rectangles' upper left corner x-coordinate
     * @param y A double representing the colored rectangles' upper left corner y-coordinate
     * @param width A double representing the colored rectangles' width
     * @param height A double representing the colored rectangles' height
     * @param color A Color object representing the colored rectangles' color
     */
    public ColoredRectangle(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        int width = (int) this.getWidth();
        int height = (int) this.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }

    /**
     * Returns this colored rectangle's outline.
     * @return A Rectangle representing this colored rectangle's outline
     */
    public Rectangle getOutline() {
        return new Rectangle(this.getUpperLeft(), this.getWidth(), this.getHeight());
    }

    /**
     * Returns this colored rectangle's color.
     * @return A Color representing this colored rectangle's color
     */
    public Color getColor() {
        return this.color;
    }
}
