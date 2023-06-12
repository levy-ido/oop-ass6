import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The third level of the game. It looks similar to how the game looked in previous assignments, but feature some
 * decoration.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); ++i) {
            Velocity v = new Velocity(-0.5 + i, 2.0);
            velocities.add(v);
        }
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        ComplexSprite background = new ComplexSprite();
        double width = GameLevel.WIDTH - 2 * GameLevel.BOUND_WIDTH;
        double height = GameLevel.HEIGHT - 2 * GameLevel.BOUND_WIDTH;
        Color bgColor = Color.decode("#2A8215");
        background.add(new Background(GameLevel.BOUND_WIDTH, GameLevel.BOUND_WIDTH, width, height, bgColor));
        double x = GameLevel.WIDTH / 10.0;
        double y = (8.5 / 12.0) * GameLevel.HEIGHT;
        width = GameLevel.WIDTH / 9.0;
        height = (3.5 / 12.0) * GameLevel.HEIGHT;
        background.add(new Block(x, y, width, height, Color.WHITE));
        Color color = Color.decode("#3E3A39");
        double borderWidth = 7.0;
        background.add(new Block(x, y, borderWidth, height, color));
        background.add(new Block(x + borderWidth, y, width - 2 * borderWidth, borderWidth, color));
        background.add(new Block(x + width - borderWidth, y, borderWidth, height, color));
        for (int i = 1; i <= 4; ++i) {
            background.add(new Block(x + (width / 5.0) * i, y, 4.0, height, color));
            background.add(new Block(x, y + (height / 5.0) * i, width, 3.0, color));
        }
        double newWidth = width / 3.0;
        x = x + width / 2.0 - newWidth / 2.0;
        height = 80.0;
        y = y - height;
        background.add(new Block(x, y, newWidth, height, Color.decode("#3E3A39")));
        double newerWidth = width / 14.0;
        x = x + newWidth / 2.0 - newerWidth / 2.0;
        height = 150.0;
        y = y - height;
        background.add(new Block(x, y, newerWidth, height, Color.decode("#4E4A49")));
        Color[] colors = {Color.decode("#D8AC66"), Color.decode("#F64D36"), Color.WHITE};
        for (int i = 0; i < 3; ++i) {
            int x1 = (int) (x + newerWidth / 2.0);
            background.add(new FillDecorator(new Ring(x1, (int) (y - 12), 12 - 5 * i, colors[i])));
        }
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colorArray = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        double blockX = GameLevel.WIDTH / 3.0;
        double blockWidth = ((2.0 / 3.0) * GameLevel.WIDTH - GameLevel.BOUND_WIDTH) / 10.0;
        double blockHeight = 560.0 / 20.0;
        for (int i = 0; i < colorArray.length; ++i) {
            double y = 20.0 + 3.0 * blockHeight + i * blockHeight;
            for (int j = 0; j < 10 - i; ++j) {
                double x = blockX + j * blockWidth;
                blocks.add(new Block(x, y, blockWidth, blockHeight, colorArray[i]));
            }
            blockX += blockWidth;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 41;
    }
}
