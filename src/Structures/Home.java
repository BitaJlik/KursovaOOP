package Structures;

import Entities.Entity;
import Entities.Steve;
import Item.Items;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Home {
    private final ArrayList<Entity> home;
    private double x;
    private double y;
    private final ImageView hbg;
    private int hp;
    public static int staticsum = 0;

    public Home(double x,double y){
        home = new ArrayList<>();
        this.x = x;
        this.y = y;
        hbg = new ImageView(new Image("images/homebg.png"));
        hp = 100;
    }
    public ImageView getImg(){return hbg;}
//----------------------------
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    //----------------------------------------------
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }
//----------------------------
    public void healing(Entity obj){
        boolean inHome = false;
        for(Entity entity : getHome()){
            if (entity.getId() == obj.getId()) {
                inHome = true;
                break;
            }
        }
        if(obj.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())){
            if (obj instanceof Steve) {
                if(obj.getProcess() >= 300) {
                    if(inHome){
                        obj.heal(obj.getHp() + 3);

                    }
                    else obj.heal(obj.getHp() + 1);
                    obj.clearProcess();
                }
                else obj.setProcess();
            }
            else {
                if(obj.getProcess() >= 300) {
                    obj.setHp(obj.getHp() - 2);
                    obj.clearProcess();
                }
                else obj.setProcess();
            }
            obj.refresh();
        }
    }
//-----------------------
    public void addToHome(Entity obj){
        if(obj instanceof Steve) { home.add(obj); }
        else System.out.println("Це павук!"); }
   public ArrayList<Entity> getHome(){return home;}
//-----------------------------
    public void sell(Entity obj, Items items) {
        if (obj.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) {
            ArrayList<Items> temp = new ArrayList<>(obj.getInv());
            int choose = temp.lastIndexOf(items);
            if (!(temp.lastIndexOf(items) == -1)) {
                if (temp.get(choose).getAmount() > 0) {
                    obj.delInv(choose);
                    obj.setMoney(obj.getMoney() + temp.get(choose).getPrice());
                    System.out.println("Продано " + temp.get(choose).getName());
                } else System.out.println("Недостаня кількість");
            }
            else System.out.println("We dont have item");
        }else System.out.println("\u001B[31mПотрібно бути дома!\u001B[0m");
    }
    public void sellAll(Entity obj){
        if (obj.getImg().getBoundsInParent().intersects(getImg().getBoundsInParent())) {
            if (!(obj.getInv().isEmpty())) {
                int sum = 0;
                for (int i = 0; i < obj.getInv().size(); i++) {
                    sum += (int) (obj.getInv().get(i).getAmount() * obj.getInv().get(i).getPrice());
                }
                staticsum = sum;
                obj.getInv().clear();
                obj.setMoney(obj.getMoney() + sum);

                System.out.println("Продано все на суму " + sum + "$");
            }
        }else System.out.println("\u001B[31mПотрібно бути дома!\u001B[0m");
    }
}
