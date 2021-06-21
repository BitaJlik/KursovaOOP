package Entities;

import Item.Items;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class StevePro extends SteveMiddle {
    private int power;
    public StevePro(String name,double hp,double dmg,double chance,int power,String position,int x,int y){
        super(name,hp,dmg,chance,position,x,y,new Image("images/SteveHeadPro.jpg"));
        mouseActivate(x,y);
        this.power = power;
    }
    public StevePro(){ this("Стів",40,4,15,20,"world", (int) (Math.random() * 3500),(int) (Math.random() * 1800)); }
    public void mouseActivate( double mx, double my)
    {
        if( getImg().boundsInParentProperty().get().contains(mx,my) ) {
            activate();
            if(isActive()){
                getImg().setImage(new Image("images/SteveHeadProActive.jpg"));
            }
            else getImg().setImage(new Image("images/SteveHeadPro.jpg"));
        }
    }
    public int getPower() {return power; }
    public void setPower(int power) { this.power = power; }
    public void heal(double hp){
        if(hp < 40){
            setHp(hp);
        }
    }
    @Override public void Attack(Entity entity) {
        super.Attack(entity);
        int beg = (int)super.getDamage();
        super.setDamage(super.getDamage()*2);
        super.Attack(entity);
        super.setDamage(beg);
        int rnd = 1+(int) (Math.random()*100);
        if(rnd > 70){
            System.out.println("Критичний удар!!! (x2)");
            this.power =+ -5;
        }
    }
    @Override public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return "\nСтів Профі {" + "\u001B[35m" + " " +
                "Name= " + super.getName()  + " " +
                "Id= " + super.getId() + "\u001B[31m" + " "+
                "Hp= " + f.format(super.getHp()) +"\u001B[36m"+ " " +
                "Damage= " + super.getDamage() + "\u001B[32m" + " " +
                "x: " + super.getX() +" | " +
                "y: " + super.getY() + "\u001B[0m"+" }";
    }
    @Override public Entity clone(){
        Entity clone =  new StevePro(super.getName(), super.getHp(), super.getDamage(), getChance(),getPower(),super.getPosition(),(int)super.getX(),(int)super.getY());
        for(Items item : this.getInv()){
            clone.addInv(item.clone());
        }
        return clone;
    }
    public void open(BufferedReader bufferedReader){
        try {
            super.setName(bufferedReader.readLine());
            super.setHp(Double.parseDouble(bufferedReader.readLine()));
            super.setDamage(Double.parseDouble(bufferedReader.readLine()));
            super.setMoney(Double.parseDouble(bufferedReader.readLine()));
            super.setX(Double.parseDouble(bufferedReader.readLine()));
            super.setY( Double.parseDouble(bufferedReader.readLine()))     ;
            super.isActive = (bufferedReader.readLine().equals("true"));
            super.setChance(((Double.parseDouble(bufferedReader.readLine()))));
            this.power = Integer.parseInt(bufferedReader.readLine());
        }
        catch (IOException e){
            System.out.println("Missing file");
        }
    }
    public void save( FileWriter fileWriter ) throws IOException {
        fileWriter.write("3\n");
        fileWriter.write( getName() );
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getHp()));
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getDamage()));
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getMoney()));
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getX()));
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getY()));
        fileWriter.write("\n");
        fileWriter.write(isActive ? "true" : "false");
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getChance()));
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(power));
        fileWriter.write("\n");
    }
}
