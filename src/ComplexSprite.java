import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A Composite of sprites.
 */
public class ComplexSprite implements Sprite {
    private List<Sprite> sprites;

    /**
     * Constructs a new ComplexSprite.
     */
    public ComplexSprite() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a given sprite to this complex sprite's sprite list.
     * @param s A Sprite to add to this complex sprite's sprite list
     */
    public void add(Sprite s) {
        this.sprites.add(s);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.sprites.forEach(sprite -> sprite.drawOn(d));
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
