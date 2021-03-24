package oop;
import

import static oop.Main.entity;

public class Home {
    private double x;
    private double y;
    private double hp
//----------------------------
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    //----------------------------------------------
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
//----------------------------
    public void Start(Entity entity) {
        if (entity.getClass() == Steve.class) {
            Healing(entity);
        }
        else{
            entity.setHp(entity.getHp() - hp);
        }
    }
//-----------------------------
    private void Healing(Entity entity){ entity.setHp(entity.getHp() + hp); }
    //-----------------------
    private void Sell(Items item,Steve steve){
        switch (item.getId()){
            case 1: steve.setmoney(item.getPrice());
        }
    }

}
