package Entities;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import sample.Main;

import java.util.Objects;

import static sample.Main.world;

public abstract class Entity implements Cloneable, ActionsP.Defalut,Comparable<Entity>{
    protected Group group;
    protected ImageView imageView;
    protected ImageView miniImage;
    protected Label label;
    protected Label labelActive;
    protected Line lineHealth;
    protected int process;
    protected Line lineProcess;

    private String name;
    private int id;
    private double hp;
    private double damage;
    private double x;
    private double y;
    private double speed = 3;
    private final double shift = 5;
    private String position;
    private String type;
    protected boolean isActive;
    protected boolean life;
    protected boolean goToHome;
    protected boolean goToCave;
    protected boolean seekFood;
    private int idCaveAim;
    protected double scale;

    {
        idCaveAim = -1;
        scale = 6.76;
    }
    static int idf;

    static {
        idf = 0;
    }

    protected double newx ;
    protected double newy ;
    // So... Constructor
    public Entity(Image img, String _name, double _hp, double _damage, String _position, String type,int x,int y){
        imageView = new ImageView(img);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);

        miniImage = new ImageView(img);
        miniImage.setPreserveRatio(true);
        miniImage.setFitHeight(10);

        label = new Label("TEXT");
        label.setFont(new Font(15));
        label.setText(_name);
        label.setTextFill(Color.web("white"));

        labelActive = new Label("TEXT");
        labelActive.setFont(new Font(15));
        labelActive.setText(_name + " " + _hp + " " + _damage);
        labelActive.setTextFill(Color.web("white"));

        lineHealth = new Line();
        lineHealth.setStroke(Color.RED);
        lineHealth.setStrokeWidth(3);

        lineProcess = new Line();
        lineProcess.setStroke(Color.BLUE);
        lineProcess.setStrokeWidth(3);

        group = new Group(imageView,label,lineHealth,lineProcess);

