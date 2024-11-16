import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    // levels maps (starting state) will be saved in resources,
    // so if we want to start new level (ex. 1, 2 3...)
    // the game will make copy from the resources and operate on the copy
    private int level;

    public Game(){
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen();           // screens must be started
            screen.doResizeIfNecessary();   // resize screen if necessary
            level = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        // TO DO
    }
}
