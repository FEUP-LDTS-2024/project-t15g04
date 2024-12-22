package AlienWalkTest.ViewerTest;

import AlienWalk.Model.Elements.Position;
import AlienWalk.Viewer.ElementViewer;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

public class ElementViewerTest {
    @Test
    void testConstructorValidPath() throws IOException {
        ElementViewer viewer = new ElementViewer("Test.png");
        assertNotNull(viewer);
    }

    @Test
    void testConstructorInvalidPath() {
        assertThrows(NullPointerException.class, () -> new ElementViewer("invalid_path.png"));
    }

    @Test
    public void drawTest() throws IOException {
        ElementViewer viewer = new ElementViewer("Test.png");
        Position position = Mockito.mock(Position.class);
        TextGraphics tg = Mockito.mock(TextGraphics.class);

        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position.getY()).thenReturn(0);

        viewer.draw(position, 0, 0, tg);

        // times 22 because image is 8x8 (so 64)
        // but it is an image of spike
        // where 42 pixels are 'empty'
        Mockito.verify(tg, times(22)).setCharacter(anyInt(), anyInt(), any());
    }
}
