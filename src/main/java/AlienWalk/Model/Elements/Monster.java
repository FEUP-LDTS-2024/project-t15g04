package AlienWalk.Model.Elements;

public class Monster extends Element {
    private int health;

    public Monster(Position position, int health) {
        super(position);
        this.health = health;
    }

    // Method to move the monster in the x and y direction using transition logic
    public void move(int deltaX, int deltaY) {
        if (deltaX > 0) {
            this.right();
        } else if (deltaX < 0) {
            this.left();
        }
        if (deltaY > 0) {
            this.down();
        } else if (deltaY < 0) {
            this.up();
        }
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0; // Ensure health doesn't go below 0
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}
