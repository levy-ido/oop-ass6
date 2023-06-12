import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The first level of the game. This level features a single block you need to destroy to clear it.
 */
public class DirectHit implements LevelInformation {
    private static final double SIDE_LEN = 50.0;
    private static final double X = GameLevel.WIDTH / 2.0 - SIDE_LEN / 2;
    private static final double Y = GameLevel.HEIGHT / 3.0 - SIDE_LEN / 2;
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0.0, 3.0));
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
        double width = GameLevel.WIDTH - 2 * GameLevel.BOUND_WIDTH;
        double height = GameLevel.HEIGHT - 2 * GameLevel.BOUND_WIDTH;
        background.add(new Background(GameLevel.BOUND_WIDTH, GameLevel.BOUND_WIDTH, width, height, Color.BLACK));
        int centerX = (int) (X + SIDE_LEN / 2);
        int centerY = (int) (Y + SIDE_LEN / 2);
        for (int i = 0; i < 3; ++i) {
            background.add(new Ring(centerX, centerY, 100 + 50 * i, Color.BLUE));
        }
        double x1 = GameLevel.WIDTH / 2.0;
        background.add(new Line(x1, 0.0, x1, centerY + 200.0, Color.BLUE));
        background.add(new Line(200.0, centerY, 600.0, centerY, Color.BLUE));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(X, Y, SIDE_LEN, SIDE_LEN, Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
