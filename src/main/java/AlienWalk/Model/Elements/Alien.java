package AlienWalk.Model.Elements;

public class Alien extends Element {
    int jumpState;
    private final int START_JUMP_STATE = 18;

    // New fields for crystal collection
    private int collectedCrystals = 0; // Counter for collected crystals

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
        jumpState = START_JUMP_STATE;
    }

    // Methods for collecting crystals
    public void collectCrystal() {
        this.collectedCrystals++;
    }

    public int getCollectedCrystals() {
        return collectedCrystals;
    }
}
