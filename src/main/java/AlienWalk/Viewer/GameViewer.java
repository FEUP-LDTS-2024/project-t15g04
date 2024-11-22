package AlienWalk.Viewer;

import AlienWalk.Model.Level;
import com.googlecode.lanterna.screen.Screen;

public class GameViewer extends Viewer{
    private Level level;

    public GameViewer(Level level, Screen screen){
        super(screen);
        this.level = level;
    }


}
