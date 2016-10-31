/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplegui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Label afsa;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afsa.setText("MIjan");
    }    

    @FXML
    private void handleNewUiAction(ActionEvent event) {
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource("Student.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        MultipleGUI.getMainStage().setScene(scene);
        MultipleGUI.getMainStage().show();
    }

    @FXML
    private void handlepop(ActionEvent event) {
        Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("Student.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
    }
    
}
