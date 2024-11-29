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
        System.out.print(model.getAlien().getPosition().getX());
        System.out.print(" -------: ");
        System.out.println(model.getAlien().getPosition().getY());
        if(key != null) {
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ' && model.isAlienOnTile()) {
                model.getAlien().start_jump();
            }
            switch (key.getKeyType()) {
//            case ArrowUp:
//                break;
                case ArrowRight:
                    if(model.isTileOnRight()) break;
                    model.getAlien().right();
                    break;
                case ArrowLeft:
                    if(model.isTileOnLeft()) break;
                    model.getAlien().left();
                    break;
            }
        }

        if(model.getAlien().getJumpState()>0){ // alien going up until possible
            System.out.println("alien up");
            if(model.isAlienUnderTile()){
                model.getAlien().setJumpState(0);
            }
            else{
                model.getAlien().up();
                model.getAlien().setJumpState(model.getAlien().getJumpState() - 1);
            }
        }

        if(model.getAlien().getJumpState() == 0 && !(model.isAlienOnTile())){ // alien falling
            System.out.println("alien down");
            model.getAlien().down();
        }
        System.out.println(model.getAlien().getPosition().getX());
        System.out.println(model.getAlien().getPosition().getY());
    }
}
