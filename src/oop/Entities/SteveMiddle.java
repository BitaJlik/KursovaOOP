package oop.Entities;

public class SteveMiddle extends Steve {
    private double chance;
    public SteveMiddle(String name,double hp,double damage,double chance){
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

    @Override
    public String toString() {
        return "Стів Бувалий {" + "\u001B[35m" + " " +
                "Name= " + super.getName()  + " " +
                "Id= " + super.getId() + "\u001B[31m" + " "+
                "Hp= " + f.format(super.getHp()) +"\u001B[36m"+ " " +
                "Damage= " + super.getDamage() + "\u001B[32m" + " " +
                "x: " + super.getX() +" | " +
                "y: " + super.getY() + "\u001B[0m"+" }";
    }
    @Override
    public Entity clone(){
        Entity clone = super.clone();

        return clone;
    }
}
