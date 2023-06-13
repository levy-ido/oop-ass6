import java.util.ArrayList;

/**
 * A rectangle.
 */
public class Rectangle {

    private Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructs a new Rectangle with a given point, width and height.
     * @param upperLeft A Point representing the rectangle's upper left corner
     * @param width     A double representing the rectangle's width
     * @param height    A double representing the rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a new Rectangle with the given parameters.
     * @param x      A double representing the rectangle's upper left corner x-coordinate
     * @param y      A double representing the rectangle's upper left corner y-coordinate
     * @param width  A double representing the rectangle's width
     * @param height A double representing the rectangle's height
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * Returns this rectangle's upper right corner.
     * @return A Point representing this rectangle's upper right corner
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Returns this rectangle's bottom right corner.
     * @return A Point representing this rectangle's bottom right corner
     */
    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Returns this rectangle's bottom left corner.
     * @return A Point representing this rectangle's bottom left corner
     */
    public Point getBottomLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * Returns a line segment representing this rectangle's left side.
     * @return A Line representing this rectangle's left side
     */
    public Line getLeftSide() {
        return new Line(this.upperLeft, this.getBottomLeft());
    }

    /**
     * Returns a line segment representing this rectangle's top side.
     * @return A Line representing this rectangle's top side
     */
    public Line getTopSide() {
        return new Line(this.upperLeft, this.getUpperRight());
    }

    /**
     * Returns a line segment representing this rectangle's right side.
     * @return A Line representing this rectangle's right side
     */
    public Line getRightSide() {
        return new Line(this.getUpperRight(), this.getBottomRight());
    }

    /**
     * Returns a line segment representing this rectangle's bottom side.
     * @return A Line representing this rectangle's bottom side
     */
    public Line getBottomSide() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }

    /**
     * Returns an array whose elements are this rectangle's sides.
     * @return A Line array representing this rectangle's sides
     */
    public Line[] getSides() {
        return new Line[]{this.getLeftSide(), this.getTopSide(), this.getRightSide(), this.getBottomSide()};
    }

    /**
     * Returns a list of intersection points between this rectangle and a given line segment.
     * @param line A Line representing the given line segment
     * @return A List of Points representing the list of intersection points between this rectangle and the given line
     * segment
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPoints = new ArrayList<>();
        for (Line side : this.getSides()) {
            Point intersectionPoint = line.intersectionWith(side);
            if (intersectionPoint != null && !intersectionPoints.contains(intersectionPoint)) {
                intersectionPoints.add(intersectionPoint);
            }
        }
        return intersectionPoints;
    }

    /**
     * Returns this rectangle's width.
     * @return A double representing this rectangle's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns this rectangle's height.
     * @return A double representing this rectangle's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns this rectangle's upper left corner.
     * @return A Point representing this rectangle's upper left corner
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Sets this rectangle's upper left corner to a given point.
     * @param upperLeft A Point to set this rectangle's upper left corner to
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    @Override
    public boolean equals(Object obj) {
        Rectangle rect = (Rectangle) obj;
        return this.upperLeft.equals(rect.upperLeft) && this.width == rect.width && this.height == rect.height;
    }
}
