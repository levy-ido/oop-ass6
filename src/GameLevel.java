import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import java.util.List;

/**
 * Represents a game level.
 */
public class GameLevel implements Animation {
    public static final int BORDER_SIZE = 20;
    public static final int GL_WIDTH = AnimationRunner.GUI_WIDTH - 2 * BORDER_SIZE;
    public static final int GL_HEIGHT = AnimationRunner.GUI_HEIGHT - 2 * BORDER_SIZE;
    private final ComplexSprite sprites;
    private final GameEnvironment environment;
    private final Counter removedBlocks;
    private final Counter removedBalls;
    private final Counter scoreCounter;
    private final AnimationRunner runner;
    private final LevelInformation levelInformation;

    /**
     * Constructs a new Game object.
     * @param levelInformation A LevelInformation carrying information about the new level.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.sprites = new ComplexSprite();
        this.environment = new GameEnvironment();
        this.removedBlocks = new Counter(0);
        this.removedBalls = new Counter(0);
        this.scoreCounter = new Counter(0);
        this.runner = new AnimationRunner(60);
        this.levelInformation = levelInformation;
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
        this.sprites.add(s);
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
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < this.levelInformation.numberOfBalls(); ++i) {
            Ball b = new Ball((WIDTH / 2.0), ((2.0 / 3.0) * HEIGHT), 10, Color.WHITE);
            b.setVelocity(velocities.get(i));
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
        }
    }

    private void createPaddle() {
        KeyboardSensor keyboardSensor = runner.getKeyboardSensor();
        int paddleWidth = this.levelInformation.paddleWidth();
        int pS = this.levelInformation.paddleSpeed();
        Paddle paddle = new Paddle(350.0, 550.0, paddleWidth, 30.0, Color.YELLOW, keyboardSensor, pS);
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
        ComplexSprite infoBar = new ComplexSprite();
        infoBar.add(new Block(BOUND_WIDTH, 0.0, WIDTH - 2 * BOUND_WIDTH, BOUND_WIDTH, Color.GRAY));
        int x = (int) (WIDTH / 4.0);
        int y = (int) ((BOUND_WIDTH / 5.0) * 4.0);
        Text scoreText = new Text(null, Color.WHITE, x, y, 20);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter, scoreText);
        scoreIndicator.addToGame(this);
        infoBar.add(scoreIndicator);
        x = 2 * x;
        Text nameText = new Text("Level Name: " + this.levelInformation.levelName(), Color.WHITE, x, y, 20);
        infoBar.add(nameText);
        this.addSprite(infoBar);
    }

    /**
     * Creates the games' background.
     */
    private void createBackground() {
        this.levelInformation.getBackground().addToGame(this);
    }

    /**
     * Creates the games' block patterns.
     */
    private void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, this.removedBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.scoreCounter);
        for (Block b:this.levelInformation.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTrackingListener);
            b.addToGame(this);
        }
    }

    /**
     * Animates the game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.runner.run(this);
        if (this.removedBlocks.getValue() == this.levelInformation.numberOfBlocksToRemove()) {
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
        boolean levelCleared = this.removedBlocks.getValue() == this.levelInformation.numberOfBlocksToRemove();
        boolean noBallsLeft = this.removedBalls.getValue() == this.levelInformation.numberOfBalls();
        return levelCleared || noBallsLeft;
    }
}
