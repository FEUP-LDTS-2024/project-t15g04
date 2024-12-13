package AlienWalk.Viewer;

import AlienWalk.Model.Elements.Crystal;
import AlienWalk.Model.Elements.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class CrystalViewer {
    private final BufferedImage image;

    // Constructor loads the crystal image from the resources
    public CrystalViewer(String path) throws IOException {
        URL resource = getClass().getClassLoader().getResource(path);
        this.image = ImageIO.read(Objects.requireNonNull(resource));
    }

    // Draw method for the crystal
    public void draw(Position position, int transitionX, int transitionY, TextGraphics tg) {
        for (int x = 0; x < image.getWidth(); x++) { // Loop through the image width
            for (int y = 0; y < image.getHeight(); y++) { // Loop through the image height
                int a = image.getRGB(x, y);
                int alpha = (a >> 24) & 0xff;
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;

                if (alpha != 0) { // Only draw non-transparent pixels
                    TextCharacter c = new TextCharacter(' ', new TextColor.RGB(red, green, blue), new TextColor.RGB(red, green, blue));
                    tg.setCharacter(position.getX() * image.getWidth() + transitionX + x,
                            position.getY() * image.getHeight() + transitionY + y, c);
                }
            }
        }
    }
}
