/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casio_calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField Textf;
    double Number1;
    double Number2;
    String operation;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handle1Action(ActionEvent event) {
        String oldText = Textf.getText();
        Textf.setText(oldText+"1");
    }

    @FXML
    private void handleEqualAction(ActionEvent event) {
        Number2 = Double.parseDouble(Textf.getText());
        double result = Number1 + Number2;
        Textf.setText(result+" ");
    }

    @FXML
    private void handleaddaction(ActionEvent event) {
        
        
        Number1 = Double.parseDouble(Textf.getText());
        Textf.setText("");
        operation= "Add";
    }

    @FXML
    private void handle789(ActionEvent event) {
        String oldText = Textf.getText();
        Button b = (Button)event.getSource();
        String newText = oldText + b.getText();
        Textf.setText(newText);
    }
    
}
