package AlienWalk.States;

import AlienWalk.Controller.GameController;
import AlienWalk.Game;
import AlienWalk.Model.Level;
import AlienWalk.Viewer.GameViewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GameState extends State<Level>{
    public int frame; //just for debuggig
    public static String pathToLevelFile;

    public GameState(Level level, GameViewer gameViewer, GameController gameController){
        super(level, gameController, gameViewer);
        //this.model.populateLevel("Levels/TestLevel.txt");
        frame = 0;
    }

//    MOVED TO LEVEL CLASS
//    public static void NextLevel(){
//        // TO DO
//        //pathToLevelFile = next
//    }

    @Override
    public void step(Game game) {
        viewer.draw(model);

        System.out.println("frame: " + frame);
        frame += 1;
        long startTime = System.currentTimeMillis(); // Record the start time

        int inputOption = viewer.read();

        try {
            controller.processInput(inputOption, game, model);
        } catch (IOException ignored) {}

        // Ensure the loop runs every 100 ms
        long elapsedTime = System.currentTimeMillis() - startTime;
        long sleepTime = Math.max(0, 10 - elapsedTime); // Calculate remaining time
        try {
            Thread.sleep(sleepTime); // Pause to maintain the desired interval
        } catch (InterruptedException ignored) {}
    }
}
