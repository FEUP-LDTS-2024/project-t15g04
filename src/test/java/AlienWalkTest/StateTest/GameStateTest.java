package AlienWalkTest.StateTest;

import AlienWalk.Controller.GameController;
import AlienWalk.Game;
import AlienWalk.Model.Level;
import AlienWalk.States.GameState;
import AlienWalk.Viewer.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class GameStateTest {
    private GameState gameState;
    private Level model;
    private GameViewer gameViewer;
    private GameController gameController;
    private Game game;

    @BeforeEach
    public void setUp(){
        model = Mockito.mock(Level.class);
        gameViewer = Mockito.mock(GameViewer.class);
        gameController = Mockito.mock(GameController.class);
        gameState = new GameState(model, gameViewer, gameController);
        game = Mockito.mock(Game.class);
    }

    @Test
    public void stepTest(){
        Mockito.when(gameViewer.read()).thenReturn(1);
        gameState.step(game);

        Mockito.verify(gameViewer, Mockito.times(1)).draw(model);

        Mockito.verify(gameController, Mockito.times(1)).processInput(1, game, model);
    }
}
