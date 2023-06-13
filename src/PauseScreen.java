import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A pause screen.
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;

    /**
     * Constructs a new PauseScreen with the given keyboard sensor.
     * @param k A KeyboardSensor to construct the new pause screen with
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(175, 300, "Paused. Press space to continue.", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.keyboard.isPressed(KeyboardSensor.SPACE_KEY);
    }
}
