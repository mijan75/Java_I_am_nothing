/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.value;

/**
 *
 * @author mijan
 */
public class PI extends Thread{
    private String name;
    private int from;
    private int to;

    public PI(int from, int to,String name) {
        this.from = from;
        this.to = to;
        this.name = name;
    }


    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    
    public void add(){
        double mpi=4;
        double j=3;
        
        for (int i=from;i<to;i++)
        {
            mpi = mpi-(4/j)+(4/(j+2));
            j=j+4;
            System.out.printf("%s %f\n",name,mpi);
        }
            
    }
    
    @Override
    public void run(){
        add();
    }
    
}
