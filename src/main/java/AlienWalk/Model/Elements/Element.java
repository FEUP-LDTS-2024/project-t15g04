package AlienWalk.Model.Elements;

import static java.lang.Math.abs;

import static java.lang.Math.abs;

public class Element {
    private Position position; //top left corner
    private int transition_x;
    private int transition_y;
    private static final int X_TRANSMISSION_STEP = 2;
    private static final int Y_TRANSMISSION_STEP = 2;

    public Element(int x, int y){
        this.position = new Position(x,y);
        transition_x=0;
        transition_y=0;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getTransitionX() {
        return transition_x;
    }

    public void setTransitionX(int transition_x) {
        this.transition_x = transition_x;
    }

    public int getTransitionY() {
        return transition_y;
    }

    public void setTransitionY(int transition_y) {
        this.transition_y = transition_y;
    }

    public void up(){
        transition_y -= Y_TRANSMISSION_STEP;
        if(transition_y == -8){
            position.increaseY();
            transition_y = 0;
        }
    }
    public void down(){
        transition_y += Y_TRANSMISSION_STEP;
        if(transition_y == 8){
            position.decreaseY();
            transition_y = 0;
        }
    }
    public void left(){
        transition_x -= X_TRANSMISSION_STEP;
        if(transition_x == -8){
            position.decreaseX();
            transition_x = 0;
        }
    }
    public void right(){
        transition_x += X_TRANSMISSION_STEP;
        if(transition_x == 8){
            position.increaseX();
            transition_x = 0;
        }
    }

    public int getPosX(){
        return this.position.getX();
    }

    public int getPosY(){
        return this.position.getY();
    }

    public boolean checkCollisionOnX(Element element) {
        switch (element.getPosX() - this.getPosX()) {
            case (-2):
                if (element.getTransitionX() - this.getTransitionX() > 8) {
                    return true;
                }
                break;
            case (-1):
                if (element.getTransitionX() - this.getTransitionX() > 0) {
                    return true;
                }
                break;
            case (0):
                if (!(abs(element.getTransitionX() - this.getTransitionX()) >= 8)) {
                    return true;
                }
                break;
            case (1):
                if (element.getTransitionX() - this.getTransitionX() < 0) {
                    return true;
                }
                break;
            case (2):
                if (element.getTransitionX() - this.getTransitionX() < -8) {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    public boolean collidesWith(Element element){
        if(this.getPosY() - element.getPosY() == 0){ //same y
            return checkCollisionOnX(element);
        }
        if(this.getPosY() - element.getPosY() == 1){ //this below
            if(element.getTransitionY() > 0){ //element transitions into this y
                return checkCollisionOnX(element);
            }
        }
        if(this.getPosY() - element.getPosY() == -1){ //this above
            if(element.getTransitionY() < 0){ //element transitions into this y
                return checkCollisionOnX(element);
            }
        }
        return false;
    }
}
