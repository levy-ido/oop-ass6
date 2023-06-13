import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The second level the game. Features a row of blocks you need to destroy to clear the level, a wide paddle,
 * and plenty of balls.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); ++i) {
            Velocity v = new Velocity(-4.0 + i, 2.0);
            velocities.add(v);
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        ComplexSprite background = new ComplexSprite();
        background.add(
                new Background(GameLevel.BORDER_SIZE,
                        GameLevel.BORDER_SIZE,
                        GameLevel.GL_WIDTH,
                        GameLevel.GL_HEIGHT,
                        Color.WHITE)
        );
        List<Color> colors = new ArrayList<>();
        colors.add(Color.decode("#EFE7B0"));
        for (int i = 0; i < RAY_NUM; ++i) {
            background.add(new Line(SUN_C, SUN_C, RAY_D * i, BLOCK_Y, colors.get(0)));
        }
        colors.add(Color.decode("#ECD749"));
        colors.add(Color.decode("#FFE118"));
        for (int i = 0; i < 3; ++i) {
            background.add(new Circle(new Ring(SUN_C, SUN_C, SUN_RADIUS - SUN_D * i, colors.get(i))));
        }
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double blockWidth = GameLevel.GL_WIDTH / 15.0;
        for (int i = 0; i < this.numberOfBlocksToRemove(); ++i) {
            double x = GameLevel.BORDER_SIZE + blockWidth * i;
            Color color = WideEasy.getColor(i);
            blocks.add(new Block(x, BLOCK_Y, blockWidth, BLOCK_H, color));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    /**
     * Returns the color of the ith block.
     * @param i An integer representing a block's index
     * @return A Color representing the color of the ith block
     */
    private static Color getColor(int i) {
        if (i < 2) {
            return Color.RED;
        }
        if (i < 4) {
            return Color.ORANGE;
        }
        if (i < 6) {
            return Color.YELLOW;
        }
        if (i < 9) {
            return Color.GREEN;
        }
        if (i < 11) {
            return Color.BLUE;
        }
        if (i < 13) {
            return Color.PINK;
        }
        if (i < 15) {
            return Color.CYAN;
        }
        return null;
    }
}
