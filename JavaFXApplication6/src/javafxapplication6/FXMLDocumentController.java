/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    private ArrayList<Image>imageList;
    private Label label;
    @FXML
    private ImageView iamgeAction;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imageList = new ArrayList<>();
        Image image = new Image("Mijan/Ayesha Takia.jpg");
        Image image1 = new Image("Mijan/Ayesha Takia1.jpg");
        imageList.add(image);
        imageList.add(image1);
        for (int i=0;i<imageList.size();i++)
        {
            iamgeAction.setImage(imageList.get(i));
        }
    }    
    
}
