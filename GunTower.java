package screepz;

import java.util.ArrayList;

public class GunTower extends CoreTower {

    public GunTower(int posX, int posY){
        super(posX, posY);
        this.type = ScreepZ.TowerType.GUN;
        this.damage = 25;
        this.range = 1;
    }
    
    public void doAction(ArrayList<CoreScreep> screeps, ArrayList<CoreTower> towers) {
        
        for(int i=0; i<range; i++){
            for(int j=0; j<range; j++){
                damageScreepsHorizontal(screeps, j+1);
            }
            damageScreepsVertical(screeps, i+1);
        }
        
    }
    
    public void useAbility(){
        
    }
    
    private void damageScreepsVertical(ArrayList<CoreScreep> screeps, int aY) {
        if (!screeps.isEmpty()) {
            for(CoreScreep screep : screeps) {
                if(screep.posX == posX && screep.posY == posY+aY){
                    screep.takeDamage(damage);
                }
                else if(screep.posX == posX && screep.posY == posY-aY){
                    screep.takeDamage(damage);
                }
            }
        }
    }
    
    private void damageScreepsHorizontal(ArrayList<CoreScreep> screeps, int aX) {
        if (!screeps.isEmpty()) {
            for(CoreScreep screep : screeps) {
                if (screep.posX == posX + aX && screep.posY == posY) {
                    screep.takeDamage(damage);
                } else if (screep.posX == posX - aX && screep.posY == posY) {
                    screep.takeDamage(damage);
                }
            }
        }
    }
    
}
