package AlienWalk.Controller;

import AlienWalk.Game;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public abstract class Controller<T> {
    public Controller(){};

    public abstract void processInput(int inputOption, Game game, T model) throws IOException;
}
