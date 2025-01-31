package AlienWalk.Controller;

import AlienWalk.Game;
import AlienWalk.Model.Elements.Monster;
import AlienWalk.Model.Level;
import AlienWalk.Model.OverMenu;
import AlienWalk.States.OverMenuState;
import AlienWalk.Viewer.OverMenuViewer;

import java.io.IOException;

public class GameController extends Controller<Level>{
    private boolean hittedTile = false;

    @Override
    public void processInput(int inputOption, Game game, Level model){
        // Handle user inputs
        switch(inputOption){
            case(0): // esc
                game.end();
                break;
            case(1): // up right
                if(!model.isTileOnRight()) model.alienRight();
                if(model.isTileBelow()){
                    model.alienStartJump();
                    hittedTile = false;
                }
                break;
            case(2): // up left
                if(!model.isTileOnLeft()) model.alienLeft();
                if(model.isTileBelow()){
                    model.alienStartJump();
                    hittedTile = false;
                }
                break;
            case(3): // up
                if(model.isTileBelow()){
                    model.alienStartJump();
                    hittedTile = false;
                }
                break;
            case(4): // right
                if(!model.isTileOnRight()) model.alienRight();
                break;
            case(5): //left
                if(!model.isTileOnLeft()) model.alienLeft();
                break;
            default:
                // do nothing (for testing, will not occur during game)
        }

        if(model.alienGetJumpState() > 0){ // alien going up until possible
            if(model.isTileAbove()){
                hittedTile = true;
            }
            else if(!hittedTile){
                model.alienUp();
            }
        model.alienSetJumpState(model.alienGetJumpState() - 1);
        }

        if(model.alienGetJumpState() == 0 && !(model.isTileBelow())){ // alien falling
            model.alienDown();
        }

        // Move monsters based on level's turning points
        for(Monster monster : model.getMonsters()){
            monster.move(model.getTurningPoints()[monster.getPosition().getY()]);
        }


        // Check if the alien reaches the ship to move to the next level
        if (model.alienInShip()) {
            if (!model.nextLevel()) { // No more levels left
                // Transition to the OverMenuState after game over
                try {
                    game.setState(new OverMenuState(new OverMenu(),
                            new OverMenuController(),
                            new OverMenuViewer(game.screen, model.getScore()))); // Set the new state to OverMenuState
                } catch (IOException ignored) {}
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
