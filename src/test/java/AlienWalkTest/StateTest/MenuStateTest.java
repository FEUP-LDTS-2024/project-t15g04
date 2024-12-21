package AlienWalkTest.StateTest;

import AlienWalk.Controller.MenuController;
import AlienWalk.Game;
import AlienWalk.Model.Menu;
import AlienWalk.States.GameState;
import AlienWalk.States.MenuState;
import AlienWalk.Viewer.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuStateTest {
    private MenuState menuState;
    private Menu model;
    private MenuViewer menuViewer;
    private MenuController menuController;
    private Game game;

    @BeforeEach
    public void setUp(){
        model = Mockito.mock(Menu.class);
        menuViewer = Mockito.mock(MenuViewer.class);
        menuController = Mockito.mock(MenuController.class);
        menuState = new MenuState(model, menuController, menuViewer);
        game = Mockito.mock(Game.class);
    }

    @Test
    public void stepTest(){
        Mockito.when(menuViewer.read()).thenReturn(1);
        menuState.step(game);

        Mockito.verify(menuViewer, Mockito.times(1)).draw(model);

        Mockito.verify(menuController, Mockito.times(1)).processInput(1, game, model);
    }
}
