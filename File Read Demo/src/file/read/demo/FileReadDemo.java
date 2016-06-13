/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.read.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author mijan
 */
public class FileReadDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            RandomAccessFile in = new RandomAccessFile("cars.txt","r");
            String line;
            String make;
            String model;
            int cc;
            int seats;
            int i;
            ArrayList<car> Cars=new ArrayList<car>();
            while (true){
                line= in.readLine();
                if (line==null){
                    break;
                }
                make = line;
                line = in.readLine();
                model = line;
                line = in.readLine();
                cc=Integer.parseInt(line);
                line = in.readLine();
                seats = Integer.parseInt(line);
                car Car = new car(make,model,cc,seats);
                Cars.add(Car);
            }
            for(i=0;i<Cars.size();i++){
            Cars.get(i).print();
        }
        }catch(FileNotFoundException fnfe){
            System.err.println("File Not Found\n");
        }catch(IOException ioe){
            System.err.println("Something odd happen\n");
        }
        
    }
    
}
