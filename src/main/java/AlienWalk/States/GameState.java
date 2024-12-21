package AlienWalk.States;

import AlienWalk.Controller.GameController;
import AlienWalk.Game;
import AlienWalk.Model.Level;
import AlienWalk.Viewer.GameViewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GameState extends State<Level> {
    public static String pathToLevelFile;

    public GameState(Level level, GameViewer gameViewer, GameController gameController) {
        super(level, gameController, gameViewer);
    }

    @Override
    public void step(Game game) {
        viewer.draw(model);

        long startTime = System.currentTimeMillis(); // Record the start time

        int inputOption = viewer.read();

        try {
            controller.processInput(inputOption, game, model);
        } catch (IOException ignored) {}

        // Ensure the loop runs every 10 ms (for smoother gameplay)
        long elapsedTime = System.currentTimeMillis() - startTime;
        long sleepTime = Math.max(0, 10 - elapsedTime); // Calculate remaining time
        try {
            Thread.sleep(sleepTime); // Pause to maintain the desired interval
        } catch (InterruptedException ignored) {}
    }
}
