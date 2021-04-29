package oop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.Entities.SteveMiddle;
import oop.Structures.World;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    static World world = new World();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        world.home.addToHome(new SteveMiddle("Саня",40,6,20));
        MainMenu.menu();
    }

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("BitaJlik");
        stage.show();
    }
}