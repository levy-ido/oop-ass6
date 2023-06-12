/**
 * In charge of removing blocks from the game, as well as counting the blocks that remain.
 */
public class BlockRemover extends Remover {

    /**
     * Constructs a new BlockRemover with the given game and counter.
     * @param gameLevel A Game object
     * @param removedBlocks A Counter object representing the number of blocks removed
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        super(gameLevel, removedBlocks);
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(super.getGame());
        super.getCounter().increase(1);
    }
}
