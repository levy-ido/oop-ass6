import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A ring.
 */
public class Ring implements Sprite {
    private int x;
    private int y;
    private final int radius;
    private final Color color;

    /**
     * Constructs a new Ring with the given parameters.
     * @param x An integer representing the new ring's center x-coordinate
     * @param y An integer representing the new ring's center y-coordinate
     * @param radius An integer representing the new ring's radius
     * @param color A Color representing the new ring's color
     */
    public Ring(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    /**
     * Returns this ring's center x-coordinate.
     * @return An integer representing this ring's center x-coordinate
     */
    public int getX() {
        return this.x;
    }
    /**
     * Returns this ring's center y-coordinate.
     * @return An integer representing this ring's center y-coordinate
     */
    public int getY() {
        return this.y;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle(this.x, this.y, this.radius);
    }

    /**
     * Returns this ring's radius.
     * @return An integer representing this ring's radius
     */
    public int getRadius() {
        return this.radius;
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }

    /**
     * Returns this ring's color.
     * @return A Color representing this ring's color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets this ring's center to a given point.
     * @param p A Point to set this ring's center to
     */
    public void setCenter(Point p) {
        this.x = (int) p.getX();
        this.y = (int) p.getY();
    }

    /**
     * Returns this ring's center.
     * @return A Point representing this ring's center
     */
    public Point getCenter() {
        return new Point(this.x, this.y);
    }
}
