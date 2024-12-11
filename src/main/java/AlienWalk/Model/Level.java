package AlienWalk.Model;

import AlienWalk.Model.Elements.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class Level {
    public int which; // in constructor
    private int width;
    private int height;
    private Alien alien;
    private Monster[][] monsters;
    private Tile[][] tiles;
    private TurningPoint[][] turningPoints;
    private Ship ship;
    private static final int MAX_LEVEL = 3;

    public Level(){
        which = 1;
        width = 40;
        height = 20;
        alien = new Alien(0,0);
        ship = new Ship(0,0);
        monsters = new Monster[height][width];
        tiles = new Tile[height][width];
        turningPoints = new TurningPoint[height][width];
        populateLevel("Levels/Level" + String.valueOf(which) + ".txt" );
    }

    public void populateLevel(String filePath){
        //clear
        alien = new Alien(0,0);
        ship = new Ship(0,0);
        monsters = new Monster[height][width];
        tiles = new Tile[height][width];
        // Access the resource
        try (InputStream inputStream = Level.class.getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            int character;
            // Read character by character
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
                    case 'S': //ship
                        this.ship.getPosition().setY(j);
                        this.ship.getPosition().setX(i);
                        i += 1;
                        break;
                    case 'M': //monster
                        this.monsters[j][i] = new Monster(i,j);
                        i += 1;
                        break;
                    case 'P': //turning Point
                        this.turningPoints[j][i] = new TurningPoint(i,j);
                        i += 1;
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("File not found in resources/Levels.");
        }
    }

    public boolean checkColision(){
        Monster monster;
        for(int j=0;j<20;j++){
            for(int i=0;i<40;i++){
                if(monsters[j][i] != null){
                    monster = monsters[j][i];
                    if(monster.getPosY() - alien.getPosY() == 0){ //same y
                        switch(alien.getPosX() - monster.getPosX()){
                            case(-2):
                                if(alien.getTransitionX() - monster.getTransitionX() > 8) {return true;}
                                break;
                            case(-1):
                                if(alien.getTransitionX() - monster.getTransitionX() > 0) {return true;}
                                break;
                            case(0):
                                if(!(abs(alien.getTransitionX() - monster.getTransitionX()) > 0)) {return true;}
                                break;
                            case(1):
                                if(alien.getTransitionX() - monster.getTransitionX() < 0) {return true;}
                                break;
                            case(2):
                                if(alien.getTransitionX() - monster.getTransitionX() < -8) {
//                                    System.out.println("option 2");
//                                    System.out.println("-: " + String.valueOf(alien.getPosX() - monster.getPosX()));
//                                    System.out.println("A pos: " + String.valueOf(alien.getPosX()) + ":" +String.valueOf(alien.getPosY()));
//                                    System.out.println("A tra: " + String.valueOf(alien.getTransitionX()) + ":" +String.valueOf(alien.getTransitionY()));
//                                    System.out.println("M pos: " + String.valueOf(monster.getPosX()) + ":" +String.valueOf(monster.getPosY()));
//                                    System.out.println("M tra: " + String.valueOf(monster.getTransitionX()) + ":" +String.valueOf(monster.getTransitionY()));
                                    return true;}
                                break;
                            default:
                                break;

                        }
                    }

                    if(monster.getPosY() - alien.getPosY() == 1){ //monster below

                    }
                    if(monster.getPosY() - alien.getPosY() == -1){ //monster above

                    }
                }
            }
        }
        return false;
    }

    public boolean alienInShip(){
        if(alien.getPosition().equals(ship.getPosition())){
            return true;
        }
        return false;
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

    public Tile[][] getTiles() {
        return tiles;
    }

    public TurningPoint[][] getTurningPoints(){
        return turningPoints;
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
}
