package AlienWalk.Model.Elements;

public class Element {
    private Position position; //top left corner
    private int transition_x;
    private int transition_y;
    private static final int TRANSMISSION_STEP = 2;

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

    public void setTransition_x(int transition_x) {
        this.transition_x = transition_x;
    }

    public int getTransitionY() {
        return transition_y;
    }

    public void setTransition_y(int transition_y) {
        this.transition_y = transition_y;
    }

    public void up(){
        transition_y -= TRANSMISSION_STEP;
        if(transition_y == -8){
            position.increaseY();
            transition_y = 0;
        }
    }
    public void down(){
        transition_y += TRANSMISSION_STEP;
        if(transition_y == 8){
            position.decreaseY();
            transition_y = 0;
        }
    }
    public void left(){
        transition_x -= TRANSMISSION_STEP;
        if(transition_x == -8){
            position.decreaseX();
            transition_x = 0;
        }
    }
    public void right(){
        transition_x += TRANSMISSION_STEP;
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
}
