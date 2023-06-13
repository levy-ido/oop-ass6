/**
 * Objects implementing this interface listen for hit events.
 */
public interface HitListener {
    /**
     * This method defines this hit listener's behavior when it is notified of a hit event.
     * @param beingHit A Block representing the block that was hit
     * @param hitter A Ball representing the ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
