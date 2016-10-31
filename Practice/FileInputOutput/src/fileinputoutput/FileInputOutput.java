/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileinputoutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author mijan
 */
public class FileInputOutput {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            RandomAccessFile in = new RandomAccessFile("input.txt","r");
            RandomAccessFile out = new RandomAccessFile("output.txt","rw");
            String line1;
            while(true)
            {
                line1= in.readLine();
                if(line1.length()==0 || line1==null)
                    break;
                out.writeBytes(line1+"\n");
                System.out.println(line1);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
