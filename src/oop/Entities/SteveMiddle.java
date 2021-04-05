package oop.Entities;

public class SteveMiddle extends Steve{
    private double chance;
    public SteveMiddle(double chance,String name,double hp,double damage){
        super(name,hp,damage);
        this.chance = chance;
    }
    public double getChance(){return this.chance;}
    public void setChance(double chance){this.chance = chance;}

    @Override
    public void Attack() {
        super.Attack();
        int rnd = 1+(int) (Math.random()*100);
        if(rnd < chance){
            System.out.println("Критичний удар!!!");
        }
    }
}
