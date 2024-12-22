package AlienWalk.ControllerTest;
import AlienWalk.Controller.GameController;
import AlienWalk.Game;
import AlienWalk.Model.Elements.Alien;
import AlienWalk.Model.Elements.Monster;
import AlienWalk.Model.Elements.TurningPoint;
import AlienWalk.Model.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

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

    @Test
    public void processUpRight(){
        Mockito.when(level.isTileOnRight()).thenReturn(false);
        Mockito.when(level.isTileBelow()).thenReturn(true);
        gc.processInput(1, game, level);
        Mockito.verify(level, times(1)).alienRight();
        Mockito.verify(level, times(1)).alienStartJump();
    }

    @Test
    public void processNoUpRight(){
        Mockito.when(level.isTileOnRight()).thenReturn(true);
        Mockito.when(level.isTileBelow()).thenReturn(false);
        gc.processInput(1, game, level);
        Mockito.verify(level, never()).alienRight();
        Mockito.verify(level, never()).alienStartJump();
    }

    @Test
    public void processUpLeft(){
        Mockito.when(level.isTileOnLeft()).thenReturn(false);
        Mockito.when(level.isTileBelow()).thenReturn(true);
        gc.processInput(2, game, level);
        Mockito.verify(level, times(1)).alienLeft();
        Mockito.verify(level, times(1)).alienStartJump();
    }

    @Test
    public void processNoUpLeft(){
        Mockito.when(level.isTileOnLeft()).thenReturn(true);
        Mockito.when(level.isTileBelow()).thenReturn(false);
        gc.processInput(2, game, level);
        Mockito.verify(level, never()).alienLeft();
        Mockito.verify(level, never()).alienStartJump();
    }

    @Test
    public void processUp(){
        Mockito.when(level.isTileBelow()).thenReturn(true);
        gc.processInput(3, game, level);
        Mockito.verify(level, times(1)).alienStartJump();
    }

    @Test
    public void processNoUp(){
        Mockito.when(level.isTileBelow()).thenReturn(false);
        gc.processInput(3, game, level);
        Mockito.verify(level, never()).alienStartJump();
    }

    @Test
    public void processRight(){
        Mockito.when(level.isTileOnRight()).thenReturn(false);
        gc.processInput(4, game, level);
        Mockito.verify(level, times(1)).alienRight();
    }

    @Test
    public void processNoRight(){
        Mockito.when(level.isTileOnRight()).thenReturn(true);
        gc.processInput(4, game, level);
        Mockito.verify(level, never()).alienRight();
    }

    @Test
    public void processLeft(){
        Mockito.when(level.isTileOnLeft()).thenReturn(false);
        gc.processInput(5, game, level);
        Mockito.verify(level, times(1)).alienLeft();
    }

    @Test
    public void processNoLeft(){
        Mockito.when(level.isTileOnLeft()).thenReturn(true);
        gc.processInput(5, game, level);
        Mockito.verify(level, never()).alienLeft();
    }

    @Test
    public void  processJumpTileAbove(){
        Mockito.when(level.alienGetJumpState()).thenReturn(1);
        Mockito.when(level.isTileAbove()).thenReturn(true);
        gc.processInput(-1, game, level);
        Mockito.verify(level, never()).alienUp();
    }

    @Test
    public void  processJumpNoTileAbove(){
        Mockito.when(level.alienGetJumpState()).thenReturn(1);
        Mockito.when(level.isTileAbove()).thenReturn(false);
        gc.processInput(-1, game, level);
        Mockito.verify(level, times(1)).alienUp();
    }

    @Test
    public void processDown(){
        Mockito.when(level.alienGetJumpState()).thenReturn(0);
        Mockito.when(level.isTileBelow()).thenReturn(false);
        gc.processInput(-1, game, level);
        Mockito.verify(level, times(1)).alienDown();
    }

    @Test
    public void getsMonsterToMove(){
        Mockito.when(level.getMonsters()).thenReturn(List.of(new Monster(10,10)));
        Mockito.when(level.getTurningPoints()).thenReturn(new TurningPoint[20][40]);
        gc.processInput(-1, game, level);
        Mockito.verify(level, times(1)).getMonsters();
    }

    @Test
    public void nextLevel(){
        when(level.alienInShip()).thenReturn(true);
        when(level.nextLevel()).thenReturn(true);
        gc.processInput(-1, game, level);
        verify(level, times(1)).nextLevel();
    }

//    Cant test that with game as mock (no screen)
//    @Test
//    public void noNextLevel(){
//        when(level.alienInShip()).thenReturn(true);
//        when(level.nextLevel()).thenReturn(false);
//        gc.processInput(-1, game, level);
//        verify(level, times(1)).nextLevel();
//        verify(game, times(1)).setState(any());
//    }

    @Test
    public void checksCrystalsCollisions(){
        gc.processInput(-1, game, level);
        verify(level, times(1)).checkCollisionWithCrystals();
    }

    @Test
    public void collision(){
        when(level.checkCollision()).thenReturn(true);
        gc.processInput(-1, game, level);
        verify(level, times(1)).repopulateLevel();
    }

    @Test
    public void noCollision(){
        when(level.checkCollision()).thenReturn(false);
        gc.processInput(-1, game, level);
        verify(level, never()).repopulateLevel();
    }
}
