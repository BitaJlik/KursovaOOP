package Entities.fxml;

import Entities.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Main;
import sample.MainController;
import sample.Menu;

public class EntController {
    public static Stage thisstage;
    public CheckBox active;
    public ToggleGroup group1;
    @FXML private Label label;
    @FXML private Label inputName;
    @FXML private Label inputDamage;
    @FXML private Label inputHp;
    @FXML private TextField inName;
    @FXML private TextField inHp;
    @FXML private TextField inDmg;
    @FXML private TextField inputX;
    @FXML private TextField inputY;
    @FXML private Button confirm;
    @FXML private RadioButton worldRadio;
    @FXML private RadioButton caveRadio;
    @FXML private RadioButton homeRadio;
    public static void focusState(boolean value) {
        if (!value) {
            thisstage.close();
            Main.primaryStage.show();
        }
    }
    @FXML private void back(){
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.hide();
        System.out.println("[Entity] Exit");
    }
    @FXML
    private void confirm(){
        Menu.update();
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.close();
        System.out.println("[Entity] closed");
    }
    public String getName(){
        String text = inName.getText();
        if(text.equals("")){
            text = "Entity";
        }
        inName.setText("");
        return text;
    }
    public int getHp(){
        int hp = 1;
        try {
            hp = Integer.parseInt(inHp.getText());
        }catch (NumberFormatException e){
            System.out.println();
        }
        inHp.setText("");
        return hp;
    }
    public int getDmg(){
        int dmg = 1;
        try {
            dmg = Integer.parseInt(inDmg.getText());
        }catch (NumberFormatException e){
            System.out.println();
        }
        inDmg.setText("");
        return dmg;
    }
    public int getX(){
        int x = 0;
        try {
            x = Integer.parseInt(inputX.getText());
        }catch (NumberFormatException e){
            System.out.println("X");
        }
        inputX.setText("");
        return x;
    }
    public int getY(){
        int y = 0;
        try {
            y = Integer.parseInt(inputY.getText());
        }catch (NumberFormatException e){
            System.out.println("Y");
        }
        inputY.setText("");
        return y;
    }

    private void check(){
        checkInputs();
        checkRadio();
    }

    public void createNovice() {
        check();
            if (worldRadio.isSelected()) {
                Entity entity;
                entity = new Steve(getName(), getHp(), getDmg(),"world",getX(),getY());
                Main.world.getWorld().add(entity);
                System.out.println("Created Steve in world");
            } else if (caveRadio.isSelected()) {
                Main.world.cave.getCave().add(new Steve(getName(), getHp(), getDmg(),"cave",getX(),getY()));
                System.out.println("Created Steve in cave");
            } else if (homeRadio.isSelected()) {
                Main.world.home.addToHome(new Steve(getName(), getHp(), getDmg(),"home",getX(),getY()));
                System.out.println("Created Steve in home");
            }
            MainController.update();
    }
    public void createMid() {
        check();
            if (worldRadio.isSelected()) {
                Main.world.getWorld().add(new SteveMiddle(getName(), getHp(), getDmg(), 1 + Math.random() * 25,"world",getX(),getY()));
                System.out.println("Created SteveMiddle in world");
            } else if (caveRadio.isSelected()) {
                Main.world.cave.getCave().add(new SteveMiddle(getName(), getHp(), getDmg(), 1 + Math.random() * 25,"cave",getX(),getY()));
                System.out.println("Created SteveMiddle in cave");
            } else if (homeRadio.isSelected()) {
                Main.world.home.addToHome(new SteveMiddle(getName(), getHp(), getDmg(), 1 + Math.random() * 25,"home",getX(),getY()));
                System.out.println("Created SteveMiddle in home");
            }
        MainController.update();
    }
    public void createPro() {
        check();
            if (worldRadio.isSelected()) {
                Main.world.getWorld().add(new StevePro(getName(), getHp(), getDmg(),
                        1 + Math.random() * 25, (int) (1 + Math.random() * 20),"world",getX(),getY()));
                System.out.println("Created StevePro in world");
            } else if (caveRadio.isSelected()) {
                Main.world.cave.getCave().add(new StevePro(getName(), getHp(), getDmg(),
                        1 + Math.random() * 25, (int) (1 + Math.random() * 20),"cave",getX(),getY()));
                System.out.println("Created StevePro in cave");
            } else if (homeRadio.isSelected()) {
                Main.world.home.addToHome(new StevePro(getName(), getHp(), getDmg(),
                        1 + Math.random() * 25, (int) (1 + Math.random() * 20),"home",getX(),getY()));
                System.out.println("Created StevePro in home");
            }
        MainController.update();
    }
    public void createSpider() {
        check();
            if (worldRadio.isSelected()) {
                Main.world.getWorld().add(new Spider("Павук",10,2.2,"world",getX(),getY()));
                System.out.println("Created Spider in world ");
            } else if (caveRadio.isSelected()) {
                Main.world.cave.getCave().add(new Spider("Павук",10,2.2,"cave",getX(),getY()));
                System.out.println("Created Spider in cave ");
            } else if (homeRadio.isSelected()) {
                Main.world.home.addToHome(new Spider("Павук",10,2.2,"home",getX(),getY()));
                System.out.println("Created Spider in home ");
            }
        MainController.update();
    }
    private void checkRadio(){
        if(!(worldRadio.isSelected()|| caveRadio.isSelected()|| homeRadio.isSelected())){
            label.setTextFill(Color.web("red"));
        }
        else {
            label.setTextFill(Color.web("white"));
        }
    }
    private void checkInputs(){
        if(inName.getText().equals("")) inputName.setTextFill(Color.web("red"));

        if(inHp.getText().equals("")) inputHp.setTextFill(Color.web("red"));

        if(inDmg.getText().equals("")) inputDamage.setTextFill(Color.web("red"));

        if(!(inName.getText().equals("")||inHp.getText().equals("")||inDmg.getText().equals(""))){
            removeRed();
        }
    }
    private void removeRed(){
        inputName.setTextFill(Color.web("white"));
        inputHp.setTextFill(Color.web("white"));
        inputDamage.setTextFill(Color.web("white"));
    }
}
