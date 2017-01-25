package screepz;

public class InputHandler {
    
    public boolean clickInGrid(int posX, int posY, int wTiles, int hTiles, int tileSize, int xOffSet, int yOffset){
        
        // CHECK IF MOUSE IS INSIDE THE GAME-GRID
        if(posX > xOffSet && posX <= xOffSet+(wTiles*tileSize) &&
                posY > yOffset && posY <= yOffset+(hTiles*tileSize)){
            return true;
        }
        return false;
    }
    
    public int[] getTile(int posX, int posY, int wTiles, int hTiles, int tileSize, int xOffSet, int yOffset){
        
        int[] x = new int[]{0,0};
        
        for(int i=0; i<wTiles; i++){
            if(posX <= (tileSize * (i+1)) + xOffSet){
                x[0] = i;
                break;
            }
        }
        
        for(int i=0; i<hTiles; i++){
            if(posY <= (tileSize * (i+1)) + yOffset){
                x[1] = i;
                break;
            }
        }
        
        return x;
    }
    
}
