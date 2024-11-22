package AlienWalk.Viewer;

import com.googlecode.lanterna.screen.Screen;

import  com.googlecode.lanterna.input.KeyStroke;
import javax.swing.text.View;

public abstract class Viewer<T> {
    Screen screen;

    public Viewer(Screen screen){
        this.screen = screen;
    }

    public abstract KeyStroke read();

    public abstract void draw(T model);
}
