package oop.Entities;

import oop.Actions.Defalut;

public abstract class Entity implements Cloneable,Defalut,Comparable<Entity>{
    private String name;
    private int id ;
    private double hp;
    private double damage;
    private double x;
    private double y;
    private double speed;
    public Entity(String _name,double _hp,double _damage){
        this.name = _name;
        this.hp = _hp;
        this.damage = _damage;
    }

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
    public double getMoney(){return 1;}
    public void setMoney(double money){}
    //--------------------------------------------------------
    public int compareTo(Entity entity) {
        return Integer.compare(id, entity.id);
    }
    @Override
    public Entity clone() throws CloneNotSupportedException{
        return (Entity) super.clone();
    }
}