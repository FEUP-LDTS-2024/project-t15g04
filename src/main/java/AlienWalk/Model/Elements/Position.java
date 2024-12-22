package AlienWalk.Model.Elements;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position p) {
        return this.x == p.getX() && this.y == p.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseY(){
        this.y = this.y - 1;
    }

    public void decreaseY(){
        this.y = this.y + 1;
    }

    public void increaseX(){
        this.x = this.x + 1;
    }

    public void decreaseX(){
        this.x = this.x - 1;
    }
}
