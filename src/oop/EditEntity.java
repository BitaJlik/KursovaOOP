package oop;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oop.Entities.Entity;
import oop.Entities.Steve;

public class EditEntity {
    static Entity entity1 = new Steve();
    @FXML
    private TextField input;
    @FXML
    private RadioButton radioHp;
    @FXML
    private ToggleGroup radios;
    @FXML
    private RadioButton radioDamage;
    @FXML
    private RadioButton radioName;
    @FXML
    private Label whatChange;
    @FXML
    private Label labelChanged;
    @FXML
    private Label labelWhat;
    @FXML
    private Button back;
    public void back() {
        Stage thisstage = (Stage) back.getScene().getWindow();
        listEntityCntrll.thisstage.show();
        thisstage.hide();
        System.out.println("[Edit] backwindow");
    }

    public void confirm() {
        check();
        if(radioName.isSelected()){
            entity1.setName(input.getText());
            labelWhat.setText("Ім'я");
        }
        else if(radioHp.isSelected()){
            try{
                int hp = 0;
                hp = Integer.parseInt(input.getText());
                entity1.setHp(hp);
            }catch (NumberFormatException e){
                System.out.println("Exception: input has string");
            }
            labelWhat.setText("Життя");
        }
        else if(radioDamage.isSelected()){
            try{
                int dmg = 0;
                dmg = Integer.parseInt(input.getText());
                entity1.setDamage(dmg);
            }catch (NumberFormatException e){
                System.out.println("Exception: input has string");
            }
            labelWhat.setText("Урон");
        }
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
            //Progress.Waiting(200);
            whatChange.setTextFill(Color.web("white"));
        }
    }
}
