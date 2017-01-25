package screepz;

import java.util.ArrayList;

public interface TowerAction {

    void doAction(ArrayList<CoreScreep> screeps, ArrayList<CoreTower> towers);
    void useAbility();
    
}
