package screepz;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class CoreTower implements TowerAction {
    
    protected int posX, posY;
    protected ScreepZ.TowerType type;
    protected int damage, range;
    
    public CoreTower(int posX, int posY){
        initPos(posX, posY);
    }
    
    protected void initPos(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public ScreepZ.TowerType getType(){
        return type;
    }
    
    public void drawTower(Graphics g, Image img, int posX, int posY){
        g.drawImage(img, posX, posY);
    }

}
