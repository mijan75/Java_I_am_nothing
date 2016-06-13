/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    private double radius = 30;
    private double dx = radius + 1;
    private double dy = radius + 1;
    private int signX = +1;
    private int signY = +1;

    ArrayList<Ball> balls;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = canvas.getGraphicsContext2D();
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        balls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Ball ball = new Ball();
            ball.setCenterX(radius + Math.random() * (width - radius * 2));
            ball.setCenterY(radius + Math.random() * (height - radius * 2));
            ball.setDx(Math.random() * 2);
            ball.setDy(Math.random() * 2);
            balls.add(ball);
        }

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(5),
                event -> {
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(0, 0, width, height);

                    for (int i = 0; i < balls.size(); i++) {
                        balls.get(i).drawBall(gc);
                        balls.get(i).update(width, height);
                        
                        for (int j = 0; j < balls.size(); j++) {
                            if (i != j) {
                                Ball a = balls.get(i);
                                Ball b = balls.get(j);
                                if (a.isColliding(b)) {
                                    double tx = a.getDx();
                                    double ty = a.getDy();
                                    a.setDx(b.getDx());
                                    a.setDy(b.getDy());
                                    b.setDx(tx);
                                    b.setDy(ty);
                                    int tsignX = a.getSignX();
                                    int tsignY = a.getSignY();
                                    a.setSignX(b.getSignX());
                                    a.setSignY(b.getSignY());
                                    b.setSignX(tsignX);
                                    b.setSignY(tsignY);
                                    // ADD CODE HERE TO
                                    // INTERCHANGE THE SIGNX/Y
                                    // VALUES BETWEEN BALL
                                    // a AND BALL b
                                }
                            }
                        }
                    }
                });
        
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();

    }

}
