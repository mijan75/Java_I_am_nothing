/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author mijan
 */
public class LogInController implements Initializable {
    
    private Label label;
    @FXML
    private TextField SMSField;
    @FXML
    private TableView<SMS> TableView;
    @FXML
    private TableColumn<SMS, String> SMS;
    private ObservableList<SMS>list;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void handleSendAcction(ActionEvent event) {
        try {
            list=FXCollections.observableArrayList();
            TableView.setItems(list);
            
            SMS.setCellValueFactory(data ->new SimpleStringProperty(data.getValue().getSms()));
            
            String DB_URL="jdbc:mysql://localhost/resturantdb";
            String DB_USERNAME="root";
            String DB_PASSWORD = "ayesha75";
            
            Connection connection= DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String messege = SMSField.getText();
            String query = "insert into Test values ('"+messege+"');";
            statement.executeUpdate(query);
            
            String query1 = "select * from Test";
            ResultSet resultSet = statement.executeQuery(query1);
            
            while (resultSet.next()){
                String sms = resultSet.getString("MESSAGE");
                
                SMS msg = new SMS(sms);
                list.add(msg);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
