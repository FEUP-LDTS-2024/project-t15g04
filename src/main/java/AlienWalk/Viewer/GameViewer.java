package AlienWalk.Viewer;

import AlienWalk.Model.Level;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class GameViewer extends Viewer<Level>{
    private Level level;

    public GameViewer(Screen screen){
        super(screen);
    }


    @Override
    public KeyStroke read() {
        return null;
    }

    @Override
    public void draw(Level model) {
//        TextGraphics textGraphics = screen.newTextGraphics();
//        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00de00"));
    }
}
