package AlienWalkTest.Controller;
import AlienWalk.Controller.GameController;
import AlienWalk.Game;
import AlienWalk.Model.Elements.Alien;
import AlienWalk.Model.Elements.Position;
import AlienWalk.Model.Level;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class GameControllerTest {
    private GameController gc;
    private Game game;
    private Level level;

    @BeforeEach
    public void setUp(){
        gc = new GameController();
        game = Mockito.mock(Game.class);
        level = Mockito.mock(Level.class);
    }

    @Test
    public void processEsc(){
        Mockito.when(level.getAlien()).thenReturn(new Alien(0,0)); // bc null error
        gc.processInput(0, game, level);
        Mockito.verify(game).end();
    }

}
