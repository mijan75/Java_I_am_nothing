/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithread;

/**
 *
 * @author mijan
 */
public class MultiThread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int dataRange = 100000;
        PrintingNumber A = new PrintingNumber("A",1,dataRange/2);
        PrintingNumber B = new PrintingNumber("B",dataRange/2+1,dataRange);
        
        A.start();
        B.start();
        
        A.join();
        B.join();
        
        int result1 = A.getResult();
        int result2 = B.getResult();
        int sum = result1 + result2;
        System.out.println("Sum is : " + sum);
    }
    
}
