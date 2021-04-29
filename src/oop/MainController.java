package oop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {
    public static int size = 0;
    @FXML
    private Button minus;
    @FXML
    private Button plus;
    @FXML
    private TextField inpt;
    @FXML
    private Button confirm;

    @FXML
    private void click() {
        if(plus.isHover()){
            if(!inpt.getText().equals("")){
                try{
                   size = Integer.parseInt(inpt.getText());
                }
                catch (NumberFormatException e){
                    System.out.println("trying String to int!");
                }
            }
            size++;
            inpt.setText(String.valueOf(size));
        }
        else if(minus.isHover()){
            if(!inpt.getText().equals("")){
                try{
                    size = Integer.parseInt(inpt.getText());
                }
                catch (NumberFormatException e){
                    System.out.println("trying String to int!");
                }
            }
            if (size >= 1) size--;
             else size = 0;
            inpt.setText(String.valueOf(size));
        }


    }
    @FXML
    public void confirm( ) throws Exception {
        if (!inpt.getText().equals(""))
            System.out.println("Text: " + inpt.getText());
        try{
            size = Integer.parseInt(inpt.getText());
        }
        catch (NumberFormatException e){
            System.out.println("Trying string to int!");
        }
        Stage thisstage = (Stage) confirm.getScene().getWindow();
        thisstage.hide();
    }
}
