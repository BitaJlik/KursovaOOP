package oop;

import java.text.DecimalFormat;

public class Spider extends Entity{
    private String name;
    private int id ;
    private double hp;
    private double damage;
    //------------------------------- Конструктори
    public Spider(String name, int id , double hp, double damage) {
        this.name = name;
        this.id = id;
        this.hp = hp;
        this.damage = damage;
    }
    public Spider(){
        this("Павук",1,10,2.2);
    }
    //--------------------   Дії
    public void Attack() {
        System.out.println("\u001B[31m" + name +" атакує!" + "\u001B[0m");
    }
    public void Healing(double heal){
        double hp = getHp();
        setHp(hp + heal);
        System.out.println("\u001B[31m" + name + " + "+ Math.round(heal) + " hp!" + "\u001B[0m");
    }
    public void Death(){
        System.out.println(name + "\u001B[31m"  + " помирає" + "\u001B[0m");
        this.hp = 0;
        this.damage = 0;
        this.id = -1;
    }
    public void Sleeping() {
        System.out.println(name +"\u001B[32m" + " Спить" + "\u001B[0m");
        Progress.func();
        Healing(3);
    }
    //------------------------------ Геттери \ Сеттери
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //-----------------
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //-----------------
    public double getHp() {
        return hp;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    //------------------
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    //-------------------- Вивод інформації
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    public String toString() {
        return "Spider {" + "\u001B[35m" +" Name= " + name  +" Id= " + id +
                "\u001B[31m"+" Hp= " + f.format(hp) +"\u001B[36m"+ " Damage= " + damage +"\u001B[0m" +"  }";
    }
}