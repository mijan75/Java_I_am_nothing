/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package total.inches.converter;

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
    private TextField Yardfield;
    @FXML
    private TextField Feetfield;
    @FXML
    private TextField Inchesfield;
    @FXML
    private TextField TotalInchesfield;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleButtonAction(ActionEvent event) {
        int yard;
        int feet;
        int inches;
        if (TotalInchesfield.getText().length()>0){
            int totalinch=Integer.parseInt(TotalInchesfield.getText());
             yard = totalinch/36;
             feet = (totalinch%36)/12;
             inches=(totalinch%36)%12;
            Yardfield.setText(""+yard);
            Feetfield.setText(""+feet);
            Inchesfield.setText(""+inches);
        }
        else{
            if (Yardfield.getText().length()>0){
                yard = Integer.parseInt(Yardfield.getText()); 
            }
            else {
                 yard = 0;
            }
            if (Feetfield.getText().length()>0){
                 feet = Integer.parseInt(Feetfield.getText()); 
            }
            else {
                 feet = 0;
            }
            if (Inchesfield.getText().length()>0){
                 inches = Integer.parseInt(Inchesfield.getText()); 
            }
            else {
                 inches = 0;
            }
            int totalinches=(yard*36)+(feet*12)+inches;
            TotalInchesfield.setText(""+totalinches);
        }
    }
    
}
