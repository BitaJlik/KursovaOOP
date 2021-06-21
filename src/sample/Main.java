package sample;
// Питання: 2 5 8 10 12

import Entities.*;
import Entities.fxml.EntController;
import Structures.World;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.Random;


public class Main extends Application {
    public static Stage primaryStage;
    public static Scene scene;
    public static Pane pane = new Pane();
    public static Random rnd = new Random();
    protected static ImageView mapBorder;
    protected static boolean mapVision = false;
    protected static boolean info;
    private static double xOffset = 0;
    private static double yOffset = 0;
    public static Group entities = new Group();
    public static Group miniEntities = new Group();
    public static Group minimap;
    public static Group map ;
    public static Group allGroup;
    public static Group sceneGroup;
    public static Group activeRect = new Group();
    public static int yActive;
    public static ScrollPane scrollPane;
    static boolean[] keysPressed = new boolean[6];
    static Entity entity1 = null;
    public static World  world = new World(25,25,475,475,5000);

    @Override
    public void start(Stage primaryStage) {

        javafx.scene.control.Menu mn = new javafx.scene.control.Menu("Menu");
        MenuItem mi1 = new MenuItem("Open");
        mi1.setOnAction(actionEvent -> OpenFile());
        MenuItem mi2 = new MenuItem("Save");
        mi2.setOnAction(actionEvent -> SaveFile());
        MenuItem mi3 = new MenuItem("About");
        mi3.setOnAction(actionEvent -> About());
        MenuItem mi4 = new MenuItem("Exit");
        mi4.setOnAction(actionEvent -> Platform.exit());
        mn.getItems().addAll(mi1,mi2,mi3,mi4);

        MenuBar mb = new MenuBar();

        mb.getMenus().add(mn);
        mb.setStyle("-fx-background-color: #DFB951;" +
                    "-fx-border-radius: 20;" +
                    "-fx-background-radius: 20;");

        map = new Group(world.groupWorld,entities);
        minimap = new Group();
        allGroup = new Group(map);
        pane = new Pane(allGroup);
        pane.setMinWidth(3800);
        pane.setMinHeight(2000);
        scrollPane = new ScrollPane(pane);
        scrollPane.setMaxSize(1920,1017);
        Entity entity = new Steve("Макс",20,20,20,20);
        world.getWorld().add(entity);
        sceneGroup = new Group(scrollPane,mb);
        mb.relocate(1843,978);
        scene = new Scene(sceneGroup);
        entities.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) Main.world.mouse(event.getX() , event.getY());
            else if (event.getButton() == MouseButton.SECONDARY) {
                entity1 = EditEntity.entity1 = world.edit(event.getX(),event.getY());
                if(entity1 != null){
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    Scene scene1 = null;
                    Parent root1 = null;
                    try {
                        root1 = FXMLLoader.load(getClass().getResource("EditEntity.fxml"));
                        scene1 = new Scene(root1);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    assert root1 != null;
                    root1.setOnMousePressed(event1 -> {
                        xOffset = event1.getSceneX();
                        yOffset = event1.getSceneY();
                    });
                    root1.setOnMouseDragged(event12 -> {
                        stage.setX(event12.getScreenX() - xOffset);
                        stage.setY(event12.getScreenY() - yOffset);
                    });
                    stage.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> EditEntity.focusState(newValue));
                    EditEntity.thisstage = stage;
                    stage.setTitle("BitaJlik");
                    stage.setScene(scene1);
                    stage.show();
                }
            }
        });
        mapBorder = new ImageView(new Image("images/MapBorder.png"));
        minimap.getChildren().addAll(world.getMiniMap(),mapBorder,miniEntities);
        minimap.setLayoutY(700);

        scene.setOnKeyPressed(new KeyPressedHandler());
        scene.setOnKeyReleased(keyEvent -> {
             switch (keyEvent.getCode()) {
                 case W -> keysPressed[0] = false;
                 case S -> keysPressed[1] = false;
                 case A -> keysPressed[2] = false;
                 case D -> keysPressed[3] = false;
                 case SHIFT -> keysPressed[4] = false;
             }
        });
        primaryStage.setScene(scene);

        Main.primaryStage = primaryStage;
        primaryStage.show();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Entity ent1 : world.getWorld()){
                    for(Entity ent2 : world.getWorld()){
                        if (ent1 != ent2 && ent1.getImg().getBoundsInParent().intersects(ent2.getImg().getBoundsInParent())){
                            Menu.Batlle(ent1,ent2);
                        }
                    }
                }
                for (Entity ent1 : world.cave.getCave()){
                    if (world.cbg.getBoundsInParent().intersects(ent1.getImg().getBoundsInParent())){
                        world.cave.dig(ent1);
                    }
                }
                for (Entity ent1 : world.getAllWorld()){
                    if (world.hbg.getBoundsInParent().intersects(ent1.getImg().getBoundsInParent()))
                        world.home.healing(ent1);}
                // Up
                if (keysPressed[0]) world.getAllWorld().forEach(entity -> entity.moveUp(keysPressed[4]));
                // Down
                if (keysPressed[1]) world.getAllWorld().forEach(entity -> entity.moveDown(keysPressed[4]));
                // Left
                if (keysPressed[2]) world.getAllWorld().forEach(entity -> entity.moveLeft(keysPressed[4]));
                // Right
                if (keysPressed[3]) world.getAllWorld().forEach(entity -> entity.moveRight(keysPressed[4]));
                world.getAllWorld().forEach(Entity::lifeCycle);
                world.updateWorld();
            }
        }.start();
    }

    public static void main(String[] args) { launch(args); }

