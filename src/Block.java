import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a block.
 */
public class Block extends ColoredRectangle implements Collidable, HitNotifier {
    private final List<HitListener> hitListeners;

    /**
     * Constructs a new Block with the given parameters.
     * @param x      A double representing the block's upper left corner x-coordinate
     * @param y      A double representing the block's upper left corner y-coordinate
     * @param width  A double representing the block's width
     * @param height A double representing the block's height
     * @param color A Color representing the blocks' color
     */
    public Block(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return super.getOutline();
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] sides = super.getSides();
        double dx = currentVelocity.getDx();
        if (sides[0].contains(collisionPoint) || sides[2].contains(collisionPoint)) {
            dx = -dx;
        }
        double dy = currentVelocity.getDy();
        if (sides[1].contains(collisionPoint) || sides[3].contains(collisionPoint)) {
            dy = -dy;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(Color.BLACK);
        Point upperLeft = super.getUpperLeft();
        int x = (int) upperLeft.getX();
        int y = (int) upperLeft.getY();
        int width = (int) super.getWidth();
        int height = (int) super.getHeight();
        d.drawRectangle(x, y, width, height);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Removes this block from the given game level.
     * @param gameLevel A GameLevel to remove this block from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all of this block's hit listeners the given ball hit this block.
     * @param hitter A Ball representing the ball that hit this block
     */
    private void notifyHit(Ball hitter) {
        this.hitListeners.forEach(hl -> hl.hitEvent(this, hitter));
    }
}
