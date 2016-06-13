/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.io.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 *
 * @author kmhasan
 */
public class FileIODemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Product a = new Product(100, "Red Leaf Marker", "Stationery");
        Product b = new Product(101, "Hot Wheels Car", "Toys");
        a.setProductName("Blue Leaf Marker");
        System.out.printf("%d %s\n", a.getProductId(), a.getProductName());
        System.out.printf("%d %s\n", b.getProductId(), b.getProductName());

        RandomAccessFile out = new RandomAccessFile("products.txt", "rw");
        out.writeBytes(a.getProductId() + " " + a.getProductName() + "\n");
        out.writeBytes(b.getProductId() + " " + b.getProductName() + "\n");
    }
    
}
