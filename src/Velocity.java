/**
 * An's velocity.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructs a new Velocity with the given dx and dy values.
     * @param dx A double representing horizontal speed
     * @param dy A double representing vertical speed
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Returns this velocity's dx.
     * @return A double representing this velocity's horizontal speed
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns this velocity's dy.
     * @return A double representing this velocity's vertical speed
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Applies this velocity to a point.
     * @param point A Point to apply this velocity to
     * @return A new Point representing the given point's location after applying this velocity to it
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + this.dx, point.getY() + this.dy);
    }

    /**
     * Constructs a new Velocity corresponding to the given angle and speed.
     * @param angle A double representing an angle in degrees relative to the positive y-axis in clockwise fashion
     * @param speed A double representing speed
     * @return A new Velocity corresponding to the given angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleInRadians = Math.toRadians(angle - 90.0);
        double dx = Math.cos(angleInRadians) * speed;
        double dy = Math.sin(angleInRadians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Returns this velocity's speed.
     * @return A double representing this velocity's speed
     */
    public double speed() {
        Point origin = new Point(0.0, 0.0);
        return origin.distance(new Point(this.dx, this.dy));
    }
}