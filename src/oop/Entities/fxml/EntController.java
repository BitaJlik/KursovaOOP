package oop.Entities.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.Entities.Spider;
import oop.Entities.Steve;
import oop.Entities.SteveMiddle;
import oop.Entities.StevePro;
import oop.Main;
import oop.MainController;
import oop.MainMenu;
import oop.Progress;

public class EntController {
    @FXML
    private Label label;
    @FXML
    private Label inputName;
    @FXML
    private Label inputDamage;
    @FXML
    private Label inputHp;
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
        System.out.println("[Entity] backwindow");
    }
    @FXML
    private void confirm(){
        MainMenu.update();
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.close();
        System.out.println("[Entity] closed");
    }
    public String getName(){
        String text = inName.getText();
        inName.setText("");
        return text;
    }
    public int getHp(){
        int hp = 1;
        try {
            hp = Integer.parseInt(inHp.getText());
        }catch (NumberFormatException e){
            System.out.println("ErrorHp");
        }
        inHp.setText("");
        return hp;
    }
    public int getDmg(){
        int dmg = 1;
        try {
            dmg = Integer.parseInt(inDmg.getText());
        }catch (NumberFormatException e){
            System.out.println("ErrorDmg");
        }
        inDmg.setText("");
        return dmg;
    }
    private void check(){
        checkInputs();
        checkRadio();
    }

    public void createNovice() {
        check();
            if (worldRadio.isSelected()) {
                Main.world.addToWorld(new Steve(getName(), getHp(), getDmg(),"world"));
                System.out.println("Created Steve in world");
            } else if (caveRadio.isSelected()) {
                Main.world.cave.addToCave(new Steve(getName(), getHp(), getDmg(),"cave"));
                System.out.println("Created Steve in cave");
            } else if (homeRadio.isSelected()) {
                Main.world.home.addToHome(new Steve(getName(), getHp(), getDmg(),"home"));
                System.out.println("Created Steve in home");
            }
    }
    public void createMid() {
        check();
            if (worldRadio.isSelected()) {
                Main.world.addToWorld(new SteveMiddle(getName(), getHp(), getDmg(), 1 + Math.random() * 25,"world"));
                System.out.println("Created SteveMiddle in world");
            } else if (caveRadio.isSelected()) {
                Main.world.cave.addToCave(new SteveMiddle(getName(), getHp(), getDmg(), 1 + Math.random() * 25,"cave"));
                System.out.println("Created SteveMiddle in cave");
            } else if (homeRadio.isSelected()) {
                Main.world.home.addToHome(new SteveMiddle(getName(), getHp(), getDmg(), 1 + Math.random() * 25,"home"));
                System.out.println("Created SteveMiddle in home");
            }
    }
    public void createPro() {
        check();
            if (worldRadio.isSelected()) {
                Main.world.addToWorld(new StevePro(getName(), getHp(), getDmg(),
                        1 + Math.random() * 25, (int) (1 + Math.random() * 20),"world"));
                System.out.println("Created StevePro in world");
            } else if (caveRadio.isSelected()) {
                Main.world.cave.addToCave(new StevePro(getName(), getHp(), getDmg(),
                        1 + Math.random() * 25, (int) (1 + Math.random() * 20),"cave"));
                System.out.println("Created StevePro in cave");
            } else if (homeRadio.isSelected()) {
                Main.world.home.addToHome(new StevePro(getName(), getHp(), getDmg(),
                        1 + Math.random() * 25, (int) (1 + Math.random() * 20),"home"));
                System.out.println("Created StevePro in home");
            }
    }
    public void createSpider() {
        check();
            if (worldRadio.isSelected()) {
                Main.world.addToWorld(new Spider(getName(), getHp(), getDmg(),"world"));
                System.out.println("Created Spider in world ");
            } else if (caveRadio.isSelected()) {
                Main.world.cave.addToCave(new Spider(getName(), getHp(), getDmg(),"cave"));
                System.out.println("Created Spider in cave ");
            } else if (homeRadio.isSelected()) {
                Main.world.home.addToHome(new Spider(getName(), getHp(), getDmg(),"home"));
                System.out.println("Created Spider in home ");
            }
    }
    private void checkRadio(){
        if(!(worldRadio.isSelected()|| caveRadio.isSelected()|| homeRadio.isSelected())){
            label.setTextFill(Color.web("red"));
        }
        else {
         Progress.Waiting(200);
            label.setTextFill(Color.web("white"));
        }
    }
    private void checkInputs(){
        if(inName.getText().equals("")) inputName.setTextFill(Color.web("red"));

        if(inHp.getText().equals("")) inputHp.setTextFill(Color.web("red"));

        if(inDmg.getText().equals("")) inputDamage.setTextFill(Color.web("red"));

        if(!(inName.getText().equals("")||inHp.getText().equals("")||inDmg.getText().equals(""))){
            Progress.Waiting(200);
            removeRed();
        }
    }
    private void removeRed(){
        inputName.setTextFill(Color.web("white"));
        inputHp.setTextFill(Color.web("white"));
        inputDamage.setTextFill(Color.web("white"));
    }
}
