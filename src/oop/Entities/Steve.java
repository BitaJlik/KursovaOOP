package oop.Entities;

import oop.Items.Items;
import oop.Progress;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Steve extends oop.Entities.Entity  {
    private double money;
    private final ArrayList<Items> inventory = new ArrayList<>();
    //------------------------------- Constructors
    public Steve(String name,double hp, double damage,String position) { super(name,hp,damage,position,"Steve"); }
    public Steve(String name,double hp, double damage) { super(name,hp,damage,"home","Steve"); }
    public Steve(){ this("Steve",20,2,"home"); }
    //---------------------(ONLY STEVE)-------------------------------\\
    public double getMoney() { return money; }
    public void setMoney(double money){this.money = money;}
    //
    public void addInv(Items item){
        if(inventory.contains(item)){
            int inx = inventory.lastIndexOf(item);
            inventory.get(inx).setAmount(1);
        }
        else inventory.add(item);
    }
    public void delInv(int index){
        if(inventory.get(index).getAmount() < 1){
            inventory.remove(index);
        }
        else inventory.get(index).setAmount(-1);
    }
    public ArrayList getInv(){ return this.inventory; }
    public void clearInv(){ inventory.clear(); }
    //--------------------   Actions
    public void Attack() {
        System.out.println("\u001B[31m" + super.getName() +" атакує!" + "\u001B[0m");
    }
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
    //-------------------- Output information
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    public String toString() {
        return "Стів Новачок  {" + "\u001B[35m" +" Name= " + super.getName()  +" Id= " + super.getId() +
                " Money= " + money + "$" + "\u001B[31m"+" Hp= " + f.format(super.getHp()) +
                "\u001B[36m"+ " Damage= " + super.getDamage() +"\u001B[0m"
                + " x: " + super.getX() + "| y: "+ super.getY() +" }";
    }
    @Override
    public  int compareTo(Entity p){
        return Integer.parseInt(String.valueOf(Double.compare(money, p.getMoney())));
    }

    @Override
    public Entity clone(){
        Entity clone = new Steve(super.getName(),super.getHp(),super.getDamage(),super.getPosition());
        return clone;
    }
}