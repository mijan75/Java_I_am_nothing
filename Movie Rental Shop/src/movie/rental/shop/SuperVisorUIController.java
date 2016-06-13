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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class SuperVisorUIController implements Initializable {
    public static int  SID;

    public static int getSID() {
        return SID;
    }
    
    
    @FXML
    private TableView<SuperVisor> tableView;
    @FXML
    private TableColumn<SuperVisor, Number> idCollum;
    @FXML
    private TableColumn<SuperVisor, String> nameCollum;
    @FXML
    private TableColumn<SuperVisor, String> phoneCollum;
    @FXML
    private TableColumn<SuperVisor, String> AddressCollum;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextArea addressField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField fieldForEmployeeSID;
    
    private ObservableList<SuperVisor>list;
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
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, Password);
            statement = connection.createStatement();
            list = FXCollections.observableArrayList();
            tableView.setItems(list);
            
            idCollum.setCellValueFactory(data ->new SimpleIntegerProperty(data.getValue().getId()));
            nameCollum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
            phoneCollum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhone()));
            AddressCollum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));
            
            String query1 = "Select * from Supervisor";
            ResultSet resultSet = statement.executeQuery(query1);
            
            while (resultSet.next())
            {
                int id = resultSet.getInt("SuperId");
                String name = resultSet.getString("SuperName");
                String address = resultSet.getString("SuperAdress");
                String phone = resultSet.getString("SuperPhone");
                
                SuperVisor superVisor = new SuperVisor (id ,name,address,phone);
                list.add(superVisor);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SuperVisorUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSuperVisorSubmit(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String password = passwordField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            
            String query = "insert into Supervisor values ('"+id+"','"+name+"','"+address+"','"+phone+"',md5('"+password+"'));";
            System.out.printf("%s",query);
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(SuperVisorUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleSubmitEmployeeSID(ActionEvent event) {
        int SId = Integer.parseInt(fieldForEmployeeSID.getText());
        SID = SId;
    }

    @FXML
    private void hadlePreviousWindow(ActionEvent event) {
        MovieRentalShop.getMainStage().setScene(MovieRentalShop.getEmployeUI());
        MovieRentalShop.getMainStage().show();
    }
    
}
