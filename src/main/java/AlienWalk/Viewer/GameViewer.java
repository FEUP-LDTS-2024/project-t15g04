package AlienWalk.Viewer;

import AlienWalk.Model.Elements.Tile;
import AlienWalk.Model.Level;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class GameViewer extends Viewer<Level>{
    private Level level;
    private TextGraphics textGraphics;
    private ElementViewer tileViewer;
    private ElementViewer alienViewer;
    private ElementViewer monsterViewer;
    private ElementViewer spikeViewer;
    private ElementViewer shipViewer;
    private ElementViewer tile2Viewer;

    public GameViewer(TerminalScreen screen) throws IOException {
        super(screen);
        this.textGraphics = screen.newTextGraphics();
        tileViewer = new ElementViewer("ElementsImages/Tile.png");
        alienViewer = new ElementViewer("ElementsImages/Alien.png");
        monsterViewer = new ElementViewer("ElementsImages/Monster.png");
        spikeViewer = new ElementViewer ("ElementsImages/Spike.png");
        shipViewer = new ElementViewer("ElementsImages/Ship.png");
        tile2Viewer = new ElementViewer("ElementsImages/Tile2.png");
    }

    @Override
    public int read(){
        if(quit) return 0;
        if((up && right && !left) || (jump && right && !left)) return 1;
        if((up && left && !right) || (jump && left && !right)) return 2;
        if(up || jump) return 3;
        if(right && !left) return 4;
        if(left && !right) return 5;
        return -1; // no input
    }

    // we could draw  using composite DP
    @Override
    public void draw(Level model) {
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00de80"));
        //textGraphics.fillRectangle(new TerminalPosition(0,0),new TerminalSize(40,20), ' ');

        //draw Alien
        alienViewer.draw(model.getAlien().getPosition(),
                model.getAlien().getTransition_x(),
                model.getAlien().getTransition_y(),
                this.textGraphics);

        //draw Ship
        shipViewer.draw(model.getShip().getPosition(),
                model.getShip().getTransition_x(),
                model.getShip().getTransition_y(),
                this.textGraphics);

        //draw tiles
        for(int i=0;i<40;i++){
            for(int j=0;j<20;j++) {
                if(model.getTiles()[j][i] != null){
                    if(j == 0 || model.getTiles()[j-1][i] == null) { // no block over -> grass
                        tileViewer.draw(model.getTiles()[j][i].getPosition(),
                                model.getTiles()[j][i].getTransition_x(),
                                model.getTiles()[j][i].getTransition_y(),
                                this.textGraphics);
                    }
                    else{
                        tile2Viewer.draw(model.getTiles()[j][i].getPosition(),
                                model.getTiles()[j][i].getTransition_x(),
                                model.getTiles()[j][i].getTransition_y(),
                                this.textGraphics);
                    }
                }
            }
        }
        //draw monsters
        for(int i=0;i<40;i++) {
            for (int j = 0; j < 20; j++) {
                if (model.getMonsters()[j][i] != null) {
                    monsterViewer.draw(model.getMonsters()[j][i].getPosition(),
                            model.getMonsters()[j][i].getTransition_x(),
                            model.getMonsters()[j][i].getTransition_y(),
                            this.textGraphics);

                }
            }
        }
        // Draw spikes
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 20; j++) {
                if (model.getSpikes()[j][i] != null) {
                    spikeViewer.draw(model.getSpikes()[j][i].getPosition(),
                            model.getSpikes()[j][i].getTransition_x(),
                            model.getSpikes()[j][i].getTransition_y(),
                            this.textGraphics);
                }
            }
        }


        try{
            screen.refresh();
        } catch (IOException ignored) {}
    }
}
