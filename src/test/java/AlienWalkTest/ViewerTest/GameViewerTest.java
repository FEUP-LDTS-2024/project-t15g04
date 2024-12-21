package AlienWalkTest.ViewerTest;

import AlienWalk.Model.Level;
import AlienWalk.Viewer.GameViewer;
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
import static org.mockito.Mockito.times;

public class GameViewerTest {
    private GameViewer gameViewer;
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
        gameViewer = new GameViewer(ts);
    }

    @Test
    public void testRead1(){
        int tmp;
        tmp = gameViewer.read();
        Assertions.assertEquals(-1, tmp);
    }

    @Test
    public void testRea2(){
        int tmp;
        gameViewer.setQuit(true);
        tmp = gameViewer.read();
        Assertions.assertEquals(0, tmp);
    }

    @Test
    public void testRead3(){
        int tmp;
        gameViewer.setUp(true);
        gameViewer.setRight(true);
        tmp = gameViewer.read();
        Assertions.assertEquals(1, tmp);
    }

    @Test
    public void testRead4(){
        int tmp;
        gameViewer.setUp(true);
        gameViewer.setLeft(true);
        tmp = gameViewer.read();
        Assertions.assertEquals(2, tmp);
    }

    @Test
    public void testRead5(){
        int tmp;
        gameViewer.setUp(true);
        tmp = gameViewer.read();
        Assertions.assertEquals(3, tmp);
    }

    @Test
    public void testRead6(){
        int tmp;
        gameViewer.setRight(true);
        tmp = gameViewer.read();
        Assertions.assertEquals(4, tmp);
    }

    @Test
    public void testRead7(){
        int tmp;
        gameViewer.setLeft(true);
        tmp = gameViewer.read();
        Assertions.assertEquals(5, tmp);
    }

    @Test
    public void testDraw() throws IOException {
        Level level = spy(new Level());
        level.setWhich(7);
        level.populateLevel();

        gameViewer.draw(level);

        Mockito.verify(ts).newTextGraphics();
        Mockito.verify(ts).clear();
        Mockito.verify(level, times(3)).getAlien();
        Mockito.verify(level, times(3)).getShip();
        Mockito.verify(level).getTiles();
        Mockito.verify(level).getMonsters();
        Mockito.verify(level).getSpikes();
        Mockito.verify(level).getCrystals();
        Mockito.verify(ts).refresh();
    }
}
