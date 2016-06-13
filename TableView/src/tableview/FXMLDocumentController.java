/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private ListView<FoodItem> itemListView;
    @FXML
    private TableView<FoodItem> itemTableView;
    private ObservableList<FoodItem>items;
    @FXML
    private TableColumn<FoodItem, Number> codeTable;
    @FXML
    private TableColumn<FoodItem, String> nameTable;
    @FXML
    private TableColumn<FoodItem, String> categoryTable;
    @FXML
    private TableColumn<FoodItem, String> descriptionTable;
    @FXML
    private TableColumn<FoodItem, Number> priceTable;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        items =FXCollections.observableArrayList();
        itemListView.setItems(items);
        itemTableView.setItems(items);
        
        codeTable.setCellValueFactory(data ->new SimpleIntegerProperty(data.getValue().getItemCode()));
        nameTable.setCellValueFactory(data ->new SimpleStringProperty(data.getValue().getItemName()));
        categoryTable.setCellValueFactory(data ->new SimpleStringProperty(data.getValue().getCategory()));
        descriptionTable.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        priceTable.setCellValueFactory(data ->new SimpleDoubleProperty(data.getValue().getPrice()));
        
        String DB_URL = "jdbc:mysql://localhost/resturantdb";
        String DB_US = "root";
        String DB_PASS = "ayesha75";
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_US, DB_PASS);
            Statement statement = connection.createStatement();
            String query ="select * from FoodItem";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                int itemCode =Integer.parseInt( resultSet.getString("itemCode"));
                String itemName = resultSet.getString("itemName");
                String category=resultSet.getString("categroy");
                String description=resultSet.getString("description");
                double price =Double.parseDouble(resultSet.getString("price"));
                FoodItem item = new FoodItem(itemCode,itemName,category,description,price);
                items.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
