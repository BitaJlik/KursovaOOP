package oop.Items;

import oop.World;

import java.util.ArrayList;

public abstract class Items  {

    public static ArrayList<Items> items = new ArrayList<>();

    private String name;
    private int id ;
    private double price;
    private double feed;
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
    public double getFeed() { return feed; }
    public void setFeed(double feed) { this.feed = feed; }
    //------------------------
    public static void initialize(){
        Ores.oresinit();
        Food.foodsinit();
        World.registryItems();
    }

}
