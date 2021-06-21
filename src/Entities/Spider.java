package Entities;

import javafx.scene.image.Image;
import sample.Main;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import static sample.Main.rnd;

public class Spider extends Entity {
    //------------------------------- Constructors
    public Spider(String name , double hp, double damage,String position,int x,int y) {
        super(new Image( "images/SpiderHead.jpg" ), name,hp,damage,position,"Spider",x,y);
        mouseActivate(x,y);
    }
    public Spider(){ this("Павук",10,1+Math.random()*5,"world", (int) (Math.random() * 3500),(int) (Math.random() * 1800));
        mouseActivate(getX(),getY());
    }
    public void mouseActivate( double mx, double my)
    {
        if( getImg().boundsInParentProperty().get().contains(mx,my) ) {
            activate();
            if(isActive()){
                getImg().setImage(new Image("images/SpiderHeadActive.jpg"));
            }
            else  getImg().setImage(new Image("images/SpiderHead.jpg"));
        }
    }
    //------------------------------- Actions
    public void setProcess(){ this.process++;}
    public int getProcess(){return process;}
    public void clearProcess(){process = 0;}
    public void Attack(Entity entity) {
        entity.setHp(entity.getHp() - this.getDamage());
        refresh();
    }
    public void kamikaze(){
        Main.world.home.setHp(Main.world.home.getHp() - 5);
        this.Death();
    }
    public void Healing(double heal){
        double hp = getHp();
        setHp(hp + heal);
        System.out.println("\u001B[31m" + super.getName() + " + "+ Math.round(heal) + " hp!" + "\u001B[0m");
    }
    //-------------------- Output information
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    public String toString() {
        return "\nПавук {" + "\u001B[35m" +" Name= " + super.getName()  +" Id= " + super.getId() +
                "\u001B[31m"+" Hp= " + f.format(super.getHp()) +"\u001B[36m"+ " Damage= " + super.getDamage() +
                "\u001B[32m" + " x: " + super.getX() +" | " +"y: "+ super.getY() + "\u001B[0m"+" }";
    }
    public void lifeCycle(){
        if(isActive) return;
            if (life) {
                if (getId() == -1) return;
                if ((imageView.getX() == newx) && (imageView.getY() == newy)) {
                    newx = rnd.nextInt(3759);
                    newy = rnd.nextInt(1969);
                }
                double dx = newx - imageView.getX();
                dx = (Math.abs(dx) > getSpeed()) ? Math.signum(dx) * getSpeed() : dx;
                double dy = newy - imageView.getY();
                dy = (Math.abs(dy) > getSpeed()) ? Math.signum(dy) * getSpeed() : dy;
                setX(imageView.getX() + dx);
                setY(imageView.getY() + dy);
            } else if (goToCave) {
                if (getId() == -1) return;
                newx = Main.world.cbg.getX() + 50;
                newy = Main.world.cbg.getY() + 50;
                double dx = newx - imageView.getX();
                dx = (Math.abs(dx) > getSpeed()) ? Math.signum(dx) * getSpeed() : dx;
                double dy = newy - imageView.getY();
                dy = (Math.abs(dy) > getSpeed()) ? Math.signum(dy) * getSpeed() : dy;
                setX(imageView.getX() + dx);
                setY(imageView.getY() + dy);
            } else if (goToHome) {
                if (getId() == -1) return;
                newx = Main.world.hbg.getX() + 170;
                newy = Main.world.hbg.getY() + 120;
                double dx = newx - imageView.getX();
                dx = (Math.abs(dx) > getSpeed()) ? Math.signum(dx) * getSpeed() : dx;
                double dy = newy - imageView.getY();
                dy = (Math.abs(dy) > getSpeed()) ? Math.signum(dy) * getSpeed() : dy;
                setX(imageView.getX() + dx);
                setY(imageView.getY() + dy);
                if(newx == getX() && newy == getY()){
                    this.kamikaze();
                }
            }
    }
    public void open(BufferedReader bufferedReader){
        try {
            super.setName(bufferedReader.readLine());
            super.setHp(Double.parseDouble(bufferedReader.readLine()));
            super.setDamage(Double.parseDouble(bufferedReader.readLine()));
            super.setX(Double.parseDouble(bufferedReader.readLine()));
            super.setY( Double.parseDouble(bufferedReader.readLine()))     ;
            super.isActive = (bufferedReader.readLine().equals("true"));
        }
        catch (IOException e){
            System.out.println("Missing file");
        }
    }
    public void save( FileWriter fileWriter ) throws IOException {
        fileWriter.write("4\n");
        fileWriter.write( getName() );
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getHp()));
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getDamage()));
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getX()));
        fileWriter.write("\n");
        fileWriter.write(String.valueOf(getY()));
        fileWriter.write("\n");
        if (isActive) {
            fileWriter.write("true");
        }
        else fileWriter.write("false");
        fileWriter.write("\n");
    }

}
