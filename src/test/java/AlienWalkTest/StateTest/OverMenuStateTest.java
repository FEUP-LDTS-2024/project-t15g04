package AlienWalkTest.StateTest;

import AlienWalk.Controller.MenuController;
import AlienWalk.Controller.OverMenuController;
import AlienWalk.Game;
import AlienWalk.Model.Menu;
import AlienWalk.Model.OverMenu;
import AlienWalk.States.MenuState;
import AlienWalk.States.OverMenuState;
import AlienWalk.Viewer.MenuViewer;
import AlienWalk.Viewer.OverMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class OverMenuStateTest {
    private OverMenuState overMenuState;
    private OverMenu model;
    private OverMenuViewer overMenuViewer;
    private OverMenuController overMenuController;
    private Game game;

    @BeforeEach
    public void setUp(){
        model = Mockito.mock(OverMenu.class);
        overMenuViewer = Mockito.mock(OverMenuViewer.class);
        overMenuController = Mockito.mock(OverMenuController.class);
        try {
            overMenuState = new OverMenuState(model, overMenuController, overMenuViewer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        game = Mockito.mock(Game.class);
    }

    @Test
    public void stepTest(){
        Mockito.when(overMenuViewer.read()).thenReturn(1);
        overMenuState.step(game);

        Mockito.verify(overMenuViewer, Mockito.times(1)).draw(model);

        Mockito.verify(overMenuController, Mockito.times(1)).processInput(1, game, model);
    }
}
