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

import java.io.IOException;

public class GameViewer extends Viewer<Level>{
    private Level level;
    private TextGraphics textGraphics;
    private ElementViewer tileViewer;
    private ElementViewer alienViewer;
    private ElementViewer monsterViewer;

    public GameViewer(Screen screen) throws IOException {
        super(screen);
        this.textGraphics = screen.newTextGraphics();
        tileViewer = new ElementViewer("ElementsImages/Tile.png");
        alienViewer = new ElementViewer("ElementsImages/Alien.png");
        monsterViewer = new ElementViewer("ElementsImages/Monster.png");
    }

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

        //draw tiles
        for(int i=0;i<40;i++){
            for(int j=0;j<20;j++) {
                if(model.getTiles()[j][i] != null){
                    tileViewer.draw(model.getTiles()[j][i].getPosition(),
                            model.getTiles()[j][i].getTransition_x(),
                            model.getTiles()[j][i].getTransition_y(),
                            this.textGraphics);
                }
            }
        }
        //draw monsters


        try{
            screen.refresh();
        } catch (IOException ignored) {}
    }
}
