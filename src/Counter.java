/**
 * A simple counter.
 */
public class Counter {
    private int value;

    /**
     * Constructs a new Counter with the given value.
     * @param value An integer representing the new counter's value
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increases this counter's value by number.
     * @param number An integer representing the number to increase this counter's value by
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Returns this counter's value.
     * @return An integer representing this counter's value
     */
    public int getValue() {
        return this.value;
    }
}
