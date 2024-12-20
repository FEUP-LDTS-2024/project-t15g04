package AlienWalkTest.ModelTest;

import AlienWalk.Model.Elements.Tile;
import AlienWalk.Model.Level;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LevelTest {
    private Level level;

    @BeforeEach
    public void setUp(){
        level = new Level();
    }

    @Test
    public void initializeLevel(){
        Assertions.assertEquals(1, level.getWhich());
        Assertions.assertEquals(40, level.getWidth());
        Assertions.assertEquals(20, level.getHeight());
        Assertions.assertEquals(0, level.getAlien().getPosX());
        Assertions.assertEquals(0, level.getAlien().getPosY());
        Assertions.assertEquals(0, level.getShip().getPosX());
        Assertions.assertEquals(0, level.getShip().getPosY());
        Assertions.assertEquals(new ArrayList<>(), level.getMonsters());
        Assertions.assertEquals(new ArrayList<>(), level.getSpikes());
        Assertions.assertEquals(new ArrayList<>(), level.getCrystals());
        // check tiles and turning points
    }

    @Test
    public void populateTest(){
        level.setWhich(2);
        level.populateLevel();
        Assertions.assertEquals(1, level.getTiles()[0][1].getPosX());
        Assertions.assertEquals(0, level.getTiles()[0][1].getPosY());
        Assertions.assertEquals(0, level.getAlien().getPosX());
        Assertions.assertEquals(1, level.getAlien().getPosY());
        Assertions.assertEquals(0, level.getShip().getPosX());
        Assertions.assertEquals(2, level.getShip().getPosY());
        Assertions.assertEquals(0, level.getMonsters().get(0).getPosX());
        Assertions.assertEquals(3, level.getMonsters().get(0).getPosY());
        Assertions.assertEquals(0, level.getTurningPoints()[4][0].getPosX());
        Assertions.assertEquals(4, level.getTurningPoints()[4][0].getPosY());
        Assertions.assertEquals(0, level.getSpikes().get(0).getPosX());
        Assertions.assertEquals(5, level.getSpikes().get(0).getPosY());
        Assertions.assertEquals(0, level.getCrystals().get(0).getPosX());
        Assertions.assertEquals(6, level.getCrystals().get(0).getPosY());
    }

    @Test
    public void repopulateTest(){
        //  level should be 'empty'
        //  (just populate with level1.txt when initialized)
        Assertions.assertEquals(0, level.getAlien().getPosX());
        Assertions.assertEquals(0, level.getAlien().getPosY());
        Assertions.assertEquals(0, level.getShip().getPosX());
        Assertions.assertEquals(0, level.getShip().getPosY());
        Assertions.assertEquals(new ArrayList<>(), level.getMonsters());
        Assertions.assertEquals(new ArrayList<>(), level.getSpikes());
        Assertions.assertEquals(new ArrayList<>(), level.getCrystals());

        level.setWhich(2);
        level.repopulateLevel();

        // after repopulating with level 2,
        // monsters and alien should change, other things no
        Assertions.assertEquals(0, level.getShip().getPosX());
        Assertions.assertEquals(0, level.getShip().getPosY());
        Assertions.assertEquals(new ArrayList<>(), level.getSpikes());
        Assertions.assertEquals(new ArrayList<>(), level.getCrystals());

        Assertions.assertEquals(0, level.getAlien().getPosX());
        Assertions.assertEquals(1, level.getAlien().getPosY()); //Y changed
        Assertions.assertEquals(0, level.getMonsters().get(0).getPosX());
        Assertions.assertEquals(3, level.getMonsters().get(0).getPosY());
    }

    @Test
    public void shipCollidesTest(

    )
}
