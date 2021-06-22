package Structures;

import Entities.Entity;
import Entities.Spider;
import Entities.Steve;
import Item.Food;
import Item.Items;
import Item.Ores;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import sample.Main;
import sample.Menu;

import java.util.ArrayList;

public class World {
    private final ArrayList<Entity> world = new ArrayList<>();
    public Home home;
    public Cave cave;
    public Group groupWorld;
    public Group bg;
    public ImageView back;
    public ImageView miniback;
    public ImageView hbg;
    public ImageView cbg;
    public Label labelHome;
    public Label labelCave;
    private int sizeHome;
    private int sizeCave;
    ImageView minihbg;
    ImageView minicbg;
    Scale scale;
    private boolean isDay = true;
    public boolean passive = true;

    public World(int homeX, int homeY, int caveX, int caveY, int size) {
        home = new Home(homeX, homeY);
        labelHome = new Label("TEXT");
        labelHome.setFont(new Font(15));
        labelHome.setTextFill(Color.web("white"));

        cave = new Cave(caveX, caveY);
        labelCave = new Label("TEXT");
        labelCave.setFont(new Font(15));
        labelCave.setTextFill(Color.web("white"));

        bg = new Group();
        back = new ImageView(new Image("images/Fon.png"));
        hbg = home.getImg();
        hbg.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText("МакроОб'єкт Дім");
                alert.setContentText(Menu.seeWorld(home.getHome()));
                alert.showAndWait();
            }
        });
        hbg.setY(200);
        hbg.setFitWidth(400);
        hbg.setFitHeight(200);
        labelHome.setLayoutX(0);
        labelHome.setLayoutY(185);

        cbg = cave.getImg();
        cbg.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText("МакроОб'єкт Дім");
                alert.setContentText(Menu.seeWorld(cave.getCave()));
                alert.showAndWait();
            }
        });
        cbg.setX(1720);
        cbg.setY(1200);
        cbg.setFitWidth(400);
        cbg.setFitHeight(200);
        labelCave.setLayoutX(1720);
        labelCave.setLayoutY(1180);
        scale = new Scale();

        groupWorld = new Group(back, hbg, cbg,labelCave,labelHome);
        Ores.oresinit();
        Food.foodsinit();
        registryItems();
    }
    public Group getMiniMap() {
        Group group = new Group();
        miniback = new ImageView(new Image("images/Fon.png"));
        miniback.getTransforms().add(scale);
        Group minibg = new Group();
        minihbg = new ImageView(new Image("images/homebg.png"));
        minicbg = new ImageView(new Image("images/cavetopbg.png"));

        minihbg.setY(200);
        minihbg.setFitWidth(400);
        minihbg.setFitHeight(200);
        minicbg.setX(1720);
        minicbg.setY(1200);
        minicbg.setFitWidth(400);
        minicbg.setFitHeight(200);

        scale.setX(0.15);
        scale.setY(0.15);

        minibg.getTransforms().add(scale);
        minihbg.getTransforms().add(scale);
        minicbg.getTransforms().add(scale);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(284);
        rectangle.setY(2);
        rectangle.setHeight(150);
        rectangle.setStroke(Color.GREENYELLOW);
        rectangle.setStrokeWidth(3);
        rectangle.setFill(Color.TRANSPARENT);

        group.getChildren().addAll(miniback, minihbg, minicbg,rectangle);

        group.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (event.getX() < 190) {
                    Main.scrollPane.setHvalue(0);
                    rectangle.setX(0);
                } else if(event.getX() > 190 & event.getX() < 300){
                    Main.scrollPane.setHvalue(0.5);
                    rectangle.setX(150);
                } else if (event.getX() > 300) {
                    Main.scrollPane.setHvalue(1);
                    rectangle.setX(284);
                }
                if (event.getY() < 150) {
                    Main.scrollPane.setVvalue(0);
                    rectangle.setY(2);
                } else if (event.getY() > 150) {
                    Main.scrollPane.setVvalue(1);
                    rectangle.setY(150);
                }
            }
        });
        return group;
    }

    public void Day() {
        passive = true;
        this.isDay = true;
        back.setImage(new Image("images/Fon.png"));
        miniback.setImage(new Image("images/Fon.png"));
    }

    public void Night() {
        passive = false;
        this.isDay = false;
        back.setImage(new Image("images/FonNight.png"));
        miniback.setImage(new Image("images/FonNight.png"));
    }

    public boolean isDay() { return isDay;}
    //----------------------------
    public ArrayList<Entity> getWorld() { return this.world; }

    public void updateWorld() {
        labelHome.setText( "Об'єктів в домі: " + home.getHome().size());
        labelCave.setText("Об'єктів в шахті: " + cave.getCave().size());
    }
    //----------------------------
    public void dig(Entity obj){
        if(obj.getId() == -1) return;
        if(obj.getImg().getBoundsInParent().intersects(back.getBoundsInParent())) {
            if (obj instanceof Steve) {
                if (obj.getIdAim() == -1) {
                    obj.setIdAim((int) (Math.random() * Food.foods.size()));
                }
                if (obj.getProcess() >= Food.foods.get(obj.getIdAim()).getDigseconds()) {
                    System.out.println("Добули " + Food.foods.get(obj.getIdAim()).getName());
                    obj.addInv(Food.foods.get(obj.getIdAim()));
                    if(!isDay){
                        int chance = (int) (Math.random() * 100);
                        if (chance > 80) {
                            Menu.deathBatlle(obj, new Spider());
                        }
                    }
                    obj.clearProcess();
                    obj.setIdAim(-1);
                } else obj.setProcess();
                obj.refresh();
            }
        }
    }
    //----------------------------
    public static void registryItems(){
        Items.globalList.addAll(Ores.ores);
        Items.globalList.addAll(Food.foods);
    }
    public boolean isAllWorldEmpty(){
        Menu.update();
        return world.size() == 0 && home.getHome().size() == 0 && cave.getCave().size() == 0;
    }
    public ArrayList<Entity> getAllWorld(){
        ArrayList<Entity> entities = new ArrayList<>();
        entities.addAll(world);
        entities.addAll(home.getHome());
        entities.addAll(cave.getCave());
        entities.removeIf(entity -> entity.getHp() < 1 || entity.getId() == -1 );
        return entities;
    }
    public void mouse(double mx,double my){
            for (int i = 0; i < getAllWorld().size(); i++) {
                getAllWorld().get(i).mouseActivate(mx,my);
            }
    }
    public Entity edit(double mx,double my){
        for(Entity entity : getAllWorld()){
            if( entity.getImg().boundsInParentProperty().get().contains(mx,my) )
                return entity;
        }
        return null;
    }
    public void cloneEntities() {
        for(Entity entity : getAllWorld()){
            if(entity.isActive()){
                Entity entity1 = entity.clone();
                entity1.setX(entity.getX()+45);
                entity1.setY(entity.getY()+45);
                world.add(entity1);
            }
        }
    }
}
