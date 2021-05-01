package oop.Entities.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import oop.Entities.Spider;
import oop.Entities.Steve;
import oop.Entities.SteveMiddle;
import oop.Entities.StevePro;
import oop.Main;
import oop.MainController;

public class EntController {
    public ToggleGroup group1;
    public Label label;
    @FXML
    private TextField inName;
    @FXML
    private TextField inHp;
    @FXML
    private TextField inDmg;
    @FXML
    private Button confirm;
    @FXML
    private RadioButton worldRadio;
    @FXML
    private RadioButton caveRadio;
    @FXML
    private RadioButton homeRadio;
    @FXML
    private void back(){
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        MainController.thisstage.show();
        thisstage.hide();
    }
    @FXML
    private void confirm(){
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.close();
    }
    public String getName(){
        String text = inName.getText();
        inName.setText("");
        return text;
    }
    public int getHp(){
        int hp = 0;
        try {
            hp = Integer.parseInt(inHp.getText());
        }catch (NumberFormatException e){
            System.out.println("Error");
        }
        inHp.setText("");
        return hp;
    }
    public int getDmg(){
        int dmg = 0;
        try {
            dmg = Integer.parseInt(inDmg.getText());
        }catch (NumberFormatException e){
            System.out.println("Error");
        }
        inDmg.setText("");
        return dmg;
    }
    public void createNovice() {
        if(worldRadio.isSelected()){
            Main.world.addToWorld(new Steve(getName(),getHp(),getDmg()));
            System.out.println("Created Steve in world");
        }
        else if(caveRadio.isSelected()){
            Main.world.cave.addToCave(new Steve(getName(),getHp(),getDmg()));
            System.out.println("Created Steve in cave");
        }
        else if(homeRadio.isSelected()){
            Main.world.home.addToHome(new Steve(getName(),getHp(),getDmg()));
            System.out.println("Created Steve in home");
        }
    }
    public void createMid() {
        if(worldRadio.isSelected()){
            Main.world.addToWorld(new SteveMiddle(getName(),getHp(),getDmg(),1+Math.random() * 25));
            System.out.println("Created SteveMiddle in world");
        }
        else if(caveRadio.isSelected()){
            Main.world.cave.addToCave(new SteveMiddle(getName(),getHp(),getDmg(),1+Math.random() * 25));
            System.out.println("Created SteveMiddle in cave");
        }
        else if(homeRadio.isSelected()){
            Main.world.home.addToHome(new SteveMiddle(getName(),getHp(),getDmg(),1+Math.random() * 25));
            System.out.println("Created SteveMiddle in home");
        }
    }
    public void createPro() {
        if (worldRadio.isSelected()) {
            Main.world.addToWorld(new StevePro(getName(),getHp(),getDmg(),
                    1+Math.random() * 25, (int) (1+Math.random()*20)));
            System.out.println("Created StevePro in world");
        }
        else if(caveRadio.isSelected()){
            Main.world.cave.addToCave(new StevePro(getName(),getHp(),getDmg(),
                    1+Math.random() * 25, (int) (1+Math.random()*20)));
            System.out.println("Created StevePro in cave");
        } else if (homeRadio.isSelected()) {
            Main.world.home.addToHome(new StevePro(getName(),getHp(),getDmg(),
                    1+Math.random() * 25, (int) (1+Math.random()*20)));
            System.out.println("Created StevePro in home");
        }
    }
    public void createSpider() {
        if(worldRadio.isSelected()){
            Main.world.addToWorld(new Spider(getName(),getHp(),getDmg()));
            System.out.println("Created Spider in world ");
        }
        else if (caveRadio.isSelected()) {
            Main.world.cave.addToCave(new Spider(getName(),getHp(),getDmg()));
            System.out.println("Created Spider in cave ");
        }
        else if(homeRadio.isSelected()){
            Main.world.home.addToHome(new Spider(getName(),getHp(),getDmg()));
            System.out.println("Created Spider in home ");
        }
    }
}
