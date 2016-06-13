/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class MultiThread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       MultiThread multiThread = new MultiThread();
       System.out.printf("%f\n",multiThread.getPi(121));
    }
    
    public double getPi(int terms){
        MultiThreadDemo multiThreadDemo = new MultiThreadDemo(0,terms/2);
        MultiThreadDemo multiThreadDemo1 = new MultiThreadDemo(terms/2+1,terms-1);
        multiThreadDemo.start();
        multiThreadDemo1.start();
        try {
            multiThreadDemo.join();
            multiThreadDemo1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        double pi = multiThreadDemo.getPi()+multiThreadDemo1.getPi();
        return pi;
    }
    
}
