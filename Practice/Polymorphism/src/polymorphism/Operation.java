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
public abstract class Operation {
    String name;
    public static int count=0;

    public Operation(String name) {
        this.name = name;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void amountOfObject(){
        System.out.println(count);
    }
            
    public abstract void result(int a, int b);
}
