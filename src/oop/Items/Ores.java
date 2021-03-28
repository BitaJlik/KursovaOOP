package oop.Items;

import java.util.ArrayList;

public class Ores extends Items{
    public static ArrayList<Items> ores = new ArrayList<>();
    private String name;
    private int id;
    private double price;
    private int amount;
    private int digSeconds;
    public Ores(String name, int id, double price,int digSeconds,int amount) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.amount = amount;
        this.digSeconds = digSeconds;
    }
    public Ores(){ this("Неизвестно",-1,0,-1,1); }// If object is not correct
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = this.amount + amount; }
    public int getDigseconds() { return digSeconds; }
    public void setDigseconds(int digSeconds) { this.digSeconds = digSeconds; }
    @Override
    public String toString(){
        return "Руда  "+ name + " | " +"\u001B[32m"+ price +"\u001B[0m" +"$ ";
    }
    public static void oresinit(){
        ores.add( new Ores("Алмаз",0,10,5,0));
        ores.add( new Ores("Золото",1,8,5,0));
        ores.add( new Ores("Залізо",2,4,3,0));
        ores.add( new Ores("Свинець",3,4,3,0));
        ores.add( new Ores("Мідь",4,3,3,0));
    }
}
