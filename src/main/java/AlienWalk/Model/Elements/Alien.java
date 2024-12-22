package AlienWalk.Model.Elements;

public class Alien extends Element {
    private int jumpState = 0;

    public Alien(int x, int y) {
        super(x, y);
    }

    // Existing methods to handle jump state
    public int getJumpState(){
        return jumpState;
    }

    public void setJumpState(int newJumpState){
        jumpState = newJumpState;
    }

    public void startJump(){
        jumpState = 18;
    }

}
