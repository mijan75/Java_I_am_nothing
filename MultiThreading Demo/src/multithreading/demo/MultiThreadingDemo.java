/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading.demo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class MultiThreadingDemo {
    public  MultiThreadingDemo(){
        long from = 1;
        long to = 100000000;
        
        Adder p1= new Adder(from,to/2);
        Adder p2= new Adder(to/2+1,to);
        
        p1.start();
        p2.start();
        
        try {
            p1.join();
            p2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiThreadingDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        long result1= p1.getResult();
        long result2 = p2.getResult();
        long result = result1+result2;
        
        
        
        System.out.printf("%d\n",result);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new MultiThreadingDemo();
    }
    
}
