package AlienWalk.Model.Elements;

public class Alien extends Element {
    private int jumpState = 0;
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
        jumpState = 18;
    }

    // Methods for collecting crystals
    public void collectCrystal() {
        this.collectedCrystals++;
    }

    public int getCollectedCrystals() {
        return collectedCrystals;
    }
}
