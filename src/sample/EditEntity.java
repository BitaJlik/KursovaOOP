package sample;

import Entities.Entity;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EditEntity {
    public static Stage thisstage;
    static Entity entity1;
    public ToggleGroup radios;
    @FXML private TextField input;
    @FXML private RadioButton radioHp;
    @FXML private RadioButton radioDamage;
    @FXML private RadioButton radioName;
    @FXML private Label whatChange;
    @FXML private Label labelChanged;
    @FXML private Label labelWhat;
    @FXML private Button exit;
    @FXML private Button world;
    @FXML private Button cave;
    @FXML private Button house;
    @FXML public static void focusState(boolean value) {
        if (!value) {
            thisstage.close();
            Main.primaryStage.show();
        }
    }
    @FXML private void initialize(){
        labelWhat.setOpacity(1);
    }
    public void confirm() {
        check();
        labelChanged.setTextFill(Color.WHITE);
        if(radioName.isSelected()){
            entity1.setName(input.getText());
            labelWhat.setText("Ім'я");
        }
        else if(radioHp.isSelected()){
            try{
                int hp;
                hp = Integer.parseInt(input.getText());
                entity1.setHp(hp);
            }catch (NumberFormatException e){
                System.out.println("Exception: input has string");
            }
            labelWhat.setText("Життя");
        }
        else if(radioDamage.isSelected()){
            try{
                int dmg;
                dmg = Integer.parseInt(input.getText());
                entity1.setDamage(dmg);
            }catch (NumberFormatException e){
                System.out.println("Exception: input has string");
            }
            labelWhat.setText("Урон");
        }
        labelWhat.setOpacity(1);
        entity1.refresh();
    }
    private void check(){
        if(input.getText().equals("")) System.out.println("input is empty!");
        checkRadio();
    }
    private void checkRadio(){
        if(!(radioDamage.isSelected()|| radioHp.isSelected()|| radioName.isSelected())){
            whatChange.setTextFill(Color.web("red"));
        }
        else {
            whatChange.setTextFill(Color.web("white"));
        }
    }

    public void house() {
        int check = 1;
        for (int i = 0; i < Main.world.home.getHome().size(); i++) {
            if (Main.world.home.getHome().get(i).getId() == entity1.getId()) {
                house.setDisable(true);
                check = 0;
                break;
            }
        }
        if (check == 1) {
            for (int i = 0; i < Main.world.cave.getCave().size(); i++) {
                if (Main.world.cave.getCave().get(i).getId() == entity1.getId()) {
                    entity1.setPosition("home");
                    entity1.setYwl(entity1.getBPosY() + Math.random() * 160);
                    entity1.setXwl(entity1.getBPosX() + Math.random() * 160);
                    Main.world.home.getHome().add(entity1);
                    Main.world.cave.getCave().remove(entity1);
                    entity1.switchHome();
                    break;
                }
            }
            for (int i = 0; i < Main.world.getWorld().size(); i++) {
                if (Main.world.getWorld().get(i).getId() == entity1.getId()) {
                    entity1.setPosition("home");
                    entity1.setYwl(entity1.getBPosY() + Math.random() * 160);
                    entity1.setXwl(entity1.getBPosX() + Math.random() * 160);
                    entity1.switchHome();
                    Main.world.home.getHome().add(entity1);
                    Main.world.getWorld().remove(entity1);
                    break;
                }
            }
            entity1.switchHome();
            world.setDisable(false);
            cave.setDisable(false);
            house.setDisable(true);
            labelChanged.setText("Переміщено");
            Animation transition = new Transition() {
                {
                    setCycleDuration(Duration.millis(1300));
                    setInterpolator(Interpolator.LINEAR);
                }
                @Override
                protected void interpolate(double frac) {
                    labelChanged.setOpacity(1-frac*2);
                }
            };
            transition.play();
            labelChanged.setOpacity(1);
            Wait food =new Wait();
            Thread thread = new Thread(food);
            thread.setDaemon(true);
            thread.start();
        }
    }

    public void cave() {
        int check = 1;
        for (int i = 0; i < Main.world.cave.getCave().size(); i++) {
            if (( Main.world.cave.getCave().get(i)).getId() == entity1.getId()) {
                cave.setDisable(true);
                check = 0;
                break;
            }
        }
        if (check == 1) {
            for (int i = 0; i < Main.world.home.getHome().size(); i++) {
                if (Main.world.home.getHome().get(i).getId() == entity1.getId()) {
                    entity1.setPosition("cave");
                    entity1.setYwl(entity1.getBPosY() + Math.random() * 160);
                    entity1.setXwl(entity1.getBPosX() + Math.random() * 360);
                    Main.world.cave.getCave().add(entity1);
                    Main.world.home.getHome().remove(entity1);
                    entity1.switchCave();
                    break;
                }
            }
            for (int i = 0; i < Main.world.getWorld().size(); i++) {
                if (Main.world.getWorld().get(i).getId() == entity1.getId()) {
                    entity1.setPosition("cave");
                    entity1.setYwl(entity1.getBPosY() + Math.random() * 160);
                    entity1.setXwl(entity1.getBPosX() + Math.random() * 360);
                    Main.world.cave.getCave().add(entity1);
                    Main.world.getWorld().remove(entity1);
                    entity1.switchCave();
                    break;
                }
            }
            world.setDisable(false);
            cave.setDisable(true);
            house.setDisable(false);
            labelChanged.setText("Переміщено");
            Animation transition = new Transition() {
                {
                    setCycleDuration(Duration.millis(1300));
                    setInterpolator(Interpolator.LINEAR);
                }
                @Override
                protected void interpolate(double frac) {
                    labelChanged.setOpacity(1-frac*2);
                }
            };
            transition.play();
            labelChanged.setOpacity(1);
            Wait food =new Wait();
            Thread thread = new Thread(food);
            thread.setDaemon(true);
            thread.start();
        }
    }

    public void world() {
        int check = 1;
        for (int i = 0; i < Main.world.getWorld().size(); i++) {
            if (Main.world.getWorld().get(i).getId() == entity1.getId()) {
                world.setDisable(true);
                check = 0;
                break;
            }
        }
        if (check == 1) {
            for (int i = 0; i < Main.world.home.getHome().size(); i++) {
                if (Main.world.home.getHome().get(i).getId() == entity1.getId()) {
                    entity1.setPosition("world");
                    Main.world.getWorld().add(entity1);
                    Main.world.home.getHome().remove(entity1);
                    entity1.switchLife();
                    break;
                }
            }
            for (int i = 0; i < Main.world.cave.getCave().size(); i++) {
                if (Main.world.cave.getCave().get(i).getId() == entity1.getId()) {
                    entity1.setPosition("world");
                    Main.world.getWorld().add(entity1);
                    Main.world.cave.getCave().remove(entity1);
                    entity1.switchLife();
                    break;
                }
            }
            world.setDisable(true);
            cave.setDisable(false);
            house.setDisable(false);
            labelChanged.setText("Переміщено");
            Animation transition = new Transition() {
                {
                    setCycleDuration(Duration.millis(1300));
                    setInterpolator(Interpolator.LINEAR);
                }
                @Override
                protected void interpolate(double frac) {
                    labelChanged.setOpacity(1-frac*2);
                }
            };
            transition.play();
            labelChanged.setOpacity(1);
            Wait food = new Wait();
            Thread thread = new Thread(food);
            thread.setDaemon(true);
            thread.start();
        }
    }

    public void exit() {
        Stage thisstage = (Stage) exit.getScene().getWindow();
        thisstage.close();
    }

    protected class Wait extends Task<Void> {
        @Override
        public Void call() {
            method_1();
            return null;
        }
        private void method_1() {
            for (int i = 0; i < 10; i++) {
                if (isCancelled()) {
                    updateMessage("Работа прервана!");
                    return;
                }
                updateProgress(i + 1,10);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        return;
                    }
                }
            }
            Platform.runLater(()-> labelChanged.setText(""));
        }
        protected void updateMessage(String message) {
            System.out.println(message);
            super.updateMessage(message);
        }
    }
}
