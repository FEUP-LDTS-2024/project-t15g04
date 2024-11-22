package AlienWalk.Model;

import AlienWalk.Model.Elements.Alien;
import AlienWalk.Model.Elements.Monster;
import AlienWalk.Model.Elements.Tile;

import java.util.List;

public class Level {
    private int which; // in constructor
    private int width;
    private int height;
    private Alien alien;
    private List<Monster> monsters;
    private List<Tile> tiles;

    public boolean checkColision(){
        // TO DO
        return false;
    }
}
