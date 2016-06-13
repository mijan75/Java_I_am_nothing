/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphism.demo;

/**
 *
 * @author kmhasan
 */
public class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }
    
    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }
    
    public double getArea() {
        // for C/C++ use M_PI or acos(0)
        return Math.PI * radius * radius;
    }
    
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getRadius() {
        return radius;
    }

//    @Override
//    public void print() {
//        System.out.printf("Hello there!\n");
//    }
    
    
}
