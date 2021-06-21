package Entities;

import Item.Items;
import javafx.scene.image.Image;
import sample.Main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static sample.Main.rnd;
import static sample.Main.world;

public class Steve extends Entities.Entity  {
    private double money;
    private final ArrayList<Items> inventory = new ArrayList<>();
    //------------------------------- Constructors
    public Steve(String name,double hp, double damage,String position,int x,int y) {
        super(new Image( "images/SteveHead.png" ), name,hp,damage,position,"Steve",x,y);
        mouseActivate(x,y);
    }

    public Steve(String name,double hp, double damage,String position,int x,int y,Image image) { // for extends classes
        super(image, name,hp,damage,position,"Steve",x,y);
        mouseActivate(x,y);
    }

    public Steve(String name,double hp, double damage,int x,int y) {
        super(new Image( "images/SteveHead.png" ), name,hp,damage,"world","Steve",x,y);
        mouseActivate(x,y);
    }
    public Steve(){ this("Steve",20,2,"world", (int) (Math.random() * 3500),(int) (Math.random() * 1800)); }
    //---------------------(ONLY STEVE)-------------------------------\\
    public void mouseActivate( double mx, double my)
    {
        if( getImg().boundsInParentProperty().get().contains(mx,my) ) {
            activate();
            if(super.isActive()){
                getImg().setImage(new Image("images/SteveHeadActive.png"));
            }
            else getImg().setImage(new  Image("images/SteveHead.png"));
        }
    }

    //================== INVENTORY ========================== \\
    public void addInv(Items item){
        if(inventory.contains(item)){
            int inx = inventory.lastIndexOf(item);
            inventory.get(inx).setAmount(1);
        }
        else inventory.add(item);
    }
    public void delInv(int index){
        if(inventory.get(index).getAmount() < 1){
            inventory.remove(index);
        }
        else inventory.get(index).setAmount(-1);
        for(int i =0;i<inventory.size();++i){
            if(inventory.get(i).getAmount() == 0) inventory.remove(inventory.get(i));
        }
    }
    public ArrayList<Items> getInv(){ return this.inventory; }
    //--------------------   Actions
    public void Attack(Entity entity) {
        entity.setHp(entity.getHp() - this.getDamage());
        refresh();
    }
    public void Healing(double heal){
        if(super.getHp() > 1){
            double hp = getHp();
            setHp(hp + heal);
            System.out.println("\u001B[31m" + super.getName() + " + "+ Math.round(heal) + " hp!" + "\u001B[0m");
        }
    }
    public void eat(Items item) {
        if(this.inventory.contains(item)){
            setHp(getHp()+ inventory.get(getInv().lastIndexOf(item)).getFeed());
            this.inventory.get(getInv().lastIndexOf(item)).setAmount(-1);
            if(inventory.get(getInv().lastIndexOf(item)).getAmount() < 1)
                inventory.remove(item);
        }
    }
    public double getMoney() { return money; }
    public void setMoney(double money){this.money = money;}
    public void setProcess(){ this.process++;}
    public int getProcess(){return process;}
    public void clearProcess(){process = 0;}
    public void heal(double hp){
        if(hp < 20){
            setHp(hp);
        }
    }
    //-------------------- Output information
    @Override public String toString() {
        DecimalFormat f = new DecimalFormat("##.00");
        return "\nСтів Новачок  {" + "\u001B[35m" +" Name= " + super.getName()  +" Id= " + super.getId() +
                " Money= " + money + "$" + "\u001B[31m"+" Hp= " + f.format(super.getHp()) +
                "\u001B[36m"+ " Damage= " + super.getDamage() +"\u001B[0m"
                + " x: " + super.getX() + "| y: "+ super.getY() +" }";
    }
    @Override public  int compareTo(Entity p){
        return Integer.parseInt(String.valueOf(Double.compare(money, p.getMoney())));
    }
    @Override public Entity clone(){
        Entity clone = new Steve(super.getName(),super.getHp(),super.getDamage(),super.getPosition(),(int) super.getX(),(int)getY());
        for(Items item : getInv()){
            clone.addInv(item.clone());
        }
        return clone;
    }
    public void lifeCycle(){
        if(isActive) return;
        if(getHp() < 5){
            if(getId() == -1) return;
            newx= Main.world.hbg.getX()+170;
            newy= Main.world.hbg.getY()+120;
            double dx = newx - imageView.getX();
            dx = (Math.abs(dx) > getSpeed()) ? Math.signum(dx) * getSpeed() : dx;
            double dy = newy - imageView.getY();
            dy = (Math.abs(dy) > getSpeed()) ? Math.signum(dy) * getSpeed() : dy;
            setX(imageView.getX() + dx);
            setY(imageView.getY() + dy);
        }
        else {
            if (life) {
                if ((imageView.getX() == newx) && (imageView.getY() == newy)) {
                    newx = getBPosX() + Math.random() * getPosX();
                    newy = getBPosY() + Math.random() * getPosY();
                }
                if (getId() == -1) return;
                double dx = newx - imageView.getX();
                dx = (Math.abs(dx) > getSpeed()) ? Math.signum(dx) * getSpeed() : dx;
                double dy = newy - imageView.getY();
                dy = (Math.abs(dy) > getSpeed()) ? Math.signum(dy) * getSpeed() : dy;
                setX(imageView.getX() + dx);
                setY(imageView.getY() + dy);
            }
            else if (goToCave) {
                if ((imageView.getX() == newx) && (imageView.getY() == newy)) {
                    newx = getBPosX() + Math.random() * getPosX();
                    newy = getBPosY() + Math.random() * getPosY();
                }
                if (getId() == -1) return;
                double dx = newx - imageView.getX();
                dx = (Math.abs(dx) > getSpeed()) ? Math.signum(dx) * getSpeed() : dx;
                double dy = newy - imageView.getY();
                dy = (Math.abs(dy) > getSpeed()) ? Math.signum(dy) * getSpeed() : dy;
                setX(imageView.getX() + dx);
                setY(imageView.getY() + dy);
            }
            else if (goToHome) {
                if ((imageView.getX() == newx) && (imageView.getY() == newy)) {
                    newx = getBPosX() + (Math.random() * 150);
                    newy = getBPosY() + (Math.random() * 100);

                }
                if (getId() == -1) return;
                double dx = newx - imageView.getX();
                dx = (Math.abs(dx) > getSpeed()) ? Math.signum(dx) * getSpeed() : dx;
                double dy = newy - imageView.getY();
                dy = (Math.abs(dy) > getSpeed()) ? Math.signum(dy) * getSpeed() : dy;
                setX(imageView.getX() + dx);
                setY(imageView.getY() + dy);
            }
            else if(seekFood){
                if ((imageView.getX() == newx) && (imageView.getY() == newy)) {
                        world.dig(this);
                        newx = rnd.nextInt(3759);
                        newy = rnd.nextInt(1969);
                }
                double dx = newx - imageView.getX();
                dx = (Math.abs(dx) > getSpeed()) ? Math.signum(dx) * getSpeed() : dx;
                double dy = newy - imageView.getY();
                dy = (Math.abs(dy) > getSpeed()) ? Math.signum(dy) * getSpeed() : dy;
                setX(imageView.getX() + dx);
                setY(imageView.getY() + dy);
            }
        }
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
            inventory.clear();
        }
        catch (IOException e){
            System.out.println("Missing file");
        }
    }
    public void save( FileWriter fileWriter ) throws IOException {
        fileWriter.write("1\n");
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
    }

}