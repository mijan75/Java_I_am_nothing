/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamining;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mijanur
 */
public class DataMining {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            RandomAccessFile input = new RandomAccessFile("input.txt", "r");
            RandomAccessFile output = new RandomAccessFile("output.txt", "rw");
            String line = null;
            
            while (true){
                line = input.readLine();
                if(line == null)
                      break;
                String []token = line.split(" ");
                double X2 = 9.1;
                double Y2 = 11.0;
                double X1 = Double.parseDouble(token[0]);
                double Y1 = Double.parseDouble(token[1]);

                
                double beforeSquare = ((X2 - X1) * (X2 - X1)) + ((Y2 - Y1) * (Y2 - Y1));
                double finalValue = Math.sqrt(beforeSquare);
                output.writeBytes(finalValue+"\n");
                System.out.printf( "%.3f -- %.3f == %.3f\n", X1, Y1, finalValue);

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataMining.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataMining.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
}
