/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kmhasan
 */
public class ThreadedPiCalculator extends Thread {
    private int startTerm;
    private int endTerm;
    private double pi;
    
    public ThreadedPiCalculator(int startTerm, int endTerm) {
        this.startTerm = startTerm;
        this.endTerm = endTerm;
        this.pi = 0.0;
    }
    
    @Override
    public void run() {
        pi = 0.0;
        for (int n = startTerm; n <= endTerm; n++) {
            if (n % 2 == 0)
                pi += 1.0 / (2 * n + 1);
            else pi -= 1.0 / (2 * n + 1);
        }
        pi = pi * 4.0;        
    }

    public int getStartTerm() {
        return startTerm;
    }

    public int getEndTerm() {
        return endTerm;
    }

    public double getPi() {
        return pi;
    }
    
    
}
