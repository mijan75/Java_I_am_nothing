/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.demo.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField itemcodeField;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemcategoryField;
    @FXML
    private TextField priceField;
    @FXML
    private TextArea descriptionArea;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void handleButtonAddAction(ActionEvent event) {
        int itemCode = Integer.parseInt(itemcodeField.getText());
        String itemName = itemNameField.getText();
        String category = itemcategoryField.getText();
        String description = descriptionArea.getText();
        double price = Double.parseDouble(priceField.getText());
        
        String DB_URL = "jdbc:mysql://localhost/resturantdb";
        String DB_US = "root";
        String DB_PASS = "ayesha75";
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_US, DB_PASS);
            Statement statement = connection.createStatement();
            String query = "insert into FoodItem values ("+itemCode+",'"+itemName+"','"+category+"','"+description+"',"+price+");";
            System.out.printf("%s",query);
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
