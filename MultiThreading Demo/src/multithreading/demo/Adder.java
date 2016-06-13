/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading.demo;

/**
 *
 * @author mijan
 */
public class Adder extends Thread{
    public long from ;
    public long to ;
    public long result=0;

    public Adder(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }

    public long getResult() {
        return result;
    }
    
    public long add(){
        long sum=0;
        for (long i=from;i<=to;i++)
        {
            sum = sum+i;
        }
        return sum;    
    }
    @Override
    public void run (){
        result= add();
    }
}
