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
    public void processInput(int inputOption, Game game, Menu model) {
        switch(inputOption){
            case 2: // down
                model.nextOption();
                break;
            case 1: // up
                model.previousOption();
                break;
            case 3: // enter
                switch (model.getCurrent()){
                    case Start:
                        game.setState(new GameState(new Level(),new GameViewer(game.screen), new GameController()));
                        break;
                    case Settings:
                        // To DO?
                        break;
                    case Quit:
                        game.end();
                        break;
                    default:
                        break;
                }
                break;
            case(0): // esc
                game.end();
                break;
            default:
                break;

        }
    }
}
