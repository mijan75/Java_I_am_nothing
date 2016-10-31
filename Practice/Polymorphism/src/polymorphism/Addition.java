/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphism;

/**
 *
 * @author mijan
 */
public class Addition extends Operation{
    
    public Addition(String name) {
        super(name);
    }
    
    public void result(int a, int b){
        int sum = a + b;
        System.out.println(name+" : "+sum);
    }
            
}
