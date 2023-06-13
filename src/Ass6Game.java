/**
 * The product of assignment 6.
 */
public class Ass6Game {
    /**
     * The entry point of the program.
     * @param args A String[]. Ignored
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel(new WideEasy());
        gameLevel.initialize();
        gameLevel.run();
    }
}
