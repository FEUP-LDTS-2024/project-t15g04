package AlienWalk.Viewer;

import AlienWalk.Model.Menu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(Screen screen) {
        super(screen);
    }

    @Override
    public KeyStroke read(){
        KeyStroke k;
        try {
            k = this.screen.readInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return k;
    }

    @Override
    public void draw(Menu model) {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00de00"));
        textGraphics.fillRectangle(new TerminalPosition(0,0),new TerminalSize(40,20), ' ');

        switch (model.getCurrent()){
            case Start:
                textGraphics.putString(3,5,"START", SGR.UNDERLINE);
                textGraphics.putString(3,10,"SETTINGS");
                textGraphics.putString(3,15,"QUIT");
                break;
            case Settings:
                textGraphics.putString(3,5,"START");
                textGraphics.putString(3,10,"SETTINGS", SGR.UNDERLINE);
                textGraphics.putString(3,15,"QUIT");
                break;
            case Quit:
                textGraphics.putString(3,5,"START");
                textGraphics.putString(3,10,"SETTINGS");
                textGraphics.putString(3,15,"QUIT", SGR.UNDERLINE);
                break;
        }
        try{
            screen.refresh();
        } catch (IOException ignored) {}
    }
}
