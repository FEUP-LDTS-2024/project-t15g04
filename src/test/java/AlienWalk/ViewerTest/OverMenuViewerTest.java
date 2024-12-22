package AlienWalk.ViewerTest;

import AlienWalk.Model.OverMenu;
import AlienWalk.Viewer.OverMenuViewer;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.spy;

public class OverMenuViewerTest {
    private OverMenuViewer menuViewer;
    private TerminalScreen screen;
    private TerminalScreen ts;

    @BeforeEach
    public void setUp() throws IOException {
        try {
            TerminalSize terminalSize = new TerminalSize(0,0);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory
                    .setInitialTerminalSize(terminalSize)
                    .setForceAWTOverSwing(true)
                    .createTerminal();

            screen = new TerminalScreen(terminal);
        }
        catch (IOException ignored) {}

        ts = spy(new TerminalScreen(screen.getTerminal()));
        menuViewer = new OverMenuViewer(ts);
    }

    @Test
    public void testRead1(){
        int tmp;
        tmp = menuViewer.read();
        Assertions.assertEquals(-1, tmp);
    }

    @Test
    public void testRea2(){
        int tmp;
        menuViewer.setQuit(true);
        tmp = menuViewer.read();
        Assertions.assertEquals(0, tmp);
    }

    @Test
    public void testRead3(){
        int tmp;
        menuViewer.setUp(true);
        tmp = menuViewer.read();
        Assertions.assertEquals(1, tmp);
    }

    @Test
    public void testRead4(){
        int tmp;
        menuViewer.setDown(true);
        tmp = menuViewer.read();
        Assertions.assertEquals(2, tmp);
    }

    @Test
    public void testRead5(){
        int tmp;
        menuViewer.setEnter(true);
        tmp = menuViewer.read();
        Assertions.assertEquals(3, tmp);
    }

    @Test
    public void testDraw1() throws IOException {
        OverMenu model = spy(new OverMenu());
        menuViewer.draw(model);

        Mockito.verify(model).getCurrent();

        Mockito.verify(ts).clear();
        Mockito.verify(ts).newTextGraphics();
        Mockito.verify(model).getCurrent();
        Mockito.verify(ts).refresh();
    }

    @Test
    public void testDraw2() throws IOException {
        OverMenu model = spy(new OverMenu());
        Mockito.when(model.getCurrent()).thenReturn(OverMenu.Option.Quit);
        menuViewer.draw(model);

        Mockito.verify(ts).clear();
        Mockito.verify(ts).newTextGraphics();
        Mockito.verify(model).getCurrent();
        Mockito.verify(ts).refresh();
    }
}
