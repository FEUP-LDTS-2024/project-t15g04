package AlienWalkTest.ViewerTest;

import AlienWalk.Model.Menu;
import AlienWalk.Viewer.MenuViewer;
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

public class MenuViewerTest {
    private MenuViewer menuViewer;
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
        menuViewer = new MenuViewer(ts);
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
        Menu model = spy(new Menu());
        menuViewer.draw(model);

        Mockito.verify(ts).clear();
        Mockito.verify(ts).newTextGraphics();
        Mockito.verify(model).getCurrent();
        Mockito.verify(ts).refresh();
    }

    @Test
    public void testDraw2() throws IOException {
        Menu model = spy(new Menu());
        Mockito.when(model.getCurrent()).thenReturn(Menu.Option.Settings);
        menuViewer.draw(model);

        Mockito.verify(ts).clear();
        Mockito.verify(ts).newTextGraphics();
        Mockito.verify(model).getCurrent();
        Mockito.verify(ts).refresh();
    }

    @Test
    public void testDraw3() throws IOException {
        Menu model = spy(new Menu());
        Mockito.when(model.getCurrent()).thenReturn(Menu.Option.Quit);
        menuViewer.draw(model);

        Mockito.verify(ts).clear();
        Mockito.verify(ts).newTextGraphics();
        Mockito.verify(model).getCurrent();
        Mockito.verify(ts).refresh();
    }
}
