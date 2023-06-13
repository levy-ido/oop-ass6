import biuoop.DrawSurface;

/**
 * A "living" game object.
 */
public interface Sprite {
    /**
     * Draws this sprite on the given draw surface.
     * @param d A DrawSurface used in drawing this sprite
     */
    void drawOn(DrawSurface d);

    /**
     * This method is responsible for bringing this sprite to life.
     */
    void timePassed();

    /**
     * Adds this sprite to the given game level.
     * @param g A GameLevel
     */
    void addToGame(GameLevel g);
}