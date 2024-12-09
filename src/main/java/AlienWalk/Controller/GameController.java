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
    public void processInput(int inputOption, Game game, Level model) throws IOException {
        System.out.print(model.getAlien().getPosition().getX());
        System.out.print(" -------: ");
        System.out.println(model.getAlien().getPosition().getY());

        switch(inputOption){
            case(0): // esc
                game.screen.close();
                game.state = null;
                break;
            case(1): // up right
                if(!model.isTileOnRight()) model.getAlien().right();
                if(model.isTileBelow()) model.getAlien().start_jump();
                break;
            case(2): // up left
                if(!model.isTileOnLeft()) model.getAlien().left();
                if(model.isTileBelow()) model.getAlien().start_jump();
                break;
            case(3): // up
                if(model.isTileBelow()) model.getAlien().start_jump();
                break;
            case(4): // right
                if(!model.isTileOnRight()) model.getAlien().right();
                break;
            case(5): //left
                if(!model.isTileOnLeft()) model.getAlien().left();
                break;
        }

        if(model.getAlien().getJumpState()>0){ // alien going up until possible
            System.out.println("alien up");
            if(model.isTileAbove()){
                model.getAlien().setJumpState(0);
            }
            else{
                model.getAlien().up();
                model.getAlien().setJumpState(model.getAlien().getJumpState() - 1);
            }
        }

        if(model.getAlien().getJumpState() == 0 && !(model.isTileBelow())){ // alien falling
            System.out.println("alien down");
            model.getAlien().down();
        }


        System.out.println(model.getAlien().getPosition().getX());
        System.out.println(model.getAlien().getPosition().getY());
    }
}
