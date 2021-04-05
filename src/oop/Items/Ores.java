package oop.Items;

import java.util.ArrayList;

public class Ores extends Items{
    public static ArrayList<Items> ores = new ArrayList<>();

    public Ores(String name, int id, double price,int digSeconds,int amount) {
      super(name,id,price,amount,digSeconds);
    }
    public Ores(){ this("Неизвестно",-1,0,-1,1); }// If object is not correct
    @Override
    public String toString(){
        return "Руда  "+ super.getName() + " | " +"\u001B[32m"+ super.getPrice() +"\u001B[0m" +"$ ";
    }
    public static void oresinit(){
        ores.add( new Ores("Алмаз",0,10,5,0));
        ores.add( new Ores("Золото",1,8,5,0));
        ores.add( new Ores("Залізо",2,4,3,0));
        ores.add( new Ores("Свинець",3,4,3,0));
        ores.add( new Ores("Мідь",4,3,3,0));
    }
}
