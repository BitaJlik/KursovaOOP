package sample;

import Entities.Entity;

import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.Arrays;

import static sample.Main.world;

public class Menu {
    public static String seeWorld(ArrayList<Entity> arrayList){
        StringBuilder information = new StringBuilder();
        if(arrayList.size() != 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).getId() != 0) {
                    information.append("\n[").append(i).append("] ").append(arrayList.get(i));
                }
            }
        }
        return information.toString();
    }
    public static void Batlle(Entity FrstEntity,Entity ScndEntity) {
        if(world.passive) return;
        if(world.hbg.getBoundsInParent().intersects(FrstEntity.getImg().getBoundsInParent()) ||
                world.hbg.getBoundsInParent().intersects(ScndEntity.getImg().getBoundsInParent())) return;
        if(world.cbg.getBoundsInParent().intersects(FrstEntity.getImg().getBoundsInParent()) ||
                world.cbg.getBoundsInParent().intersects(ScndEntity.getImg().getBoundsInParent())) return;
        if(FrstEntity.getId() == -1){ Main.entities.getChildren().remove(FrstEntity.getGroup()); return;}
        if(ScndEntity.getId() == -1){ Main.entities.getChildren().remove(ScndEntity.getGroup()); return;}
         FrstEntity.Attack(ScndEntity);
        ScndEntity.Attack(FrstEntity);
        if(FrstEntity.getHp() <= 0){
            ScndEntity.Healing(2);
        }
        if(ScndEntity.getHp() <= 0){
            FrstEntity.Healing(2);
        }
    }
    public static void deathBatlle(Entity FrstEntity,Entity ScndEntity) {
        while(FrstEntity.getId() != -1 || ScndEntity.getId() != -1){
            if(FrstEntity.getId() == -1){ break; }
            else if(ScndEntity.getId() == -1){ break; }
            else {
                System.out.println(FrstEntity.getName() + " " + ScndEntity.getName());
                FrstEntity.Attack(ScndEntity);
                ScndEntity.Attack(FrstEntity);
            }
        }
    }
    public static void update(){
    }
    public static void ArraysExampleAndComparable(){
        int[] hps = new int[world.getWorld().size()];
        for(int i =0;i < world.getWorld().size();i++){
            hps[i] = (int) world.getWorld().get(i).getHp();
        }
        System.out.println(Arrays.toString(hps));
        Arrays.sort(hps);
        System.out.println("Comparable");
        Entity[] entities = new Entity[world.getWorld().size()];
        world.getWorld().toArray(entities);
        System.out.println(Arrays.toString(entities));
        Arrays.sort(entities,Entity::compareTo);
        System.out.println(Arrays.toString(entities));
    }
}
