package AlienWalk.Model.Elements;

public class Crystal extends Element {
    public Crystal(int x, int y) {
        super(x, y);
    }

    // Method to check if the crystal collides with the alien
    public boolean collidesWith(Alien alien) {
        return this.getPosition().equals(alien.getPosition());
    }
}
