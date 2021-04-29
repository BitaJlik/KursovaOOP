package oop.Entities.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oop.Entities.Steve;

public class EntController {
    @FXML
    private TextField inName;
    @FXML
    private TextField inHp;
    @FXML
    private TextField inDmg;
    @FXML
    private Button back;
    @FXML
    private Button confirm;
    @FXML
    private RadioButton radioStevenovice;
    @FXML
    private RadioButton radioSteveMidlle;
    @FXML
    private RadioButton radioSpider;
    @FXML
    private void back(){
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.close();
    }
    @FXML
    private void confirm(){
        String name;
        double hp,dmg;
        Steve steve = null;
        try {
            name = inName.getText();
            hp = Integer.parseInt(inHp.getText());
            dmg = Integer.parseInt(inDmg.getText());
            steve = new Steve(name,hp,dmg);
        }
        catch (NumberFormatException e){
            System.out.println("Trying string to int !!!");
        }
        System.out.println(steve);
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.close();
    }
}
