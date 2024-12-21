package AlienWalk.States;

import AlienWalk.Game;
import AlienWalk.Controller.OverMenuController;
import AlienWalk.Model.OverMenu;
import AlienWalk.Viewer.OverMenuViewer;
import java.io.IOException;

public class OverMenuState extends State<OverMenu> {

    private static final int STEP_INTERVAL_MS = 100; // Configurable interval for step timing

    public OverMenuState(OverMenu model, OverMenuController omc, OverMenuViewer omv) throws IOException {
        super(model, omc, omv);
    }


    @Override
    public void step(Game game) {
        viewer.draw(model);

        long startTime = System.currentTimeMillis();

        int inputOption = viewer.read();
        try {
            controller.processInput(inputOption, game, model);
        } catch (IOException ignored) {}

        long elapsedTime = System.currentTimeMillis() - startTime;
        long sleepTime = Math.max(0, STEP_INTERVAL_MS - elapsedTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ignored) {}
    }
}