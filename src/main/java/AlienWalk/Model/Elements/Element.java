package AlienWalk.Model.Elements;

public class Element {
    private Position position; //top left corner
    private int transition_x;
    private int transition_y;

    public Element(int x, int y){
        this.position = new Position(x,y);
        transition_x=0;
        transition_y=0;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getTransition_x() {
        return transition_x;
    }

    public void setTransition_x(int transition_x) {
        this.transition_x = transition_x;
    }

    public int getTransition_y() {
        return transition_y;
    }

    public void setTransition_y(int transition_y) {
        this.transition_y = transition_y;
    }

    public void up(){
        transition_y -= 2;
        if(transition_y == -8){
            position.increaseY();
            transition_y = 0;
        }
    }
    public void down(){
        transition_y += 2;
        if(transition_y == 8){
            position.decreaseY();
            transition_y = 0;
        }
    }
    public void left(){
        transition_x -= 2;
        if(transition_x == -8){
            position.decreaseX();
            transition_x = 0;
        }
    }
    public void right(){
        transition_x += 2;
        if(transition_x == 8){
            position.increaseX();
            transition_x = 0;
        }
    }
}
