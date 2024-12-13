package AlienWalk.Model.Elements;

public class Spike extends Element {
    public Spike(int x, int y) {
        super(x, y);
    }

    // also looks at the element transitionY because spikes are shorter than monsters
    @Override
    public boolean collidesWith(Element element) {
        if(this.getPosY() - element.getPosY() == 0 && element.getTransitionY() > -6){ //same y
            return checkCollisionOnX(element);
        }
        if(this.getPosY() - element.getPosY() == 1 && element.getTransitionY() > 2){ //this below
            if(element.getTransitionY() > 0){ //element transitions into this y
                return checkCollisionOnX(element);
            }
        }
        if(this.getPosY() - element.getPosY() == -1){ //this above
            if(element.getTransitionY() < 0){ //element transitions into this y
                return checkCollisionOnX(element);
            }
        }
        return false;
    }
}
