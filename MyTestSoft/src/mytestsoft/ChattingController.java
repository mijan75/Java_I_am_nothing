/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytestsoft;

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

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class ChattingController implements Initializable {
    private String userName;
    @FXML
    private TableView<MESSAGE> TableView;
    @FXML
    private TableColumn<MESSAGE, String> ChatCollum;
    private ObservableList<MESSAGE>list;
    @FXML
    private TextField textField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
            
            String DB_URL = "jdbc:mysql://localhost/resturantdb";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
            
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String query = "select * from TemporaryUserName;";
            ResultSet resultSet =  statement.executeQuery(query);
            
            if (resultSet.next()){
                
                userName =resultSet.getString("UserName");
                String query1="delete from TemporaryUserName where UserName='"+userName+"';";
                statement.executeUpdate(query1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChattingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void handleSendAction(ActionEvent event) {
        try {
            
            list = FXCollections.observableArrayList();
            TableView.setItems(list);
            
            ChatCollum.setCellValueFactory(data ->new SimpleStringProperty(data.getValue().getMessage()));
            
            String DB_URL = "jdbc:mysql://localhost/resturantdb";
            String DB_USER = "root";
            String DB_PASS = "ayesha75";
            
            
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            Statement statement = connection.createStatement();
            String message =textField.getText();
            String query ="insert into Message values('"+message+"');";
            statement.executeUpdate(query);
            
            //String messege = SMSField.getText();
//            String query = "insert into Test values ('"+messege+"');";
//            statement.executeUpdate(query);
//            
//            String query1 = "select * from Test";
//            ResultSet resultSet = statement.executeQuery(query1);
//            
//            while (resultSet.next()){
//                String sms = resultSet.getString("MESSAGE");
//                
//                SMS msg = new SMS(sms);
//                list.add(msg);
//            }
            
            String query1="select * from Message";
            ResultSet resultSet=null;
                    resultSet = statement.executeQuery(query1);
            
            while (resultSet.next()){
                String sms =resultSet.getString("Text");
                MESSAGE msg = new MESSAGE(sms);
                list.add(msg);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ChattingController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void stopChatting(ActionEvent event) {
        try {
            Parent root =null;
            root= FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
            
            Scene scene = new Scene(root);
            
            MyTestSoft.getMainStage().setScene(scene);
            MyTestSoft.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(ChattingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
