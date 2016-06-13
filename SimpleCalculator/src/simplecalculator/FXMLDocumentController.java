/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField Number1field;
    @FXML
    private TextField Number2field;
    @FXML
    private TextField Resultfield;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleSubstractAction(ActionEvent event) {
        int number1;
        int number2;
        int substract;
        number1=Integer.parseInt(Number1field.getText());
        number2=Integer.parseInt(Number2field.getText());
        substract=number1-number2;
        Resultfield.setText(""+substract);
    }

    @FXML
    private void handleMultiplicationAction(ActionEvent event) {
        int number1;
        int number2;
        int multiplication;
        number1=Integer.parseInt(Number1field.getText());
        number2=Integer.parseInt(Number2field.getText());
        multiplication=number1*number2;
        Resultfield.setText(""+multiplication);
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        int number1;
        int number2;
        int add;
        number1=Integer.parseInt(Number1field.getText());
        number2=Integer.parseInt(Number2field.getText());
        add=number1+number2;
        Resultfield.setText(""+add);
    }

    @FXML
    private void handleDivisionAction(ActionEvent event) {
        double number1;
        double number2;
        double division;
        if (Double.parseDouble(Number2field.getText())==0)
        {
            Resultfield.setText("Division is not Possible");
        }
        else
        {
            number1=Double.parseDouble(Number1field.getText());
            number2=Double.parseDouble(Number2field.getText());
            division=number1/number2;
            Resultfield.setText(""+division);
        }
    }
    
}
