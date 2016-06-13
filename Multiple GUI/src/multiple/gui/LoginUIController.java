/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiple.gui;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author mijan
 */
public class LoginUIController implements Initializable {
    
    private Label label;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField usernamField;
    @FXML
    private PasswordField passwordField;
    private ObservableList<String>list;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list = FXCollections.observableArrayList();
        list.add("Waiter");
        list.add("Manager");
        comboBox.setItems(list);
    }    

    @FXML
    private void hadleLogInButton(ActionEvent event) {
        try {
            String DB_URL = "jdbc:mysql://localhost/resturantdb";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
 
            String userName = usernamField.getText();
            String password = passwordField.getText();
            String accountType = comboBox.getSelectionModel().getSelectedItem();
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String query = "Select * from User where UserName = '"+userName+"'and Password=md5('"+password+"');";
            ResultSet resultSet = statement.executeQuery(query);
            
            Parent root =null;
            
            if (resultSet.next()){
                System.out.printf("You are a %s",accountType);
                if (accountType.equals("Waiter")){
                    root = FXMLLoader.load(getClass().getResource("Waiter.fxml"));
                }
                else{
                    root = FXMLLoader.load(getClass().getResource("ManagerUI.fxml"));
                }
                
                Scene scene = new Scene(root);

                MultipleGUI.getMainStage().setScene(scene);
                MultipleGUI.getMainStage().show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
