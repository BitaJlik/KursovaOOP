package Item;

import java.util.ArrayList;

public class Ores extends Items{
    public static ArrayList<Items> ores = new ArrayList<>();

    public Ores(String name,  double price,int digSeconds) {
      super(name,price,digSeconds,"Ore");
    }
    public Ores(){ this("Неизвестно",0,-1); }// If object is not correct
   @Override
    public String toString(){
        return "Руда  "+ super.getName() + " | " +"\u001B[32m"+ super.getPrice() +"\u001B[0m" +"$ ("+ super.getAmount() + ")шт.";
    }
    public static void oresinit(){
        ores.add( new Ores("Алмаз",10,5));
        ores.add( new Ores("Золото",8,5));
        ores.add( new Ores("Залізо",4,3));
        ores.add( new Ores("Свинець",4,3));
        ores.add( new Ores("Мідь",3,3));
    }
    public String getKlas(){
        return "Ore";
    }
    public Items clone(){
        return new Ores(super.getName(),super.getPrice(),super.getDigseconds());
    }
}