        this.name = _name;
        this.hp = _hp;
        this.damage = _damage;
        this.position = _position;
        this.type = type;
        id = idf++;
        Main.entities.getChildren().addAll(group);
        Main.miniEntities.getChildren().add(miniImage);
        newx = Main.rnd.nextInt(3800);
        newy = Main.rnd.nextInt(2000);
        setX(x);
        setY(y);
        refresh();
    }
    public void refresh(){
        lineHealth.setEndX( x+(getHp()) );
        label.setText(name);
        if(getProcess() < 5){
            lineProcess.setOpacity(0);
        }
        else {
            lineProcess.setEndX(x + (double) getProcess() / 20);
            lineProcess.setOpacity(1);
        }
        if(getDamage() < 1){
            Death();
        }
        else if(getHp() < 1){
            Death();
        }
        labelActive.setText(name + " " + hp + " " + damage);
    }
    public void move( double dx, double dy ) {
        if(!isActive) return;

        if(position.equals("home") && !world.hbg.getBoundsInParent().intersects(getImg().getBoundsInParent())){ return; }
        if(position.equals("cave") && !world.cbg.getBoundsInParent().intersects(getImg().getBoundsInParent())){ return; }
        setX(imageView.getX() + dx);
        setY(imageView.getY() + dy);

    }
    // ==== Getters position Begin
    public double getBPosX(){
        return switch (position) {
            case "home" -> 1;
            case "cave" -> 1721;
            default -> 0;
        };
    }
    public double getPosX(){
        return switch (position) {
            case "world" -> 3760;
            case "home" -> 360;
            case "cave" -> 2079;
            default -> 0;
        };
    }
    // ==== Getters position End
    public double getBPosY(){
        return switch (position) {
            case "home" -> 200;
            case "cave" -> 1200;
            default -> 0;
        };
    }
    public double getPosY(){
        return switch (position) {
            case "world" -> 1960;
            case "home" -> 360;
            case "cave" -> 1360;
            default -> 0;
        };
    }
    public void setX(double x) {
        if (x < getBPosX() || x > getPosX()) return;
        this.x = x;
        label.setLayoutX(x);
        miniImage.setX(x / scale);
        lineHealth.setStartX(x);
        lineHealth.setEndX(x + (getHp()));
        lineProcess.setStartX(x);
        lineProcess.setEndX(x + (double) getProcess() / 20);
        imageView.setX(x);
    }
    public void setY(double y) {
        if (y < getBPosY() || y > getPosY()) return;
        this.y = y;
        label.setLayoutY(y-15);
        lineHealth.setStartY(y+45);
        lineHealth.setEndY(y+45);
        lineProcess.setStartY(y+49);
        lineProcess.setEndY(y+49);
        miniImage.setY(y/scale);
        imageView.setY(y);
    }
    // (setX\Y|WithoutLimit)
    public void setXwl(double x) {
        this.x = x;
        label.setLayoutX(x);
        miniImage.setX(x / scale);
        lineHealth.setStartX(x);
        lineHealth.setEndX(x + (getHp()));
        lineProcess.setStartX(x);
        lineProcess.setEndX(x + (double) getProcess() / 20);
        imageView.setX(x);
    }
    public void setYwl(double y) {
        this.y = y;
        label.setLayoutY(y-15);
        lineHealth.setStartY(y+45);
        lineHealth.setEndY(y+45);
        lineProcess.setStartY(y+49);
        lineProcess.setEndY(y+49);
        miniImage.setY(y/scale);
        imageView.setY(y);
    }

    public double getX() {return x;}
    public double getY() {return y; }
    public boolean isActive(){return isActive; }
    public void activate(){
        isActive = !isActive;
        if(isActive){
            Main.activeRect.getChildren().add(labelActive);
            labelActive.setLayoutY(Main.yActive+= 15);
        }
        else {
            Main.activeRect.getChildren().remove(labelActive);
            labelActive.setLayoutY(Main.yActive -= 15);
        }
    }
    public void mouseActivate(double mx, double my) {}
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
    public String getName() { return name; }
    public void setName(String name){this.name = name;}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getDamage() { return (double)Math.round(damage * 100d) / 100d ; }
    public void setDamage(double damage) { this.damage = damage; }
    public double getHp() { return hp; }
    public void setHp(double hp) { this.hp = hp; }
    public void heal(double hp){ }
    public void setIdAim(int id){this.idCaveAim = id; }
    public int getIdAim() { return idCaveAim; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public void switchLife(){
        life = !life;
        goToCave = false;
        goToHome = false;
        seekFood = false;
    }
    public void switchCave(){
        goToCave = !goToCave;
        life = false;
        goToHome = false;
        seekFood = false;
        position = "cave";
        newx = getBPosX() + Math.random() * getPosX();
        newy = getBPosY() + Math.random() * getPosY();

    }
    public void switchHome(){
        goToHome = !goToHome;
        goToCave = false;
        seekFood = false;
        life = false;
        position = "home";
        newx = getBPosX() + Math.random() * getPosX();
        newy = getBPosY() + Math.random() * getPosY();
    }
    public void switchSeekFood(){
        if(!isActive) return;
        seekFood = !seekFood;
        goToHome = false;
        goToCave = false;
        life = false;
        position = "world";
        newx = getBPosX() + Math.random() * getPosX();
        newy = getBPosY() + Math.random() * getPosY();
    }
    // ================= JAVAFX ================ \\
    public Group getGroup(){return this.group;}
    public ImageView getImg(){return imageView;}
    public ImageView getMiniImage(){return miniImage;}
    public Label getLabel(){ return labelActive; }


    public void Death(){
        lineHealth.setEndX(0);
        System.out.println(name + " Видалено!");
        this.name = "death";
        this.id = -1;
        this.hp = 0;
        this.damage = 0;
        this.x = 0;
        this.y = 0;
        this.speed = 0;
        this.position = "death";
        this.type = "death";
        this.isActive = false;
        Main.entities.getChildren().remove(this.getGroup());
        Main.miniEntities.getChildren().remove(miniImage);
        Main.activeRect.getChildren().remove(labelActive);
        if(this instanceof Steve){ getInv().clear(); }
    }
    //----------------------------- Movements
    public void moveUp(boolean shift){
        if(shift){
            move(0,-1*(2+this.shift));
        }
        else  move(0,-2);
    }
    public void moveDown(boolean shift){
        if(shift){
            move(0,2+this.shift);
        }
        else  move(0,2);
    }
    public void moveLeft(boolean shift){
        if(shift){
            move(-1*(2+this.shift),0);
        }
        else  move(-2,0);
    }
    public void moveRight(boolean shift){
        if(shift){
            move(2+this.shift,0);
        }
        else  move(2,0);
    }

    public int compareTo(Entity entity) { // Comparable
        if( this.name.equals(entity.name) )
        { return Double.compare(this.hp, entity.hp); }
        else return this.name.compareTo(entity.name);
    }
    @Override public Entity clone(){ return null; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id && Double.compare(entity.hp, hp) == 0 &&
                Double.compare(entity.damage, damage) == 0 &&
                Double.compare(entity.x, x) == 0 &&
                Double.compare(entity.y, y) == 0 &&
                Double.compare(entity.speed, speed) == 0 &&
                Objects.equals(name, entity.name) &&
                Objects.equals(position, entity.position) &&
                Objects.equals(type, entity.type);
    }

    public void lifeCycle() { }
    public static void clearIdf(){idf = 0;}
}