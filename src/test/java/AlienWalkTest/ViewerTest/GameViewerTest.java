package AlienWalkTest.ViewerTest;

import AlienWalk.Controller.MenuController;
import AlienWalk.Model.Level;
import AlienWalk.Model.Menu;
import AlienWalk.States.MenuState;
import AlienWalk.Viewer.GameViewer;
import AlienWalk.Viewer.MenuViewer;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

public class GameViewerTest {
    private GameViewer gameViewer;
    private TerminalScreen screen;

    @BeforeEach
    public void setUp() throws IOException {
        try {
            // GameViewer constructor casts screen co cant use mocks
            TerminalSize terminalSize = new TerminalSize(0,0);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory
                    .setInitialTerminalSize(terminalSize)
                    .setForceAWTOverSwing(true)
                    .createTerminal();

            screen = spy(new TerminalScreen(terminal));
        }
        catch (IOException ignored) {}

        TerminalScreen ts = new TerminalScreen(screen.getTerminal());
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

        Mockito.verify(level, times(3)).getAlien();

        Mockito.verify(level, times(3)).getShip();

        Mockito.verify(level).getTiles();

        Mockito.verify(level).getMonsters();

        Mockito.verify(level).getSpikes();

        Mockito.verify(level).getCrystals();
    }
}
