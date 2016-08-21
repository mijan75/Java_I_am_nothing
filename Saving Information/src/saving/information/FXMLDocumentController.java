/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving.information;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    public Connection connection;
    public Statement statement;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String DB_URL = "jdbc:mysql://localhost/I_am_nothing";
        String DB_USER = "root";
        String DB_Password = "ayesha75";
        
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_Password);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleLogInAction(ActionEvent event) {
        try {
            String Name = userName.getText();
            String Password = password.getText();
            
            String query = "select * from UserInformation where Password=md5('"+Password+"')";
            
            ResultSet resultSet = statement.executeQuery(query);
            Parent root = null;
            if(resultSet.next())
            {
                root = FXMLLoader.load(getClass().getResource("mainUI.fxml"));
                
                Scene scene = new Scene(root);
                SavingInformation.getNewStage().setScene(scene);
                SavingInformation.getNewStage().show();
            }
            else
            {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Look At Below For The Error");
                alert.setContentText("You Are Not DON");
                alert.showAndWait();
                userName.setText("");
                password.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
