package AlienWalk.Viewer;

import AlienWalk.Model.Elements.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ElementViewer {
    private final BufferedImage image;

    public ElementViewer(String path) throws IOException {
        URL resource = getClass().getClassLoader().getResource(path);
        this.image = ImageIO.read(Objects.requireNonNull(resource));
    }

    public void draw(Position position,int transitionX, int transitionY, TextGraphics tg){
        for (int x = 0; x < 8; x++){ // image.getWidth()
            for (int y = 0; y < 8; y++){ // image.getHeight()
                int a = image.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;

                if (alpha != 0) {
                    TextCharacter c = new TextCharacter(' ', new TextColor.RGB(red, green, blue), new TextColor.RGB(red, green, blue));
                    tg.setCharacter(position.getX()*8 + transitionX + x, position.getY()*8 + transitionY + y, c);
                }
            }
        }
    }
}
