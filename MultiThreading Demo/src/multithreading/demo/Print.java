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
public class Print extends Thread{
    String name;

    public Print(String name) {
        this.name = name;
    }

    public void print(){
        int  sum = 0;
        for (int  i=1;i<100; i++)
        System.out.printf("%s %d\n",name,i);
        
    }
    
    @Override
    public void run(){
        print();
    }
}
