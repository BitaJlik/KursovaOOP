package oop.Entities;

public class StevePro extends SteveMiddle {
    private int power;
    public StevePro(String name,double hp,double dmg,double chance,int power){
        super(name,hp,dmg,chance);
        this.power = power;
    }
    public int getPower() {return power; }
    public void setPower(int power) { this.power = power; }
    @Override
    public void Attack() {
        super.Attack();
        int beg = (int)super.getDamage();
        super.setDamage(super.getDamage()*2);
        super.Attack();
        super.setDamage(beg);
        int rnd = 1+(int) (Math.random()*100);
        if(rnd > 70){
            System.out.println("Критичний удар!!! (x2)");
            this.power =+ -5;
        }
    }
    @Override
    public String toString() {
        return "Стів Профі {" + "\u001B[35m" + " " +
                "Name= " + super.getName()  + " " +
                "Id= " + super.getId() + "\u001B[31m" + " "+
                "Hp= " + f.format(super.getHp()) +"\u001B[36m"+ " " +
                "Damage= " + super.getDamage() + "\u001B[32m" + " " +
                "x: " + super.getX() +" | " +
                "y: " + super.getY() + "\u001B[0m"+" }";
    }
}
