/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithread;

/**
 *
 * @author mijan
 */
public class MultiThreadDemo extends Thread{
    int startTime ;
    int endTime;
    double pi= 0.0;

    public MultiThreadDemo(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public double getPi() {
        return pi;
    }
    
    @Override
    public void run (){
        
        for (int n=startTime;n<=endTime;n++)
        {
            if (n%2 == 0) pi = pi +1.0/(2*n+1);
            else pi = pi-1.0/(2*n+1);
        }
        pi =  pi*4.0;
    }
    
    
}
