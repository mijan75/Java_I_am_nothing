/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphism;

import java.util.ArrayList;

/**
 *
 * @author mijan
 */
public class Polymorphism {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Operation> list = new ArrayList<>();
        list.add(new Addition("addition")); 
        list.add(new Subtraction("subtraction"));
        
        for(int i=0; i<list.size(); i++){
            list.get(i).result(20, 10);
        }
        System.out.println(Operation.count);
            
    }
    
}
