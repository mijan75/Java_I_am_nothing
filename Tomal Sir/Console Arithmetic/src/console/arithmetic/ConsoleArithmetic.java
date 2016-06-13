/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.arithmetic;

/**
 *
 * @author kmhasan
 */
public class ConsoleArithmetic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //int a = 10;
        //int b = 15;
        double c = ((25 + 40) / 30.0 - 13.0 / 2) / 5.0;
        if (c > 10)
            System.out.printf("Result %.3f\n", c);
        else System.out.printf("RESULT %.3f\n", c);
    }
    
}
