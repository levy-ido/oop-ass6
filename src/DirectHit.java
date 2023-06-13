import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The first level of the game. This level features a single block you need to destroy to clear it.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, 3));
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
        background.add(new ColoredRectangle(20, 20, 760, 560, Color.BLACK));
        for (int i = 0; i < 3; ++i) {
            background.add(new Ring(400, 200, 100 + 50 * i, Color.BLUE));
        }
        background.add(new Line(400, 0, 400, 400, Color.BLUE));
        background.add(new Line(200, 200, 600, 200, Color.BLUE));
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(375, 175, 50, 50, Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
