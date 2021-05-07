package oop;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController {
    public static Stage thisstage;
    private double xOffset = 0;
    private double yOffset = 0;

    public static int size = 0;
    @FXML
    private Button minus;
    @FXML
    private Button plus;
    @FXML
    private TextField inpt;
    @FXML
    private Button confirm;
    @FXML
    private void click() {
        if(plus.isHover()){
            if(!inpt.getText().equals("")){
                try{
                   size = Integer.parseInt(inpt.getText());
                }
                catch (NumberFormatException e){
                    System.out.println("trying String to int!");
                }
            }
            size++;
            inpt.setText(String.valueOf(size));
        }
        else if(minus.isHover()){
            if(!inpt.getText().equals("")){
                try{
                    size = Integer.parseInt(inpt.getText());
                }
                catch (NumberFormatException e){
                    System.out.println("trying String to int!");
                }
            }
            if (size >= 1) size--;
             else size = 0;
            inpt.setText(String.valueOf(size));
        }
    }
    @FXML
    public void confirm( )  {
        if (!inpt.getText().equals(""))
            System.out.println("Text: " + inpt.getText());
        try{
            size = Integer.parseInt(inpt.getText());
        }
        catch (NumberFormatException e){
            System.out.println("Trying string to int!");
        }
        for(int i = 0;i < size;++i){
            Main.world.addToWorld(Main.rndEntity());
        }
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.hide();
    }
    public void entityCreate() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = null;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Entities/fxml/entity.fxml"));
            scene = new Scene(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        assert root != null;
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
        thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.hide();
        stage.setTitle("BitaJlik");
        stage.setScene(scene);

        stage.show();
        System.out.println("[Start] opened other window");
    }
    public void listEntity() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = null;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("listEntity.fxml"));
            scene = new Scene(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        assert root != null;
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
        thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.hide();
        stage.setTitle("BitaJlik");
        stage.setScene(scene);
        stage.show();
        System.out.println("[Start]opened other window");
    }
    public void close() {
        thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.close();
    }
}
