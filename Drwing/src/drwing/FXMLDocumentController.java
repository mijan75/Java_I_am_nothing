/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drwing;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Canvas canvas;
    
    private void sinCurve(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double XMAX = canvas.getWidth();
        double YMAX = canvas.getHeight();
       
        gc.strokeLine(0, YMAX/2, XMAX, YMAX/2);
        gc.strokeLine(XMAX/2, 0, XMAX/2, YMAX);
        
        double h = Math.PI/2;
        for (double x1 = XMAX/2; x1 <= XMAX;x1 = x1+h)
        {
            double y1 = Math.sin(x1/20)*100+YMAX/2;
            double x2 = x1+h;
            double y2 = Math.sin(x2/20)*100+YMAX/2;
            
            gc.strokeLine(x1, y1, x2, y2);
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       sinCurve();
       
    }    
    
}
