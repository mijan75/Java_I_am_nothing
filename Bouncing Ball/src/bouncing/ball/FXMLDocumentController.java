/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bouncing.ball;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author mijan
 */
public class FXMLDocumentController implements Initializable {
    GraphicsContext gc;
    public double  radius = 30;
    public double dx = radius;
    public int sinX = +1;
    public int sinY = +1;
    public double dy = radius;
    @FXML
    private Canvas canvas;
    
    public void drawball(double X,double Y,double radius)
    {
        gc.setFill(Color.RED);
        gc.fillOval(X-radius, Y-radius, radius*2, radius*2);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gc = canvas.getGraphicsContext2D();
        double height = canvas.getHeight();
        double width = canvas.getWidth();
        
        Timeline timeline = new Timeline();
        KeyFrame keyframe = new KeyFrame(Duration.millis(05),
        event -> {
            gc.setFill(Color.YELLOWGREEN);
            gc.fillRect(0, 0, width, height);
            drawball(dx,dy,30);
            dx = dx +2 *sinX;
            dy = dy +1.5 *sinY;
            
            
            
            if (dx >= width - radius || dx <= radius)
            {
                sinX = -sinX;
            }
            if (dy >= height - radius || dy <= radius)
            {
                sinY = -sinY;
            }
        });
        
        timeline.getKeyFrames().add(keyframe);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();
    }    
    
}
