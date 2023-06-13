import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

import java.util.List;

/**
 * Represents a game level.
 */
public class GameLevel implements Animation {
    private final ComplexSprite sprites;
    private final GameEnvironment environment;
    private final Counter removedBlocks;
    private final Counter removedBalls;
    private final Counter scoreCounter;
    private final AnimationRunner runner;
    private final LevelInformation levelInformation;

    /**
     * Constructs a new GameLevel object.
     * @param levelInformation A LevelInformation carrying information about the new level.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.sprites = new ComplexSprite();
        this.environment = new GameEnvironment();
        this.removedBlocks = new Counter(0);
        this.removedBalls = new Counter(0);
        this.scoreCounter = new Counter(0);
        this.runner = new AnimationRunner();
        this.levelInformation = levelInformation;
    }
    /**
     * Adds a given collidable to the levels' environment.
     * @param c A Collidable to add to the levels' environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a given sprite to the levels' sprites.
     * @param s A Sprite to add to the levels' sprites
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Creates the borders of the game.
     */
    private void createBorders() {
        new Block(0, 0, 20, 600, Color.GRAY).addToGame(this);
        new Block(20, 0, 760, 20, Color.GRAY).addToGame(this);
        new Block(780, 0, 20, 600, Color.GRAY).addToGame(this);
        Block bottomBorder = new Block(20, 580, 760, 20, Color.GRAY);
        bottomBorder.addHitListener(new BallRemover(this, this.removedBalls));
        bottomBorder.addToGame(this);
    }

    /**
     * Creates the level's balls.
     */
    private void createBalls() {
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < this.levelInformation.numberOfBalls(); ++i) {
            Ball b = new Ball(400, 400, 10, Color.WHITE);
            b.setVelocity(velocities.get(i));
            b.setGameEnvironment(this.environment);
            b.addToGame(this);
        }
    }

    /**
     * Creates the game's paddle.
     */
    private void createPaddle() {
        KeyboardSensor keyboardSensor = this.runner.getKeyboardSensor();
        int paddleWidth = this.levelInformation.paddleWidth();
        int paddleSpeed = this.levelInformation.paddleSpeed();
        int paddleX = 400 - paddleWidth / 2;
        Paddle paddle = new Paddle(paddleX, 550, paddleWidth, 30, Color.YELLOW, keyboardSensor, paddleSpeed);
        paddle.addToGame(this);
    }

    /**
     * Creates the game's info bar.
     * @return A ComplexSprite that will serve as the game's info bar.
     */
    private ComplexSprite createInfoBar() {
        ComplexSprite infoBar = new ComplexSprite();
        infoBar.add(new ColoredRectangle(20, 0, 760, 20, Color.GRAY));
        Text scoreText = new Text(null, Color.WHITE, 150, 16, 20);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter, scoreText);
        scoreIndicator.addToGame(this);
        infoBar.add(scoreIndicator);
        String text = "Level Name: " + this.levelInformation.levelName();
        Text nameText = new Text(text, Color.WHITE, 300, 16, 20);
        infoBar.add(nameText);
        return infoBar;
    }

    /**
     * Initializes this game level.
     */
    public void initialize() {
        createBackground();
        createBorders();
        createBalls();
        createPaddle();
        createBlocks();
        this.addSprite(createInfoBar());
    }

    /**
     * Creates the level's background.
     */
    private void createBackground() {
        this.levelInformation.getBackground().addToGame(this);
    }

    /**
     * Creates this levels' block patterns.
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
     * Removes the given collidable from this levels' game environment.
     * @param c A Collidable to be removed from this levels' game environment
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Removes the given sprite from this games' complex sprite's sprite list.
     * @param s A Sprite to be removed from this games' complex sprite's sprite list
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
        this.sprites.drawOn(d);
        this.sprites.timePassed();
    }

    @Override
    public boolean shouldStop() {
        boolean levelCleared = this.removedBlocks.getValue() == this.levelInformation.numberOfBlocksToRemove();
        boolean noBallsLeft = this.removedBalls.getValue() == this.levelInformation.numberOfBalls();
        return levelCleared || noBallsLeft;
    }
}
