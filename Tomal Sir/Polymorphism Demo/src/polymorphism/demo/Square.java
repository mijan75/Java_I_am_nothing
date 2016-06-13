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
public class Square extends Shape {
    double length;

    public Square(double length) {
        super("Square");
        this.length = length;
    }
    
    
    public Square(String name, double length) {
        super(name);
        this.length = length;
    }

    @Override
    public double getPerimeter() {
        return 4 * length;
    }

    @Override
    public double getArea() {
        return length * length;
    }
    
    
}
