package oop.Structures;

import oop.Entities.Entity;
import oop.Entities.Spider;
import oop.Entities.Steve;
import oop.MainMenu;

import java.util.ArrayList;

public class Cave {
    private final ArrayList<Entity> cave = new ArrayList<>();
    private double x;
    private double y;
    public Cave(double x,double y){
        this.x = x;
        this.y = y; }
    //----------------------------------------
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    //-----------------------------------------
    public void addToCave(Entity obj){ this.cave.add(obj); }
    public void delToCave(int indx){ this.cave.remove(indx); }
    public ArrayList getCave(){ return this.cave; }
    public void clearCave(){this.cave.clear();}
    // ------------------------------------------ ONLY STEVE-------\\
    public static void Fight(Entity obj){
        if(obj instanceof Steve){
            double shance = 1 + Math.random()*100;
            if(shance > 70){
                MainMenu.deathBatlle(obj,new Spider());
            }
        }
    }
//    public void Dig(Entity obj){
//        if(obj instanceof Steve){
//            double id = Math.round(Math.random() * Ores.ores.size());
//            for ( oop.Items.Items item : Ores.ores) {
//                if ( item.getId() == id ) {
//                    Fight(obj);
//                    Progress.func(item.getDigseconds());
//                    System.out.println("Добули " + item.getName());
//                    ((Steve) obj).addInv(Ores.ores.get((int) id));
//                }
//            }
//        }
//    }
}
