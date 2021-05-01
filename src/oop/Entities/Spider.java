package oop.Entities;

import oop.Progress;

import java.text.DecimalFormat;

public class Spider extends Entity {
    //------------------------------- Constructors
    public Spider(String name , double hp, double damage) {
        super(name,hp,damage);

    }
    public Spider(){ this("Павук",10,2.2); }
    //------------------------------- Actions
    public void Attack() { System.out.println("\u001B[31m" + super.getName() +" атакує!" + "\u001B[0m"); }
    public void Healing(double heal){
        double hp = getHp();
        setHp(hp + heal);
        System.out.println("\u001B[31m" + super.getName() + " + "+ Math.round(heal) + " hp!" + "\u001B[0m");
    }
    public void Death(){
        System.out.println(super.getName() + "\u001B[31m"  + " помирає" + "\u001B[0m");
        super.setHp(0);
        super.setDamage(0);
        super.setId(-1);
        super.setSpeed(0);
    }
    public void Sleeping() {
        System.out.println(super.getName() +"\u001B[32m" + " Спить" + "\u001B[0m");
        Progress.func(1);
    }
    //-------------------------------
    public  void moveUp(){ setY(getY() + super.getSpeed()); }
    public  void moveDown(){ setY(getY() + (-1 * super.getSpeed())); }
    public  void moveLeft(){ setX(getX() + (-1 * super.getSpeed())); }
    public  void moveRight(){ setX(getX() + super.getSpeed()); }
    //-------------------- Output information
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    public String toString() {
        return "Павук {" + "\u001B[35m" +" Name= " + super.getName()  +" Id= " + super.getId() +
                "\u001B[31m"+" Hp= " + f.format(super.getHp()) +"\u001B[36m"+ " Damage= " + super.getDamage() +
                "\u001B[32m" + " x: " + super.getX() +" | " +"y: "+ super.getY() + "\u001B[0m"+" }";
    }
}
