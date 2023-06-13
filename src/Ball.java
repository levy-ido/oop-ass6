import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Represents a ball.
 */
public class Ball extends FilledRing {
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a new Ball with the given center coordinates, radius and color.
     * @param x      An integer representing the new ball's center x-coordinate
     * @param y      An integer representing the new ball's center y-coordinate
     * @param radius An integer representing the new ball's radius
     * @param color  A Color representing the new ball's color
     */
    public Ball(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    /**
     * Returns this ball's current trajectory.
     *
     * @return A Line representing this ball's current trajectory
     */
    public Line trajectory() {
        Vector trajectoryVector = new Vector(this.velocity.getDx(), this.velocity.getDy());
        double trajectoryAngle = trajectoryVector.angle();
        int x = this.getX();
        int y = this.getY();
        int r = this.getRadius();
        double trajectoryEndX = x + Math.cos(trajectoryAngle) * r;
        double trajectoryEndY = y + Math.sin(trajectoryAngle) * r;
        return new Line(x, y, trajectoryEndX, trajectoryEndY);
    }

    /**
     * If the ball hits a Collidable, its velocity is reversed in the corresponding direction.
     * Moves the ball one step according to its velocity
     */
    public void moveOneStep() {
        Line trajectory = this.trajectory();
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            Point collisionPoint = collisionInfo.collisionPoint();
            Collidable collidedWith = collisionInfo.collisionObject();
            this.velocity = collidedWith.hit(this, collisionPoint, this.velocity);
        }
        this.setCenter(this.velocity.applyToPoint(this.getCenter()));
    }

    /**
     * Sets this ball's game environment to the given game environment.
     *
     * @param gameEnvironment A GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        new Ring(this.getX(), this.getY(), this.getRadius(), Color.BLACK).drawOn(d);
    }
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removes this ball from the given game level.
     * @param g A GameLevel
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Sets this ball's velocity to a given velocity.
     *
     * @param velocity A Velocity to set this ball's velocity to
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }
}