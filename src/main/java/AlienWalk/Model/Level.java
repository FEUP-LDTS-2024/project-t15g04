package AlienWalk.Model;

import AlienWalk.Model.Elements.Alien;
import AlienWalk.Model.Elements.Monster;
import AlienWalk.Model.Elements.Tile;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

public class Level {
    private int which; // in constructor
    private int width;
    private int height;
    private Alien alien;
    private Monster[][] monsters;
    private Tile[][] tiles;

    public Level(){
        which = 1;
        width = 20;
        height = 40;
        alien = new Alien(0,0);
        monsters = new Monster[height][width];
        tiles = new Tile[height][width];
    }

    public boolean checkColision(){
        // TO DO
        return false;
    }

    public Alien getAlien() {
        return alien;
    }

    public Monster[][] getMonsters() {
        return monsters;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public boolean isAlienOnTitle(){
        return tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()] != null;
    }
}