private static class KeyPressedHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent keyEvent) {
            // Movements
            switch (keyEvent.getCode())  {
                case W -> keysPressed[0] = true;
                case S -> keysPressed[1] = true;
                case A -> keysPressed[2] = true;
                case D -> keysPressed[3] = true;
                case SHIFT -> keysPressed[4] = true;
            }
            // All keyboard
            switch (keyEvent.getCode())  {
                // Info Alert
                case F1 -> About();
                // Switch ON\OFF Info about active Entities
                case F2 -> {
                    info = !info;
                    if (info) {
                        sceneGroup.getChildren().add(activeRect);
                    } else {
                        sceneGroup.getChildren().remove(activeRect);
                    }
                }
                // Arrays and Comparable
                case F3 -> Menu.ArraysExampleAndComparable();
                // Information about all Entities
                case TAB -> {
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    Scene scene1 = null;
                    Parent root1 = null;
                    try {
                        root1 = FXMLLoader.load(getClass().getResource("listEntity.fxml"));
                        scene1 = new Scene(root1);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    assert root1 != null;
                    root1.setOnMousePressed(event -> {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    });
                    root1.setOnMouseDragged(event -> {
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                    });
                    stage.setTitle("BitaJlik");
                    stage.setScene(scene1);
                    stage.show();
                    stage.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> listEntityCntrll.focusState(newValue));
                    listEntityCntrll.thisstage = stage;
                }
                // Adding new Entity
                case INSERT -> {
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    Scene scene1 = null;
                    Parent root1 = null;
                    try {
                        root1 = FXMLLoader.load(getClass().getResource("entity.fxml"));
                        scene1 = new Scene(root1);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    assert root1 != null;
                    root1.setOnMousePressed(event -> {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    });
                    root1.setOnMouseDragged(event -> {
                        stage.setX(event.getScreenX() - xOffset);
                        stage.setY(event.getScreenY() - yOffset);
                    });
                    stage.setTitle("BitaJlik");
                    stage.setScene(scene1);
                    stage.show();
                    stage.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> EntController.focusState(newValue));
                    EntController.thisstage = stage;
                }
                // Logic...Delete active entities
                case DELETE -> {
                    for (Entity entity : Main.world.getAllWorld()) {
                        if (entity.isActive()) {
                            allGroup.getChildren().remove(entity.getGroup());
                            entity.Death();
                        }
                    }

                }
                // Deactivate active entities
                case ESCAPE -> {
                    for (Entity entity : world.getAllWorld()) {
                        if (entity.isActive()) {
                            entity.mouseActivate(entity.getX() + 1, entity.getY() + 1);
                        }
                    }

                }
                // Switch DAY\NIGHT
                case K -> {
                    if (world.isDay()) world.Night();
                    else world.Day();
                }
                // Switch ON\OFF seeking food
                case G -> {
                    for (Entity ent : world.getAllWorld()) {
                        ent.switchSeekFood();
                    }
                }
                // Turn ON\OFF going to Home if instanceof Steve
                case H -> {
                    for (Entity ent : world.getAllWorld()) {
                        ent.switchHome();
                    }
                }
                // Turn ON\OFF All going to cave
                case J -> {
                    for (Entity ent : world.getAllWorld()) {
                        ent.switchCave();
                    }
                }
                // Turn ON\OFF LifeCycle
                case L -> {
                    for (Entity ent : world.getAllWorld()) {
                        ent.switchLife();
                    }
                }
                // Turn ON\OFF MiniMap
                case M -> {
                    mapVision = !mapVision;
                    map();
                    System.out.println("Minimap: " + (mapVision ? "ON" : "OFF"));
                    map();
                }
                // Kamikaze for all spiders!
                case F -> {
                    for (Entity entity : world.getAllWorld()) {
                        if (entity.getClass() == Spider.class) {
                            entity.switchHome();
                        }
                    }
                }
                // Switch ON\OFF character Entities
                case P -> {
                    world.passive = !world.passive;
                    System.out.println("Passive" + (world.passive ? " ON" : " OFF"));
                }
            }
            if (keyEvent.isControlDown()){
                switch (keyEvent.getCode()) {
                    // Activate all Entities
                    case A -> {
                        for (int i = 0; i < keysPressed.length; i++)
                            if (keysPressed[i]) keysPressed[i] = false;
                        for (Entity entity : world.getWorld()) {
                            if (!entity.isActive())
                                entity.mouseActivate(entity.getX(), entity.getY());
                        }
                        for (Entity entity : world.home.getHome()) {
                            if (!entity.isActive())
                                entity.mouseActivate(entity.getX(), entity.getY());
                        }
                    }
                    // Deserialization
                    case O -> OpenFile();
                    // Serialization
                    case S -> SaveFile();
                    // Clone all active Entities
                    case C -> world.cloneEntities();
                }
            }
            // Fast create Entities
            switch (keyEvent.getCode())  {
                    case NUMPAD1 -> world.getWorld().add(new Steve());
                    case NUMPAD2 -> world.getWorld().add(new SteveMiddle());
                    case NUMPAD3 -> world.getWorld().add(new StevePro());
                    case NUMPAD4 -> world.getWorld().add(new Spider());
                }

            }
        }
        private static void map(){
            if (mapVision) {
                sceneGroup.getChildren().remove(minimap);
                sceneGroup.getChildren().add(minimap);
            }
            else {
                sceneGroup.getChildren().remove(minimap);
            }
        }
        private static void About(){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Lab_6 BitaJlik");
            alert.setContentText("""
                            W,S,A,D - Переміщення активних об'єктів             
                            Стрілки для Керування екраном\s
                            ЛКМ по мінікарті для переміщення видимої області 
                            K - День\\Ніч
                            P - Зміна Характеру Мікрооб'єктів
                            H - Додому
                            J - Шахта
                            M - Мінікарта\s
                            L - Рандомне переміщення
                            F2 - Інформація про активні об'єкти
                            F3 - Arrays & Comparable
                            Tab - Передивитись всі об'єкти
                            Insert - Добавити об'єкт
                            Escape - Всі активні стають неактивними
                            Delete - Видалити всі активні об'єкти
                            Num 1,2,3,4 Швидкі Створення
                            Ctrl + A - Виділити всіх
                            Ctrl + C - Скопіювати всіх активних""");
            alert.showAndWait();
        }
        private static void OpenFile(){
            Entity.clearIdf();
            for (int i = 0; i < keysPressed.length; i++)
                if (keysPressed[i]) keysPressed[i] = false;
            String currentDir = System.getProperty("user.dir");
            File initDirectory = new File(currentDir);
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.dat)", "*.dat");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(initDirectory);
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    world.home.setHp(Integer.parseInt(bufferedReader.readLine()));
                    String text;
                    text = bufferedReader.readLine();
                    int hmany = Integer.parseInt(text);

                    for (Entity e : world.getWorld()) {
                        entities.getChildren().remove(e.getGroup());
                        miniEntities.getChildren().remove(e.getMiniImage());
                    }
                    world.getWorld().clear();

                    int type;
                    for (int i = 0; i < hmany; i++) {
                        text = bufferedReader.readLine();
                        type = Integer.parseInt(text);

                        if (type == 1) {
                            Entity entity = new Steve();
                            world.getWorld().add(entity);
                            entity.open(bufferedReader);
                        } else if (type == 2) {
                            Entity entity = new SteveMiddle();
                            world.getWorld().add(entity);
                            entity.open(bufferedReader);
                        } else if (type == 3) {
                            Entity entity = new StevePro();
                            world.getWorld().add(entity);
                            entity.open(bufferedReader);
                        } else if (type == 4) {
                            Entity entity = new Spider();
                            world.getWorld().add(entity);
                            entity.open(bufferedReader);
                        }
                    }
                } catch (IOException ex) {
                    System.out.println("File error");
                } finally {
                    try {
                        assert bufferedReader != null;
                        bufferedReader.close();
                    } catch (IOException ex) {
                        System.out.println("Cant close file");
                    }
                }
            }
        }
        private static void SaveFile(){
            for (int i = 0; i < keysPressed.length; i++)
                if (keysPressed[i]) keysPressed[i] = false;
            String currentDir = System.getProperty("user.dir");
            File initDirectory = new File(currentDir);
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TXT files (*.dat)", "*.dat");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(initDirectory);
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                try {
                    FileWriter fileWriter;
                    fileWriter = new FileWriter(file);
                    fileWriter.write(String.valueOf(world.home.getHp()));
                    fileWriter.write("\n");
                    fileWriter.write(Integer.toString(world.getWorld().size()));
                    fileWriter.write("\n");
                    for (Entity mr : world.getWorld()) {
                        mr.save(fileWriter);
                    }
                    fileWriter.close();
                } catch (IOException ex) {
                    System.out.println();
                }
            }
        }
    }