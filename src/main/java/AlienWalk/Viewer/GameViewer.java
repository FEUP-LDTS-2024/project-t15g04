package AlienWalk.Viewer;

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

    public GameViewer(Screen screen){
        super(screen);
    }


    @Override
    public KeyStroke read() {
        KeyStroke k;
        try {
            k = this.screen.readInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return k;
    }

    @Override
    public void draw(Level model) {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00de75"));
        textGraphics.fillRectangle(new TerminalPosition(0,0),new TerminalSize(40,20), ' ');

        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(model.getAlien().getPosition().getX(),
                model.getAlien().getPosition().getY()), "X");

        try{
            screen.refresh();
        } catch (IOException ignored) {}
    }
}
