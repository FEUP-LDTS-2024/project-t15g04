package AlienWalk.Viewer;

import AlienWalk.Model.Elements.Position;
import AlienWalk.Model.Menu;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class MenuViewer extends Viewer<Menu> {
    private final ElementViewer menuViewer;

    public MenuViewer(TerminalScreen screen) throws IOException {
        super(screen);
        menuViewer = new ElementViewer("MainMenuImage.png");
    }

    @Override
    public int read(){
        if(quit) return 0;
        if(up) return 1;
        if(down) return 2;
        if(enter) return 3;
        return -1; //no input
    }

    @Override
    public void draw(Menu model) {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#1bc70f"));
        //textGraphics.fillRectangle(new TerminalPosition(0,0),new TerminalSize(40,20), ' ');

        menuViewer.draw(new Position(0,0),0,0,textGraphics);

        switch (model.getCurrent()){
            case Start:
                textGraphics.drawLine(115,75,180,75,' ');
                break;
            case Settings:
                textGraphics.drawLine(115,94,190,94,' ');
                break;
            case Quit:
                textGraphics.drawLine(115,114,160,114,' ');
                break;
        }
        try{
            screen.refresh();
        } catch (IOException ignored) {}
    }
}
