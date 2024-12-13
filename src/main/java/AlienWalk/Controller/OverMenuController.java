package AlienWalk.Controller;

import AlienWalk.Game;
import AlienWalk.Model.OverMenu;
import AlienWalk.Model.Level;
import AlienWalk.States.GameState;
import AlienWalk.Viewer.GameViewer;

import java.io.IOException;

public class OverMenuController extends Controller<OverMenu> {

    @Override
    public void processInput(int inputOption, Game game, OverMenu model) throws IOException {
        // Handle input
        switch (inputOption) {
            case 2: // down
                model.setCurrent(OverMenu.Option.next(model.getCurrent()));
                break;
            case 1: // up
                model.setCurrent(OverMenu.Option.previous(model.getCurrent()));
                break;
            case 3: // enter
                switch (model.getCurrent()) {
                    case Restart:
                        // Restart the game
                        game.state = new GameState(new Level(), new GameViewer(game.screen), new GameController());
                        break;
                    case Quit:
                        // Quit the game
                        try {
                            game.screen.close();
                        } catch (IOException e) {
                        }
                        game.state = null;
                        break;
                }
                break;
            case 0: // esc
                try {
                    game.screen.close();
                } catch (IOException e) {
                }
                game.state = null;
                break;
        }
    }
}
