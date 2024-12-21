package AlienWalk.Model.Elements;

public class Monster extends Element{
    private boolean goingRight;
    //variable to make monsters not stuck in turning points
    private int justTurned;

    public Monster(int  x, int y){
        super(x,y);
        goingRight = true;
    }

    public void move(TurningPoint[] turningPoints){
        if(isGoingRight()){
            this.right();
            if(turningPoints[this.getPosition().getX()] != null && justTurned <= 0){
                goingRight = false;
                justTurned = 5;
            }
        }
        else{
            this.left();
            if(turningPoints[this.getPosition().getX()] != null && justTurned <= 0){
                goingRight = true;
                justTurned = 5;
            }
        }
        justTurned -= 1;
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }
}
