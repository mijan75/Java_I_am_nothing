/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pivaluecalculator;

/**
 *
 * @author mijan
 */
public class PivalueCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CalculatePI calculate = new CalculatePI();
        System.out.printf("%f\n",calculate.getPi(1));
    }
    
}
