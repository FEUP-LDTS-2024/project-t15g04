package AlienWalkTest;

import AlienWalk.Game;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void initializeTest(){
        Game game = new Game();
        game.end();
    }
}
