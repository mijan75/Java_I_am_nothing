/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.browsing.option;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextArea displayField;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
          FileChooser fileChooser = new FileChooser();
          File file = fileChooser.showOpenDialog(null);
          try {
               RandomAccessFile input = new RandomAccessFile(file,"r");
               String line ;
               while(true){
                    line = input.readLine();
                    if(line == null || line.length()<0){
                        break;
                    }
                    System.out.println(line);
               }
          }catch (FileNotFoundException fnfe){
              System.err.println("Could not find the file\n");
          }catch (IOException ioe){
              System.err.println("Something odd Happen\n");
          }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
