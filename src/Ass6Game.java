import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The product of assignment 6.
 */
public class Ass6Game {
    /**
     * The entry point of the program.
     * @param args A String[]. If no arguments are present runs levels 1, 2 and 3 sequentially, otherwise runs
     *             levels according to specification.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = parseArgs(args);
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(levels);
    }

    /**
     * Parses args and returns a fitting level information list.
     * @param args A String[]
     * @return A LevelInformation list
     */
    private static List<LevelInformation> parseArgs(String[] args) {
        LevelInformation[] levelsA = {new DirectHit(), new WideEasy(), new Green3()};
        List<LevelInformation> levelsL = new ArrayList<>();
        for (String s : args) {
            if (s.length() != 1) {
                continue;
            }
            char c = s.charAt(0);
            if (c < '1' || c > '3') {
                continue;
            }
            levelsL.add(levelsA[Integer.parseInt(s) - 1]);
        }
        if (levelsL.isEmpty()) {
            return new ArrayList<>(Arrays.asList(levelsA));
        }
        return levelsL;
    }
}
