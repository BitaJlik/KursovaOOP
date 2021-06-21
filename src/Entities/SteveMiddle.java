package Entities;

import Item.Items;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class SteveMiddle extends Steve {
    private double chance;
    public SteveMiddle(String name,double hp,double damage,double chance,String position ,int x,int y){
        super(name,hp,damage,position,x,y,new Image("images/SteveHeadMiddle.jpg"));
        mouseActivate(x,y);
        this.chance = chance;
    }
    public SteveMiddle(){ this("Steve",20,2,10, "world", (int) (Math.random() * 3500),(int) (Math.random() * 1800)); }

    public SteveMiddle(String name,double hp,double damage,double chance,String position ,int x,int y,Image image){
        super(name,hp,damage,position,x,y,image);
        mouseActivate(x,y);
        this.chance = chance;
    }
    public void mouseActivate( double mx, double my)
    {
        if( getImg().boundsInParentProperty().get().contains(mx,my) ) {
            activate();
            if(isActive()){
                getImg().setImage(new Image("images/SteveHeadMiddleActive.jpg"));
            }
            else getImg().setImage(new Image("images/SteveHeadMiddle.jpg"));
        }
    }
    public double getChance(){return this.chance;}
    public void setChance(double chance){this.chance = chance;}
    public void heal(double hp){
        if(hp < 30){
            setHp(hp);
        }
    }

    @Override public void Attack(Entity entity) {
        int rnd = 1+(int) (Math.random()*100);
        if(rnd < chance){
            setDamage(super.getDamage()+5);
        }
        super.Attack(entity);
        setDamage(super.getDamage() - 5);
    }
    @Override public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return "\nСтів Бувалий {" + "\u001B[35m" + " " +
                "Name= " + super.getName()  + " " +
                "Id= " + super.getId() + "\u001B[31m" + " "+
                "Hp= " + f.format(super.getHp()) +"\u001B[36m"+ " " +
                "Damage= " + super.getDamage() + "\u001B[32m" + " " +
                "x: " + super.getX() +" | " +
                "y: " + super.getY() + "\u001B[0m"+" }";
    }
    @Override public Entity clone(){
        Entity clone =  new SteveMiddle(super.getName(), super.getHp(), super.getDamage(), getChance(),super.getPosition(),(int)getX(),(int)getY());
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
            this.chance = ((Double.parseDouble(bufferedReader.readLine())) );

        }
        catch (IOException e){
            System.out.println("Missing file");
        }
    }

    public void save( FileWriter fileWriter ) throws IOException {
        fileWriter.write("2\n");
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
        fileWriter.write(String.valueOf(chance));
        fileWriter.write("\n");
    }
}
