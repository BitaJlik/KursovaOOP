package oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.Entities.Entity;
import oop.Entities.Steve;
import oop.Entities.SteveMiddle;
import oop.Entities.StevePro;
import oop.Items.Items;
import oop.Structures.World;

import java.util.Random;
import java.util.Scanner;

public class Main extends Application {
    public static World world = new World();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws CloneNotSupportedException {
        Items.initialize();
        Entity entity1 = new Steve("Гоша",20,6);

        Entity entity2 = entity1.clone();

        MainMenu.seeInventory(entity1);
        System.out.println();
        MainMenu.seeInventory(entity2);
        //launch(args);
        //MainLaunch();
    }
    public static void MainLaunch(){
        MainMenu.update();
        Items.initialize();
        if(MainController.size < 2){
            System.out.println("Less objects!");
        }        else {

            for(int i = 0;i < MainController.size;++i){
                world.addToWorld(rndEntity());
            }
            MainMenu.menu();
        }
    }
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BitaJlik");
        stage.show();
    }
    public static Entity rndEntity(){
        Entity entity;
        Random rnd = new Random();
        switch ((int) (Math.random() * 3)){
            case 0 -> entity = new Steve("Стів",20,2);
            case 1 -> entity = new StevePro("Стів",40,8,20,30);
            case 2 -> entity = new SteveMiddle("Стів",30,4,10);
            default -> entity = new Steve();
        }
        return entity;
    }
}