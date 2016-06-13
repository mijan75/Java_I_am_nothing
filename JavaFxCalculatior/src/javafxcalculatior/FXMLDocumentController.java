/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcalculatior;

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
    double number1;
    double number2;
    String operation;
    int flag=0;
    
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
    private void handleOneAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("1");
        }
        else
        {
            String newText=oldText+"1";
            displayField.setText(newText);
        }
    }

    @FXML
    private void handleTwoAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("2");
        }
        else
        {
            String newText=oldText+"2";
            displayField.setText(newText);
        }
    }

    @FXML
    private void handleThreeAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("3");
        }
        else
        {
            String newText=oldText+"3";
            displayField.setText(newText);
        }
    }

    @FXML
    private void handleFourAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("4");
        }
        else
        {
            String newText=oldText+"4";
            displayField.setText(newText);
        }
    }

    @FXML
    private void handleFiveAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("5");
        }
        else
        {
            String newText=oldText+"5";
            displayField.setText(newText);
        }  
    }

    @FXML
    private void handleSixAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("6");
        }
        else
        {
            String newText=oldText+"6";
            displayField.setText(newText);
        }
    }

    @FXML
    private void handleSevenAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("7");
        }
        else
        {
            String newText=oldText+"7";
            displayField.setText(newText);
        }
    }

    @FXML
    private void handleEightAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("8");
        }
        else
        {
            String newText=oldText+"8";
            displayField.setText(newText);
        }
    }

    @FXML
    private void handleNineAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals("0"))
        {
            displayField.setText("9");
        }
        else
        {
            String newText=oldText+"9";
            displayField.setText(newText);
        }   
    }
    
    @FXML
    private void handleZeroAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (!oldText.equals("0"))
        {
            String newText=oldText+"0";
            displayField.setText(newText);
        }
        
    }
    
    @FXML
    private void handleDotAction(ActionEvent event) {
        String oldText=displayField.getText();
        if (oldText.equals(""))
        {
            displayField.setText(".");
        }
        else
        {
            displayField.setText(oldText);
        }
        if (flag==0)
        {
                displayField.setText(oldText+".");
                flag=1;
        }
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        String oldText=displayField.getText();
        number1=Double.parseDouble(oldText);
        operation="Add";
        displayField.setText("");
        flag=0;
    }
    
    @FXML
    private void handleSutractAction(ActionEvent event) {
        String oldText=displayField.getText();
        number1=Double.parseDouble(oldText);
        operation="Substract";
        displayField.setText("");
        flag=0;
    }

    @FXML
    private void handleMultiplicationAction(ActionEvent event) {
        String oldText=displayField.getText();
        number1=Double.parseDouble(oldText);
        operation="Multiplication";
        displayField.setText("");
        flag=0;
    }

    @FXML
    private void handleDivisionAction(ActionEvent event) {
        String oldText=displayField.getText();
        number1=Double.parseDouble(oldText);
        operation="Division";
        displayField.setText("");
        flag=0;
    }
    
    @FXML
    private void handleEqualAction(ActionEvent event) {
        String newText=displayField.getText();
        number2=Double.parseDouble(newText);
        double result= 0.0;
        if (operation.equals("Add"))
        {
            result=number1+number2;
            displayField.setText(" "+result);
        }
        else if (operation.equals("Substract"))
        {
            result=number1-number2;
            displayField.setText(" "+result);
        }
        else if (operation.equals("Multiplication"))
        {
            result=number1*number2;
            displayField.setText(" "+result);
        }
        else if (operation.equals("Division"))
        {
            if (number2<0)
            {
                displayField.setText("Math Error!");
            }
            else
            {
                result=number1/number2;
                displayField.setText(" "+result);
            }
        }
    }

    @FXML
    private void handleAcAction(ActionEvent event) {
        displayField.setText("");
    }
}
