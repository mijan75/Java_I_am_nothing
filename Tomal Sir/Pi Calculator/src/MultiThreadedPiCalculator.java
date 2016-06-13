
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kmhasan
 */
public class MultiThreadedPiCalculator {
    public double getPi(int terms) {
        // MODIFY THIS CODE SO THAT IT WORKS FOR numThreads = 100
        // OR FOR THAT MATTER ANY VALUE OF numThreads
        // YOU'LL NEED TO USE ARRAY AND FOR LOOPS
        int numThreads = 2;
        ThreadedPiCalculator thread1 = new ThreadedPiCalculator(0, terms / 2);
        ThreadedPiCalculator thread2 = new ThreadedPiCalculator(terms / 2 + 1, terms-1 );
        thread1.start();
        thread2.start();
        
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MultiThreadedPiCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        double pi = thread1.getPi() + thread2.getPi();
        return pi;
    }
}
