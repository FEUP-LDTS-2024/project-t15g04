package AlienWalkTest.ModelTest;

import AlienWalk.Model.Elements.Alien;
import AlienWalk.Model.Elements.Crystal;
import AlienWalk.Model.Level;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    // SMH WRONG!!!
    @Test
    public void shipCollidesTest(){
        level.setWhich(3);
        boolean tmp = level.alienInShip();
//        Assertions.assertFalse(tmp);
        level.alienRight();
        tmp = level.alienInShip();
        Assertions.assertTrue(tmp);
    }

    @Test
    public void nextLevelTest(){
        boolean tmp = level.nextLevel();
        Assertions.assertTrue(tmp);
        Assertions.assertEquals(2, level.getWhich());
        level.setWhich(5);
        tmp = level.nextLevel();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileAboveTest(){
        level.setWhich(4);
        level.populateLevel();
        boolean tmp = level.isTileAbove();
        Assertions.assertTrue(tmp);
    }

    @Test
    public void isTileBelowTest(){
        level.setWhich(4);
        level.populateLevel();
        boolean tmp = level.isTileBelow();
        Assertions.assertTrue(tmp);
    }
    @Test
    public void isTileOnLeftTest(){
        level.setWhich(4);
        level.populateLevel();
        boolean tmp = level.isTileOnLeft();
        Assertions.assertTrue(tmp);
    }
    @Test
    public void isTileOnRightTest(){
        level.setWhich(4);
        level.populateLevel();
        boolean tmp = level.isTileOnRight();
        Assertions.assertTrue(tmp);
    }

    @Test
    public void isTileAboveTestWithTransition1(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionX(2);
        boolean tmp = level.isTileAbove();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileAboveTestWithTransition2(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionX(-2);
        boolean tmp = level.isTileAbove();
        Assertions.assertFalse(tmp);

    }

    @Test
    public void isTileBelowTestWithTransition1(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionX(2);
        boolean tmp = level.isTileBelow();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileBelowTestWithTransition2(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionX(-2);
        boolean tmp = level.isTileBelow();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileOnLeftTestWithTransition1(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionY(2);
        boolean tmp = level.isTileOnLeft();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileOnLeftTestWithTransition2(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionY(-2);
        boolean tmp = level.isTileOnLeft();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileOnLeftTestWithTransition3(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionX(2);
        boolean tmp = level.isTileOnLeft();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileOnRightTestWithTransition(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionY(2);
        boolean tmp = level.isTileOnRight();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileOnRightTestWithTransition2(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionY(-2);
        boolean tmp = level.isTileOnRight();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void isTileOnRightTestWithTransition3(){
        level.setWhich(5);
        level.populateLevel();
        level.getAlien().setTransitionX(-2);
        boolean tmp = level.isTileOnRight();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void checkCollisionTest(){
        level.setWhich(5);
        level.populateLevel();
        boolean tmp = level.checkCollision();
        Assertions.assertFalse(tmp);
    }

    @Test
    public void checkCollisionTest2(){
        level.setWhich(6);
        level.populateLevel();
        level.getAlien().down();
        level.getAlien().down();
        boolean tmp = level.checkCollision();
        Assertions.assertTrue(tmp);
    }

    @Test
    public void checkCollisionTest3(){
        level.setWhich(6);
        level.populateLevel();
        level.getAlien().up();
        boolean tmp = level.checkCollision();
        Assertions.assertTrue(tmp);
    }

    @Test
    public void alienLeftTest(){
        level.setAlien(Mockito.mock(Alien.class));
        level.alienLeft();
        Mockito.verify(level.getAlien()).left();
    }

    @Test
    public void alienRightTest(){
        level.setAlien(Mockito.mock(Alien.class));
        level.alienRight();
        Mockito.verify(level.getAlien()).right();
    }

    @Test
    public void alienUpTest(){
        level.setAlien(Mockito.mock(Alien.class));
        level.alienUp();
        Mockito.verify(level.getAlien()).up();
    }

    @Test
    public void alienDownTest(){
        level.setAlien(Mockito.mock(Alien.class));
        level.alienDown();
        Mockito.verify(level.getAlien()).down();
    }

    @Test
    public void alienGetJumpStateTest(){
        level.setAlien(Mockito.mock(Alien.class));
        level.alienGetJumpState();
        Mockito.verify(level.getAlien()).getJumpState();
    }

    @Test
    public void alienSetJumpStateTest(){
        level.setAlien(Mockito.mock(Alien.class));
        level.alienSetJumpState(1);
        Mockito.verify(level.getAlien()).setJumpState(1);
    }

    @Test
    public void alienStartJumpTest(){
        level.setAlien(Mockito.mock(Alien.class));
        level.alienStartJump();
        Mockito.verify(level.getAlien()).startJump();
    }

    @Test
    public void checkCollisionsWithCrystalsTest(){
        Crystal crystal1 = Mockito.mock(Crystal.class);
        Crystal crystal2 = Mockito.mock(Crystal.class);
        Mockito.when(crystal1.collidesWith(level.getAlien())).thenReturn(true);
        Mockito.when(crystal2.collidesWith(level.getAlien())).thenReturn(false);
        level.getCrystals().add(crystal1);
        level.getCrystals().add(crystal2);

        level.checkCollisionWithCrystals();

        Mockito.verify(crystal1).collidesWith(level.getAlien());
        Mockito.verify(crystal2).collidesWith(level.getAlien());

        Assertions.assertFalse(level.getCrystals().contains(crystal1));
        Assertions.assertTrue(level.getCrystals().contains(crystal2));
    }
}
