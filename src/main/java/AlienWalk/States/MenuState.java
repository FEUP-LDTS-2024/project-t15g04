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
        System.out.println("MenuStep");
        viewer.draw(model);
        int inputOption = viewer.read();
        System.out.println("menuStep inputOption = " + String.valueOf(inputOption));
        try {
            controller.processInput(inputOption, game, model);
        } catch (IOException ignored) {
        }
    }
}
