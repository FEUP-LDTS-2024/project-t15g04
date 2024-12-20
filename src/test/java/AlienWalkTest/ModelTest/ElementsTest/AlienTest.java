package AlienWalkTest.ModelTest.ElementsTest;

import AlienWalk.Model.Elements.Alien;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlienTest {
    private Alien alien;

    @BeforeEach
    public void setUp(){
        alien = new Alien(5,5);
    }
}
