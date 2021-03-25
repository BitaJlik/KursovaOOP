package oop.Items;

import java.util.ArrayList;

public abstract class Items  {
    public static ArrayList<Items> items = new ArrayList<>();
    private String name;
    private int id ;
    private double price;
    private int amount = 0;
    private int digSeconds;
    //-------------------- Default methods
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
    //------------------------
    public static void initialize(){
        items.add( new Ores("Алмаз",0,10,5,0));
        items.add( new Ores("Золото",1,8,5,0));
        items.add( new Ores("Залізо",2,4,3,0));
        items.add( new Ores("Свинець",3,4,3,0));
        items.add( new Ores("Мідь",4,3,3,0));
    }

}
