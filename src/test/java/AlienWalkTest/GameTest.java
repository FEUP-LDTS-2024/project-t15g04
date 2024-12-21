package AlienWalkTest;

import AlienWalk.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {
    private Game game;

    @Test
    public void initializeTest(){
        game = new Game();
        game.end();
    }
}
