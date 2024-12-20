package AlienWalkTest.ControllerTest;

import AlienWalk.Controller.OverMenuController;
import AlienWalk.Game;
import AlienWalk.Model.OverMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class OverMenuControllerTest {
    private OverMenuController mc;
    private Game game;
    private OverMenu menu;

    @BeforeEach
    public void setUp(){
        mc = new OverMenuController();
        game = Mockito.mock(Game.class);
        menu = Mockito.mock(OverMenu.class);
    }

    @Test
    public void processDown(){
        mc.processInput(2, game, menu);
        verify(menu, times(1)).nextOption();
    }

    @Test
    public void processUp(){
        mc.processInput(1, game, menu);
        verify(menu, times(1)).previousOption();
    }

    @Test
    public void processEsc() {
        mc.processInput(0, game, menu);
        verify(game, times(1)).end();
    }
    //    Cant test with mocks bc screen is null
//    @Test
//    public void processEnterStart(){
//        when(menu.getCurrent()).thenReturn(Menu.Option.Start);
//        mc.processInput(3,game, menu);
//        verify(game, times(1)).setState(any());
//    }

    @Test
    public void processEnterQuit(){
        when(menu.getCurrent()).thenReturn(OverMenu.Option.Quit);
        mc.processInput(3, game, menu);
        verify(game, times(1)).end();
    }

    @Test
    public void processDefault(){
        mc.processInput(-1, game, menu);
        verify(game, never()).end();
        verify(menu, never()).nextOption();
        verify(menu, never()).previousOption();
    }
}
