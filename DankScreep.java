package screepz;

public class DankScreep extends CoreScreep {
    
    public DankScreep(int posX, int posY){
        initPos(posX, posY);
        this.type = ScreepZ.ScreepType.DANK;
        this.health = 100;
    }
    
}
