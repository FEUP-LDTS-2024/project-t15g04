package AlienWalkTest.ModelTest.ElementsTest;

import AlienWalk.Model.Elements.Monster;
import AlienWalk.Model.Elements.TurningPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonsterTest {
    private Monster monster;

    @BeforeEach
    public void setUp(){
        monster = new Monster(5,5);
    }

    @Test
    public void moveTest1(){
        TurningPoint[] t = new TurningPoint[40];
        monster.move(t);
        Assertions.assertTrue(monster.isGoingRight());
    }

    @Test
    public void moveTest2(){
        TurningPoint[] t = new TurningPoint[40];
        monster.setGoingRight(false);
        monster.move(t);
        Assertions.assertFalse(monster.isGoingRight());
    }

    @Test
    public void moveTest3(){
        TurningPoint[] t = new TurningPoint[40];
        t[6] = new TurningPoint(6,5);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        Assertions.assertFalse(monster.isGoingRight());
    }

    @Test
    public void moveTest4(){
        TurningPoint[] t = new TurningPoint[40];
        t[4] = new TurningPoint(4,5);
        monster.setGoingRight(false);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        Assertions.assertTrue(monster.isGoingRight());
    }

    @Test
    public void moveTest5(){
        TurningPoint[] t = new TurningPoint[40];
        t[6] = new TurningPoint(6,5);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        Assertions.assertFalse(monster.isGoingRight());
    }

    @Test
    public void moveTest6(){
        TurningPoint[] t = new TurningPoint[40];
        t[4] = new TurningPoint(4,5);
        monster.setGoingRight(false);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        monster.move(t);
        Assertions.assertTrue(monster.isGoingRight());
    }
}
