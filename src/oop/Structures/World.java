package oop.Structures;

import oop.Entities.Entity;
import oop.Items.Food;
import oop.Items.Items;
import oop.Items.Ores;
import oop.MainMenu;

import java.util.ArrayList;

public class World {
    private final ArrayList<Entity> world = new ArrayList<>();
    public Home home;
    public Cave cave;
    public World (int homeX,int homeY,int caveX,int caveY){
        home = new Home(25,25);
        cave = new Cave(475,475);
        Ores.oresinit();
        Food.foodsinit();
        registryItems();
    }
    private boolean isDay = true;
    public void Day() { this.isDay = true; }
    public void Night() { this.isDay = false;}
    public boolean isDay() { return isDay; }
//----------------------------
    public void addToWorld(Entity obj){ this.world.add(obj); }
    public void delToWorld(int indx){ this.world.remove(indx); }
    public void delToWorld(Entity indx){ this.world.remove(indx); }
    public ArrayList getWorld(){ return this.world; }
    public void clearWorld(){ this.world.clear(); }
//----------------------------

//----------------------------
    public static void registryItems(){
        Items.globalList.addAll(Ores.ores);
        Items.globalList.addAll(Food.foods);
    }
    public boolean isAllWorldEmpty(){
        MainMenu.update();
        return world.size() == 0 && home.getHome().size() == 0 && cave.getCave().size() == 0;
    }

}
