import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The third level of the game. It looks similar to how the game looked in previous assignments, but features some
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

    /**
     * Creates a building.
     * @return A ComplexSprite that looks like a building
     */
    private ComplexSprite createBuilding() {
        ComplexSprite building = new ComplexSprite();
        double x = AnimationRunner.GUI_WIDTH / 10.0;
        double y = (8.5 / 12.0) * AnimationRunner.GUI_HEIGHT;
        double width = AnimationRunner.GUI_WIDTH / 9.0;
        double height = (3.5 / 12.0) * AnimationRunner.GUI_HEIGHT;
        building.add(new Background(x, y, width, height, Color.WHITE));
        Color color = Color.decode("#3E3A39");
        double outerSize = 7.0;
        building.add(new Background(x, y, outerSize, height, color));
        building.add(new Background(x + outerSize, y, width - 2 * outerSize, outerSize, color));
        building.add(new Background(x + width - outerSize, y, outerSize, height, color));
        double innerSizeV = 4.0;
        double innerSizeH = 3.0;
        for (int i = 1; i <= 4; ++i) {
            building.add(new Background(x + (width / 5.0) * i, y, innerSizeV, height, color));
            building.add(new Background(x, y + (height / 5.0) * i, width, innerSizeH, color));
        }
        return building;
    }

    /**
     * Creates an antenna.
     * @return A ComplexSprite that looks like an antenna
     */
    private ComplexSprite createAntenna() {
        ComplexSprite antenna = new ComplexSprite();
        double buildingX = AnimationRunner.GUI_WIDTH / 10.0;
        double buildingWidth = AnimationRunner.GUI_WIDTH / 9.0;
        double antennaHolderWidth = buildingWidth / 3.0;
        double x = buildingX + buildingWidth / 2.0 - antennaHolderWidth / 2.0;
        double buildingY = (8.5 / 12.0) * AnimationRunner.GUI_HEIGHT;
        double y = buildingY - buildingX;
        antenna.add(new Background(x, y, antennaHolderWidth, buildingX, Color.decode("#3E3A39")));
        double antennaWidth = antennaHolderWidth / 14.0;
        x = x + antennaHolderWidth / 2.0 - antennaWidth / 2.0;
        double antennaHeight = AnimationRunner.GUI_HEIGHT / 4.0;
        y = y - antennaHeight;
        antenna.add(new Background(x, y, antennaWidth, antennaHeight, Color.decode("#4E4A49")));
        Color[] colors = {Color.decode("#D8AC66"), Color.decode("#F64D36"), Color.WHITE};
        for (int i = 0; i < 3; ++i) {
            int x1 = (int) (x + antennaWidth / 2.0);
            antenna.add(new Circle(new Ring(x1, (int) (y - INIT_LIGHT_RAD), INIT_LIGHT_RAD - D * i, colors[i])));
        }
        return antenna;
    }

    @Override
    public Sprite getBackground() {
        ComplexSprite background = new ComplexSprite();
        Color bgColor = Color.decode("#2A8215");
        background.add(
                new Background(
                        GameLevel.BORDER_SIZE,
                        GameLevel.BORDER_SIZE,
                        GameLevel.GL_WIDTH,
                        GameLevel.GL_HEIGHT,
                        bgColor)
                );
        background.add(createBuilding());
        background.add(createAntenna());
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colorArray = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        double blockX = AnimationRunner.GUI_WIDTH / 3.0;
        double blockWidth = ((2.0 / 3.0) * AnimationRunner.GUI_WIDTH - GameLevel.BORDER_SIZE) / 10.0;
        double blockHeight = GameLevel.GL_HEIGHT / 20.0;
        for (int i = 0; i < colorArray.length; ++i) {
            double y = GameLevel.BORDER_SIZE + 3.0 * blockHeight + i * blockHeight;
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
        return 40;
    }
}
