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

    //-------------------- Default methods

    @Override
    public String toString(){
        return "Еда  "+ super.getName() + " | " +"\u001B[32m"+ super.getPrice() +"\u001B[0m" +"$ ";
    }

    public static void foodsinit(){
        foods.add( new Food("Хлeб",0,1,0,2,5));
        foods.add( new Food("Яблуко",1,2,0,1,4));
        foods.add( new Food("Кокос",2,4,0,2,10));
        foods.add( new Food("Банан",3,4,0,1,8));
        foods.add( new Food("Риба",4,2,0,1,1));
    }
}
