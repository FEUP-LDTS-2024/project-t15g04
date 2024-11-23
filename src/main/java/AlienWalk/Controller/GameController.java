package AlienWalk.Controller;

import AlienWalk.Game;
import AlienWalk.Model.Elements.Alien;
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
        if(key != null) {
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ' && model.isAlienOnTile()) {
                model.getAlien().setJumpState(5);
            }
            switch (key.getKeyType()) {
//            case ArrowUp:
//                break;
                case ArrowRight:
                    model.getAlien().getPosition().increaseX();
                    break;
                case ArrowLeft:
                    model.getAlien().getPosition().decreaseX();
                    break;
            }
        }

        if(model.getAlien().getJumpState()>0){ // alien going up until possible
            System.out.println("alien up");
            if(model.isAlienUnderTile()){
                model.getAlien().setJumpState(0);
            }
            else{
                model.getAlien().getPosition().increaseY();
                model.getAlien().setJumpState(model.getAlien().getJumpState() - 1);
            }
        }

        if(model.getAlien().getJumpState() == 0 && !(model.isAlienOnTile())){ // alien falling
            System.out.println("alien down");
            model.getAlien().getPosition().decreaseY();
        }
        System.out.println(model.getAlien().getPosition().getX());
        System.out.println(model.getAlien().getPosition().getY());
    }
}
