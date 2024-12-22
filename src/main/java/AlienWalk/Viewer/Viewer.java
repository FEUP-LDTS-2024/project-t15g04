package AlienWalk.Viewer;

import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public abstract class Viewer<T> {
    TerminalScreen screen;
    boolean left, right, up, down, jump, enter, quit;

    public Viewer(TerminalScreen screen){
        this.screen = screen;
        ((AWTTerminalFrame) this.screen.getTerminal()).getComponent(0).addKeyListener(new MyKeyAdapter());
        left = false;
        right = false;
        up = false;
        down = false;
        jump = false;
        enter = false;
        quit = false;
    }

    public abstract void draw(T model);

    public abstract int read();

    // maybe change according to strategy pattern?
    public class MyKeyAdapter implements KeyListener{

        public MyKeyAdapter(){}

        @Override
        public void keyTyped(KeyEvent keyEvent) {}

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            int code = keyEvent.getKeyCode();
            switch(code){
                case(VK_LEFT):
                    left = true;
                    break;
                case(VK_RIGHT):
                    right = true;
                    break;
                case(VK_UP):
                    up = true;
                    break;
                case(VK_DOWN):
                    down = true;
                    break;
                case(VK_ENTER):
                    enter = true;
                    break;
                case(VK_SPACE):
                    jump = true;
                    break;
                case(VK_ESCAPE):
                    quit = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            int code = keyEvent.getKeyCode();
            switch(code){
                case(VK_LEFT):
                    left = false;
                case(VK_RIGHT):
                    right = false;
                case(VK_UP):
                    up = false;
                case(VK_DOWN):
                    down = false;
                case(VK_ENTER):
                    enter = false;
                case(VK_SPACE):
                    jump = false;
                case(VK_ESCAPE):
                    quit = false;
            }
        }
    }

    // setters for testing
    public void setUp(boolean x){
        up = x;
    }
    public void setDown(boolean x){
        down = x;
    }
    public void setLeft(boolean x){
        left = x;
    }
    public void setRight(boolean x){
        right = x;
    }
    public void setEnter(boolean x){
        enter = x;
    }
    public void setQuit(boolean x){
        quit = x;
    }

}
