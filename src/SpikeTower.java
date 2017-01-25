package screepz;

import java.util.ArrayList;

public class SpikeTower extends CoreTower{
    
    public SpikeTower(int posX, int posY) {
        super(posX, posY);
        this.type = ScreepZ.TowerType.SPIKE;
        
        this.damage = 20;
        this.range = 1;
        this.maxCooldown = 1;
        this.areaOfEffect = true;
    }
    
    @Override
    public void doAction(ArrayList<CoreScreep> screeps, ArrayList<CoreTower> towers) {
    
        if(checkCooldown()){
            damageScreepsHorizontal(screeps);
            damageScreepsVertical(screeps);
            increaseCooldown();
        }
        
    }
    
    @Override
    public void useAbility() {
        
    }
}
