/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencvdemo;

import org.opencv.core.Core;

/**
 *
 * @author mijanur
 */
public class OpenCVDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.load(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Hello");
    }
    
}
