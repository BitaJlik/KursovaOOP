package oop;

import oop.Actions.Defalut;

public abstract class Entity implements Cloneable,Defalut,Comparable<Entity>{
    private String name;
    private int id ;
    private double hp;
    private double damage;
    private double x;
    private double y;
    private double speed;
    private double money;

    //------------------------------ Getters & Setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    //
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    //------------------
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    //------------------
    public String getName() { return name; }
    public void setName(String name){this.name = name;}
    //-----------------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    //-----------------
    public double getHp() { return hp; }
    public void setHp(double hp) { this.hp = hp; }
    //------------------
    public double getDamage() { return damage; }
    public void setDamage(double damage) { this.damage = damage; }
    //---------------------(ONLY STEVE)-------------------------------\\
    public double getMoney() { return money; }
    public void setMoney(double money) { this.money = money; }
    //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return hp == entity.hp &&
                name.equals(entity.name) &&
                id == entity.id &&
                damage == entity.damage;
    }
    public int compareTo(Entity entity){
        if (this.hp == entity.hp)
            return 0;
        else if(this.hp < entity.hp)
            return -1;
        else return 1;
    }

}