import biuoop.DrawSurface;

/**
 * Enables rings to be drawn as circles.
 */
public class Circle implements Sprite {
    private final Ring r;

    /**
     * Constructs a new Circle with the same center, radius and color as the given ring.
     * @param r A Ring
     */
    public Circle(Ring r) {
        this.r = r;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(r.getColor());
        d.fillCircle(this.r.getX(), this.r.getY(), this.r.getRadius());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }
}
