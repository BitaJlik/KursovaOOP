package oop.Items;

import java.util.ArrayList;

public abstract class Items  {

    public static ArrayList<Items> globalList = new ArrayList<>();

    private String name;
    private double price;
    private int amount;
    private int digSeconds;
    public Items(String name, double price, int digSeconds){
        this.name = name;
        this.price = price;
        this.amount = 1;
        this.digSeconds = digSeconds;
    }
    //-------------------- Default methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = this.amount + amount; }
    public int getDigseconds() { return digSeconds; }
    public void setDigseconds(int digSeconds) { this.digSeconds = digSeconds; }
    public double getFeed() { return 0; }
    public void setFeed(double feed) {  }
    //------------------------
    public Items clone()  {
        return null;
    }

}
