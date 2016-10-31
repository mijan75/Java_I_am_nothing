/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinputoutput1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
  
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    public ArrayList<Student>list;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list = new ArrayList<>();
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(null);
        try {
            RandomAccessFile input = new RandomAccessFile(file,"r");
            String line;
            String name;
            int id;
            while(true)
            {
                line = input.readLine();
                
                if(line==null || line.length()<0)
                    break;
                
                name = line;
                line = input.readLine();
                id = Integer.parseInt(line);
                
                Student student = new Student(name, id);
                list.add(student);
            }
            System.out.println("File Reading Complete");
            for(int i=0; i<list.size(); i++)
            {
                System.out.println(list.get(i).toString());
            }
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
