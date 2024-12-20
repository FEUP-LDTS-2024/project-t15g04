package AlienWalk.Model;

import AlienWalk.Model.Elements.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Level {
    private int which; // in constructor
    private final int width;
    private final int height;
    private Alien alien;
    private List<Monster> monsters;
    private List<Spike> spikes;
    private Tile[][] tiles;
    private TurningPoint[][] turningPoints;
    private Ship ship;
    private List<Crystal> crystals; // New list to hold crystals
    private static final int MAX_LEVEL = 5;

    public Level(){
        which = 1;
        width = 40;
        height = 20;
        alien = new Alien(0,0);
        ship = new Ship(0,0);
        monsters = new ArrayList<>();
        spikes = new ArrayList<>();
        tiles = new Tile[height][width];
        turningPoints = new TurningPoint[height][width];
        crystals = new ArrayList<>(); // Initialize the crystal list
        populateLevel();
    }

    public void populateLevel() {
        // Clear previous elements
        alien = new Alien(0, 0);
        ship = new Ship(0, 0);
        monsters = new ArrayList<>();
        tiles = new Tile[height][width];
        turningPoints = new TurningPoint[height][width];
        spikes = new ArrayList<>();
        crystals.clear(); // Clear any existing crystals

        try {
            InputStream inputStream = Level.class.getClassLoader().getResourceAsStream("Levels/Level" + String.valueOf(which) + ".txt");
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader);

            int character;
            int i = 0;
            int j = 0;
            while ((character = bufferedReader.read()) != -1) {
                switch ((char) character) {
                    case '\n':
                        j += 1;
                        i = 0;
                        break;
                    case ' ':
                        i += 1;
                        break;
                    case 'T':
                        this.tiles[j][i] = new Tile(i, j);
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
                        this.monsters.add(new Monster(i, j));
                        i += 1;
                        break;
                    case 'P': // turning point
                        this.turningPoints[j][i] = new TurningPoint(i, j);
                        i += 1;
                        break;
                    case 'K': // spike
                        this.spikes.add(new Spike(i, j));
                        i += 1;
                        break;
                    case 'C': // crystal (new symbol for crystal)
                        this.crystals.add(new Crystal(i, j)); // Add crystal to the list
                        i += 1;
                        break;
                }
            }
        }
        catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    // Moves alien and monster to starting positions,
    // Does not respawn crystals
    // (used in case of hostile collision)
    public void repopulateLevel(){
        alien = new Alien(0,0);
        monsters = new ArrayList<>();

        try {
            InputStream inputStream = Level.class.getClassLoader().getResourceAsStream("Levels/Level" + String.valueOf(which) + ".txt");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            int character;
            int i = 0;
            int j = 0;
            while ((character = bufferedReader.read()) != -1) {
                switch((char) character){
                    case '\n':
                        j += 1;
                        i = 0;
                        break;
                    case 'A':
                        this.alien.getPosition().setY(j);
                        this.alien.getPosition().setX(i);
                        i += 1;
                        break;
                    case 'M': // monster
                        this.monsters.add(new Monster(i,j));
                        i += 1;
                        break;
                    default:
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
        populateLevel();
        return true;
    }

    public Alien getAlien() {
        return alien;
    }

    public Ship getShip(){
        return ship;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Spike> getSpikes() {
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
        return (checkCollisionWithSpikes() || checkCollisionWithMonsters());
    }
    public void checkCollisionWithCrystals() {
        Iterator<Crystal> iterator = crystals.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().collidesWith(alien)) {
                iterator.remove();
                break;
            }
        }
    }

    public boolean checkCollisionWithSpikes() {
        for(Spike spike : spikes){
            if(spike.collidesWith(alien)){
                return true;
            }
        }
        return false;
    }

    private boolean checkCollisionWithMonsters() {
        for(Monster monster : monsters){
            if(monster.collidesWith(alien)){
                return true;
            }
        }
        return false;
    }

    // Alien functions added do Level to make
    // using mocks easier (GameController uses this instead of .getAlien().smh)
    public void alienLeft(){
        alien.left();
    }
    public void alienRight(){
        alien.right();
    }
    public void alienUp(){
        alien.up();
    }
    public void alienDown(){
        alien.down();
    }
    public int alienGetJumpState(){
        return alien.getJumpState();
    }
    public void alienSetJumpState(int newJumpState) {
        alien.setJumpState(newJumpState);
    }
    public void alienStartJump(){
        alien.startJump();
    }
    public int getWhich(){
        return which;
    }
    // just for tests so number can be bigger than MAX_LEVEL
    public void setWhich(int number){
        which = number;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
