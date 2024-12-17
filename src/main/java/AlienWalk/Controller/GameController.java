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

public class GameController extends Controller<Level> {
    private int defaultJumpState;

    public GameController() {
        defaultJumpState = 5;
    }

    @Override
    public void processInput(int inputOption, Game game, Level model) throws IOException {
        // Handle user inputs
        switch (inputOption) {
            case (0): // esc
                game.screen.close();
                game.state = null;
                break;
            case (1): // up right
                if (!model.isTileOnRight()) model.getAlien().right();
                if (model.isTileBelow()) model.getAlien().start_jump();
                break;
            case (2): // up left
                if (!model.isTileOnLeft()) model.getAlien().left();
                if (model.isTileBelow()) model.getAlien().start_jump();
                break;
            case (3): // up
                if (model.isTileBelow()) model.getAlien().start_jump();
                break;
            case (4): // right
                if (!model.isTileOnRight()) model.getAlien().right();
                break;
            case (5): // left
                if (!model.isTileOnLeft()) model.getAlien().left();
                break;
        }

        // Handle jump and fall mechanics for the alien
        if (model.getAlien().getJumpState() > 0) { // Alien is jumping upwards
            if (model.isTileAbove()) {
                model.getAlien().setJumpState(0); // Stop jumping if it hits the ceiling
            } else {
                model.getAlien().up();
                model.getAlien().setJumpState(model.getAlien().getJumpState() - 1);
            }
        }

        // Handle alien falling if no surface below
        if (model.getAlien().getJumpState() == 0 && !(model.isTileBelow())) { // Alien is falling
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
                // Show the score or handle game over scenario
                game.screen.close();
                game.state = null;
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
