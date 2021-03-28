package oop.Items;

import java.util.ArrayList;

public class Food extends Items{
    public static ArrayList<Items> foods = new ArrayList<>();
    private String name;
    private int id ;
    private double price;
    private int amount = 0;
    private double feed;
    private int digSeconds;

    public Food(String name, int id, double price, int amount, double feed,int digSeconds) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.amount = amount;
        this.feed = feed;
    }

    public Food(){ this("Неизвестно",-1,0,0,0,0); }// If object is not correct

    //-------------------- Default methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = this.amount + amount; }
    public double getFeed() { return feed; }
    public void setFeed(double feed) { this.feed = feed; }
    public int getDigseconds() { return digSeconds; }
    public void setDigseconds(int digSeconds) { this.digSeconds = digSeconds; }
    @Override
    public String toString(){
        return "Еда  "+ name + " | " +"\u001B[32m"+ price +"\u001B[0m" +"$ ";
    }

    public static void foodsinit(){
        foods.add( new Food("Хліб",0,1,0,2,5));
        foods.add( new Food("Яблуко",1,2,0,1,4));
        foods.add( new Food("Кокос",2,4,0,2,10));
        foods.add( new Food("Банан",3,4,0,1,8));
    }
}
