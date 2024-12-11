package AlienWalk.Controller;

import AlienWalk.Game;
import AlienWalk.Model.Level;
import AlienWalk.Model.Menu;
import AlienWalk.States.GameState;
import AlienWalk.Viewer.GameViewer;
import com.googlecode.lanterna.input.KeyStroke;

import javax.imageio.IIOException;
import java.io.IOException;

public class MenuController extends Controller<Menu>{
    @Override
    public void processInput(int inputOption, Game game, Menu model) throws IOException {
        switch(inputOption){
            case 2: // down
                model.setCurrent(Menu.Option.next(model.getCurrent()));
                break;
            case 1: // up
                model.setCurrent(Menu.Option.previous(model.getCurrent()));
                break;
            case 3: // enter
                switch (model.getCurrent()){
                    case Start:
                        game.state = new GameState(new Level(),new GameViewer(game.screen), new GameController());
                        break;
                    case Settings:
                        // To DO?
                        break;
                    case Quit:
                        game.screen.close();
                        game.state = null;
                        break;
                    default:
                        break;
                }
                break;
            case(0): // esc
                game.screen.close();
                game.state = null;
                break;
            default:
                   break;

        }
    }
}
