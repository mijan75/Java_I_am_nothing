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
public abstract class Shape {
    String name;

    public Shape(int count) {
        this.count = count;
    }
    static private int count;

    public Shape(String name) {
        this.name = name;
        count++;
    }

    public String getName() {
        return name;
    }
    
    public abstract double getarea();
    
    public double getperimeter(){
        return 0.0;
    }
    
    public void print(){
        System.out.printf("Shape name : %s\nPerimeter : %.2f\nArea : %.2f\n",getName(),getperimeter(),getarea());
    }

    public static int getCount() {
        return count;
    }
}
