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
public class PrintingNumber extends Thread{
    String name;
    private int from;
    private int to;
    private int result;
    
    public PrintingNumber(String name, int from, int to){
        this.name = name;
        this.from = from;
        this.to = to;
        result = 0;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
    
    public int getResult(){
        return result;
    }
    
    public void Add(){
        for(int i=from; i<=to; i++){
            result = result + i;
        }
    }
    
    @Override
    public void run(){
        Add();
    }
}
