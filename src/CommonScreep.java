package screepz;

public class CommonScreep extends CoreScreep{
    
    public CommonScreep(int posX, int posY){
        initPos(posX, posY);
        this.type = ScreepZ.ScreepType.COMMON;
        this.health = 50;
    }
    
}
