package AlienWalkTest.ModelTest.ElementsTest;

import AlienWalk.Model.Elements.Alien;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlienTest {
    private Alien alien;

    @BeforeEach
    public void setUp(){
        alien = new Alien(5,5);
    }

    @Test
    public void getSetJumpStateTest(){
        int tmp = 0;
        alien.setJumpState(5);
        tmp = alien.getJumpState();
        Assertions.assertEquals(5, tmp);
    }

    @Test
    public void startJumpTest(){
        int tmp;
        alien.startJump();
        tmp = alien.getJumpState();
        Assertions.assertEquals(18, tmp);
    }
}
