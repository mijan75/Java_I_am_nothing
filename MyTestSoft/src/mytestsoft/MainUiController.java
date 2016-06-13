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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author mijan
 */
public class MainUiController implements Initializable {
    
    private Label label;
    @FXML
    private ComboBox<String> accountTypeBox;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    private ObservableList<String>listt;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listt=FXCollections.observableArrayList();
        listt.add("CHAIRMAN");
        listt.add("C0-ORDINAOR");
        listt.add("TEACHER");
        listt.add("STUDENT");
        accountTypeBox.setItems(listt);
    }    

    @FXML
    private void hadleLogInAction(ActionEvent event) {
        try {
            String DB_URL = "jdbc:mysql://localhost/resturantdb";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String userNamee = userName.getText();
            String passwordd = password.getText();
            String accountType = accountTypeBox.getSelectionModel().getSelectedItem();
            
            String query = "select * from UserInformation where UserName='"+userNamee+"'and Password =md5('"+passwordd+"');";
            ResultSet resultSet = statement.executeQuery(query);
            
            if (resultSet.next()){
                
                String acco=resultSet.getString("AccountType");
                if (acco.equals(accountType)){
                    String Query="insert into TemporaryUserName values ('"+userNamee+"');";
                    
                    
                    statement.executeUpdate(Query);
                    Parent root = null;
                    
                    root =FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
        
                    Scene scene = new Scene(root);

                    MyTestSoft.getMainStage().setScene(scene);
                    MyTestSoft.getMainStage().show();
                }
                else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR ALERT");
                    alert.setHeaderText("Look At Below For The Error");
                    alert.setContentText("Account Type Don't Match");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR ALERT");
                alert.setHeaderText("Look At Below For The Error");
                alert.setContentText("Username and PassWord Don't Match");
                alert.showAndWait();
                  
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(MainUiController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSignUpAction(ActionEvent event) {
        try {
            Parent root = null;
            
            root = FXMLLoader.load(getClass().getResource("SignUpUi.fxml"));
            Scene scene = new Scene(root);
            MyTestSoft.getMainStage().setScene(scene);
            MyTestSoft.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(MainUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
