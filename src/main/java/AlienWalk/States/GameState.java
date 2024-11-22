package AlienWalk.States;

import AlienWalk.Controller.GameController;
import AlienWalk.Game;
import AlienWalk.Model.Level;
import AlienWalk.Viewer.GameViewer;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GameState extends State<Level>{
    public GameState(Level level, GameViewer gameViewer, GameController gameController){
        super(level, gameController, gameViewer);
    }

    @Override
    public void step(Game game) {
        viewer.draw(model);
        KeyStroke key = viewer.read();
        if (key.getKeyType() == KeyType.EOF) {
            game.state = null;
        }
        try {
            controller.processInput(key, game, model);
        }
        catch (IOException ignored) {}

    }
}
