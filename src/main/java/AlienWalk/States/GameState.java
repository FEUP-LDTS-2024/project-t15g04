package AlienWalk.States;

import AlienWalk.Controller.GameController;
import AlienWalk.Game;
import AlienWalk.Model.Level;
import AlienWalk.Viewer.GameViewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GameState extends State<Level> {
    public int frame; // just for debugging
    public static String pathToLevelFile;

    public GameState(Level level, GameViewer gameViewer, GameController gameController) {
        super(level, gameController, gameViewer);
        frame = 0;
    }

    @Override
    public void step(Game game) {
        viewer.draw(model);
        frame += 1;

        long startTime = System.currentTimeMillis(); // Record the start time

        int inputOption = viewer.read();

        try {
            controller.processInput(inputOption, game, model);
        } catch (IOException e) {
        }

        // Ensure the loop runs every 10 ms (for smoother gameplay)
        long elapsedTime = System.currentTimeMillis() - startTime;
        long sleepTime = Math.max(0, 10 - elapsedTime); // Calculate remaining time
        try {
            Thread.sleep(sleepTime); // Pause to maintain the desired interval
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
