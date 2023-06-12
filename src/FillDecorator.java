import biuoop.DrawSurface;

/**
 * Enables rings to be drawn as a circle.
 */
public class FillDecorator implements Sprite{
    private final Ring r;

    /**
     * Constructs a new FillDecorator.
     * @param r A Ring to decorate
     */
    public FillDecorator(Ring r) {
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
