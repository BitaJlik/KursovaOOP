package oop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import oop.Entities.Entity;
import oop.Entities.Steve;
import oop.Items.Food;
import oop.Items.Items;
import oop.Structures.Home;

import java.io.IOException;

public class InventoryCntrll {
    static Entity entity = new Steve();
    @FXML
    private Button get;
    private Object item;
    private double xOffset = 0;
    private double yOffset = 0;
   // public static Stage thisstage;
    private final ObservableList<Items> items = FXCollections.observableArrayList();
    @FXML
    private TableView<Items> tableItems;
    @FXML
    private TableColumn<Items,String> tableType;
    @FXML
    private TableColumn<Items,String> tableName;
    @FXML
    private TableColumn<Items,Integer> tablePrice;
    @FXML
    private TableColumn<Items,Integer> tableAmount;
    @FXML
    private Button back;
    @FXML
    private Button sell;
    @FXML
    private Button eat;
    @FXML
    private void initialize(){
        sell.setOnMouseClicked(event ->
        {
            if (event.getButton() == MouseButton.PRIMARY)
            {
                sell();
            } else if (event.getButton() == MouseButton.SECONDARY)
            {
                sellAll();
            }
        });
        initData();
        tableType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        for(int i = 0;i < items.size();i++){
            if(items.get(i).getAmount() <= 0) items.remove(i);
        }
        tableItems.setItems(items);
        }

    public void back() {
        Stage thisstage = (Stage) back.getScene().getWindow();
        listEntityCntrll.thisstage.show();
        thisstage.hide();
        System.out.println("[Inventory] backwindow");
    }

    public void sell() {
        item = tableItems.getSelectionModel().getSelectedItem();
        Main.world.home.sell(entity, (Items) item);
        if(!(item == null))
            if(((Items) item).getAmount() == 0) tableItems.getItems().remove(item);
        tableItems.getColumns().get(0).setVisible(false);
        tableItems.getColumns().get(0).setVisible(true);
    }
    public void sellAll() {
        Main.world.home.sellAll(entity);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText("Продано!");
        alert.setContentText("Продано все на суму " + Home.staticsum + "$");
        alert.show();
        //
        for(int i = 0;i < tableItems.getItems().size();++i){
            if(tableItems.getItems().get(i).getAmount() <= 0){
                tableItems.getItems().remove(i);
            }
        }
        items.clear();
        tableItems.refresh();
        tableItems.getColumns().get(0).setVisible(false);
        tableItems.getColumns().get(0).setVisible(true);
    }
    public void eat() {
        item = tableItems.getSelectionModel().getSelectedItem();
        if(item instanceof Food) {
            entity.eat((Items) item);
            if(!(item == null))
                if(((Items) item).getAmount() == 0) tableItems.getItems().remove(item);
            tableItems.getColumns().get(0).setVisible(false);
            tableItems.getColumns().get(0).setVisible(true);
            tableItems.refresh();
            System.out.println("[Inventory] Eat " + ((Items) item).getName());
        }
    }
    private void initData(){ items.addAll(entity.getInv()); }

    public void getResourses() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = null;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Actions.fxml"));
            scene = new Scene(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        assert root != null;
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        Stage thisstage = (Stage) get.getScene().getWindow();
        thisstage.hide();
        stage.setTitle("BitaJlik");
        stage.setScene(scene);
        Actions.entity = EditEntity.entity1;
        stage.show();
        System.out.println("[Inventory]opened other window");
    }
}
