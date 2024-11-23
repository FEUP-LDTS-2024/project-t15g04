package AlienWalk.Viewer;

import com.googlecode.lanterna.screen.Screen;

import  com.googlecode.lanterna.input.KeyStroke;
import javax.swing.text.View;
import java.io.IOException;

public abstract class Viewer<T> {
    Screen screen;

    public Viewer(Screen screen){
        this.screen = screen;
    }

    public abstract void draw(T model);

    public KeyStroke read(){
        KeyStroke k;


        try {
            k = this.screen.readInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return k;
    }

    public KeyStroke poll() {
        KeyStroke k;
        try {
            k = this.screen.pollInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return k;
    }

    public void flushInput() throws IOException {
        KeyStroke key;
        // Continuously poll until no input is left
        while ((key = screen.pollInput()) != null) {
            // Optionally, process or log the flushed keys
        }
    }
}
