package screepz;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageHandler{
    
    private Image[] images = new Image[10];
    private String[] imgPath = new String[7];
    
    public ImageHandler() throws SlickException{
        initPaths();
        initImages();
    }
    
    private void initImages() throws SlickException{
        
        for(int i=0; i<imgPath.length; i++){
            images[i] = new Image(imgPath[i]);
        }
        
    }
    
    // PATH TO THE IMAGE RESOURCES
    private void initPaths(){
        String s = "./res/images/";
        imgPath[0] = s + "road.png";
        imgPath[1] = s + "tree.png";
        imgPath[2] = s + "common.png";
        imgPath[3] = s + "memer.png";
        imgPath[4] = s + "gunTower.png";
        imgPath[5] = s + "ground.png";
        imgPath[6] = s + "test.png";
    }
    
    public Image getImage(int idx){
        if(idx >= 0 && idx < imgPath.length)
            return images[idx];
        return null;
    }
    
    public Image getScreepImg(ScreepZ.ScreepType type){
        switch(type){
            case COMMON: return images[2];
            case DANK: return images[3];
            default: return null;
        }
    }
    
    public Image getTowerImg(ScreepZ.TowerType type){
        switch(type){
            case GUN: return images[4];
            default: return null;
        }
    }
    
    public Image getGroundImg(ScreepZ.GroundType type){
        switch(type){
            case GROUND: return images[5];
            case ROAD: return images[0];
            case TREE: return images[1];
            case SPAWN: return images[6];
            default: return null;
        }
    }
}
