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
public class CalculatePI {
    public double getPi(int terms){
        double pi = 0.0;
        for (int n=0;n<terms;n++)
        {
            if (n%2 == 0) pi = pi +1.0/(2*n+1);
            else pi = pi-1.0/(2*n+1);
        }
        return pi*4.0;
    }
    
}
