package AlienWalk.Model;

import AlienWalk.Model.Elements.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Level {
    public int which; // in constructor
    private int width;
    private int height;
    private Alien alien;
    private Monster[][] monsters;
    private Spike [][] spikes;
    private Tile[][] tiles;
    private TurningPoint[][] turningPoints;
    private Ship ship;
    private List<Crystal> crystals; // New list to hold crystals
    private static final int MAX_LEVEL = 3;

    public Level(){
        which = 1;
        width = 40;
        height = 20;
        alien = new Alien(0,0);
        ship = new Ship(0,0);
        monsters = new Monster[height][width];
        spikes = new Spike[height][width];
        tiles = new Tile[height][width];
        turningPoints = new TurningPoint[height][width];
        crystals = new ArrayList<>(); // Initialize the crystal list
        populateLevel("Levels/Level" + String.valueOf(which) + ".txt" );
    }

    public void populateLevel(String filePath){
        // Clear previous elements
        alien = new Alien(0,0);
        ship = new Ship(0,0);
        monsters = new Monster[height][width];
        tiles = new Tile[height][width];
        spikes = new Spike[height][width];
        crystals.clear(); // Clear any existing crystals

        try (InputStream inputStream = Level.class.getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            int character;
            int i = 0;
            int j = 0;
            while ((character = bufferedReader.read()) != -1) {
                switch((char) character){
                    case '\n':
                        j += 1;
                        i = 0;
                        break;
                    case ' ':
                        i += 1;
                        break;
                    case 'T':
                        this.tiles[j][i] = new Tile(i,j);
                        i += 1;
                        break;
                    case 'A':
                        this.alien.getPosition().setY(j);
                        this.alien.getPosition().setX(i);
                        i += 1;
                        break;
                    case 'S': // ship
                        this.ship.getPosition().setY(j);
                        this.ship.getPosition().setX(i);
                        i += 1;
                        break;
                    case 'M': // monster
                        this.monsters[j][i] = new Monster(i,j);
                        i += 1;
                        break;
                    case 'P': // turning point
                        this.turningPoints[j][i] = new TurningPoint(i,j);
                        i += 1;
                        break;
                    case 'K': // spike
                        this.spikes[j][i] = new Spike(i, j);
                        i += 1;
                        break;
                    case 'C': // crystal (new symbol for crystal)
                        this.crystals.add(new Crystal(i, j)); // Add crystal to the list
                        i += 1;
                        break;
                }
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public boolean alienInShip(){
        return ship.collidesWith(this.alien);
    }

    public boolean nextLevel(){
        which += 1;
        if(which > MAX_LEVEL){
            return false; // no more levels
        }
        populateLevel("Levels/Level" + String.valueOf(which) + ".txt" );
        return true;
    }

    public Alien getAlien() {
        return alien;
    }

    public Ship getShip(){
        return ship;
    }

    public Monster[][] getMonsters() {
        return monsters;
    }

    public Spike[][] getSpikes() {
        return spikes;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public TurningPoint[][] getTurningPoints(){
        return turningPoints;
    }

    public List<Crystal> getCrystals() {
        return crystals;
    }

    // Check collision with crystals
    public boolean checkCollisionWithCrystals() {
        for (Crystal crystal : crystals) {
            if (crystal.collidesWith(alien)) {
                crystals.remove(crystal); // Remove the crystal once collected
                alien.collectCrystal(); // Increment the collected crystals counter
                return true;
            }
        }
        return false;
    }

    public boolean isTileAbove(){
        boolean tmp = false;
        if(alien.getTransitionX()>0){
            tmp = tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()+1] != null;
        }
        if(alien.getTransitionX()<0){
            tmp = tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()-1] != null;
        }

        return tmp || (tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()] != null);
    }

    public boolean isTileBelow(){
        if(alien.getTransitionY() < 0) return false;
        boolean tmp = false;
        if(alien.getTransitionX()>0){
            tmp = tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()+1] != null;
        }
        if(alien.getTransitionX()<0){
            tmp = tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()-1] != null;
        }

        return tmp || (tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()] != null);
    }

    public boolean isTileOnLeft(){
        if(alien.getTransitionX() > 0) return false;
        boolean tmp = false;
        if(alien.getPosition().getX() == 0) return true;

        if(alien.getTransitionY()>0){
            tmp = tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()-1] != null;
        }
        if(alien.getTransitionY()<0){
            tmp = tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()-1] != null;
        }

        return tmp || (tiles[alien.getPosition().getY()][alien.getPosition().getX()-1] != null);
    }

    public boolean isTileOnRight(){
        if(alien.getTransitionX() < 0) return false;
        if(alien.getPosition().getX() == width-1) return true;

        boolean tmp = false;
        if(alien.getTransitionY()>0){
            tmp = tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()+1] != null;
        }
        if(alien.getTransitionY()<0){
            tmp = tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()+1] != null;
        }

        return tmp || (tiles[alien.getPosition().getY()][alien.getPosition().getX()+1] != null);
    }

    public boolean checkCollision(){
        return (checkCollisionWithSpikes() || checkCollisionWithMonsters() || checkCollisionWithCrystals());
    }

    public boolean checkCollisionWithSpikes() {
        for(int i=0;i<40;i++){
            for(int j=0;j<20;j++){
                if(spikes[j][i] != null){
                    if(spikes[j][i].collidesWith(this.alien)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkCollisionWithMonsters() {
        for(int i=0;i<40;i++){
            for(int j=0;j<20;j++){
                if(monsters[j][i] != null){
                    if(monsters[j][i].collidesWith(this.alien)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
