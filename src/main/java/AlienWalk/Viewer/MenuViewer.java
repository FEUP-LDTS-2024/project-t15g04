package AlienWalk.Viewer;

import AlienWalk.Model.Menu;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class MenuViewer extends Viewer<Menu> {

    public MenuViewer(TerminalScreen screen) {
        super(screen);
    }

    @Override
    public int read(){
        System.out.println("menu read vals: " + String.valueOf(quit)+ String.valueOf(up)
        + String.valueOf(down)+ String.valueOf(enter));
        if(quit) return 0;
        if(up) return 1;
        if(down) return 2;
        if(enter) return 3;
        return -1; //no input
    }

    @Override
    public void draw(Menu model) {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00de75"));
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
