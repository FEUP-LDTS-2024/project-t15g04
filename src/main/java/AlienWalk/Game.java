package AlienWalk;

import AlienWalk.States.State;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Game {
    private Screen screen;
    // levels maps (starting state) will be saved in resources,
    // so if we want to start new level (ex. 1, 2 3...)
    // the game will make copy from the resources and operate on the copy
    private int level;
    private State state;

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.run();
    }

    public Game(){
        //this.level = 1;
    }

    public void run(){
        init();
        while(true){
            state.draw();
            state.upodate();
        }
    }

    public void init(){
        //this.state() = new state(1);
    }
}
