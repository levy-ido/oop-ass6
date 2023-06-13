import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A Sprite composite.
 */
public class ComplexSprite implements Sprite {
    private final List<Sprite> sprites;

    /**
     * Constructs a new ComplexSprite.
     */
    public ComplexSprite() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds the given sprite to this complex sprite's sprite list.
     * @param s A Sprite to add to this complex sprite's sprite list
     */
    public void add(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Returns this complex sprite's sprite list.
     * @return A List of Sprites representing this complex sprite's sprite list
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.sprites.forEach(sprite -> sprite.drawOn(d));
    }

    @Override
    public void timePassed() {
        new ArrayList<>(this.sprites).forEach(Sprite::timePassed);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}