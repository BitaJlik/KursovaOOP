package oop.Entities;

import oop.Progress;

import java.text.DecimalFormat;

public class Steve extends Entity {

    private double money;
    //------------------------------- Constructors
    public Steve(String name,double hp, double damage) { super(name,hp,damage); }
    public Steve(){ this("Стів",20,2); }
    //---------------------(ONLY STEVE)-------------------------------\\
    //--------------------   Actions
    public void Attack() { System.out.println("\u001B[31m" + super.getName() +" атакує!" + "\u001B[0m"); }
    public void Healing(double heal){
        if(super.getHp() > 1){
        double hp = getHp();
        setHp(hp + heal);
        System.out.println("\u001B[31m" + super.getName() + " + "+ Math.round(heal) + " hp!" + "\u001B[0m");
        }
    }
    public void Death(){
        System.out.println(super.getName() + "\u001B[31m"  + " помирає" + "\u001B[0m");
        super.setHp(0);
        super.setDamage(0);
        super.setId(-1);
        super.setSpeed(0);
    }
    public void Sleeping() {
        if(super.getHp() > 1){
             System.out.println(super.getName() +"\u001B[32m" + " Спить" + "\u001B[0m");
             Progress.func(2);
             Healing(3);
        }
    }
    //----------------------------- Movements
    public  void moveUp(){ setY(getY() + super.getSpeed()); }
    public  void moveDown(){ setY(getY() + (-1 * super.getSpeed())); }
    public  void moveLeft(){ setX(getX() + (-1 * super.getSpeed())); }
    public  void moveRight(){ setX(getX() + super.getSpeed()); }
    //------------------------------ Getters & Setters

    //------------------
    public double getMoney() {return money; }
    public void setMoney(double money) { this.money = money; }
    //-------------------- Output information
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    public String toString() {
        return "Steve  {" + "\u001B[35m" +" Name= " + super.getName()  +" Id= " + super.getId() +
                " Money= " + money + "$" + "\u001B[31m"+" Hp= " + f.format(super.getHp()) +
                "\u001B[36m"+ " Damage= " + super.getDamage() +"\u001B[0m"
                + " x: " + super.getX() + "| y: "+ super.getY() +" }";
    }
    @Override
    public int compareTo(Entity o) {
        Steve steve = (Steve) o;
        return super.getName().compareTo(steve.getName());
    }
}