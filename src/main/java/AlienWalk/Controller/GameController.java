package AlienWalk.Controller;

import AlienWalk.Game;
import AlienWalk.Model.Elements.Alien;
import AlienWalk.Model.Elements.Monster;
import AlienWalk.Model.Elements.Position;
import AlienWalk.Model.Level;
import AlienWalk.Viewer.GameViewer;
import AlienWalk.States.OverMenuState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class GameController extends Controller<Level>{
    private boolean hittedTile = false;

    @Override
    public void processInput(int inputOption, Game game, Level model) throws IOException {
        // Handle user inputs
        switch(inputOption){
            case(0): // esc
                game.screen.close();
                game.state = null;
                break;
            case(1): // up right
                if(!model.isTileOnRight()) model.getAlien().right();
                if(model.isTileBelow()){
                    model.getAlien().start_jump();
                    hittedTile = false;
                }
                break;
            case(2): // up left
                if(!model.isTileOnLeft()) model.getAlien().left();
                if(model.isTileBelow()){
                    model.getAlien().start_jump();
                    hittedTile = false;
                }
                break;
            case(3): // up
                if(model.isTileBelow()){
                    model.getAlien().start_jump();
                    hittedTile = false;
                }
                break;
            case(4): // right
                if(!model.isTileOnRight()) model.getAlien().right();
                break;
            case(5): //left
                if(!model.isTileOnLeft()) model.getAlien().left();
                break;
        }

    if(model.getAlien().getJumpState()>0){ // alien going up until possible
            if(model.isTileAbove()){
                hittedTile = true;
            }
            else if(!hittedTile){
                model.getAlien().up();
            }
        model.getAlien().setJumpState(model.getAlien().getJumpState() - 1);
        }

        if(model.getAlien().getJumpState() == 0 && !(model.isTileBelow())){ // alien falling
            model.getAlien().down();
        }

        // Move monsters based on level's turning points
        Monster monster;
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 40; i++) {
                monster = model.getMonsters()[j][i];
                if (monster != null) {
                    monster.move(model.getTurningPoints()[j]);
                }
            }
        }

        // Check if the alien reaches the ship to move to the next level
        if (model.alienInShip()) {
            if (!model.nextLevel()) { // No more levels left
                // Transition to the OverMenuState after game over
                try {
                    game.state = new OverMenuState(game); // Set the new state to OverMenuState
                } catch (IOException e) {
                    game.screen.close(); // Close the screen if an error occurs
                    game.state = null; // Reset the game state
                }
            }
        }

        // Check collision with crystals
        model.checkCollisionWithCrystals();

        // Check for collision with other hostile elements
        if (model.checkCollision()) {
            model.repopulateLevel();
        }
    }
}
