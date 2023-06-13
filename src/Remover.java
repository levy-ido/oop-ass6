/**
 * Remover scaffolding.
 */
public abstract class Remover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter counter;

    /**
     * Constructs a new Remover with the given game level and counter.
     * @param gameLevel A GameLevel
     * @param counter A Counter
     */
    public Remover(GameLevel gameLevel, Counter counter) {
        this.gameLevel = gameLevel;
        this.counter = counter;
    }

    /**
     * Returns this remover's game level.
     * @return A GameLevel representing this remover's game level
     */
    public GameLevel getGame() {
        return this.gameLevel;
    }
    /**
     * Returns this remover's counter.
     * @return A Counter representing this remover's counter
     */
    public Counter getCounter() {
        return this.counter;
    }
}
