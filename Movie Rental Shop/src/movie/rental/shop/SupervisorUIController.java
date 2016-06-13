/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.rental.shop;

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
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class SupervisorUIController implements Initializable {
 
    @FXML
    private ImageView imageView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void addNewEmployee(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("EmployeeSignUpUI.fxml"));
            
            Scene scene = new Scene(root);
            
            MovieRentalShop.getMainStage().setScene(scene);
            MovieRentalShop.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(SupervisorUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
            
            Scene scene = new Scene(root);
            
            MovieRentalShop.getMainStage().setScene(scene);
            MovieRentalShop.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(SupervisorUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
