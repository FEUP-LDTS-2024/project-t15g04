package AlienWalk.States;

import AlienWalk.Controller.Controller;
import AlienWalk.Controller.MenuController;
import AlienWalk.Game;
import AlienWalk.Model.Menu;
import AlienWalk.Viewer.MenuViewer;

public class MenuState extends State<Menu>{

    public MenuState(Menu model, MenuController controller, MenuViewer viewer) {
        super(model, controller, viewer);
    }

    @Override
    public void step(Game game){

    }
}
