package oop.Structures;

import oop.Entities.Entity;
import oop.Entities.Spider;
import oop.Entities.Steve;
import oop.Items.Ores;
import oop.MainMenu;
import oop.Progress;

public class MineCave {
    private double x;
    private double y;
    public MineCave(double x,double y){
        this.x = x;
        this.y = y;
    }
    //-------------------------------
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    //-----------------------------------------
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    // ------------------------------------------ ONLY STEVE-------\\
    public static void Fight(Entity obj){
        if(obj instanceof Steve){
            double shance = 1 + Math.random()*100;
            if(shance > 70){
                MainMenu.Deathbatlle(obj,new Spider());
            }
        }
    }
    //---------------------------------
    public void Dig(Entity obj){
        if(obj instanceof Steve ) {
            double id = Math.round( Math.random() * Ores.ores.size() );
            for ( oop.Items.Items item : Ores.ores) {
                if ( item.getId() == id ) {
                    Fight(obj);
                    Progress.func(item.getDigseconds());
                    System.out.println("Добули " + item.getName());
                    item.setAmount(1);
                }
            }
        }
        else{
            System.out.println("\u001B[31m" + "Це не Стів!!!" + "\u001B[31m");
            Progress.Waiting(1000);
            MainMenu.cls();
        }
    }
}
