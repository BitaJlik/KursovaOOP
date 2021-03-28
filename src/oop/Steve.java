package oop;

import java.text.DecimalFormat;

public class Steve extends Entity {
    private String name;
    private int id ;
    private double hp;
    private double damage;
    private double x;
    private double y;
    private double speed;
    private double money;
    //------------------------------- Constructors
    public Steve(String name,double hp, double damage) {
        this.name = name;
        this.id = 2;
        this.hp = hp;
        this.damage = damage;
        this.x = 5;
        this.y = 5;
    }
    public Steve(String name, double hp, double damage,double x,double y, double speed){
        this.name = name;
        this.id = 2;
        this.hp = hp;
        this.damage = damage;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
    public Steve(){ this("Стів",20,4,5,5,10); }
    //--------------------   Actions
    public void Attack() { System.out.println("\u001B[31m" + name +" атакує!" + "\u001B[0m"); }
    public void Healing(double heal){
        if(this.hp > 1){
        double hp = getHp();
        setHp(hp + heal);
        System.out.println("\u001B[31m" + name + " + "+ Math.round(heal) + " hp!" + "\u001B[0m");
        }
    }
    public void Death(){
        System.out.println(name + "\u001B[31m"  + " помирає" + "\u001B[0m");
        this.hp = 0;
        this.damage = 0;
        this.id = -1;
    }
    public void Sleeping() {
        if(this.hp > 1){
             System.out.println(name +"\u001B[32m" + " Спить" + "\u001B[0m");
             Progress.func(2);
             Healing(3);
        }
    }
    //----------------------------- Movements
    public  void moveUp(){ setY(getY() + speed); }
    public  void moveDown(){ setY(getY() + (-1 * speed)); }
    public  void moveLeft(){ setX(getX() + (-1 * speed)); }
    public  void moveRight(){ setX(getX() + speed); }
    //------------------------------ Getters & Setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    //------------------
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    //------------------
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    //------------------
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    //------------------
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    //------------------
    public double getHp() { return hp; }
    public void setHp(double hp) { this.hp = hp; }
    //------------------
    public double getDamage() { return damage; }
    public void setDamage(double damage) { this.damage = damage; }
    //------------------
    public double getMoney() { return money; }
    public void setMoney(double money) { this.money = money; }
    //-------------------- Output information
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    public String toString() {
        return "Steve  {" + "\u001B[35m" +" Name= " + name  +" Id= " + id + " Money= " + money + "$" +
                "\u001B[31m"+" Hp= " + f.format(hp) +"\u001B[36m"+ " Damage= " + damage +"\u001B[0m" + " x: " + x + "| y: "+ y +" }";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Steve entity = (Steve) o;
        return this.hp == entity.hp &&
                this.name.equals(entity.name) &&
                this.id == entity.id &&
                this.damage == entity.damage;
    }
    public static int compareTo(Entity other,Entity entity1) {
        return other.getName().compareTo(entity1.getName());
    }
}