package Item;

import java.util.ArrayList;

public class Food extends Items{
    public static ArrayList<Items> foods = new ArrayList<>();
     private double feed;

    public Food(String name, double price, double feed,int digSeconds) {
        super(name,price,digSeconds,"Food");
        this.feed = feed;
    }

    public Food(){ this("Неизвестно",0,0,0); }// If object is not correct

    public double getFeed() { return this.feed; }
    public void setFeed(double feed) { this.feed = feed; }
    //-------------------- Default methods
    @Override
    public String toString(){
        return "Food  "+ super.getName() + " | " +"\u001B[32m"+ super.getPrice() +"\u001B[0m" +"$ ("+ super.getAmount() + ")шт.";
    }
    public static void foodsinit(){
        foods.add( new Food("Хлeб",1,2,5));
        foods.add( new Food("Яблуко",2,1,4));
        foods.add( new Food("Кокос",4,2,5));
        foods.add( new Food("Банан",3,1,4));
        foods.add( new Food("Риба",2,1,2));
    }
    public String getKlas(){
        return "Food";
    }
    public Items clone(){
        return new Food(super.getName(),super.getPrice(),getFeed(),super.getDigseconds());
    }
}
