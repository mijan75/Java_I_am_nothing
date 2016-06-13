/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kmhasan
 */
public class SingleThreadedPiCalculator {
    public double getPi(int terms) {
        double pi = 0.0;
        for (int n = 0; n < terms; n++) {
            if (n % 2 == 0)
                pi += 1.0 / (2 * n + 1);
            else pi -= 1.0 / (2 * n + 1);
        }
        return pi * 4.0;
    }
}
