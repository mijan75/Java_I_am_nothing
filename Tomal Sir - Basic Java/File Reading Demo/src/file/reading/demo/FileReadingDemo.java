/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.reading.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author kmhasan
 */
public class FileReadingDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.printf("Starting car database\n");
        try {
            RandomAccessFile in = new RandomAccessFile("cars.txt", "r");
            String line;
            String make;
            String model;
            int cc;
            int seats;
            
//            Car cars[] = new Car[5];
            ArrayList<Car> cars = new ArrayList<>();
//            int index = 0;
            while (true) {
                line = in.readLine();
                if (line == null || line.length() == 0)
                    break;
                make = line;
                line = in.readLine();
                model = line;
                line = in.readLine();
                cc = Integer.parseInt(line);
                line = in.readLine();
                seats = Integer.parseInt(line);
                Car car = new Car(make, model, cc, seats);
                cars.add(car);
//                cars[index] = car;
//                index = index + 1;
            }
            System.out.printf("Done reading the file\n");
            
//            for (int i = 0; i < cars.length; i++)
//                cars[i].print();
            for (int i = 0; i < cars.size(); i++)
                cars.get(i).print();
        } catch (FileNotFoundException fnfe) {
            System.err.printf("Could not find cars.txt\n");
        } catch (IOException ioe) {
            System.err.printf("Something odd happened\n");
        }
    }

}
