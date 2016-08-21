/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving.information;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class MainUIController implements Initializable {

    @FXML
    private ComboBox<String> ComboBox;
    @FXML
    private TextArea textArea;
    @FXML
    private Label lavel1;
    @FXML
    private Label lavel2;
    public Connection connection;
    public Statement statement;
    private ObservableList<String>list;
    private ArrayList<Links> linklist;
    String item;
    int count = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String DB_URL = "jdbc:mysql://localhost/I_am_nothing";
        String DB_USER = "root";
        String DB_Password = "ayesha75";
        
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_Password);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        linklist = new ArrayList<Links>();
        list = FXCollections.observableArrayList();
        list.add("Links");
        list.add("Quotes");
        ComboBox.setItems(list);     
    }    

    @FXML
    private void submit(ActionEvent event) {
        item = ComboBox.getSelectionModel().getSelectedItem();
        if(item.equals("Links"))
        {
            try {
                count = 0;
                String text = textArea.getText();
                String query = "insert into Links values ('"+text+"')";
                statement.executeUpdate(query);
                textArea.setText("");
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Congratz!!!");
                alert.setHeaderText("Look At Below For The Information");
                alert.setContentText("Links Submitted SuccesFully");
                alert.showAndWait();
                
            } catch (SQLException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(item.equals("Quotes"))
        {
            try {
                count = 0;
                String text = textArea.getText();
                String query = "insert into Quotes values ('"+text+"')";
                statement.executeUpdate(query);
                textArea.setText("");
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Congratz!!!");
                alert.setHeaderText("Look At Below For The Information");
                alert.setContentText("Quotes Submitted SuccesFully");
                alert.showAndWait();
                
            } catch (SQLException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleDownload(ActionEvent event) {
        item = ComboBox.getSelectionModel().getSelectedItem();
        
        if(item.equals("Links") && count == 0)
        {
            try {
                count = 1;
                String query = "select * from Links";
                ResultSet resultSet = null; 
                resultSet =statement.executeQuery(query);
                
                while(resultSet.next())
                {
                    String link = resultSet.getString("links");
                    Links lenk = new Links(link);
                    linklist.add(lenk);
                }
                
                RandomAccessFile out = new RandomAccessFile("Links.txt","rw"); 
              
                for(int i=0; i<linklist.size(); i++)
                {
                    String output = linklist.get(i).getLink();
                    out.seek(out.length());
                    out.writeBytes(output+"\n");
                }
              
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Congratz!!!");
                alert.setHeaderText("Look At Below For The Information");
                alert.setContentText("Links Succesfully Downloades");
                alert.showAndWait();
                
            } catch (SQLException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(item.equals("Quotes") && count == 0)
        {
            try {
                count = 1;
                String queryy = "select * from Quotes"; 
                ResultSet resultSet = null;
                resultSet =statement.executeQuery(queryy);
                
                while(resultSet.next())
                {
                    String link = resultSet.getString("quotes");
                    Links lenk = new Links(link);
                    linklist.add(lenk);
                }
                
                RandomAccessFile out = new RandomAccessFile("Quotes.txt","rw"); 
              
                for(int i=0; i<linklist.size(); i++)
                {
                    String output = linklist.get(i).getLink();
                    out.seek(out.length());
                    out.writeBytes(output+"\n");
                }
              
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Congratz!!!");
                alert.setHeaderText("Look At Below For The Information");
                alert.setContentText("Quotes Succesfully Downloades");
                alert.showAndWait();
                
            } catch (SQLException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
