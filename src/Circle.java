import biuoop.DrawSurface;

/**
 * Enables rings to be drawn as a circle.
 */
public class Circle implements Sprite {
    private final Ring r;
    /**
     * Constructs a new Circle.
     * @param r A Ring to decorate
     */
    public Circle(Ring r) {
        this.r = r;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.r.getColor());
        d.fillCircle(this.r.getX(), this.r.getY(), this.r.getRadius());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
    }
}
