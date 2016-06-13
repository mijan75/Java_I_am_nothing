/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.Scanner;

/**
 *
 * @author kmhasan
 */
public class Lab2Task1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Here is a simple program that takes the radius of the circle as input
        // and prints the area and the circumference of it
        
        // Prompt the user to enter the radius of the circle
        System.out.printf("Enter the radius of the circle\n");
        Scanner scanner = new Scanner(System.in);
        // Reads a line from the console and stores in a string
        String input = scanner.nextLine();
        
        // The input is read as a string, we need to convert it to double
        double radius = Double.parseDouble(input);
        double area = Math.PI * radius * radius;
        // I've shown you how to compute the area
        // EDIT THE FOLLOWING LINE TO COMPUTE THE CIRCUMFERENCE
        double circumference = 0.0;
        
        System.out.printf("The area of the circle is %.3f\n", area);
        System.out.printf("The circumference of the circle is %.3f\n", circumference);
    }
    
}
