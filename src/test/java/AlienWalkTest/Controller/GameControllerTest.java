package AlienWalkTest.Controller;
import AlienWalk.Controller.GameController;
import AlienWalk.Model.Elements.Alien;
import AlienWalk.Model.Elements.Position;
import AlienWalk.Model.Level;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class GameControllerTest {

    private GameController gameController;

    @Mock
    private Level level;  // Mocked Level

    @Mock
    private Alien alien;  // Mocked Alien

    @Mock
    private Position position;  // Mocked Position

    @BeforeEach
    public void setup() {
        // Initialize mocks using MockitoAnnotations
        MockitoAnnotations.openMocks(this);

        // Set up the mock behaviors
        when(level.getAlien()).thenReturn(alien);
        when(alien.getPosition()).thenReturn(position);

        gameController = new GameController();
    }

    @Test
    public void testMoveLeft() throws Exception {
        // Create KeyStroke for left arrow key
        KeyStroke keyStroke = new KeyStroke(KeyType.ArrowLeft);

        // Simulate the left arrow key press
        gameController.processInput(keyStroke, null, level);

        // Verify that the position's X was decreased
        verify(position, times(1)).decreaseX();  // Verify that decreaseX was called once
    }
}
