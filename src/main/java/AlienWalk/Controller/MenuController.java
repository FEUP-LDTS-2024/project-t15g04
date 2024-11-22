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
    public void processInput(KeyStroke key, Game game, Menu model) throws IOException {
        switch(key.getKeyType()){
            case ArrowDown:
                model.setCurrent(Menu.Option.next(model.getCurrent()));
            case ArrowUp:
                model.setCurrent(Menu.Option.previous(model.getCurrent()));
            case Enter:
                switch (model.getCurrent()){
                    case Start:
                        game.state = new GameState(new Level(),new GameViewer(game.screen), new GameController());
                    case Settings:
                        // To DO?
                    case Quit:
                        //game.screen.close();
                        //game.state = null;
                }

        }
    }
}
