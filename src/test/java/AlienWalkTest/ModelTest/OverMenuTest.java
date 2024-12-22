package AlienWalkTest.ModelTest;

import AlienWalk.Model.OverMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OverMenuTest {
    OverMenu menu;

    @BeforeEach
    public void setUp(){
        menu = new OverMenu();
    }

    @Test
    public void menuInitializationTest(){
        menu = new OverMenu();
        OverMenu.Option tmp = menu.getCurrent();
        Assertions.assertEquals(OverMenu.Option.Restart, tmp);
    }

    @Test
    public void setCurrentTest1(){
        menu.setCurrent(OverMenu.Option.Restart);
        Assertions.assertEquals(OverMenu.Option.Restart, menu.getCurrent());
    }

    @Test
    public void setCurrentTest2(){
        menu.setCurrent(OverMenu.Option.Quit);
        Assertions.assertEquals(OverMenu.Option.Quit, menu.getCurrent());
    }

    @Test
    public void nextOptionTest1(){
        menu.setCurrent(OverMenu.Option.Restart);
        menu.nextOption();
        Assertions.assertEquals(OverMenu.Option.Quit, menu.getCurrent());
    }

    @Test
    public void nextOptionTest2(){
        menu.setCurrent(OverMenu.Option.Quit);
        menu.nextOption();
        Assertions.assertEquals(OverMenu.Option.Restart, menu.getCurrent());
    }

    @Test
    public void previousOptionTest1(){
        menu.setCurrent(OverMenu.Option.Restart);
        menu.previousOption();
        Assertions.assertEquals(OverMenu.Option.Quit, menu.getCurrent());
    }

    @Test
    public void previousOptionTest2(){
        menu.setCurrent(OverMenu.Option.Quit);
        menu.previousOption();
        Assertions.assertEquals(OverMenu.Option.Restart, menu.getCurrent());
    }
}
