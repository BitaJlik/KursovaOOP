package oop;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import oop.Entities.Entity;
import oop.Entities.Steve;
import oop.Entities.SteveMiddle;
import oop.Entities.StevePro;
import oop.Items.Food;
import oop.Items.Items;
import oop.Structures.World;

import java.util.Scanner;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    public static World world = new World(25,25,475,475);
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        world.home.addToHome(new Steve());
        ((Entity) world.home.getHome().get(0)).addInv(Items.globalList.get(0));
        ((Entity) world.home.getHome().get(0)).setPosition("world");
        ((Entity) world.home.getHome().get(0)).addInv(Items.globalList.get(1));
        ((Entity) world.home.getHome().get(0)).addInv(Food.foods.get(0));
        launch(args);
        MainLaunch();
    }
    public static void MainLaunch(){
        MainMenu.update();
        if(world.isAllWorldEmpty()){
            System.out.println("Less objects!");
        }
        else {
            MainMenu.menu();
        }
    }
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BitaJlik");
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        Animation transition = new Transition() {
            {
                setCycleDuration(Duration.millis(750));
                setInterpolator(Interpolator.LINEAR);
            }
            @Override
            protected void interpolate(double frac) {
                // frac is the percentage of how much time of the Transition
                // Already passed (1000 ms => frac = 0.5)
                // Here you just Multiply frac with the designated value
                stage.setHeight(277*frac);
                stage.setWidth(219*frac*-1);
            }
        };
        transition.play();
        stage.show();
    }
    public static Entity rndEntity(){
        Entity entity;
        switch ((int) (Math.random() * 3)){
            case 0 -> entity = new Steve("Стів",Math.random()*20,2);
            case 1 -> entity = new StevePro("Стів",Math.random()*40,6,20,30);
            case 2 -> entity = new SteveMiddle("Стів",Math.random()*30,4,10);
            default -> entity = new Steve();
        }
        return entity;
    }

}