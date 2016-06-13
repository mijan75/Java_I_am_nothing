/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField makeField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField ccField;
    @FXML
    private TextField seatsField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handleAddCarAction(ActionEvent event) throws FileNotFoundException, IOException {
        String make = makeField.getText();
        String model = modelField.getText();
        int cc = Integer.parseInt(ccField.getText());
        int seats = Integer.parseInt(seatsField.getText());
        
        Car car = new Car(make, model, cc, seats);
        
        RandomAccessFile out = new RandomAccessFile("cars.txt", "rw");
        out.seek(out.length());
        out.writeBytes(car.getMake() + "\n");
        out.writeBytes(car.getModel() + "\n");
        out.writeBytes(car.getCc() + "\n");
        out.writeBytes(car.getSeats() + "\n");
    }

    @FXML
    private void handleDeleteAllAction(ActionEvent event) {
    }
    
}
