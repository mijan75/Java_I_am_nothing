/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.rental.shop;

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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class DistribuotUIController implements Initializable {
    public static int DID;

    public static int getDID() {
        return DID;
    }
    @FXML
    private TableView<Distributor> tablesView;
    @FXML
    private TableColumn<Distributor, Number> idCollum;
    @FXML
    private TableColumn<Distributor, String> nameCollum;
    @FXML
    private TableColumn<Distributor, String> phoneCollum;
    @FXML
    private TableColumn<Distributor, String> adressCollum;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextArea addressField;
    @FXML
    private TextField didForEmployee;
    
    private ObservableList<Distributor>list;
    
    private String DB_URL = "jdbc:mysql://localhost/MovieRentalShop";
    private String DB_USER = "root";
    private String Password = "ayesha75";
    private Statement statement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        tablesView.setItems(list);
        
        idCollum.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        nameCollum.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getName()));
        phoneCollum.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getPhone()));
        adressCollum.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getAddress()));
        
        try {
            // TODO
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
            statement = connection.createStatement();
            String query = "select * from Distributor";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next())
            {
                int id = resultSet.getInt("DId");
                String name = resultSet.getString("DName");
                String address = resultSet.getString("DAddress");
                String phone = resultSet.getString("DPhone");
                
                Distributor distributor = new Distributor (id,name,phone,address);
                list.add(distributor);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DistribuotUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void handleDistributorSubmit(ActionEvent event) {
        Connection connection =null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
        } catch (SQLException ex) {
            Logger.getLogger(DistribuotUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DistribuotUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        list = FXCollections.observableArrayList();
        tablesView.setItems(list);
        
        idCollum.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        nameCollum.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getName()));
        phoneCollum.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getPhone()));
        adressCollum.setCellValueFactory(data -> new SimpleStringProperty (data.getValue().getAddress()));
        
        
        
        
        
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            
            String query = "insert into Distributor values ('"+id+"','"+name+"','"+phone+"','"+address+"');";
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DistribuotUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // TODO
            connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
            statement = connection.createStatement();
            String query = "select * from Distributor";
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next())
            {
                int id = resultSet.getInt("DId");
                String name = resultSet.getString("DName");
                String address = resultSet.getString("DAddress");
                String phone = resultSet.getString("DPhone");
                
                Distributor distributor = new Distributor (id,name,phone,address);
                list.add(distributor);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DistribuotUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleDIdSubmitAction(ActionEvent event) {
        int id = Integer.parseInt(didForEmployee.getText());
        DID = id;
    }
    
}
