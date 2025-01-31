package AlienWalk.Controller;

import AlienWalk.Game;

import java.io.IOException;

public abstract class Controller<T> {
    public Controller(){}
    public abstract void processInput(int inputOption, Game game, T model) throws IOException;
}
