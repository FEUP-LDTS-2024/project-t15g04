package AlienWalk.Model;

import AlienWalk.Model.Elements.Alien;
import AlienWalk.Model.Elements.Monster;
import AlienWalk.Model.Elements.Tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        width = 40;
        height = 20;
        alien = new Alien(0,0);
        monsters = new Monster[height][width];
        tiles = new Tile[height][width];
    }

    public void populateLevel(String filePath){
        // Access the resource
        try (InputStream inputStream = Level.class.getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            int character;
            // Read character by character
            int i = 0;
            int j = 0;
            while ((character = bufferedReader.read()) != -1) {
                System.out.println(i);
                System.out.println(j);
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
                }
                System.out.print((char) character); // Print each character
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("File not found in resources/Levels.");
        }
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

    public boolean isTileAbove(){
        boolean tmp = false;
        if(alien.getTransition_x()>0){
            tmp = tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()+1] != null;
        }
        if(alien.getTransition_x()<0){
            tmp = tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()-1] != null;
        }

        return tmp || (tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()] != null);
    }

    public boolean isTileBelow(){
        if(alien.getTransition_y() < 0) return false;
        boolean tmp = false;
        if(alien.getTransition_x()>0){
            tmp = tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()+1] != null;
        }
        if(alien.getTransition_x()<0){
            tmp = tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()-1] != null;
        }

        return tmp || (tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()] != null);
    }

    public boolean isTileOnLeft(){
        boolean tmp = false;
        if(alien.getPosition().getX() == 0) return true;

        if(alien.getTransition_y()>0){
            tmp = tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()-1] != null;
        }
        if(alien.getTransition_y()<0){
            tmp = tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()-1] != null;
        }

        return tmp || (tiles[alien.getPosition().getY()][alien.getPosition().getX()-1] != null);
    }

    public boolean isTileOnRight(){
        if(alien.getPosition().getX() == width-1) return true;

        boolean tmp = false;
        if(alien.getTransition_y()>0){
            tmp = tiles[alien.getPosition().getY() + 1][alien.getPosition().getX()+1] != null;
        }
        if(alien.getTransition_y()<0){
            tmp = tiles[alien.getPosition().getY() - 1][alien.getPosition().getX()+1] != null;
        }

        return tmp || (tiles[alien.getPosition().getY()][alien.getPosition().getX()+1] != null);
    }
}
