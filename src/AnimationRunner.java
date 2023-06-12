import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * Instances of this class can run an instance of a class that implements the animation interface.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * Constructs a new AnimationRunner object with the given framesPerSecond.
     *
     * @param framesPerSecond An integer representing how many frames of the animation should the new animation runner
     *                        display per second.
     */
    public AnimationRunner(int framesPerSecond) {
        this.gui = new GUI("", 800, 600);
        if (framesPerSecond == 0) {
            this.framesPerSecond = 60;
        } else {
            this.framesPerSecond = framesPerSecond;
        }
        this.sleeper = new Sleeper();
    }

    /**
     * Runs the given animation.
     * @param animation An Animation object to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Returns this animation runner's gui's keyboard sensor.
     * @return A KeyboardSensor Object representing this animation runner's gui's keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.gui.getKeyboardSensor();
    }

    /**
     * Closes this animation runner.
     */
    public void close() {
        this.gui.close();
    }
}
