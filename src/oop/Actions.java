package oop;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import oop.Entities.Entity;
import oop.Entities.Spider;
import oop.Entities.Steve;
import oop.Items.Ores;

import java.io.IOException;
import java.util.Optional;

public class Actions {
    private double xOffset = 0;
    private double yOffset = 0;
    static Entity entity = null;
    public  ProgressBar progressBar;
    @FXML
    private MenuItem inCave;
    @FXML
    private MenuItem inIsland;
    @FXML
    private SplitMenuButton wheregoing;
    @FXML
    private Button dig;
    @FXML
    private Button seekFood;
    @FXML
    private Label labelEntity;
    @FXML
    private Label item;
    @FXML
    private Button back;

    @FXML
    private void initialize(){
        entity = InventoryCntrll.entity;
        if(entity != null)
        labelEntity.setText(entity.getName());
        progressBar.setStyle(String.valueOf(ProgressBar.BASELINE_OFFSET_SAME_AS_HEIGHT));
    }
    @FXML
    private void seekFood() {
        entity = InventoryCntrll.entity;
        if(entity.getId() == -1){
            dig.setDisable(true);
            seekFood.setDisable(true);
            item.setLayoutY(143);
            item.setOpacity(1);
            item.setText(entity.getName() + " Мертвий");
        }else {
            if (entity != null) {
                dig(entity);
            }
        }
    }
    @FXML
    private void dig() {
        entity = InventoryCntrll.entity;
        if(entity.getId() == -1){
            dig.setDisable(true);
            seekFood.setDisable(true);
            item.setLayoutY(143);
            item.setOpacity(1);
            item.setText(entity.getName() + " Мертвий");
        }else {
            if (entity.getHp() <= 5) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Мало життя!!!");
                alert.setContentText("Ви бажаєте піти в шахту з низьким здоров'ям?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == null) {
                    System.out.println("No selection!");
                } else if (option.get() == ButtonType.OK) {
                    if (entity != null) {
                        digcave(entity);
                    }
                } else if (option.get() == ButtonType.CANCEL) {
                    System.out.println("Cancelled!");
                } else {
                    System.out.println("-");
                }
            } else {
                if (entity != null) {
                    digcave(entity);
                }
            }
        }
    }
    @FXML
    public void back() {
        Stage thisstage = (Stage) back.getScene().getWindow();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = null;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
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
        thisstage.hide();
        stage.setTitle("BitaJlik");
        stage.setScene(scene);
        stage.show();
        System.out.println("[Actions]opened other window");
    }
    public void dig(Entity obj) {
        int id = (int) (Math.random() * oop.Items.Food.foods.size());
        item.setText(oop.Items.Food.foods.get(id).getName()+"!");
        Animation transition = new Transition() {
            {
                setCycleDuration(Duration.millis(1300));
                setInterpolator(Interpolator.LINEAR);
            }
            @Override
            protected void interpolate(double frac) {
                // frac is the percentage of how much time of the Transition
                // Already passed (1000 ms => frac = 0.5)
                // Here you just Multiply frac with the designated value
                item.setOpacity(1-frac*2);
                item.setLayoutY(item.getLayoutY()+40*(frac/10)*-1);
            }
        };
        transition.play();
        item.setLayoutY(143);
        item.setOpacity(1);
        progressBar.setVisible(true);
        int check = 0;
        if (obj.getPosition().equals("world")) {
            check = 1;
        }
        if(check == 1){
            if (obj instanceof Steve) {
                Food food = new Food(id);
                Thread thread = new Thread(food);
                thread.setDaemon(true);
                thread.start();
                progressBar.progressProperty().bind(food.progressProperty());
            }
            back.setDisable(true);
            dig.setDisable(true);
            seekFood.setDisable(true);
        }
        else System.out.println("\u001B[31mОб'єкт не в світі!\u001B[m");
    }
    public void digcave(Entity obj){
            int id = (int) (Math.random() * Ores.ores.size());
            item.setText(oop.Items.Ores.ores.get(id).getName() + "!");
            Animation transition = new Transition() {
                {
                    setCycleDuration(Duration.millis(1300));
                    setInterpolator(Interpolator.LINEAR);
                }

                @Override
                protected void interpolate(double frac) {
                    // frac is the percentage of how much time of the Transition
                    // Already passed (1000 ms => frac = 0.5)
                    // Here you just Multiply frac with the designated value
                    item.setOpacity(1 - frac * 2);
                    item.setLayoutY(item.getLayoutY() + 40 * (frac / 10) * -1);
                }
            };
            transition.play();
            item.setLayoutY(143);
            item.setOpacity(1);
            int check = 0;
            if (obj.getPosition().equals("world")) {
                check = 1;
            }
            if (check == 1) {
                if (obj instanceof Steve) {

                    back.setDisable(true);
                    dig.setDisable(true);
                    seekFood.setDisable(true);
                    Ore ore = new Ore(id);
                    Thread thread = new Thread(ore);
                    thread.setDaemon(true);
                    thread.start();
                    progressBar.progressProperty().bind(ore.progressProperty());
                }
            } else System.out.println("\u001B[31mПотрібно піти в шахту!\u001B[0m");
    }
    protected class Food extends Task<Void> {
        private int id;
        int time = oop.Items.Food.foods.get(id).getDigseconds() * 5;
        public Food(int id){
            this.id = id;
        }
        @Override
        public Void call() {
            method_1();
            entity.addInv(oop.Items.Food.foods.get(id));
            return null;
        }
        private void method_1() {
            updateMessage("Идет работа");
            for (int i = 0; i < time; i++) {
                if (isCancelled()) {
                    updateMessage("Работа прервана!");
                    return;
                }
                updateProgress(i + 1,time);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Работа прервана!");
                        return;
                    }
                }
            }
            updateMessage("Работа завершена");
            Platform.runLater(()->{ item.setText("");});
            seekFood.setDisable(false);
            dig.setDisable(false);
            back.setDisable(false);
        }
        protected void updateMessage(String message) {
            System.out.println(message);
            super.updateMessage(message);
        }
    }
    protected class Ore extends Task<Void> {
        int id ;
        int time = oop.Items.Ores.ores.get(id).getDigseconds() * 5;
        public Ore(int id){
            this.id = id;
        }
        @Override
        public Void call() {
            method_1();
            entity.addInv(oop.Items.Ores.ores.get(id));
            return null;
        }
        private void method_1() {
            updateMessage("Идет работа");
            int chance = (int) (Math.random() * 100);
            int time2 = -1;
            if (chance > 80) {
                time2 = (int) (time - Math.random() * time);
            }
            for (int i = 0; i < time; i++) {
                if (isCancelled()) {
                    updateMessage("Работа прервана!");
                    return;
                }
                if(time2 == i){
                    MainMenu.deathBatlle(entity,new Spider());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                updateProgress(i + 1,time);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Работа прервана!");
                        return;
                    }
                }
            }
            updateMessage("Работа завершена");
            Platform.runLater(()->{ item.setText("");});
            seekFood.setDisable(false);
            dig.setDisable(false);
            back.setDisable(false);
        }
        protected void updateMessage(String message) {
            System.out.println(message);
            super.updateMessage(message);
        }
    }
}
