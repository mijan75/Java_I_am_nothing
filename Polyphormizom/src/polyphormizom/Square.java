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
public class Square extends Shape {
    double length;

    public Square(double length){
        super("Square");
        this.length = length;
    }
    
    public Square(double length, String name) {
        super(name);
        this.length = length;
    }
    
    public Square(String name) {
        super(name);
    }

    @Override
    public double getperimeter() {
        return 4*length;
    }

    @Override
    public double getarea() {
        return length * length ;
    }
    
    
}
