/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytestsoft;

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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class UserInterfaceController implements Initializable {
    String userName=null;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            String DB_URL = "jdbc:mysql://localhost/resturantdb";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
            
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String query="select * from TemporaryUserName;";
            
            ResultSet resultSet=statement.executeQuery(query);
            if (resultSet.next()){
                userName = resultSet.getString("UserName");
                String query1="delete from TemporaryUserName where UserName='"+userName+"';";
                statement.executeUpdate(query1);
                label.setText("WelCome "+userName);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void hadleAllUser(ActionEvent event) {
        try {
            String Query="insert into TemporaryUserName values ('"+userName+"');";
            String DB_URL = "jdbc:mysql://localhost/resturantdb";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
            
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate(Query);
            
            Parent root =null; 
            root= FXMLLoader.load(getClass().getResource("AllUser.fxml"));
        
            Scene scene = new Scene(root);
        
            MyTestSoft.getMainStage().setScene(scene);
            MyTestSoft.getMainStage().show();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleStartChating(ActionEvent event) {
        try {
            String Query="insert into TemporaryUserName values ('"+userName+"');";
            String DB_URL = "jdbc:mysql://localhost/resturantdb";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
            
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            statement.executeUpdate(Query);
            
            Parent root =null; 
            root= FXMLLoader.load(getClass().getResource("Chatting.fxml"));
        
            Scene scene = new Scene(root);
        
            MyTestSoft.getMainStage().setScene(scene);
            MyTestSoft.getMainStage().show();
        } catch (SQLException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleProfile(ActionEvent event) {
    }
    
}
