/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.car.program;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField makedisplayField;
    @FXML
    private TextField modelDiaplayField;
    @FXML
    private TextField ccfield;
    @FXML
    private TextField seatDisplayFiled;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAddAction(ActionEvent event) throws FileNotFoundException, IOException {
        String make=makedisplayField.getText();
        String model=modelDiaplayField.getText();
        int cc=Integer.parseInt(ccfield.getText());
        int seats=Integer.parseInt(seatDisplayFiled.getText());
        
        car Car=new car(make,model,cc,seats);
        RandomAccessFile out = new RandomAccessFile("car.txt","rw");
        out.seek(out.length());
        out.writeBytes(Car.getmakeName()+"\n");
        out.writeBytes(Car.getmodelName()+"\n");
        out.writeBytes(Car.getCC()+"\n");
        out.writeBytes(Car.getcarSeats()+"\n");
        Car.print();
    }

    @FXML
    private void handleDeleteAction(ActionEvent event) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("car.txt");
        writer.print("");
        writer.close();
    }
    
}
