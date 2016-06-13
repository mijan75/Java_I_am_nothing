/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kmhasan
 */
public class PiCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SingleThreadedPiCalculator singleThreaded = new SingleThreadedPiCalculator();
        MultiThreadedPiCalculator multiThreaded = new MultiThreadedPiCalculator();

        //System.out.printf("SingleThreaded Terms: %6d Pi: %.10f\n", 121, singleThreaded.getPi(121));
        System.out.printf("MultiThreaded  Terms: %6d Pi: %.10f\n", 10, multiThreaded.getPi(1000000000));
    }

}
