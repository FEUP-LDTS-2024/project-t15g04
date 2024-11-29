package AlienWalk.Model.Elements;

public class Alien extends Element {
    int jumpState;
    // from 0-n. If there is jump than jumpState goes to n and hero is going up until jumpState == 0 again
    // jumpState descends every step by 1
    private final int START_JUMP_STATE = 18;

    public Alien(int x, int y) {
        super(x, y);
    }

//    public void updateJumpState(){
//        jumpState -= 1;
//    }

    public int getJumpState(){
        return jumpState;
    }

    public void setJumpState(int newJumpState){
        jumpState = newJumpState;
    }

    public void start_jump(){
        jumpState = START_JUMP_STATE;
    }
}
