package AlienWalk.States;

import AlienWalk.Controller.MenuController;
import AlienWalk.Game;
import AlienWalk.Model.Menu;
import AlienWalk.Viewer.MenuViewer;


import java.io.IOException;

public class MenuState extends State<Menu>{

    public MenuState(Menu model, MenuController controller, MenuViewer viewer) {
        super(model, controller, viewer);
    }

    @Override
    public void step(Game game) {
        viewer.draw(model);

        long startTime = System.currentTimeMillis();

        int inputOption = viewer.read();
        try {
            controller.processInput(inputOption, game, model);
        } catch (IOException ignored) {}

        // Ensure the loop runs every 100 ms
        long elapsedTime = System.currentTimeMillis() - startTime;
        long sleepTime = Math.max(0, 12 - elapsedTime); // Calculate remaining time
        try {
            Thread.sleep(sleepTime); // Pause to maintain the desired interval
        } catch (InterruptedException ignored) {}
    }
}
