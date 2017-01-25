package screepz;

import org.newdawn.slick.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ScreepZ extends BasicGame{
    
    private final int wTiles = 12;
    private final int hTiles = 12;
    private final int tileSize = 50;
    private final int xOffset = 50;
    private final int yOffset = 50;
    
    private int maxScreeps = 100;
    private int maxTowers = 15;
    private int startX, startY;
    
    private Tile[][] tiles = new Tile[hTiles][wTiles];
    private int[][] tileGrid = new int[hTiles][wTiles];
    private int[][] builGrid = new int[hTiles][wTiles];
    
    private ImageHandler imgHandler;
    private InputHandler inHandler;
    
    private ArrayList<CoreScreep> activeScreeps = new ArrayList<>(maxScreeps);
    private ArrayList<CoreTower> activeTowers = new ArrayList<>(maxTowers);
    private ArrayList<Integer> mapActivity;
    
    private TowerType selectedTower = TowerType.GUN;
    
    public static void main(String[] args){
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new ScreepZ("Test"), 1280, 720, false);
            appgc.setShowFPS(false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            System.err.println(ex.toString());
        }
    }
    
    public ScreepZ(String title) throws SlickException {
        super(title);
    }
    
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        initTiles();
        imgHandler = new ImageHandler();
        inHandler = new InputHandler();
        loadLevel(1);
    }
    
    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        if(mapActivity != null){
            if(!mapActivity.isEmpty()){
                int x = mapActivity.get(0);
                switch(x){
                    case 0: break;
                    case 1: activeScreeps.add(new CommonScreep(startX, startY)); break;
                    case 2: activeScreeps.add(new DankScreep(startX, startY)); break;
                    default: break;
                }
                mapActivity.remove(0);
            }
        }
        
        if(!activeTowers.isEmpty()){
            for(CoreTower tower: activeTowers){
                tower.reduceCooldown(1);
                tower.reload();
                tower.doAction(activeScreeps,activeTowers);
            }
        }
        
        while(!removeScreeps());
        
        if(!activeScreeps.isEmpty()){
            for(CoreScreep screep: activeScreeps){
                screep.move(tileGrid,hTiles,wTiles);
            }
        }
        
        while(!removeScreeps());
        
    }
    
    private boolean removeScreeps(){
        if(!activeScreeps.isEmpty()){
            for(CoreScreep screep: activeScreeps){
                if(!screep.getAlive()){
                    activeScreeps.remove(screep);
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        
        // DRAW TILEMAP //
        for(int i=0; i<hTiles; i++){
            for (int j=0; j<wTiles; j++){
                GroundType type;
                
                if(tileGrid[i][j] == 1){
                    type = GroundType.ROAD;
                }
                else if(tileGrid[i][j] == 9){
                    type = GroundType.SPAWN;
                }
                else if(tileGrid[i][j] == 3){
                    tiles[i][j].drawTile(graphics, imgHandler.getGroundImg(GroundType.GROUND),
                            (j*tileSize)+xOffset, (i*tileSize)+yOffset);
                    type = GroundType.TREE;
                }
                else{
                    type = GroundType.GROUND;
                }
                tiles[i][j].drawTile(graphics, imgHandler.getGroundImg(type),
                        (j*tileSize)+xOffset, (i*tileSize)+yOffset);
            }
        }
        // ************ //
        
        // TOWER BUTTON TEST //
        graphics.setColor(Color.gray);
        graphics.fillRect(2*xOffset+(wTiles*tileSize),
                yOffset, 100, 100);
        graphics.drawImage(imgHandler.getTowerImg(selectedTower), 2*xOffset+(wTiles*tileSize)+25,yOffset+25);
        // ***************** //
        
        
        // DRAW Tower-count //
        graphics.drawString("Tower left: " + (maxTowers - activeTowers.size()), 900,200);
        
        /*
        if(activeTowers.size() == 1){
            for(CoreScreep screep: activeScreeps){
                screep.alive = false;
            }
            
            mapActivity.clear();
        }
        */
        
        // DRAW Screepz //
        if(!activeScreeps.isEmpty()){
            for(CoreScreep screep: activeScreeps){
                screep.drawScreep(graphics, imgHandler.getScreepImg(screep.type),
                        (screep.posX*tileSize)+xOffset, (screep.posY*tileSize)+yOffset);
            }
        }
        // ************ //
        
        if(!activeTowers.isEmpty()){
            for(CoreTower tower: activeTowers){
                tower.drawTower(graphics, imgHandler.getTowerImg(tower.type),
                        (tower.posX*tileSize)+xOffset, (tower.posY*tileSize)+yOffset);
            }
        }
        
    }
    
    private void loadLevelDynamic(String values){
        
        if(values != "null"){
            mapActivity = new ArrayList<>(values.length());
    
            for(int i=0; i<values.length(); i++){
                mapActivity.add(i,Character.getNumericValue(values.charAt(i)));
            }
        }
        else{
            mapActivity = null;
        }
        
    }
    
    private void loadLevel(int idx){
        String levelPath = "./res/levels/level" + idx + ".lev";
        int height = 0;
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(levelPath));
            br.readLine();
            for(String line; height<hTiles;){
                line = br.readLine();
                for(int i=0; i<wTiles; i++){
                    if(line.charAt(i) == '9'){
                        startX = i;
                        startY = height;
                    }
                    tileGrid[height][i] = Character.getNumericValue(line.charAt(i));
                    builGrid[height][i] = Character.getNumericValue(line.charAt(i));
                }
                height++;
            }
            
            br.readLine();
            loadLevelDynamic(br.readLine());
            br.close();
            
        }catch(Exception ex){
            System.err.println("Error while reading the level-file!");
        }
    }
    
    private void initTiles(){
        for(int i=0; i<hTiles; i++){
            for(int j=0; j<wTiles; j++){
                tiles[i][j] = new Tile(tileSize);
            }
        }
    }
    
    public void mouseClicked(int i, int i1, int i2, int i3) {
        /* i = Which side of the mouse is clicked:
        1 -> RightClick
        0 -> LeftClick
        i1 = x-Pos
        i2 = y-Pos
        i3 = FUCK i3
        */
        
        // Handle click inside the grid
        if(inHandler.clickInGrid(i1,i2,wTiles,hTiles,tileSize,xOffset,yOffset)){
            int[] x = inHandler.getTile(i1,i2,wTiles,hTiles,tileSize,xOffset,yOffset);
            addTower(selectedTower,x[0],x[1]);
        }
        
        int x = 2*xOffset+(wTiles*tileSize);
        if(i1 >= x && i1 <= x+100 && i2 >= yOffset && i2 <= yOffset+100){
            switch(selectedTower){
                case GUN: selectedTower = TowerType.SPIKE; break;
                case SPIKE: selectedTower = TowerType.GUN; break;
            }
        }
        
    }
    
    private boolean addTower(TowerType type, int posX, int posY){
        
        if(posX != 99 && tileGrid[posY][posX] != 1 && tileGrid[posY][posX] != 3 && activeTowers.size() < maxTowers
                && builGrid[posY][posX] != 10){
            
            switch(type){
                case SPIKE: activeTowers.add(new SpikeTower(posX, posY)); break;
                case GUN: activeTowers.add(new GunTower(posX, posY)); break;
            }
            
            builGrid[posY][posX] = 10;
        }
        
        return false;
    }
    
    public enum ScreepType{
        COMMON, DANK
    }
    
    public enum TowerType{
        GUN, SPIKE
    }
    
    public enum GroundType{
        ROAD, GROUND, TREE, SPAWN
    }
    
}