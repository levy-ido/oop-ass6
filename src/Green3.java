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
        velocities.add(new Velocity(-0.25, 2.0));
        velocities.add(new Velocity(0.25, 2.0));
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
        building.add(new ColoredRectangle(80, 400, 90, 180, Color.WHITE));
        Color kindOfBlack = Color.decode("#3E3A39");
        building.add(new ColoredRectangle(80, 400, 7, 180, kindOfBlack));
        building.add(new ColoredRectangle(87, 400, 76, 7, kindOfBlack));
        building.add(new ColoredRectangle(163, 400, 7, 180, kindOfBlack));
        for (int i = 1; i <= 4; ++i) {
            building.add(new ColoredRectangle(80 + 18 * i, 400, 4, 280, kindOfBlack));
            building.add(new ColoredRectangle(80, 400 + 56 * i, 90, 3, kindOfBlack));
        }
        return building;
    }

    /**
     * Creates an antenna.
     * @return A ComplexSprite that looks like an antenna
     */
    private ComplexSprite createAntenna() {
        ComplexSprite antenna = new ComplexSprite();
        antenna.add(new ColoredRectangle(110, 340, 30, 60, Color.decode("#3E3A39")));
        antenna.add(new ColoredRectangle(124, 220, 2, 120, Color.decode("#4E4A49")));
        Color[] colors = {Color.decode("#D8AC66"), Color.decode("#F64D36"), Color.WHITE};
        for (int i = 0; i < 3; ++i) {
            antenna.add(new FilledRing(new Ring(125, 205, 15 - 5 * i, colors[i])));
        }
        return antenna;
    }

    @Override
    public Sprite getBackground() {
        ComplexSprite background = new ComplexSprite();
        Color bgColor = Color.decode("#2A8215");
        background.add(new ColoredRectangle(20, 20, 760, 560, bgColor));
        background.add(createBuilding());
        background.add(createAntenna());
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colorArray = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        double blockX = 300;
        for (int i = 0; i < colorArray.length; ++i) {
            for (int j = 0; j < 10 - i; ++j) {
                blocks.add(new Block(blockX + j * 48, 110 + i * 30, 48, 30, colorArray[i]));
            }
            blockX += 48;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
