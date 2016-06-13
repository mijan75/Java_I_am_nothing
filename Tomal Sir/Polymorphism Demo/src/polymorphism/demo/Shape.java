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
public abstract class Shape {
    String name;
    private static int count;
    
    public Shape(String name) {
        this.name = name;
        count++;
    }

    public String getName() {
        return name;
    }
    
    public abstract double getArea();
    
    public double getPerimeter() {
        return 0.0;
    }
    
    public void print() {
        System.out.printf("Shape: %s\nArea: %.2f\nPerimeter: %.2f\n", getName(), getArea(), getPerimeter());
    }

    public static int getCount() {
        return count;
    }
}
