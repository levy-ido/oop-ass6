import java.util.ArrayList;
import java.util.List;

/**
 * A game level's environment. Holds a collection of collidables.
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructs a new GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Adds the given collidable to this game environment.
     * @param c A Collidable to add to this game environment
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assuming an object is moving from trajectory's start to trajectory's end returns the closest point of collision
     * between the object and this game environment's collidables.
     * @param trajectory A Line representing the object's trajectory
     * @return A CollisionInfo holding information about the closest point of collision between the object and
     * this game environment's collidables. If there are no collision points on the given trajectory returns null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo minCollisionInfo = null;
        double minCollisionProximity = java.lang.Double.POSITIVE_INFINITY;
        for (Collidable collidable : this.collidables) {
            Rectangle outline = collidable.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(outline);
            if (collisionPoint == null) {
                continue;
            }
            double collisionProximity = trajectory.start().distance(collisionPoint);
            if (collisionProximity < minCollisionProximity) {
                minCollisionInfo = new CollisionInfo(collisionPoint, collidable);
                minCollisionProximity = collisionProximity;
            }
        }
        return minCollisionInfo;
    }

    /**
     * Returns this game environment's collidables.
     * @return A List of Collidables representing this game environment's collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}
