package oop.Structures;

import oop.Entities.Entity;
import oop.Entities.Steve;
import oop.Items.Food;
import oop.Items.Items;
import oop.Items.Ores;
import oop.MainMenu;
import oop.Progress;

import java.util.ArrayList;

public class World {
    private final ArrayList<Entity> world = new ArrayList<>();
    public Home home = new Home(25,25);
    public Cave cave = new Cave(475,475);
    private boolean isDay = true;
    public void Day() { this.isDay = true; }
    public void Night() { this.isDay = false;}
    public boolean isDay() { return isDay; }
//----------------------------
    public void addToWorld(Entity obj){ this.world.add(obj); }
    public void delToWorld(int indx){ this.world.remove(indx); }
    public ArrayList getWorld(){ return this.world; }
    public void clearWorld(){ this.world.clear(); }
//----------------------------
    public static void registryItems(){
        Items.items.addAll(Ores.ores);
        Items.items.addAll(Food.foods);
    }
    public boolean isAllWorldEmpty(){
        return world.size() == 0 && home.getHome().size() == 0 && cave.getCave().size() == 0;
    }
    public void SeekFood(Entity obj){
        if(obj instanceof Steve ) {
            double id = Math.round( Math.random() * Food.foods.size() );
            for ( oop.Items.Items item : Food.foods) {
                if ( item.getId() == id ) {
                    if(!isDay)Cave.Fight(obj);
                    Progress.func(item.getDigseconds());
                    System.out.println("Добули " + item.getName());
                    obj.addInv(item);
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
