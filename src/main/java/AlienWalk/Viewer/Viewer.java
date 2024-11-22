package AlienWalk.Viewer;

import com.googlecode.lanterna.screen.Screen;

import javax.swing.text.View;

public abstract class Viewer {
    Screen screen;

    public Viewer(Screen screen){
        this.screen = screen;
    }
}
