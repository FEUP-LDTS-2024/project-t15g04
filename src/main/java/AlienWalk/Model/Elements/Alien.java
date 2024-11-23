package AlienWalk.Model.Elements;

public class Alien extends Element {
    int jumpState;
    // from 0-n. If there is jump than jumpState goes to n and hero is going up until jumpState == 0 again
    // jumpState descends every step by 1

    public Alien(int x, int y) {
        super(x, y);
    }

    public int getJumpState(){
        return jumpState;
    }

    public void setJumpState(int n){
        this.jumpState = n;
    }
}
