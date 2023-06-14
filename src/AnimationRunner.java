import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * Instances of this class can run an animation.
 */
public class AnimationRunner {
    private static final Sleeper SLEEPER = new Sleeper();
    private static final int FPS = 60;
    private final GUI gui;

    /**
     * Constructs a new AnimationRunner.
     * @param gui A GUI
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
    }

    /**
     * Runs the given animation.
     * @param animation An Animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / FPS;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                SLEEPER.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Returns this animation runner's gui's keyboard sensor.
     * @return A KeyboardSensor representing this animation runner's gui's keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.gui.getKeyboardSensor();
    }
}
