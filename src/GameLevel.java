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
    private static final int BALL_RADIUS = 10;
    private static final int PADDLE_HEIGHT = 30;
    private static final int PADDLE_Y = AnimationRunner.GUI_HEIGHT - BORDER_SIZE - PADDLE_HEIGHT;
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
        this.runner = new AnimationRunner(60);
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
        new Block(0.0, 0.0, BORDER_SIZE, AnimationRunner.GUI_HEIGHT, Color.GRAY).addToGame(this);
        new Block(20.0, 0.0, GL_WIDTH, BORDER_SIZE, Color.GRAY).addToGame(this);
        int var = AnimationRunner.GUI_WIDTH - BORDER_SIZE;
        new Block(var, 0.0, BORDER_SIZE, AnimationRunner.GUI_HEIGHT, Color.GRAY).addToGame(this);
        var = AnimationRunner.GUI_HEIGHT - BORDER_SIZE;
        Block bottomBorder = new Block(BORDER_SIZE, var, GL_WIDTH, BORDER_SIZE, Color.GRAY);
        bottomBorder.addHitListener(new BallRemover(this, this.removedBalls));
        bottomBorder.addToGame(this);
    }

    /**
     * Creates the level's balls.
     */
    private void createBalls() {
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < this.levelInformation.numberOfBalls(); ++i) {
            double x = AnimationRunner.GUI_WIDTH / 2.0;
            double y = (2.0 / 3.0) * AnimationRunner.GUI_HEIGHT;
            Ball b = new Ball(x, y, BALL_RADIUS, Color.WHITE);
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
        int pS = this.levelInformation.paddleSpeed();
        double paddleX = AnimationRunner.GUI_WIDTH / 2.0 - paddleWidth / 2.0;
        Paddle paddle = new Paddle(paddleX, PADDLE_Y, paddleWidth, PADDLE_HEIGHT, Color.YELLOW, keyboardSensor, pS);
        paddle.addToGame(this);
    }

    /**
     * Creates the game's info bar.
     * @return A ComplexSprite that will serve as the game's info bar.
     */
    private ComplexSprite createInfoBar() {
        ComplexSprite infoBar = new ComplexSprite();
        infoBar.add(new Background(BORDER_SIZE, 0.0, GL_WIDTH, BORDER_SIZE, Color.GRAY));
        int x = AnimationRunner.GUI_WIDTH / 4;
        int y = (int) ((4.0 / 5.0) * BORDER_SIZE);
        int fontSize = 20;
        Text scoreText = new Text(null, Color.WHITE, x, y, fontSize);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.scoreCounter, scoreText);
        scoreIndicator.addToGame(this);
        infoBar.add(scoreIndicator);
        String text = "Level Name: " + this.levelInformation.levelName();
        Text nameText = new Text(text, Color.WHITE, 2 * x, y, fontSize);
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
