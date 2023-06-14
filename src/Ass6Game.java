import java.util.ArrayList;
import java.util.List;

/**
 * The product of assignment 6.
 */
public class Ass6Game {
    /**
     * The entry point of the program.
     * @param args A String[]. Ignored
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(levels);
    }
}
