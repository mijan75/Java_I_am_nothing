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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class StoreUIController implements Initializable {
    private static int SID;

    public static int getSID() {
        return SID;
    }
    
    @FXML
    private TextField idField;
    @FXML
    private TableColumn<Store, String> nameField;
    @FXML
    private TableView<Store> tableView;
    @FXML
    private TableColumn<Store, Number> idCollum;
    @FXML
    private TableColumn<Store, String> phoneField;
    @FXML
    private TableColumn<Store, String> addressField;
    @FXML
    private TextField SIDForEmployee;
    @FXML
    private TextField name;
    @FXML
    private TextArea phone;
    @FXML
    private TextField adress;
    
    private ObservableList<Store>list;
    private String DB_URL = "jdbc:mysql://localhost/MovieRentalShop";
    private String DB_USER = "root";
    private String Password = "ayesha75";
    private Statement statement;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list = FXCollections.observableArrayList();
        tableView.setItems(list);
        
        idCollum.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        nameField.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getName()));
        phoneField.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getPhone()));
        addressField.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getAddress()));
       
        
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
            statement = connection.createStatement();
            String query = "select * from Store";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next())
            {
                int id = resultSet.getInt("SId");
                String name = resultSet.getString("SName");
                String phone = resultSet.getString("SPhone");
                String address = resultSet.getString("SAdress");
                
                Store store = new Store (id,name,phone,address);
                list.add(store);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StoreUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleBackAction(ActionEvent event) {
        MovieRentalShop.getMainStage().setScene(MovieRentalShop.getEmployeUI());
        MovieRentalShop.getMainStage().show();
    }

    @FXML
    private void handleAddDistributor(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DistribuotUI.fxml"));
            
            
            Scene scene = new Scene(root);
            
            
            MovieRentalShop.getMainStage().setScene(scene);
            MovieRentalShop.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeSignUpUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void submintStoreInformation(ActionEvent event) {
        Connection connection =null;
        
        list = FXCollections.observableArrayList();
        tableView.setItems(list);
        
        idCollum.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        nameField.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getName()));
        phoneField.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getPhone()));
        addressField.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getAddress()));
        
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
            statement = connection.createStatement();
           
            
            int id = Integer.parseInt(idField.getText());
            //int DId = DistribuotUIController.getDID();
            int DId = 1;
            String name = this.name.getText();
            String phone = this.phone.getText();
            String address = this.adress.getText();
            
            String query1 = "insert into Store values ('"+id+"','"+DId+"','"+name+"','"+phone+"','"+address+"');";
            System.out.printf("%s", query1);
            statement.executeUpdate(query1);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(StoreUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
            statement = connection.createStatement();
            String query = "select * from Store";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next())
            {
                int id = resultSet.getInt("SId");
                String name = resultSet.getString("SName");
                String phone = resultSet.getString("SPhone");
                String address = resultSet.getString("SAdress");
                
                Store store = new Store (id,name,phone,address);
                list.add(store);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StoreUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void handleSubmitSAction(ActionEvent event) {
        int Sid = Integer.parseInt(SIDForEmployee.getText());
        SID = Sid;
    }

    
}
