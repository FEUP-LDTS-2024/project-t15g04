//package AlienWalkTest.Model.Element;
//
//import AlienWalk.Model.Elements.Position;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PositionTest {
//
//    private Position position;
//
//    @BeforeEach
//    void setUp() {
//        position = new Position(0, 0); // Initializing Position with x=0 and y=0
//    }
//
//    @Test
//    void testGetX() {
//        assertEquals(0, position.getX(), "Initial X position should be 0");
//    }
//
//    @Test
//    void testSetX() {
//        position.setX(5);
//        assertEquals(5, position.getX(), "X position should be 5 after setting it");
//    }
//
//    @Test
//    void testGetY() {
//        assertEquals(0, position.getY(), "Initial Y position should be 0");
//    }
//
//    @Test
//    void testSetY() {
//        position.setY(3);
//        assertEquals(3, position.getY(), "Y position should be 3 after setting it");
//    }
//
//    @Test
//    void testIncreaseY() {
//        position.increaseY();
//        assertEquals(-1, position.getY(), "Y position should decrease by 1 when increaseY() is called");
//    }
//
//    @Test
//    void testDecreaseY() {
//        position.decreaseY();
//        assertEquals(1, position.getY(), "Y position should increase by 1 when decreaseY() is called");
//    }
//
//    @Test
//    void testIncreaseX() {
//        position.increaseX();
//        assertEquals(1, position.getX(), "X position should increase by 1 when increaseX() is called");
//    }
//
//    @Test
//    void testDecreaseX() {
//        position.decreaseX();
//        assertEquals(-1, position.getX(), "X position should decrease by 1 when decreaseX() is called");
//    }
//}
