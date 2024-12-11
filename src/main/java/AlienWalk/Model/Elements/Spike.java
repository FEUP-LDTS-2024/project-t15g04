package AlienWalk.Model.Elements;

public class Spike extends Element {
    public Spike(int x, int y) {
        super(x, y);
    }

    public boolean collidesWith(Alien alien) {
        // Check if the alien's position matches the spike's position
        return this.getPosition().equals(alien.getPosition());
    }
}
