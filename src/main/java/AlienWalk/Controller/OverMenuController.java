package AlienWalk.Controller;

import AlienWalk.Game;
import AlienWalk.Model.OverMenu;
import AlienWalk.Model.Level;
import AlienWalk.States.GameState;
import AlienWalk.Viewer.GameViewer;

import java.io.IOException;

public class OverMenuController extends Controller<OverMenu> {

    @Override
    public void processInput(int inputOption, Game game, OverMenu model) {
        // Handle input
        switch (inputOption) {
            case 2: // down
                model.nextOption();
                break;
            case 1: // up
                model.previousOption();
                break;
            case 3: // enter
                switch (model.getCurrent()) {
                    case Restart:
                        // Restart the game
                        game.state = new GameState(new Level(), new GameViewer(game.screen), new GameController());
                        break;
                    case Quit:
                        // Quit the game
                        game.end();
                        break;
                }
                break;
            case 0: // esc
                game.end();
                break;
        }
    }
}
