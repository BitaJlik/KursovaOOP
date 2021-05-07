package oop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oop.Entities.Entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class listEntityCntrll {
    private final ObservableList<Entity> entities = FXCollections.observableArrayList();
    public Button close;
    public Button back;
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
    private TableColumn<Entity,String> tablePosition;
    @FXML
    private void initialize() {
        initData();
        tableentity.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableHp.setCellValueFactory(new PropertyValueFactory<>("Hp"));
        tableDmg.setCellValueFactory(new PropertyValueFactory<>("Damage"));
        tablePosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        tableEntity.setItems(entities);
    }
    public void update() {
        System.out.println("[List] Updating" + getTime());
        Progress.Waiting(2000);
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
        thisstage.hide();
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
}
