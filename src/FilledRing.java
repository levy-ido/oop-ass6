import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A filled ring.
 */
public class FilledRing extends Ring {

    /**
     * Constructs a new FilledRing with the given parameters.
     * @param x An integer representing the new filled ring's center x-coordinate
     * @param y An integer representing the new filled ring's center y-coordinate
     * @param radius An integer representing the new filled ring's radius
     * @param color An integer representing the new filled ring's color
     */
    public FilledRing(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillCircle((int) this.getX(), (int) this.getY(), this.getRadius());
    }
}
