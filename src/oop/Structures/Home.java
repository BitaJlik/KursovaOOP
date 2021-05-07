package oop.Structures;

import oop.Entities.Entity;
import oop.Entities.Steve;
import oop.Items.Items;

import java.util.ArrayList;

public class Home {
    private final ArrayList<Entity> home;
    private double x;
    private double y;

    public Home(double x,double y){
        home = new ArrayList<>();
        this.x = x;
        this.y = y;
    }
//----------------------------
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    //----------------------------------------------
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
//----------------------------
    private void healing(Entity entity){
        double hp = 3;
        entity.setHp(entity.getHp() + hp); }
//-----------------------
    public void addToHome(Entity obj){ if(obj instanceof Steve) home.add(obj);else System.out.println("Це павук!"); }
    public void delToHome(int indx){ this.home.remove(indx); }
    public void delToHome(Entity indx){ this.home.remove(indx); }
    public ArrayList getHome(){ return this.home; }
    public void clearHome(){this.home.clear();}
//-----------------------------
    public void sell(Entity obj, int choose) {
        int check = 0;
        for (Entity entity : home) {
            if (entity.getId() == obj.getId()) {
                check = 1;
                break;
            }
        }
        if (check == 1) {
            ArrayList<Items> temp = new ArrayList(((Steve) obj).getInv());
            if (temp.get(choose).getAmount() > 0) {
                obj.delInv(choose);
                obj.setMoney(obj.getMoney() + temp.get(choose).getPrice());
                System.out.println("Продано " + temp.get(choose).getName());
            } else System.out.println("Недостаня кількість");
        }else System.out.println("\u001B[31mПотрібно бути дома!\u001B[0m");
    }
    public void sellAll(Entity obj){
        int check = 0;
        for (Entity entity : home) {
            if (entity.getId() == obj.getId()) {
                check = 1;
                break;
            }
        }
        if(check == 1){
            if (!(obj.getInv().isEmpty())) {
                int sum = 0;
                for (int i = 0; i < obj.getInv().size(); i++) {
                    sum += (int) (((Items) obj.getInv().get(i)).getAmount() * ((Items) obj.getInv().get(i)).getPrice());
                }
                obj.clearInv();
                obj.setMoney(obj.getMoney() + sum);
                System.out.println("Продано все на суму " + sum + "$");
            }
        }else System.out.println("\u001B[31mПотрібно бути дома!\u001B[0m");
    }
}
