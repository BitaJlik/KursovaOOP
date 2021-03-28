package oop;

import oop.Items.Food;
import oop.Items.Items;
import oop.Items.Ores;

public class World {
    private final int sizeWorld;
    private boolean isDay = true;

    public World( int sizeWorld){
        this.sizeWorld = sizeWorld;
        }
    public int getSizeWorld(){return this.sizeWorld;}
    public void Day() { this.isDay = true; }
    public void Night() { this.isDay = false;}
    public boolean isDay() { return isDay; }

    public static void registryItems(){
        Items.items.addAll(Ores.ores);
        Items.items.addAll(Food.foods);
    }
    public void SeekFood(Entity obj){
        if(obj.getClass() == Steve.class ) {
            double id = Math.round( Math.random() * Food.foods.size() );
            for ( oop.Items.Items item : Food.foods) {
                if ( item.getId() == id ) {
                    if(!isDay)MineCave.Fight(obj);
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
