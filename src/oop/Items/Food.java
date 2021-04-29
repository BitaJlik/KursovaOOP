package oop.Items;

import java.util.ArrayList;

public class Food extends Items{
    public static ArrayList<Items> foods = new ArrayList<>();
     private double feed;

    public Food(String name, int id, double price, int amount, double feed,int digSeconds) {
        super(name,id,price,amount,digSeconds);
        this.feed = feed;
    }

    public Food(){ this("Неизвестно",-1,0,0,0,0); }// If object is not correct

    public double getFeed() { return this.feed; }
    public void setFeed(double feed) {this.feed = feed;  }
    //-------------------- Default methods

    @Override
    public String toString(){
        return "Food  "+ super.getName() + " | " +"\u001B[32m"+ super.getPrice() +"\u001B[0m" +"$ ("+ super.getAmount() + ")шт.";
    }

    public static void foodsinit(){
        foods.add( new Food("Хлeб",0,1,1,2,5));
        foods.add( new Food("Яблуко",1,2,1,1,4));
        foods.add( new Food("Кокос",2,4,1,2,5));
        foods.add( new Food("Банан",3,4,1,1,4));
        foods.add( new Food("Риба",4,2,1,1,2));
    }
    public Items clone(){
        return new Food(super.getName(),super.getId(),super.getPrice(),super.getAmount(),getFeed(),super.getDigseconds());
    }
}
