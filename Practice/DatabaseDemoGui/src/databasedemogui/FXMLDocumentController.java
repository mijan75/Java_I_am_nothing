/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasedemogui;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private ListView<Student> listview;
    private ObservableList<Student> list;
    
    @FXML
    private TableView<Student> tableview;
    @FXML
    private TableColumn<Student, Number> idColumn;
    @FXML
    private TableColumn<Student, String> namecolumn;
    @FXML
    private TableColumn<Student, Number> balancecolumn;
    @FXML
    private ComboBox<Student> cbox;
    @FXML
    private DatePicker datePicker;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            list = FXCollections.observableArrayList();
            listview.setItems(list);
            tableview.setItems(list);
            cbox.setItems(list);
            String DB_URL = "jdbc:mysql://localhost/Mijan";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
            
            idColumn.setCellValueFactory(data -> new SimpleIntegerProperty (data.getValue().getId()));
            namecolumn.setCellValueFactory(data -> new  SimpleStringProperty (data.getValue().getName()));
            balancecolumn.setCellValueFactory(data -> new SimpleDoubleProperty (data.getValue().getBalance()));
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String query = "select * from Table1";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double balance = resultSet.getDouble("balance");
                
                Student student = new Student(id, name, balance);
                list.add(student);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                       
        } 

    @FXML
    private void handledateAction(ActionEvent event) {
        LocalDate date = datePicker.getValue();
        System.out.println(date);
    }
}    
    

