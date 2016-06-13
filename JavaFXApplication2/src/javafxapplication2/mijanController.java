/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javafxapplication2.JavaFXApplication2.mainscene;

/**
 *
 * @author mijan
 */
public class mijanController implements Initializable {
    
    private Label label;
    @FXML
    private TextField number1;
    @FXML
    private TextField number2;
    @FXML
    private TextField resultField;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) throws IOException {
        int number_1 = Integer.parseInt(number1.getText());
        int number_2 = Integer.parseInt(number2.getText());
        
        int result= number_1+number_2;
        
        resultField.setText(result+"");
        
        Parent root = FXMLLoader.load(getClass().getResource("mijan1.fxml"));
        
        Scene scene = new Scene(root);
        JavaFXApplication2.getMainstage().setScene(scene);
        JavaFXApplication2.getMainstage().show();
            
    }
    
}
