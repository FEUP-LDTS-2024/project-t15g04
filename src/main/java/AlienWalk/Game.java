package AlienWalk;

import AlienWalk.Controller.MenuController;
import AlienWalk.Model.Level;
import AlienWalk.Model.Menu;
import AlienWalk.States.MenuState;
import AlienWalk.States.State;
import AlienWalk.Viewer.MenuViewer;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Game {
    public State state;
    public Screen screen;

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }

    public Game(){
        try {
            // Get square font
            URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
            assert resource != null;
            File fontFile = new File(resource.toURI());
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            Font newfont = font.deriveFont(Font.PLAIN, 4);

            AWTTerminalFontConfiguration cfg = AWTTerminalFontConfiguration.newInstance(newfont);

            TerminalSize terminalSize = new TerminalSize(40*8, 20*8);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory
                    .setInitialTerminalSize(terminalSize)
                    .setTerminalEmulatorFontConfiguration(cfg)
                    .setForceAWTOverSwing(true)
                    .createTerminal();

//            TerminalSize terminalSize = new TerminalSize(40, 20);
//            DefaultTerminalFactory terminalFactory = new
//                    DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
//            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            this.state = new MenuState(new Menu(), new MenuController(), new MenuViewer(screen));
        }
        catch (IOException ignored) {}
        catch (FontFormatException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void run(){
        while(state != null){
            state.step(this);
            //System.out.println("running");
        }
        //System.out.println("END");
    }
}
