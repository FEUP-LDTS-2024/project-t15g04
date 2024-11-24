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

import java.io.IOException;

public class Game {
    public State state;
    public Screen screen;

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }

    public Game(){
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            this.state = new MenuState(new Menu(), new MenuController(), new MenuViewer(screen));
        } catch (IOException ignored) {}
    }

    public void run(){
        while(state != null){
            state.step(this);
            //System.out.println("running");
        }
        //System.out.println("END");
    }
}
