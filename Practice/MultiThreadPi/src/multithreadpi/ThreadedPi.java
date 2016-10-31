/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadpi;

/**
 *
 * @author mijan
 */
public class ThreadedPi extends Thread{
    int start;
    int end;
    double pi;

    public ThreadedPi(int start, int end) {
        this.start = start;
        this.end = end;
        pi = 0.0;
    }

    public double getPi() {
        return pi;
    }
    
    @Override
    public void run(){
        for(int i=start; i<=end; i++){
            if(i%2==0) pi = pi + 1.0/(2*i+1);
            else pi = pi - 1.0/(2*i+1);
        }
        pi = pi * 4.00;
    }
}
