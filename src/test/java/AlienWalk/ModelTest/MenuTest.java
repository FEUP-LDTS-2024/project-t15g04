package AlienWalk.ModelTest;

import AlienWalk.Model.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest {
    Menu menu;

    @BeforeEach
    public void setUp(){
        menu = new Menu();
    }

    @Test
    public void menuInitializationTest(){
        menu = new Menu();
        Menu.Option tmp = menu.getCurrent();
        Assertions.assertEquals(Menu.Option.Start, tmp);
    }

    @Test
    public void setCurrentTest1(){
        menu.setCurrent(Menu.Option.Start);
        Assertions.assertEquals(Menu.Option.Start, menu.getCurrent());
    }

    @Test
    public void setCurrentTest2(){
        menu.setCurrent(Menu.Option.Settings);
        Assertions.assertEquals(Menu.Option.Settings, menu.getCurrent());
    }

    @Test
    public void setCurrentTest3(){
        menu.setCurrent(Menu.Option.Quit);
        Assertions.assertEquals(Menu.Option.Quit, menu.getCurrent());
    }

    @Test
    public void nextOptionTest1(){
        menu.setCurrent(Menu.Option.Start);
        menu.nextOption();
        Assertions.assertEquals(Menu.Option.Settings, menu.getCurrent());
    }

    @Test
    public void nextOptionTest2(){
        menu.setCurrent(Menu.Option.Settings);
        menu.nextOption();
        Assertions.assertEquals(Menu.Option.Quit, menu.getCurrent());
    }

    @Test
    public void nextOptionTest3(){
        menu.setCurrent(Menu.Option.Quit);
        menu.nextOption();
        Assertions.assertEquals(Menu.Option.Start, menu.getCurrent());
    }

    @Test
    public void previousOptionTest1(){
        menu.setCurrent(Menu.Option.Start);
        menu.previousOption();
        Assertions.assertEquals(Menu.Option.Quit, menu.getCurrent());
    }

    @Test
    public void previousOptionTest2(){
        menu.setCurrent(Menu.Option.Settings);
        menu.previousOption();
        Assertions.assertEquals(Menu.Option.Start, menu.getCurrent());
    }

    @Test
    public void previousOptionTest3(){
        menu.setCurrent(Menu.Option.Quit);
        menu.previousOption();
        Assertions.assertEquals(Menu.Option.Settings, menu.getCurrent());
    }
}
