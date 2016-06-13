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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class SignUpUiController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField re_Type_PasswordField;
    @FXML
    private ComboBox<String> ComboBox;
    private ObservableList<String> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list = FXCollections.observableArrayList();
        list.add("CHAIRMAN");
        list.add("C0-ORDINAOR");
        list.add("TEACHER");
        list.add("STUDENT");
        ComboBox.setItems(list);
    }

    @FXML
    private void handleSignUpAction(ActionEvent event) {

        try {
            String DB_URL = "jdbc:mysql://localhost/resturantdb";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
            
            String name = nameField.getText();
            String mail = mailField.getText();
            String userName = userNameField.getText();
            String password = passwordField.getText();
            String re_TypePassword = re_Type_PasswordField.getText();
            String accountType = ComboBox.getSelectionModel().getSelectedItem();
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            
            String query1 = "select * from UserInformation where UserName=('"+userName+"');";
            ResultSet resultSet = statement.executeQuery(query1);
            if (resultSet.next()){
                Alert alert = new Alert (AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Please Look The Infomation Below");
                alert.setContentText("Sorry!The User Name already taken\nPlease Choose Another One");
                alert.showAndWait();
                
                userNameField.setText("");
            }
            
            else{
                if (password.equals(re_TypePassword)) {
                    try {
                        String query = "insert into UserInformation values ('" + name + "','" + mail + "','" + userName + "',md5('" + password + "'),'" + accountType + "');";
                        statement.executeUpdate(query);
                        
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dailog");
                        alert.setHeaderText("Look At Below");
                        alert.setContentText("Account Create Successfully");
                        alert.showAndWait();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(SignUpUiController.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Look, The warning below");
                    alert.setContentText("Sorry! Password don't match\nPlease Enter the Password again");
                    alert.showAndWait();
                    
                    
                    passwordField.setText("");
                    re_Type_PasswordField.setText("");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void handleLogInAction(ActionEvent event) {
        try {
            Parent root =null;
            root = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
            
            Scene scene = new Scene(root);
            
            MyTestSoft.getMainStage().setScene(scene);
            MyTestSoft.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(SignUpUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
