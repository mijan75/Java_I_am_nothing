/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasesimpleapp;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField itemCodeField;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemCategoryField;
    @FXML
    private TextField itemDescription;
    @FXML
    private TextField priceField;
    private ArrayList<Food>nameofFood;
    private int length;
    private int flag=0;
    
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
//  
    private String DB_URL = "jdbc:mysql://localhost/resturantdb";
    private String DB_USER = "root";
    private String DB_PASSWORD = "ayesha75";
    
    private Connection connection;
    private Statement statement;
    public int currentIndex;
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameofFood=new ArrayList<>();
        String query = "select * from FoodItem";
        try {
            // TODO
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next())
            {
                int id = resultSet.getInt("itemCode");
                String name = resultSet.getString("itemName");
                String category = resultSet.getString("categroy");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                
                Food food = new Food (id,name,category,description,price);
                nameofFood.add(food);
            }
            
            display();
            previousButton.setDisable(true);
            length=nameofFood.size();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    private void display(){
        int id = nameofFood.get(currentIndex).getId();
        itemCodeField.setText(id+"");
        String name = nameofFood.get(currentIndex).getName();
        itemNameField.setText(name);
        String category = nameofFood.get(currentIndex).getCategory();
        itemCategoryField.setText(category);
        String description = nameofFood.get(currentIndex).getDescription();
        itemDescription.setText(description);
        double price = nameofFood.get(currentIndex).getPrice();
        priceField.setText(price+"");
        
    } 

    @FXML
    private void resetAction(ActionEvent event) {
        itemCodeField.setText("");
        itemNameField.setText("");
        itemCategoryField.setText("");
        itemDescription.setText("");
        priceField.setText("");
    }

    @FXML
    private void submitAction(ActionEvent event) {
    }

    @FXML
    private void pAction(ActionEvent event) {
        currentIndex--;
        if(currentIndex<length)
        {
            nextButton.setDisable(false);
        }
        if (currentIndex<0)
        {
            previousButton.setDisable(true);
        }
        if(currentIndex==0)
        {
            display();
            previousButton.setDisable(true);
        }
        else
        {
            display();
        }
           
    }

    @FXML
    private void nAction(ActionEvent event) {
        currentIndex++;
        if (currentIndex>0)
        {
            previousButton.setDisable(false);
        }
        if (currentIndex == length-1)
        {
            display();
            nextButton.setDisable(true);
        }
        else
        {
            display();
        }
        
    }

    @FXML
    private void searchAction(ActionEvent event) {
        int id=Integer.parseInt(itemCodeField.getText());
        flag=0;
        for (int i=0;i<nameofFood.size();i++)
        {
            if (id==nameofFood.get(i).getId())
            {
                flag=1;
                currentIndex=i;
                
                if (currentIndex==0)
                {
                    previousButton.setDisable(true);
                    nextButton.setDisable(false);
                    display();
                    System.out.printf("ghgjghgjgj");
                }
                else if (currentIndex==length-1)
                {
                    nextButton.setDisable(true);
                    previousButton.setDisable(false);
                    display();
                }
                else
                {
                    display();
                    nextButton.setDisable(false);
                    previousButton.setDisable(false);
                }
            }
        }
        if (flag==0)
        {
            Alert alert =new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Look Below For The Information");
            alert.setContentText("You enter a wrong Food Id");
            
            alert.showAndWait();
            itemCodeField.setText("");
        }
    }
    
    
}
