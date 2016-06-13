/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polyphormizom;

/**
 *
 * @author mijan
 */
public class Circle extends Shape {
    double radius;
    
    public Circle(double radius ){
        super("Circle");
        this.radius = radius;
    }
    
    public Circle(String name) {
        super(name);
    }
    
    @Override
    public double getarea(){
        return Math.PI * radius * radius;
    }
    
    @Override
    public double getperimeter(){
        return 2*Math.PI*radius;
    }
    
}
