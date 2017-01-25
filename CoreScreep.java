package screepz;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class CoreScreep {

    protected int posX, posY, lastMoveX, lastMoveY, health;
    protected ScreepZ.ScreepType type;
    protected int goldValue;
    protected boolean alive = true;
    
    protected void move(int[][] array, int hTiles, int wTiles){
        
        boolean moved = false;
        
        if(posX < wTiles-1 && !moved){
            if(array[posY][posX+1] == 1 && movePossible(posX+1,posY)){
                lastMoveX = posX;
                lastMoveY = posY;
                posX++;
                moved = true;
            }
        }
        
        if(posX > 0 && !moved){
            if(array[posY][posX-1] == 1 && movePossible(posX-1,posY)){
                lastMoveX = posX;
                lastMoveY = posY;
                posX--;
                moved = true;
            }
        }
        
        if(posY < hTiles-1 && !moved){
            if(array[posY+1][posX] == 1 && movePossible(posX,posY+1)){
                lastMoveX = posX;
                lastMoveY = posY;
                posY++;
                moved = true;
            }
        }
        
        if(posY > 0 && !moved){
            if(array[posY-1][posX] == 1 && movePossible(posX,posY-1)){
                lastMoveX = posX;
                lastMoveY = posY;
                posY--;
                moved = true;
            }
        }
        
        if(!moved){
           alive = false;
        }
        
    }
    
    protected void initPos(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        this.lastMoveX = posX;
        this.lastMoveY = posY;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public int getGoldValue(){
        return goldValue;
    }
    
    public boolean getAlive(){
        return alive;
    }
    
    public ScreepZ.ScreepType getType(){
        return type;
    }
    
    public void drawScreep(Graphics g, Image img, int posX, int posY){
        g.drawImage(img, posX, posY);
    }
    
    private boolean movePossible(int x, int y){
        if(x != lastMoveX || y != lastMoveY)
            return true;
        
        return false;
    }
    
    protected void takeDamage(int damage){
        
        health -= damage;
        
        if(health <= 0)
            alive = false;
    }
    
}
