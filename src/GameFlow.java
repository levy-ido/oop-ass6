import java.util.List;

import biuoop.GUI;

/**
 * Handles level creation and moving from one level to the next.
 */
public class GameFlow {

    /**
     * Runs the given levels sequentially.
     * @param levels A List of LevelInformations to run
     */
    public void runLevels(List<LevelInformation> levels) {
        GUI gui = new GUI("", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        Counter score = new Counter(0);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(score, animationRunner, levelInfo);
            level.initialize();
            level.run();
            if (level.isCleared()) {
                score.increase(100);
                continue;
            }
            break;
        }
        gui.close();
    }
}
