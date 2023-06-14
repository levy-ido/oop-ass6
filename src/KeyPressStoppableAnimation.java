import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * An animation one can stop with a key press.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final String key;
    private final KeyboardSensor keyboardSensor;
    private final Animation animation;

    /**
     * Constructs a new KeyPressStoppableAnimation with the given key, keyboard sensor and animation.
     * @param key A String representing the key to stop this animation with
     * @param ks A KeyboardSensor
     * @param anim An Animation to decorate
     */
    public KeyPressStoppableAnimation(String key, KeyboardSensor ks, Animation anim) {
        this.key = key;
        this.keyboardSensor = ks;
        this.animation = anim;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        return this.keyboardSensor.isPressed(this.key);
    }
}
