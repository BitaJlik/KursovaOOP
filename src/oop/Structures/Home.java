package oop.Structures;

import oop.Entities.Entity;
import oop.Entities.Steve;

import java.util.ArrayList;

public class Home {
    private final ArrayList<Entity> homeList = new ArrayList<>();
    private double x;
    private double y;

    public Home(double x,double y){
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
    public void addToHome(Entity obj){ if(obj instanceof Steve) homeList.add(obj);else System.out.println("Це павук!"); }
    public void delToHome(int indx){ this.homeList.remove(indx); }
    public ArrayList getHome(){ return this.homeList; }
    public void clearHome(){this.homeList.clear();}
//-----------------------------
//    public  void Sell(int choose, Entity obj) throws IOException, CloneNotSupportedException {
//        ArrayList<Items> temp = new ArrayList(((Steve) obj).getInv());
//        if(temp.get(choose).getAmount() > 0){
//            ((Steve) obj).delInv(temp.get(choose));
//            obj.setMoney(obj.getMoney() + temp.get(choose).getPrice());
//            System.out.println("Продано!");
//        }
//        else System.out.println("Недостатня кількість!");
//    }
}
