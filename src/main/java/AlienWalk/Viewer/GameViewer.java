package AlienWalk.Viewer;

import AlienWalk.Model.Level;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import java.io.IOException;

public class GameViewer extends Viewer<Level> {
    private TextGraphics textGraphics;
    private ElementViewer tileViewer;
    private ElementViewer alienViewer;
    private ElementViewer monsterViewer;

    public GameViewer(TerminalScreen screen) throws IOException {
        super(screen);
        this.textGraphics = screen.newTextGraphics();
        // Initialize the viewers for different game elements
        tileViewer = new ElementViewer("ElementsImages/Tile.png");
        alienViewer = new ElementViewer("ElementsImages/Alien.png");
        monsterViewer = new ElementViewer("ElementsImages/Monster.png");
    }

    @Override
    public int read() {
        // Read user input and map it to actions
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
        // Clear the screen before redrawing
        screen.clear();

        // Draw tiles
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                if (model.getTiles()[j][i] != null) {
                    tileViewer.draw(
                            model.getTiles()[j][i].getPosition(),
                            model.getTiles()[j][i].getTransition_x(),
                            model.getTiles()[j][i].getTransition_y(),
                            this.textGraphics
                    );
                }
            }
        }

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                if (model.getMonsters()[j][i] != null) {
                    System.out.println("Monster found at (" + i + ", " + j + ")");
                    monsterViewer.draw(
                            model.getMonsters()[j][i].getPosition(),
                            model.getMonsters()[j][i].getTransition_x(),
                            model.getMonsters()[j][i].getTransition_y(),
                            this.textGraphics
                    );
                }
            }
        }


        // Draw the alien
        alienViewer.draw(
                model.getAlien().getPosition(),
                model.getAlien().getTransition_x(),
                model.getAlien().getTransition_y(),
                this.textGraphics
        );

        // Refresh the screen to display all updates
        try {
            screen.refresh();
        } catch (IOException ignored) {}
    }
}
