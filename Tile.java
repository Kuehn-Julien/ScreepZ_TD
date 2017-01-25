package screepz;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile{
    
    private int tileSize;
    private int posX, posY;
    
    public Tile(int tileSize){
        this.tileSize = tileSize;
    }
    
    public void drawTile(Graphics g, Image img, int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        g.drawImage(img, posX, posY);
    }
    
}
