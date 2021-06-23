package Structures;

import Entities.Entity;
import Entities.Spider;
import Entities.Steve;
import Item.Ores;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Menu;
import java.util.ArrayList;

public class Cave {
    private final ArrayList<Entity> cave;
    private double x;
    private double y;
    private final ImageView cbg;
    public Cave(double x,double y){
        cave = new ArrayList<>();
        this.x = x;
        this.y = y;
        cbg = new ImageView(new Image("images/cavetopbg.png"));
    }
    //----------------------------------------
    public ImageView getImg(){return cbg;}
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    //-----------------------------------------
    public ArrayList<Entity> getCave(){return cave;}

    public void dig(Entity obj){
        if(obj.getId() == -1) return;
        if(obj.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())){
            if (obj instanceof Steve) {
                if(obj.getIdAim() == -1){
                    obj.setIdAim((int) (Math.random() * Ores.ores.size()));
                }
                if(obj.getProcess() >= Ores.ores.get(obj.getIdAim()).getDigseconds()*200) {
                    System.out.println("Добули " + Ores.ores.get(obj.getIdAim()).getName());
                    obj.addInv(Ores.ores.get(obj.getIdAim()));
                    int chance = (int) (Math.random() * 100);
                    if (chance > 80) {
                        Menu.deathBatlle(obj, new Spider());
                    }
                    obj.clearProcess();
                    obj.setIdAim(-1);
                }
                else obj.setProcess();
                obj.refresh();
            }
        }else System.out.println("\u001B[31mПотрібно піти в шахту!\u001B[0m");
    }
}
