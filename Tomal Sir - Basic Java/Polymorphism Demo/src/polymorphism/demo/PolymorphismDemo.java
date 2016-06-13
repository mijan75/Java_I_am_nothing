/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphism.demo;

import java.util.ArrayList;

/**
 *
 * @author kmhasan
 */
public class PolymorphismDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Shape shape = new Shape("Random shape");
//        shape.print();
//        
//        Circle circle = new Circle(10);
//        circle.print();
//        
//        Square square = new Square(10);
//        square.print();
        
//        Shape shape1 = new Shape("Empty shape");
//        Shape shape2 = new Circle(12);
//        Shape shape3 = new Square(10);
//        
//        shape1.print();
//        shape2.print();
//        shape3.print();
        
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Square(15));
        shapes.add(new Circle(12));
        shapes.add(new Square(10));
        
        for (int i = 0; i < shapes.size(); i++)
            shapes.get(i).print();
        
        System.out.printf("Total shapes: %d\n", shapes.get(0).getCount());
        //System.out.printf("Total shape:s %d\n", Shape.getCount());
    }
    
}
