package oop.Items;

public class Ores extends Items{
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
    public Ores(){ // If object is not correct
        this("Неизвестно",-1,0,-1,1);
    }
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
}
