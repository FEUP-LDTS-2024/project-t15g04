package AlienWalk.Controller;

import AlienWalk.Game;
import AlienWalk.Model.Elements.Position;
import AlienWalk.Model.Level;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class GameController extends Controller<Level>{
    int defaultJumpState;

    public GameController(){
        defaultJumpState = 5;
    }

    @Override
    public void processInput(KeyStroke key, Game game, Level model) throws IOException {
        if(key.getCharacter() == ' ' && model.isAlienOnTitle()){
            model.getAlien().setJumpState(5);
            //model.getAlien().getPosition().increaseY();
        }
        switch(key.getKeyType()){
            case ArrowUp:
                break;
            case ArrowRight:
                break;
            case ArrowLeft:
                break;
        }

    }
}
