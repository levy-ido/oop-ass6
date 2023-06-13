import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * A keyboard controlled paddle.
 */
public class Paddle extends ColoredRectangle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final int speed;

    /**
     * Constructs a new paddle with the given parameters.
     * @param x A double representing the paddle's upper left corner x-coordinate
     * @param y A double representing the paddle's upper left corner y-coordinate
     * @param width A double representing the paddle's width
     * @param height A double representing the paddle's height
     * @param color A Color representing the paddle's color
     * @param kS A KeyboardSensor essential for moving the paddle
     * @param speed An integer representing the paddle's speed
     */
    public Paddle(double x, double y, double width, double height, Color color, KeyboardSensor kS, int speed) {
        super(x, y, width, height, color);
        this.keyboard = kS;
        this.speed = speed;
    }

    /**
     * Moves this paddle the specified distance horizontally.
     * @param moveLen A double representing the distance to move this paddle by
     */
    private void move(double moveLen) {
        Point upperLeft = this.getUpperLeft();
        this.setUpperLeft(new Point(upperLeft.getX() + moveLen, upperLeft.getY()));
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        if (this.getUpperLeft().getX() > 20) {
            this.move(-this.speed);
        }
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        if (this.getUpperLeft().getX() + this.getWidth() < 780) {
            this.move(this.speed);
        }
    }
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        Point upperLeft = this.getUpperLeft();
        int x = (int) upperLeft.getX();
        int y = (int) upperLeft.getY();
        int width = (int) this.getWidth();
        int height = (int) this.getHeight();
        new Block(x, y, width, height, this.getColor()).drawOn(d);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.getOutline();
    }

    /**
     * Divides this paddle's top side into 5 equally long segments.
     * @return A Line array representing this paddle's top side divided into 5 equally long segments.
     */
    private Line[] segment() {
        Point upperLeft = this.getUpperLeft();
        Line[] segments = new Line[5];
        double segmentLength = this.getWidth() / 5;
        for (int i = 0; i < 5; ++i) {
            double segmentStartX = upperLeft.getX() + i * segmentLength;
            Point segmentStart = new Point(segmentStartX, upperLeft.getY());
            Point segmentEnd = new Point(segmentStartX + segmentLength, upperLeft.getY());
            segments[i] = new Line(segmentStart, segmentEnd);
        }
        return segments;

    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] segments = this.segment();
        for (int i = 0; i < 5; ++i) {
            if (i == 2) {
                continue;
            }
            if (segments[i].contains(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(-60.0 + i * 30.0, currentVelocity.speed());
            }
        }
        Point upperLeft = this.getUpperLeft();
        Block b = new Block(upperLeft.getX(), upperLeft.getY(), this.getWidth(), this.getHeight(), this.getColor());
        return b.hit(hitter, collisionPoint, currentVelocity);
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
