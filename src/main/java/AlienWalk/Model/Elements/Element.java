package AlienWalk.Model.Elements;

public class Element {
    private Position position;

    public Element(int x, int y){
        this.position = new Position(x,y);
    }

    public Position getPosition() {
        return this.position;
    }
}
