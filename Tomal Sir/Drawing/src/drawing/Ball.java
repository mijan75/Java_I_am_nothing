/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author kmhasan
 */
public class Ball {

    private double radius;
    private double dx;
    private double dy;
    private double centerX;
    private double centerY;
    private int signX;
    private int signY;


    public Ball() {
        radius = 30;
        dx = 1;
        dy = 1;
        centerX = radius + 1;
        centerY = radius + 1;
        signX = +1;
        signY = +1;
    }

    public Ball(double radius, double dx, double dy, double centerX, double centerY, int signX, int signY) {
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
        this.centerX = centerX;
        this.centerY = centerY;
        this.signX = signX;
        this.signY = signY;
    }

    public void drawBall(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }
    
    public void update(double width, double height) {
        centerX = centerX + dx * signX;
        centerY = centerY + dy * signY;
        
        if (centerX < radius || centerX > width - radius)
            signX = -signX;
        if (centerY < radius || centerY > height - radius)
            signY = -signY;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean isColliding(Ball b) {
        Ball a = this;
        double distanceX = a.centerX - b.centerX;
        double distanceY = a.centerY - b.centerY;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        if (a.radius + b.radius >= distance)
            return true;
        else return false;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }
    public void setSignX(int signX) {
        this.signX = signX;
    }

    public void setSignY(int signY) {
        this.signY = signY;
    }

    public int getSignX() {
        return signX;
    }

    public int getSignY() {
        return signY;
    }
}
