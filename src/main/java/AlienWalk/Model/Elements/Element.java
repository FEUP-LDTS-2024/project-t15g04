package AlienWalk.Model.Elements;

public class Element {
    private Position position; // Top left corner
    private int transition_x;
    private int transition_y;
    private static final int TRANSMISSION_STEP = 2;

    public Element(int x, int y) {
        this.position = new Position(x, y);
        this.transition_x = 0;
        this.transition_y = 0;
    }

    // New constructor to accept a Position object
    public Element(Position position) {
        this.position = position;
        this.transition_x = 0;
        this.transition_y = 0;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public void up() {
        transition_y -= TRANSMISSION_STEP;
        if (transition_y == -8) {
            position.increaseY();
            transition_y = 0;
        }
    }
    public void down() {
        transition_y += TRANSMISSION_STEP;
        if (transition_y == 8) {
            position.decreaseY();
            transition_y = 0;
        }
    }
    public void left() {
        transition_x -= TRANSMISSION_STEP;
        if (transition_x == -8) {
            position.decreaseX();
            transition_x = 0;
        }
    }
    public void right() {
        transition_x += TRANSMISSION_STEP;
        if (transition_x == 8) {
            position.increaseX();
            transition_x = 0;

        }
    }
    public class Monster extends Element {
        public Monster(Position position) {
            super(position); // Call the Element constructor to initialize the position
        }
        // Additional behavior specific to Monster
        public void moveRandomly() {
            // Generate random movement (dx and dy can be -1, 0, or 1)
            int dx = (int) (Math.random() * 3) - 1;
            int dy = (int) (Math.random() * 3) - 1;
            // Move the monster using inherited methods
            if (dx > 0) {
                this.right();
            } else if (dx < 0) {
                this.left();
            }

            if (dy > 0) {
                this.down();
            } else if (dy < 0) {
                this.up();
            }
        }
    }
}
