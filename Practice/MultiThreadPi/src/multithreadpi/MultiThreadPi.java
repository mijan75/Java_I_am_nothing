/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadpi;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijan
 */
public class MultiThreadPi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int limit;
        Scanner sc = new Scanner(System.in);
        limit = sc.nextInt();
        
        ThreadedPi A = new ThreadedPi(0,limit/2);
        ThreadedPi B = new ThreadedPi(limit/2+1,limit-1);
        
        A.start();
        B.start();
        
        try {
            A.join();
            B.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiThreadPi.class.getName()).log(Level.SEVERE, null, ex);
        }
        double result1 = A.getPi();
        double result2 = B.getPi();
        
        double finalResult = result1 + result2;
        System.out.println(finalResult);
    }
    
}
