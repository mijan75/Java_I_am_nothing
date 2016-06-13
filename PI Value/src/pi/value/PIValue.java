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
public class PIValue {

    public PIValue() {
        int from=1;
        int to=2000;
        
        PI p1 = new PI(from,to/2,"P1");
        PI p2 = new PI(to/2+1,to,"P2");
        
        p1.start();
        p2.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new PIValue();
    }
    
}
