package AlienWalk.Viewer;

import AlienWalk.Model.Elements.Monster;
import AlienWalk.Model.Elements.Spike;
import AlienWalk.Model.Level;
import AlienWalk.Model.Elements.Crystal;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.util.Iterator;

public class GameViewer extends Viewer<Level> {
    private TextGraphics textGraphics;
    private ElementViewer tileViewer;
    private ElementViewer alienViewer;
    private ElementViewer monsterViewer;
    private ElementViewer spikeViewer;
    private ElementViewer shipViewer;
    private ElementViewer tile2Viewer;
    private ElementViewer crystalViewer; // New viewer for crystals
    private int score;  // Field to store the score

    public GameViewer(TerminalScreen screen) throws IOException {
        super(screen);
        this.textGraphics = screen.newTextGraphics();
        tileViewer = new ElementViewer("ElementsImages/Tile.png");
        alienViewer = new ElementViewer("ElementsImages/Alien.png");
        monsterViewer = new ElementViewer("ElementsImages/Monster.png");
        spikeViewer = new ElementViewer("ElementsImages/Spike.png");
        shipViewer = new ElementViewer("ElementsImages/Ship.png");
        tile2Viewer = new ElementViewer("ElementsImages/Tile2.png");
        crystalViewer = new ElementViewer("ElementsImages/Crystal.png"); // Initialize crystal viewer
        this.score = 0;  // Initialize the score
    }

    @Override
    public int read() {
        if (quit) return 0;
        if ((up && right && !left) || (jump && right && !left)) return 1;
        if ((up && left && !right) || (jump && left && !right)) return 2;
        if (up || jump) return 3;
        if (right && !left) return 4;
        if (left && !right) return 5;
        return -1; // no input
    }

    @Override
    public void draw(Level model) {
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00de80"));

        // Draw the score at the top-left corner of the screen
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        textGraphics.putString(1, 1, "Score: " + score);  // Display score

        // Draw Alien
        alienViewer.draw(model.getAlien().getPosition(),
                model.getAlien().getTransitionX(),
                model.getAlien().getTransitionY(),
                this.textGraphics);

        // Draw Ship
        shipViewer.draw(model.getShip().getPosition(),
                model.getShip().getTransitionX(),
                model.getShip().getTransitionY(),
                this.textGraphics);

        // Draw tiles
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                if (model.getTiles()[j][i] != null) {
                    if (j == 0 || model.getTiles()[j - 1][i] == null) { // no block over -> grass
                        tileViewer.draw(model.getTiles()[j][i].getPosition(),
                                model.getTiles()[j][i].getTransitionX(),
                                model.getTiles()[j][i].getTransitionY(),
                                this.textGraphics);
                    } else {
                        tile2Viewer.draw(model.getTiles()[j][i].getPosition(),
                                model.getTiles()[j][i].getTransitionX(),
                                model.getTiles()[j][i].getTransitionY(),
                                this.textGraphics);
                    }
                }
            }
        }

        // Draw monsters
        for(Monster monster : model.getMonsters()){
            monsterViewer.draw(monster.getPosition(),
                    monster.getTransitionX(),
                    monster.getTransitionY(),
                    this.textGraphics);
        }


        // Draw spikes
        for(Spike spike : model.getSpikes()){
            spikeViewer.draw(spike.getPosition(),
                    0,0,
                    this.textGraphics);
        }


        // Draw crystals
        for (Crystal crystal : model.getCrystals()) {
            crystalViewer.draw(crystal.getPosition(),
                    0, 0, // Crystals do not move, so no transition values
                    this.textGraphics);
        }

        try {
            screen.refresh();
        } catch (IOException ignored) {
        }
    }
}
