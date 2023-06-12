import java.util.List;

/**
 * Specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     * @return An integer representing the number of balls in the level
     */
    int numberOfBalls();

    /**
     * Returns a list of the initial ball velocities of the level.
     * @return A List of Velocity objects representing the initial ball velocities of the level.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the level's paddle speed.
     * @return An integer representing the level's paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the level's paddle width.
     * @return An integer representing the level's paddle width
     */
    int paddleWidth();

    /**
     * Returns the level's name.
     * @return A String representing the level's name
     */
    String levelName();

    /**
     * Returns the level's background.
     * @return A Sprite representing the level's background
     */
    Sprite getBackground();

    /**
     * Returns a list of the level's blocks.
     * @return A List of Block objects representing the level's blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks to remove to clear the level.
     * @return An integer representing the number of blocks to remove to clear the level
     */
    int numberOfBlocksToRemove();
}