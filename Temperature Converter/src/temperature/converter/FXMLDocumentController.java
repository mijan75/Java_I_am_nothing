/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperature.converter;

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
    private TextField Celsiusfield;
    @FXML
    private TextField Farenheitfield;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Handlebutton(ActionEvent event) {
        double Celsius=Double.parseDouble(Celsiusfield.getText());
        double Farenheit=Celsius*9/5+32;
        Farenheitfield.setText(""+Farenheit);
    }
    
}
