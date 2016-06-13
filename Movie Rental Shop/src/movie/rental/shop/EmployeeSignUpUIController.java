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
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class EmployeeSignUpUIController implements Initializable {

    @FXML
    private Label welconeNote;
    @FXML
    private TextField employID;
    @FXML
    private TextField employName;
    @FXML
    private TextArea employeeAddress;
    @FXML
    private TextField employeePhone;
    @FXML
    private DatePicker employeeHireDate;
    @FXML
    private PasswordField employeePassword;
    private String DB_URL = "jdbc:mysql://localhost/MovieRentalShop";
    private String DB_USER = "root";
    private String Password = "ayesha75";
    private Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            welconeNote.setText("Complete the from to add a new Employee");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSignUpUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void storeInformation(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StoreUI.fxml"));
            
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeSignUpUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void supervisorInformation(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SuperVisorUI.fxml"));
            
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeSignUpUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void signUP(ActionEvent event) {
        try {
            int EId = Integer.parseInt(employID.getText());
            String EName = employName.getText();
            String EAddress = employeeAddress.getText();
            String EPhone = employeePhone.getText();
            LocalDate EHireDate = employeeHireDate.getValue();
            LocalDate localDate = null;
            String EPassword = employeePassword.getText();
            int SId = SuperVisorUIController.getSID();
            int DId =StoreUIController.getSID();
            
            String query1 = "select * from Employee where EId = '"+EId+"'";
            ResultSet resultSet = statement.executeQuery(query1);
            
            if (resultSet.next())
            {
                employID.setText("");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Alert");
                alert.setHeaderText("Look at the below for tha information");
                alert.setContentText("UsesriD is already taken\nPlease enter anothe ueseId");
                alert.showAndWait();
            }
            
            else
            {
                String query = "insert into Employee values ("+EId+","+SId+","+DId+",'"+EName+"','"+EAddress+"','"+EPhone+"','"+EHireDate+"',md5('"+EPassword+"'))";
                statement.executeUpdate(query);
                
                employID.setText("");
                employName.setText("");
                employeeAddress.setText("");
                employeePhone.setText("");
                employeeHireDate.setValue(localDate);
                employeePassword.setText("");
                
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Alert");
                alert.setHeaderText("Look at the below for tha information");
                alert.setContentText("Account create successfully");
                alert.showAndWait();
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeSignUpUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
        MovieRentalShop.getMainStage().setScene(MovieRentalShop.getSuperViosrUiScene());
        MovieRentalShop.getMainStage().show();
    }

}
