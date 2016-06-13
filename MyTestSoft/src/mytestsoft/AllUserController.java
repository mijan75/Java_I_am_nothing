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

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class AllUserController implements Initializable {
    private String userName;
    @FXML
    private TableView<UserClass> TableView;
    @FXML
    private TableColumn<UserClass, String> nameCollum;
    @FXML
    private TableColumn<UserClass, String> MailCollum;
    @FXML
    private TableColumn<UserClass, String> accountTypeCollum;
    private ObservableList<UserClass>list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
                    list=FXCollections.observableArrayList();
                    TableView.setItems(list);
                    
                    nameCollum.setCellValueFactory(data ->new SimpleStringProperty(data.getValue().getName()));
                    MailCollum.setCellValueFactory(data ->new SimpleStringProperty(data.getValue().getMail()));
                    accountTypeCollum.setCellValueFactory(data ->new SimpleStringProperty(data.getValue().getAccountType()));
                    
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
                    
                    String query2="select * from UserInformation";
                     resultSet =statement.executeQuery(query2);
                        
                        while (resultSet.next()){
                            String name =resultSet.getString("Name");
                            String mail = resultSet.getString("Mail");
                            String accountType = resultSet.getString("AccountType");
                            
                            UserClass user =new UserClass(name,mail,accountType);
                            list.add(user);
                        }
                        
            
                    } catch (SQLException ex) {
            Logger.getLogger(AllUserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    

    @FXML
    private void backAcction(ActionEvent event) {
        try {
            Parent root =null;
            root= FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
            
            Scene scene = new Scene(root);
            
            MyTestSoft.getMainStage().setScene(scene);
            MyTestSoft.getMainStage().show();
        } catch (IOException ex) {
            Logger.getLogger(AllUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
