package oop.Structures;

import oop.Entities.Entity;
import oop.Entities.Spider;
import oop.Entities.Steve;
import oop.Items.Ores;
import oop.MainMenu;
import oop.Progress;

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
    public void delToCave(Entity indx){ this.cave.remove(indx); }
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
    public void dig(Entity obj){
        int check = 0;
        for (Entity entity : cave) {
            if (entity.equals(obj)) {
                check = 1;
                break;
            }
        }
        if(check == 1){
            if (obj instanceof Steve) {
                int id = (int) (Math.random() * Ores.ores.size());
                int chance = (int) (Math.random() * 100);
                if (chance > 60) {
                    MainMenu.deathBatlle(obj, new Spider());
                }
                Progress.func(Ores.ores.get(id).getDigseconds());
                System.out.println("Добули " + Ores.ores.get(id).getName());
                obj.addInv(Ores.ores.get(id));
            }
        }else System.out.println("\u001B[31mПотрібно піти в шахту!\u001B[0m");
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
