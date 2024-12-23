package AlienWalk;

import AlienWalk.Controller.MenuController;
import AlienWalk.Controller.OverMenuController;
import AlienWalk.Model.Menu;
import AlienWalk.Model.OverMenu;
import AlienWalk.States.MenuState;
import AlienWalk.States.OverMenuState;
import AlienWalk.States.State;
import AlienWalk.Viewer.MenuViewer;
import AlienWalk.Viewer.OverMenuViewer;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

public class Game {
    public State<?> state;
    public TerminalScreen screen;
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());

    public static void main(String[] args)  {
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

            AWTTerminalFrame frame = (AWTTerminalFrame) terminal;
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Cleanup and exit
                    try {
                        if (screen != null) {
                            screen.stopScreen();
                        }
                        terminal.close();
                    } catch (IOException ex) {
                        LOGGER.severe(ex.getMessage());
                    }
                    System.exit(0);
                }
            });

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            this.state = new MenuState(new Menu(), new MenuController(), new MenuViewer(screen));
        }
        catch (IOException | FontFormatException | URISyntaxException ignored) {}
    }

    public void run(){
        while(state != null){
            state.step(this);
        }
    }

    public void end(){
        try{
            this.screen.close();
        } catch (IOException ignored){}
        this.setState(null);
    }

    public void setState(State<?> state){
        this.state = state;
    }
}
