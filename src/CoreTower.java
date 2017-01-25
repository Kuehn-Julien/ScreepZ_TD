package screepz;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public abstract class CoreTower implements TowerAction {
    
    protected int maxCooldown = 1;
    protected int damage = 10;
    protected int range = 1;
    protected boolean areaOfEffect = false;
    // ************************ //
    
    // DONT CHANGE //
    protected int posX, posY;
    protected ScreepZ.TowerType type;
    protected boolean reload = false;
    protected int cooldown = 0;
    // *********** //
    
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
    
    protected void damageScreepsVertical(ArrayList<CoreScreep> screeps) {
        
        if (!screeps.isEmpty()) {
            for(CoreScreep screep : screeps) {
                for(int i=0; i<range; i++){
                    if(screep.posX == posX){
                        if(screep.posY == posY-(i+1) && checkCooldown() &&(!reload || areaOfEffect)){
                            screep.takeDamage(damage);
                            reload = true;
                        }
                        else if(screep.posY == posY+(i+1) && checkCooldown() &&(!reload || areaOfEffect)){
                            screep.takeDamage(damage);
                            reload = true;
                        }
                    }
                }
            }
        }
    }
    
    protected void damageScreepsHorizontal(ArrayList<CoreScreep> screeps) {
        
        if (!screeps.isEmpty()) {
            for(CoreScreep screep : screeps) {
                for(int i=0; i<range; i++){
                    if(screep.posY == posY){
                        if(screep.posX == posX-(i+1) && checkCooldown() &&(!reload || areaOfEffect)){
                            screep.takeDamage(damage);
                            reload = true;
                        }
                        else if(screep.posX == posX+(i+1) && checkCooldown() &&(!reload || areaOfEffect)){
                            screep.takeDamage(damage);
                            reload = true;
                        }
                    }
                }
            }
        }
    }
    
    protected boolean checkCooldown(){
        return cooldown == 0;
    }
    
    protected void reduceCooldown(int x){
        
        if(cooldown - x >= 0){
            cooldown -= x;
        }
        else{
            cooldown = 0;
        }
    }
    
    protected void increaseCooldown(){
        cooldown = maxCooldown;
    }
    
    protected void reload(){
        reload = false;
    }
}
