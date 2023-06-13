import biuoop.DrawSurface;

/**
 * Represents an animation.
 */
public interface Animation {
    /**
     * Draws one frame of the animation on the given draw surface.
     * @param d A DrawSurface object
     */
    void doOneFrame(DrawSurface d);

    /**
     * Defines the stop condition of the animation.
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
