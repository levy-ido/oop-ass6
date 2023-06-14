import java.awt.Color;

import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;

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
        boolean clearedAllLvls = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(score, animationRunner, levelInfo);
            level.initialize();
            level.run();
            if (level.isCleared()) {
                score.increase(100);
                continue;
            }
            clearedAllLvls = false;
            break;
        }
        runEndScreen(clearedAllLvls, score, animationRunner, gui);
        gui.close();
    }

    /**
     * Runs the game's end screen.
     * @param clearedAllLvls A boolean representing whether the player cleared all levels.
     * @param score A Counter representing score
     * @param ar An AnimationRunner
     * @param gui A GUI
     */
    private void runEndScreen(boolean clearedAllLvls, Counter score, AnimationRunner ar, GUI gui) {
        String content;
        if (clearedAllLvls) {
            content = "You Win!";
        } else {
            content = "Game Over.";
        }
        content = content + " Your score is " + score.getValue();
        Text text = new Text(content, Color.BLACK, 175, 300, 32);
        TextDisplayingAnimation tda = new TextDisplayingAnimation(text);
        ar.run(new KeyPressStoppableAnimation(KeyboardSensor.SPACE_KEY, gui.getKeyboardSensor(), tda));
    }
}
