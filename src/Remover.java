/**
 * A base class.
 */
public abstract class Remover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter counter;

    /**
     * Constructs a new Remover with the given game and counter.
     * @param gameLevel A Game object
     * @param counter A Counter object
     */
    public Remover(GameLevel gameLevel, Counter counter) {
        this.gameLevel = gameLevel;
        this.counter = counter;
    }

    /**
     * Returns this removers' game.
     * @return A Game object representing this removers' game
     */
    public GameLevel getGame() {
        return this.gameLevel;
    }
    /**
     * Returns this removers' counter.
     * @return A Counter object representing this removers' counter
     */
    public Counter getCounter() {
        return this.counter;
    }
}
