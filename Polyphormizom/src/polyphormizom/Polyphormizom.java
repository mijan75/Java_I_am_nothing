/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polyphormizom;

import java.util.ArrayList;

/**
 *
 * @author mijan
 */
public class Polyphormizom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Shape> shapes; 
        shapes = new ArrayList<Shape>();
        shapes.add(new Circle(10)); 
        shapes.add(new Square(10)); 
        shapes.add(new Circle(15));
        shapes.add(new Square(15));
        
        for (int i=0;i<shapes.size();i++)
            shapes.get(i).print();
        System.out.printf("Total shapes : %d\n",shapes.get(0).getCount());
        System.out.printf("Total shapes : %d\n",Shape.getCount());
    }
    
}
