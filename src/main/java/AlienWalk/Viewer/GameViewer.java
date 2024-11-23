package AlienWalk.Viewer;

import AlienWalk.Model.Elements.Tile;
import AlienWalk.Model.Level;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class GameViewer extends Viewer<Level>{
    private Level level;
    private TextGraphics textGraphics;

    public GameViewer(Screen screen){
        super(screen);
        this.textGraphics = screen.newTextGraphics();
    }

    @Override
    public void draw(Level model) {
        screen.clear();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00de75"));
        textGraphics.fillRectangle(new TerminalPosition(0,0),new TerminalSize(40,20), ' ');

        //draw Alien
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(model.getAlien().getPosition().getX(),
                                                    model.getAlien().getPosition().getY()), "X");

        //draw tiles
        for(int i=0; i<20; i++){
            for(int j=0; j<40; j++){
                if(model.getTiles()[i][j] != null){
                    textGraphics.putString(new TerminalPosition(j,i), "T");
                }
            }
        }
        try{
            screen.refresh();
        } catch (IOException ignored) {}
    }
}
