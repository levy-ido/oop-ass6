import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Holds the sprites and collidables. Responsible for animating the sprites.
 */
public class GameLevel implements Animation {
    public static final int NUM_OF_BLOCKS = 57;
    private static final int NUM_OF_BALLS = 3;
    public static final double WIDTH = 800.0;
    private static final double HEIGHT = 600.0;
    public static final double BOUND_WIDTH = 20.0;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter removedBlocks;
    private final Counter removedBalls;
    private final Counter scoreCounter;
    private final AnimationRunner runner;

    /**
     * Constructs a new Game object.
     */
    public GameLevel() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.removedBlocks = new Counter(0);
        this.removedBalls = new Counter(0);
        this.scoreCounter = new Counter(0);
        this.runner = new AnimationRunner(60);
    }
    /**
     * Adds a given collidable to the games' environment.
     * @param c A Collidable representing the collidable to add to the games' environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a given sprite to the games' sprites.
     * @param s A Sprite representing the sprite to add to the games' sprites
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Creates the borders of the game.
     */
    private void createBorders() {
        double widthOfTopAndBot = WIDTH - 2 * BOUND_WIDTH;
        new Block(0.0, 0.0, BOUND_WIDTH, HEIGHT, Color.GRAY).addToGame(this);
        new Block(20.0, 0.0, widthOfTopAndBot, BOUND_WIDTH, Color.GRAY).addToGame(this);
        new Block(WIDTH - BOUND_WIDTH, 0.0, BOUND_WIDTH, HEIGHT, Color.GRAY).addToGame(this);
        Block bottomBorder = new Block(BOUND_WIDTH, HEIGHT - BOUND_WIDTH, widthOfTopAndBot, BOUND_WIDTH, Color.GRAY);
        bottomBorder.addHitListener(new BallRemover(this, this.removedBalls));
        bottomBorder.addToGame(this);
    }

    /**
     * Creates the balls of the game.
     */
    private void createBalls() {
        for (int i = 0; i < NUM_OF_BALLS; ++i) {
            Ball b = new Ball(30.0 + 40.0 * i, 300.0 - 10.0 * i, 10, Color.WHITE);
            b.setVelocity(0.0, 3.0);
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
        }
    }

    private void createPaddle() {
        KeyboardSensor keyboardSensor = runner.getKeyboardSensor();
        Paddle paddle = new Paddle(350.0, 550.0, 100.0, 30.0, Color.YELLOW, keyboardSensor);
        paddle.addToGame(this);
    }

    /**
     * Initializes this game.
     */
    public void initialize() {
        createBackground();
        createBorders();
        createBalls();
        createPaddle();
        createBlocks();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter);
        scoreIndicator.addToGame(this);
    }

    /**
     * Creates the games' background.
     */
    private void createBackground() {
        double width = WIDTH - 2 * BOUND_WIDTH;
        new Background(20.0, 20.0, width, HEIGHT - 2 * BOUND_WIDTH, Color.BLUE).addToGame(this);
    }

    /**
     * Creates the games' block patterns.
     */
    private void createBlocks() {
        Color[] colorArray = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        double blockWidth = (WIDTH - 2 * BOUND_WIDTH) / 16.0;
        double blockHeight = (HEIGHT - 2 * BOUND_WIDTH) / 20.0;
        double blockX = 20.0 + 4.0 * blockWidth;
        BlockRemover blockRemover = new BlockRemover(this, this.removedBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        for (int i = 0; i < colorArray.length; ++i) {
            double y = 20.0 + 3.0 * blockHeight + i * blockHeight;
            for (int j = 0; j < 12 - i; ++j) {
                double x = blockX + j * blockWidth;
                Block b = new Block(x, y, blockWidth, blockHeight, colorArray[i]);
                b.addHitListener(blockRemover);
                b.addHitListener(scoreTrackingListener);
                b.addToGame(this);
            }
            blockX += blockWidth;
        }
    }

    /**
     * Animates the game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
        if (this.removedBlocks.getValue() == NUM_OF_BLOCKS) {
            this.scoreCounter.increase(100);
        }
        this.runner.close();
    }

    /**
     * Removes the given collidable from this games' game environment.
     * @param c A Collidable to be removed from this games' game environment
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Removes the given sprite from this games' sprite collection.
     * @param s A Sprite to be removed from this games' sprite collection
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        KeyboardSensor keyboardSensor = this.runner.getKeyboardSensor();
        if (keyboardSensor.isPressed("p")) {
            this.runner.run(new PauseScreen(keyboardSensor));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return this.removedBlocks.getValue() == NUM_OF_BLOCKS || this.removedBalls.getValue() == NUM_OF_BALLS;
    }
}
