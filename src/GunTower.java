package screepz;

import java.util.ArrayList;

public class GunTower extends CoreTower {

    public GunTower(int posX, int posY){
        super(posX, posY);
        this.type = ScreepZ.TowerType.GUN;
        
        this.damage = 50;
        this.range = 2;
        this.maxCooldown = 2;
    }
    
    public void doAction(ArrayList<CoreScreep> screeps, ArrayList<CoreTower> towers) {
        
        if(checkCooldown()){
            damageScreepsHorizontal(screeps);
            damageScreepsVertical(screeps);
            increaseCooldown();
        }
        
    }
    
    public void useAbility(){
        
    }
}
