import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The first level of the game. This level features a single block you need to destroy to clear it.
 */
public class DirectHit implements LevelInformation {
    private static final int SIDE_LEN = 50;
    private static final int BLOCK_X = AnimationRunner.GUI_WIDTH / 2 - SIDE_LEN / 2;
    private static final int BLOCK_Y = AnimationRunner.GUI_HEIGHT / 3 - SIDE_LEN / 2;
    private static final int INIT_RADIUS = 100;
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(3.0, 3.0));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        ComplexSprite background = new ComplexSprite();
        background.add(
                new Background(
                        GameLevel.BORDER_SIZE,
                        GameLevel.BORDER_SIZE,
                        GameLevel.GL_WIDTH,
                        GameLevel.GL_HEIGHT,
                        Color.BLACK)
        );
        int blockCenterX = BLOCK_X + SIDE_LEN / 2;
        int blockCenterY = BLOCK_Y + SIDE_LEN / 2;
        for (int i = 0; i < 3; ++i) {
            background.add(new Ring(blockCenterX, blockCenterY, INIT_RADIUS + i * SIDE_LEN, Color.BLUE));
        }
        background.add(new Line(blockCenterX, 0, blockCenterX, blockCenterY + 2 * SIDE_LEN, Color.BLUE));
        int x1 = blockCenterX - 2 * SIDE_LEN;
        background.add(new Line(x1, blockCenterY, blockCenterX + 2 * SIDE_LEN, blockCenterY, Color.BLUE));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(BLOCK_X, BLOCK_Y, SIDE_LEN, SIDE_LEN, Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
