package oop;

public class Items {
    private String name;
    private int id;
    private double price;

    public Items(String name, int id, double price)  {
        this.name = name;
        this.id = id;
        this.price = price;
    }
    public Items() {
        this("Неизвестно",-1,0);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getId() { return id; }
    public void setId(int id) { id = id; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
   static class Item extends Items{
        Items diamond = new Item();

   }
}
