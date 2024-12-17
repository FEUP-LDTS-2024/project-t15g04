
package AlienWalk.Viewer;

import AlienWalk.Model.OverMenu;
import AlienWalk.Model.Elements.Position;  // <-- Add this import
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

    public class OverMenuViewer extends Viewer<OverMenu> {

        private ElementViewer menuViewer;

        public OverMenuViewer(TerminalScreen screen) throws IOException {
            super(screen);
            // Initialize ElementViewer with the image you want to use
            menuViewer = new ElementViewer("MainMenuImage.png");
        }

        @Override
        public int read() {
            if (quit) return 0;   // Quit
            if (up) return 1;     // Up
            if (down) return 2;   // Down
            if (enter) return 3;  // Enter
            return -1;            // No input
        }

        @Override
        public void draw(OverMenu model) {
            screen.clear();  // Clear the terminal screen before drawing
            TextGraphics textGraphics = screen.newTextGraphics();
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#1bc70f")); // Set background color

            // Draw the background or image
            menuViewer.draw(new Position(0, 0), 0, 0, textGraphics);  // Drawing the background image at position (0,0)

            // Highlight the currently selected option
            switch (model.getCurrent()) {
                case Restart:
                    textGraphics.setForegroundColor(TextColor.Factory.fromString("#ff0000")); // Red for Restart
                    textGraphics.drawLine(115, 75, 180, 75, ' ');
                    break;
                case Quit:
                    textGraphics.setForegroundColor(TextColor.Factory.fromString("#00ff00")); // Green for Quit
                    textGraphics.drawLine(115, 94, 190, 94, ' ');
                    break;
            }

            // Refresh the screen to apply changes
            try {
                screen.refresh();
            } catch (IOException ignored) {
            }
        }
    }
