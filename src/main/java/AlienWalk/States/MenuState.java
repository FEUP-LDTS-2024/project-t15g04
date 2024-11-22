package AlienWalk.States;

import AlienWalk.Controller.Controller;
import AlienWalk.Controller.MenuController;
import AlienWalk.Game;
import AlienWalk.Model.Menu;
import AlienWalk.Viewer.MenuViewer;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class MenuState extends State<Menu>{

    public MenuState(Menu model, MenuController controller, MenuViewer viewer) {
        super(model, controller, viewer);
    }

    @Override
    public void step(Game game) {
        viewer.draw(model);
        KeyStroke key = viewer.read();
        if (key.getKeyType() == KeyType.EOF) {
            game.state = null;
        }
        try {
            controller.processInput(key, game, model);
        } catch (IOException ignored) {
        }
    }
}
