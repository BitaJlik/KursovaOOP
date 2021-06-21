package oop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oop.Entities.Entity;
import oop.Entities.Steve;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class listEntityCntrll {
    public  Object entity = null;
    public static Stage thisstage;
    private double xOffset = 0;
    private double yOffset = 0;
    private final ObservableList<Entity> entities = FXCollections.observableArrayList();
    public Button close;
    public Button back;
    @FXML
    private Button edit;
    @FXML
    private TableView<Entity> tableEntity;
    @FXML
    private TableColumn<Entity,String> tableentity;
    @FXML
    private TableColumn<Entity,String> tableName;
    @FXML
    private TableColumn<Entity,String> tableId;
    @FXML
    private TableColumn<Entity,Integer> tableHp;
    @FXML
    private TableColumn<Entity,Integer> tableDmg;
    @FXML
    public TableColumn<Entity,Integer> tableMoney;
    @FXML
    private TableColumn<Entity,String> tablePosition;
    @FXML
    private Button inventory;
    @FXML
    private void initialize() {
        initData();
        tableentity.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableHp.setCellValueFactory(new PropertyValueFactory<>("Hp"));
        tableDmg.setCellValueFactory(new PropertyValueFactory<>("Damage"));
        tablePosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        tableMoney.setCellValueFactory(new PropertyValueFactory<>("Money"));
        tableEntity.setItems(entities);
        // Зробити логіку вибора об'єкта,щоб кнопка інв була сірою якщо Ід == -1
        tableEntity.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                inventory();
            }
        });
        tableEntity.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                                Boolean arg1, Boolean arg2) {
                entity = tableEntity.getSelectionModel().getSelectedItem();
                if(entity != null){
                    if(((Entity) entity).getId() == -1){
                       inventory.setDisable(true);
                        tableEntity.setOnMousePressed(event -> {
                            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                            }
                        });
                    }
                    else {
                        inventory.setDisable(false);
                        tableEntity.setOnMousePressed(event -> {
                            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                                inventory();
                            }
                        });
                    }
                }
            }
        });

    }
    public void update() {
        System.out.println("[List] Updating" + getTime());
        tableEntity.getColumns().get(0).setVisible(false);
        tableEntity.getColumns().get(0).setVisible(true);
        System.out.println("[List] Updated" + getTime());
    }
    public void close() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        System.out.println("[List] closed" +getTime());
    }
    public void back() {
        Stage thisstage = (Stage) back.getScene().getWindow();
        MainController.thisstage.show();
        thisstage.close();
        System.out.println("[List] backwindow");
    }
    private void initData(){
        MainMenu.update();
        entities.addAll(Main.world.getWorld());
        entities.addAll(Main.world.home.getHome());
        entities.addAll(Main.world.cave.getCave());

    }
    public String getTime(){
        return " "+new SimpleDateFormat("dd.MM_HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public void edit() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = null;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("EditEntity.fxml"));
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
        thisstage = (Stage) edit.getScene().getWindow();
        thisstage.hide();
        stage.setTitle("BitaJlik");
        stage.setScene(scene);
        EditEntity.entity1 = tableEntity.getSelectionModel().getSelectedItem();
        stage.show();
        System.out.println("[List]opened other window");

    }

    public void inventory() {
        InventoryCntrll.entity = tableEntity.getSelectionModel().getSelectedItem();
        if(InventoryCntrll.entity instanceof Steve) {
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
            thisstage = (Stage) edit.getScene().getWindow();
            thisstage.hide();
            stage.setTitle("BitaJlik");
            stage.setScene(scene);
            stage.show();
            System.out.println("[List]opened other window");
        }
    }
}
