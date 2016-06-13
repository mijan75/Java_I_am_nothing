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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author mijan
 */
public class Mijan1Controller implements Initializable {
    private static Scene mainsecne;

    public static Scene getMainsecne() {
        return mainsecne;
    }
    
    
    
    @FXML
    private TextField textfield;
    @FXML
    private TextField textfield2;
    @FXML
    private TextField resuktgh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void asdf(ActionEvent event) throws IOException {
      JavaFXApplication2.getMainstage().setScene(JavaFXApplication2.getMainscene());
      JavaFXApplication2.getMainstage().show();
          
    }

    @FXML
    private void jklm(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mijan2.fxml"));
        
        Scene scene = new Scene(root);
        JavaFXApplication2.getMainstage().setScene(scene);
        JavaFXApplication2.getMainstage().show();
        
    }

    @FXML
    private void addkor(ActionEvent event) {
        int number_1 = Integer.parseInt(textfield.getText());
        int number_2 = Integer.parseInt(textfield2.getText());
        
        int result= number_1+number_2;
        
        resuktgh.setText(result+"");
    }
    
    
}
