/**
 * Removes balls from the game and counts remaining balls.
 */
public class BallRemover extends Remover {

    /**
     * Constructs a new BallRemover.
     * @param gameLevel A GameLevel
     * @param removedBalls A Counter representing the number of balls removed
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        super(gameLevel, removedBalls);
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Rectangle beingHitOutline = beingHit.getCollisionRectangle();
        Rectangle bottomBorderOutline = new Rectangle(20.0, 580.0, 760.0, 20.0);
        if (beingHitOutline.equals(bottomBorderOutline)) {
            hitter.removeFromGame(this.getGame());
            this.getCounter().increase(1);
        }
    }
}
