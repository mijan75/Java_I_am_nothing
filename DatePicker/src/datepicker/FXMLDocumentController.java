/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datepicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private DatePicker date;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
         LocalDate mijan = date.getValue();
         System.out.printf("%s", mijan);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    
}
