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
    private State state;

    public static void main(String[] args) throws IOException {
        init();
        while(true){
            state.draw();
            state.upodate();
        }
    }

    public Game(){
        //this.level = 1;
    }

    public void init(){
        //this.state() = new state(1);
    }

    public void update(){
        // TO DO
    }
}
