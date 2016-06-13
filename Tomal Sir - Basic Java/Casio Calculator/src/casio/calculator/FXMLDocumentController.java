/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casio.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {
    double number1;
    double number2;
    String operation;
    
    private Label label;
    @FXML
    private TextField displayField;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleOneClickAction(ActionEvent event) {
        String oldText = displayField.getText();
        String newText = oldText + "1";
        displayField.setText(newText);
    }

    @FXML
    private void handleTwoClickAction(ActionEvent event) {
        String oldText = displayField.getText();
        String newText = oldText + "2";
        displayField.setText(newText);
    }

    @FXML
    private void handleZeroClickAction(ActionEvent event) {
        String oldText = displayField.getText();
        if (!oldText.equals("0")) {
            String newText = oldText + "0";
            displayField.setText(newText);        
        }
    }

    @FXML
    private void handleAdditionOperation(ActionEvent event) {
        String oldText = displayField.getText();
        number1 = Double.parseDouble(oldText);
        operation = "ADDITION";
        displayField.setText("");
    }

    @FXML
    private void handleEqualOperation(ActionEvent event) {
        String newText = displayField.getText();
        number2 = Double.parseDouble(newText);
        double result = 0.0;
        if (operation.equals("ADDITION"))
            result = number1 + number2;
        else if (operation.equals("SUBTRACTION"))
            result = number1 - number2;
        displayField.setText("" + result);
    }
    
}
