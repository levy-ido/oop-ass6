/**
 * Defines the behavior of collidable objects.
 */
public interface Collidable {
    /**
     * Returns a rectangle representing this collidable's outline.
     *
     * @return A Rectangle representing this collidable's outline
     */
    Rectangle getCollisionRectangle();

    /**
     * Returns an updated velocity based on collision point and collision velocity.
     * If this collidable is a HitNotifier, notifies all listeners a hit occurred.
     *
     * @param hitter A Ball representing the ball that hit this collidable
     * @param collisionPoint  A Point representing the collision point
     * @param currentVelocity A Velocity representing the collision velocity
     * @return A new Velocity representing the updated velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}