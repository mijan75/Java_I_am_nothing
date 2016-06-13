/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salespoint;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author mijan
 */
public class SalesPoint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        POS a=new POS("Mijan",2400,10);
        POS b=new POS("Rasib",3200,20);
        System.out.printf("%s %d %d\n",a.getproductName(),a.getproductId(),a.getproductQuantity());
        System.out.printf("%s %d %d\n",b.getproductName(),b.getproductId(),b.getproductQuantity());
        RandomAccessFile out=new RandomAccessFile("Product.txt","rw");
        out.writeBytes(a.getproductName()+ " " +a.getproductId()+"\n");
        out.writeBytes(b.getproductName()+ " " +b.getproductId()+"\n");
    }
    
}
