/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.rental.shop;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author mijan
 */
public class MainUIController implements Initializable {
    public static String Supervisor ;

    public static String getUserName() {
        return Supervisor;
    }

    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    private ObservableList<String>list;
    private String DB_URL = "jdbc:mysql://localhost/MovieRentalShop";
    private String DB_USER = "root";
    private String Password = "ayesha75";
    private Statement statement;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
            statement = connection.createStatement();
            } catch (SQLException ex) {
            Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        list = FXCollections.observableArrayList();
        list.add("Supervisor");
        list.add("Employee");
        comboBox.setItems(list);
        Image image = new Image("Images/Selection_003.png");
        imageView.setImage(image);
        
    }    

    @FXML
    private void handleLogInAction(ActionEvent event) {
        try {
            String accountType = comboBox.getSelectionModel().getSelectedItem();
            String supervisor = "Supervisor";
            String employee = "Employee";
            int userId = Integer.parseInt(userNameField.getText());
            String password = passwordField.getText();
            
            
            
            
            if (supervisor.equals(accountType))
            {
                String query = "select * from Supervisor where SuperId="+userId+" and password=md5('"+password+"');" ;
                ResultSet resultSet = statement.executeQuery(query);
                
                if (resultSet.next())
                {
                    this.Supervisor =resultSet.getString("SuperName");
                    Parent root= null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("SupervisorUI.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                    Scene scene = new Scene(root);

                    MovieRentalShop.getMainStage().setScene(scene);
                    MovieRentalShop.getMainStage().show();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR ALERT");
                    alert.setHeaderText("Look At Below For The Error");
                    alert.setContentText("Username and PassWord Don't Match");
                    alert.showAndWait();
                }
            }
            else if (employee.equals(accountType))
            {
                String query1 = "select * from Employee where EId="+userId+" and password=md5('"+password+"');" ;
                ResultSet resultSet1 = statement.executeQuery(query1);
                
                if (resultSet1.next())
                {
                    
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR ALERT");
                    alert.setHeaderText("Look At Below For The Error");
                    alert.setContentText("Username and PassWord Don't Match");
                    alert.showAndWait();
                }
            }
            else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR ALERT");
                    alert.setHeaderText("Look At Below For The Error");
                    alert.setContentText("Account Type Don't Match");
                    alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
