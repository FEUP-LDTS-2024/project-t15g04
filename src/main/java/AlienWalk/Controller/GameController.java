package AlienWalk.Controller;

import AlienWalk.Game;
import AlienWalk.Model.Elements.Position;
import AlienWalk.Model.Level;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GameController extends Controller<Level>{
    int defaultJumpState;

    public GameController(){
        defaultJumpState = 5;
    }

    @Override
    public void processInput(KeyStroke key, Game game, Level model) throws IOException {
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == ' ' && model.isAlienOnTile()){
            model.getAlien().setJumpState(5);
        }
        switch(key.getKeyType()){
//            case ArrowUp:
//                break;
            case ArrowRight:
                model.getAlien().getPosition().increaseX();
                break;
            case ArrowLeft:
                model.getAlien().getPosition().decreaseX();
                break;
        }

        if(model.getAlien().getJumpState()>0){
            if(model.isAlienUnderTile()){
                model.getAlien().setJumpState(0);
                model.getAlien().getPosition().decreaseY();
            }
            else{
                model.getAlien().getPosition().increaseY();
                model.getAlien().setJumpState(model.getAlien().getJumpState() - 1);
            }
        }

        if(model.getAlien().getJumpState() == 0 && !(model.isAlienOnTile())){
            model.getAlien().getPosition().decreaseY();
        }

    }
}
