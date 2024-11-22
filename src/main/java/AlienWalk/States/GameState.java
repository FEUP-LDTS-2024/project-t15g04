package AlienWalk.States;

import AlienWalk.Controller.GameController;
import AlienWalk.Game;
import AlienWalk.Model.Level;
import AlienWalk.Viewer.GameViewer;

public class GameState extends State<Level>{
    public GameState(Level level, GameViewer gameViewer, GameController gameController){
        super(level, gameController, gameViewer);
    }

    @Override
    public void step(Game game) {

    }
}
