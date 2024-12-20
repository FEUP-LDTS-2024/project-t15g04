package AlienWalkTest.ModelTest.ElementsTest;

import AlienWalk.Model.Elements.Element;
import AlienWalk.Model.Elements.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElementTest {
    private Element element;

    @BeforeEach
    public void setUp(){
        element = new Element(5,6);
    }

    @Test
    public void initializationTest(){
        Assertions.assertTrue(element.getPosition().equals(new Position(5,6)));
        int tmp = element.getPosX();
        Assertions.assertEquals(5,tmp);
        tmp = element.getPosY();
        Assertions.assertEquals(6, tmp);
        tmp = element.getTransitionX();
        Assertions.assertEquals(0, tmp);
        tmp = element.getTransitionY();
        Assertions.assertEquals(0, tmp);
    }

    @Test
    public void getPosXTest(){
        int tmp = element.getPosX();
        Assertions.assertEquals(5, tmp);
    }

    @Test
    public void getPosYTest(){
        int tmp = element.getPosY();
        Assertions.assertEquals(6, tmp);
    }

    @Test
    public void setTransitionXTest(){
        element.setTransitionX(2);
        Assertions.assertEquals(2,element.getTransitionX());
    }

    @Test
    public void setTransitionYTest(){
        element.setTransitionX(2);
        Assertions.assertEquals(2,element.getTransitionX());
    }

    @Test
    public void upTest1(){
        element.up();
        Assertions.assertEquals(-Element.Y_TRANSMISSION_STEP,
                element.getTransitionY());
    }

    @Test
    public void upTest2(){
        element.up();
        element.up();
        element.up();
        element.up();
        Assertions.assertEquals(0, element.getTransitionY());
        Assertions.assertEquals(5, element.getPosY());
    }

    @Test
    public void downTest1(){
        element.down();
        Assertions.assertEquals(Element.Y_TRANSMISSION_STEP,
                element.getTransitionY());
    }

    @Test
    public void downTest2(){
        element.down();
        element.down();
        element.down();
        element.down();
        Assertions.assertEquals(0, element.getTransitionY());
        Assertions.assertEquals(7, element.getPosY());
    }

    @Test
    public void leftTest1(){
        element.left();
        Assertions.assertEquals(-Element.X_TRANSMISSION_STEP, element.getTransitionX());
        Assertions.assertEquals(5, element.getPosX());
    }

    @Test
    public void leftTest2(){
        element.left();
        element.left();
        element.left();
        element.left();
        Assertions.assertEquals(0, element.getTransitionX());
        Assertions.assertEquals(4, element.getPosX());
    }

    @Test
    public void rightTest1(){
        element.right();
        Assertions.assertEquals(Element.X_TRANSMISSION_STEP, element.getTransitionX());
        Assertions.assertEquals(5, element.getPosX());
    }

    @Test
    public void rightTest2() {
        element.right();
        element.right();
        element.right();
        element.right();
        Assertions.assertEquals(0, element.getTransitionX());
        Assertions.assertEquals(6, element.getPosX());
    }

    @Test
    public void collidesWithTest1(){
        Element element2 = new Element(6,7);
        boolean tmp = element.collidesWith(element2);
        Assertions.assertFalse(tmp);
    }

    @Test
    public void collidesWithTest2(){
        Element element2 = new Element(5,6);
        boolean tmp = element.collidesWith(element2);
        Assertions.assertTrue(tmp);
    }

    @Test
    public void collidesWithTest3(){
        Element element2 = new Element(4,5);
        boolean tmp = element.collidesWith(element2);
        Assertions.assertFalse(tmp);
    }

    @Test
    public void collidesWithTest4(){
        Element element2 = new Element(4,5);
        element2.setTransitionY(2);
        boolean tmp = element.collidesWith(element2);
        Assertions.assertFalse(tmp);
    }

    @Test
    public void checkCollisionOnXTest1(){
        Element element2 = new Element(3,7);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertFalse(tmp);
    }

    @Test
    public void checkCollisionOnXTest2(){
        Element element2 = new Element(3,7);
        element2.setTransitionX(6);
        element.setTransitionX(-4);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertTrue(tmp);
    }

    @Test
    public void checkCollisionOnXTest3(){
        Element element2 = new Element(4,7);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertFalse(tmp);
    }

    @Test
    public void checkCollisionOnXTest4(){
        Element element2 = new Element(4,7);
        element2.setTransitionX(4);
        element.setTransitionX(-2);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertTrue(tmp);
    }

    @Test
    public void checkCollisionOnXTest5(){
        Element element2 = new Element(5,7);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertTrue(tmp);
    }

    @Test
    public void checkCollisionOnXTest6(){
        Element element2 = new Element(5,7);
        element2.setTransitionX(4);
        element.setTransitionX(-2);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertTrue(tmp);
    }

    @Test
    public void checkCollisionOnXTest7(){
        Element element2 = new Element(6,7);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertFalse(tmp);
    }

    @Test
    public void checkCollisionOnXTest8(){
        Element element2 = new Element(6,7);
        element2.setTransitionX(-2);
        element.setTransitionX(0);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertTrue(tmp);
    }

    @Test
    public void checkCollisionOnXTest9(){
        Element element2 = new Element(7,7);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertFalse(tmp);
    }

    @Test
    public void checkCollisionOnXTest10(){
        Element element2 = new Element(7,7);
        element2.setTransitionX(-6);
        element.setTransitionX(4);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertTrue(tmp);
    }

    @Test
    public void checkCollisionOnXTest11(){
        Element element2 = new Element(5,7);
        element2.setTransitionX(-6);
        element.setTransitionX(6);
        boolean tmp = element.checkCollisionOnX(element2);
        Assertions.assertFalse(tmp);
    }
}
